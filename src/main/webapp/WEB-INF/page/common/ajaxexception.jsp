<%@page import="com.lumei.crm.support.mvc.PosApiCode"%>
<%@page import="com.lumei.crm.commons.util.JsonUtil"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.lumei.crm.commons.bean.BusinessException"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Exception ex = (Exception) request.getAttribute("ex");
	BusinessException e = null;
	if(ex instanceof BusinessException){
	  e = (BusinessException) ex;
	}else{
	  e = new BusinessException(PosApiCode.UNKNOWN_ERROR.getCode());
	}
	Map m = new HashMap();
	m.put("retCode", e.getCode());
	if(StringUtils.isNotBlank(e.getMessage())){
	  m.put("retMsg", e.getMessage());
	}
	String json = JsonUtil.toJson(m);
%>
<%=json%>
