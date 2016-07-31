package com.lumei.crm.commons.bean.support;

import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;

/**
 * 
 * @author dave
 *
 */
public class ScaledLongFormatter implements Formatter<Long> {
  private int scale;
  private int round;

  public ScaledLongFormatter(int scale) {
    this(2, scale);
  }

  public ScaledLongFormatter(int round, int scale) {
    if (scale < 1 || scale > 17)
      throw new IllegalArgumentException("scale must between 1 and 17");
    if (round < 0)
      throw new IllegalArgumentException("round must great than or equals 0");
    this.scale = scale;
    this.round = round;
  }

  @Override
  public String print(Long arg0, Locale arg1) {
    if (arg0 == null)
      return "";
    Integer i = 1 << this.scale;
    Long l = Long.valueOf(Integer.toBinaryString(i));
    Long arg2 = Long.valueOf(arg0);
    boolean positive = true;
    if (arg2 < 0) {
      arg2 *= -1L;
      positive = false;
    }
    String result = "";
    if (arg2 < l) {
      result = (positive ? "" : "-") + "0." + String.valueOf(l + arg2).substring(1);
    } else {
      char[] c = String.valueOf(arg2).toCharArray();
      char[] s = ArrayUtils.add(c, c.length - scale, '.');
      result = (positive ? "" : "-") + String.valueOf(s);
    }
    return round == 0 ? result.substring(0, result.indexOf('.')) : result.substring(0,
        result.indexOf('.') + round + 1);
  }

  @Override
  public Long parse(String arg0, Locale arg1) throws ParseException {
    if (StringUtils.isBlank(arg0))
      return null;
    try {
      String[] s = arg0.split("\\.");
      if (s.length > 2)
        throw new Exception();
      String s1 = s[0];
      String s2 =
          s.length == 2 ? s[1]
              .substring(0, s[1].length() > this.scale ? this.scale : s[1].length()) : "0";
      String s0 = s1 + s2;
      int offset = s2.length();
      Integer i = 1 << this.scale - offset;
      Long l = Long.valueOf(Integer.toBinaryString(i));
      if (s0.replaceFirst("-", "").length() + this.scale - offset > 18)
        throw new Exception();
      Long r = Long.valueOf(s0);
      r *= l;
      return r;
    } catch (Exception e) {
      throw new ParseException(arg0 + " scale:" + this.scale, 0);
    }
  }

}
