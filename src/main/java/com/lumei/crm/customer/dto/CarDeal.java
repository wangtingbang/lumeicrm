package com.lumei.crm.customer.dto;

import java.io.Serializable;
import java.util.Date;

import com.lumei.crm.commons.biz.support.BaseModel;

/**
 * 
 * @generated by Code Generator v0.1
 * @date Sat Aug 13 20:32:59 CST 2016
 */
public class CarDeal extends BaseModel<String> implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * @field id
   */
  //@Length(min = 1, max = 0, message = "ID长度为0")
  private String id;

  /**
   * @field customerId
   */
  //@Length(min = 1, max = 0, message = "CUSTOMER_ID长度为0")
  private String customerId;

  /**
   * @field salesId
   */
  //@Length(min = 1, max = 0, message = "SALES_ID长度为0")
  private String salesId;

  private String customerName;

  private String wechat;

  private String phone;

  private String salesName;

  private String createUserName;

  private String updateUserName;

  private boolean readonly;

  private String latestNotes;
  
  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getWechat() {
    return wechat;
  }

  public void setWechat(String wechat) {
    this.wechat = wechat;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getSalesName() {
    return salesName;
  }

  public void setSalesName(String salesName) {
    this.salesName = salesName;
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

  public boolean isReadonly() {
    return readonly;
  }

  public void setReadonly(boolean readonly) {
    this.readonly = readonly;
  }

  /**
   * @field dealStatus
   */
  private Byte dealStatus;

  /**
   * @field dealDate
   */
  private Date dealDate;

  /**
   * @field rating
   */
  private Byte rating;

  /**
   * @field deposit
   */
  private Byte deposit;

  /**
   * @field creditCardNo
   */
  //@Length(min = 1, max = 0, message = "CREDIT_CARD_NO长度为0")
  private String creditCardNo;

  /**
   * @field isNew
   */
  private Byte isNew;
  
  private Byte source;
  
  private String mileages;
  
  private String tradeIn;
  
  private String caseNo;

  /**
   * @field years
   */
  //@Length(min = 1, max = 0, message = "YEARS长度为0")
  private String years;

  /**
   * @field makes
   */
  //@Length(min = 1, max = 0, message = "MAKES长度为0")
  private String makes;

  /**
   * @field model
   */
  //@Length(min = 1, max = 0, message = "MODEL长度为0")
  private String model;

  /**
   * @field exteriorColor
   */
  //@Length(min = 1, max = 0, message = "EXTERIOR_COLOR长度为0")
  private String exteriorColor;

  /**
   * @field interiorColor
   */
  //@Length(min = 1, max = 0, message = "INTERIOR_COLOR长度为0")
  private String interiorColor;

  /**
   * @field msrp
   */
  //@Length(min = 1, max = 0, message = "MSRP长度为0")
  private String msrp;

  /**
   * @field sellingPrice
   */
  //@Length(min = 1, max = 0, message = "SELLING_PRICE长度为0")
  private String sellingPrice;

  /**
   * @field comments
   */
  //@Length(min = 1, max = 0, message = "COMMENTS长度为0")
  private String comments;

  /**
   * @field method
   */
  private Byte method;

  /**
   * @field downPayment
   */
  //@Length(min = 1, max = 0, message = "DOWN_PAYMENT长度为0")
  private String downPayment;

  /**
   * @field rate
   */
  //@Length(min = 1, max = 0, message = "RATE长度为0")
  private String rate;

  /**
   * @field terms
   */
  //@Length(min = 1, max = 0, message = "TERMS长度为0")
  private String terms;

  /**
   * @field monthlyPay
   */
  //@Length(min = 1, max = 0, message = "MONTHLY_PAY长度为0")
  private String monthlyPay;

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
   * @field ambassador
   */
  private String ambassador;

  /**
   * @field tradeInValue
   */
  private String tradeInValue;

  /**
   * @field tradeInMake
   */
  private String tradeInMake;

  /**
   * @field tradeInModel
   */
  private String tradeInModel;

  /**
   * @field tradeInYear
   */
  private String tradeInYear;

  /**
   * @field tradeInMileage
   */
  private String tradeInMileage;

  /**
   * @field tradeInEc
   */
  private String tradeInEc;

  /**
   * @field tradeInIc
   */
  private String tradeInIc;

  /**
   * @field remark
   */
  private String remark;

  /**
   * @field vinNo
   */
  private String vinNo;

  /**
   * @field qualification
   */
  private Byte qualification;

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
   * @return customerId
   */
  public String getCustomerId() {
    return customerId;
  }

  /**
   * @param customerId
   */
  public void setCustomerId(String customerId) {
    this.customerId = customerId;
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
   * @return dealStatus
   */
  public Byte getDealStatus() {
    return dealStatus;
  }

  /**
   * @param dealStatus
   */
  public void setDealStatus(Byte dealStatus) {
    this.dealStatus = dealStatus;
  }

  /**
   * @return dealDate
   */
  public Date getDealDate() {
    return dealDate;
  }

  /**
   * @param dealDate
   */
  public void setDealDate(Date dealDate) {
    this.dealDate = dealDate;
  }

  /**
   * @return rating
   */
  public Byte getRating() {
    return rating;
  }

  /**
   * @param rating
   */
  public void setRating(Byte rating) {
    this.rating = rating;
  }

  /**
   * @return deposit
   */
  public Byte getDeposit() {
    return deposit;
  }

  /**
   * @param deposit
   */
  public void setDeposit(Byte deposit) {
    this.deposit = deposit;
  }

  /**
   * @return creditCardNo
   */
  public String getCreditCardNo() {
    return creditCardNo;
  }

  /**
   * @param creditCardNo
   */
  public void setCreditCardNo(String creditCardNo) {
    this.creditCardNo = creditCardNo;
  }

  /**
   * @return isNew
   */
  public Byte getIsNew() {
    return isNew;
  }

  /**
   * @param isNew
   */
  public void setIsNew(Byte isNew) {
    this.isNew = isNew;
  }

  public Byte getSource() {
    return source;
  }

  public void setSource(Byte source) {
    this.source = source;
  }

  public String getMileages() {
    return mileages;
  }

  public void setMileages(String mileages) {
    this.mileages = mileages;
  }

  public String getTradeIn() {
    return tradeIn;
  }

  public void setTradeIn(String tradeIn) {
    this.tradeIn = tradeIn;
  }

  public String getCaseNo() {
    return caseNo;
  }

  public void setCaseNo(String caseNo) {
    this.caseNo = caseNo;
  }

  /**
   * @return years
   */
  public String getYears() {
    return years;
  }

  /**
   * @param years
   */
  public void setYears(String years) {
    this.years = years;
  }

  /**
   * @return makes
   */
  public String getMakes() {
    return makes;
  }

  /**
   * @param makes
   */
  public void setMakes(String makes) {
    this.makes = makes;
  }

  /**
   * @return model
   */
  public String getModel() {
    return model;
  }

  /**
   * @param model
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * @return exteriorColor
   */
  public String getExteriorColor() {
    return exteriorColor;
  }

  /**
   * @param exteriorColor
   */
  public void setExteriorColor(String exteriorColor) {
    this.exteriorColor = exteriorColor;
  }

  /**
   * @return interiorColor
   */
  public String getInteriorColor() {
    return interiorColor;
  }

  /**
   * @param interiorColor
   */
  public void setInteriorColor(String interiorColor) {
    this.interiorColor = interiorColor;
  }

  /**
   * @return msrp
   */
  public String getMsrp() {
    return msrp;
  }

  /**
   * @param msrp
   */
  public void setMsrp(String msrp) {
    this.msrp = msrp;
  }

  /**
   * @return sellingPrice
   */
  public String getSellingPrice() {
    return sellingPrice;
  }

  /**
   * @param sellingPrice
   */
  public void setSellingPrice(String sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  /**
   * @return comments
   */
  public String getComments() {
    return comments;
  }

  /**
   * @param comments
   */
  public void setComments(String comments) {
    this.comments = comments;
  }

  /**
   * @return method
   */
  public Byte getMethod() {
    return method;
  }

  /**
   * @param method
   */
  public void setMethod(Byte method) {
    this.method = method;
  }

  /**
   * @return downPayment
   */
  public String getDownPayment() {
    return downPayment;
  }

  /**
   * @param downPayment
   */
  public void setDownPayment(String downPayment) {
    this.downPayment = downPayment;
  }

  /**
   * @return rate
   */
  public String getRate() {
    return rate;
  }

  /**
   * @param rate
   */
  public void setRate(String rate) {
    this.rate = rate;
  }

  /**
   * @return terms
   */
  public String getTerms() {
    return terms;
  }

  /**
   * @param terms
   */
  public void setTerms(String terms) {
    this.terms = terms;
  }

  /**
   * @return monthlyPay
   */
  public String getMonthlyPay() {
    return monthlyPay;
  }

  /**
   * @param monthlyPay
   */
  public void setMonthlyPay(String monthlyPay) {
    this.monthlyPay = monthlyPay;
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
   * @return ambassador
   */
  public String getAmbassador() {
    return ambassador;
  }

  /**
   * @param ambassador
   */
  public void setAmbassador(String ambassador) {
    this.ambassador = ambassador;
  }

  /**
   * @return tradeInValue
   */
  public String getTradeInValue() {
    return tradeInValue;
  }

  /**
   * @param tradeInValue
   */
  public void setTradeInValue(String tradeInValue) {
    this.tradeInValue = tradeInValue;
  }

  /**
   * @return tradeInMake
   */
  public String getTradeInMake() {
    return tradeInMake;
  }

  /**
   * @param tradeInMake
   */
  public void setTradeInMake(String tradeInMake) {
    this.tradeInMake = tradeInMake;
  }

  /**
   * @return tradeInModel
   */
  public String getTradeInModel() {
    return tradeInModel;
  }

  /**
   * @param tradeInModel
   */
  public void setTradeInModel(String tradeInModel) {
    this.tradeInModel = tradeInModel;
  }

  /**
   * @return tradeInYear
   */
  public String getTradeInYear() {
    return tradeInYear;
  }

  /**
   * @param tradeInYear
   */
  public void setTradeInYear(String tradeInYear) {
    this.tradeInYear = tradeInYear;
  }

  /**
   * @return tradeInMileage
   */
  public String getTradeInMileage() {
    return tradeInMileage;
  }

  /**
   * @param tradeInMileage
   */
  public void setTradeInMileage(String tradeInMileage) {
    this.tradeInMileage = tradeInMileage;
  }

  /**
   * @return tradeInEc
   */
  public String getTradeInEc() {
    return tradeInEc;
  }

  /**
   * @param tradeInEc
   */
  public void setTradeInEc(String tradeInEc) {
    this.tradeInEc = tradeInEc;
  }

  /**
   * @return tradeInIc
   */
  public String getTradeInIc() {
    return tradeInIc;
  }

  /**
   * @param tradeInIc
   */
  public void setTradeInIc(String tradeInIc) {
    this.tradeInIc = tradeInIc;
  }

  /**
   * @return remark
   */
  public String getRemark() {
    return remark;
  }

  /**
   * @param remark
   */
  public void setRemark(String remark) {
    this.remark = remark;
  }

  /**
   * @return vinNo
   */
  public String getVinNo() {
    return vinNo;
  }

  /**
   * @param vinNo
   */
  public void setVinNo(String vinNo) {
    this.vinNo = vinNo;
  }

  /**
   * @return qualification
   */
  public Byte getQualification() {
    return qualification;
  }

  /**
   * @param qualification
   */
  public void setQualification(Byte qualification) {
    this.qualification = qualification;
  }

public String getLatestNotes() {
	return latestNotes;
}

public void setLatestNotes(String latestNotes) {
	this.latestNotes = latestNotes;
}

}
