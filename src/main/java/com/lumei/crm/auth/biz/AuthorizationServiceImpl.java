package com.lumei.crm.auth.biz;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lumei.crm.auth.bean.SysMenuConf;
import com.lumei.crm.auth.dao.OpAuthUserDao;
import com.lumei.crm.auth.dao.OpAuthUserRoleDao;
import com.lumei.crm.auth.entity.TOpAuthUser;
import com.lumei.crm.auth.entity.TOpAuthUserRole;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.support.security.bean.RoleUrl;
import com.lumei.crm.support.security.bean.ShiroLoginUser;
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
      String path =
          SysMenuConf.class.getClassLoader().getResource("conf/lumei/xml/sys-menu.xml").getPath();
      File configFile = new File(path);
      SAXReader saxReader = new SAXReader();
      Document doc = saxReader.read(configFile);
      List<Element> menus = doc.selectNodes("/menu-conf/menu");
      for (Element element : menus) {
        String url = element.attributeValue("url");
        List<Element> roles = element.element("roles").elements("role");
        for (Element element2 : roles) {
          String role = element2.attributeValue("name");
          RoleUrl e = new RoleUrl();
          e.setUrl(url);
          e.setRole(role);
          list.add(e);
        }
      }
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage(), e);
    }
    return list;
  }

}
