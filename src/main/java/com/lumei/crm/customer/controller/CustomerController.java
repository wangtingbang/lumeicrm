package com.lumei.crm.customer.controller;

import java.util.Date;
import java.util.List;

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

import com.google.common.collect.Lists;
import com.lumei.crm.auth.bean.SysRole;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.BeanUtils;
import com.lumei.crm.commons.util.DateTimeUtil;
import com.lumei.crm.customer.biz.CarBuyingBusiness;
import com.lumei.crm.customer.biz.EmergencyContactBusiness;
import com.lumei.crm.customer.biz.NotesBusiness;
import com.lumei.crm.customer.biz.ProfileBusiness;
import com.lumei.crm.customer.dto.CarBuying;
import com.lumei.crm.customer.dto.EmergencyContact;
import com.lumei.crm.customer.dto.Notes;
import com.lumei.crm.customer.dto.Profile;
import com.lumei.crm.customer.dto.ServiceInfo;
import com.lumei.crm.customer.entity.TCarBuying;
import com.lumei.crm.customer.entity.TEmergencyContact;
import com.lumei.crm.customer.entity.TNotes;
import com.lumei.crm.customer.entity.TProfile;
import com.lumei.crm.util.SessionUtil;

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
  private CarBuyingBusiness carBuyingBusiness;

  @Autowired
  private EmergencyContactBusiness emergencyContactBusiness;

  @Autowired
  private NotesBusiness notesBusiness;

  @RequestMapping(value = "list", method = RequestMethod.GET)
  public ModelAndView listProfiles() {
    ModelAndView mav = new ModelAndView("customer/listCustomer");
    return mav;
  }

  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<Profile> listProfiles(String customerName, String customerPhone,
      String customerEmail, @RequestParam(value = "statusList[]") Byte[] statusList, int page,
      int limit) {

    log.debug("param, page:{}, limit:{}", page, limit);
    Example<TProfile> example = Example.newExample(TProfile.class);
    List<Byte> status_s = Lists.newArrayList();
    int idx = 1;
    for (Byte status : statusList) {
      if (status > 0) {
        status_s.add((byte) idx);
        idx++;
      }
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
//    if(SysRole.SALES.getKey().equals(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey()))){
    log.info("SessionUtil.getCurrentUser().getRoles():{}",SessionUtil.getCurrentUser().getRoles());
    log.info("SysRole.SALES.getKey():{}",SysRole.SALES.getKey());
    log.info("SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey()):{}",SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey()));
    log.info("SessionUtil.getCurrentUser().getRoles().contains(SysRole.admin.getKey()):{}",SessionUtil.getCurrentUser().getRoles().contains(SysRole.ADMIN.getKey()));
    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey())){
      example.param("createUserId", SessionUtil.getCurrentUserId());
    }
    example.paramIn("status", status_s);
    Pagination<Profile> profilePagination = profileBusiness.listByPage(example, page, limit);
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
      return new Profile();
    }
    
    Example<TCarBuying> example0 = Example.newExample(TCarBuying.class);
    example0.param("userId", customerId);
    List<CarBuying> list0 = carBuyingBusiness.list(example0);

    Example<TEmergencyContact> example1 = Example.newExample(TEmergencyContact.class);
    example1.param("userId", customerId);
    List<EmergencyContact> list1 = emergencyContactBusiness.list(example1);
    
    ServiceInfo serviceInfo = new ServiceInfo();
    if(list0!=null&&list0.size()>0){
      serviceInfo.setS1(list0.get(0).getId());
    }
    
    if(list1!=null&&list1.size()>0){
      serviceInfo.setS2(list1.get(0).getId());
    }
    
    profile.setServiceInfo(serviceInfo);
    
    return profile;
  }

  @RequestMapping(value = "profile/save", method = RequestMethod.POST)
  @ResponseBody
  public String saveProfile(Profile param) {

    Profile profile = BeanUtils.map(param, Profile.class);
    if(profile.getCreateTime()==null){
      Date now = DateTimeUtil.now();
      profile.setCreateTime(now);
      profile.setUpdateTime(now);
    }
    if(StringUtils.isBlank(profile.getCreateUserId())){
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


  @RequestMapping(value = "getCarBuying", method = RequestMethod.GET)
  public ModelAndView getCustomerCarBuying(String customerId, String customerName) {
    ModelAndView mav = new ModelAndView("customer/carBuyingTemp");
    SessionUtil.setAttributes("customerId", customerId);
    SessionUtil.setAttributes("customerName", customerName);
    return mav;
  }


  @RequestMapping(value = "service/carbuying/get", method = RequestMethod.GET)
  @ResponseBody
  public CarBuying getCustomerCarbuying(String customerId) {

    log.debug("param, customerId:{}", customerId);
    Example<TCarBuying> example = Example.newExample(TCarBuying.class);
    example.param("userId", customerId);
    List<CarBuying> list = carBuyingBusiness.list(example);
    
    if(list==null||list.size()==0){
      return new CarBuying();
    }
    CarBuying carBuying = list.get(0);
    if(carBuying==null){
      return new CarBuying();
    }
    
    return carBuying;
  }

  @RequestMapping(value = "service/carbuying/save", method = RequestMethod.POST)
  @ResponseBody
  public String saveCarBuying(CarBuying carBuying) {

    int result = 0;
    if(carBuying.getCreateTime()==null){
      Date now = DateTimeUtil.now();
      carBuying.setCreateTime(now);
      carBuying.setUpdateTime(now);
    }
    if(StringUtils.isBlank(carBuying.getCreateUserId())){
      carBuying.setCreateUserId(SessionUtil.getCurrentUserId());
    }
    if (StringUtils.isBlank(carBuying.getId())) {
      result = carBuyingBusiness.create(carBuying);
    } else {
      carBuying.setUpdateUserId(SessionUtil.getCurrentUserId());
      result = carBuyingBusiness.update(carBuying);
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
    if(list==null||list.size()==0){
      return new EmergencyContact();
    }
    EmergencyContact emergencyContact = list.get(0);
    
    if (emergencyContact == null) {
      return new EmergencyContact();
    }
    return emergencyContact;
  }

  @RequestMapping(value = "service/emergencycontact/save", method = RequestMethod.POST)
  @ResponseBody
  public String saveEmergencyContact(EmergencyContact emergencyContact) {

    int result = 0;
    if(emergencyContact.getCreateTime()==null){
      Date now = DateTimeUtil.now();
      emergencyContact.setCreateTime(now);
      emergencyContact.setUpdateTime(now);
    }
    if(StringUtils.isBlank(emergencyContact.getCreateUserId())){
      emergencyContact.setCreateUserId(SessionUtil.getCurrentUserId());
    }
    if (StringUtils.isBlank(emergencyContact.getId())){
      result = emergencyContactBusiness.create(emergencyContact);
    } else {
      emergencyContact.setUpdateUserId(SessionUtil.getCurrentUserId());
      result = emergencyContactBusiness.update(emergencyContact);
    }
    return 1 == result ? "success" : "fail";
  }

  @RequestMapping(value = "notes/listByPage", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<Notes> listNotesByPage(String customerId, String serviceType, int page,
      int limit) {

    log.debug("param, page:{}, limit:{}", customerId, serviceType);
    Example<TNotes> example = Example.newExample(TNotes.class);

    example.param("userId", customerId);
    example.param("noteServiceType", serviceType);
    example.orderBy("createTime");
    return notesBusiness.listByPage(example, page, limit);
  }

  @RequestMapping(value = "notes/save", method = RequestMethod.POST)
  @ResponseBody
  public String saveNotes(Notes notes) {
    int result = 0;
    if(notes.getCreateTime()==null){
      Date now = DateTimeUtil.now();
      notes.setCreateTime(now);
      notes.setUpdateTime(now);
    }
    if(StringUtils.isBlank(notes.getCreateUserId())){
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

}
