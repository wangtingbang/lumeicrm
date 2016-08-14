package com.lumei.crm.customer.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.alibaba.fastjson.JSONObject;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.DateTimeUtil;
import com.lumei.crm.commons.util.KeyGenerator;
import com.lumei.crm.customer.biz.CarDealBusiness;
import com.lumei.crm.customer.biz.CustomerBusiness;
import com.lumei.crm.customer.biz.TransactionBusiness;
import com.lumei.crm.customer.biz.UserInfoBusiness;
import com.lumei.crm.customer.constants.LumeiCrmConstants;
import com.lumei.crm.customer.dto.CarDeal;
import com.lumei.crm.customer.dto.CarDealQueryParam;
import com.lumei.crm.customer.dto.Customer;
import com.lumei.crm.customer.dto.Transaction;
import com.lumei.crm.customer.entity.TTransaction;
import com.lumei.crm.util.SessionUtil;

/**
 * Created by wangtingbang on 16/8/13.
 */
@Controller
@RequestMapping(value = "cardeal")
public class CarDealController {

  private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private CustomerBusiness customerBusiness;
  
  @Autowired
  UserInfoBusiness userInfoBusiness;
  
  @Autowired
  CarDealBusiness carDealBusiness;

  @Autowired
  TransactionBusiness transactionBusiness;

  @RequestMapping(value = "list", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView("cardeal/listcardeal");
    SessionUtil.setAttributes("dateStart", 
    		DateTimeUtil.fromDate(DateTimeUtil.today(), DateTimeUtil.Pattern.DEFAULT_FORMATE_DATE));
    SessionUtil.setAttributes("dateEnd",
    		DateTimeUtil.fromDate(DateTimeUtil.plusMonths(DateTimeUtil.today(), 1), DateTimeUtil.Pattern.DEFAULT_FORMATE_DATE));
    return mav;
  }

  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<CarDeal> list(CarDealQueryParam carDeal, int page, int limit){
      log.debug("list param, page:{}, limit:{}, param:{}",
        page, limit, JSONObject.toJSONString(carDeal));

    Map param = new HashMap();

    if (StringUtils.isNotBlank(carDeal.getSalesId())) {
    	param.put("salesId", carDeal.getSalesId());
    }
    if (StringUtils.isNotBlank(carDeal.getCustomerName())) {
    	param.put("customerName", carDeal.getCustomerName());
    }
    if (StringUtils.isNotBlank(carDeal.getWechat())) {
        param.put("wechat", carDeal.getWechat());
    }
    if (StringUtils.isNotBlank(carDeal.getPhone())) {
        param.put("phone", carDeal.getPhone());
    }
    if (Byte.parseByte("0")!=carDeal.getDealStatus()) {
        param.put("dealStatus", carDeal.getDealStatus());
    }
    if (Byte.parseByte("0")!=carDeal.getRating()) {
        param.put("rating", carDeal.getRating());
    }
    if (null!=carDeal.getRating()) {
        param.put("dealDateStart", carDeal.getDealDateStart());
    }
    if (null!=carDeal.getRating()) {
    	param.put("dealDateEnd", carDeal.getDealDateEnd());
    }
    param.put("orderBycolumn", "deal_date");
    
    Pagination<CarDeal> pg = carDealBusiness.selectForList(param, page, limit);
    
    if(pg.getResult()==null||pg.getResult().size()<0){
        log.debug("result null");
      return Pagination.newInstance(1, 1, 0);
    }else{
      List<CarDeal> carDeal_s = pg.getResult();
      List<String> uids = new ArrayList<>();
      for (CarDeal deal: carDeal_s) {
        String salesId = deal.getSalesId();
        String createUserId = deal.getCreateUserId();
        String updateUserId = deal.getUpdateUserId();
        uids.add(salesId);
        uids.add(createUserId);
        uids.add(updateUserId);
      }

      List<CarDeal> newResult = new ArrayList<>();
      Map<String,OpAuthUser> userMap = userInfoBusiness.getUserInfoById(uids);
      if(userMap!=null) {
        for (CarDeal deal: carDeal_s) {
          String salesId = deal.getSalesId();
          OpAuthUser sales = userMap.get(salesId);
          if (sales != null) {
            deal.setSalesName(sales.getNickName());
          }

          String createUserId = deal.getCreateUserId();
          OpAuthUser createUser = userMap.get(createUserId);
          if(createUser!=null){
            deal.setCreateUserName(createUser.getNickName());
          }

          String updateUserId = deal.getCreateUserId();
          OpAuthUser updateUser = userMap.get(updateUserId);
          if(updateUser!=null){
            deal.setUpdateUserName(updateUser.getNickName());
          }

          newResult.add(deal);
        }
      }else{
        newResult = carDeal_s;
      }
      pg.setResult(newResult);
    }
    return pg;
  }

