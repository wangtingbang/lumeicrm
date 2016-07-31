package com.lumei.crm.auth.biz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lumei.crm.auth.dao.OpAuthUserDao;
import com.lumei.crm.auth.dao.OpAuthUserRoleDao;
import com.lumei.crm.auth.entity.TOpAuthUser;
import com.lumei.crm.auth.entity.TOpAuthUserRole;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.support.security.bean.RoleUrl;
import com.lumei.crm.support.security.bean.ShiroLoginUser;
import com.lumei.crm.support.security.service.FilterChainsService;
import com.lumei.crm.support.security.service.ShiroAuthorizationService;

/**
 * 
 * @author dave
 *
 */
@Service(value = "shiroAuthorizationService")
public class AuthorizationServiceImpl implements ShiroAuthorizationService {
  private Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

  @Autowired
  private OpAuthUserDao opAuthUserDao;
  @Autowired
  private OpAuthUserRoleDao opAuthUserRoleDao;

  @Override
  public List<String> listRoleByUser(String userName) {
    String userId =
        opAuthUserDao.selectByExample(Example.newExample(TOpAuthUser.class).param("userName", userName)).get(0).getId();
    List<TOpAuthUserRole> list =
        opAuthUserRoleDao.selectByExample(Example.newExample(TOpAuthUserRole.class).param("userId", userId).distinct());
    List<String> result = new LinkedList<String>();
    for (TOpAuthUserRole tOpAuthUserRole : list) {
      result.add(tOpAuthUserRole.getRoleId());
    }
    return result;
  }

  @Override
  public List<String> listPermissionsByUser(String userName) {
    return null;
  }

  @Override
  public ShiroLoginUser findUserByName(String userName) {
    List<TOpAuthUser> opUserList =
        opAuthUserDao.selectByExample(Example.newExample(TOpAuthUser.class).param("userName", userName));
    if (null == opUserList || opUserList.size() == 0) {
      return null;
    }
    TOpAuthUser opUser = opUserList.get(0);
    ShiroLoginUser loginUser = new ShiroLoginUser();
    loginUser.setUserName(opUser.getUserName());
    loginUser.setPassword(opUser.getPassword());
    loginUser.setSalt(opUser.getSalt());
    loginUser.setLocked(0 == opUser.getEnabled());
    return loginUser;
  }

  @Override
  public List<RoleUrl> getAllUrlAndRole() {
    List<RoleUrl> list = new LinkedList<RoleUrl>();
    Properties properties = new Properties();
    try {
      InputStream is =
          new FileInputStream(new File(FilterChainsService.class.getClassLoader()
              .getResource("conf/lumei/properties/Dyna_auth.properties").getPath()));
      properties.load(is);
    } catch (IOException e) {
      logger.error("");
    }
    for (Iterator its = properties.keySet().iterator(); its.hasNext();) {
      String key = (String) its.next();
      String[] strs = properties.getProperty(key).trim().split(",");
      for (int i = 0; i < strs.length; i++) {
        RoleUrl e = new RoleUrl();
        e.setUrl(key);
        e.setRole(strs[i]);
        list.add(e);
      }
    }
    return list;
  }

}
