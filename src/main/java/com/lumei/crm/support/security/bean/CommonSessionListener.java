package com.lumei.crm.support.security.bean;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumei.crm.auth.bean.LoginUser;
import com.lumei.crm.commons.util.DateTimeUtil;

/**
 * 
 * @author dave
 *
 */
public class CommonSessionListener implements SessionListener {
  private static final Logger logger = LoggerFactory.getLogger(CommonSessionListener.class);

  @Override
  public void onStart(Session session) {
    // System.out.println(session);
  }

  @Override
  public void onStop(Session session) {
    LoginUser loginUser = (LoginUser) session.getAttribute(LoginUser.CURRENT_USER);
  }

  @Override
  public void onExpiration(Session session) {
    LoginUser loginUser = (LoginUser) session.getAttribute(LoginUser.CURRENT_USER);
  }

}
