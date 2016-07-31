package com.lumei.crm.support.security.service;

import java.util.List;

import com.lumei.crm.support.security.bean.RoleUrl;
import com.lumei.crm.support.security.bean.ShiroLoginUser;

/**
 * 
 * @author dave
 *
 */
public interface ShiroAuthorizationService {
  List<String> listRoleByUser(String userName);

  List<String> listPermissionsByUser(String userName);

  ShiroLoginUser findUserByName(String userName);

  List<RoleUrl> getAllUrlAndRole();

}
