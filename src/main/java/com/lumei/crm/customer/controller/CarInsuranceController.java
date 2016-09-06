package com.lumei.crm.customer.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

import com.alibaba.fastjson.JSONObject;
import com.lumei.crm.auth.biz.OpAuthUserBusiness;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.auth.entity.TOpAuthUser;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.DateTimeUtil;
import com.lumei.crm.commons.util.KeyGenerator;
import com.lumei.crm.customer.biz.CarInsuranceBusiness;
import com.lumei.crm.customer.biz.CustomerBusiness;
import com.lumei.crm.customer.biz.NotesBusiness;
import com.lumei.crm.customer.biz.TransactionBusiness;
import com.lumei.crm.customer.biz.UserInfoBusiness;
import com.lumei.crm.customer.constants.LumeiCrmConstants;
import com.lumei.crm.customer.dao.SequenceDao;
import com.lumei.crm.customer.dto.CarInsurance;
import com.lumei.crm.customer.dto.CarInsuranceQueryParam;
import com.lumei.crm.customer.dto.Customer;
import com.lumei.crm.customer.dto.Notes;
import com.lumei.crm.customer.dto.Transaction;
import com.lumei.crm.customer.entity.TCarInsurance;
import com.lumei.crm.customer.entity.TCustomer;
import com.lumei.crm.customer.entity.TNotes;
import com.lumei.crm.customer.entity.TSequence;
import com.lumei.crm.customer.entity.TTransaction;
import com.lumei.crm.util.SessionUtil;

@Controller
@RequestMapping(value = "carinsurance")
public class CarInsuranceController {

  private static final Logger log = LoggerFactory.getLogger(CarInsuranceController.class);

  @Autowired
  private CustomerBusiness customerBusiness;
  
  @Autowired
  UserInfoBusiness userInfoBusiness;
  
