package com.lumei.crm.auth.bean;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SysRoleFunction {
  private static Map<String, Set<String>> functionConfigs = new HashMap<String, Set<String>>();
  static {
    String path =
        SysRoleFunction.class.getClassLoader().getResource("conf/lumei/xml/sys-menu.xml").getPath();
    File configFile = new File(path);
    SAXReader saxReader = new SAXReader();
    try {
      Document doc = saxReader.read(configFile);
      List<Element> menus = doc.selectNodes("/menu-conf/menu");
      for (Element element : menus) {
        String function = element.attributeValue("function");
        if (null == function || "".equals(function)) {
          continue;
        }
        List<Element> roles = element.element("roles").elements("role");
        for (Element element2 : roles) {
          String role = element2.attributeValue("name");
          Set<String> functionSet = null;
          if (functionConfigs.containsKey(role)) {
            functionSet = functionConfigs.get(role);
          } else {
            functionSet = new HashSet<String>();
            functionConfigs.put(role, functionSet);
          }
          functionSet.add(function);
        }
      }
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  public static Map<String, Set<String>> getFunctionConfigs() {
    return functionConfigs;
  }

  public static void setFunctionConfigs(Map<String, Set<String>> functionConfigs) {
    SysRoleFunction.functionConfigs = functionConfigs;
  }

}
