package com.lumei.crm.auth.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lumei.crm.auth.bean.LoginUser;
import com.lumei.crm.auth.bean.SysMenu;
import com.lumei.crm.auth.bean.SysMenuConf;
import com.lumei.crm.auth.biz.OpAuthUserBusiness;
import com.lumei.crm.auth.biz.OpAuthUserRoleBusiness;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.commons.util.IpUtil;
import com.lumei.crm.support.op.OpLoggerFactory;
import com.lumei.crm.util.SessionUtil;
import com.lumei.crm.util.Tree;


/**
 * 
 * @author dave
 *
 */
@Controller
public class AuthController {
  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
  
  @Autowired
  OpAuthUserBusiness opAuthUserBusiness;
  @Autowired
  OpAuthUserRoleBusiness opAuthUserRoleBusiness;

  @RequestMapping(value = "authlogin", method = RequestMethod.GET)
  public String login() throws Exception {
    Subject currentUser = SecurityUtils.getSubject();
    if(currentUser.isAuthenticated()){
      return "redirect:/"; 
    }
    return "redirect:/login";
  }
  
  @RequestMapping(value = "authlogin", method = RequestMethod.POST)
  public String login(Model model, OpAuthUser user, HttpServletRequest request) throws Exception {
    Subject currentUser = SecurityUtils.getSubject();
    if(currentUser.isAuthenticated()){
      return "redirect:/"; 
    }
    String userId = user.getId();
    String loginName = user.getUserName();
    String password = user.getPassword();
    
    try {
      UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
      currentUser.login(token);
      loadSessionData(loginName, request);  
    } catch (LockedAccountException e) {
      model.addAttribute("errorMsg", "用户锁定，请联系管理员!");
      return "common/login";
    } catch (IncorrectCredentialsException e) {
      model.addAttribute("errorMsg", "用户名或密码错误!");
      return "common/login";
    } catch (UnknownAccountException e) {
      model.addAttribute("errorMsg", "用户名或密码错误!");
      return "common/login";
    } catch (AuthenticationException e) {
      logger.error("error:{}", e);
      model.addAttribute("errorMsg", "用户认证错误!");
      return "common/login";
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      throw e;
    }
    return "redirect:/";
  }

  private void loadSessionData(String userName, HttpServletRequest request) {
    LoginUser loginUser = SessionUtil.getCurrentUser();
    SessionUtil.setAttributes("loginIp", IpUtil.getIpAddr(request));
    loadUser(loginUser, userName);
    loadRole(loginUser);
    loadSideBar(loginUser);
  }

  private void loadUser(LoginUser loginUser, String userName) {
    OpAuthUser user = opAuthUserBusiness.findUserByName(userName);
    loginUser.setUser(user);
  }

  private void loadRole(LoginUser loginUser) {
    List<String> roles = opAuthUserRoleBusiness.listAllRoleByUser(loginUser.getUser().getId());
    loginUser.setRoles(roles);
  }

  private void loadSideBar(LoginUser loginUser) {
    Map<String, Tree<SysMenu>> map = new HashMap<String, Tree<SysMenu>>();
    List<Tree<SysMenu>> list = new LinkedList<Tree<SysMenu>>();
    Set<SysMenu> menuSet = new HashSet<SysMenu>();

    List<String> roles = loginUser.getRoles();
    for (String role : roles) {
      Set<SysMenu> s = SysMenuConf.getMenuConfigs().get(role);
      if (null == s) {
        continue;
      }
      menuSet.addAll(s);
    }
    TreeSet<SysMenu> treeMenuSet = new TreeSet<SysMenu>(menuSet);
    for (SysMenu sysMenu : treeMenuSet) {
      Tree<SysMenu> tree = new Tree<SysMenu>();
      String id = sysMenu.getId();
      tree.setName(sysMenu.getName());
      tree.setType(Tree.FOLDER);
      tree.setParam(sysMenu);
      if (StringUtils.isNotBlank(sysMenu.getUrl())) {
        tree.setType(Tree.ITEM);
      }
      map.put(id, tree);
    }

    for (SysMenu sysMenu : treeMenuSet) {
      String id = sysMenu.getId();
      String pId = sysMenu.getParentId();
      if (StringUtils.isNotBlank(pId)) {
        Tree<SysMenu> ptree = map.get(pId);
        if (null == ptree) {
          continue;
        }
        ptree.newAdditionalParameters().getChildren().add(map.get(id));
      } else {
        list.add(map.get(id));
      }
    }
    loginUser.setTree(list);
  }

}
