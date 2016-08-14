package com.lumei.crm.util;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.lumei.crm.auth.bean.LoginUser;
import com.lumei.crm.auth.bean.SysMenu;
import com.lumei.crm.auth.bean.SysRole;
import com.lumei.crm.auth.dto.OpAuthUser;

/**
 * 
 * @author dave
 *
 */
public class SessionUtil {
  public static LoginUser getCurrentUser() {
    Subject currentUser = SecurityUtils.getSubject();
    Session session = currentUser.getSession();
    LoginUser loginUser = (LoginUser) session.getAttribute(LoginUser.CURRENT_USER);
    if (null == loginUser) {
      loginUser = new LoginUser();
      session.setAttribute(LoginUser.CURRENT_USER, loginUser);
    }
    return loginUser;
  }

  public static String getSessionId() {
    Subject currentUser = SecurityUtils.getSubject();
    Session session = currentUser.getSession();
    return session.getId().toString();
  }
  
  private static OpAuthUser getOpAuthUser(){
    return getCurrentUser().getUser();
  }
  
  public static String getCurrentUserId() {
    return getOpAuthUser()==null?"":getOpAuthUser().getId();
  }

  public static String getCurrentUserName() {
    return getOpAuthUser()==null?"":getOpAuthUser().getUserName();
  }

  public static String getCurrentUserNickName() {
    return getOpAuthUser()==null?"":getOpAuthUser().getNickName();
  }

  public static Object getAttributes(String key) {
    return getCurrentUser().getAttributes().get(key);
  }

  public static void setAttributes(String key, Object value) {
    getCurrentUser().getAttributes().put(key, value);
  }

  public static List<Tree<SysMenu>> currentUserMenuTree() {
    return getCurrentUser().getTree();
  }
  
  public static String getLoginIp(){
    return getAttributes("loginIp").toString();
  }
  
  public static boolean salesReadonly(String salesId){
  if(getCurrentUser().getRoles().contains(SysRole.CUSTOMER_MANAGER.getKey())
		  ||getCurrentUser().getRoles().contains(SysRole.ADMIN.getKey())
		  ||getCurrentUser().getRoles().contains(SysRole.CUSTOMER_SERVICE.getKey())){
        return false;
  }
  if(SessionUtil.getCurrentUserId().equals(salesId)){
    return false;
  }
  return true;
  }
  
  public static boolean notesReadonly(String salesId){
	  if(getCurrentUser().getRoles().contains(SysRole.CUSTOMER_MANAGER.getKey())
			  ||getCurrentUser().getRoles().contains(SysRole.ADMIN.getKey())){
	        return false;
	    }
	  if(SessionUtil.getCurrentUserId().equals(salesId)){
		    return false;
		}
	  return true;
  }
  
  public static boolean assignRight(){
	  if(getCurrentUser().getRoles().contains(SysRole.CUSTOMER_MANAGER.getKey())
			  ||getCurrentUser().getRoles().contains(SysRole.ADMIN.getKey())){
	        return true;
	    }
	  return false;
  }
  
  
}
