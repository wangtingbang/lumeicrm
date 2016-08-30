package com.lumei.crm.customer.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lumei.crm.auth.biz.OpAuthUserBusiness;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.auth.entity.TOpAuthUser;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.DateTimeUtil;
import com.lumei.crm.commons.util.KeyGenerator;
import com.lumei.crm.customer.biz.CarDealBusiness;
import com.lumei.crm.customer.biz.CustomerBusiness;
import com.lumei.crm.customer.biz.NotesBusiness;
import com.lumei.crm.customer.biz.TransactionBusiness;
import com.lumei.crm.customer.biz.UserInfoBusiness;
import com.lumei.crm.customer.constants.LumeiCrmConstants;
import com.lumei.crm.customer.dao.SequenceDao;
import com.lumei.crm.customer.dto.CarDeal;
import com.lumei.crm.customer.dto.CarDealQueryParam;
import com.lumei.crm.customer.dto.Customer;
import com.lumei.crm.customer.dto.Notes;
import com.lumei.crm.customer.dto.Transaction;
import com.lumei.crm.customer.entity.TNotes;
import com.lumei.crm.customer.entity.TSequence;
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
  
  @Autowired
  OpAuthUserBusiness opAuthUserBusiness;

  @Autowired
  NotesBusiness notesBusiness;

  @Autowired
  SequenceDao sequenceDao;
  
  @RequestMapping(value = "list", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView("cardeal/listcardeal");
    SessionUtil.setAttributes("dateStart", 
    		DateTimeUtil.fromDate(DateTimeUtil.today(), DateTimeUtil.Pattern.DEFAULT_FORMATE_DATE));
    SessionUtil.setAttributes("dateEnd",
    		DateTimeUtil.fromDate(DateTimeUtil.plusMonths(DateTimeUtil.today(), 1), DateTimeUtil.Pattern.DEFAULT_FORMATE_DATE));
    return mav;
  }

  @RequestMapping(value = "list2", method = RequestMethod.GET)
  public ModelAndView list2() {
    ModelAndView mav = new ModelAndView("cardeal/listcardeal2");
    SessionUtil.setAttributes("dateStart", 
    		DateTimeUtil.fromDate(DateTimeUtil.plusDays(DateTimeUtil.today(), -1), DateTimeUtil.Pattern.DEFAULT_FORMATE_DATE));
    SessionUtil.setAttributes("dateEnd",
    		DateTimeUtil.fromDate(DateTimeUtil.plusDays(DateTimeUtil.today(), -1), DateTimeUtil.Pattern.DEFAULT_FORMATE_DATE));
    return mav;
  }
  
  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<CarDeal> list(CarDealQueryParam carDeal, int timezoneOffset, int page, int limit){
      log.debug("list param, page:{}, limit:{}, param:{}",
        page, limit, JSONObject.toJSONString(carDeal));

    Map param = new HashMap();
    List<Map> orderList = new LinkedList<Map>();
    if(StringUtils.isBlank(carDeal.getOrderColumn())){
    	carDeal.setOrderColumn("UPDATE_TIME");
    	carDeal.setOrderDesc(true);
    }
    if("UPDATE_TIME".equals(carDeal.getOrderColumn())){
    	Map m1 = new HashMap();
    	m1.put("column", "CAR_DEAL.UPDATE_TIME");
    	if(carDeal.isOrderDesc()){
    		m1.put("desc", true);
    	}else{
    		m1.put("desc", false);
    	}
    	orderList.add(m1);
    	Map m2 = new HashMap();
    	m2.put("column", "CAR_DEAL.SALES_ID");
    	m2.put("desc", false);
    	orderList.add(m2);
    }
    if("DEAL_DATE".equals(carDeal.getOrderColumn())){
    	Map m1 = new HashMap();
    	m1.put("column", "CAR_DEAL.DEAL_DATE");
    	if(carDeal.isOrderDesc()){
    		m1.put("desc", true);
    	}else{
    		m1.put("desc", false);
    	}
    	orderList.add(m1);
    	Map m2 = new HashMap();
    	m2.put("column", "CAR_DEAL.SALES_ID");
    	m2.put("desc", false);
    	orderList.add(m2);
    }
    if("DEAL_STATUS".equals(carDeal.getOrderColumn())){
    	Map m1 = new HashMap();
    	m1.put("column", "CAR_DEAL.DEAL_STATUS");
    	if(carDeal.isOrderDesc()){
    		m1.put("desc", true);
    	}else{
    		m1.put("desc", false);
    	}
    	orderList.add(m1);
    	Map m2 = new HashMap();
    	m2.put("column", "CAR_DEAL.SALES_ID");
    	m2.put("desc", false);
    	orderList.add(m2);
    }
    if("RATE".equals(carDeal.getOrderColumn())){
    	Map m1 = new HashMap();
    	m1.put("column", "CAR_DEAL.RATE");
    	if(carDeal.isOrderDesc()){
    		m1.put("desc", true);
    	}else{
    		m1.put("desc", false);
    	}
    	orderList.add(m1);
    	Map m2 = new HashMap();
    	m2.put("column", "CAR_DEAL.SALES_ID");
    	m2.put("desc", false);
    	orderList.add(m2);
    }
    if("SALES_ID".equals(carDeal.getOrderColumn())){
    	Map m1 = new HashMap();
    	m1.put("column", "CAR_DEAL.SALES_ID");
    	if(carDeal.isOrderDesc()){
    		m1.put("desc", true);
    	}else{
    		m1.put("desc", false);
    	}
    	orderList.add(m1);
    	Map m2 = new HashMap();
    	m2.put("column", "CAR_DEAL.DEAL_DATE");
    	m2.put("desc", true);
    	orderList.add(m2);
    }
    Map m0 = new HashMap();
    m0.put("column", "CAR_DEAL.CREATE_TIME");
    m0.put("desc", true);
    param.put("order", orderList);
    orderList.add(m0);
    if (!StringUtils.isBlank(carDeal.getSalesName()) && StringUtils.isBlank(carDeal.getSalesId())) {
    	Example<TOpAuthUser> example2 = Example.newExample(TOpAuthUser.class);
    	example2.paramLikeTo("nickName", carDeal.getSalesName());
    	List<OpAuthUser> l = opAuthUserBusiness.list(example2);
    	if(l != null && l.size()>0){
    		List<String> ll = new LinkedList<String>();
    		for (OpAuthUser id : l) {
    			ll.add(id.getId());
    		}
    		param.put("salesIds", ll);
    	}
    }
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
    int serverOffset = Calendar.getInstance().getTimeZone().getOffset(System.currentTimeMillis());
    serverOffset /= 60000;
    if (null!=carDeal.getDealDateStart()) {
    	carDeal.setDealDateStart(DateTimeUtil.plusMinutes(carDeal.getDealDateStart(), (serverOffset+timezoneOffset)));
        param.put("dealDateStart", carDeal.getDealDateStart());
    }
    if (null!=carDeal.getDealDateEnd()) {
    	carDeal.setDealDateEnd(DateTimeUtil.plusMinutes(carDeal.getDealDateEnd(), (serverOffset+timezoneOffset)));
    	param.put("dealDateEnd", DateTimeUtil.fromDate(DateTimeUtil.plusDays(carDeal.getDealDateEnd(), 1)));
    }
    if (null!=carDeal.getModifiedDateStart()) {
    	carDeal.setModifiedDateStart(DateTimeUtil.plusMinutes(carDeal.getModifiedDateStart(), (serverOffset+timezoneOffset)));
        param.put("modifiedDateStart", carDeal.getModifiedDateStart());
    }
    if (null!=carDeal.getModifiedDateEnd()) {
    	carDeal.setModifiedDateEnd(DateTimeUtil.plusMinutes(carDeal.getModifiedDateEnd(), (serverOffset+timezoneOffset)));
    	param.put("modifiedDateEnd", DateTimeUtil.fromDate(DateTimeUtil.plusDays(carDeal.getModifiedDateEnd(), 1)));
    }
    Pagination<CarDeal> pg = carDealBusiness.selectForList(param, page, limit);
    
    if(pg.getResult()==null||pg.getResult().size()==0){
        log.debug("result null");
      return pg;
    }else{
      List<CarDeal> carDeal_s = pg.getResult();
      List<String> uids = new ArrayList<>();
      for (CarDeal deal: carDeal_s) {
    	Example<TNotes> example = Example.newExample(TNotes.class);
    	example.paramEqualTo("serviceId", deal.getId()).orderByDesc("create_time");
    	List<Notes> l = notesBusiness.list(example);
    	if(l!=null && l.size() >0){
    		deal.setLatestNotes(l.get(0).getContent());
    	}
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
  public String save(CarDeal carDeal, int timezoneOffset){
	log.debug("saveCarDeal, param:{}", carDeal==null?null:JSONObject.toJSONString(carDeal));
	if(null != carDeal.getDealDate()){
	int serverOffset = Calendar.getInstance().getTimeZone().getOffset(System.currentTimeMillis());
    serverOffset /= 60000;
	carDeal.setDealDate(DateTimeUtil.plusMinutes(carDeal.getDealDate(), (serverOffset+timezoneOffset)));
	}

	Date now = DateTimeUtil.now();
    if ("0".equals(carDeal.getId())) {
    	String id = KeyGenerator.uuid();
        carDeal.setId(id);
        TSequence record = new TSequence();
        record.setName(LumeiCrmConstants.CaseNo.CarSale);
        TSequence record2 = sequenceDao.selectByPrimaryKey(record);
        if(null == record2){
        	record2 = new TSequence();
        	record2.setName(LumeiCrmConstants.CaseNo.CarSale);
        	record2.setCurrentValue(1);
        	record2.setIncrement(1);
        	sequenceDao.insert(record2);
        }else{
        	record2.setCurrentValue(record2.getCurrentValue() + 1);
        	sequenceDao.updateByPrimaryKey(record2);
        }
        String caseNo = LumeiCrmConstants.CaseNo.CarSale 
        		+ String.format("%06d", record2.getCurrentValue());
        carDeal.setCaseNo(caseNo);
    	carDeal.setCreateTime(now);
    	carDeal.setUpdateTime(now);
    	carDeal.setSalesId(SessionUtil.getCurrentUserId());
    	carDeal.setCreateUserId(SessionUtil.getCurrentUserId());
    	carDeal.setUpdateUserId(SessionUtil.getCurrentUserId());
    	carDealBusiness.create(carDeal);
    	Transaction transaction = new Transaction();
    	List<String> salesIds = new ArrayList<String>();
        salesIds.add(carDeal.getSalesId());
        Map<String,OpAuthUser> userMap = userInfoBusiness.getUserInfoById(salesIds);
        OpAuthUser user0 = userMap.get(carDeal.getSalesId());
        if (user0 != null) {
        	carDeal.setSalesName(user0.getNickName());
        }
        transaction.setServiceId(id);
        transaction.setUserId(carDeal.getCustomerId());
        transaction.setServiceType(LumeiCrmConstants.SERVICE_TYPE.CAR_SELLING.getValue());
        transaction.setDetails(carDeal.getSalesName());
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
        example0.paramEqualTo("serviceId", carDeal.getId());
        List<Transaction> transactions = transactionBusiness.list(example0);
        if(null != transactions && transactions.size() >0){
        Transaction transaction = transactions.get(0);
        List<String> salesIds = new ArrayList<String>();
        salesIds.add(carDeal.getSalesId());
        Map<String,OpAuthUser> userMap = userInfoBusiness.getUserInfoById(salesIds);
        OpAuthUser user0 = userMap.get(carDeal.getSalesId());
        if (user0 != null) {
        	carDeal.setSalesName(user0.getNickName());
        }
        transaction.setDetails(carDeal.getSalesName());
        transaction.setUpdateTime(now);
        transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
        transactionBusiness.update(transaction);
        }
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
      example0.paramEqualTo("serviceId", id);
      List<Transaction> transactions = transactionBusiness.list(example0);
      if(null != transactions && transactions.size() >0){
      Transaction transaction = transactions.get(0);
      transactionBusiness.delete(transaction.getId(),Transaction.class);
      }
    return 1 == result ? "success" : "fail";
  }

  @RequestMapping(value = "assign", method = RequestMethod.POST)
  @ResponseBody
  public String assign(String sales, @RequestParam("ids[]") String[] ids) {
	List<String> salesIds = Arrays.asList(sales);
    Map<String,OpAuthUser> userMap = userInfoBusiness.getUserInfoById(salesIds);
    Date now = DateTimeUtil.now();
    for (int i = 0; i < ids.length; i++) {
	CarDeal carDeal = new CarDeal();
	carDeal.setId(ids[i]);
	carDeal.setSalesId(sales);
	carDeal.setUpdateTime(now);
	carDeal.setUpdateUserId(SessionUtil.getCurrentUserId());
	carDealBusiness.updateSelective(carDeal);
	Example<TTransaction> example0 = Example.newExample(TTransaction.class);
    example0.paramEqualTo("serviceId", carDeal.getId());
	List<Transaction> transactions = transactionBusiness.list(example0);
    if(null != transactions && transactions.size() >0){
	Transaction transaction = transactions.get(0);
    OpAuthUser user0 = userMap.get(carDeal.getSalesId());
    if (user0 != null) {
    	carDeal.setSalesName(user0.getNickName());
    }
    transaction.setDetails(carDeal.getSalesName());
    transaction.setUpdateTime(now);
    transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
    transactionBusiness.update(transaction);
    }
    }
    return sales;
  }
}
