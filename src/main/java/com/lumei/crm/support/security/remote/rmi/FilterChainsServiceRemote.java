package com.lumei.crm.support.security.remote.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author dave
 *
 */
public interface FilterChainsServiceRemote extends Remote {
  public void reCreateFilterChains() throws RemoteException;
}
