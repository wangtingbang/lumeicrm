package com.lumei.crm.commons.util;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author dave
 * 
 */
public class KeyGenerator {
  public synchronized static String uuid() {
    String s = UUID.randomUUID().toString();
    s =
        s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23)
            + s.substring(24);
    return s;
  }

  public synchronized static String randomWords(int length, char[]... charSet) {
    if (length <= 0)
      return "";
    StringBuffer word = new StringBuffer(length);
    for (int i = 0; i < length; i++) {
      int j = new SecureRandom().nextInt(charSet.length);
      int k = new SecureRandom().nextInt(charSet[j].length);
      word.append(charSet[j][k]);
    }
    return word.toString();
  }

  public final static class CharSet {
    public final static char[] NUMBER = "1234567890".toCharArray();
    public final static char[] ALPHA_LOWER_CASE = "asdfghjklzxcvbnmqwertyuiop".toCharArray();
    public final static char[] ALPHA_UPPER_CASE = "ASDFGHJKLZXCVBNMQWERTYUIOP".toCharArray();
    public final static char[] PUNCTUATION = "$#@*&%?^~+-".toCharArray();
  }


  public static class RegularPrefixIDGenerator {
    private static final Logger logger = LoggerFactory.getLogger(RegularPrefixIDGenerator.class);

    private static volatile long lastTimestamp = System.currentTimeMillis();

    private static volatile int seq = 0;

    private static final int maxShort = 999;

    private static final Object lock = new Object();

    private static byte[] keygen() {
      if (seq == maxShort) {
        throw new RuntimeException("Too fast");
      }

      long time;
      synchronized (lock) {
        time = System.currentTimeMillis();
        if (time > lastTimestamp) {
          lastTimestamp = time;
          seq = 0;
        } else {
          time = lastTimestamp;
        }
        ByteBuffer bb = ByteBuffer.allocate(12);
        bb.rewind();
        bb.putLong(time);
        bb.putInt(seq++);
        return bb.array();
      }
    }

    public static String Id(String prefix) {
      StringBuilder sb = new StringBuilder(prefix);
      byte[] ba = keygen();
      ByteBuffer bb = ByteBuffer.wrap(ba);
      long ts = bb.getLong();
      int seq = bb.getInt();
      // StringBuilder sb = new StringBuilder(String.valueOf(ts));
      sb.append(String.valueOf(ts));
      sb.append(StringUtils.leftPad(String.valueOf(seq), 3, '0'));
      String id = sb.toString();
      logger.info("生成id{}", id);
      return id;
    }
  }

}
