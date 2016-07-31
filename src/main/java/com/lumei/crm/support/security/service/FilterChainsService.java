package com.lumei.crm.support.security.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lumei.crm.support.security.bean.RoleUrl;

/**
 * 
 * @author dave
 *
 */
@Service(value = "filterChainsService")
public class FilterChainsService {
  private static final Logger log = LoggerFactory.getLogger(FilterChainsService.class);

  private static final String CRLF = "\r\n";
  private static final String LAST_AUTH_STR = "/** = authc\r\n";
//  private static final String LAST_AUTH_STR = "/** = fobiddenFilter\r\n";

  @Autowired
  private ShiroFilterFactoryBean shiroFilterFactoryBean;
  @Autowired
  private ShiroAuthorizationService shiroAuthorizationService;

  private String fixedAuthorityLocation;

  public String loadFilterChainDefinitions() {
    StringBuffer sb = new StringBuffer("");
    sb.append(getFixedAuthRule()).append(getDynaAuthRule()).append(LAST_AUTH_STR);
    return sb.toString();
  }

  private String getDynaAuthRule() {
    StringBuffer sb = new StringBuffer("");
    Map<String, Set<String>> rules = new HashMap<String, Set<String>>();
    List<RoleUrl> list = shiroAuthorizationService.getAllUrlAndRole();
    for (RoleUrl roleUrl : list) {
      String url = roleUrl.getUrl();
      if (null == url || "".equals(url)) {
        continue;
      }
      if (!url.startsWith("/")) {
        url = "/" + url;
      }
      String role = roleUrl.getRole();
      if (!rules.containsKey(url)) {
        rules.put(url, new HashSet<String>());
      }
      rules.get(url).add((role));
    }
    for (Map.Entry<String, Set<String>> entry : rules.entrySet()) {
      sb.append(entry.getKey()).append(" = ").append("authc,roleOrFilter").append(entry.getValue())
          .append(CRLF);
    }
    return sb.toString();
  }

  private String getFixedAuthRule() {
    StringBuffer sb = new StringBuffer("");
    Properties properties = new Properties();
    try {
      InputStream is =
          new FileInputStream(new File(FilterChainsService.class.getClassLoader()
              .getResource(fixedAuthorityLocation).getPath()));
      properties.load(is);
    } catch (IOException e) {
      log.error("load fixed authority error!", e);
      throw new RuntimeException("load fixed authority error!");
    }
    for (Iterator its = properties.keySet().iterator(); its.hasNext();) {
      String key = (String) its.next();
      sb.append(key).append(" = ").append(properties.getProperty(key).trim()).append(CRLF);
    }
    return sb.toString();
  }

  public synchronized void reCreateFilterChains() {
    AbstractShiroFilter shiroFilter = null;
    try {
      shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
    } catch (Exception e) {
      log.error("reCreateFilterChains error!", e);
      throw new RuntimeException("reCreateFilterChains error!");
    }
    PathMatchingFilterChainResolver filterChainResolver =
        (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
    DefaultFilterChainManager manager =
        (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

    manager.getFilterChains().clear();
    shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
    shiroFilterFactoryBean.setFilterChainDefinitions(loadFilterChainDefinitions());

    Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
    for (Map.Entry<String, String> entry : chains.entrySet()) {
      String url = entry.getKey();
      String chainDefinition = entry.getValue().trim().replace(" ", "");
      manager.createChain(url, chainDefinition);
    }
  }

  public String getFixedAuthorityLocation() {
    return fixedAuthorityLocation;
  }

  public void setFixedAuthorityLocation(String fixedAuthorityLocation) {
    this.fixedAuthorityLocation = fixedAuthorityLocation;
  }

}
