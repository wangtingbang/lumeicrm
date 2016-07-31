package com.lumei.crm.commons.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;

/**
 * 
 * @author dave
 *
 */
public class JsonUtil {
  public static String toJson(Object obj, boolean includeNull) {
    ObjectMapper mapper = new ObjectMapper();
    if (!includeNull) {
      mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    }
    Writer strWriter = new StringWriter();
    try {
      mapper.writeValue(strWriter, obj);
      return strWriter.toString();
    } catch (JsonGenerationException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }

  public static String toJson(Object obj) {
    return toJson(obj, false);
  }

  public static String toJsonIncludeNull(Object obj) {
    return toJson(obj, true);
  }

  public static Map fromJson(String json) {
    return fromJson(json, Map.class);
  }

  public static <T> T fromJson(String json, Class<T> clazz) {
    String str = replaceQuoteMark(json);
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(str, clazz);
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static List<Map> Json2List(String json) {
    return fromJson(json, List.class);
  }

  public static <T> List<T> Json2List(String json, Class<T> clazz) {
    String str = replaceQuoteMark(json);
    ObjectMapper mapper = new ObjectMapper();
    JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
    try {
      return mapper.readValue(str, javaType);
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static String replaceQuoteMark(String str) {
    return str.replaceAll("'", "\"");
  }

}
