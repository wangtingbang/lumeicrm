package com.lumei.crm.customer.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.BeanUtils;
import com.lumei.crm.customer.biz.CarBuyingBusiness;
import com.lumei.crm.customer.biz.EmergencyContactBusiness;
import com.lumei.crm.customer.biz.NotesBusiness;
import com.lumei.crm.customer.biz.ProfileBusiness;
import com.lumei.crm.customer.dto.CarBuying;
import com.lumei.crm.customer.dto.EmergencyContact;
import com.lumei.crm.customer.dto.Notes;
import com.lumei.crm.customer.dto.Profile;
import com.lumei.crm.customer.entity.TNotes;
import com.lumei.crm.customer.entity.TProfile;
import com.lumei.crm.customer.vo.ProfileDetail;
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

  @RequestMapping(value="{key}/list", method = RequestMethod.GET)
  public ModelAndView listProfiles(@PathVariable("key") String key){
    ModelAndView mav = new ModelAndView("customer/listCustomer");
    mav.getModel().put("authType", key);
    return mav;
  }

  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<Profile> listProfiles( int page, int limit){

    log.debug("param, page:{}, limit:{}", page, limit);
    Example<TProfile> example = Example.newExample(TProfile.class);
    Pagination<Profile> profilePagination = profileBusiness.listByPage(example,page, limit);
    return profilePagination;
  }


  @RequestMapping(value="getProfile", method = RequestMethod.GET)
  public ModelAndView getProfile(String customerId){
    ModelAndView mav = new ModelAndView("customer/profileTemp");
    SessionUtil.setAttributes("customerId", customerId);
    return mav;
  }

  @RequestMapping(value = "profile/get", method = RequestMethod.GET)
  @ResponseBody
  public Profile getCustomerProfile(String customerId){

    log.debug("param, customerId:{}", customerId);
    
    if(StringUtils.isBlank(customerId)){
      return new Profile();
    }

    Profile profile = profileBusiness.find(customerId, Profile.class);
    if(profile ==null || StringUtils.isBlank(profile.getId())){
      return new Profile();
    }
//    Example<TNotes> example = Example.newExample(TNotes.class);
//    example.param("userId", customerId);
//    example.orderBy("commitTime").desc();
//    List<Notes> notes = notesBusiness.list(example);
//    ProfileDetail detail = new ProfileDetail();
//    
//    BeanUtils.copy(profile, detail);
//    detail.setNotes(notes);
//    return detail;
    return profile;
  }


  @RequestMapping(value="getCarBuying", method = RequestMethod.GET)
  public ModelAndView getCarBuying(String customerId, String customerName){
    ModelAndView mav = new ModelAndView("customer/carBuyingTemp");
    SessionUtil.setAttributes("customerId", customerId);
    SessionUtil.setAttributes("customerName", customerName);
    return mav;
  }

  @RequestMapping(value="carBuying/get", method = RequestMethod.GET)
  @ResponseBody
  public CarBuying getCustomerCarBuying(String customerId){
    
    log.debug("param, customerId:{}", customerId);
    CarBuying carBuying = carBuyingBusiness.find(customerId, CarBuying.class);
    if(carBuying==null){
      return new CarBuying();
    }
    return carBuying;
  }

  @RequestMapping(value="getEmergencyContact", method = RequestMethod.GET)
  public ModelAndView getEmergency(String customerId, String customerName){
    ModelAndView mav = new ModelAndView("customer/emergencyContactTemp");
    SessionUtil.setAttributes("customerId", customerId);
    SessionUtil.setAttributes("customerName", customerName);
    return mav;
  }

  @RequestMapping(value="emergencyContact/get", method = RequestMethod.GET)
  @ResponseBody
  public EmergencyContact getCustomerEmergencyContact(String customerId){
    
    log.debug("param, customerId:{}", customerId);
    EmergencyContact emergencyContact = emergencyContactBusiness.find(customerId, EmergencyContact.class);
    if(emergencyContact==null){
      return new EmergencyContact();
    }
    return emergencyContact;
  }

  @RequestMapping(value = "profile/create", method = RequestMethod.POST)
  @ResponseBody
  public String createProfile(Profile param){

    Profile profile = BeanUtils.map(param, Profile.class);

    int result = 0;
    if( StringUtils.isBlank(profile.getId())){
      result = profileBusiness.create(profile);
    }else{
      result = profileBusiness.update(profile);
    }

    return 1==result?"success":"fail";
  }


  @RequestMapping(value = "service/carbuying/create", method = RequestMethod.POST)
  @ResponseBody
  public String createCarBuying(CarBuying carBuying){

    int result = 0;
    if(StringUtils.isBlank(carBuying.getId())){
      result = carBuyingBusiness.create(carBuying);
    }else{
      result = carBuyingBusiness.create(carBuying);
    }

    return 1==result?"success":"fail";
  }

  @RequestMapping(value = "service/carbuying/get", method = RequestMethod.GET)
  @ResponseBody
  public CarBuying getCustomerCarbuying(String customerId){

    log.debug("param, customerId:{}", customerId);
    CarBuying service = carBuyingBusiness.find(customerId, CarBuying.class);
    return service;
  }

  @RequestMapping(value = "service/emergencycontact/create", method = RequestMethod.POST)
  @ResponseBody
  public String createEmergencyContact(EmergencyContact emergencyContact){

    int result =0;
    if(StringUtils.isBlank(emergencyContact.getId())){ //userId?
      result= emergencyContactBusiness.create(emergencyContact);
    }else{
      result= emergencyContactBusiness.update(emergencyContact);
    }
    return 1==result?"success":"fail";
  }

  @RequestMapping(value = "service/emergencycontact/get", method = RequestMethod.GET)
  @ResponseBody
  public EmergencyContact getCustomerEmergency(String customerId){

    log.debug("param, customerId:{}", customerId);
    EmergencyContact service = emergencyContactBusiness.find(customerId, EmergencyContact.class);
    return service;
  }

  @RequestMapping(value = "notes/create", method = RequestMethod.POST)
  @ResponseBody
  public String createNotes(Notes notes){
    int result = 0;
    if(StringUtils.isBlank(notes.getId())){
      result = notesBusiness.create(notes);
    }else{
      result = notesBusiness.update(notes);
    }
    return 1==result?"success":"fail";
  }

  @RequestMapping(value = "notes/list", method = RequestMethod.GET)
  @ResponseBody
  public List<Notes> listNotes( String customerId, String serviceType ){

    log.debug("param, page:{}, limit:{}", customerId, serviceType);
    Example<TNotes> example = Example.newExample(TNotes.class);

    example.param("userId", customerId);
    example.param("noteServiceType", serviceType);
    example.orderBy("commitTime");
    List<Notes> result = notesBusiness.list(example);
    return result;
  }

  @RequestMapping(value = "notes/listByPage", method = RequestMethod.GET)
  @ResponseBody
  public Pagination<Notes> listNotesByPage( String customerId, String serviceType, int page, int limit ){

    log.debug("param, page:{}, limit:{}", customerId, serviceType);
    Example<TNotes> example = Example.newExample(TNotes.class);

    example.param("userId", customerId);
    example.param("noteServiceType", serviceType);
    example.orderBy("commitTime");
    return notesBusiness.listByPage(example, page, limit);
  }
}
