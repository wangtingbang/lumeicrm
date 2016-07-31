package com.lumei.crm.support.security.bean;

/**
 * 
 * @author dave
 *
 */
public class ShiroLoginUser {
  private boolean locked;
  private String userName;
  private String password;
  private String salt;

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

}
