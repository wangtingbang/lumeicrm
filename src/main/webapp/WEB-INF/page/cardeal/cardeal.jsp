<%@ page import="com.lumei.crm.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script src="<%=request.getContextPath()%>/resources/custjs/cardeal/cardeal.js"></script>
<script src="<%=request.getContextPath()%>/resources/custjs/notes/notes.js"></script>

<div class="page-header">
	<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-10">
			<h1>
			<small>
				<i class="ace-icon fa fa-home home-icon"></i>
        		<a href="<%=request.getContextPath() %>/">Home</a>
        		<i class="ace-icon fa fa-angle-double-right"></i>
				<a href="<%=request.getContextPath() %>/cardeal/list?active=cardeal">Car Deals</a>
			</small>
			</h1>
		</div>
		<div class="col-sm-2">
    	<div class="clearfix">
    	<a class="btn btn-info btn-xs pull-right" id="">
        <i class="ace-icon fa fa-laptop bigger-110"></i> Add Insurance
        </a>
		</div>
 	</div>
	</div>
	</div>
</div>

<form id="search_form" class="form-horizontal" action="#" hidden="hidden">
<input id="serviceIdSearch" name="serviceIdSearch"  class="form-control" value="<%=SessionUtil.getAttributes("serviceId") %>" hidden="hidden"/>
<input id="customerIdSearch" name="customerIdSearch"  class="form-control" value="<%=SessionUtil.getAttributes("customerId") %>" hidden="hidden"/>
</form>

<div class="row">
<div id="profile-content"></div>
</div>

<script id="profile_temp" type="text/x-dot-template">
<form class="form-horizontal" id="submit-form">
<input type="hidden" name="id" id="id" value="{{=it.id||''}}"/>
<input type="hidden" name="salesId" id="salesId" value="{{=it.salesId||''}}"/>
<input type="hidden" name="customerId" id="customerId" value="{{=it.customerId||''}}"/>

