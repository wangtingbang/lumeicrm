<%@page import="com.lumei.crm.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="page-header">
	<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-10">
			<h1>
			<small>
				<i class="ace-icon fa fa-home home-icon"></i>
				<a href="<%=request.getContextPath() %>/">主页</a>
				<i class="ace-icon fa fa-angle-double-right"></i>
				<a href="#">售后宝</a>
				<i class="ace-icon fa fa-angle-double-right"></i>
				<c:if test="${authType == 'op'}">售后宝消费记录</c:if><c:if test="${authType == 'ap'}">售后宝消费记录</c:if>
			</small>
			</h1>
		</div>
	</div>
	</div>
</div>
<div class="row">
<div class="col-xs-12">
<form id="search_form" class="form-horizontal" action="#">
		<div class="row">
			<div class="col-sm-6 form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">门店名称</label>
				<div class="col-sm-9">
						<input id="form-field-store-name"  class="form-control" value="<%=SessionUtil.getAttributes("storeFullName") %>" readonly="readonly"/>
				</div>
			</div>
			<div class="col-sm-6 form-group">
				<label class="col-sm-1 control-label no-padding-right" for="form-field-2">品牌</label>
				<div class="col-sm-5">
						<input id="form-field-store-brand"  class="form-control" value="<%=SessionUtil.getAttributes("storeBrand") %>" readonly="readonly"/>
				</div>
			<div class="col-sm-3 form-group center">
				<a class="btn btn-info btn-sm" id="search_btn">
					 <i class="ace-icon fa fa-search  bigger-110"></i> 查询
				</a>
			</div>
			<div class="col-sm-3 form-group center">
				<a class="btn btn-info btn-sm" id="create_btn">
					<i class="ace-icon fa fa-plus  bigger-110"></i> 新增
				</a>
			</div>
			</div>

		</div>
		<div class="row">
			<div class="col-sm-6 form-group">
				<label class="col-sm-2 control-label no-padding-right" for="">消费时间</label>
				<div class="col-sm-4">
					<input id="form-search-create-time-start" class="date-timepicker form-control col-sm-12 " type="text" name="createTimeStart"></input>
				</div>
				<label class="col-sm-1" for="" style="text-align: center;">~</label>
				<div class="col-sm-4">
					<input id="form-search-create-time-end" class="date-timepicker form-control col-sm-12 " type="text" name="createTimeEnd"></input>
				</div>
			</div>

			<div class="col-sm-6 form-group">
				<label class="col-sm-1 control-label no-padding-right" for="form-field-3">状态</label>
				<div class="col-sm-10">
					<label style="margin-right: 10px; margin-top: 5px;">
					<input id="form-search-status-unconfirm" name="form-search-status-unconfirm" type="checkbox" class="ace" />
					<span class="lbl">未确认&nbsp;&nbsp;</span>
					</label>
					<label style="margin-right: 10px; margin-top: 5px;">
					<input id="form-search-status-confirm" name="form-search-status-confirm" type="checkbox" class="ace" />
					<span class="lbl">已确认&nbsp;&nbsp;</span>
					</label>
					<label style="margin-right: 10px; margin-top: 5px;">
					<input id="form-search-status-failed" name="form-search-status-failed" type="checkbox" class="ace" />
					<span class="lbl">失败&nbsp;&nbsp;</span>
					</label>
					<label style="margin-right: 10px; margin-top: 5px;">
					<input id="form-search-status-paied" name="form-search-status-paied" type="checkbox" class="ace" />
					<span class="lbl">已结息&nbsp;&nbsp;</span>
					</label>
					</label>
				</div>
			</div>

		</div>
		</form>
	</div>
</div>
<div class="row">
<div class="col-xs-12">
		<hr class="no-margin-top"></hr>
		<div class="row">
				<div class="col-xs-12 table-responsive" id="page"></div>
		</div>
</div>
</div>

<script id="grid_temp" type="text/x-dot-template">
	<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>订单ID</th>
			<th>门店名称</th>
			<th>产品订单</th>
			<th>投资人</th>
			<th>投资本金(元)</th>
			<th>消费金额(元)</th>
			<th>门店收益(元)</th>
			<th>门店收益时间</th>
			<th>创建时间</th>
			<th>确认时间</th>
			<th>状态</th>
		</tr>
	</thead>
	<tbody>
	{{~it.data :p:index}}
		<tr>
			<td>{{=p.id }}</td>
			<td><a href="javascript:findStore('{{=p.storeId }}');">{{=p.storeName}}</a></td>
			<td>{{=p.orderId}}</td>
			<td>{{=p.userName }}</td>
			<td>{{=p.orderAmount.formatter(2,4) }}</td>
			<td>{{=p.amount.formatter(2,4)}}</td>
			<td>{{=p.storeProfit.formatter(2,4)}}</td>
			<td>{{=new Date(p.storeProfitTime).toChString(true)}}</td>
			<td>{{=new Date(p.createTime).toChString(true)}}</td>
			<td>{{=new Date(p.confimTime).toChString(true)}}</td>
			<td>
				{{? p.storeProfitPaymentStatus==3}}
					已结息
				{{?? p.status}}
					{{=datadic['status'][p.status]}}
				{{?}}
			</td>
		</tr>
{{~}}
{{? !it.data.length}}
<tr ><td colspan="12">没有相关记录</td></tr>
{{?}}
	</tbody>
</table>
</script>

<%@ include file="activityTemp.jsp" %>
<%@ include file="storeTemp.jsp" %>

<script src="<%=request.getContextPath()%>/resources/custjs/consume/consume.js"></script>
