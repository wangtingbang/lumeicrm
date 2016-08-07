<%@page import="com.lumei.crm.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="<%=request.getContextPath()%>/resources/custjs/customer/carselling.js"></script>
<script src="<%=request.getContextPath()%>/resources/custjs/customer/notemgr.js"></script>

<div class="page-header">
	<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-10">
			<h1>
			<small>
				<i class="ace-icon fa fa-home home-icon"></i>
				<a href="<%=request.getContextPath() %>/">Home</a>
				<i class="ace-icon fa fa-angle-double-right"></i>
				<a href="<%=request.getContextPath() %>/customer/getProfile?customerId=<%=SessionUtil.getAttributes("customerId") %>">
				<%=SessionUtil.getAttributes("customerName") %>
				</a>
				<i class="ace-icon fa fa-angle-double-right"></i>
				 Car Selling
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<%=request.getContextPath() %>/customer/getProfile?customerId=<%=SessionUtil.getAttributes("customerId") %>">
				<i class="ace-icon fa fa-angle-double-left"></i>
				Back
				</a> 
			</small>
			</h1>
		</div>
	</div>
	</div>
</div>

<form id="search_form" class="form-horizontal" action="#" hidden="hidden" >
<input id="serviceId" name="serviceId"  class="form-control" value="<%=SessionUtil.getAttributes("serviceId") %>" readonly="readonly"/>
<input id="customerId" name="customerId"  class="form-control" value="<%=SessionUtil.getAttributes("customerId") %>" readonly="readonly"/>
</form>

<div id="car-selling-content"></div>

<script id="notes_temp" type="text/x-dot-template">
	{{~it.data:p:index}}
<div class="itemdiv commentdiv">
<div class="body">
	<div class="name blue" >
	{{=p.createUserName||''}}
	</div>
	<div class="time">
	<i class="ace-icon fa fa-clock-o"></i>&nbsp;
	<span class="blue">{{=new Date(p.createTime).toChString(true) ||''}}</span>
	</div>
	<div class="text" style="display:block;word-break:break-all;word-wrap: break-word;">
	<i class="ace-icon fa fa-quote-left"></i>
	{{=p.content}}
	<i class="ace-icon fa fa-quote-right" style="color:#DCE3ED;margin-left: 4px"></i>
	</div>
</div>
	<div class="tools">
	<div class="action-buttons bigger-125">
	<a href="javascript:deleteNotes('{{=p.id}}');">
	<i class="ace-icon fa fa-trash-o red"></i>
	</a>
	</div>
	</div>
</div>
	{{~}}
	{{? !it||!it.data||!it.data.length}}
<div class="itemdiv commentdiv">
<div class="body">
<div class="text">
    &nbsp;No notes
</div>
</div>
</div>
	{{?}}
</div>
</script>
<script id="step_temp_1" type="text/x-dot-template">
<form class="form-horizontal" id="submit-form1">
<input type="hidden" name="id" id="id" value="{{=it.id}}"/>
<input type="hidden" name="userId" id="userId" value="{{=it.userId}}"/>
<input type="hidden" name="salesId" id="salesId" value="{{=it.salesId||'<%=SessionUtil.getCurrentUserId() %>'||''}}"/>
<input type="hidden" name="createUserId" id="createUserId" value="{{=it.createUserId||''}}" readonly="readonly"/>
<input type="hidden" name="createTime" id="createTime" value="{{=new Date(it.createTime).toChString(true)||''}}" readonly="readonly"/>
<div class="col-sm-6">
	<div class="form-group" style="margin-left:8.3%">
	<label class="col-sm-12"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Basic</h5></label>
	</div>
	
	<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="">Sales Name:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="salesName" id="salesName" class="form-control col-sm-12" value="{{=it.salesName||'<%=SessionUtil.getCurrentUserNickName() %>'||''}}" readonly="readonly"/>
		</div>
	</div>
	</div>

	<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="category">Category:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="category" id="category" class="form-control col-sm-12" value="{{=it.category || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	</div>