<div class="col-sm-6">
	<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="name">Customer Name</label>
	<div class="col-sm-6">
    	<div class="clearfix">
    		<input type="text" name="customerName" id="customerName" class="form-control col-sm-1 " value="{{=it.customerName||'' }}" readonly="readonly"/>
		</div>
 	</div>
	<div class="col-sm-2">
    	<div class="clearfix">
    	<a class="btn btn-info btn-sm pull-right" href="<%=request.getContextPath() %>/customer/get?customerId={{=it.customerId||''}}">
        <i class="ace-icon fa fa-user bigger-110"></i> Profile
        </a>
		</div>
 	</div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Sales Name</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="salesName" id="salesName" class="form-control col-sm-1" value="{{=it.salesName||''}}" readonly="readonly"/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Status Date</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="dealDate" id="dealDate" class="date-timepicker form-control col-sm-1 " value="{{=new Date(it.dealDate).toChString()||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Deal Status</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<select class="form-control" id="dealStatus" name="dealStatus" {{? it.readonly}}disabled="disabled" readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Rating</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<select class="form-control" id="rating" name="rating" {{? it.readonly}}disabled="disabled" readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Deposit</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<select class="form-control" id="deposit" name="deposit" {{? it.readonly}}disabled="disabled" readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">CC#</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<input type="text" name="creditCardNo" id="creditCardNo" class="form-control col-sm-1" value="{{=it.creditCardNo||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Source</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<select class="form-control" id="source" name="source" {{? it.readonly}}disabled="disabled" readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group" id="ambassadordiv">
    <label class="col-sm-4 control-label no-padding-right">Ambassador</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<input type="text" name="ambassador" id="ambassador" class="form-control col-sm-1" value="{{=it.ambassador||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>
	<div class="form-group" id="remarkdiv">
    <label class="col-sm-4 control-label no-padding-right">Remark</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<input type="text" name="remark" id="remark" class="form-control col-sm-1" value="{{=it.remark||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Case No</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<input type="text" name="caseNo" id="caseNo" class="form-control col-sm-1" value="{{=it.caseNo||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
	  <label class="col-sm-4 control-label no-padding-right">Used/New</label>
	  <div class="col-sm-8">
		<select class="form-control" id="isNew" name="isNew" {{? it.readonly}}disabled="disabled" readonly="readonly"{{?}}>
		<option value="1" {{? 1 == it.isNew}}selected="selected"{{?}}>New</option>
		<option value="2" {{? 2 == it.isNew}}selected="selected"{{?}}>Used</option>
		</select>
	  </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Years</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="years" id="years" class="form-control col-sm-1 " value="{{=it.years||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Make</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="makes" id="makes" class="form-control col-sm-1 " value="{{=it.makes||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>
	
	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Model</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="model" id="model" class="form-control col-sm-1 " value="{{=it.model||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>
			
 	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Exterior Color</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="exteriorColor" id="exteriorColor" class="form-control col-sm-1 " value="{{=it.exteriorColor||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Interior Color</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="interiorColor" id="interiorColor" class="form-control col-sm-1 " value="{{=it.interiorColor||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group" id="mileagesdiv">
    <label class="col-sm-4 control-label no-padding-right">Mileages</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="mileages" id="mileages" class="form-control col-sm-1 " value="{{=it.mileages||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

 	<div class="form-group" id="msrpdiv">
    <label class="col-sm-4 control-label no-padding-right">MSRP</label>
	<label class="col-sm-1 control-label no-padding-right">$</label>
    <div class="col-sm-7">
		<div class="clearfix">
    		<input type="text" name="msrp" id="msrp" class="form-control col-sm-1 " value="{{=it.msrp||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Selling Price</label>
	<label class="col-sm-1 control-label no-padding-right">$</label>
    <div class="col-sm-7">
		<div class="clearfix">
    		<input type="text" name="sellingPrice" id="sellingPrice" class="form-control col-sm-1 " value="{{=it.sellingPrice||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="comments">Comments:</label>
	<div class="col-sm-8">
	<div class="clearfix">
	<textarea class="form-control limited" id="comments"  name="comments" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.comments || ''}}</textarea>
	</div>
	</div>
	</div>

</div>

<div class="col-sm-6">
	<div class="form-group" style="margin-left:8.3%">
	<label class="col-sm-12"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Notes
	<a href="#" class="btn btn-info btn-xs pull-right" onclick="javascript:addNote();">
        <i class="ace-icon fa fa-comments-o"></i> Add Note
    </a>
	</h5>
	</label>
	</div>
	<div class="comments" id="notesdiv"></div>
</div>

<div class="col-sm-12">

<div class="col-sm-6">
	<div class="form-group">
	  <label class="col-sm-4 control-label no-padding-right">Type</label>
	  <div class="col-sm-8">
		<select class="form-control" id="method" name="method" {{? it.readonly}}disabled="disabled" readonly="readonly"{{?}}>
		<option value="1" {{? 1 == it.method}}selected="selected"{{?}}>Cash Deal</option>
		<option value="2" {{? 2 == it.method}}selected="selected"{{?}}>Finance</option>
		<option value="3" {{? 3 == it.method}}selected="selected"{{?}}>Lease</option>
		</select>
	  </div>
	</div>

	<div class="form-group" id="downPaymentdiv">
    <label class="col-sm-4 control-label no-padding-right">Down Payment</label>
	<label class="col-sm-1 control-label no-padding-right">$</label>
    <div class="col-sm-7">
		<div class="clearfix">
    		<input type="text" name="downPayment" id="downPayment" class="form-control col-sm-1 " value="{{=it.downPayment||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group" id="ratediv">
    <label class="col-sm-4 control-label no-padding-right">Rate</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="rate" id="rate" class="form-control col-sm-1 " value="{{=it.rate||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group" id="termsdiv">
    <label class="col-sm-4 control-label no-padding-right">Terms</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="terms" id="terms" class="form-control col-sm-1 " value="{{=it.terms||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group" id="monthlyPaydiv">
    <label class="col-sm-4 control-label no-padding-right">Monthly Pay</label>
	<label class="col-sm-1 control-label no-padding-right">$</label>
    <div class="col-sm-7">
		<div class="clearfix">
    		<input type="text" name="monthlyPay" id="monthlyPay" class="form-control col-sm-1 " value="{{=it.monthlyPay||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Files</label>
    <div class="col-sm-6">
		<div class="clearfix col-sm-11 no-padding-left">
    		<input type="file" name="file" id="file">
    	</div>
    </div>
	<div class="col-sm-2">
    	<div class="clearfix">
    	<a class="btn btn-info btn-xs pull-right" id="files_btn">
        <i class="ace-icon fa fa-cloud-upload bigger-110"></i> upload
        </a>
		</div>
 	</div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right"></label>
    <div class="col-sm-8" id="fileListDiv">
    </div>
	</div>

</div>


<div class="col-sm-6">
	<div class="form-group">
	  <label class="col-sm-4 control-label no-padding-right">Trade In</label>
	  <div class="col-sm-8">
		<select class="form-control" id="tradeIn" name="tradeIn" {{? it.readonly}}disabled="disabled" readonly="readonly"{{?}}>
		<option value="1" {{? 1 == it.tradeIn}}selected="selected"{{?}}>No</option>
		<option value="2" {{? 2 == it.tradeIn}}selected="selected"{{?}}>Yes</option>
		</select>
	  </div>
	</div>

	<div class="form-group" id="tradeInValueDiv">
    <label class="col-sm-4 control-label no-padding-right">Trade In Value</label>
	<label class="col-sm-1 control-label no-padding-right">$</label>
    <div class="col-sm-7">
		<div class="clearfix">
    		<input type="text" name="tradeInValue" id="tradeInValue" class="form-control col-sm-1 " value="{{=it.tradeInValue||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group" id="tradeInMakeDiv">
    <label class="col-sm-4 control-label no-padding-right">Make</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="tradeInMake" id="tradeInMake" class="form-control col-sm-1 " value="{{=it.tradeInMake||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group" id="tradeInModelDiv">
    <label class="col-sm-4 control-label no-padding-right">Model</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="tradeInModel" id="tradeInModel" class="form-control col-sm-1 " value="{{=it.tradeInModel||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group" id="tradeInYearDiv">
    <label class="col-sm-4 control-label no-padding-right">Year</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="tradeInYear" id="tradeInYear" class="form-control col-sm-1 " value="{{=it.tradeInYear||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group" id="tradeInMileageDiv">
    <label class="col-sm-4 control-label no-padding-right">Mileage</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="tradeInMileage" id="tradeInMileage" class="form-control col-sm-1 " value="{{=it.tradeInMileage||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group" id="tradeInEcDiv">
    <label class="col-sm-4 control-label no-padding-right">Exterior Color</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="tradeInEc" id="tradeInEc" class="form-control col-sm-1 " value="{{=it.tradeInEc||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group" id="tradeInIcDiv">
    <label class="col-sm-4 control-label no-padding-right">Interior Color</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="tradeInIc" id="tradeInIc" class="form-control col-sm-1 " value="{{=it.tradeInIc||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

</div>

</div>

<div class="col-sm-12 form-group">
<hr class="no-margin-top"> </hr>
	<div class="col-sm-4 form-group center">
	</div>
	<div class="col-sm-4 form-group center">
		<div class="col-sm-6 form-group center">
			<a class="btn btn-info btn-sm" id="save_btn" {{? !it.readonly}}onclick="javascript:saveProfile();"{{?}}>
				 <i class="ace-icon fa fa-check bigger-110"></i> Save
			</a>
		</div>
		<div class="col-sm-6 form-group center">
			<a class="btn btn-sm btn-default" type="reset" id="delete_btn"  {{? !it.readonly}}onclick="javascript:deleteProfile('{{=it.id}}');"{{?}}>
				<i class="ace-icon fa fa-times bigger-110"></i> Delete
			</a>
		</div>
	</div>
	<div class="col-sm-4 form-group center">
	</div>
</div>

</form>
</script>
<%@ include file="../notes/notes.jsp"%>
<%@ include file="../customer/file.jsp"%>