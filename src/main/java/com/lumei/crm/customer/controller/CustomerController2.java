package com.lumei.crm.customer.controller;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.lumei.crm.auth.bean.SysRole;
//import com.lumei.crm.auth.biz.OpAuthUserBusiness;
//import com.lumei.crm.auth.dto.OpAuthUser;
//import com.lumei.crm.auth.entity.TOpAuthUser;
//import com.lumei.crm.commons.bean.BusinessException;
//import com.lumei.crm.commons.mybatis.support.Example;
//import com.lumei.crm.commons.mybatis.support.Pagination;
//import com.lumei.crm.commons.util.DateTimeUtil;
//import com.lumei.crm.commons.util.KeyGenerator;
//import com.lumei.crm.customer.biz.CarSellingBusiness;
//import com.lumei.crm.customer.biz.CustomerBusiness;
//import com.lumei.crm.customer.biz.EmergencyContactBusiness;
//import com.lumei.crm.customer.biz.NotesBusiness;
//import com.lumei.crm.customer.biz.ProfileBusiness;
//import com.lumei.crm.customer.biz.ServiceLogBusiness;
//import com.lumei.crm.customer.biz.TransactionBusiness;
//import com.lumei.crm.customer.constants.LumeiCrmConstants;
//import com.lumei.crm.customer.dto.CarSelling;
//import com.lumei.crm.customer.dto.Customer;
//import com.lumei.crm.customer.dto.EmergencyContact;
//import com.lumei.crm.customer.dto.Notes;
//import com.lumei.crm.customer.dto.Profile;
//import com.lumei.crm.customer.dto.ServiceLog;
//import com.lumei.crm.customer.dto.Transaction;
//import com.lumei.crm.customer.entity.TCustomer;
//import com.lumei.crm.customer.entity.TNotes;
//import com.lumei.crm.customer.entity.TServiceLog;
//import com.lumei.crm.customer.entity.TTransaction;
//import com.lumei.crm.util.SessionUtil;
//
///**
// * Created by wangtingbang on 15/7/30.
// */
public class CustomerController2 {
//  private static final Logger log = LoggerFactory.getLogger(CustomerController2.class);
//
//  @Autowired
//  private CustomerBusiness customerBusiness;
//  @Autowired
//  private ProfileBusiness profileBusiness;
//  @Autowired
//  private CarSellingBusiness carSellingBusiness;
//
//  @Autowired
//  private EmergencyContactBusiness emergencyContactBusiness;
//
//  @Autowired
//  private NotesBusiness notesBusiness;
//
//  @Autowired
//  private TransactionBusiness transactionBusiness;
//
//  @Autowired
//  private ServiceLogBusiness serviceLogBusiness;
//
//  @Autowired
//  private OpAuthUserBusiness opAuthUserBusiness;
//
//  @RequestMapping(value = "list", method = RequestMethod.GET)
//  public ModelAndView listCustomer() {
//    ModelAndView mav = new ModelAndView("customer/listCustomer");
//    return mav;
//  }
//
//  @RequestMapping(value = "list", method = RequestMethod.POST)
//  @ResponseBody
//  public Pagination<Customer> listCustomer(//
//    String name,  //
//    String wechat, //
//    String phone,  //
//    String sales,//
//    int page, int limit,//
//    String orderColumn, boolean orderDesc) {
//
//    Example<TCustomer> example = Example.newExample(TCustomer.class);
//    if (!StringUtils.isBlank(sales)) {
//        example.param("salesId", sales);
//    }
//    if (!StringUtils.isBlank(name)) {
//      example.paramLikeTo("name", name);
//    }
//    if (!StringUtils.isBlank(wechat)) {
//      example.paramLikeTo("wechat", wechat);
//    }
//    if (!StringUtils.isBlank(phone)) {
//      example.paramLikeTo("phone", phone);
//    }
//    if(StringUtils.isNoneBlank(orderColumn)){
//    	example.orderBy(orderColumn);
//    	if(orderDesc){
//    		example.desc();
//    	}
//    }else{
//    	example.orderBy("createTime").desc();
//    }
//    Pagination<Customer> profilePagination = customerBusiness.listByPage(example, page, limit);
//
//    if (profilePagination == null) {
//      profilePagination = Pagination.newInstance(page, limit, 0);
//      profilePagination.setResult(new ArrayList<Customer>());
//      return profilePagination;
//    } else if (profilePagination.getResult() == null || profilePagination.getResult().size() < 1) {
//      return profilePagination;
//    } else {
//      List<Customer> profileList = profilePagination.getResult();
//      List<String> salesIds = new ArrayList<>();
//      for (Customer customer : profileList) {
//        String salesId = customer.getSalesId();
//        salesIds.add(salesId);
//      }
//      List<Customer> newResult = new ArrayList<>();
//      Map<String,OpAuthUser> userMap = getUserInfoById(salesIds);
//      if(userMap!=null) {
//        for (Customer customer : profileList) {
//          String salesId = customer.getSalesId();
//          OpAuthUser user = userMap.get(salesId);
//          if (user != null) {
//        	customer.setSales(user.getNickName());
//            newResult.add(customer);
//          } else{
//            newResult.add(customer);
//          }
//        }
//      }else{
//        newResult = profileList;
//      }
//      profilePagination.setResult(newResult);
//    }
//    return profilePagination;
//  }
//
//  @RequestMapping(value = "create", method = RequestMethod.GET)
//  public ModelAndView createCustomer() {
//    ModelAndView mav = new ModelAndView("customer/customer");
//    SessionUtil.setAttributes("customerId", 0);
//    return mav;
//  }
//
//  @RequestMapping(value = "get", method = RequestMethod.GET)
//  public ModelAndView getCustomer(String customerId) {
//    ModelAndView mav = new ModelAndView("customer/customer");
//    SessionUtil.setAttributes("customerId", customerId);
//    return mav;
//  }
//  
//  @RequestMapping(value = "get/getCustomer", method = RequestMethod.GET)
//  @ResponseBody
//  public Customer getCustomerProfile(String customerId) {
//    log.debug("param, customerId:{}", customerId);
//    if ("0".equals(customerId)) {
//    	Customer p = new Customer();
//    	p.setId("0");
//      return p;
//    }
//
//    Customer profile = customerBusiness.find(customerId, Customer.class);
//    if (profile == null || StringUtils.isBlank(profile.getId())) {
//    	Customer p = new Customer();
//    	p.setId("0");
//      return p;
//    }
//
//    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey())){
//      if(!SessionUtil.getCurrentUserId().equals(profile.getSalesId())){
//        profile.setReadonly(true);
//      }
//    }
//
//    List<String> salesIds = new ArrayList<String>();
//    salesIds.add(profile.getSalesId());
//    Map<String,OpAuthUser> userMap = getUserInfoById(salesIds);
//    OpAuthUser user0 = userMap.get(profile.getSalesId());
//    if (user0 != null) {
//    	profile.setSales(user0.getNickName());
//    }
//    return profile;
//  }
//
//  @RequestMapping(value = "save", method = RequestMethod.POST)
//  @ResponseBody
//  public String saveProfile(Customer customer) {
//    Date now = DateTimeUtil.now();
//    if (StringUtils.isBlank(customer.getId())) {
//    	customer.setCreateTime(now);
//    	customer.setUpdateTime(now);
//    	customer.setCreateUserId(SessionUtil.getCurrentUserId());
//    	customer.setUpdateUserId(SessionUtil.getCurrentUserId());
//    	customerBusiness.create(customer);
//    } else {
//    	customer.setUpdateTime(now);
//    	customer.setUpdateUserId(SessionUtil.getCurrentUserId());
//    	customerBusiness.update(customer);
//    }
//
//    return customer.getId();
//  }
//  
//  @RequestMapping(value = "delete", method = RequestMethod.POST)
//  @ResponseBody
//  private String deleteProfile(String id){
//    return profileBusiness.delete(id, Profile.class)==1?"success":"fail";
//  }  
//  
//  
//  
//  @RequestMapping(value = "getCarSelling", method = RequestMethod.GET)
//  public ModelAndView getCustomerCarSelling(String customerId, String customerName, String serviceId) {
//    ModelAndView mav = new ModelAndView("customer/carSellingTemp");
//    SessionUtil.setAttributes("serviceId", serviceId);
//    SessionUtil.setAttributes("customerId", customerId);
//    SessionUtil.setAttributes("customerName", customerName);
//    return mav;
//  }
//
//  @RequestMapping(value = "service/carselling/get", method = RequestMethod.GET)
//  @ResponseBody
//  public CarSelling getCustomerCarSellingGet(String serviceId) {
//    log.debug("param, serviceId:{}", serviceId);
//    if(StringUtils.isBlank(serviceId) || "null".equals(serviceId)){
//    	return new CarSelling();
//    }
//    CarSelling carSelling = carSellingBusiness.find(serviceId, CarSelling.class);
//    if(carSelling == null){
//    	CarSelling c = new CarSelling();
//    	c.setId("0");
//    	return c;
//    }
//    List<String> salesIds = new ArrayList<String>();
//    salesIds.add(carSelling.getSalesId());
//    Map<String,OpAuthUser> userMap = getUserInfoById(salesIds);
//    OpAuthUser user = userMap.get(carSelling.getSalesId());
//    if (user != null) {
//    	carSelling.setSalesName(user.getNickName());
//    }
//    
//    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey())){
//      if(!SessionUtil.getCurrentUserId().equals(carSelling.getSalesId())){
//        carSelling.setReadonly(true);
//      }
//    }
//    return carSelling;
//  }
//
//  @RequestMapping(value = "service/carselling/save", method = RequestMethod.POST)
//  @ResponseBody
//  public String saveCarSelling(CarSelling carSelling) {
//    int result = 0;
//    Date now = DateTimeUtil.now();
//    String id = carSelling.getId();
//
//    if (StringUtils.isBlank(id) || "null".equals(id) ||"undefined".equals(id)) {
//      id = KeyGenerator.uuid();
//      carSelling.setId(id);
//      carSelling.setCreateTime(now);
//      carSelling.setUpdateTime(now);
//      carSelling.setCreateUserId(SessionUtil.getCurrentUserId());
//      carSelling.setUpdateUserId(SessionUtil.getCurrentUserId());
//      carSellingBusiness.create(carSelling);
//      Transaction transaction = new Transaction();
//      transaction.setServiceId(id);
//      transaction.setUserId(carSelling.getUserId());
//      transaction.setServiceType(LumeiCrmConstants.SERVICE_TYPE.CAR_SELLING.getValue());
//      transaction.setCreateTime(now);
//      transaction.setCreateUserId(SessionUtil.getCurrentUserId());
//      transaction.setUpdateTime(now);
//      transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
//      transactionBusiness.create(transaction);
//
//    } else {
//      carSelling.setUpdateTime(now);
//      carSelling.setUpdateUserId(SessionUtil.getCurrentUserId());
//      carSellingBusiness.update(carSelling);
//      Example<TTransaction> example0 = Example.newExample(TTransaction.class);
//      example0.param("serviceId", id);
//      List<Transaction> transactions = transactionBusiness.list(example0);
//      Transaction transaction = transactions.get(0);
//      transaction.setUpdateTime(now);
//      transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
//      transactionBusiness.update(transaction);
//    }
//
//    return id;
//  }
//
//  @RequestMapping(value = "getEmergencyContact", method = RequestMethod.GET)
//  public ModelAndView getEmergency(String customerId, String customerName, String serviceId) {
//    ModelAndView mav = new ModelAndView("customer/emergencyContactTemp");
//    SessionUtil.setAttributes("serviceId", serviceId);
//    SessionUtil.setAttributes("customerId", customerId);
//    SessionUtil.setAttributes("customerName", customerName);
//    return mav;
//  }
//
//  @RequestMapping(value = "service/emergencycontact/get", method = RequestMethod.GET)
//  @ResponseBody
//  public EmergencyContact getCustomerEmergency(String serviceId) {
//	    log.debug("param, serviceId:{}", serviceId);
//	    if(StringUtils.isBlank(serviceId) || "null".equals(serviceId)){
//	    	EmergencyContact c = new EmergencyContact();
//	    	c.setTotal(6);
//	    	c.setUsed(0);
//	    	return c;
//	    }
//	    EmergencyContact emergencyContact = emergencyContactBusiness.find(serviceId, EmergencyContact.class);
//	    if(emergencyContact == null){
//	    	EmergencyContact c = new EmergencyContact();
//	    	c.setId("0");
//	    	return c;
//	    }
//	    List<String> salesIds = new ArrayList<String>();
//	    salesIds.add(emergencyContact.getSalesId());
//	    Map<String,OpAuthUser> userMap = getUserInfoById(salesIds);
//	    OpAuthUser user = userMap.get(emergencyContact.getSalesId());
//	    if (user != null) {
//	    	emergencyContact.setSalesName(user.getNickName());
//	    }
//	    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey())){
//	      if(!SessionUtil.getCurrentUserId().equals(emergencyContact.getSalesId())){
//	    	  emergencyContact.setReadonly(true);
//	      }
//	    }
//	    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.ADMIN.getKey())){
//	      emergencyContact.setSpecialReadable(true);
//	    }
//	    return emergencyContact;
//  }
//
//  @RequestMapping(value = "service/emergencycontact/save", method = RequestMethod.POST)
//  @ResponseBody
//  public String saveEmergencyContact(EmergencyContact emergencyContact) {
//	   int result = 0;
//	    Date now = DateTimeUtil.now();
//	    String id = emergencyContact.getId();
//
//	    if (StringUtils.isBlank(id) || "null".equals(id) ||"undefined".equals(id)) {
//	      id = KeyGenerator.uuid();
//	      emergencyContact.setId(id);
//	      emergencyContact.setCreateTime(now);
//	      emergencyContact.setUpdateTime(now);
//	      emergencyContact.setCreateUserId(SessionUtil.getCurrentUserId());
//	      emergencyContact.setUpdateUserId(SessionUtil.getCurrentUserId());
//	      emergencyContactBusiness.create(emergencyContact);
//	      Transaction transaction = new Transaction();
//	      transaction.setServiceId(id);
//	      transaction.setUserId(emergencyContact.getUserId());
//	      transaction.setServiceType(LumeiCrmConstants.SERVICE_TYPE.EMERGENCY_CONTACT.getValue());
//	      transaction.setCreateTime(now);
//	      transaction.setCreateUserId(SessionUtil.getCurrentUserId());
//	      transaction.setUpdateTime(now);
//	      transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
//	      transactionBusiness.create(transaction);
//
//	    } else {
//	      emergencyContact.setUpdateTime(now);
//	      emergencyContact.setUpdateUserId(SessionUtil.getCurrentUserId());
//	      emergencyContactBusiness.update(emergencyContact);
//	      Example<TTransaction> example0 = Example.newExample(TTransaction.class);
//	      example0.param("serviceId", id);
//	      List<Transaction> transactions = transactionBusiness.list(example0);
//	      Transaction transaction = transactions.get(0);
//	      transaction.setUpdateTime(now);
//	      transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
//	      transactionBusiness.update(transaction);
//	    }
//
//	    return id;
//  }
//
//  @RequestMapping(value = "service/emergencycontact/use", method = RequestMethod.POST)
//  @ResponseBody
//  public String useEmergencyContactService(String serviceId) throws BusinessException{
//    int result = 0;
//    EmergencyContact existedemergencyContact = emergencyContactBusiness.find(serviceId, EmergencyContact.class);
//    int use = existedemergencyContact.getUsed();
//    int total = existedemergencyContact.getTotal();
//    Date expDate = DateTimeUtil.round(existedemergencyContact.getExpirationDate());
//
//    Date now = DateTimeUtil.now();
//    Date today = DateTimeUtil.today();
//    
//    String opUserId = SessionUtil.getCurrentUserId();
//    if(total<=use){
//      throw new BusinessException("", "No times has been used");
//    }else if(today.after(expDate)){
//      throw new BusinessException("", "Service has expired");
//    } else{
//      use++;
//      existedemergencyContact.setUsed(use);
//      existedemergencyContact.setUpdateTime(now);
//      existedemergencyContact.setUpdateUserId(SessionUtil.getCurrentUserId());
//      result = emergencyContactBusiness.update(existedemergencyContact);
//
//      ServiceLog serviceLog = new ServiceLog();
//      serviceLog.setCreateTime(now);
//      serviceLog.setCreateUserId(opUserId);
//      serviceLog.setUpdateTime(now);
//      serviceLog.setUpdateUserId(opUserId);
//      serviceLog.setServiceId(serviceId);
//      serviceLog.setServiceType(LumeiCrmConstants.SERVICE_TYPE.EMERGENCY_CONTACT.getValue());
//      serviceLog.setContent("Total : "+total+", Used : "+use);
//
//      serviceLogBusiness.create(serviceLog);
//    }
//    return result==1?"success":"fail";
//  }
//
//  @RequestMapping(value = "service/emergencycontact/useList", method = RequestMethod.POST)
//  @ResponseBody
//  public Pagination<ServiceLog> listEmergencyUseList(String serviceId,int page, int limit){
//    Example<TServiceLog> example = Example.newExample(TServiceLog.class)
//    		.param("serviceId", serviceId).orderBy("createTime").desc();
//    Pagination<ServiceLog> pg = serviceLogBusiness.listByPage(example, page, limit);
////    Pagination<ServiceLog> pg = Pagination.newInstance(1,10);
//    List<ServiceLog> serviceLogs = pg.getResult();
//    if (serviceLogs != null && serviceLogs.size() > 0) {
//      List<String> uids = new ArrayList<String>();
//      for (ServiceLog tmp : serviceLogs) {
//        String uid1 = tmp.getCreateUserId();
//        uids.add(uid1);
//      }
//      List<ServiceLog> serviceLog = new ArrayList();
//      Map<String,OpAuthUser> userMap = getUserInfoById(uids);
//      for (ServiceLog tmp : serviceLogs) {
//        String crtUId = tmp.getCreateUserId();
//        OpAuthUser crtUser = userMap.get(crtUId);
//        String crtUName = crtUser == null ? "" : crtUser.getNickName();
//        tmp.setCreateUserName(crtUName);
//        serviceLog.add(tmp);
//      }
//      pg.setResult(serviceLog);
//    }
//    return pg;
//  }
//
//
//  @RequestMapping(value = "notes/listByPage", method = RequestMethod.POST)
//  @ResponseBody
//  public Pagination<Notes> listNotesByPage(
//	String serviceId,//
//    int page,//
//    int limit) {
//
//    log.debug("param:{}, page:{}, limit:{}", serviceId);
//    Example<TNotes> example = Example.newExample(TNotes.class);
//
//    if (StringUtils.isBlank(serviceId)|| "null".equals(serviceId) ||"undefined".equals(serviceId)) {
//      Pagination<Notes> pg = Pagination.newInstance(page, limit);
//      pg.setResult(new ArrayList<Notes>());
//      pg.setTotal(0);
//      pg.setTotalPage(0);
//      return pg;
//    }
//    boolean readonly = false;
//
//    example.param("serviceId", serviceId);
//    example.orderBy("createTime").desc();
//    Pagination<Notes> pg = notesBusiness.listByPage(example, page, limit);
//
//    List<Notes> notes = pg.getResult();
//    if (notes != null || notes.size() > 0) {
//      List<String> uids = new ArrayList<String>();
//      for (Notes tmp : notes) {
//        String uid0 = tmp.getUpdateUserId();
//        String uid1 = tmp.getCreateUserId();
//        uids.add(uid0);
//        uids.add(uid1);
//      }
//      List<Notes> notesNew = new ArrayList();
//      Map<String,OpAuthUser> userMap = getUserInfoById(uids);
//      for (Notes tmp : notes) {
//        String crtUId = tmp.getCreateUserId();
//        String updUId = tmp.getUpdateUserId();
//        OpAuthUser crtUser = userMap.get(crtUId);
//        OpAuthUser updUser = userMap.get(updUId);
//
//        if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey())){
//          if(!SessionUtil.getCurrentUserId().equals((tmp.getCreateUserId()))){
//            tmp.setReadonly(true);
//          }
//        }
//        String crtUName = crtUser == null ? "" : crtUser.getNickName();
//        String updUName = updUser == null ? "" : updUser.getNickName();
//        tmp.setCreateUserName(crtUName);
//        tmp.setUpdateUserName(updUName);
//        notesNew.add(tmp);
//      }
//      pg.setResult(notesNew);
//    }
//    return pg;
//  }
//
//  @RequestMapping(value = "notes/save", method = RequestMethod.POST)
//  @ResponseBody
//  public String saveNotes(Notes notes) {
//    int result = 0;
//      Date now = DateTimeUtil.now();
//      notes.setCreateTime(now);
//      notes.setUpdateTime(now);
//    if (StringUtils.isBlank(notes.getCreateUserId())) {
//      notes.setCreateUserId(SessionUtil.getCurrentUserId());
//    }
//    if (StringUtils.isBlank(notes.getId())) {
//      result = notesBusiness.create(notes);
//    } else {
//      notes.setUpdateUserId(SessionUtil.getCurrentUserId());
//      result = notesBusiness.update(notes);
//    }
//    return 1 == result ? "success" : "fail";
//  }
//
//  @RequestMapping(value = "notes/delete", method = RequestMethod.POST)
//  @ResponseBody
//  public String deleteNotes(String id) {
//    int result = 0;
//    log.info("delete note:{}, opId:{},opName:{}", id, SessionUtil.getCurrentUserId(),
//      SessionUtil.getCurrentUserName());
//
//    result = notesBusiness.delete(id, Notes.class);
//    return 1 == result ? "success" : "fail";
//  }
//
//  /**
//   *
//   */
//  @RequestMapping(value = "transaction/listByPage", method = RequestMethod.POST)
//  @ResponseBody
//  public Pagination<Transaction> listTransactionByPage(String customerId, int page,
//    int limit) {
//
//    log.debug("param, customerId:{}, page:{}, limit:{}", customerId);
//    Example<TTransaction> example = Example.newExample(TTransaction.class);
//
//    if (StringUtils.isBlank(customerId)|| "null".equals(customerId) ||"undefined".equals(customerId)) {
//      Pagination<Transaction> pg = Pagination.newInstance(page, limit);
//      pg.setResult(new ArrayList<Transaction>());
//      pg.setTotal(0);
//      pg.setTotalPage(0);
//      return pg;
//    }
//
//    example.param("userId", customerId);
//    example.orderBy("createTime").desc();
//    Pagination<Transaction> pg = transactionBusiness.listByPage(example, page, limit);
//
//    /*
//    List<Transaction> notes = pg.getResult();
//    if(notes!=null||notes.size()>0){
//      List<String> uids = new ArrayList<String>();
//      for(Transaction tmp:notes){
//        String uid0 = tmp.getUpdateUserId();
//        String uid1 = tmp.getCreateUserId();
//        uids.add(uid0);
//        uids.add(uid1);
//      }
//      Example<TOpAuthUser> example1 = Example.newExample(TOpAuthUser.class);
//      example1.paramIn("id", uids);
//      List<OpAuthUser> users = opAuthUserBusiness.list(example1);
//      Map<String, OpAuthUser> userMap = new HashMap();
//      for(OpAuthUser user:users){
//        userMap.put(user.getId(), user);
//      }
//      List<Notes> notesNew = new ArrayList();
//      for(Notes tmp: notes){
//        String crtUId = tmp.getCreateUserId();
//        String updUId = tmp.getUpdateUserId();
//        OpAuthUser crtUser = userMap.get(crtUId);
//        OpAuthUser updUser = userMap.get(updUId);
//
//        String crtUName = crtUser==null?"":crtUser.getUserName();
//        String updUName = updUser==null?"":updUser.getUserName();
//        tmp.setCreateUserName(crtUName);
//        tmp.setUpdateUserName(updUName);
//        notesNew.add(tmp);
//      }
//      pg.setResult(notesNew);
//    }//*/
//    return pg;
//  }
//
//  @RequestMapping(value = "transaction/save", method = RequestMethod.POST)
//  @ResponseBody
//  public String saveTransaction(Transaction notes) {
//    int result = 0;
//    if (notes.getCreateTime() == null) {
//      Date now = DateTimeUtil.now();
//      notes.setCreateTime(now);
//      notes.setUpdateTime(now);
//    }
//    if (StringUtils.isBlank(notes.getCreateUserId())) {
//      notes.setCreateUserId(SessionUtil.getCurrentUserId());
//    }
//    if (StringUtils.isBlank(notes.getId())) {
//      result = transactionBusiness.create(notes);
//    } else {
//      notes.setUpdateUserId(SessionUtil.getCurrentUserId());
//      result = transactionBusiness.update(notes);
//    }
//    return 1 == result ? "success" : "fail";
//  }
//
//  
//  private String deleteTransaction(String id) {
//    int result = 0;
//    log.info("delete note:{}, opId:{},opName:{}", id, SessionUtil.getCurrentUserId(),
//      SessionUtil.getCurrentUserName());
//
//    result = transactionBusiness.delete(id, Transaction.class);
//    return 1 == result ? "success" : "fail";
//  }
//
//  private Map<String, OpAuthUser> getUserInfoById(List<String> userIds) {
//    Example<TOpAuthUser> example1 = Example.newExample(TOpAuthUser.class);
//    example1.paramIn("id", userIds);
//    List<OpAuthUser> users = opAuthUserBusiness.list(example1);
//    Map<String, OpAuthUser> userMap = new HashMap();
//    for (OpAuthUser user : users) {
//      userMap.put(user.getId(), user);
//    }
//    return userMap;
//  }
//
//  @RequestMapping(value = "service/carselling/delete", method = RequestMethod.POST)
//  @ResponseBody
//  private String deleteCarSelling(String id){
//	  int i = carSellingBusiness.delete(id, CarSelling.class);
//	  List<Transaction> list = transactionBusiness.list(
//			  Example.newExample(TTransaction.class).param("serviceId", id));
//	  if(null != list && list.size() > 0){
//		  i = transactionBusiness.delete(list.get(0).getId(), Transaction.class);
//	  }
//	  
//    return i ==1?"success":"fail";
//  }
//  @RequestMapping(value = "service/emergencycontact/delete", method = RequestMethod.POST)
//  @ResponseBody
//  private String deleteEmergencyContact(String id){
//	  int i = emergencyContactBusiness.delete(id, EmergencyContact.class);
//	  List<Transaction> list = transactionBusiness.list(
//			  Example.newExample(TTransaction.class).param("serviceId", id));
//	  if(null != list && list.size() > 0){
//		  i = transactionBusiness.delete(list.get(0).getId(), Transaction.class);
//	  }
//	  
//  return i ==1?"success":"fail";
//  }
}