<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="">Makes:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="makes" id="makes" class="form-control col-sm-12" value="{{=it.makes || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="model">Model:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="model" id="model" class="form-control col-sm-12" value="{{=it.model || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="carDescription">Car Description:</label>
	<div class="col-sm-8">
	<div class="clearfix">
	<textarea class="form-control limited" id="carDescription"  name="carDescription" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.carDescription || ''}}</textarea>
	</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right">Is New:</label>
	<div class="col-sm-8">
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="isNew" value="1" type="radio" class="ace" {{? it.readonly && 1 != it.isNew}}disabled="disabled"{{?}} {{? !it.isNew || 1 == it.isNew}}checked="checked"{{?}}  {{? it.readonly}}readonly="readonly"{{?}}/>
				<span class="lbl">New</span>
			</label>
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="isNew" value="0" type="radio" class="ace" {{? it.readonly && 0 != it.isNew}}disabled="disabled"{{?}} {{? 0 == it.isNew}}checked="checked"{{?}}  {{? it.readonly}}readonly="readonly"{{?}}/>
				<span class="lbl">Used</span>
			</label>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="years">Years:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="years" id="years" class="form-control col-sm-12" value="{{=it.years || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="mileages">Mileages:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="mileages" id="mileages" class="form-control col-sm-12" value="{{=it.mileages || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="msrp">MSRP:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="msrp" id="msrp" class="form-control col-sm-12" value="{{=it.msrp || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="exterior">Exterior:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="exterior" id="exterior" class="form-control col-sm-12" value="{{=it.exterior || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="interior">Interior:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="interior" id="interior" class="form-control col-sm-12" value="{{=it.interior || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="color">Color:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="color" id="color" class="form-control col-sm-12" value="{{=it.color || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
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
</div>

<div class="col-sm-6">
	<div class="form-group" style="margin-left:8.3%">
	<label class="col-sm-12"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Notes</h5></label>
</div>
	<div class="comments" id="notesdiv"></div>
	<div class="center">
	{{? !it.readonly}}<a href="#" onclick="javascript:addNote();"><i class="ace-icon fa fa-comments-o"></i>&nbsp; Add Note</a> {{?}}
	</div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Finance</h5></label>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="financeDownPayment">Down Payment:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="financeDownPayment" id="financeDownPayment" class="form-control col-sm-12" value="{{=it.financeDownPayment || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="financeRate">Rate:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="financeRate" id="financeRate" class="form-control col-sm-12" value="{{=it.financeRate || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="financeTerms">Terms:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="financeTerms" id="financeTerms" class="form-control col-sm-12" value="{{=it.financeTerms || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="financeMonthlyPay">Monthly Pay:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="financeMonthlyPay" id="financeMonthlyPay" class="form-control col-sm-12" value="{{=it.financeMonthlyPay || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="financeComments">Comments:</label>
	<div class="col-sm-4">
	<div class="clearfix">
	<textarea class="form-control limited" id="financeComments"  name="financeComments" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.financeComments || ''}}</textarea>
	</div>
	</div>
</div>

<div class="form-group" style="margin-left:8.3%">
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Additional Information</h5></label>
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
	<label class="col-sm-1 control-label no-padding-right" for="driversLicense">Driver License:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="driversLicense" id="driversLicense" class="form-control col-sm-12" value="{{=it.driversLicense || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>


<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Lease</h5></label>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="leaseDownPayment">Down Payment:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="leaseDownPayment" id="leaseDownPayment" class="form-control col-sm-12" value="{{=it.leaseDownPayment || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="terms">Terms:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="leaseTerms" id="leaseTerms" class="form-control col-sm-12" value="{{=it.leaseTerms || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="leaseMonthlyPay">Monthly Pay:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="leaseMonthlyPay" id="leaseMonthlyPay" class="form-control col-sm-12" value="{{=it.leaseMonthlyPay || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="leaseComments">Comments:</label>
	<div class="col-sm-4">
	<div class="clearfix">
	<textarea class="form-control limited" id="leaseComments"  name="leaseComments" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.leaseComments || ''}}</textarea>
	</div>
	</div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Trade In</h5></label>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right">Trade In:</label>
	<div class="col-sm-4">
	<div class="clearfix">
		<label class="line-height-1" style="margin-right: 10px;">
		<input id="tradeIn1" name="tradeIn" value="1" type="radio" class="ace" {{? it.readonly && 1 != it.tradeIn}}disabled="disabled"{{?}}  {{? it.readonly}}readonly="readonly"{{?}} {{? !it.tradeIn||1 == it.tradeIn}}checked="checked"{{?}}/>
		<span class="lbl">Yes</span>
		</label>
		<label class="line-height-1" style="margin-right: 10px;">
		<input id="tradeIn2" name="tradeIn" value="0" type="radio" class="ace" {{? it.readonly && 0 != it.tradeIn}}disabled="disabled"{{?}}  {{? it.readonly}}readonly="readonly"{{?}} {{? 0 == it.tradeIn}}checked="checked"{{?}}/>
		<span class="lbl">No</span>
		</label>
	</div>
	</div>
	<div class="col-sm-5">
		<div class="clearfix">
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right">Mileage:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="tradeInMileage" id="tradeInMileage" class="form-control col-sm-12" value="{{=it.tradeInMileage || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right" for="model">Model:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="tradeInModel" id="tradeInModel" class="form-control col-sm-12" value="{{=it.tradeInModel || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="years">Years:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="tradeInYears" id="tradeInYears" class="form-control col-sm-12" value="{{=it.tradeInYears || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right">Value:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="tradeInValue" id="tradeInValue" class="form-control col-sm-12" value="{{=it.tradeInValue || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right">Exterior:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="tradeInExterior" id="tradeInExterior" class="form-control col-sm-12" value="{{=it.tradeInExterior || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right">Interior:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="tradeInInterior" id="tradeInInterior" class="form-control col-sm-12" value="{{=it.tradeInInterior || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right">Color:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="tradeInColor" id="tradeInColor" class="form-control col-sm-12" value="{{=it.tradeInColor || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label no-padding-right">Makes:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="tradeInMakes" id="tradeInMakes" class="form-control col-sm-12" value="{{=it.tradeInMakes || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="col-sm-12 form-group">
<hr class="no-margin-top"> </hr>
	<div class="col-sm-4 form-group center">
	</div>
	<div class="col-sm-4 form-group center">
		<div class="col-sm-6 form-group center">
			<a class="btn btn-info btn-sm" id="save_btn"  {{? !it.readonly}}onclick="javascript:saveCarSelling();"{{?}}>
				 <i class="ace-icon fa fa-check bigger-110"></i> Save
			</a>
		</div>
		<div class="col-sm-6 form-group center">
			<a class="btn btn-sm btn-default" type="reset" id="delete_btn"  {{? !it.readonly}}onclick="javascript:deleteCarSelling('{{=it.id}}');"{{?}}>
				<i class="ace-icon fa fa-times bigger-110"></i> Delete
			</a>
		</div>
	</div>
	<div class="col-sm-4 form-group center">
	</div>
</div>
</form>
</script>

<script id="add-notes-temp" type="text/x-dot-template">
<form class="form-horizontal" id="add-notes-form">
<div class="form-group">
<input type="hidden" name="userId" value="{{=it.customerId||''}}" class="col-xs-12 col-sm-12"/>
<input type="hidden" name="serviceId" value="{{=it.serviceId||''}}" class="col-xs-12 col-sm-12" />
<input type="hidden" name="createUserId" value="<%=SessionUtil.getCurrentUserId() %>" class="col-xs-12 col-sm-12" />
<label class="col-sm-2 control-label no-padding-right" for="id">Created By:</label>
<div class="col-sm-4">
<input type="text" id="createdBy" name="createdBy" class="col-xs-12 col-sm-12" value="<%=SessionUtil.getCurrentUserNickName() %>" readonly="readonly"/>
</div>
<label class="col-sm-2 control-label no-padding-right" for="id">Create Time:</label>
<div class="col-sm-4">
<input type="text" name="createTime" class="col-xs-12 col-sm-12" value="{{=new Date().toChString(true)||''}}" readonly="readonly"/>
</div>
</div>
<div class="form-group" style="width:600px">
<label class="col-sm-2 control-label no-padding-right" for="id">Service:</label>
<div class="col-sm-10">
<select class="input-large" id="noteservicetype-select" name="noteServiceType" disabled="disabled">
	<option value="1" selected="selected">Car Selling</option>
</select>
</div>
</div>
<div class="form-group">
<label class="col-sm-2 control-label no-padding-right" for="id">Content:</label>
<div class="col-sm-10">
<textarea class="form-control limited" name="content" maxlength="500" {{? it.readonly}}readonly="readonly"{{?}}></textarea>
</div>
</div>
</form>
</script>
