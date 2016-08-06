package com.lumei.crm.customer.dto;

import java.io.Serializable;
import java.util.Date;
// import com.cmbchina.commons.biz.support.BaseModel;
// import org.hibernate.validator.constraints.Length;

import com.lumei.crm.commons.biz.support.BaseModel;

/**
 * 
 * @generated by Code Generator v0.1
 * @date Sat Aug 06 12:01:34 CST 2016
 */
public class Notes extends BaseModel<String> implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * @field id
   */
  //@Length(min = 1, max = 0, message = "ID长度为0")
  private String id;

  /**
   * @field noteServiceType
   */
  private Byte noteServiceType;

  /**
   * @field userId
   */
  //@Length(min = 1, max = 0, message = "USER_ID长度为0")
  private String userId;

  /**
   * @field serviceId
   */
  //@Length(min = 1, max = 0, message = "SERVICE_ID长度为0")
  private String serviceId;

  /**
   * @field content
   */
  //@Length(min = 1, max = 0, message = "CONTENT长度为0")
  private String content;

  /**
   * @field deleteFlag
   */
  private Byte deleteFlag;

  /**
   * @field createUserId
   */
  //@Length(min = 1, max = 0, message = "CREATE_USER_ID长度为0")
  private String createUserId;

  private String createUserName;
  
  /**
   * @field createTime
   */
  private Date createTime;

  /**
   * @field updateUserId
   */
  //@Length(min = 1, max = 0, message = "UPDATE_USER_ID长度为0")
  private String updateUserId;

  private String updateUserName;
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
   * @return noteServiceType
   */
  public Byte getNoteServiceType() {
    return noteServiceType;
  }

  /**
   * @param noteServiceType
   */
  public void setNoteServiceType(Byte noteServiceType) {
    this.noteServiceType = noteServiceType;
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

  public String getCreateUserName() {
    return createUserName;
  }

  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
  }

  public String getUpdateUserName() {
    return updateUserName;
  }

  public void setUpdateUserName(String updateUserName) {
    this.updateUserName = updateUserName;
  }
}
