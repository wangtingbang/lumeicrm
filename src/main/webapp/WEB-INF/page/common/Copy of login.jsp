<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
boolean ajaxFlag = false;
String xRequestedWith = request.getHeader("X-Requested-With");
if (!StringUtils.isEmpty(xRequestedWith)) {
  ajaxFlag = true;
  request.setAttribute("ajaxFlag", ajaxFlag);
}else{
  Subject currentUser = SecurityUtils.getSubject();
  if(currentUser.isAuthenticated()){
    response.sendRedirect(request.getContextPath()+"/");
  }
}
%>
<c:choose>
<c:when test="${ajaxFlag }">
401.1
</c:when>
<c:otherwise>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/font-awesome.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/open.font.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace.min.css" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace-ie.min.css" />
		<![endif]-->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
			<script type="text/javascript">
			 location.href="<%=request.getContextPath() %>/ietest";
			</script>
		<![endif]-->

		<!--[if lt IE 9]>
		<script src="<%=request.getContextPath() %>/resources/js/html5shiv.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="login-layout light-login">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container" style="margin-right: 10px;margin-top: 40px;">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="red">User</span>
									<span class="white" id="id-text2">Login</span>
								</h1>
							</div>
							<div class="space-6"></div>
							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												&copy;Lumei CRM
											</h4>

											<div class="space-6"></div>
<!-- ################### -->
											<form action="<%=request.getContextPath() %>/authlogin" onsubmit="return check();" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="uname" type="text" class="form-control" name="userName" value="" placeholder="Login Name" onfocus="focusuname();"/>
															<span id="checkuname"></span>
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="upwd" type="password" class="form-control" name="password" value="" placeholder="Password" onfocus="focusupwd();"/>
															<span id="checkupwd"></span>
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<c:if test="${! empty errorMsg }">
														<span id="checkuser">
											    			<font color="red">${errorMsg }</font>
														</span>
													</c:if>
													<div class="space"></div>

													<div class="clearfix">
														<button type="submit" class="width-35 pull-right btn btn-sm btn-primary" >
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110">Login</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

											<div class="social-or-login center">
												<span class="bigger-110"></span>
											</div>

											<div class="space-6"></div>

										</div><!-- /.widget-main -->

										<div class="toolbar clearfix" style="height: 20px;">

										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->
							<!-- <div class="navbar-fixed-top align-right">
								<br />
								&nbsp;
								<a id="btn-login-dark" href="#">Dark</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-blur" href="#">Blur</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-light" href="#">Light</a>
								&nbsp; &nbsp; &nbsp;
							</div> -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="<%=request.getContextPath() %>/resources/js/jquery.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="<%=request.getContextPath() %>/resources/js/jquery.min.js"></script>
<![endif]-->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=request.getContextPath() %>/resources/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='<%=request.getContextPath() %>/resources/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath() %>/resources/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			 $(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			});
			</script>
			<script type="text/javascript">
				function check(){
				var userName=$("#uname").val();
				var password=$("#upwd").val();
				/* alert(userName);
				alert(password);  */
				if(userName==""){
				$("#checkuname").html("<font color='red'>login name needed！</font>");
				return false;
				}
				if(password==""){
				$("#checkupwd").html("<font color='red'>password needed！</font>");
				return false;
				}
				return true;
				}
				</script>

				<script type="text/javascript">
				function focusuname(){
				$("#checkuname").html("");
				}
				</script>

				<script type="text/javascript">
				function focusupwd(){
				$("#checkupwd").html("");
				}
				</script>

<!-- 			//you don't need this, just used for changing background
			jQuery(function($) {
			 $('#btn-login-dark').on('click', function(e) {
				$('body').attr('class', 'login-layout');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'blue');

				e.preventDefault();
			 });
			 $('#btn-login-light').on('click', function(e) {
				$('body').attr('class', 'login-layout light-login');
				$('#id-text2').attr('class', 'grey');
				$('#id-company-text').attr('class', 'blue');

				e.preventDefault();
			 });
			 $('#btn-login-blur').on('click', function(e) {
				$('body').attr('class', 'login-layout blur-login');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'light-blue');

				e.preventDefault();
			 });

			});
		 -->
	</body>
</html>

</c:otherwise>
</c:choose>
