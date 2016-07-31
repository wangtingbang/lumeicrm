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
				用户管理
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
				<option value="nickName">姓名</option>
			</select>
		</div>
		<div class="col-sm-4 form-group" id="search_input">
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

	<script id="search_nickName_temp" type="text/x-dot-template">
		<input type="text"  name="nickName" class="form-control" placeholder="输入检索内容"/>
	</script>

<script id="grid_temp" type="text/x-dot-template">
<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>用户名</th>
			<th>姓名</th>
			<th>状态</th>
			<th>角色</th>			
			<th>权限</th>
		</tr>
	</thead>
	<tbody>
	{{~it.data :p:index}}
		<tr>
			<td>
				{{=p.userName }}
			</td>
			<td>
				{{=p.nickName }}
			</td>
			<td>
			<div class="action-buttons">
							{{? p.enabled==1}}
							<i class="ace-icon fa fa-unlock bigger-130 red"></i>   启用
							{{??}}
							<i class="ace-icon fa fa-lock bigger-130 red"></i>   禁用
							{{?}}
			</div>
			</td>
			<td>
				{{=p.roleSet }}
			</td>
			<td>
				{{=p.functionSet }}
			</td>
		</tr>
{{~}}
{{? !it.data.length}}
<tr ><td colspan="12">没有相关记录</td></tr>
{{?}}
	</tbody>
</table>
</script>

<script src="<%=request.getContextPath()%>/resources/custjs/auth/userrole.js"></script>
