    <%@page import="com.lumei.crm.util.SessionUtil" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8"
                 pageEncoding="UTF-8" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/custcss/common.css" />
        <script src="<%=request.getContextPath()%>/resources/custjs/customer/listcustomer.js"></script>

        <div class="page-header">
        <div class="row">
        <div class="col-sm-12">
        <div class="col-sm-10">
        <h1>
        <small>
        <i class="ace-icon fa fa-home home-icon"></i>
        <a href="<%=request.getContextPath() %>/">Home</a>
        <i class="ace-icon fa fa-angle-double-right"></i>
        Customers
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
        <input id="name" class="form-control" type="text" name="name"/>
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
               
       	<div class="col-sm-3 no-padding-left">
        <a class="btn btn-info btn-sm" id="email_btn">
        <i class="ace-icon fa fa-envelope bigger-110"></i> Send Email
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
        <i class="ace-icon fa fa-plus bigger-110"></i> Add Customer
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
        <th>Phone</th>
        <th>Email</th>
		<th>Service Types</th>
        <th>Sales</th>
        <th>Operation</th>
        </tr>
        </thead>
        <tbody>
        {{~it.data :p:index}}
        <tr>
		<td class="center">
		<label class="position-relative">
		<input name="checkItem" type="checkbox" class="ace" data_email="{{=p.email||''}}" data_id="{{=p.id||''}}"/>
		<span class="lbl"/>
		</label>
		</td>
        <td>{{=p.name||''}}</td>
        <td>{{=p.wechat||''}}</td>
        <td>{{=p.phone||''}}</td>
        <td>{{=p.email||''}}</td>
		<td>{{=serviceTypeDisp(p.service||'')}}</td>
        <td>{{=p.sales||''}}</td>
        <td><a href="javascript:viewProfile('{{=p.id }}');">profile</a></td>
        </tr>
        {{~}}
        {{? !it.data.length}}
        <tr ><td colspan="12">No records</td></tr>
        {{?}}
        </tbody>
        </table>
        </script>
<%@ include file="assign.jsp"%>