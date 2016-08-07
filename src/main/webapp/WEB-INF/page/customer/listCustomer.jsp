    <%@page import="com.lumei.crm.util.SessionUtil" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8"
                 pageEncoding="UTF-8" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/custcss/common.css" />
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
        Customers
        </small>
        </h1>
        </div>
        </div>
        </div>
        </div>
        <div class="row">
        <div class="col-xs-12">
        <form id="search_form" class="form-horizontal" action="#">
        <div class="col-sm-4 form-group">
        <label class="col-sm-2 control-label no-padding-right">Name</label>
        <div class="col-sm-10">
        <input id="form-field-customer-name" class="form-control" type="text" name="customerName"/>
        </div>
        </div>
        <div class="col-sm-4 form-group">
        <label class="col-sm-2 control-label no-padding-right">Phone</label>
        <div class="col-sm-10">
        <input id="form-field-customer-phone" class="form-control" type="text" name="customerPhone"/>
        </div>
        </div>
        <div class="col-sm-4 form-group">
        <label class="col-sm-2 control-label no-padding-right">Email</label>
        <div class="col-sm-10">
        <input id="form-field-customer-email" class="form-control" type="text" name="customerEmail"/>
        </div>
        </div>

        <div class="col-sm-4 form-group">
        <label class="col-sm-2 control-label no-padding-right">Wechat</label>
        <div class="col-sm-10">
        <input id="wechatId" class="form-control" type="text" name="wechatId"/>
        </div>
        </div>
        
        <div class="col-sm-4 form-group">
        <label class="col-sm-5 control-label no-padding-left">Potential Buying Date</label>
        <div class="col-sm-7">
        <input id="form-field-5-1" class="date-timepicker form-control col-sm-12 " type="text"
        name="potentialBuyingDateStart" />
        </div>
        </div>
        <div class="col-sm-4 form-group">
        <label class="col-sm-2 control-label no-padding-center">~</label>
        <div class="col-sm-10">
        <input id="form-field-5-2" class="date-timepicker form-control col-sm-12 " type="text"
        name="potentialBuyingDateEnd"/>
        </div>
        </div>

        <div class="col-sm-6 form-group">
        <label class="col-sm-1 control-label no-padding-right">Rating</label>
        <div class="col-sm-11">
        <label style="margin-right: 10px; margin-top: 5px;">
        <input id="form-search-status-appointmented" name="ratingArray" type="checkbox" value="1"
        checked="checked" class="ace" />
        <span class="lbl">A&nbsp;&nbsp;</span>
        </label>
        <label style="margin-right: 10px; margin-top: 5px;">
        <input id="form-search-status-soldbylumei" name="ratingArray" type="checkbox" value="2"
        checked="checked" class="ace" />
        <span class="lbl">B&nbsp;&nbsp;</span>
        </label>
        <label style="margin-right: 10px; margin-top: 5px;">
        <input id="form-search-status-buyfromother" name="ratingArray" type="checkbox" value="3"
        checked="checked" class="ace" />
        <span class="lbl">C&nbsp;&nbsp;</span>
        </label>
        </div>
        </div>

        <div class="col-sm-6 form-group">
        <div class="col-sm-4 form-group center">
        <a class="btn btn-info btn-sm" id="search_btn">
        <i class="ace-icon fa fa-search bigger-110"></i> Search All
        </a>
        </div>
        <div class="col-sm-4 form-group center">
        <a class="btn btn-info btn-sm" id="searchbyme_btn">
        <i class="ace-icon fa fa-search bigger-110"></i> Search mine
        </a>
        </div>
        <div class="col-sm-4 form-group center">
        <a class="btn btn-info btn-sm" id="create_btn">
        <i class="ace-icon fa fa-plus bigger-110"></i> Add Customer
        </a>
        </div>
        </div>
        </form>
        <input type="hidden" value="<%=SessionUtil.getCurrentUserId() %>" id="currentUserId">
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
	<label class="col-sm-12"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">
    <a id="send_email_btn" href="javascript:void(0);"><i class="ace-icon fa fa-envelope"></i>&nbsp; Send Email</i></a></h5></label>
        <table id="sample-table-1" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
		<th class="center">
			<label class="position-relative"><input id="checkAll" type="checkbox" class="ace"/>
			<span class="lbl"/>
			</label>
		</th>
        <th>Name</th>
        <th>Wechat</th>
        <th>Phone</th>
        <th>Email</th>
        <th class="sorting" data="rating">Rating</th>
        <th class="sorting" data="salesId">Sales</th>
        <th class="sorting" data="potentialBuyingDate">Potential Buying Date</th>
        <th>Operation</th>
        </tr>
        </thead>
        <tbody>
        {{~it.data :p:index}}
        <tr>
		<td class="center">
		<label class="position-relative">
		<input name="checkItem" type="checkbox" class="ace" value="{{=p.email||''}}"/>
		<span class="lbl"/>
		</label>
		</td>
        <td>{{=p.name||''}}</td>
        <td>{{=p.wechatId||''}}</td>
        <td>{{=p.phone||''}}</td>
        <td>{{=p.email||''}}</td>
        <td>{{=datadic['customerRating'][p.rating]||''}}</td>
        <td>{{=p.sales||''}}</td>
        <td>{{=new Date(p.potentialBuyingDate).toChString(false)||''}}</td>
        <td><a href="javascript:viewProfile('{{=p.id }}','{{=p.name }}');">Profile</a></td>
        </tr>
        {{~}}
        {{? !it.data.length}}
        <tr ><td colspan="12">No records</td></tr>
        {{?}}
        </tbody>
        </table>
        </script>
