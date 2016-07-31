package com.lumei.crm.util;

import com.lumei.crm.commons.bean.SystemProperties;

public class SystemConfig {
  public static String FILE_SERVER_ADDR;

  static {
    FILE_SERVER_ADDR = SystemProperties.get("file.server.addr");

    System.out.println("====================>>>>>>>"+(SystemProperties.getProps()));
  }

}
