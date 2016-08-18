<%@ page import="com.lumei.crm.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script src="<%=request.getContextPath()%>/resources/custjs/customer/customer.js"></script>
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
				Profile
			</small>
			</h1>
		</div>
	</div>
	</div>
</div>

<form id="search_form" class="form-horizontal" action="#" hidden="hidden">
<input id="customerId" name="customerId"  class="form-control" value="<%=SessionUtil.getAttributes("customerId") %>" hidden="hidden"/>
</form>

<div class="row">
<div id="profile-content"></div>
</div>

<script id="profile_temp" type="text/x-dot-template">
<form class="form-horizontal" id="submit-form">
<input type="hidden" name="id" id="id" value="{{=it.id}}"/>
<input type="hidden" name="salesId" id="salesId" value="{{=it.salesId||'<%=SessionUtil.getCurrentUserId() %>'||''}}"/>

<div class="col-sm-6">
	<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="name">Customer Name</label>
	<div class="col-sm-8">
    	<div class="clearfix">
    		<input type="text" name="name" id="name" class="form-control col-sm-1 " value="{{=it.name|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
 	</div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Sales Name</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="sales" id="sales" class="form-control col-sm-1 " value="{{=it.sales||'<%=SessionUtil.getCurrentUserNickName() %>'||''}}" readonly="readonly"/>
    	</div>
    </div>
	</div>

	<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="name">Service Types</label>
	<div class="col-sm-6">
    	<div class="clearfix col-sm-11 no-padding-left">
		<select class="form-control" id="service" name="service"/>
		</div>
 	</div>
	<div class="col-sm-2">
    	<div class="clearfix">
    	<a class="btn btn-info btn-sm pull-right" id="addDeal_btn">
        <i class="ace-icon fa fa-briefcase bigger-110"></i> Add Deal
        </a>
		</div>
 	</div>
	</div>

	<div class="form-group">
	  <label class="col-sm-4 control-label no-padding-right">Gender</label>
	  <div class="col-sm-8">
		<select class="form-control" id="gender" name="gender" {{? it.readonly}}disabled="disabled" readonly="readonly"{{?}}>
		<option value="1" {{? 1 == it.gender}}selected="selected"{{?}}>Female</option>
		<option value="0" {{? 0 == it.gender}}selected="selected"{{?}}>Male</option>
		</select>
	  </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">WeChat</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="wechat" id="wechat" class="form-control col-sm-1 " value="{{=it.wechat||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Phone</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="phone" id="phone" class="form-control col-sm-1 " value="{{=it.phone||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>
	
	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Email</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="email" id="email" class="form-control col-sm-1 " value="{{=it.email||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">University</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="university" id="university" class="form-control col-sm-1 " value="{{=it.university||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>
			
 	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Major</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="major" id="major" class="form-control col-sm-1 " value="{{=it.major||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

 	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Address</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="address" id="address" class="form-control col-sm-1 " value="{{=it.address||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
    	</div>
    </div>
	</div>

 	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Zip Code</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="zipCode" id="zipCode" class="form-control col-sm-1 " value="{{=it.zipCode||''}}" {{? it.readonly}}readonly="readonly"{{?}}/>
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

<!-- transaction -->
<div class="form-group">
<div class="col-sm-10" id="transaction-content" style="margin-left:8.3%">
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

<script id="transaction_grid_temp" type="text/x-dot-template">
    <table id="sample-table-2" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
    <th>Date</th>
    <th>Service Type</th>
    <th>Sales</th>
	<th>Detail</th>
    </tr>
    </thead>
    <tbody>
    {{~it.data:p:index}}
    <div class="col-sm-2 form-group center">
    </div>
    <tr>
    <td>{{=new Date(p.createTime).toChString(false) ||''}}</td>
    <td>{{=datadic['serviceType'][p.serviceType]}}</td>
    <td>{{=p.details||''}}</td>
	<td><a href="javascript:viewTran('{{=p.serviceType}}','{{=p.serviceId}}','{{=p.userId}}');">view</a></td>
    </tr>
    {{~}}
    {{? !it||!it.data||!it.data.length}}
    <tr ><td colspan="12">No Transaction</td></tr>
    {{?}}
    </tbody>
    </table>
</script>

<script id="addDeal-temp" type="text/x-dot-template">
<form class="form-horizontal" action="#">
<div class="row" style="min-width:300px;">
<div class="col-xs-12">
	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Service Type</label>
    <div class="col-sm-8">
		<div class="clearfix">
		<select class="form-control" id="deal-select" name="dealselect"/>
    	</div>
    </div>
	</div>
</div>
</div>
</form>
</script>

<%@ include file="../notes/notes.jsp"%>