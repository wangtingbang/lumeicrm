package com.lumei.crm.customer.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.DateTimeUtil;
import com.lumei.crm.customer.biz.CustomerBusiness;
import com.lumei.crm.customer.biz.UserInfoBusiness;
import com.lumei.crm.customer.dto.Customer;
import com.lumei.crm.customer.entity.TCustomer;
import com.lumei.crm.util.SessionUtil;

/**
 * Created by wangtingbang on 15/7/30.
 */
@Controller
@RequestMapping(value = "customer")
public class CustomerController {
  private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private CustomerBusiness customerBusiness;
  @Autowired
  UserInfoBusiness userInfoBusiness;
  
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
    int page, int limit,//
    String orderColumn, boolean orderDesc) {

    Example<TCustomer> example = Example.newExample(TCustomer.class);
    if (!StringUtils.isBlank(sales)) {
        example.param("salesId", sales);
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
    if(StringUtils.isNoneBlank(orderColumn)){
    	example.orderBy(orderColumn);
    	if(orderDesc){
    		example.desc();
    	}
    }else{
    	example.orderBy("createTime").desc();
    }
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
  
}
