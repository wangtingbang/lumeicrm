package com.lumei.crm.auth.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.util.Tree;
/**
 * 
 * @author dave
 *
 */
public class LoginUser implements Serializable {
  private static final long serialVersionUID = 1652207803981229657L;
  public static final String CURRENT_USER = "CURRENT_USER";

  OpAuthUser user;
  List<String> roles;
  List<Tree<SysMenu>> tree;
  Map<String, Object> attributes = new HashMap<String, Object>();

  public OpAuthUser getUser() {
    return user;
  }

  public void setUser(OpAuthUser user) {
    this.user = user;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public List<Tree<SysMenu>> getTree() {
    return tree;
  }

  public void setTree(List<Tree<SysMenu>> tree) {
    this.tree = tree;
  }

  public Map<String, Object> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

}
