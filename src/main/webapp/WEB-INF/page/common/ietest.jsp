<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
											<i class="ace-icon fa fa-random"></i>
										</span>
										update you web browser...
									</h1>

									<hr />
									<h2 class="lighter smaller">
										<i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>
										We recommaned IE10 or later, or FireFox, or Google Chrome
									</h2>
<div>
<a href="http://www.firefox.com.cn">
    <img height="70" width="185" data-inverse-src="http://www.firefox.com.cn/media/img/firefox/template/header-logo-inverse.png?2013-06" alt="Mozilla Firefox" src="http://www.firefox.com.cn//media/img/firefox/template/header-logo.png?2013-06"></img>
</a>
<br/>
<a href="http://www.google.cn/intl/zh-CN/chrome/">
    <img id="logo" src="http://www.google.cn/intl/zh-CN/chrome/assets/common/images/chrome_logo_2x.png" data-g-label="consumer-home" data-g-event="nav-logo" alt="Chrome"></img>
</a>
<br/>
<a href="http://www.apple.com/cn/safari/">
	<img class="center"  alt="" src="http://images.apple.com/v/safari/c/images/overview/safari_icon_large.png"></img>
</a>
<br/>
<a id="brand" class="hf--brand" href="http://www.opera.com/zh-cn">
<img src="http://www-static.opera.com/static-heap/dc/dc1c127749b90b6ee27818dcfa7ed1016599aa82/opera-software-logo.png" srcset="http://www-static.opera.com/static-heap/dc/dc1c127749b90b6ee27818dcfa7ed1016599aa82/opera-software-logo.png 1x, http://www-static.opera.com/static-heap/0f/0f386473c9173f038fb026ef5f809c63fe7affcf/opera-software-logo@2x.png 2x" alt="Opera Software 徽标" height="50" width="122">
</a>
</div>
									<h3 class="lighter smaller">
										<i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>
										(click to download)
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
	</body>
</html>
