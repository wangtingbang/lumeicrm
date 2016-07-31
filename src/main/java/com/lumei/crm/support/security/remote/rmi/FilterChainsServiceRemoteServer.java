package com.lumei.crm.support.security.remote.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 
 * @author dave
 *
 */
public class FilterChainsServiceRemoteServer implements ApplicationListener<ContextRefreshedEvent> {
  private String host;
  private String registryPort;
  private String serviceName;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (event.getApplicationContext().getParent() == null) {
      try {
        FilterChainsServiceRemote filterChainsServiceRemote = new FilterChainsServiceRemoteImpl();
        LocateRegistry.createRegistry(Integer.valueOf(registryPort));
        String url = "rmi://" + host + ":" + registryPort + "/" + serviceName;
        Naming.rebind(url, filterChainsServiceRemote);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getRegistryPort() {
    return registryPort;
  }

  public void setRegistryPort(String registryPort) {
    this.registryPort = registryPort;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

}
