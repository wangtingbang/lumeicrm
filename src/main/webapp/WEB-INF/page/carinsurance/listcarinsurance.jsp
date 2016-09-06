    <%@page import="com.lumei.crm.util.SessionUtil" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8"
                 pageEncoding="UTF-8" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/custcss/common.css" />
        <script src="<%=request.getContextPath()%>/resources/custjs/carinsurance/listcarinsurance.js"></script>

        <div class="page-header">
        <div class="row">
        <div class="col-sm-12">
        <div class="col-sm-10">
        <h1>
        <small>
        <i class="ace-icon fa fa-home home-icon"></i>
        <a href="<%=request.getContextPath() %>/">Home</a>
        <i class="ace-icon fa fa-angle-double-right"></i>
        Car Insurance
        </small>
        </h1>
        </div>
        </div>
        </div>
        </div>
        <form id="search_form" class="form-horizontal" action="#">
        <div class="row">
        <div class="col-xs-12">
        
        <div class="col-sm-3 form-group">
        <label class="col-sm-4 control-label no-padding-right">Name</label>
        <div class="col-sm-8">
        <input id="name" class="form-control" type="text" name="customerName"/>
        </div>
        </div>
        	
		<div class="col-sm-3 form-group">
        <label class="col-sm-4 control-label no-padding-right">WeChat</label>
        <div class="col-sm-8">
        <input id="wechat" class="form-control" type="text" name="wechat"/>
        </div>
        </div>
        
        <div class="col-sm-3 form-group">
        <label class="col-sm-4 control-label no-padding-right">Phone</label>
        <div class="col-sm-8">
        <input id="phone" class="form-control" type="text" name="phone"/>
        </div>
        </div>

        <div class="col-sm-3 form-group pull-right">
        <a class="btn btn-info btn-sm pull-right" style="margin-left:5px;" id="searchAll_btn">
        <i class="ace-icon fa fa-search-plus bigger-110"></i> Search All
        </a>
        <a class="btn btn-info btn-sm pull-right" id="searchMine_btn">
        <i class="ace-icon fa fa-search bigger-110"></i> Search Mine
        </a>
        </div>
		
		</div>
        </div>
        
		<div class="row">
        <div class="col-xs-12">
        
        <div class="col-sm-3 form-group">
        <label class="col-sm-4 control-label no-padding-right">Effective Date</label>
        <div class="col-sm-8">
        <input id="dateStart" class="date-timepicker form-control col-sm-12 " type="text"
        name="modifiedDateStart" value="<%=SessionUtil.getAttributes("dateStart")%>"/>
        </div>
        </div>
        
        <div class="col-sm-3 form-group">
        <label class="col-sm-4 control-label no-padding-right">To</label>
        <div class="col-sm-8">
        <input id="dateEnd" class="date-timepicker form-control col-sm-12 " type="text"
        name="modifiedDateEnd" value="<%=SessionUtil.getAttributes("dateEnd")%>"/>
        </div>
        </div>
        
		<div class="col-sm-3 form-group">
        <label class="col-sm-4 control-label no-padding-right">Sales</label>
        <div class="col-sm-8">
        <input id="salesName" class="form-control" type="text" name="salesName"/>
        </div>
        </div>
        
		</div>
        </div>        
        
		<div class="row">
        <div class="col-xs-12">
               
       	<div class="col-sm-3 no-padding-left">
       	<a class="btn btn-info btn-sm" id="export_btn">
        <i class="ace-icon fa fa-share bigger-110"></i> Data Export
        </a>
        <% if(SessionUtil.assignRight()){ %>
        <a class="btn btn-info btn-sm" id="assign_btn">
        <i class="ace-icon fa fa-check-square-o bigger-110"></i> Assign
        </a>
        <%} %>
        </div>
        <div class="col-sm-3 form-group"></div>
        <div class="col-sm-3 form-group"></div>
       	<div class="col-sm-3 form-group pull-right">
        <a class="btn btn-info btn-sm pull-right" id="create_btn">
        <i class="ace-icon fa fa-plus bigger-110"></i> Add Insurance
        </a>
        </div>
        
        </div>
        </div>
        
        </form>
        <input type="hidden" value="<%=SessionUtil.getCurrentUserId() %>" id="currentUserId">
        
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
		<th class="center">
			<label class="position-relative"><input id="checkAll" type="checkbox" class="ace"/>
			<span class="lbl"/>
			</label>
		</th>
        <th>Name</th>
        <th>WeChat</th>
        <th>Status</th>
        <th class="sorting" data="insurance_company">Insurance Company</th>
		<th class="sorting" data="effective_date">Effective Date</th>
		<th>Terms</th>
		<th class="sorting" data="SALES_ID">Sales</th>
        <th class="sorting" data="UPDATE_TIME">Modified Date</th>
		<th>Latest Notes</th>
		<th>Operation</th>
        </tr>
        </thead>
        <tbody>
        {{~it.data :p:index}}
        <tr>
		<td class="center">
		<label class="position-relative">
		<input name="checkItem" type="checkbox" class="ace" data_id="{{=p.id||''}}"/>
		<span class="lbl"/>
		</label>
		</td>
        <td>{{=p.customerName||''}}</td>
        <td>{{=p.wechat||''}}</td>
		<td>{{=datadic['carInsurStatus'][p.status]||''}}</td>
		<td>{{=p.insuranceCompany||''}}</td>
		<td>{{=new Date(p.effectiveDate).toChString()||''}}</td>
		<td>{{=p.terms||''}}</td>
        <td>{{=p.salesName||''}}</td>
		<td>{{=new Date(p.updateTime).toChString()||''}}</td>
        <td><a href="javascript:void();" class="latest_notes">
		{{?p.latestNotes}}{{=p.latestNotes.substring(0,10)}}{{?}}</a>
		<div class="tooltiptext" style="max-width:80px;display: none;">
    	{{=p.latestNotes}}
		</div>
		</td>
		<td><a href="javascript:viewDetails('{{=p.customerId }}','{{=p.id }}','{{=p.carDealId}}');">details</a></td>
        </tr>
        {{~}}
        {{? !it.data.length}}
        <tr ><td colspan="12">No records</td></tr>
        {{?}}
        </tbody>
        </table>
        </script>
        
