package com.lumei.crm.support.security.bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 
 * @author dave
 *
 */
public class FobiddenFilter extends AuthorizationFilter {

  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
      Object mappedValue) throws Exception {
    return false;
  }

}
