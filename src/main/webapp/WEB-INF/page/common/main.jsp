<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title><decorator:title default="Lumei CRM" /></title>

		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap-datetimepicker.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap-datetimepicker.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap-editable.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap-timepicker.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrapValidator.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/chosen.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/colorbox.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/colorpicker.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/datepicker.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/daterangepicker.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/dropzone.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/fullcalendar.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/jquery-ui.custom.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/jquery-ui.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/jquery.gritter.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/prettify.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/select2.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/iosOverlay.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/sweetalert.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ifacebook.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/open.font.css" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace-skins.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->
		<style type="text/css">
		.table-responsive > .table {
			margin-bottom: 10px;
		}
		.table-responsive > .table-bordered {
			border: 1px solid #DDD;
		}
		</style>
		<!-- ace settings handler -->
		<script src="<%=request.getContextPath() %>/resources/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="<%=request.getContextPath() %>/resources/js/html5shiv.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/respond.min.js"></script>
		<![endif]-->

		<!-- basic scripts -->
		<!--[if !IE]> -->
		<script src="<%=request.getContextPath() %>/resources/js/jquery.min.js"></script>
		<!-- <![endif]-->
		<!--[if IE]>
		<script src="<%=request.getContextPath() %>/resources/js/jquery-1.11.1.min.js"></script>
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
		<script src="<%=request.getContextPath() %>/resources/js/bootstrap.min.js"></script>
		<!-- page specific plugin scripts -->
		<!--[if lte IE 8]>
		  <script src="<%=request.getContextPath() %>/resources/js/excanvas.min.js"></script>
		<![endif]-->

		<script src="<%=request.getContextPath() %>/resources/js/ajaxfileupload.js"></script>
		<%-- <script src="<%=request.getContextPath() %>/resources/js/jquery.ajaxfileupload.js"></script> --%>
		<script src="<%=request.getContextPath() %>/resources/js/bootbox.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/bootstrap-colorpicker.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/bootstrap-tag.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/bootstrap-wysiwyg.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/bootstrapValidator.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/chosen.jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/doT.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/dropzone.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery-ui.custom.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery-ui.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.autosize.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery.colorbox-min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.dataTables.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.dataTables.bootstrap.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.easypiechart.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.gritter.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.hotkeys.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.inputlimiter.1.3.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.knob.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.maskedinput.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.nestable.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.slimscroll.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.ui.touch-punch.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.validate.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/json2.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/prettify.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/select2.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/spin.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/additional-methods.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jquery.noty.packaged.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/iosOverlay.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/sweetalert.min.js"></script>

		<script src="<%=request.getContextPath() %>/resources/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/date-time/bootstrap-timepicker.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/date-time/moment.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/date-time/daterangepicker.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/date-time/bootstrap-datetimepicker.min.js"></script>

		<script src="<%=request.getContextPath() %>/resources/js/fuelux/fuelux.spinner.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/fuelux/fuelux.tree.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/fuelux/fuelux.wizard.min.js"></script>

		<script src="<%=request.getContextPath() %>/resources/js/fullcalendar.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/fullcalendar/lang/lang-all.js"></script>

		<script src="<%=request.getContextPath() %>/resources/js/jqGrid/i18n/grid.locale-en.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/jqGrid/jquery.jqGrid.min.js"></script>

		<script src="<%=request.getContextPath() %>/resources/js/language/zh_CN.js"></script>

		<script src="<%=request.getContextPath() %>/resources/js/markdown/bootstrap-markdown.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/markdown/markdown.min.js"></script>

		<script src="<%=request.getContextPath() %>/resources/js/x-editable/bootstrap-editable.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/x-editable/ace-editable.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			var  contextPath = '<%=request.getContextPath() %>';
			var fileServerPath = '<%=com.lumei.crm.util.SystemConfig.FILE_SERVER_ADDR%>';
			var _browser = _getbrowser();
			function _getbrowser(){
				if(/firefox/.test(navigator.userAgent.toLowerCase())){
					return 'firefox';
				}else if(/webkit/.test(navigator.userAgent.toLowerCase())){
					return 'webkit';
				}else if(/opera/.test(navigator.userAgent.toLowerCase())){
					return 'opera';
				}else if(/msie/.test(navigator.userAgent.toLowerCase())){
					return 'msie';
				}else{
					return 'unknown';
				}
			}
			Number.prototype.formatter = function(round, scale, intf){
				var a = 0;
				if(typeof (scale) =='number'){
					var i = 1;
					i = i << scale
					i = i.toString(2);
					i = parseInt(i,10);
					a = this/i;
				}else{
					a = this;
				}
				if(typeof (round) =='number'){
					if(parseInt(a)==a && !intf){
					  return a.toString();
					}
					return a.toFixed(round).toString();
				}else{
					return a.toString();
				}
			}
			Date.prototype.toChString = function(hhmmss,showWeek){
			    var year = this.getFullYear();
			    if(isNaN(year))return "";
			    var month = this.getMonth();
			    var date = this.getDate();
			    var day = this.getDay();
			    var hour = this.getHours();
			    var minu = this.getMinutes();
			    var sec = this.getSeconds();
			    month = month + 1;
			    if (month < 10) month = "0" + month;
			    if (date < 10) date = "0" + date;
			    if (hour < 10) hour = "0" + hour;
			    if (minu < 10) minu = "0" + minu;
			    if (sec < 10) sec = "0" + sec;

			    if(hhmmss){
			    time = year + "-" + month + "-" + date + "" + " " + hour + ":" + minu + ":" + sec
			    }else{
	    		time = year + "-" + month + "-" + date
			    }

			    if(showWeek){
				    var arr_week = new Array("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
				    var week;
				    week = arr_week[day];
			    	time +=" " + week;
			    }
				return time;
			}
		</script>
		<script type="text/javascript">
			var authType = "${authType}";
		</script>
<script id="pagination_bar_temp" type="text/x-dot-template">
		<div class="row">
				<div class="col-xs-12">
				<form class="form-horizontal">
					<div class="form-group">
					<div class="col-sm-1 form-controls">
					    <select id="search_option" class="">
					    {{~it.rowlist :value:index}}
					        <option value="{{=value}}" {{? it.limit == value }}selected="selected" {{?}}>{{=value}}</option>
					    {{~}}
					    </select>
					</div>
					<label class="col-sm-3 form-controls">
					     No.{{=it.page}} page of {{=it.totalPage}} pages, Total rows:{{=it.total}}
					</label>
					<div class="col-sm-8 control-label no-padding-top">
						<ul class="pagination no-margin-top no-margin-bottom">
					    <li {{? it.firstpage }}class="disabled"{{?}}>
						<a href="javascript:void(0);">
						    <i class="ace-icon fa fa-angle-double-left"></i>
							<input type="hidden" value="{{=it.page-1}}"/>
						</a>
					    </li>
					    {{~it.pageArray :value:index}}
					    <li {{? it.page == value }}class="active disabled"{{?}}>
					        <a href="javascript:void(0);">{{=value}}<input type="hidden" value="{{=value}}"/></a>
					    </li>
					    {{~}}
					    <li {{? it.totalPage==0 || it.totalPage == it.page }}class="disabled"{{?}}>
							<a href="javascript:void(0);">
							    <i class="ace-icon fa fa-angle-double-right"></i>
								<input type="hidden" value="{{=it.page+1}}"/>
							</a>
					    </li>
						</ul>
					</div>
					</div>
					</form>
				</div>
		</div>
</div>
</div>
</script>
<script id="pagination_bar_temp2" type="text/x-dot-template">
		<div class="row">
				<div class="col-xs-12">
				<form class="form-horizontal">
					<div class="form-group">
					<div class="col-sm-12 control-label no-padding-top">
						<ul class="pagination no-margin-top no-margin-bottom">
					    <li {{? it.firstpage }}class="disabled"{{?}}>
						<a href="javascript:void(0);">
						    <i class="ace-icon fa fa-angle-double-left"></i>
							<input type="hidden" value="{{=it.page-1}}"/>
						</a>
					    </li>
					    {{~it.pageArray :value:index}}
					    <li {{? it.page == value }}class="active disabled"{{?}}>
					        <a href="javascript:void(0);">{{=value}}<input type="hidden" value="{{=value}}"/></a>
					    </li>
					    {{~}}
					    <li {{? it.totalPage==0 || it.totalPage == it.page }}class="disabled"{{?}}>
							<a href="javascript:void(0);">
							    <i class="ace-icon fa fa-angle-double-right"></i>
								<input type="hidden" value="{{=it.page+1}}"/>
							</a>
					    </li>
						</ul>
					</div>
					</div>
					</form>
				</div>
		</div>
</div>
</div>
</script>
<script id="modaltemp" type="text/x-dot-template">
<div class="modal-dialog text-left" style="display: inline-block; width: auto;">
<div class="modal-content">
{{? it.headDisp}}
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h5 class="modal-title">{{=it.title}}</h5><span id="error" class="label label-danger"></span>
</div>
{{?}}
<div class="modal-body" id="modal-body">
<div id="modalAlertMsgDiv"></div>
</div>
{{? it.footDisp}}
<div class="modal-footer">
{{~it.buttons :p:index}}
<button type="button" class="{{=p.addClass}}" id="imodalBtn{{=index}}" {{=p.attr }}>{{=p.text}}</button>
{{~}}
</div>
{{?}}
</div>
</div>
</script>
<script id="resetpwdtemp" type="text/x-dot-template">
<form class="form-horizontal" id="pwdForm">
<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="id">Old Password</label>
	<div class="col-sm-8">
		<input type="password" id="oldPwd" name="oldPwd" class="col-xs-12 col-sm-12" value=""/>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="id">New Password</label>
	<div class="col-sm-8">
		<input type="password" id="newPwd1" name="newPwd1" class="col-xs-12 col-sm-12" value=""/>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="id">Confirm New Password</label>
	<div class="col-sm-8">
		<input type="password" id="newPwd2" name="newPwd2" class="col-xs-12 col-sm-12" value=""/>
	</div>
</div>
</form>
</script>

		<script src="<%=request.getContextPath() %>/resources/custjs/common/retcode.js"></script>
		<script src="<%=request.getContextPath() %>/resources/custjs/common/common.js"></script>
		<script src="<%=request.getContextPath() %>/resources/custjs/common/grid.js"></script>
		<script src="<%=request.getContextPath() %>/resources/custjs/common/datadic.js"></script>
		<decorator:head />
	</head>

	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<jsp:include page="left.jsp"></jsp:include>

			<div class="main-content">
				<%-- <jsp:include page="breadcrumbs.jsp"></jsp:include> --%>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div id="alertMsg"></div>
							<decorator:body />
							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->

			<jsp:include page="footer.jsp"></jsp:include>
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>

		</div><!-- /.main-container -->

		<!-- ace scripts -->
		<script src="<%=request.getContextPath() %>/resources/js/ace-elements.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/ace.min.js"></script>

		<div class="modal fade text-center" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static"/>
	</body>
</html>
