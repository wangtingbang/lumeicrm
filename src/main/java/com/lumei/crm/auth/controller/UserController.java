package com.lumei.crm.auth.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lumei.crm.auth.bean.SysRole;
import com.lumei.crm.auth.bean.SysRoleFunction;
import com.lumei.crm.auth.biz.OpAuthUserBusiness;
import com.lumei.crm.auth.biz.OpAuthUserRoleBusiness;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.auth.dto.OpAuthUserRole;
import com.lumei.crm.auth.entity.TOpAuthUser;
import com.lumei.crm.auth.entity.TOpAuthUserRole;
import com.lumei.crm.commons.bean.BusinessException;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.BeanUtils;
import com.lumei.crm.commons.util.DateTimeUtil;
import com.lumei.crm.commons.util.Digests;
import com.lumei.crm.support.mvc.PosApiCode;
import com.lumei.crm.util.SessionUtil;

/**
 * 
 * @author dave
 *
 */
@Controller
public class UserController {
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  OpAuthUserBusiness opAuthUserBusiness;
  @Autowired
  OpAuthUserRoleBusiness opAuthUserRoleBusiness;

  @RequestMapping(value = "auth/resetpwd", method = RequestMethod.POST)
  @ResponseBody
  public Map resetpwd(String oldPwd, String newPwd1, String newPwd2, HttpServletRequest request)
      throws Exception {
    Map<String, String> rm = new HashMap<String, String>();
    String userId = SessionUtil.getCurrentUserId();
    OpAuthUser user = opAuthUserBusiness.find(userId, OpAuthUser.class);
    if (!(StringUtils.isNotBlank(newPwd1) && StringUtils.isNotBlank(newPwd2)
        && StringUtils.trimToEmpty(newPwd1).length() >= 6 && StringUtils.trimToEmpty(newPwd1)
        .length() >= 6)) {
      throw new BusinessException(PosApiCode.AUTH_ERROR8.getCode());
    }
    if (null != user) {
      oldPwd = Digests.entryptPassword(user.getSalt(), oldPwd);
      if (oldPwd.equals(user.getPassword())) {
        if (newPwd1.equals(newPwd2)) {
          user.setSalt(Digests.generateSalt());
          user.setPassword(Digests.entryptPassword(user.getSalt(), newPwd1));
          user.setUpdateBy(userId);
          user.setUpdateTime(DateTimeUtil.now());
          opAuthUserBusiness.updateSelective(user);
          rm.put("msg", "update success !");

        } else {
          throw new BusinessException(PosApiCode.AUTH_ERROR1.getCode());
        }
      } else {
        throw new BusinessException(PosApiCode.AUTH_ERROR2.getCode());
      }
    } else {
      throw new BusinessException(PosApiCode.UNKNOWN_ERROR.getCode());
    }
    return rm;
  }

  @RequestMapping(value = "auth/user/listUserByPage", method = RequestMethod.GET)
  public String listUserByPage() {
    return "auth/listUser";
  }

