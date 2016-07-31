package com.lumei.crm.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lumei.crm.auth.biz.OpAuthUserBusiness;
import com.lumei.crm.auth.biz.OpAuthUserRoleBusiness;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.commons.bean.BusinessException;
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
    if( !(StringUtils.isNotBlank(newPwd1) && StringUtils.isNotBlank(newPwd2) 
        && StringUtils.trimToEmpty(newPwd1).length() > 6 && StringUtils.trimToEmpty(newPwd1).length() > 6 )){
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
          rm.put("msg", "更新成功!");
          
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

}
