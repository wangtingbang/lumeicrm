package com.lumei.crm.auth.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author dave
 *
 */
public class SysMenu implements Comparable<SysMenu>, Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String id;
  private String parentId;
  private String name;
  private String url;
  private int order;
  private String icon;
  private List<String> roles;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  @Override
  public int compareTo(SysMenu o) {
    return this.order - o.order;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

}
