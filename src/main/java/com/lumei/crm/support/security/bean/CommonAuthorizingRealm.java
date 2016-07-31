package com.lumei.crm.support.security.bean;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource.Util;
import org.springframework.beans.factory.annotation.Autowired;

import com.lumei.crm.commons.util.Digests;
import com.lumei.crm.support.security.service.ShiroAuthorizationService;

/**
 * 
 * @author dave
 *
 */
public class CommonAuthorizingRealm extends AuthorizingRealm {
  @Autowired
  ShiroAuthorizationService shiroAuthorizationService;

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    try {
      String username = pc.getPrimaryPrincipal().toString();
      List<String> roles = shiroAuthorizationService.listRoleByUser(username);
      if (null != roles) {
        info.addRoles(roles);
      }
      List<String> permissions = shiroAuthorizationService.listPermissionsByUser(username);
      if (null != permissions) {
        info.addStringPermissions(permissions);
      }
    } catch (Exception e) {
      return null;
    }
    return info;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    try {
      String username = (String) token.getPrincipal();
      String password = new String((char[]) token.getCredentials());
      ShiroLoginUser user = shiroAuthorizationService.findUserByName(username);
      if (null == user) {
        throw new UnknownAccountException();
      }
      String salt = user.getSalt();
      if (!user.getPassword().equals(Digests.entryptPassword(salt, password))) {
        throw new IncorrectCredentialsException();
      }
      if (user.isLocked()) {
        throw new LockedAccountException();
      }
      return new SimpleAuthenticationInfo(username, password.toCharArray(), Util.bytes(salt),
          getName());
    } catch (UnknownAccountException e) {
      throw e;
    } catch (LockedAccountException e) {
      throw e;
    } catch (IncorrectCredentialsException e) {
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
      throw new AuthenticationException();
    }
  }

  public void removeUserCache(String userId) {
    SimplePrincipalCollection pc = new SimplePrincipalCollection();
    pc.add(userId, getName());
    super.clearCachedAuthorizationInfo(pc);
  }

}
