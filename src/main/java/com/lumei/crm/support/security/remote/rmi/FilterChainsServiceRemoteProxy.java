package com.lumei.crm.support.security.remote.rmi;

import java.rmi.Naming;

/**
 * 
 * @author dave
 *
 */
public class FilterChainsServiceRemoteProxy {
  private String serviceUrl;

  public void reCreateFilterChains() {
    String[] urls = serviceUrl.split("\\|");
    for (int i = 0; i < urls.length; i++) {
      try {
        FilterChainsServiceRemote filterChainsServiceRemote =
            (FilterChainsServiceRemote) Naming.lookup(urls[i]);
        filterChainsServiceRemote.reCreateFilterChains();
      } catch (Exception e) {
      }
    }
  }

  public String getServiceUrl() {
    return serviceUrl;
  }

  public void setServiceUrl(String serviceUrl) {
    this.serviceUrl = serviceUrl;
  }

}
