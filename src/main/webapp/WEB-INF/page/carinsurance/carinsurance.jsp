<%@ page import="com.lumei.crm.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script src="<%=request.getContextPath()%>/resources/custjs/carinsurance/carinsurance.js"></script>
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
				<a href="<%=request.getContextPath() %>/cardeal/list?active=cardeal">Car Insurance</a>
			</small>
			</h1>
		</div>
	</div>
	</div>
</div>

<form id="search_form" class="form-horizontal" action="#" hidden="hidden">
<input id="serviceIdSearch" name="serviceIdSearch"  class="form-control" value="<%=SessionUtil.getAttributes("serviceId") %>" hidden="hidden"/>
<input id="customerIdSearch" name="customerIdSearch"  class="form-control" value="<%=SessionUtil.getAttributes("customerId") %>" hidden="hidden"/>
<input id="carDealIdSearch" name="carDealIdSearch"  class="form-control" value="<%=SessionUtil.getAttributes("carDealId") %>" hidden="hidden"/>
</form>

<div class="row">
<div id="profile-content"></div>
</div>

<script id="profile_temp" type="text/x-dot-template">
<form class="form-horizontal" id="submit-form">
<input type="hidden" name="id" id="id" value="{{=it.id||''}}"/>
<input type="hidden" name="salesId" id="salesId" value="{{=it.salesId||''}}"/>
<input type="hidden" name="customerId" id="customerId" value="{{=it.customerId||''}}"/>
<input type="hidden" name="carDealId" id="carDealId" value="{{=it.carDealId||''}}"/>

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
    <label class="col-sm-4 control-label no-padding-right">Case No</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<input type="text" name="caseNo" id="caseNo" class="form-control col-sm-1" value="{{=it.caseNo||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Status</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<select class="form-control" id="status" name="status" {{? it.readonly}}disabled="disabled" readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Effective Date</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="effectiveDate" id="effectiveDate" class="date-timepicker form-control col-sm-1 " value="{{=new Date(it.effectiveDate).toChString()||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Insurance Company</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<input type="text" name="insuranceCompany" id="insuranceCompany" class="form-control col-sm-1" value="{{=it.insuranceCompany||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Terms</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<input type="text" name="terms" id="terms" class="form-control col-sm-1" value="{{=it.terms||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">VIN No.</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<input type="text" name="vinNo" id="vinNo" class="form-control col-sm-1" value="{{=it.vinNo||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Price</label>
	<label class="col-sm-1 control-label no-padding-right">$</label>
    <div class="col-sm-7">
		<div class="clearfix">
    		<input type="text" name="price" id="price" class="form-control col-sm-1 " value="{{=it.price||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
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