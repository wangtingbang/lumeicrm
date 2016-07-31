<%@page import="com.lumei.crm.auth.bean.SysMenu"%>
<%@page import="com.lumei.crm.util.SessionUtil"%>
<%@page import="com.lumei.crm.util.Tree"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
		List<Tree<SysMenu>> list = SessionUtil.currentUserMenuTree();
		String active= request.getParameter("active");
		request.setAttribute("list", list);
		request.setAttribute("active", active);
%>
			<div id="sidebar" class="sidebar responsive sidebar-fixed sidebar-scroll">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>
				
				<ul class="nav nav-list">
					<c:forEach var="p" items="${list}">
						<c:choose>
							<c:when test="${p.param.id == active}">
								<li class="active">
							</c:when>
							<c:otherwise>
								<li
									class="<c:forEach var="p1" items="${p.additionalParameters.children}"><c:if test="${p1.param.id == active}">active open hsub</c:if></c:forEach>">
							</c:otherwise>
						</c:choose>
						<c:if test="${p.type == 'item'}">
							<a
								href="<%=request.getContextPath() %>/${p.param.url }?active=${p.param.id }">
						</c:if>
						<c:if test="${p.type == 'folder'}">
							<a href="#" class="dropdown-toggle">
						</c:if>
						<i class="menu-icon fa ${p.param.icon }"></i>
						<span class="menu-text"> ${p.name } </span>
						<c:if test="${p.type == 'folder'}">
							<b class="arrow fa fa-angle-down"></b>
						</c:if>
						</a>
						<b class="arrow"></b>
						<c:if test="${p.type == 'folder'}">
							<ul class="submenu">
								<c:forEach var="p1" items="${p.additionalParameters.children}">
									<li class="<c:if test="${p1.param.id == active}">active</c:if>">
										<a
										href="<%=request.getContextPath() %>/${p1.param.url }?active=${p1.param.id }">
											<i class="menu-icon fa fa-caret-right"></i> ${p1.name }
									</a> <b class="arrow"></b>
									</li>
								</c:forEach>
							</ul>
						</c:if>
						</li>
					</c:forEach>
				</ul><!-- /.nav-list -->

				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>

				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>
