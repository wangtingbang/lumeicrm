package com.lumei.crm.auth.biz;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lumei.crm.auth.dao.OpAuthUserRoleDao;
import com.lumei.crm.auth.dto.OpAuthUserRole;
import com.lumei.crm.auth.entity.TOpAuthUserRole;
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
public class OpAuthUserRoleBusiness extends CommonBusiness<OpAuthUserRole, TOpAuthUserRole, String> {

  @Autowired
  private OpAuthUserRoleDao opAuthUserRoleDao;

  private Logger logger = LoggerFactory.getLogger(OpAuthUserRoleBusiness.class);

  /**
   * dto转换成po 根据实际情况重写此方法增加其他Bean属性的转换
   */
  @Override
  protected TOpAuthUserRole toEntity(OpAuthUserRole model) {
    TOpAuthUserRole entity = new TOpAuthUserRole();
    BeanUtils.copy(model, entity);
    return entity;
  }

  /**
   * po转换成dto 根据实际情况重写此方法增加其他Bean属性的转换
   */
  @Override
  protected OpAuthUserRole fromEntity(TOpAuthUserRole entity) {
    OpAuthUserRole model = new OpAuthUserRole();
    BeanUtils.copy(entity, model);
    return model;
  }

  /**
   * 为父类CommonBusiness注入Dao
   */
  @Override
  protected BaseDao<TOpAuthUserRole> getBaseDao() {
    return opAuthUserRoleDao;
  }

  /**
   * 为父类CommonBusiness注入logger
   */
  @Override
  protected Logger getLogger() {
    return logger;
  }

  public List<String> listAllRoleByUser(String id) {
    List<TOpAuthUserRole> list = opAuthUserRoleDao.selectByExample(Example.newExample(TOpAuthUserRole.class).param("userId", id));
    List<String> result = new LinkedList<String>();
    for (TOpAuthUserRole tOpAuthUserRole : list) {
      result.add(tOpAuthUserRole.getRoleId());
    }
    return result;
  }

  public int deleteHard(String id) {
    TOpAuthUserRole record = new TOpAuthUserRole();
    record.setId(id);
    return opAuthUserRoleDao.deleteByPrimaryKeyHard(record);
  }

}
