package com.lumei.crm.customer.biz;

import com.lumei.crm.commons.biz.support.CommonBusiness;
import com.lumei.crm.commons.dao.support.BaseDao;
import com.lumei.crm.commons.util.BeanUtils;
import com.lumei.crm.customer.dao.TransactionDao;
import com.lumei.crm.customer.dto.Transaction;
import com.lumei.crm.customer.entity.TTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @generated by Code Generator v0.1
 * @Sat Aug 06 17:20:50 CST 2016
 */
@Service
public class TransactionBusiness extends CommonBusiness<Transaction, TTransaction, String> {

  @Autowired
  private TransactionDao transactionDao;

  private static Logger logger = LoggerFactory.getLogger(TransactionBusiness.class);

  /**
   * dto转换成po 根据实际情况重写此方法增加其他Bean属性的转换
   */
  @Override
  protected TTransaction toEntity(Transaction model) {
    TTransaction entity = new TTransaction();
    BeanUtils.copy(model, entity);
    return entity;
  }

  /**
   * po转换成dto 根据实际情况重写此方法增加其他Bean属性的转换
   */
  @Override
  protected Transaction fromEntity(TTransaction entity) {
    Transaction model = new Transaction();
    BeanUtils.copy(entity, model);
    return model;
  }

  /**
   * 为父类CommonBusiness注入Dao
   */
  @Override
  protected BaseDao<TTransaction> getBaseDao() {
    return transactionDao;
  }

  /**
   * 为父类CommonBusiness注入logger
   */
  @Override
  protected Logger getLogger() {
    return logger;
  }

}