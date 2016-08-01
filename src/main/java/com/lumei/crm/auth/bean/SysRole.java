package com.lumei.crm.auth.bean;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author dave
 *
 */
public enum SysRole {

  ADMIN("ADMIN", "Administrator"),
  SALES("SALES", "Sales"),
  CUSTOMER_SERVICE("CUSTOMER_SERVICE", "Customer Service");
  
  private String key;
  private String desc;

  SysRole(String key, String desc) {
    this.key = key;
    this.desc = desc;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public static List<Map<String, String>> toList() {
    List list = new LinkedList();
    for (SysRole sysRole : SysRole.values()) {
      Map m = new HashMap();
      m.put("key", sysRole.getKey());
      m.put("desc", sysRole.getDesc());
      list.add(m);
    }
    return list;
  }

  public static SysRole get(String key) {
    for (SysRole sysRole : SysRole.values()) {
      if (sysRole.getKey().equals(key)) {
        return sysRole;
      }
    }
    return null;
  }
}