  @Autowired
  CarInsuranceBusiness carInsuranceBusiness;

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
    ModelAndView mav = new ModelAndView("carinsurance/listcarinsurance");
    SessionUtil.setAttributes("dateStart", 
    		DateTimeUtil.fromDate(
					DateTimeUtil.plusMonths(DateTimeUtil.plusHours(DateTimeUtil.now(), -15), -1),
					DateTimeUtil.Pattern.DEFAULT_FORMATE_DATE));
    SessionUtil.setAttributes("dateEnd",
    		DateTimeUtil.fromDate(
					DateTimeUtil.plusMonths(DateTimeUtil.plusHours(DateTimeUtil.now(), -15), 1),
					DateTimeUtil.Pattern.DEFAULT_FORMATE_DATE));
    return mav;
  }
  
  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<CarInsurance> list(CarInsuranceQueryParam carInsurance, int timezoneOffset, int page, int limit){
	  return process(carInsurance, timezoneOffset, page, limit);
  }
  
  @RequestMapping(value = "export")
  public String export(CarInsuranceQueryParam carInsurance, int timezoneOffset, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination<CarInsurance> pg = process(carInsurance, timezoneOffset, 1, 65535);
		List result = new ArrayList();
		if(pg != null && pg.getResult() != null){
			result = pg.getResult();
		}
	    String filename = "Car_Insurance_Export.csv";
	    String downloadName = new String(filename.getBytes("utf-8"), "iso8859-1");
	    response.setContentType("application/x-msdownload");
	    response.setHeader("Content-Disposition", "attachment;filename=" + downloadName);
	    model.addAttribute("result", result);
	    return "xls/carinsurance";
	}
  
  private Pagination<CarInsurance> process(CarInsuranceQueryParam carInsurance, int timezoneOffset, int page, int limit){
      log.debug("list param, page:{}, limit:{}, param:{}",
    	        page, limit, JSONObject.toJSONString(carInsurance));
    	    Example<TCarInsurance> example0 = Example.newExample(TCarInsurance.class);
    	    
    	    if (StringUtils.isNotBlank(carInsurance.getSalesName()) && StringUtils.isBlank(carInsurance.getSalesId())) {
    	    	Example<TOpAuthUser> example2 = Example.newExample(TOpAuthUser.class);
    	    	example2.paramLikeTo("nickName", carInsurance.getSalesName());
    	    	List<OpAuthUser> l = opAuthUserBusiness.list(example2);
    	    	if(l != null && l.size()>0){
    	    		List<String> ll = new LinkedList<String>();
    	    		for (OpAuthUser id : l) {
    	    			ll.add(id.getId());
    	    		}
    	    		example0.paramIn("salesId", ll);
    	    	}
    	    }
    	    
    	    if (StringUtils.isNotBlank(carInsurance.getSalesId())) {
    	    	example0.paramEqualTo("salesId", carInsurance.getSalesId());
    	    }
    	    
//    	    if(StringUtils.isNotBlank(carInsurance.getCustomerName())
//    	    		||StringUtils.isNotBlank(carInsurance.getWechat())
//    	    		||StringUtils.isNotBlank(carInsurance.getPhone())){
    	    Example<TCustomer> example1 = Example.newExample(TCustomer.class);
    	    if (StringUtils.isNotBlank(carInsurance.getCustomerName())) {
    	    	example1.paramLikeTo("name", carInsurance.getCustomerName());
    	    }
    	    if (StringUtils.isNotBlank(carInsurance.getWechat())) {
    	    	example1.paramLikeTo("wechat", carInsurance.getWechat());
    	    }
    	    if (StringUtils.isNotBlank(carInsurance.getPhone())) {
    	    	example1.paramLikeTo("phone", carInsurance.getPhone());
    	    }
    	    List<Customer> custList = customerBusiness.list(example1);
    	    if(custList == null || custList.size() == 0){
    	    	example0.paramEqualTo("customerId", "0");
    	    }else{
    	    	List<String> custIds = new LinkedList<String>();
    	    	for (Customer customer : custList) {
    	    		custIds.add(customer.getId());
    			}
    	    	example0.paramIn("customerId", custIds);
    	    }
//    	    }
    	    int serverOffset = Calendar.getInstance().getTimeZone().getOffset(System.currentTimeMillis());
    	    serverOffset /= 60000;
    	    if (null!=carInsurance.getModifiedDateStart()) {
    	    	carInsurance.setModifiedDateStart(DateTimeUtil.plusMinutes(carInsurance.getModifiedDateStart(), (serverOffset+timezoneOffset)));
    	    	example0.paramGreaterThanOrEqualTo("effectiveDate", carInsurance.getModifiedDateStart());
    	    }
    	    if (null!=carInsurance.getModifiedDateEnd()) {
    	    	carInsurance.setModifiedDateEnd(DateTimeUtil.plusMinutes(carInsurance.getModifiedDateEnd(), (serverOffset+timezoneOffset)));
    	    	example0.paramLessThanOrEqualTo("effectiveDate", DateTimeUtil.fromDate(DateTimeUtil.plusDays(carInsurance.getModifiedDateEnd(), 1)));
    	    }
    	    
    	    if(StringUtils.isNotBlank(carInsurance.getOrderColumn())){
    	    	if(carInsurance.isOrderDesc()){
    	    		example0.orderByDesc(carInsurance.getOrderColumn());
    	    	}else{
    	    		example0.orderBy(carInsurance.getOrderColumn());
    	    	}
    	    }
    	    example0.orderByDesc("UPDATE_TIME");
    	    
    	    Pagination<CarInsurance> pg = carInsuranceBusiness.listByPage(example0, page, limit);
    	    
    	    if(pg.getResult()==null||pg.getResult().size()==0){
    	        log.debug("result null");
    	      return pg;
    	    }else{
    	      List<CarInsurance> carInsurance_s = pg.getResult();
    	      List<String> uids = new ArrayList<>();
    	      for (CarInsurance deal: carInsurance_s) {
    	    	Example<TNotes> example = Example.newExample(TNotes.class);
    	    	example.paramEqualTo("serviceId", deal.getId()).orderByDesc("create_time");
    	    	List<Notes> l = notesBusiness.list(example);
    	    	if(l!=null && l.size() >0){
    	    		deal.setLatestNotes(l.get(0).getContent());
    	    	}
    	    	Customer cust = customerBusiness.find(deal.getCustomerId(), Customer.class);
    	    	deal.setCustomerName(cust.getName());
    	    	deal.setWechat(cust.getWechat());
    	        String salesId = deal.getSalesId();
    	        String createUserId = deal.getCreateUserId();
    	        String updateUserId = deal.getUpdateUserId();
    	        uids.add(salesId);
    	        uids.add(createUserId);
    	        uids.add(updateUserId);
    	      }

    	      List<CarInsurance> newResult = new ArrayList<>();
    	      Map<String,OpAuthUser> userMap = userInfoBusiness.getUserInfoById(uids);
    	      if(userMap!=null) {
    	        for (CarInsurance deal: carInsurance_s) {
    	          String salesId = deal.getSalesId();
    	          OpAuthUser sales = userMap.get(salesId);
    	          if (sales != null) {
    	            deal.setSalesName(sales.getNickName());
    	          }

    	          newResult.add(deal);
    	        }
    	      }else{
    	        newResult = carInsurance_s;
    	      }
    	      pg.setResult(newResult);
    	    }
    	    return pg;
  }
  
  @RequestMapping(value = "create", method = RequestMethod.GET)
  public ModelAndView create(String customerId, String carDealId) {
    ModelAndView mav = new ModelAndView("carinsurance/carinsurance");
    SessionUtil.setAttributes("serviceId", "0");
    SessionUtil.setAttributes("customerId", customerId);
    SessionUtil.setAttributes("carDealId", carDealId);
    return mav;
  }
  
  @RequestMapping(value = "get", method = RequestMethod.GET)
  public ModelAndView get(String id,String customerId, String carDealId) {
    ModelAndView mav = new ModelAndView("carinsurance/carinsurance");
    SessionUtil.setAttributes("serviceId", id);
    SessionUtil.setAttributes("customerId", customerId);
    SessionUtil.setAttributes("carDealId", carDealId);
    return mav;
  }
  
  @RequestMapping(value = "get/getCarInsurance", method = RequestMethod.GET)
  @ResponseBody
  public CarInsurance getDetails(String id, String customerId, String carDealId){
	  String customerName = "";
	  Customer cust = customerBusiness.find(customerId, Customer.class);
	  if(null != cust){
		  customerName = cust.getName();
	  }
	  if ("0".equals(id)) {
		  CarInsurance p = new CarInsurance();
	    	p.setId("0");
	    	p.setCustomerId(customerId);
	    	p.setCarDealId(carDealId);
	    	p.setCustomerName(customerName);
	    	p.setSalesName(SessionUtil.getCurrentUserNickName());
	      return p;
	    }
	CarInsurance carInsurance = carInsuranceBusiness.find(id, CarInsurance.class);
    if (carInsurance == null || StringUtils.isBlank(carInsurance.getId())) {
    	CarInsurance p = new CarInsurance();
    	p.setId("1");
      return p;
    }
    carInsurance.setCustomerName(customerName);
    if(SessionUtil.salesReadonly(carInsurance.getSalesId())){
    	carInsurance.setReadonly(true);
    }
    List<String> salesIds = new ArrayList<String>();
    salesIds.add(carInsurance.getSalesId());
    Map<String,OpAuthUser> userMap = userInfoBusiness.getUserInfoById(salesIds);
    OpAuthUser user0 = userMap.get(carInsurance.getSalesId());
    if (user0 != null) {
    	carInsurance.setSalesName(user0.getNickName());
    }
    return carInsurance;
  }

  @RequestMapping(value = "save", method = RequestMethod.POST)
  @ResponseBody
  public String save(CarInsurance carInsurance, int timezoneOffset){
	log.debug("carInsurance, param:{}", carInsurance==null?null:JSONObject.toJSONString(carInsurance));
	if(null != carInsurance.getEffectiveDate()){
		int serverOffset = Calendar.getInstance().getTimeZone().getOffset(System.currentTimeMillis());
	    serverOffset /= 60000;
	    carInsurance.setEffectiveDate(DateTimeUtil.plusMinutes(carInsurance.getEffectiveDate(), (serverOffset+timezoneOffset)));
	}
	Date now = DateTimeUtil.now();
    if ("0".equals(carInsurance.getId())) {
    	String id = KeyGenerator.uuid();
    	carInsurance.setId(id);
        TSequence record = new TSequence();
        record.setName(LumeiCrmConstants.CaseNo.CarInsur);
        TSequence record2 = sequenceDao.selectByPrimaryKey(record);
        if(null == record2){
        	record2 = new TSequence();
        	record2.setName(LumeiCrmConstants.CaseNo.CarInsur);
        	record2.setCurrentValue(1);
        	record2.setIncrement(1);
        	sequenceDao.insert(record2);
        }else{
        	record2.setCurrentValue(record2.getCurrentValue() + 1);
        	sequenceDao.updateByPrimaryKey(record2);
        }
        String caseNo = LumeiCrmConstants.CaseNo.CarInsur 
        		+ String.format("%06d", record2.getCurrentValue());
        carInsurance.setCaseNo(caseNo);
        carInsurance.setCreateTime(now);
        carInsurance.setUpdateTime(now);
        carInsurance.setSalesId(SessionUtil.getCurrentUserId());
        carInsurance.setCreateUserId(SessionUtil.getCurrentUserId());
        carInsurance.setUpdateUserId(SessionUtil.getCurrentUserId());
        carInsuranceBusiness.create(carInsurance);
    	Transaction transaction = new Transaction();
    	List<String> salesIds = new ArrayList<String>();
        salesIds.add(carInsurance.getSalesId());
        Map<String,OpAuthUser> userMap = userInfoBusiness.getUserInfoById(salesIds);
        OpAuthUser user0 = userMap.get(carInsurance.getSalesId());
        if (user0 != null) {
        	carInsurance.setSalesName(user0.getNickName());
        }
        transaction.setServiceId(id);
        transaction.setUserId(carInsurance.getCustomerId());
        transaction.setServiceType(LumeiCrmConstants.SERVICE_TYPE.CAR_INSURANCE.getValue());
        transaction.setDetails(carInsurance.getSalesName());
        transaction.setCreateTime(now);
        transaction.setCreateUserId(SessionUtil.getCurrentUserId());
        transaction.setUpdateTime(now);
        transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
        transactionBusiness.create(transaction);
    } else {
    	carInsurance.setUpdateTime(now);
    	carInsurance.setUpdateUserId(SessionUtil.getCurrentUserId());
    	carInsuranceBusiness.update(carInsurance);
    	Example<TTransaction> example0 = Example.newExample(TTransaction.class);
        example0.paramEqualTo("serviceId", carInsurance.getId());
        List<Transaction> transactions = transactionBusiness.list(example0);
        if(null != transactions && transactions.size() >0){
        Transaction transaction = transactions.get(0);
        List<String> salesIds = new ArrayList<String>();
        salesIds.add(carInsurance.getSalesId());
        Map<String,OpAuthUser> userMap = userInfoBusiness.getUserInfoById(salesIds);
        OpAuthUser user0 = userMap.get(carInsurance.getSalesId());
        if (user0 != null) {
        	carInsurance.setSalesName(user0.getNickName());
        }
        transaction.setDetails(carInsurance.getSalesName());
        transaction.setUpdateTime(now);
        transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
        transactionBusiness.update(transaction);
        }
    }
      log.debug("result:{}",carInsurance.getId());
    return carInsurance.getId();
  }

  @RequestMapping(value = "delete", method = RequestMethod.POST)
  @ResponseBody
  public String delete(String id) {
    int result;
      log.debug("delete carInsurance:{}, opId:{},opName:{}", id, SessionUtil.getCurrentUserId(),
        SessionUtil.getCurrentUserName());
      result = carInsuranceBusiness.delete(id, CarInsurance.class);
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
    CarInsurance carInsurance = new CarInsurance();
    carInsurance.setId(ids[i]);
    carInsurance.setSalesId(sales);
    carInsurance.setUpdateTime(now);
    carInsurance.setUpdateUserId(SessionUtil.getCurrentUserId());
    carInsuranceBusiness.updateSelective(carInsurance);
	Example<TTransaction> example0 = Example.newExample(TTransaction.class);
    example0.paramEqualTo("serviceId", carInsurance.getId());
	List<Transaction> transactions = transactionBusiness.list(example0);
    if(null != transactions && transactions.size() >0){
	Transaction transaction = transactions.get(0);
    OpAuthUser user0 = userMap.get(carInsurance.getSalesId());
    if (user0 != null) {
    	carInsurance.setSalesName(user0.getNickName());
    }
    transaction.setDetails(carInsurance.getSalesName());
    transaction.setUpdateTime(now);
    transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
    transactionBusiness.update(transaction);
    }
    }
    return sales;
  }
}
