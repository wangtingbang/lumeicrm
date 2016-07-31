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
  SUCCESS("0", "正常"),
  /**
   * 未知错误
   */
  UNKNOWN_ERROR("001000", "未知错误"),
  /**
   * 日期格式错误
   */
  DATE_BIND_ERROR("001001", "日期格式错误"),
  /**
   * 数字格式错误
   */
  NUBMER_BIND_ERROR("001001", "数字格式错误"),
  /**
   * 新密码两次不一致
   */
  AUTH_ERROR1("001101", "新密码两次不一致"),
  /**
   * 原密码错误
   */
  AUTH_ERROR2("001102", "原密码错误"),
  /**
   * 用户名不可为空
   */
  AUTH_ERROR3("001103", "用户名不可为空"),
  /**
   * 昵称不可为空
   */
  AUTH_ERROR4("001104", "昵称不可为空"),
  /**
   * 用户已经存在
   */
  AUTH_ERROR5("001105", "用户已经存在"),
  /**
   * 用户不存在
   */
  AUTH_ERROR6("001106", "用户不存在"),
  /**
   * 用户ID不可为空
   */
  AUTH_ERROR7("001107", "用户ID不可为空"),
  /**
   * 密码长度不可小于6位非空白字符
   */
  AUTH_ERROR8("001108", "密码长度不可小于6位非空白字符");

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
