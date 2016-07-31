package com.lumei.crm.commons.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @author dave
 * 
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
  private static ApplicationContext applicationContext;

  @SuppressWarnings("static-access")
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @SuppressWarnings("unchecked")
  public static <T> T getBean(String name) {
    applicationContext.getBeanDefinitionNames();
    return (T) applicationContext.getBean(name);
  }

  public static <T> T getBean(Class<T> requiredType) {
    return (T) applicationContext.getBean(requiredType);
  }

}
