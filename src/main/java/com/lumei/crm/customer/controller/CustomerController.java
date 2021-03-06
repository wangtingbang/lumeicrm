package com.lumei.crm.customer.controller;

import com.lumei.crm.auth.bean.SysRole;
import com.lumei.crm.auth.biz.OpAuthUserBusiness;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.auth.entity.TOpAuthUser;
import com.lumei.crm.commons.bean.BusinessException;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.BeanUtils;
import com.lumei.crm.commons.util.DateTimeUtil;
import com.lumei.crm.commons.util.KeyGenerator;
import com.lumei.crm.customer.biz.*;
import com.lumei.crm.customer.constants.LumeiCrmConstants;
import com.lumei.crm.customer.dto.*;
import com.lumei.crm.customer.entity.*;
import com.lumei.crm.util.SessionUtil;
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

import java.util.*;

/**
 * Created by wangtingbang on 15/7/30.
 */
@Controller
@RequestMapping(value = "customer")
public class CustomerController {
  private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private ProfileBusiness profileBusiness;

  @Autowired
  private CarSellingBusiness carSellingBusiness;

  @Autowired
  private EmergencyContactBusiness emergencyContactBusiness;

  @Autowired
  private NotesBusiness notesBusiness;

  @Autowired
  private TransactionBusiness transactionBusiness;

  @Autowired
  private ServiceLogBusiness serviceLogBusiness;

  @Autowired
  private OpAuthUserBusiness opAuthUserBusiness;

  @RequestMapping(value = "list", method = RequestMethod.GET)
  public ModelAndView listProfiles() {
    ModelAndView mav = new ModelAndView("customer/listCustomer");
    return mav;
  }

  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<Profile> listProfiles( //
    String customerName,  //
    String customerPhone, //
    String customerEmail,  //
    String wechatId, //
    @RequestParam(value = "rating[]") Byte[] ratingArray, //
    Date potentialBuyingDateStart, //
    Date potentialBuyingDateEnd, //
    String sales,
    int page, int limit,//
    String orderColumn, boolean orderDesc) {

    log.debug("param, page:{}, limit:{}", page, limit);
    if(ratingArray == null || ratingArray.length == 0){
    	return Pagination.newInstance(page, limit, 0);
    }
    Example<TProfile> example = Example.newExample(TProfile.class);
    if (!StringUtils.isBlank(sales)) {
        example.param("salesId", sales);
    }
    if (!StringUtils.isBlank(customerName)) {
      example.paramLikeTo("name", customerName);
    }
    if (!StringUtils.isBlank(customerPhone)) {
      example.paramLikeTo("phone", customerPhone);
    }
    if (!StringUtils.isBlank(customerEmail)) {
      example.paramLikeTo("email", customerEmail);
    }
    if (!StringUtils.isBlank(wechatId)) {
      example.paramLikeTo("wechatId", wechatId);
    }
    if (potentialBuyingDateStart != null) {
      example.paramGreaterThanOrEqualTo("potentialBuyingDate", potentialBuyingDateStart);
    }
    if (potentialBuyingDateEnd != null) {
      example.paramLessThanOrEqualTo("potentialBuyingDate", potentialBuyingDateEnd);
    }
    //    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey())){
    //      example.param("createUserId", SessionUtil.getCurrentUserId());
    //    }
    example.paramIn("rating", Arrays.asList(ratingArray));
    //    example.paramIn("rating", rating_s);
    if(StringUtils.isNoneBlank(orderColumn)){
    	example.orderBy(orderColumn);
    	if(orderDesc){
    		example.desc();
    	}
    }else{
    	example.orderBy("createTime").desc();
    }
    Pagination<Profile> profilePagination = profileBusiness.listByPage(example, page, limit);

    if (profilePagination == null) {
      profilePagination = Pagination.newInstance(page, limit, 0);
      profilePagination.setResult(new ArrayList<Profile>());
      return profilePagination;
    } else if (profilePagination.getResult() == null || profilePagination.getResult().size() < 1) {
      return profilePagination;
    } else {
      List<Profile> profileList = profilePagination.getResult();
      List<String> salesIds = new ArrayList<>();
      for (Profile profile : profileList) {
        String salesId = profile.getSalesId();
        salesIds.add(salesId);
      }

      //TODO
      List<Profile> newResult = new ArrayList<>();
      Map<String,OpAuthUser> userMap = getUserInfoById(salesIds);
      if(userMap!=null) {
        for (Profile profile : profileList) {
          String salesId = profile.getSalesId();
          OpAuthUser user = userMap.get(salesId);
          if (user != null) {
            profile.setSales(user.getNickName());
            newResult.add(profile);
          } else{
            newResult.add(profile);
          }
        }
      }else{
        newResult = profileList;
      }
      profilePagination.setResult(newResult);
    }

    return profilePagination;
  }


