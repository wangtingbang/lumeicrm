package com.lumei.crm.auth.dto;

import java.io.Serializable;
import java.util.Date;

import com.lumei.crm.commons.biz.support.BaseModel;

/**
 * 
 * @generated by Code Generator v0.1
 * @date Tue Jun 16 21:06:25 CST 2015
 */
public class OpAuthUserRole extends BaseModel<String> implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * @field id
   */
  private String id;

  /**
   * @field userId
   */
  private String userId;

  /**
   * @field roleId
   */
  private String roleId;

  /**
   * @field roleName
   */
  private String roleName;

  /**
   * @field createBy
   */
  private String createBy;

  /**
   * @field createTime
   */
  private Date createTime;

  /**
   * @field updateBy
   */
  private String updateBy;

  /**
   * @field updateTime
   */
  private Date updateTime;

  /**
   * @field deleteFlag
   */
  private Byte deleteFlag;

  /**
   * @return id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return userId
   */
  public String getUserId() {
    return userId;
  }

  /**
   * @param userId
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * @return roleId
   */
  public String getRoleId() {
    return roleId;
  }

  /**
   * @param roleId
   */
  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  /**
   * @return roleName
   */
  public String getRoleName() {
    return roleName;
  }

  /**
   * @param roleName
   */
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  /**
   * @return createBy
   */
  public String getCreateBy() {
    return createBy;
  }

  /**
   * @param createBy
   */
  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  /**
   * @return createTime
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * @param createTime
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * @return updateBy
   */
  public String getUpdateBy() {
    return updateBy;
  }

  /**
   * @param updateBy
   */
  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }

  /**
   * @return updateTime
   */
  public Date getUpdateTime() {
    return updateTime;
  }

  /**
   * @param updateTime
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  /**
   * @return deleteFlag
   */
  public Byte getDeleteFlag() {
    return deleteFlag;
  }

  /**
   * @param deleteFlag
   */
  public void setDeleteFlag(Byte deleteFlag) {
    this.deleteFlag = deleteFlag;
  }

}
