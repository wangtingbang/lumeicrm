<%@ page import="com.lumei.crm.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script src="<%=request.getContextPath()%>/resources/custjs/cardeal/cardeal.js"></script>
<script src="<%=request.getContextPath()%>/resources/custjs/notes/notes.js"></script>

<div class="page-header">
	<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-20">
			<h1>
			<small>
				<i class="ace-icon fa fa-home home-icon"></i>
        		<a href="<%=request.getContextPath() %>/">Home</a>
        		<i class="ace-icon fa fa-angle-double-right"></i>
				<a href="<%=request.getContextPath() %>/cardeal/list?active=cardeal">Car Deals</a>
			</small>
			</h1>
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
	<div class="col-sm-8">
    	<div class="clearfix">
    		<input type="text" name="customerName" id="customerName" class="form-control col-sm-1 " value="{{=it.customerName||'' }}" readonly="readonly"/>
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
    <label class="col-sm-4 control-label no-padding-right">Date</label>
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
	  <label class="col-sm-4 control-label no-padding-right">Used/New</label>
	  <div class="col-sm-8" id="isNew">
	      <label class="line-height-1" style="margin-right: 10px; margin-top: 5px;">
	        <input name="isNew" value="1" type="radio" class="ace" {{? it.readonly}}disabled="disabled"{{?}} {{? !it.gender|| 1 == it.isNew}}checked="checked"{{?}} {{? it.readonly}}readonly="readonly"{{?}}/>
	        <span class="lbl"> New</span>
	      </label>
	      <label class="line-height-1" style="margin-right: 10px;">
	        <input name="isNew" value="2" type="radio" class="ace" {{? it.readonly}}disabled="disabled"{{?}} {{? 2 == it.isNew}}checked="checked"{{?}} {{? it.readonly}}readonly="readonly"{{?}}/>
	        <span class="lbl"> Used</span>
	      </label>
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

 	<div class="form-group" id="msrpdiv">
    <label class="col-sm-4 control-label no-padding-right">MSRP</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="msrp" id="msrp" class="form-control col-sm-1 " value="{{=it.msrp||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
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

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Selling Price</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="sellingPrice" id="sellingPrice" class="form-control col-sm-1 " value="{{=it.sellingPrice||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="comments">Comments:</label>
	<div class="col-sm-8">
	<div class="clearfix">
	<textarea class="form-control limited" id="comments"  name="comments" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.carDescription || ''}}</textarea>
	</div>
	</div>
	</div>

	<div class="form-group">
	  <label class="col-sm-4 control-label no-padding-right">Method</label>
	  <div class="col-sm-8" id="method">
	      <label class="line-height-1" style="margin-right: 10px; margin-top: 5px;">
	        <input name="method" value="1" type="radio" class="ace" {{? it.readonly}}disabled="disabled"{{?}} {{? !it.gender|| 1 == it.method}}checked="checked"{{?}} {{? it.readonly}}readonly="readonly"{{?}}/>
	        <span class="lbl"> Cash Deal</span>
	      </label>
	      <label class="line-height-1" style="margin-right: 10px;">
	        <input name="method" value="2" type="radio" class="ace" {{? it.readonly}}disabled="disabled"{{?}} {{? 2 == it.method}}checked="checked"{{?}} {{? it.readonly}}readonly="readonly"{{?}}/>
	        <span class="lbl"> Finance</span>
	      </label>
		  <label class="line-height-1" style="margin-right: 10px;">
	        <input name="method" value="3" type="radio" class="ace" {{? it.readonly}}disabled="disabled"{{?}} {{? 3 == it.method}}checked="checked"{{?}} {{? it.readonly}}readonly="readonly"{{?}}/>
	        <span class="lbl"> Lease</span>
	      </label>
	  </div>
	</div>

	<div class="form-group" id="downPaymentdiv">
    <label class="col-sm-4 control-label no-padding-right">Down Payment</label>
    <div class="col-sm-8">
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
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="monthlyPay" id="monthlyPay" class="form-control col-sm-1 " value="{{=it.monthlyPay||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

</div>

<div class="col-sm-6">
	<div class="form-group" style="margin-left:8.3%">
	<label class="col-sm-12"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Notes</h5></label>
</div>
	<div class="comments" id="notesdiv"></div>
	<div class="center">
	<a href="#" onclick="javascript:addNote();"><i class="ace-icon fa fa-comments-o"></i>&nbsp; Add Note</a>
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