  @RequestMapping(value = "auth/user/listUserByPage", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<OpAuthUser> listUserByPage(OpAuthUser opAuthUser, int page, int limit) {
    if (StringUtils.isAnyBlank(opAuthUser.getUserName())) {
      opAuthUser.setUserName(null);
    }
    if (StringUtils.isAnyBlank(opAuthUser.getNickName())) {
      opAuthUser.setNickName(null);
    }
    return opAuthUserBusiness.listByPage(
        Example.newExample(TOpAuthUser.class).paramLikeTo("userName", opAuthUser.getUserName())
            .paramLikeTo("nickName", opAuthUser.getNickName()).orderBy("create_time").desc(), page,
        limit);
  }

  @RequestMapping(value = "auth/user/listAllUserEnabled", method = RequestMethod.GET)
  @ResponseBody
  public List<OpAuthUser> listAllUserEnabled() {
    return opAuthUserBusiness.list(
        Example.newExample(TOpAuthUser.class).param("enabled", 1).orderBy("nickName").asc());
  }
  
  @RequestMapping(value = "auth/user/create", method = RequestMethod.POST)
  @ResponseBody
  public Map create(OpAuthUser opAuthUser, HttpServletRequest request) throws BusinessException {
    if (StringUtils.isAnyBlank(opAuthUser.getUserName())) {
      throw new BusinessException(PosApiCode.AUTH_ERROR3.getCode());
    }
    if (StringUtils.isAnyBlank(opAuthUser.getNickName())) {
      throw new BusinessException(PosApiCode.AUTH_ERROR4.getCode());
    }
    int cnt =
        opAuthUserBusiness.count(Example.newExample(TOpAuthUser.class).param("userName",
            opAuthUser.getUserName()));
    if (cnt > 0) {
      throw new BusinessException(PosApiCode.AUTH_ERROR5.getCode());
    }
    opAuthUser.setSalt(Digests.generateSalt());
    opAuthUser.setPassword(Digests.entryptPassword(opAuthUser.getSalt(), opAuthUser.getUserName()));
    opAuthUser.setEnabled((byte) 1);
    opAuthUser.setCreateBy(SessionUtil.getCurrentUserId());
    opAuthUser.setCreateTime(DateTimeUtil.now());
    opAuthUserBusiness.create(opAuthUser);
    Map<String, String> rm = new HashMap<String, String>();
    rm.put("msg", "create success !");
    return rm;
  }

  @RequestMapping(value = "auth/user/update", method = RequestMethod.POST)
  @ResponseBody
  public Map update(OpAuthUser opAuthUser, HttpServletRequest request) throws BusinessException {
    if (StringUtils.isAnyBlank(opAuthUser.getNickName())) {
      throw new BusinessException(PosApiCode.AUTH_ERROR4.getCode());
    }
    opAuthUser.setUpdateBy(SessionUtil.getCurrentUserId());
    opAuthUser.setUpdateTime(DateTimeUtil.now());
    opAuthUser.setUserName(null);
    opAuthUser.setSalt(null);
    opAuthUser.setPassword(null);
    opAuthUserBusiness.updateSelective(opAuthUser);
    Map<String, String> rm = new HashMap<String, String>();
    rm.put("msg", "update success !");
    return rm;
  }

  @RequestMapping(value = "auth/user/resetuserpwd", method = RequestMethod.POST)
  @ResponseBody
  public Map resetUserPwd(String userId, HttpServletRequest request) throws BusinessException {
    Map<String, String> rm = new HashMap<String, String>();
    OpAuthUser opAuthUser = opAuthUserBusiness.find(userId, OpAuthUser.class);
    if (opAuthUser == null) {
      throw new BusinessException(PosApiCode.AUTH_ERROR6.getCode());
    }
    opAuthUser.setPassword(Digests.entryptPassword(opAuthUser.getSalt(), opAuthUser.getUserName()));
    opAuthUser.setUpdateBy(SessionUtil.getCurrentUserId());
    opAuthUser.setUpdateTime(DateTimeUtil.now());
    opAuthUserBusiness.updateSelective(opAuthUser);
    rm.put("msg", "reset success !");
    return rm;
  }

  @RequestMapping(value = "auth/user/lock", method = RequestMethod.POST)
  @ResponseBody
  public Map lock(@RequestParam String userId, HttpServletRequest request) throws BusinessException {
    if (StringUtils.isAnyBlank(userId)) {
      throw new BusinessException(PosApiCode.AUTH_ERROR7.getCode());
    }
    OpAuthUser opAuthUser = opAuthUserBusiness.find(userId, OpAuthUser.class);
    byte flag = opAuthUser.getEnabled().byteValue();
    flag = (byte) ((~flag) & 1);
    String act = flag == 0 ? "lock" : "unlock";
    opAuthUser.setEnabled(flag);
    opAuthUser.setUpdateBy(SessionUtil.getCurrentUserId());
    opAuthUser.setUpdateTime(DateTimeUtil.now());
    opAuthUserBusiness.updateSelective(opAuthUser);
    Map<String, String> rm = new HashMap<String, String>();
    rm.put("msg", act + " success !");
    return rm;
  }

  @RequestMapping(value = "auth/user/listAllRole", method = RequestMethod.GET)
  @ResponseBody
  public Map listAllRole() {
    Map rm = new HashMap();
    rm.put("allRoles", SysRole.toList());
    return rm;
  }

  @RequestMapping(value = "auth/user/listUserRoleByUserId", method = RequestMethod.GET)
  @ResponseBody
  public Map listUserRoleByUserId(@RequestParam String userId) {
    Map rm = new HashMap();
    List<OpAuthUserRole> list =
        opAuthUserRoleBusiness.list(Example.newExample(TOpAuthUserRole.class).param("userId",
            userId));
    rm.put("list", list);
    return rm;
  }

  @RequestMapping(value = "auth/user/addUserRole", method = RequestMethod.POST)
  @ResponseBody
  public Map addUserRole(OpAuthUserRole opAuthUserRole, HttpServletRequest request) {
    Map<String, String> rm = new HashMap<String, String>();
    List<String> userRoles = opAuthUserRoleBusiness.listAllRoleByUser(opAuthUserRole.getUserId());
    for (String userRole : userRoles) {
      if (userRole.equals(opAuthUserRole.getRoleId())) {
        return rm;
      }
    }
    opAuthUserRole.setCreateBy(SessionUtil.getCurrentUserId());
    opAuthUserRole.setCreateTime(DateTimeUtil.now());
    opAuthUserRole.setRoleName((SysRole.get(opAuthUserRole.getRoleId()).getDesc()));
    opAuthUserRoleBusiness.create(opAuthUserRole);

    return rm;
  }

  @RequestMapping(value = "auth/user/delUserRole", method = RequestMethod.GET)
  @ResponseBody
  public Map delUserRole(String id, HttpServletRequest request) {
    Map<String, String> rm = new HashMap<String, String>();
    opAuthUserRoleBusiness.deleteHard(id);
    return rm;
  }

  @RequestMapping(value = "auth/user/listUserRoleByPage", method = RequestMethod.GET)
  public String listUserRoleByPage() {
    return "auth/listUserRole";
  }

  @RequestMapping(value = "auth/user/listUserRoleByPage", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<Map> listUserRoleByPage(OpAuthUser opAuthUser, int page, int limit) {
    if (StringUtils.isAnyBlank(opAuthUser.getUserName())) {
      opAuthUser.setUserName(null);
    }
    if (StringUtils.isAnyBlank(opAuthUser.getNickName())) {
      opAuthUser.setNickName(null);
    }
    Pagination<OpAuthUser> rs =
        opAuthUserBusiness.listByPage(
            Example.newExample(TOpAuthUser.class).paramLikeTo("userName", opAuthUser.getUserName())
                .paramLikeTo("nickName", opAuthUser.getNickName()).orderBy("create_time").desc(),
            page, limit);
    Pagination<Map> result = Pagination.newInstance(page, limit);
    result.setTotal(rs.getTotal());
    List<OpAuthUser> list = rs.getResult();
    List resultList = new LinkedList();
    for (OpAuthUser oau : list) {
      List<String> roles = opAuthUserRoleBusiness.listAllRoleByUser(oau.getId());
      Set<String> functionSet = new HashSet<String>();
      Set<String> roleSet = new HashSet<String>();
      for (String role : roles) {
        Set<String> fs = SysRoleFunction.getFunctionConfigs().get(role);
        if (null != fs) {
          functionSet.addAll(fs);
        }
        roleSet.add(SysRole.valueOf(role).getDesc());
      }
      Map map = BeanUtils.map(oau, HashMap.class);
      map.put("roleSet", roleSet.toString().substring(1, roleSet.toString().length() - 1));
      map.put("functionSet",
          functionSet.toString().substring(1, functionSet.toString().length() - 1));
      resultList.add(map);
    }
    result.setResult(resultList);
    return result;
  }

}
