package com.lumei.crm.commons.bean;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.lumei.crm.commons.util.ApplicationContextUtil;

public class SystemProperties {
  public static Properties props = new Properties();

  static {
    try {
      SystemPropertiesLocation local =
          ApplicationContextUtil.getBean(SystemPropertiesLocation.class);
      ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
      Resource[] resources = resolver.getResources(local.getLocation());
      for (int i = 0; i < resources.length; i++) {
        EncodedResource encRes = new EncodedResource(resources[i], "UTF-8");
        PropertiesLoaderUtils.fillProperties(props, encRes);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Properties getProps() {
    return props;
  }

  public static void setProps(Properties props) {
    SystemProperties.props = props;
  }

  public static String get(String key) {
    return props.getProperty(key, "");
  }
}
