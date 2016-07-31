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
  PRD_OP("PRD_OP", "产品发布岗"),
  PRD_AP("PRD_AP", "产品审核岗"),
  FIN_OP("FIN_OP", "固定理财资金维护岗"),
  FIN_AP("FIN_AP", "固定理财资金审核岗"),
  PSP_USER_FIN_OP("PSP_USER_FIN_OP", "售后宝用户资金维护岗"),
  PSP_DCCJ_FIN_OP("PSP_DCCJ_FIN_OP", "售后宝平台资金维护岗"),
  PSP_STORE_FIN_OP("PSP_STORE_FIN_OP", "售后宝门店资金维护岗"),
  STORE_OP("STORE_OP", "门店管理岗"),
  ID_AP("ID_AP", "身份认证审核岗"),
  STORE_USER_OP("STORE_USER_OP", "门店消费维护岗");
  
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
