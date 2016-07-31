package com.lumei.crm.customer.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lumei.crm.commons.biz.support.CommonBusiness;
import com.lumei.crm.commons.dao.support.BaseDao;
import com.lumei.crm.commons.util.BeanUtils;
import com.lumei.crm.customer.dao.ProfileDao;
import com.lumei.crm.customer.dto.Profile;
import com.lumei.crm.customer.entity.TProfile;

/**
 * 
 * @generated by Code Generator v0.1
 * @Sat Jul 30 23:58:18 CST 2016
 */
@Service
public class ProfileBusiness extends CommonBusiness<Profile, TProfile, String> {

  @Autowired
  private ProfileDao profileDao;

  private static Logger logger = LoggerFactory.getLogger(ProfileBusiness.class);

  /**
   * dto转换成po 根据实际情况重写此方法增加其他Bean属性的转换
   */
  @Override
  protected TProfile toEntity(Profile model) {
    TProfile entity = new TProfile();
    BeanUtils.copy(model, entity);
    return entity;
  }

  /**
   * po转换成dto 根据实际情况重写此方法增加其他Bean属性的转换
   */
  @Override
  protected Profile fromEntity(TProfile entity) {
    Profile model = new Profile();
    BeanUtils.copy(entity, model);
    return model;
  }

  /**
   * 为父类CommonBusiness注入Dao
   */
  @Override
  protected BaseDao<TProfile> getBaseDao() {
    return profileDao;
  }

  /**
   * 为父类CommonBusiness注入logger
   */
  @Override
  protected Logger getLogger() {
    return logger;
  }

}
