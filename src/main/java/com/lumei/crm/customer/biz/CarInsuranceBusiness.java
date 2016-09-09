package com.lumei.crm.customer.biz;

import com.lumei.crm.customer.dao.CarInsuranceDao;
import com.lumei.crm.customer.entity.TCarInsurance;
import com.lumei.crm.customer.dto.CarInsurance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lumei.crm.commons.dao.support.BaseDao;
import com.lumei.crm.commons.biz.support.CommonBusiness;
import com.lumei.crm.commons.util.BeanUtils;

/**
 * 
 * @generated by Code Generator v0.1
 * @Sun Aug 28 13:20:17 CST 2016
 */
@Service
public class CarInsuranceBusiness extends CommonBusiness<CarInsurance, TCarInsurance, String> {

  @Autowired
  private CarInsuranceDao carInsuranceDao;

  private static Logger logger = LoggerFactory.getLogger(CarInsuranceBusiness.class);

  /**
   * dto转换成po 根据实际情况重写此方法增加其他Bean属性的转换
   */
  @Override
  protected TCarInsurance toEntity(CarInsurance model) {
    TCarInsurance entity = new TCarInsurance();
    BeanUtils.copy(model, entity);
    return entity;
  }

  /**
   * po转换成dto 根据实际情况重写此方法增加其他Bean属性的转换
   */
  @Override
  protected CarInsurance fromEntity(TCarInsurance entity) {
    CarInsurance model = new CarInsurance();
    BeanUtils.copy(entity, model);
    return model;
  }

  /**
   * 为父类CommonBusiness注入Dao
   */
  @Override
  protected BaseDao<TCarInsurance> getBaseDao() {
    return carInsuranceDao;
  }

  /**
   * 为父类CommonBusiness注入logger
   */
  @Override
  protected Logger getLogger() {
    return logger;
  }

}