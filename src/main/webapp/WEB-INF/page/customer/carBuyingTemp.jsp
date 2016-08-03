<%@page import="com.lumei.crm.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="<%=request.getContextPath()%>/resources/custjs/customer/carbuying.js"></script>

<div class="page-header">
	<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-10">
			<h1>
			<small>
				<i class="ace-icon fa fa-home home-icon"></i>
				<a href="<%=request.getContextPath() %>/">Home</a>
				<i class="ace-icon fa fa-angle-double-right"></i>
				<%=SessionUtil.getAttributes("customerName") %>
				<i class="ace-icon fa fa-angle-double-right"></i>
				 Car Buying
			</small>
			</h1>
		</div>
	</div>
	</div>
</div>

<form id="search_form" class="form-horizontal" action="#" hidden="hidden" >
	<div class="row">
		<div class="col-sm-6 form-group">
			<label class="col-sm-2 control-label no-padding-right" for="form-field-1">customerId</label>
			<div class="col-sm-4">
				<input id="customerId" name="customerId"  class="form-control" value="<%=SessionUtil.getAttributes("customerId") %>" readonly="readonly"/>
			</div>
		</div>
	</div>
</form>

<div id="car-buying-content"></div>

<script id="step_temp_1" type="text/x-dot-template">
<form class="form-horizontal" id="submit-form1">
<input type="hidden" name="id" id="id" value="{{=it.id}}"/>
<input type="hidden" name="userId" id="userId" value="{{=it.userId}}"/>

<div class="form-group" style="margin-left:8.3%">
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Basic Information</h5></label>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="activityName">Sales Name:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="salesName" id="salesName" class="form-control col-sm-12" value="{{=it.salesName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="category">Category:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="category" id="category" class="form-control col-sm-12" value="{{=it.category || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="activityName">Makes:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="makes" id="makes" class="form-control col-sm-12" value="{{=it.makes || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="model">Model:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="model" id="model" class="form-control col-sm-12" value="{{=it.model || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="car description">Car Description:</label>
	<div class="col-sm-4">
	<div class="clearfix">
	<textarea class="form-control limited" id="carDescription"  name="carDescription" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.carDescription || ''}}</textarea>
	</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right">Is New:</label>
	<div class="col-sm-4">
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="isNew" value="1" type="radio" class="ace" {{? it.readonly && 1 != it.isNew}}disabled="disabled"{{?}} {{? 1 == it.isNew}}checked="checked"{{?}}/>
				<span class="lbl">New</span>
			</label>
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="isNew" value="0" type="radio" class="ace" {{? it.readonly && 0 != it.isNew}}disabled="disabled"{{?}} {{? 2 == it.isNew}}checked="checked"{{?}}/>
				<span class="lbl">Used</span>
			</label>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="years">Years:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="years" id="years" class="form-control col-sm-12" value="{{=it.years || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="mileages">Mileages:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="mileages" id="mileages" class="form-control col-sm-12" value="{{=it.mileages || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="msrp">MSRP:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="msrp" id="msrp" class="form-control col-sm-12" value="{{=it.msrp || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="exterior">Exterior:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="exterior" id="productName" class="form-control col-sm-12" value="{{=it.exterior || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="interior">Interior:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="interior" id="interior" class="form-control col-sm-12" value="{{=it.interior || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="color">Color:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="color" id="color" class="form-control col-sm-12" value="{{=it.color || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="comments">Comments:</label>
	<div class="col-sm-4">
	<div class="clearfix">
	<textarea class="form-control limited" id="comments"  name="comments" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.carDescription || ''}}</textarea>
	</div>
	</div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Finance</h5></label>
</div>


<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="downPayment">Down Payment:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="downPayment" id="downPayment" class="form-control col-sm-12" value="{{=it.downPayment || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="rate">Rate:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="rate" id="rate" class="form-control col-sm-12" value="{{=it.rate || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="terms">Terms:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="terms" id="terms" class="form-control col-sm-12" value="{{=it.terms || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="monthlyPay">Monthly Pay:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="monthlyPay" id="monthlyPay" class="form-control col-sm-12" value="{{=it.monthlyPay || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="comments">Comments:</label>
	<div class="col-sm-4">
	<div class="clearfix">
	<textarea class="form-control limited" id="comments"  name="comments" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.comments || ''}}</textarea>
	</div>
	</div>
</div>

<div class="form-group" style="margin-left:8.3%">
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Additional Information</h5></label>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="creditCardNo">Credit Card #:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="creditCardNo" id="creditCardNo" class="form-control col-sm-12" value="{{=it.creditCardNo || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="expirationDate">Expiration Date:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="expirationDate" id="expirationDate" class="form-control col-sm-12" value="{{=it.expirationDate || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="cvs">CVS:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="cvs" id="cvs" class="form-control col-sm-12" value="{{=it.cvs || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="driverLicense">Driver License:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="driverLicense" id="driverLicense" class="form-control col-sm-12" value="{{=it.driverLicense || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>


<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Lease</h5></label>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="downPayment">Down Payment:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="downPayment" id="downPayment" class="form-control col-sm-12" value="{{=it.downPayment || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="terms">Terms:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="terms" id="terms" class="form-control col-sm-12" value="{{=it.terms || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="monthlyPay">Monthly Pay:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="monthlyPay" id="monthlyPay" class="form-control col-sm-12" value="{{=it.monthlyPay || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="comments">Comments:</label>
	<div class="col-sm-4">
	<div class="clearfix">
	<textarea class="form-control limited" id="comments"  name="comments" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.comments || ''}}</textarea>
	</div>
	</div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Trade In</h5></label>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="tradeIn">Trade In:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="tradeIn" id="tradeIn" class="form-control col-sm-12" value="{{=it.tradeIn || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="makes">Makes:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="makes" id="makes" class="form-control col-sm-12" value="{{=it.makes || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="mileage">Mileage:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="mileage" id="mileage" class="form-control col-sm-12" value="{{=it.mileage || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="model">Model:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="model" id="model" class="form-control col-sm-12" value="{{=it.model || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="years">Years:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="years" id="years" class="form-control col-sm-12" value="{{=it.years || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="value">Value:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="value" id="value" class="form-control col-sm-12" value="{{=it.value || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="exterior">Exterior:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="exterior" id="exterior" class="form-control col-sm-12" value="{{=it.exterior || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="interior">Interior:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="interior" id="interior" class="form-control col-sm-12" value="{{=it.interior || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="color">Color:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="color" id="color" class="form-control col-sm-12" value="{{=it.color || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group" style="margin-bottom:0px">
<label class="col-sm-12"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px"></h5></label>
</div>

<div class="col-sm-12 form-group">
	<div class="col-sm-4 form-group center">
	</div>
	<div class="col-sm-4 form-group center">
		<div class="col-sm-6 form-group center">
			<a class="btn btn-info btn-sm" id="save_btn" onclick="javascript:saveCarBuying();">
				 <i class="ace-icon fa fa-check bigger-110"></i> Save
			</a>
		</div>
		<div class="col-sm-6 form-group center">
			<a class="btn btn-sm btn-default" type="reset" id="delete_btn">
				<i class="ace-icon fa fa-times bigger-110"></i> Delete
			</a>
		</div>
	</div>
	<div class="col-sm-4 form-group center">
	</div>
</div>
</form>
</script>
