package com.lumei.crm.customer.entity;

import java.util.Date;

import com.lumei.crm.commons.dao.support.BaseEntity;

/**
 * 
 * @generated by Code Generator v0.1
 * @date Sat Aug 13 20:32:59 CST 2016
 */
public class TCustomer extends BaseEntity<String> {

  /**
   * @field id
   */
  private String id;

  /**
   * @field salesId
   */
  private String salesId;

  /**
   * @field name
   */
  private String name;

  /**
   * @field gender
   */
  private Short gender;

  /**
   * @field wechat
   */
  private String wechat;

  /**
   * @field phone
   */
  private String phone;

  /**
   * @field email
   */
  private String email;

  /**
   * @field university
   */
  private String university;

  /**
   * @field major
   */
  private String major;

  /**
   * @field address
   */
  private String address;

  /**
   * @field zipCode
   */
  private String zipCode;

  /**
   * @field service
   */
  private String service;

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
   * @return salesId
   */
  public String getSalesId() {
    return salesId;
  }

  /**
   * @param salesId
   */
  public void setSalesId(String salesId) {
    this.salesId = salesId;
  }

  /**
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return gender
   */
  public Short getGender() {
    return gender;
  }

  /**
   * @param gender
   */
  public void setGender(Short gender) {
    this.gender = gender;
  }

  /**
   * @return wechat
   */
  public String getWechat() {
    return wechat;
  }

  /**
   * @param wechat
   */
  public void setWechat(String wechat) {
    this.wechat = wechat;
  }

  /**
   * @return phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * @param phone
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return university
   */
  public String getUniversity() {
    return university;
  }

  /**
   * @param university
   */
  public void setUniversity(String university) {
    this.university = university;
  }

  /**
   * @return major
   */
  public String getMajor() {
    return major;
  }

  /**
   * @param major
   */
  public void setMajor(String major) {
    this.major = major;
  }

  /**
   * @return address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * @return zipCode
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * @param zipCode
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * @return service
   */
  public String getService() {
    return service;
  }

  /**
   * @param service
   */
  public void setService(String service) {
    this.service = service;
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
  
  /**
   * @field CITY
   */
  private String city;

  /**
   * @field STATE
   */
  private String state;

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}
  
}