  @RequestMapping(value = "create", method = RequestMethod.GET)
  public ModelAndView create(String customerId) {
    ModelAndView mav = new ModelAndView("cardeal/cardeal");
    SessionUtil.setAttributes("serviceId", "0");
    SessionUtil.setAttributes("customerId", customerId);
    return mav;
  }
  
  @RequestMapping(value = "get", method = RequestMethod.GET)
  public ModelAndView get(String id,String customerId) {
    ModelAndView mav = new ModelAndView("cardeal/cardeal");
    SessionUtil.setAttributes("serviceId", id);
    SessionUtil.setAttributes("customerId", customerId);
    return mav;
  }
  
  @RequestMapping(value = "get/getCardeal", method = RequestMethod.GET)
  @ResponseBody
  public CarDeal getDetails(String id, String customerId){
	  String customerName = "";
	  Customer cust = customerBusiness.find(customerId, Customer.class);
	  if(null != cust){
		  customerName = cust.getName();
	  }
	  if ("0".equals(id)) {
		  CarDeal p = new CarDeal();
	    	p.setId("0");
	    	p.setCustomerId(customerId);
	    	p.setCustomerName(customerName);
	    	p.setSalesName(SessionUtil.getCurrentUserNickName());
	      return p;
	    }
    CarDeal carDeal = carDealBusiness.find(id, CarDeal.class);
    if (carDeal == null || StringUtils.isBlank(carDeal.getId())) {
    	CarDeal p = new CarDeal();
    	p.setId("1");
      return p;
    }
    carDeal.setCustomerName(customerName);
    if(SessionUtil.salesReadonly(carDeal.getSalesId())){
    	carDeal.setReadonly(true);
    }
    List<String> salesIds = new ArrayList<String>();
    salesIds.add(carDeal.getSalesId());
    Map<String,OpAuthUser> userMap = userInfoBusiness.getUserInfoById(salesIds);
    OpAuthUser user0 = userMap.get(carDeal.getSalesId());
    if (user0 != null) {
    	carDeal.setSalesName(user0.getNickName());
    }
    return carDeal;
  }

  @RequestMapping(value = "save", method = RequestMethod.POST)
  @ResponseBody
  public String save(CarDeal carDeal){
	log.debug("saveCarDeal, param:{}", carDeal==null?null:JSONObject.toJSONString(carDeal));
	Date now = DateTimeUtil.now();
    if ("0".equals(carDeal.getId())) {
    	String id = KeyGenerator.uuid();
        carDeal.setId(id);
    	carDeal.setCreateTime(now);
    	carDeal.setUpdateTime(now);
    	carDeal.setSalesId(SessionUtil.getCurrentUserId());
    	carDeal.setCreateUserId(SessionUtil.getCurrentUserId());
    	carDeal.setUpdateUserId(SessionUtil.getCurrentUserId());
    	carDealBusiness.create(carDeal);
    	Transaction transaction = new Transaction();
        transaction.setServiceId(id);
        transaction.setUserId(carDeal.getCustomerId());
        transaction.setServiceType(LumeiCrmConstants.SERVICE_TYPE.CAR_SELLING.getValue());
        transaction.setCreateTime(now);
        transaction.setCreateUserId(SessionUtil.getCurrentUserId());
        transaction.setUpdateTime(now);
        transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
        transactionBusiness.create(transaction);
    } else {
    	carDeal.setUpdateTime(now);
    	carDeal.setUpdateUserId(SessionUtil.getCurrentUserId());
    	carDealBusiness.update(carDeal);
    	Example<TTransaction> example0 = Example.newExample(TTransaction.class);
        example0.param("serviceId", carDeal.getId());
        List<Transaction> transactions = transactionBusiness.list(example0);
        Transaction transaction = transactions.get(0);
        transaction.setUpdateTime(now);
        transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
        transactionBusiness.update(transaction);
    }
      log.debug("result:{}",carDeal.getId());
    return carDeal.getId();
  }

  @RequestMapping(value = "delete", method = RequestMethod.POST)
  @ResponseBody
  public String delete(String id) {
    int result;
      log.debug("delete carDeal:{}, opId:{},opName:{}", id, SessionUtil.getCurrentUserId(),
        SessionUtil.getCurrentUserName());
      result = carDealBusiness.delete(id, CarDeal.class);
      log.debug("result:{}", result);
      Example<TTransaction> example0 = Example.newExample(TTransaction.class);
      example0.param("serviceId", id);
      List<Transaction> transactions = transactionBusiness.list(example0);
      if(null != transactions && transactions.size() >0){
      Transaction transaction = transactions.get(0);
      transactionBusiness.delete(transaction.getId(),Transaction.class);
      }
    return 1 == result ? "success" : "fail";
  }

}
