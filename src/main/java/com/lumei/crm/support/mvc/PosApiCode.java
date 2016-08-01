package com.lumei.crm.support.mvc;

import com.lumei.crm.commons.bean.ApiCode;

/**
 * 
 * @author dave
 *
 */
public enum PosApiCode implements ApiCode {

  /**
   * 正常
   */
  SUCCESS("0", "success"),
  /**
   * 未知错误
   */
  UNKNOWN_ERROR("001000", "unknown error"),
  /**
   * 日期格式错误
   */
  DATE_BIND_ERROR("001001", "date format error"),
  /**
   * 数字格式错误
   */
  NUBMER_BIND_ERROR("001001", "number format error"),
  /**
   * 新密码两次不一致
   */
  AUTH_ERROR1("001101", "new password inconsistent"),
  /**
   * 原密码错误
   */
  AUTH_ERROR2("001102", "old password incorrect"),
  /**
   * 用户名不可为空
   */
  AUTH_ERROR3("001103", "username is empty"),
  /**
   * 昵称不可为空
   */
  AUTH_ERROR4("001104", "nickname name is empty"),
  /**
   * 用户已经存在
   */
  AUTH_ERROR5("001105", "user exist"),
  /**
   * 用户不存在
   */
  AUTH_ERROR6("001106", "user not exist"),
  /**
   * 用户ID不可为空
   */
  AUTH_ERROR7("001107", "user id cannot be null"),
  /**
   * 密码长度不可小于6位非空白字符
   */
  AUTH_ERROR8("001108", "password cannot less than 6 characters");

  private String code;
  private String msg;

  PosApiCode(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Override
  public String toString() {
    return this.getCode();
  }

}