  @RequestMapping(value = "getProfile", method = RequestMethod.GET)
  public ModelAndView getProfile(String customerId) {
    ModelAndView mav = new ModelAndView("customer/profileTemp");
    SessionUtil.setAttributes("customerId", customerId);
    return mav;
  }

  @RequestMapping(value = "profile/get", method = RequestMethod.GET)
  @ResponseBody
  public Profile getCustomerProfile(String customerId) {

    log.debug("param, customerId:{}", customerId);

    if (StringUtils.isBlank(customerId)) {
      return new Profile();
    }

    Profile profile = profileBusiness.find(customerId, Profile.class);
    if (profile == null || StringUtils.isBlank(profile.getId())) {
    	Profile p = new Profile();
    	p.setId("0");
      return p;
    }

    /*
    Example<TCarSelling> example0 = Example.newExample(TCarSelling.class);
    example0.param("userId", customerId);
    List<CarSelling> list0 = carSellingBusiness.list(example0);

    Example<TEmergencyContact> example1 = Example.newExample(TEmergencyContact.class);
    example1.param("userId", customerId);
    List<EmergencyContact> list1 = emergencyContactBusiness.list(example1);
    //*/

    ServiceInfo serviceInfo = new ServiceInfo();
    /*
    if (list0 != null && list0.size() > 0) {
      serviceInfo.setS1(list0.get(0).getId());
    }

    if (list1 != null && list1.size() > 0) {
      serviceInfo.setS2(list1.get(0).getId());
    }//*/
    if(StringUtils.isBlank(profile.getService())){
      serviceInfo.setS1("0");
      serviceInfo.setS2("0");
    }else{
      int idx = 0;
      for(char ch:profile.getService().toCharArray()){
        if(idx==0) {
          serviceInfo.setS1("" + ch);
        }
        if(idx==1) {
          serviceInfo.setS2("" + ch);
        }
        if(idx==2) {
          serviceInfo.setS3("" + ch);
        }
        if(idx==3) {
          serviceInfo.setS4("" + ch);
        }
        if(idx==4) {
          serviceInfo.setS5("" + ch);
        }
        if(idx==5) {
          serviceInfo.setS6("" + ch);
        }
        if(idx==6) {
          serviceInfo.setS7("" + ch);
        }
        if(idx==7) {
          serviceInfo.setS6("" + ch);
        }
        idx++;
      }
    }

    profile.setServiceInfo(serviceInfo);

    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey())){
      if(!SessionUtil.getCurrentUserId().equals(profile.getSalesId())){
        profile.setReadonly(true);
      }
    }

    List<String> salesIds = new ArrayList<String>();
    salesIds.add(profile.getSalesId());
    salesIds.add(profile.getCreateUserId());
    salesIds.add(profile.getUpdateUserId());
    Map<String,OpAuthUser> userMap = getUserInfoById(salesIds);
    OpAuthUser user0 = userMap.get(profile.getSalesId());
    OpAuthUser user1 = userMap.get(profile.getCreateUserId());
    OpAuthUser user2 = userMap.get(profile.getUpdateUserId());
    if (user0 != null) {
    	profile.setSales(user0.getNickName());
    }
    if (user1 != null) {
    	profile.setCreateUserName(user1.getNickName());
    }
    if (user2 != null) {
    	profile.setUpdateUserName(user2.getNickName());
    }
    return profile;
  }

  @RequestMapping(value = "profile/save", method = RequestMethod.POST)
  @ResponseBody
  public String saveProfile(Profile param) {

    Profile profile = BeanUtils.map(param, Profile.class);
    Date now = DateTimeUtil.now();
    if (StringUtils.isBlank(profile.getId())) {
    	profile.setCreateTime(now);
        profile.setUpdateTime(now);
        profile.setCreateUserId(SessionUtil.getCurrentUserId());
        profile.setUpdateUserId(SessionUtil.getCurrentUserId());
      profileBusiness.create(profile);
    } else {
        profile.setUpdateTime(now);
        profile.setUpdateUserId(SessionUtil.getCurrentUserId());
      profileBusiness.update(profile);
    }

    return profile.getId();
  }
  
  @RequestMapping(value = "getCarSelling", method = RequestMethod.GET)
  public ModelAndView getCustomerCarSelling(String customerId, String customerName, String serviceId) {
    ModelAndView mav = new ModelAndView("customer/carSellingTemp");
    SessionUtil.setAttributes("serviceId", serviceId);
    SessionUtil.setAttributes("customerId", customerId);
    SessionUtil.setAttributes("customerName", customerName);
    return mav;
  }

  @RequestMapping(value = "service/carselling/get", method = RequestMethod.GET)
  @ResponseBody
  public CarSelling getCustomerCarSellingGet(String serviceId) {
    log.debug("param, serviceId:{}", serviceId);
    if(StringUtils.isBlank(serviceId) || "null".equals(serviceId)){
    	return new CarSelling();
    }
    CarSelling carSelling = carSellingBusiness.find(serviceId, CarSelling.class);
    if(carSelling == null){
    	CarSelling c = new CarSelling();
    	c.setId("0");
    	return c;
    }
    List<String> salesIds = new ArrayList<String>();
    salesIds.add(carSelling.getSalesId());
    Map<String,OpAuthUser> userMap = getUserInfoById(salesIds);
    OpAuthUser user = userMap.get(carSelling.getSalesId());
    if (user != null) {
    	carSelling.setSalesName(user.getNickName());
    }
    
    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey())){
      if(!SessionUtil.getCurrentUserId().equals(carSelling.getSalesId())){
        carSelling.setReadonly(true);
      }
    }
    return carSelling;
  }

  @RequestMapping(value = "service/carselling/save", method = RequestMethod.POST)
  @ResponseBody
  public String saveCarSelling(CarSelling carSelling) {
    int result = 0;
    Date now = DateTimeUtil.now();
    String id = carSelling.getId();

    if (StringUtils.isBlank(id) || "null".equals(id) ||"undefined".equals(id)) {
      id = KeyGenerator.uuid();
      carSelling.setId(id);
      carSelling.setCreateTime(now);
      carSelling.setUpdateTime(now);
      carSelling.setCreateUserId(SessionUtil.getCurrentUserId());
      carSelling.setUpdateUserId(SessionUtil.getCurrentUserId());
      carSellingBusiness.create(carSelling);
      Transaction transaction = new Transaction();
      transaction.setServiceId(id);
      transaction.setUserId(carSelling.getUserId());
      transaction.setServiceType(LumeiCrmConstants.SERVICE_TYPE.CAR_SELLING.getValue());
      transaction.setCreateTime(now);
      transaction.setCreateUserId(SessionUtil.getCurrentUserId());
      transaction.setUpdateTime(now);
      transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
      transactionBusiness.create(transaction);

    } else {
      carSelling.setUpdateTime(now);
      carSelling.setUpdateUserId(SessionUtil.getCurrentUserId());
      carSellingBusiness.update(carSelling);
      Example<TTransaction> example0 = Example.newExample(TTransaction.class);
      example0.param("serviceId", id);
      List<Transaction> transactions = transactionBusiness.list(example0);
      Transaction transaction = transactions.get(0);
      transaction.setUpdateTime(now);
      transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
      transactionBusiness.update(transaction);
    }

    return id;
  }

  @RequestMapping(value = "getEmergencyContact", method = RequestMethod.GET)
  public ModelAndView getEmergency(String customerId, String customerName, String serviceId) {
    ModelAndView mav = new ModelAndView("customer/emergencyContactTemp");
    SessionUtil.setAttributes("serviceId", serviceId);
    SessionUtil.setAttributes("customerId", customerId);
    SessionUtil.setAttributes("customerName", customerName);
    return mav;
  }

  @RequestMapping(value = "service/emergencycontact/get", method = RequestMethod.GET)
  @ResponseBody
  public EmergencyContact getCustomerEmergency(String serviceId) {
	    log.debug("param, serviceId:{}", serviceId);
	    if(StringUtils.isBlank(serviceId) || "null".equals(serviceId)){
	    	EmergencyContact c = new EmergencyContact();
	    	c.setTotal(6);
	    	c.setUsed(0);
	    	return c;
	    }
	    EmergencyContact emergencyContact = emergencyContactBusiness.find(serviceId, EmergencyContact.class);
	    if(emergencyContact == null){
	    	EmergencyContact c = new EmergencyContact();
	    	c.setId("0");
	    	return c;
	    }
	    List<String> salesIds = new ArrayList<String>();
	    salesIds.add(emergencyContact.getSalesId());
	    Map<String,OpAuthUser> userMap = getUserInfoById(salesIds);
	    OpAuthUser user = userMap.get(emergencyContact.getSalesId());
	    if (user != null) {
	    	emergencyContact.setSalesName(user.getNickName());
	    }
	    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey())){
	      if(!SessionUtil.getCurrentUserId().equals(emergencyContact.getSalesId())){
	    	  emergencyContact.setReadonly(true);
	      }
	    }
	    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.ADMIN.getKey())){
	      emergencyContact.setSpecialReadable(true);
	    }
	    return emergencyContact;
  }

  @RequestMapping(value = "service/emergencycontact/save", method = RequestMethod.POST)
  @ResponseBody
  public String saveEmergencyContact(EmergencyContact emergencyContact) {
	   int result = 0;
	    Date now = DateTimeUtil.now();
	    String id = emergencyContact.getId();

	    if (StringUtils.isBlank(id) || "null".equals(id) ||"undefined".equals(id)) {
	      id = KeyGenerator.uuid();
	      emergencyContact.setId(id);
	      emergencyContact.setCreateTime(now);
	      emergencyContact.setUpdateTime(now);
	      emergencyContact.setCreateUserId(SessionUtil.getCurrentUserId());
	      emergencyContact.setUpdateUserId(SessionUtil.getCurrentUserId());
	      emergencyContactBusiness.create(emergencyContact);
	      Transaction transaction = new Transaction();
	      transaction.setServiceId(id);
	      transaction.setUserId(emergencyContact.getUserId());
	      transaction.setServiceType(LumeiCrmConstants.SERVICE_TYPE.EMERGENCY_CONTACT.getValue());
	      transaction.setCreateTime(now);
	      transaction.setCreateUserId(SessionUtil.getCurrentUserId());
	      transaction.setUpdateTime(now);
	      transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
	      transactionBusiness.create(transaction);

	    } else {
	      emergencyContact.setUpdateTime(now);
	      emergencyContact.setUpdateUserId(SessionUtil.getCurrentUserId());
	      emergencyContactBusiness.update(emergencyContact);
	      Example<TTransaction> example0 = Example.newExample(TTransaction.class);
	      example0.param("serviceId", id);
	      List<Transaction> transactions = transactionBusiness.list(example0);
	      Transaction transaction = transactions.get(0);
	      transaction.setUpdateTime(now);
	      transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
	      transactionBusiness.update(transaction);
	    }

	    return id;
  }

  @RequestMapping(value = "service/emergencycontact/use", method = RequestMethod.POST)
  @ResponseBody
  public String useEmergencyContactService(String serviceId) throws BusinessException{
    int result = 0;
    EmergencyContact existedemergencyContact = emergencyContactBusiness.find(serviceId, EmergencyContact.class);
    int use = existedemergencyContact.getUsed();
    int total = existedemergencyContact.getTotal();
    Date expDate = DateTimeUtil.round(existedemergencyContact.getExpirationDate());

    Date now = DateTimeUtil.now();
    Date today = DateTimeUtil.today();
    
    String opUserId = SessionUtil.getCurrentUserId();
    if(total<=use){
      throw new BusinessException("", "No times has been used");
    }else if(today.after(expDate)){
      throw new BusinessException("", "Service has expired");
    } else{
      use++;
      existedemergencyContact.setUsed(use);
      existedemergencyContact.setUpdateTime(now);
      existedemergencyContact.setUpdateUserId(SessionUtil.getCurrentUserId());
      result = emergencyContactBusiness.update(existedemergencyContact);

      ServiceLog serviceLog = new ServiceLog();
      serviceLog.setCreateTime(now);
      serviceLog.setCreateUserId(opUserId);
      serviceLog.setUpdateTime(now);
      serviceLog.setUpdateUserId(opUserId);
      serviceLog.setServiceId(serviceId);
      serviceLog.setServiceType(LumeiCrmConstants.SERVICE_TYPE.EMERGENCY_CONTACT.getValue());
      serviceLog.setContent("Total : "+total+", Used : "+use);

      serviceLogBusiness.create(serviceLog);
    }
    return result==1?"success":"fail";
  }

  @RequestMapping(value = "service/emergencycontact/useList", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<ServiceLog> listEmergencyUseList(String serviceId,int page, int limit){
    Example<TServiceLog> example = Example.newExample(TServiceLog.class)
    		.param("serviceId", serviceId).orderBy("createTime").desc();
    Pagination<ServiceLog> pg = serviceLogBusiness.listByPage(example, page, limit);
//    Pagination<ServiceLog> pg = Pagination.newInstance(1,10);
    List<ServiceLog> serviceLogs = pg.getResult();
    if (serviceLogs != null && serviceLogs.size() > 0) {
      List<String> uids = new ArrayList<String>();
      for (ServiceLog tmp : serviceLogs) {
        String uid1 = tmp.getCreateUserId();
        uids.add(uid1);
      }
      List<ServiceLog> serviceLog = new ArrayList();
      Map<String,OpAuthUser> userMap = getUserInfoById(uids);
      for (ServiceLog tmp : serviceLogs) {
        String crtUId = tmp.getCreateUserId();
        OpAuthUser crtUser = userMap.get(crtUId);
        String crtUName = crtUser == null ? "" : crtUser.getNickName();
        tmp.setCreateUserName(crtUName);
        serviceLog.add(tmp);
      }
      pg.setResult(serviceLog);
    }
    return pg;
  }


  @RequestMapping(value = "notes/listByPage", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<Notes> listNotesByPage(
	String serviceId,//
    int page,//
    int limit) {

    log.debug("param:{}, page:{}, limit:{}", serviceId);
    Example<TNotes> example = Example.newExample(TNotes.class);

    if (StringUtils.isBlank(serviceId)|| "null".equals(serviceId) ||"undefined".equals(serviceId)) {
      Pagination<Notes> pg = Pagination.newInstance(page, limit);
      pg.setResult(new ArrayList<Notes>());
      pg.setTotal(0);
      pg.setTotalPage(0);
      return pg;
    }
    boolean readonly = false;

    example.param("serviceId", serviceId);
    example.orderBy("createTime").desc();
    Pagination<Notes> pg = notesBusiness.listByPage(example, page, limit);

    List<Notes> notes = pg.getResult();
    if (notes != null || notes.size() > 0) {
      List<String> uids = new ArrayList<String>();
      for (Notes tmp : notes) {
        String uid0 = tmp.getUpdateUserId();
        String uid1 = tmp.getCreateUserId();
        uids.add(uid0);
        uids.add(uid1);
      }
      List<Notes> notesNew = new ArrayList();
      Map<String,OpAuthUser> userMap = getUserInfoById(uids);
      for (Notes tmp : notes) {
        String crtUId = tmp.getCreateUserId();
        String updUId = tmp.getUpdateUserId();
        OpAuthUser crtUser = userMap.get(crtUId);
        OpAuthUser updUser = userMap.get(updUId);

        if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey())){
          if(!SessionUtil.getCurrentUserId().equals((tmp.getCreateUserId()))){
            tmp.setReadonly(true);
          }
        }
        String crtUName = crtUser == null ? "" : crtUser.getNickName();
        String updUName = updUser == null ? "" : updUser.getNickName();
        tmp.setCreateUserName(crtUName);
        tmp.setUpdateUserName(updUName);
        notesNew.add(tmp);
      }
      pg.setResult(notesNew);
    }
    return pg;
  }

  @RequestMapping(value = "notes/save", method = RequestMethod.POST)
  @ResponseBody
  public String saveNotes(Notes notes) {
    int result = 0;
      Date now = DateTimeUtil.now();
      notes.setCreateTime(now);
      notes.setUpdateTime(now);
    if (StringUtils.isBlank(notes.getCreateUserId())) {
      notes.setCreateUserId(SessionUtil.getCurrentUserId());
    }
    if (StringUtils.isBlank(notes.getId())) {
      result = notesBusiness.create(notes);
    } else {
      notes.setUpdateUserId(SessionUtil.getCurrentUserId());
      result = notesBusiness.update(notes);
    }
    return 1 == result ? "success" : "fail";
  }

  @RequestMapping(value = "notes/delete", method = RequestMethod.POST)
  @ResponseBody
  public String deleteNotes(String id) {
    int result = 0;
    log.info("delete note:{}, opId:{},opName:{}", id, SessionUtil.getCurrentUserId(),
      SessionUtil.getCurrentUserName());

    result = notesBusiness.delete(id, Notes.class);
    return 1 == result ? "success" : "fail";
  }

  /**
   *
   */
  @RequestMapping(value = "transaction/listByPage", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<Transaction> listTransactionByPage(String customerId, int page,
    int limit) {

    log.debug("param, customerId:{}, page:{}, limit:{}", customerId);
    Example<TTransaction> example = Example.newExample(TTransaction.class);

    if (StringUtils.isBlank(customerId)|| "null".equals(customerId) ||"undefined".equals(customerId)) {
      Pagination<Transaction> pg = Pagination.newInstance(page, limit);
      pg.setResult(new ArrayList<Transaction>());
      pg.setTotal(0);
      pg.setTotalPage(0);
      return pg;
    }

    example.param("userId", customerId);
    example.orderBy("createTime").desc();
    Pagination<Transaction> pg = transactionBusiness.listByPage(example, page, limit);

    /*
    List<Transaction> notes = pg.getResult();
    if(notes!=null||notes.size()>0){
      List<String> uids = new ArrayList<String>();
      for(Transaction tmp:notes){
        String uid0 = tmp.getUpdateUserId();
        String uid1 = tmp.getCreateUserId();
        uids.add(uid0);
        uids.add(uid1);
      }
      Example<TOpAuthUser> example1 = Example.newExample(TOpAuthUser.class);
      example1.paramIn("id", uids);
      List<OpAuthUser> users = opAuthUserBusiness.list(example1);
      Map<String, OpAuthUser> userMap = new HashMap();
      for(OpAuthUser user:users){
        userMap.put(user.getId(), user);
      }
      List<Notes> notesNew = new ArrayList();
      for(Notes tmp: notes){
        String crtUId = tmp.getCreateUserId();
        String updUId = tmp.getUpdateUserId();
        OpAuthUser crtUser = userMap.get(crtUId);
        OpAuthUser updUser = userMap.get(updUId);

        String crtUName = crtUser==null?"":crtUser.getUserName();
        String updUName = updUser==null?"":updUser.getUserName();
        tmp.setCreateUserName(crtUName);
        tmp.setUpdateUserName(updUName);
        notesNew.add(tmp);
      }
      pg.setResult(notesNew);
    }//*/
    return pg;
  }

  @RequestMapping(value = "transaction/save", method = RequestMethod.POST)
  @ResponseBody
  public String saveTransaction(Transaction notes) {
    int result = 0;
    if (notes.getCreateTime() == null) {
      Date now = DateTimeUtil.now();
      notes.setCreateTime(now);
      notes.setUpdateTime(now);
    }
    if (StringUtils.isBlank(notes.getCreateUserId())) {
      notes.setCreateUserId(SessionUtil.getCurrentUserId());
    }
    if (StringUtils.isBlank(notes.getId())) {
      result = transactionBusiness.create(notes);
    } else {
      notes.setUpdateUserId(SessionUtil.getCurrentUserId());
      result = transactionBusiness.update(notes);
    }
    return 1 == result ? "success" : "fail";
  }

  
  private String deleteTransaction(String id) {
    int result = 0;
    log.info("delete note:{}, opId:{},opName:{}", id, SessionUtil.getCurrentUserId(),
      SessionUtil.getCurrentUserName());

    result = transactionBusiness.delete(id, Transaction.class);
    return 1 == result ? "success" : "fail";
  }

  private Map<String, OpAuthUser> getUserInfoById(List<String> userIds) {
    Example<TOpAuthUser> example1 = Example.newExample(TOpAuthUser.class);
    example1.paramIn("id", userIds);
    List<OpAuthUser> users = opAuthUserBusiness.list(example1);
    Map<String, OpAuthUser> userMap = new HashMap();
    for (OpAuthUser user : users) {
      userMap.put(user.getId(), user);
    }
    return userMap;
  }

  @RequestMapping(value = "profile/delete", method = RequestMethod.POST)
  @ResponseBody
  private String deleteProfile(String id){
    return profileBusiness.delete(id, Profile.class)==1?"success":"fail";
  }
  @RequestMapping(value = "service/carselling/delete", method = RequestMethod.POST)
  @ResponseBody
  private String deleteCarSelling(String id){
	  int i = carSellingBusiness.delete(id, CarSelling.class);
	  List<Transaction> list = transactionBusiness.list(
			  Example.newExample(TTransaction.class).param("serviceId", id));
	  if(null != list && list.size() > 0){
		  i = transactionBusiness.delete(list.get(0).getId(), Transaction.class);
	  }
	  
    return i ==1?"success":"fail";
  }
  @RequestMapping(value = "service/emergencycontact/delete", method = RequestMethod.POST)
  @ResponseBody
  private String deleteEmergencyContact(String id){
	  int i = emergencyContactBusiness.delete(id, EmergencyContact.class);
	  List<Transaction> list = transactionBusiness.list(
			  Example.newExample(TTransaction.class).param("serviceId", id));
	  if(null != list && list.size() > 0){
		  i = transactionBusiness.delete(list.get(0).getId(), Transaction.class);
	  }
	  
  return i ==1?"success":"fail";
  }
}
