package com.lumei.crm.customer.dto;

import java.io.Serializable;
import java.util.Date;
// import com.cmbchina.commons.biz.support.BaseModel;
// import org.hibernate.validator.constraints.Length;

import com.lumei.crm.commons.biz.support.BaseModel;

/**
 * 
 * @generated by Code Generator v0.1
 * @date Sat Aug 06 12:39:08 CST 2016
 */
public class Profile extends BaseModel<String> implements Serializable {

  private static final long serialVersionUID = 1L;

  ServiceInfo serviceInfo;
  
  /**
   * @field id
   */
  //@Length(min = 1, max = 0, message = "ID长度为0")
  private String id;

  /**
   * @field salesId
   */
  //@Length(min = 1, max = 0, message = "SALES_ID长度为0")
  private String salesId;

  /**
   * @field name
   */
  //@Length(min = 1, max = 0, message = "NAME长度为0")
  private String name;

  /**
   * @field gender
   */
  private Short gender;

  /**
   * @field phone
   */
  //@Length(min = 1, max = 0, message = "PHONE长度为0")
  private String phone;

  /**
   * @field email
   */
  //@Length(min = 1, max = 0, message = "EMAIL长度为0")
  private String email;

  /**
   * @field wechatId
   */
  //@Length(min = 1, max = 0, message = "WECHAT_ID长度为0")
  private String wechatId;

  /**
   * @field street
   */
  //@Length(min = 1, max = 0, message = "STREET长度为0")
  private String street;

  /**
   * @field city
   */
  //@Length(min = 1, max = 0, message = "CITY长度为0")
  private String city;

  /**
   * @field carSellingState
   */
  //@Length(min = 1, max = 0, message = "CAR_SELLING_STATE长度为0")
  private String carSellingState;

  /**
   * @field financeState
   */
  //@Length(min = 1, max = 0, message = "FINANCE_STATE长度为0")
  private String financeState;

  /**
   * @field leaseState
   */
  //@Length(min = 1, max = 0, message = "LEASE_STATE长度为0")
  private String leaseState;

  /**
   * @field zipCode
   */
  //@Length(min = 1, max = 0, message = "ZIP_CODE长度为0")
  private String zipCode;

  /**
   * @field university
   */
  //@Length(min = 1, max = 0, message = "UNIVERSITY长度为0")
  private String university;

  /**
   * @field status
   */
  //@Length(min = 1, max = 0, message = "STATUS长度为0")
  private String status;

  /**
   * @field rating
   */
  //@Length(min = 1, max = 0, message = "RATING长度为0")
  private String rating;

  /**
   * @field potentialBuyingDate
   */
  private Date potentialBuyingDate;

  /**
   * @field service
   */
  //@Length(min = 1, max = 0, message = "SERVICE长度为0")
  private String service;

  /**
   * @field deleteFlag
   */
  private Byte deleteFlag;

  /**
   * @field createUserId
   */
  //@Length(min = 1, max = 0, message = "CREATE_USER_ID长度为0")
  private String createUserId;

  /**
   * @field createTime
   */
  private Date createTime;

  /**
   * @field updateUserId
   */
  //@Length(min = 1, max = 0, message = "UPDATE_USER_ID长度为0")
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
   * @return wechatId
   */
  public String getWechatId() {
    return wechatId;
  }

  /**
   * @param wechatId
   */
  public void setWechatId(String wechatId) {
    this.wechatId = wechatId;
  }

  /**
   * @return street
   */
  public String getStreet() {
    return street;
  }

  /**
   * @param street
   */
  public void setStreet(String street) {
    this.street = street;
  }

  /**
   * @return city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return carSellingState
   */
  public String getCarSellingState() {
    return carSellingState;
  }

  /**
   * @param carSellingState
   */
  public void setCarSellingState(String carSellingState) {
    this.carSellingState = carSellingState;
  }

  /**
   * @return financeState
   */
  public String getFinanceState() {
    return financeState;
  }

  /**
   * @param financeState
   */
  public void setFinanceState(String financeState) {
    this.financeState = financeState;
  }

  /**
   * @return leaseState
   */
  public String getLeaseState() {
    return leaseState;
  }

  /**
   * @param leaseState
   */
  public void setLeaseState(String leaseState) {
    this.leaseState = leaseState;
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
   * @return status
   */
  public String getStatus() {
    return status;
  }

  /**
   * @param status
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * @return rating
   */
  public String getRating() {
    return rating;
  }

  /**
   * @param rating
   */
  public void setRating(String rating) {
    this.rating = rating;
  }

  /**
   * @return potentialBuyingDate
   */
  public Date getPotentialBuyingDate() {
    return potentialBuyingDate;
  }

  /**
   * @param potentialBuyingDate
   */
  public void setPotentialBuyingDate(Date potentialBuyingDate) {
    this.potentialBuyingDate = potentialBuyingDate;
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

  public ServiceInfo getServiceInfo() {
    return serviceInfo;
  }

  public void setServiceInfo(ServiceInfo serviceInfo) {
    this.serviceInfo = serviceInfo;
  }
}
