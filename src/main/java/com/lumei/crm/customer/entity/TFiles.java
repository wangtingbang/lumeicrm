package com.lumei.crm.customer.entity;

import java.util.Date;

import com.lumei.crm.commons.dao.support.BaseEntity;

/**
 * 
 * @generated by Code Generator v0.1
 * @date Tue Aug 02 16:22:54 CST 2016
 */
public class TFiles extends BaseEntity<String> {

  /**
   * @field id
   */
  private String id;

  /**
   * @field userId
   */
  private String userId;

  /**
   * @field serviceType
   */
  private String serviceType;

  /**
   * @field uri
   */
  private String uri;

  /**
   * @field deleteFlag
   */
  private Byte deleteFlag;

  /**
   * @field createUserId
   */
  private String createUserId;

  /**
   * @field createTime
   */
  private Date createTime;

  /**
   * @field updateUserId
   */
  private String updateUserId;

  /**
   * @field updateTime
   */
  private Date updateTime;

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
   * @return serviceType
   */
  public String getServiceType() {
    return serviceType;
  }

  /**
   * @param serviceType
   */
  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  /**
   * @return uri
   */
  public String getUri() {
    return uri;
  }

  /**
   * @param uri
   */
  public void setUri(String uri) {
    this.uri = uri;
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

  /**
   * @return createUserId
   */
  public String getCreateUserId() {
    return createUserId;
  }

  /**
   * @param createUserId
   */
  public void setCreateUserId(String createUserId) {
    this.createUserId = createUserId;
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
   * @return updateUserId
   */
  public String getUpdateUserId() {
    return updateUserId;
  }

  /**
   * @param updateUserId
   */
  public void setUpdateUserId(String updateUserId) {
    this.updateUserId = updateUserId;
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
}
