<%@page import="com.lumei.crm.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- <script src="<%=request.getContextPath()%>/resources/custjs/customer/customer.js"></script> -->
<script src="<%=request.getContextPath()%>/resources/custjs/customer/customer.js"></script>

<div class="page-header">
	<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-10">
			<h1>
			<small>
				<i class="ace-icon fa fa-home home-icon"></i>
				<a href="<%=request.getContextPath() %>/">Home</a>
				<i class="ace-icon fa fa-angle-double-right"></i>
				<c:if test="${authType == 'op'}">Customers List</c:if><c:if test="${authType == 'ap'}">Customers View</c:if>
			</small>
			</h1>
		</div>
	</div>
	</div>
</div>
<div class="row">
<div class="col-xs-12">
<form id="search_form" class="form-horizontal" action="#">
		<%-- <div class="row"> --%>
			<div class="col-sm-4 form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">Name</label>
				<div class="col-sm-10">
						<input id="form-field-customer-name"  class="form-control" type="text" name="customerName"/>
				</div>
			</div>
			<div class="col-sm-4 form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-2">Phone</label>
				<div class="col-sm-10">
					<input id="form-field-customer-phone"  class="form-control" type="text" name="customerPhone"/>
				</div>
			</div>
			<div class="col-sm-4 form-group">
				<label class="col-sm-2 control-label no-padding-right" for="">Email</label>
				<div class="col-sm-10">
					<input id="form-field-customer-email"  class="form-control" type="text" name="customerEmail"/>
				</div>
			</div>
			<%-- </div>

		</div> --%>
		<%-- <div class="row"> --%>

			<div class="col-sm-8 form-group">
				<label class="col-sm-1 control-label no-padding-right" for="form-field-3">Status</label>
				<div class="col-sm-11">
					<label style="margin-right: 10px; margin-top: 5px;">
					<input id="form-search-status-appointmented" name="form-search-status-appointmented" type="checkbox" checked="checked" class="ace" />
					<span class="lbl">Appointmented&nbsp;&nbsp;</span>
					</label>
					<label style="margin-right: 10px; margin-top: 5px;">
					<input id="form-search-status-soldbylumei" name="form-search-status-soldbylumei" type="checkbox" checked="checked" class="ace" />
					<span class="lbl">Sold by Lumei&nbsp;&nbsp;</span>
					</label>
					<label style="margin-right: 10px; margin-top: 5px;">
					<input id="form-search-status-buyfromother" name="form-search-status-buyfromother" type="checkbox" checked="checked" class="ace" />
					<span class="lbl">Buy from Other&nbsp;&nbsp;</span>
					</label>
					<label style="margin-right: 10px; margin-top: 5px;">
					<input id="form-search-status-noresponse" name="form-search-status-noresponse" type="checkbox" checked="checked" class="ace" />
					<span class="lbl">No Response&nbsp;&nbsp;</span>
					</label>
					<label style="margin-right: 10px; margin-top: 5px;">
					<input id="form-search-status-stillinthemarket" name="form-search-status-stillinthemarket" type="checkbox" checked="checked" class="ace" />
					<span class="lbl">Still in the Market&nbsp;&nbsp;</span>
					</label>
				</div>
			</div>
			<div class="col-sm-4 form-group">
			<div class="col-sm-6 form-group center">
				<a class="btn btn-info btn-sm" id="search_btn">
					 <i class="ace-icon fa fa-search  bigger-110"></i> Search
				</a>
			</div>
			<div class="col-sm-6 form-group center">
				<a class="btn btn-info btn-sm" id="create_btn">
					<i class="ace-icon fa fa-plus  bigger-110"></i> Add
				</a>
			</div>
		</div>
</form>
</div>
</div>
<div class="row">
<div class="col-xs-12">
		<hr class="no-margin-top"> </hr>
		<div class="row">
				<div class="col-xs-12 table-responsive" id="page"></div>
		</div>
</div>
</div>

<script id="grid_temp" type="text/x-dot-template">
	<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>Name</th>
			<th>Phone</th>
			<th>Email</th>
			<th>Status</th>
			<th>Sales</th>
			<th>Operation</th>
		</tr>
	</thead>
	<tbody>
	{{~it.data :p:index}}
		<tr>
			<td>{{=p.name||''}}</td>
			<td>{{=p.phone||''}}</td>
			<td>{{=p.email||''}}</td>
			<td>{{=datadic['customerStatus'][p.status]}}</td>
			<td>{{=p.sales||''}}</td>
			<td><a href="javascript:viewProfile('{{=p.id }}','{{=p.name }}');">View Profile</a></td>
		</tr>
{{~}}
{{? !it.data.length}}
<tr ><td colspan="12">No records</td></tr>
{{?}}
	</tbody>
</table>
</script>
