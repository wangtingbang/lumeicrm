package com.lumei.crm.customer.dto;

import java.io.Serializable;
import java.util.Date;
// import com.cmbchina.commons.biz.support.BaseModel;
// import org.hibernate.validator.constraints.Length;

import com.lumei.crm.commons.biz.support.BaseModel;

/**
 * 
 * @generated by Code Generator v0.1
 * @date Sat Aug 06 13:01:17 CST 2016
 */
public class Profile extends BaseModel<String> implements Serializable {

  private static final long serialVersionUID = 1L;

  private boolean readonly;

  public boolean isReadonly() {
    return readonly;
  }

  public void setReadonly(boolean readonly) {
    this.readonly = readonly;
  }
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

  private String sales;
  private String createUserName;
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
private String updateUserName;
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
   * @field state
   */
  //@Length(min = 1, max = 0, message = "STATE长度为0")
  private String state;

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
   * @field carSellingStatus
   */
  //@Length(min = 1, max = 0, message = "CAR_SELLING_STATUS长度为0")
  private String carSellingStatus;

  /**
   * @field financeStatus
   */
  //@Length(min = 1, max = 0, message = "FINANCE_STATUS长度为0")
  private String financeStatus;

  /**
   * @field leaseStatus
   */
  //@Length(min = 1, max = 0, message = "LEASE_STATUS长度为0")
  private String leaseStatus;

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
   * @return state
   */
  public String getState() {
    return state;
  }

  /**
   * @param state
   */
  public void setState(String state) {
    this.state = state;
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
   * @return carSellingStatus
   */
  public String getCarSellingStatus() {
    return carSellingStatus;
  }

  /**
   * @param carSellingStatus
   */
  public void setCarSellingStatus(String carSellingStatus) {
    this.carSellingStatus = carSellingStatus;
  }

  /**
   * @return financeStatus
   */
  public String getFinanceStatus() {
    return financeStatus;
  }

  /**
   * @param financeStatus
   */
  public void setFinanceStatus(String financeStatus) {
    this.financeStatus = financeStatus;
  }

  /**
   * @return leaseStatus
   */
  public String getLeaseStatus() {
    return leaseStatus;
  }

  /**
   * @param leaseStatus
   */
  public void setLeaseStatus(String leaseStatus) {
    this.leaseStatus = leaseStatus;
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

  private ServiceInfo serviceInfo;

  public ServiceInfo getServiceInfo() {
    return serviceInfo;
  }

  public void setServiceInfo(ServiceInfo serviceInfo) {
    this.serviceInfo = serviceInfo;
  }

  public String getSales() {
    return sales;
  }

  public void setSales(String sales) {
    this.sales = sales;
  }

}
