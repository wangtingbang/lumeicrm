package com.lumei.crm.customer.entity;

import java.util.Date;

import com.lumei.crm.commons.dao.support.BaseEntity;

/**
 * 
 * @generated by Code Generator v0.1
 * @date Sun Aug 07 09:31:58 CST 2016
 */
public class TServiceLog extends BaseEntity<String> {

  /**
   * @field id
   */
  private String id;

  /**
   * @field userId
   */
  private String userId;

  /**
   * @field serviceId
   */
  private String serviceId;

  /**
   * @field serviceType
   */
  private Byte serviceType;

  /**
   * @field content
   */
  private String content;

  /**
   * @field createTime
   */
  private Date createTime;

  /**
   * @field createUserId
   */
  private String createUserId;

  /**
   * @field updateTime
   */
  private Date updateTime;

  /**
   * @field updateUserId
   */
  private String updateUserId;

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
   * @return serviceId
   */
  public String getServiceId() {
    return serviceId;
  }

  /**
   * @param serviceId
   */
  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  /**
   * @return serviceType
   */
  public Byte getServiceType() {
    return serviceType;
  }

  /**
   * @param serviceType
   */
  public void setServiceType(Byte serviceType) {
    this.serviceType = serviceType;
  }

  /**
   * @return content
   */
  public String getContent() {
    return content;
  }

  /**
   * @param content
   */
  public void setContent(String content) {
    this.content = content;
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