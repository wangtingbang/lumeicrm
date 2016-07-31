package com.lumei.crm.support.mvc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.lumei.crm.commons.util.JsonUtil;

/**
 * 
 * @author dave
 *
 */
@ControllerAdvice(basePackages = "com.lumei.crm")
public class CommonResponseBodyAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public Object beforeBodyWrite(Object arg0, MethodParameter arg1, MediaType arg2,
      Class<? extends HttpMessageConverter<?>> arg3, ServerHttpRequest arg4, ServerHttpResponse arg5) {
    Map map = new HashMap();
    map.put("retCode", PosApiCode.SUCCESS.getCode());
    map.put("retData", arg0);
    if(String.class == arg1.getMethod().getReturnType()){
      return JsonUtil.toJson(map);
    }
    return map;
  }

  @Override
  public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
    return true;
  }

}
