<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="page-header">
	<div class="row">
		<div class="col-xs-12">
			<h1>
			<small>
				<i class="ace-icon fa fa-home home-icon"></i>
				<a href="<%=request.getContextPath() %>/">主页</a>
				<i class="ace-icon fa fa-angle-double-right"></i>
				<a href="#">系统管理</a>
				<i class="ace-icon fa fa-angle-double-right"></i>
			操作日志
			</small>
			</h1>
		</div>
	</div>
</div>
<div class="row">
<div class="col-xs-12">
		<form id="search_form" class="" action="#">
		<div class="col-sm-2 form-group">
			<select id="search_option" class="form-control">
				<option value="userName">用户名</option>
			</select>
		</div>
		<div id="search_input" class="col-sm-4 form-group">
		</div>
		<div class="col-sm-4 form-group">
		    <div class="input-group">
		        <span class="input-group-addon">
		    		<i class="fa fa-calendar bigger-110"></i>
				</span>
			<input id="id-date-range-picker-1" class="form-control" type="text" name="daterange" placeholder="选择日期"></input>
		    </div>
		</div>
		<div class="col-sm-2 form-group">
			<a class="btn btn-info btn-sm" id="search_btn">
				 <i class="ace-icon fa fa-search  bigger-110"></i> 查询
			</a>
		</div>
		</form>
	</div>
</div>
<div class="row">
<div class="col-xs-12">
		<hr class="no-margin-top"></hr>
		<div class="row">
				<div class="col-xs-12" id="page"></div>
		</div>
</div>
</div>
	<script id="search_userName_temp" type="text/x-dot-template">
		<input type="text"  name="userName" class="form-control" placeholder="输入检索内容"/>
	</script>

<script id="grid_temp" type="text/x-dot-template">
<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>操作时间</th>
			<th>用户名</th>
			<th class="hidden-480">ip地址</th>
			<th class="hidden-480">操作类型</th>
			<th>操作内容</th>
		</tr>
	</thead>
	<tbody>
	{{~it.data :p:index}}
		<tr>
			<td>
				{{=new Date(p.createTime).toChString(true) }}
			</td>
			<td>
				{{=p.createByName }}
			</td>
			<td class="hidden-480">
				{{=p.ip }}
			</td>
			<td class="hidden-480">
				{{=p.logType }}
			</td>
			<td>
				{{=p.content }}
			</td>
		</tr>
{{~}}
{{? !it.data.length}}
<tr ><td colspan="12">没有相关记录</td></tr>
{{?}}
	</tbody>
</table>
</script>

<script type="text/javascript">
$(function () {
$('#search_btn').click(function() {
	searchSubmit();
});

$('#search_option').change(function() {
	var v = $(this).val();
	if (v) {
		 var $dom = setSearch(v);
	}
});

$('#id-date-range-picker-1').daterangepicker({});

setSearch($('#search_option').val());
$('#search_btn').click();

});

function setSearch(v) {
	var pagefn = doT.template($('#search_' + v
			+ '_temp').text());
	var ipt = $("#search_input").html('');
	var $dom = $(pagefn()).appendTo(ipt);
	return $dom;
}

function searchSubmit() {
	var param = {};
	$('#search_form').find(":input").each(function(k, v) {
		var name = $(v).attr('name');
		var val = $(v).val();
		if (name) {
			param[name] = $(v).val();
		}
	});
	 $page = $('#page').igrid({
			url : contextPath + '/auth/sysoplog/list',
			param : param,
			temp : "grid_temp"
		});
}
</script>
