package com.lumei.crm.auth.biz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lumei.crm.auth.dao.OpAuthUserDao;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.auth.entity.TOpAuthUser;
import com.lumei.crm.commons.biz.support.CommonBusiness;
import com.lumei.crm.commons.dao.support.BaseDao;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.util.BeanUtils;

/**
 * 
 * @generated by Code Generator v0.1
 * @Fri Jun 12 18:44:36 CST 2015
 */
@Service
public class OpAuthUserBusiness extends CommonBusiness<OpAuthUser, TOpAuthUser, String> {

  @Autowired
  private OpAuthUserDao opAuthUserDao;

  private Logger logger = LoggerFactory.getLogger(OpAuthUserBusiness.class);

  /**
   * dto转换成po 根据实际情况重写此方法增加其他Bean属性的转换
   */
  @Override
  protected TOpAuthUser toEntity(OpAuthUser model) {
    TOpAuthUser entity = new TOpAuthUser();
    BeanUtils.copy(model, entity);
    return entity;
  }

  /**
   * po转换成dto 根据实际情况重写此方法增加其他Bean属性的转换
   */
  @Override
  protected OpAuthUser fromEntity(TOpAuthUser entity) {
    OpAuthUser model = new OpAuthUser();
    BeanUtils.copy(entity, model);
    return model;
  }

  /**
   * 为父类CommonBusiness注入Dao
   */
  @Override
  protected BaseDao<TOpAuthUser> getBaseDao() {
    return opAuthUserDao;
  }

  /**
   * 为父类CommonBusiness注入logger
   */
  @Override
  protected Logger getLogger() {
    return logger;
  }

  public OpAuthUser findUserByName(String userName) {
    List<TOpAuthUser> list = opAuthUserDao.selectByExample(Example.newExample(TOpAuthUser.class).paramEqualTo("userName", userName));
    if (null == list || list.size() == 0) {
      return null;
    }
    return fromEntity(list.get(0));
  }

}
