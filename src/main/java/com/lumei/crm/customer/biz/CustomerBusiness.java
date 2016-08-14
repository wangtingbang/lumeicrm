package com.lumei.crm.customer.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lumei.crm.commons.biz.support.CommonBusiness;
import com.lumei.crm.commons.dao.support.BaseDao;
import com.lumei.crm.commons.util.BeanUtils;
import com.lumei.crm.customer.dao.CustomerDao;
import com.lumei.crm.customer.dto.Customer;
import com.lumei.crm.customer.entity.TCustomer;

/**
 * 
 * @generated by Code Generator v0.1
 * @Sat Aug 13 20:32:59 CST 2016
 */
@Service
public class CustomerBusiness extends CommonBusiness<Customer, TCustomer, String> {

  @Autowired
  private CustomerDao customerDao;

  private static Logger logger = LoggerFactory.getLogger(CustomerBusiness.class);

  /**
   * dto转换成po 根据实际情况重写此方法增加其他Bean属性的转换
   */
  @Override
  protected TCustomer toEntity(Customer model) {
    TCustomer entity = new TCustomer();
    BeanUtils.copy(model, entity);
    return entity;
  }

  /**
   * po转换成dto 根据实际情况重写此方法增加其他Bean属性的转换
   */
  @Override
  protected Customer fromEntity(TCustomer entity) {
    Customer model = new Customer();
    BeanUtils.copy(entity, model);
    return model;
  }

  /**
   * 为父类CommonBusiness注入Dao
   */
  @Override
  protected BaseDao<TCustomer> getBaseDao() {
    return customerDao;
  }

  /**
   * 为父类CommonBusiness注入logger
   */
  @Override
  protected Logger getLogger() {
    return logger;
  }

}