<script id="export-temp" type="text/x-dot-template">
<form id="export-form" class="form-horizontal" action="#">
		<div class="row">
        <div class="col-xs-12">
        
        <div class="col-sm-4 form-group">
        <label class="col-sm-4 control-label no-padding-right">Name</label>
        <div class="col-sm-8">
        <input id="name" class="form-control" type="text" name="customerName"/>
        </div>
        </div>
        	
		<div class="col-sm-4 form-group">
        <label class="col-sm-4 control-label no-padding-right">WeChat</label>
        <div class="col-sm-8">
        <input id="wechat" class="form-control" type="text" name="wechat"/>
        </div>
        </div>
        
        <div class="col-sm-4 form-group">
        <label class="col-sm-4 control-label no-padding-right">Phone</label>
        <div class="col-sm-8">
        <input id="phone" class="form-control" type="text" name="phone"/>
        </div>
        </div>

		</div>
        </div>
        
		<div class="row">
        <div class="col-xs-12">
        
        <div class="col-sm-4 form-group">
        <label class="col-sm-4 control-label no-padding-right">Effective Date</label>
        <div class="col-sm-8">
        <input id="dateStart" class="date-timepicker form-control col-sm-12 " type="text"
        name="modifiedDateStart" value=""/>
        </div>
        </div>
        
        <div class="col-sm-4 form-group">
        <label class="col-sm-4 control-label no-padding-right">To</label>
        <div class="col-sm-8">
        <input id="dateEnd" class="date-timepicker form-control col-sm-12 " type="text"
        name="modifiedDateEnd" value=""/>
        </div>
        </div>
        
		<div class="col-sm-4 form-group">
        <label class="col-sm-4 control-label no-padding-right">Sales</label>
        <div class="col-sm-8">
        <input id="salesName" class="form-control" type="text" name="salesName"/>
        </div>
        </div>
        
		</div>
        </div>
</form>
</script>
<%@ include file="../customer/customerSearch.jsp"%>
<%@ include file="../customer/assign.jsp"%>