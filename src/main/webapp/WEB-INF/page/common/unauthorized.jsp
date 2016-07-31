<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
boolean ajaxFlag = false;
String xRequestedWith = request.getHeader("X-Requested-With");
if (!StringUtils.isEmpty(xRequestedWith)) {
  ajaxFlag = true;
}
request.setAttribute("ajaxFlag", ajaxFlag);
%>
<c:choose>
<c:when test="${ajaxFlag }">
403
</c:when>
<c:otherwise>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>403 Error Page</title>

		<meta name="description" content="403 Error Page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/font-awesome.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/open.font.css" />

		<!-- page specific plugin styles -->

		<!-- ace styles -->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace.min.css" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace-skins.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="<%=request.getContextPath() %>/resources/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="<%=request.getContextPath() %>/resources/js/html5shiv.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="no-skin">
		<div class="main-container" id="main-container">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->

							<div class="error-container">
								<div class="well">
									<h1 class="grey lighter smaller">
										<span class="blue bigger-125">
											<i class="ace-icon fa fa-lock"></i>
											403
										</span>
										Forbidden!
									</h1>

									<hr />
									<h3 class="lighter smaller">
										<i class="ace-icon fa fa-key icon-animated-wrench bigger-125"></i>
										The Action will be recorded！
									</h3>

									<div class="space"></div>

									<hr />
									<div class="space"></div>
								</div>
							</div>

							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
		</div><!-- /.main-container -->

	<script type="text/javascript">
		if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath() %>/resources/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
	</script>

	<!-- page specific plugin scripts -->

	<!-- ace scripts -->
	<script src="<%=request.getContextPath() %>/resources/js/ace-elements.min.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
	function fgoto(){
		location.href="<%=request.getContextPath() %>/";
	}
	setTimeout("fgoto()", 3000 )
	</script>
	</body>
</html>

</c:otherwise>
</c:choose>
