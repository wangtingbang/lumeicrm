package com.lumei.crm.customer.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lumei.crm.auth.biz.OpAuthUserBusiness;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.auth.entity.TOpAuthUser;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.DateTimeUtil;
import com.lumei.crm.customer.biz.CustomerBusiness;
import com.lumei.crm.customer.biz.NotesBusiness;
import com.lumei.crm.customer.biz.UserInfoBusiness;
import com.lumei.crm.customer.dto.Customer;
import com.lumei.crm.customer.dto.Notes;
import com.lumei.crm.customer.entity.TCustomer;
import com.lumei.crm.customer.entity.TNotes;
import com.lumei.crm.util.SessionUtil;

@Controller
@RequestMapping(value = "customer")
public class CustomerController {
  private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private CustomerBusiness customerBusiness;
  @Autowired
  UserInfoBusiness userInfoBusiness;
  @Autowired
  OpAuthUserBusiness opAuthUserBusiness;
  @Autowired
  NotesBusiness notesBusiness;
  
  @RequestMapping(value = "list", method = RequestMethod.GET)
  public ModelAndView listCustomer() {
    ModelAndView mav = new ModelAndView("customer/listCustomer");
    return mav;
  }
  
  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<Customer> listCustomer(//
    String name,  //
    String wechat, //
    String phone,  //
    String sales,//
    String salesName,//
    int page, int limit,//
    String orderColumn, boolean orderDesc) {
	  return process(name, wechat, phone, sales, salesName, page, limit, orderColumn, orderDesc);
  }
  
  @RequestMapping(value = "export")
  public String export(
		  String name,  //
		String wechat, //
		String phone,  //
		String sales,//
		String salesName,//
		Model model, HttpServletResponse response)throws UnsupportedEncodingException {
		Pagination<Customer> pg = process(name, wechat, phone, sales, salesName, 1, 65535, null, false);
		List result = new ArrayList();
		if(pg != null && pg.getResult() != null){
			result = pg.getResult();
		}
	    String filename = "Customer.csv";
	    String downloadName = new String(filename.getBytes("utf-8"), "iso8859-1");
	    response.setContentType("application/x-msdownload");
	    response.setHeader("Content-Disposition", "attachment;filename=" + downloadName);
	    model.addAttribute("result", result);
	    return "xls/customer";
	}
  
  private Pagination<Customer> process(//
    String name,  //
    String wechat, //
    String phone,  //
    String sales,//
    String salesName,//
    int page, int limit,//
    String orderColumn, boolean orderDesc) {

    Example<TCustomer> example = Example.newExample(TCustomer.class);
    if (!StringUtils.isBlank(salesName) && StringUtils.isBlank(sales)) {
    	Example<TOpAuthUser> example2 = Example.newExample(TOpAuthUser.class);
    	example2.paramLikeTo("nickName", salesName);
    	List<OpAuthUser> l = opAuthUserBusiness.list(example2);
    	if(l != null && l.size()>0){
    		List<String> ll = new LinkedList<String>();
    		for (OpAuthUser id : l) {
    			ll.add(id.getId());
    		}
    		example.paramIn("salesId", ll);
    	}
    }
    if (StringUtils.isNotBlank(sales)) {
        example.paramEqualTo("salesId", sales);
    }
    
    if (!StringUtils.isBlank(name)) {
      example.paramLikeTo("name", name);
    }
    if (!StringUtils.isBlank(wechat)) {
      example.paramLikeTo("wechat", wechat);
    }
    if (!StringUtils.isBlank(phone)) {
      example.paramLikeTo("phone", phone);
    }
    if("SALES_ID".equals(orderColumn)){
    	if(orderDesc){
    		example.orderByDesc(orderColumn);
    	}else{
    		example.orderBy(orderColumn);
    	}
    	example.orderByDesc("UPDATE_TIME");
    }
    if("UPDATE_TIME".equals(orderColumn)){
    	if(orderDesc){
    		example.orderByDesc(orderColumn);
    	}else{
    		example.orderBy(orderColumn);
    	}
    	example.orderBy("SALES_ID");
    }
    example.orderByDesc("CREATE_TIME");
    Pagination<Customer> profilePagination = customerBusiness.listByPage(example, page, limit);

    if (profilePagination == null) {
      profilePagination = Pagination.newInstance(page, limit, 0);
      profilePagination.setResult(new ArrayList<Customer>());
      return profilePagination;
    } else if (profilePagination.getResult() == null || profilePagination.getResult().size() < 1) {
      return profilePagination;
    } else {
      List<Customer> profileList = profilePagination.getResult();
      List<String> salesIds = new ArrayList<>();
      for (Customer customer : profileList) {
        String salesId = customer.getSalesId();
        salesIds.add(salesId);
      }
      List<Customer> newResult = new ArrayList<>();
      Map<String,OpAuthUser> userMap = userInfoBusiness.getUserInfoById(salesIds);
      if(userMap!=null) {
        for (Customer customer : profileList) {
		Example<TNotes> example2 = Example.newExample(TNotes.class);
		example2.paramEqualTo("serviceId", customer.getId()).orderByDesc("create_time");
		List<Notes> l = notesBusiness.list(example2);
		if(l!=null && l.size() >0){
			customer.setLatestNotes(l.get(0).getContent());
		}
          String salesId = customer.getSalesId();
          OpAuthUser user = userMap.get(salesId);
          if (user != null) {
        	customer.setSales(user.getNickName());
            newResult.add(customer);
          } else{
            newResult.add(customer);
          }
        }
      }else{
        newResult = profileList;
      }
      profilePagination.setResult(newResult);
    }
    return profilePagination;
  }

