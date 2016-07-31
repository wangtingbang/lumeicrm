package com.lumei.crm.customer.controller;

import java.util.HashMap;
import java.util.List;

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


  @RequestMapping(value="{key}/getProfile", method = RequestMethod.GET)
  public ModelAndView getProfile(@PathVariable("key") String key, String customerId){
    ModelAndView mav = new ModelAndView("customer/profileTemp");
    mav.getModel().put("authType", key);
    mav.getModel().put("customerId", customerId);
    SessionUtil.setAttributes("customerId", customerId);
    return mav;
  }

  @RequestMapping(value="{key}/getCarBuying", method = RequestMethod.GET)
  public ModelAndView getCarBuying(@PathVariable("key") String key, String customerId){
    ModelAndView mav = new ModelAndView("customer/carBuyingTemp");
    mav.getModel().put("authType", key);
    mav.getModel().put("customerId", key);
    return mav;
  }

  @RequestMapping(value="{key}/getEmergency", method = RequestMethod.GET)
  public ModelAndView getEmergency(@PathVariable("key") String key){
    ModelAndView mav = new ModelAndView("customer/emerencyTemp");
    mav.getModel().put("authType", key);
    return mav;
  }

  @RequestMapping(value = "profile/get", method = RequestMethod.GET)
  @ResponseBody
  public Profile getCustomerProfile(String customerId){

    log.debug("param, customerId:{}", customerId);

    Profile profile = profileBusiness.find(customerId, Profile.class);
    return profile;
  }

  @RequestMapping(value = "profile/create", method = RequestMethod.POST)
  @ResponseBody
  public String createProfile(@RequestBody HashMap param){

    Profile profile = BeanUtils.map(param, Profile.class);

    int result = profileBusiness.create(profile);

    return 1==result?"success":"fail";
  }


  @RequestMapping(value = "service/carbuying/create", method = RequestMethod.POST)
  @ResponseBody
  public String createCarBuying(@RequestBody HashMap param){

    CarBuying carBuying = BeanUtils.map(param, CarBuying.class);

    int result = carBuyingBusiness.create(carBuying);

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
  public String createEmergencyContact(@RequestBody HashMap param){

    EmergencyContact emergencyContact= BeanUtils.map(param, EmergencyContact.class);

    int result = emergencyContactBusiness.create(emergencyContact);

    return 1==result?"success":"fail";
  }

  @RequestMapping(value = "service/emergencycontact/get", method = RequestMethod.GET)
  @ResponseBody
  public EmergencyContact getCustomerEmergency(String customerId){

    log.debug("param, customerId:{}", customerId);
    EmergencyContact service = emergencyContactBusiness.find(customerId, EmergencyContact.class);
    return service;
  }

  @RequestMapping(value = "note/create", method = RequestMethod.POST)
  @ResponseBody
  public String createNotes(@RequestBody HashMap param){
    Notes notes = BeanUtils.map(param, Notes.class);
    int result = notesBusiness.create(notes);
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
}
