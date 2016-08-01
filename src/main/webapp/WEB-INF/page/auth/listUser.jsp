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
				<a href="<%=request.getContextPath() %>/">Home</a>
				<i class="ace-icon fa fa-angle-double-right"></i>
				User Config
			</small>
			</h1>
		</div>
	</div>
</div>
<div class="row">
<div class="col-xs-12">
		<form id="search_form" class="" action="#">
		<div class="col-sm-2 form-group">
			<a class="btn btn-sm btn-success" id="create_btn">
				 <i class="ace-icon fa fa-plus bigger-110"></i> Add
			</a>
		</div>
		<div class="col-sm-2 form-group">
			<select id="search_option" class="form-control">
				<option value="userName">Username</option>
				<option value="nickName">Nickname</option>
			</select>
		</div>
		<div class="col-sm-4 form-group" id="search_input">
		</div>
		<div class="col-sm-2 form-group">
			<a class="btn btn-info btn-sm" id="search_btn">
				 <i class="ace-icon fa fa-search  bigger-110"></i> Find
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
		<input type="text"  name="userName" class="form-control" placeholder=""/>
	</script>

	<script id="search_nickName_temp" type="text/x-dot-template">
		<input type="text"  name="nickName" class="form-control" placeholder=""/>
	</script>

<script id="grid_temp" type="text/x-dot-template">
<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>Username</th>
			<th>Nickname</th>
			<th>Status</th>
			<th>Opts</th>
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
						<a class="red" href="javascript:lockSubmit('{{=p.id }}');" data-rel="tooltip"  {{? p.enabled==1}} title="lock" data-original-title="lock">{{??}} title="unlock" data-original-title="unlock">{{?}}
							{{? p.enabled==1}}
							<i class="ace-icon fa fa-unlock bigger-130"></i>   unlock
							{{??}}
							<i class="ace-icon fa fa-lock bigger-130"></i>   lock
							{{?}}
						</a>
			</div>
			</td>
			<td>
			<div class="hidden-sm hidden-xs action-buttons">
				<a class="green col-xs-2" href="javascript:updateuser('{{=p.id }}','{{=p.userName }}','{{=p.nickName }}');" data-rel="tooltip" title="edit" data-original-title="edit">
					<i class="ace-icon fa fa-pencil bigger-130"></i>   edit
				</a>
				<a class="orange col-xs-2" href="javascript:resetuserpwd('{{=p.id }}','{{=p.nickName }}');" data-rel="tooltip" title="reset password" data-original-title="reset password">
					<i class="ace-icon fa fa-undo bigger-130"></i>   reset password
				</a>
				<a class="purple col-xs-2" href="javascript:userRole('{{=p.id }}','{{=p.nickName }}');" data-rel="tooltip" title="roles" data-original-title="roles">
					<i class="ace-icon fa fa-group bigger-130"></i>   roles
				</a>
			</div>
			<div class="hidden-md hidden-lg">
				<div class="inline position-relative">
					<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">
						<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i>
					</button>
					<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
						<li>
							<a class="green" href="javascript:updateuser('{{=p.id }}','{{=p.userName }}','{{=p.nickName }}');" data-rel="tooltip" title="edit" data-original-title="edit">
								<i class="ace-icon fa fa-pencil bigger-130"></i>   edit
							</a>
						</li>
						<li>
							<a class="orange" href="javascript:resetuserpwd('{{=p.id }}','{{=p.nickName }}');" data-rel="tooltip" title="reset password" data-original-title="reset password">
								<i class="ace-icon fa fa-undo bigger-130"></i>   reset password
							</a>
						</li>
						<li>
							<a class="purple" href="javascript:userRole('{{=p.id }}','{{=p.nickName }}');" data-rel="tooltip" title="roles" data-original-title="roles">
								<i class="ace-icon fa fa-group bigger-130"></i>   roles
							</a>
						</li>
					</ul>
				</div>
			</div>
			</td>
		</tr>
{{~}}
{{? !it.data.length}}
<tr ><td colspan="12">not found</td></tr>
{{?}}
	</tbody>
</table>
</script>

<script id="create_temp" type="text/x-dot-template">
<form class="form-horizontal" id="createuserform">
<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="id">Username</label>
	<div class="col-sm-8">
		<input type="text" id="userName" name="userName" class="col-xs-12 col-sm-12" value=""/>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="id">Nickname</label>
	<div class="col-sm-8">
		<input type="text" id="nickName" name="nickName" class="col-xs-12 col-sm-12" value=""/>
	</div>
</div>
</form>
</script>

<script id="update_user_temp" type="text/x-dot-template">
<form class="form-horizontal" id="updateuserform">
<input type="hidden" id="id" name="id" value="{{=it.id}}"/>
<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="id">Username</label>
	<div class="col-sm-8">
		<input type="text" id="userName" name="userName" class="col-xs-12 col-sm-12" value="{{=it.userName}}" readonly="readonly"/>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="id">Nickname</label>
	<div class="col-sm-8">
		<input type="text" id="nickName" name="nickName" class="col-xs-12 col-sm-12" value="{{=it.nickName}}"/>
	</div>
</div>
</form>
</script>

<script id="user_role_temp" type="text/x-dot-template">
<div class="row">
<div class="col-xs-12">
	<div class="btn-toolbar">
		<form class="form-horizontal" id="userRoleForm">
			<input type="hidden" name="userId" value="{{=it.id}}"/>
			<div class="col-sm-8">
				<div class="form-group">
					<div class="col-sm-12">
							<select class="chosen-select col-sm-12" id="role_select" name="roleId">
							</select>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div id="usrRoleBtn" class="btn btn-info btn-sm">
				<i class="ace-icon fa fa-plus  bigger-110"></i>
				Add
				</div>
			</div>
		</form>
		<div class="col-xs-12">
		<hr/>
		</div>
		<div id="usr_role_org" class="col-xs-12"></div>
	</div>
</div>
</div>
</script>

<script src="<%=request.getContextPath()%>/resources/custjs/auth/user.js"></script>
