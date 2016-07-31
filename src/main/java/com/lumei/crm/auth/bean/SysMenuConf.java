package com.lumei.crm.auth.bean;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author dave
 *
 */
public class SysMenuConf {
  private static Map<String, Set<SysMenu>> menuConfigs = new HashMap<String, Set<SysMenu>>();

  static {
    String path =
        SysMenuConf.class.getClassLoader().getResource("conf/lumei/xml/sys-menu.xml").getPath();
    File configFile = new File(path);
    SAXReader saxReader = new SAXReader();
    try {
      Document doc = saxReader.read(configFile);
      List<Element> menus = doc.selectNodes("/menu-conf/menu");
      for (Element element : menus) {
        String id = element.attributeValue("id");
        if (null == id || "".equals(id)) {
          continue;
        }
        SysMenu menu = new SysMenu();
        menu.setId(id);
        String name = element.attributeValue("name");
        menu.setName(name);
        String parentId = element.attributeValue("pid");
        menu.setParentId(parentId);
        String icon = element.attributeValue("icon");
        menu.setIcon(icon);
        String url = element.attributeValue("url");
        menu.setUrl(url);
        String order = element.attributeValue("order");
        menu.setOrder(Integer.valueOf(order));
        menu.setRoles(new LinkedList<String>());
        List<Element> roles = element.element("roles").elements("role");
        for (Element element2 : roles) {
          String role = element2.attributeValue("name");
          menu.getRoles().add(role);
          Set<SysMenu> menuSet = null;
          if (menuConfigs.containsKey(role)) {
            menuSet = menuConfigs.get(role);
          } else {
            menuSet = new HashSet<SysMenu>();
            menuConfigs.put(role, menuSet);
          }
          menuSet.add(menu);
        }
      }
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  public static Map<String, Set<SysMenu>> getMenuConfigs() {
    return menuConfigs;
  }

  public static void setMenuConfigs(Map<String, Set<SysMenu>> menuConfigs) {
    SysMenuConf.menuConfigs = menuConfigs;
  }

}