  @RequestMapping(value = "create", method = RequestMethod.GET)
  public ModelAndView createCustomer() {
    ModelAndView mav = new ModelAndView("customer/customer");
    SessionUtil.setAttributes("customerId", "0");
    return mav;
  }

  @RequestMapping(value = "get", method = RequestMethod.GET)
  public ModelAndView getCustomer(String customerId) {
    ModelAndView mav = new ModelAndView("customer/customer");
    SessionUtil.setAttributes("customerId", customerId);
    return mav;
  }
  
  @RequestMapping(value = "get/getCustomer", method = RequestMethod.GET)
  @ResponseBody
  public Customer getCustomerProfile(String customerId) {
    log.debug("param, customerId:{}", customerId);
    if ("0".equals(customerId)) {
    	Customer p = new Customer();
    	p.setId("0");
      return p;
    }

    Customer profile = customerBusiness.find(customerId, Customer.class);
    if (profile == null || StringUtils.isBlank(profile.getId())) {
    	Customer p = new Customer();
    	p.setId("1");
      return p;
    }

    if(SessionUtil.salesReadonly(profile.getSalesId())){
        profile.setReadonly(true);
    }

    List<String> salesIds = new ArrayList<String>();
    salesIds.add(profile.getSalesId());
    Map<String,OpAuthUser> userMap = userInfoBusiness.getUserInfoById(salesIds);
    OpAuthUser user0 = userMap.get(profile.getSalesId());
    if (user0 != null) {
    	profile.setSales(user0.getNickName());
    }
    return profile;
  }

  @RequestMapping(value = "save", method = RequestMethod.POST)
  @ResponseBody
  public String saveProfile(Customer customer) {
    Date now = DateTimeUtil.now();
    if ("0".equals(customer.getId())) {
    	customer.setCreateTime(now);
    	customer.setUpdateTime(now);
    	customer.setCreateUserId(SessionUtil.getCurrentUserId());
    	customer.setUpdateUserId(SessionUtil.getCurrentUserId());
    	customerBusiness.create(customer);
    } else {
    	customer.setUpdateTime(now);
    	customer.setUpdateUserId(SessionUtil.getCurrentUserId());
    	customerBusiness.update(customer);
    }

    return customer.getId();
  }
  
  @RequestMapping(value = "delete", method = RequestMethod.POST)
  @ResponseBody
  private String deleteProfile(String id){
    return customerBusiness.delete(id, Customer.class)==1?"success":"fail";
  }  
  
  @RequestMapping(value = "assign", method = RequestMethod.POST)
  @ResponseBody
  public String assign(String sales, @RequestParam("ids[]") String[] ids) {
    Date now = DateTimeUtil.now();
    for (int i = 0; i < ids.length; i++) {
    Customer customer = new Customer();
    customer.setId(ids[i]);
    customer.setSalesId(sales);
	customer.setUpdateTime(now);
	customer.setUpdateUserId(SessionUtil.getCurrentUserId());
	customerBusiness.updateSelective(customer);
    }
    return sales;
  }
}
