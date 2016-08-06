package com.lumei.crm.customer.controller;

import com.google.common.collect.Lists;
import com.lumei.crm.auth.bean.SysRole;
import com.lumei.crm.auth.biz.OpAuthUserBusiness;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.auth.entity.TOpAuthUser;
import com.lumei.crm.commons.bean.BusinessException;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.BeanUtils;
import com.lumei.crm.commons.util.DateTimeUtil;
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
    @RequestParam(value = "statusList[]") Byte[] statusList, //
    //    @RequestParam(value = "ratingList[]") Byte[] ratingList, //
    String wechatId, //
    Date potentialBuyingDateStart, //
    Date potentialBuyingDateEnd, //
    int page, int limit) {

    log.debug("param, page:{}, limit:{}", page, limit);
    Example<TProfile> example = Example.newExample(TProfile.class);
    List<Byte> status_s = Lists.newArrayList(); //car_selling_status
    //    List<Byte> rating_s = Lists.newArrayList();
    for (Byte status : statusList) {
      status_s.add(status);
    }
    //    for (Byte status : ratingList) {
    //      rating_s.add(status);
    //    }

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
    example.paramIn("carSellingStatus", status_s);
    //    example.paramIn("rating", rating_s);
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
  public ModelAndView getProfile(String customerId, String customerName) {
    ModelAndView mav = new ModelAndView("customer/profileTemp");
    SessionUtil.setAttributes("customerId", customerId);
    SessionUtil.setAttributes("customerName", customerName);
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
      return new Profile();
    }

    Example<TCarSelling> example0 = Example.newExample(TCarSelling.class);
    example0.param("userId", customerId);
    List<CarSelling> list0 = carSellingBusiness.list(example0);

    Example<TEmergencyContact> example1 = Example.newExample(TEmergencyContact.class);
    example1.param("userId", customerId);
    List<EmergencyContact> list1 = emergencyContactBusiness.list(example1);

    ServiceInfo serviceInfo = new ServiceInfo();
    if (list0 != null && list0.size() > 0) {
      serviceInfo.setS1(list0.get(0).getId());
    }

    if (list1 != null && list1.size() > 0) {
      serviceInfo.setS2(list1.get(0).getId());
    }

    profile.setServiceInfo(serviceInfo);

    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES)){
      if(!SessionUtil.getCurrentUserId().equals(profile.getSalesId())){
        profile.setReadonly(true);
      }
    }

    return profile;
  }

  @RequestMapping(value = "profile/save", method = RequestMethod.POST)
  @ResponseBody
  public String saveProfile(Profile param) {

    Profile profile = BeanUtils.map(param, Profile.class);
    if (profile.getCreateTime() == null) {
      Date now = DateTimeUtil.now();
      profile.setCreateTime(now);
      profile.setUpdateTime(now);
    }
    if (StringUtils.isBlank(profile.getCreateUserId())) {
      profile.setCreateUserId(SessionUtil.getCurrentUserId());
    }

    int result = 0;
    if (StringUtils.isBlank(profile.getId())) {
      result = profileBusiness.create(profile);
    } else {
      profile.setUpdateUserId(SessionUtil.getCurrentUserId());
      result = profileBusiness.update(profile);
    }

    return 1 == result ? "success" : "fail";
  }


  @RequestMapping(value = "getCarSelling", method = RequestMethod.GET)
  public ModelAndView getCustomerCarSelling(String customerId, String customerName) {
    ModelAndView mav = new ModelAndView("customer/carSellingTemp");
    SessionUtil.setAttributes("customerId", customerId);
    SessionUtil.setAttributes("customerName", customerName);
    return mav;
  }


  @RequestMapping(value = "service/carselling/get", method = RequestMethod.GET)
  @ResponseBody
  public CarSelling getCustomerSellbuying(String customerId) {

    log.debug("param, customerId:{}", customerId);
    Example<TCarSelling> example = Example.newExample(TCarSelling.class);
    example.param("userId", customerId);
    List<CarSelling> list = carSellingBusiness.list(example);

    if (list == null || list.size() == 0) {
      return new CarSelling();
    }
    CarSelling carSelling = list.get(0);
    if (carSelling == null) {
      return new CarSelling();
    }
    //*
    Example<TNotes> example1 = Example.newExample(TNotes.class);
    example1.param("userId", customerId);
    example1.param("noteServiceType", LumeiCrmConstants.SERVICE_TYPE.CAR_SELLING.getValue());//TODO
    example1.orderBy("updateTime").desc();
    List<Notes> notes = notesBusiness.list(example1);
    //*/
    if (notes != null && notes.size() > 0) {
      carSelling.setNotes(notes.get(0).getContent());
    }

    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES)){
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
    if (StringUtils.isBlank(carSelling.getUserId())) {
      return "fail";
    }
    if (carSelling.getCreateTime() == null) {
      Date now = DateTimeUtil.now();
      carSelling.setCreateTime(now);
      carSelling.setUpdateTime(now);
    }
    if (StringUtils.isBlank(carSelling.getCreateUserId())) {
      carSelling.setCreateUserId(SessionUtil.getCurrentUserId());
    }
    if (StringUtils.isBlank(carSelling.getId())) {
      result = carSellingBusiness.create(carSelling);
    } else {
      carSelling.setUpdateUserId(SessionUtil.getCurrentUserId());
      result = carSellingBusiness.update(carSelling);
    }

    String notes = carSelling.getNotes();

    String customerId = carSelling.getUserId();
    Example<TNotes> example1 = Example.newExample(TNotes.class);
    example1.param("userId", customerId);
    example1.param("noteServiceType", LumeiCrmConstants.SERVICE_TYPE.CAR_SELLING.getValue());//TODO
    example1.orderBy("updateTime").desc();
    List<Notes> notesList = notesBusiness.list(example1);
    Notes newNotes;
    Date now = DateTimeUtil.now();
    if (notesList == null || notesList.size() < 1) {
      newNotes = new Notes();
      newNotes.setUserId(customerId);
      newNotes.setCreateTime(now);
      newNotes.setCreateUserId(SessionUtil.getCurrentUserId());
      newNotes.setUpdateTime(now);
      newNotes.setUpdateUserId(SessionUtil.getCurrentUserId());
      newNotes.setContent(notes);
      newNotes.setNoteServiceType(LumeiCrmConstants.SERVICE_TYPE.CAR_SELLING.getValue());
      notesBusiness.create(newNotes);
    } else {
      newNotes = notesList.get(0);
      newNotes.setUpdateTime(now);
      newNotes.setUpdateUserId(SessionUtil.getCurrentUserId());
      newNotes.setContent(notes);
      notesBusiness.updateSelective(newNotes);
    }

    return 1 == result ? "success" : "fail";
  }

  @RequestMapping(value = "getEmergencyContact", method = RequestMethod.GET)
  public ModelAndView getEmergency(String customerId, String customerName) {
    ModelAndView mav = new ModelAndView("customer/emergencyContactTemp");
    SessionUtil.setAttributes("customerId", customerId);
    SessionUtil.setAttributes("customerName", customerName);
    return mav;
  }

  @RequestMapping(value = "service/emergencycontact/get", method = RequestMethod.GET)
  @ResponseBody
  public EmergencyContact getCustomerEmergency(String customerId) {

    log.debug("param, customerId:{}", customerId);
    Example<TEmergencyContact> example = Example.newExample(TEmergencyContact.class);
    example.param("userId", customerId);
    List<EmergencyContact> list = emergencyContactBusiness.list(example);
    if (list == null || list.size() == 0) {
      return new EmergencyContact();
    }
    EmergencyContact emergencyContact = list.get(0);

    if (emergencyContact == null) {
      return new EmergencyContact();
    }

    //*
    Example<TNotes> example1 = Example.newExample(TNotes.class);
    example1.param("userId", customerId);
    example1
      .param("noteServiceType", LumeiCrmConstants.SERVICE_TYPE.EMERGENCY_CONTACT.getValue());//TODO
    example1.orderBy("updateTime").desc();
    List<Notes> notes = notesBusiness.list(example1);
    //*/
    if (notes != null && notes.size() > 0) {
      emergencyContact.setNotes(notes.get(0).getContent());
    }

    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES)){
      if(!SessionUtil.getCurrentUserId().equals(emergencyContact.getSalesId())){
        emergencyContact.setReadonly(true);
      }
    }
    return emergencyContact;
  }

  @RequestMapping(value = "service/emergencycontact/save", method = RequestMethod.POST)
  @ResponseBody
  public String saveEmergencyContact(EmergencyContact emergencyContact) {

    int result = 0;
    if (StringUtils.isBlank(emergencyContact.getUserId())) {
      return "fail";
    }
    if (emergencyContact.getCreateTime() == null) {
      Date now = DateTimeUtil.now();
      emergencyContact.setCreateTime(now);
      emergencyContact.setUpdateTime(now);
    }
    if (StringUtils.isBlank(emergencyContact.getCreateUserId())) {
      emergencyContact.setCreateUserId(SessionUtil.getCurrentUserId());
    }
    String customerId = emergencyContact.getUserId();
    if (StringUtils.isBlank(emergencyContact.getId())) {
      result = emergencyContactBusiness.create(emergencyContact);
    } else {
      emergencyContact.setUpdateUserId(SessionUtil.getCurrentUserId());
      result = emergencyContactBusiness.update(emergencyContact);
    }

    String notes = emergencyContact.getNotes();

    Example<TNotes> example1 = Example.newExample(TNotes.class);
    example1.param("userId", customerId);
    example1
      .param("noteServiceType", LumeiCrmConstants.SERVICE_TYPE.EMERGENCY_CONTACT.getValue());//TODO
    example1.orderBy("updateTime").desc();
    List<Notes> notesList = notesBusiness.list(example1);
    Notes newNotes;
    Date now = DateTimeUtil.now();
    if (notesList == null || notesList.size() < 1) {
      newNotes = new Notes();
      newNotes.setUserId(customerId);
      newNotes.setCreateTime(now);
      newNotes.setCreateUserId(SessionUtil.getCurrentUserId());
      newNotes.setUpdateTime(now);
      newNotes.setUpdateUserId(SessionUtil.getCurrentUserId());
      newNotes.setContent(notes);
      newNotes.setNoteServiceType(LumeiCrmConstants.SERVICE_TYPE.EMERGENCY_CONTACT.getValue());
      notesBusiness.create(newNotes);
    } else {
      newNotes = notesList.get(0);
      newNotes.setUpdateTime(now);
      newNotes.setUpdateUserId(SessionUtil.getCurrentUserId());
      newNotes.setContent(notes);
      notesBusiness.updateSelective(newNotes);
    }

    return 1 == result ? "success" : "fail";
  }

  @RequestMapping(value = "service/emergencycontact/use", method = RequestMethod.POST)
  @ResponseBody
  public String useEmergencyContactService(EmergencyContact emergencyContact) throws BusinessException{
    int result = 0;

    if(emergencyContact==null
      ||StringUtils.isBlank(emergencyContact.getUserId())
      ||StringUtils.isBlank(emergencyContact.getId())){
      throw new BusinessException("","Invalid service to use");
    }
    String id = emergencyContact.getId();

    EmergencyContact existedemergencyContact = emergencyContactBusiness.find(id, EmergencyContact.class);

    int use = existedemergencyContact.getUsed();
    int total = existedemergencyContact.getTotal();
    Date expDate = existedemergencyContact.getExpirationDate();

    Date now = DateTimeUtil.now();
    String opUserId = SessionUtil.getCurrentUserId();
    if(total<=use){
      throw new BusinessException("", "No service can use, used off");
    }else if(expDate==null||now.after(expDate)){
      throw new BusinessException("", "No service can use, service expired");
    } else{
      use++;
      existedemergencyContact.setUsed(use);
      result = emergencyContactBusiness.update(existedemergencyContact);

      Transaction transaction = new Transaction();
      transaction.setCreateTime(now);
      transaction.setCreateUserId(opUserId);
      transaction.setUpdateTime(now);
      transaction.setUpdateUserId(opUserId);
      transaction.setServiceId(id);
      transaction.setUserId(transaction.getUserId());
      transaction.setServiceType(LumeiCrmConstants.SERVICE_TYPE.EMERGENCY_CONTACT.getValue());
      transactionBusiness.create(transaction);
    }
    return result==1?"success":"fail";
  }

  @RequestMapping(value = "notes/listByPage", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<Notes> listNotesByPage(
    String customerId, String serviceType, String serviceId,
    int page,
    int limit) {

    log.debug("param, page:{}, limit:{}", customerId, serviceType);
    Example<TNotes> example = Example.newExample(TNotes.class);

    if (StringUtils.isBlank(customerId)) {
      Pagination<Notes> pg = Pagination.newInstance(page, limit);
      pg.setResult(new ArrayList<Notes>());
      pg.setTotal(0);
      pg.setTotalPage(0);
      return pg;
    }

    example.param("userId", customerId);
    example.param("noteServiceType", serviceType);
    example.param("serviceId", serviceId);
    example.orderBy("createTime");
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
    if (notes.getCreateTime() == null) {
      Date now = DateTimeUtil.now();
      notes.setCreateTime(now);
      notes.setUpdateTime(now);
    }
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

    if (StringUtils.isBlank(customerId)) {
      Pagination<Transaction> pg = Pagination.newInstance(page, limit);
      pg.setResult(new ArrayList<Transaction>());
      pg.setTotal(0);
      pg.setTotalPage(0);
      return pg;
    }

    example.param("userId", customerId);
    example.orderBy("createTime");
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

  @RequestMapping(value = "transaction/delete", method = RequestMethod.POST)
  @ResponseBody
  public String deleteTransaction(String id) {
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
}
