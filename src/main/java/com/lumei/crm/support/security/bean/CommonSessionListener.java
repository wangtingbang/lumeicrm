package com.lumei.crm.support.security.bean;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author dave
 *
 */
public class CommonSessionListener implements SessionListener {
  private static final Logger logger = LoggerFactory.getLogger(CommonSessionListener.class);

  @Override
  public void onStart(Session session) {}

  @Override
  public void onStop(Session session) {}

  @Override
  public void onExpiration(Session session) {}

}
