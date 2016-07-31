package com.lumei.crm.support.security.remote.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.lumei.crm.commons.util.ApplicationContextUtil;
import com.lumei.crm.support.security.service.FilterChainsService;

/**
 * 
 * @author dave
 *
 */
public class FilterChainsServiceRemoteImpl extends UnicastRemoteObject implements
    FilterChainsServiceRemote {

  private static final long serialVersionUID = 1L;

  protected FilterChainsServiceRemoteImpl() throws RemoteException {
    super();
  }

  @Override
  public void reCreateFilterChains() throws RemoteException {
    FilterChainsService flterChainsService = ApplicationContextUtil.getBean("filterChainsService");
    flterChainsService.reCreateFilterChains();
  }

}
