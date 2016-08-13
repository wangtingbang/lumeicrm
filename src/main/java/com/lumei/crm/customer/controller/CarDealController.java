package com.lumei.crm.customer.controller;

import com.alibaba.fastjson.JSONObject;
import com.lumei.crm.auth.bean.SysRole;
import com.lumei.crm.auth.biz.OpAuthUserBusiness;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.auth.entity.TOpAuthUser;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.DateTimeUtil;
import com.lumei.crm.commons.util.KeyGenerator;
import com.lumei.crm.customer.biz.CarDealBusiness;
import com.lumei.crm.customer.biz.TransactionBusiness;
import com.lumei.crm.customer.constants.LumeiCrmConstants;
import com.lumei.crm.customer.dto.CarDeal;
import com.lumei.crm.customer.dto.CarDealQueryParam;
import com.lumei.crm.customer.dto.Transaction;
import com.lumei.crm.customer.entity.TCarDeal;
import com.lumei.crm.customer.entity.TTransaction;
import com.lumei.crm.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by wangtingbang on 16/8/13.
 */
@Controller
@RequestMapping(value = "carDeal")
public class CarDealController {

  private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  OpAuthUserBusiness opAuthUserBusiness;

  @Autowired
  CarDealBusiness carDealBusiness;

  @Autowired
  TransactionBusiness transactionBusiness;


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
  @RequestMapping(value = "list", method = RequestMethod.GET)
  public ModelAndView listProfiles() {
    ModelAndView mav = new ModelAndView("customer/listCarDeal");
    return mav;
  }

  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<CarDeal> listProfiles(CarDealQueryParam carDeal, int page, int limit){

    if(log.isDebugEnabled()) {
      log.debug("list param, page:{}, limit:{}, param:{}",
        page, limit, JSONObject.toJSONString(carDeal));
    }

    Example<TCarDeal> example = Example.newExample(TCarDeal.class);

    example.paramLikeTo("customerName", carDeal.getCustomerName());
    example.param("wechat", carDeal.getWechat());
    example.param("phone", carDeal.getPhone());
    example.param("dealStatus", carDeal.getDealStatus());
    example.paramGreaterThanOrEqualTo("dealDate", carDeal.getDealDateStart());
    example.paramLessThanOrEqualTo("dealDate", carDeal.getDealDateEnd());
    example.param("rating", carDeal.getRating());
    example.orderBy("createTime").desc();
    
    String customerName = carDeal.getCustomerName();
    String wechat = carDeal.getWechat();
    String phone = carDeal.getPhone();
    Byte dealStatus = carDeal.getDealStatus();
    Date dealDateStart = carDeal.getDealDateStart();
    Date dealDateEnd = carDeal.getDealDateEnd();
    Byte rating = carDeal.getRating();

    Map param = new HashMap();
    param.put("customerName", carDeal.getCustomerName());
    param.put("wechat", carDeal.getWechat());
    param.put("phone", carDeal.getPhone());
    param.put("dealStatus", carDeal.getDealStatus());
    param.put("dealDate", carDeal.getDealDateStart());
    param.put("dealDate", carDeal.getDealDateEnd());
    param.put("rating", carDeal.getRating());
//    Pagination<CarDeal> pg = carDealBusiness.listByPage(example, page, limit);
//    Pagination<CarDeal> pg = carDealBusiness.selectForList(
//        customerName, phone, wechat, rating, dealStatus, dealDateStart, dealDateEnd, page, limit);
    Pagination<CarDeal> pg = carDealBusiness.selectForList(param, page, limit);
    if(pg.getResult()==null||pg.getResult().size()<1){
      if(log.isDebugEnabled()){
        log.debug("result null");
      }
      return pg;
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
      Map<String,OpAuthUser> userMap = getUserInfoById(uids);
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


  @RequestMapping(value = "get", method = RequestMethod.GET)
  @ResponseBody
  public CarDeal getCarDeal(String id){

    CarDeal carDeal = carDealBusiness.find(id, CarDeal.class);
    if(carDeal== null){
      carDeal = new CarDeal();
      carDeal.setId("0");
      return carDeal;
    }
    if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey())){
      if(!SessionUtil.getCurrentUserId().equals(carDeal.getSalesId())){
        carDeal.setReadonly(true);
      }
    }
    return carDeal;
  }

  @RequestMapping(value = "save", method = RequestMethod.POST)
  @ResponseBody
  public String saveCarDeal(CarDeal carDeal){

    if(log.isDebugEnabled()){
      log.debug("saveCarDeal, param:{}", carDeal==null?null:JSONObject.toJSONString(carDeal));
    }
    if(carDeal==null){
      return null;
    }
    String id = carDeal.getId();
    Date now = DateTimeUtil.now();
    String currentUserId = SessionUtil.getCurrentUserId();
    if (StringUtils.isBlank(id) || "null".equals(id) ||"undefined".equals(id)) {
      id = KeyGenerator.uuid();

      carDeal.setId(id);
      carDeal.setCreateUserId(currentUserId);
      carDeal.setCreateTime(now);
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
    }else{
      carDeal.setUpdateUserId(currentUserId);
      carDeal.setUpdateTime(now);

      carDealBusiness.update(carDeal);

      Example<TTransaction> example0 = Example.newExample(TTransaction.class);
      example0.param("serviceId", id);
      List<Transaction> transactions = transactionBusiness.list(example0);
      Transaction transaction = transactions.get(0);
      transaction.setUpdateTime(now);
      transaction.setUpdateUserId(SessionUtil.getCurrentUserId());
      transactionBusiness.update(transaction);
    }

    if(log.isDebugEnabled()){
      log.debug("result:{}",id);
    }
    return id;
  }


  @RequestMapping(value = "delete", method = RequestMethod.POST)
  @ResponseBody
  public String deleteNotes(String id) {
    int result;

    if(log.isDebugEnabled()) {
      log.debug("delete note:{}, opId:{},opName:{}", id, SessionUtil.getCurrentUserId(),
        SessionUtil.getCurrentUserName());
    }

    result = carDealBusiness.delete(id, CarDeal.class);

    if(log.isDebugEnabled()){
      log.debug("result:{}", result);
    }
    return 1 == result ? "success" : "fail";
  }

}
