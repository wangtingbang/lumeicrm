<%@page import="com.lumei.crm.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="<%=request.getContextPath()%>/resources/custjs/customer/profile.js"></script>

<div class="page-header">
	<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-20">
			<h1>
			<small>
				<i class="ace-icon fa fa-home home-icon"></i>
				<a href="<%=request.getContextPath() %>/">Home</a>
				<i class="ace-icon fa fa-angle-double-right"></i>
				Customers Profile
			</small>
			</h1>
		</div>
	</div>
	</div>
</div>

<form id="search_form" class="form-horizontal" action="#" hidden="hidden">
	<div class="row">
		<div class="col-sm-6 form-group">
			<label class="col-sm-1 control-label no-padding-right" for="form-field-1">customerId</label>
			<div class="col-sm-9">
				<input id="customerId" name="customerId"  class="form-control" value="<%=SessionUtil.getAttributes("customerId") %>" readonly="readonly"/>
			</div>
		</div>
	</div>
</form>
<div id="profile-content"></div>
<div id="notes-content"></div>
<script id="create_temp" type="text/x-dot-template">
<div class="widget-box" style="{{? 'firefox'==_browser}}min-width: 600px;{{?}} {{? 'webkit'==_browser || 'msie' == _browser}}max-width: 600px;{{?}}" >
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">{{=it.title}}</h4>
			<button type="button" class="close pull-right" data-dismiss="modal" aria-hidden="true" style="font-size: 32px; margin: 3px;">&times;</button>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div id="fuelux-wizard" data-target="#step-container">
				<ul class="wizard-steps">
					<li data-target="#step1" class="active">
						<span class="step">1</span>
						<span class="title">Profile</span>
					</li>
				</ul>
			</div>

			<hr />

			<div class="step-content pos-rel" id="step-container">
				<div class="step-pane active" id="step1">{{=it.page1}}</div>
				<div class="step-pane" id="step2">{{=it.page2}}</div>
				<div class="step-pane" id="step3">{{=it.page3}}</div>
			</div>

			<hr />
			<div class="wizard-actions">
				<button class="btn btn-sm btn-prev" style="margin-right: 3px;">
					<i class="ace-icon fa fa-arrow-left"></i>Back
				</button>

				<button class="btn btn-sm btn-success btn-next" data-last="OK">Next
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</button>
					<a style="margin-left: 20px;" data-dismiss="modal" href="#">Close</a>
			</div>
		</div>
	</div>
</div>
</script>

<script id="step_temp_1" type="text/x-dot-template">
<form class="form-horizontal" id="submit-form1">
<input type="hidden" name="id" id="id" value="{{=it.id}}"/>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Basic Information</h5></label>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="name">Name:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="name" id="name" class="form-control col-sm-1 " value="{{=it.name|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
  <label class="col-sm-1 control-label no-padding-right">Gender:</label>
  <div class="col-sm-4">
      <label class="line-height-1" style="margin-right: 10px; margin-top: 5px;">
        <input name="gender" value="1" type="radio" class="ace" {{? it.readonly && 1 != it.gender}}disabled="disabled"{{?}} {{? 1 == it.gender}}checked="checked"{{?}}/>
        <span class="lbl"> F</span>
      </label>
      <label class="line-height-1" style="margin-right: 10px;">
        <input name="gender" value="0" type="radio" class="ace" {{? it.readonly && 0 != it.gender}}disabled="disabled"{{?}} {{? 0 == it.gender}}checked="checked"{{?}}/>
        <span class="lbl"> M</span>
      </label>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="phone">Phone:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="phone" id="phone" class="form-control col-sm-1 " value="{{=it.phone|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
  <label class="col-sm-1 control-label no-padding-right" for="name">Email:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="email" id="email" class="form-control col-sm-1 " value="{{=it.email|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="university">University:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="email" id="university" class="form-control col-sm-1 " value="{{=it.university|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
  <label class="col-sm-1 control-label no-padding-right" for="name">Wechat ID:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="wechatId" id="wechatId" class="form-control col-sm-1 " value="{{=it.wechatId|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
</div>



<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="university">Created By:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="email" id="university" class="form-control col-sm-1 " value="{{=it.university|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
  <label class="col-sm-1 control-label no-padding-right" for="name">Updated By:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="wechatId" id="wechatId" class="form-control col-sm-1 " value="{{=it.wechatId|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="university">Sales:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="email" id="university" class="form-control col-sm-1 " value="{{=it.university|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Address</h5></label>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="state">State:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="state" id="state" class="form-control col-sm-1 " value="{{=it.state|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
  <label class="col-sm-1 control-label no-padding-right" for="name">City:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="city" id="city" class="form-control col-sm-1 " value="{{=it.city|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="name">ZIP Code:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="zipCode" id="zipCode" class="form-control col-sm-1 " value="{{=it.zipCode|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
  <label class="col-sm-1 control-label no-padding-right" for="name">Street:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="street" id="street" class="form-control col-sm-1 " value="{{=it.street|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Servces</h5></label>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="name">Finance:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="finance" id="finance" class="form-control col-sm-1 " value="{{=it.finance|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
  <label class="col-sm-1 control-label no-padding-right" for="name">Cash Deal:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="cashDeal" id="cashDeal" class="form-control col-sm-1 " value="{{=it.cashDeal|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="name">Deposit:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="deposit" id="deposit" class="form-control col-sm-1 " value="{{=it.deposit|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
  <label class="col-sm-1 control-label no-padding-right" for="name">When to Buy:</label>
  <div class="col-sm-4">
    <div class="clearfix">
      <input type="text" name="whenToBuy" id="whenToBuy" class="form-control col-sm-1 " value="{{=it.whenToBuy|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="name">Service:</label>
  <div class="col-sm-9">
      <label style="margin-right: 10px; margin-top: 5px;">
      <input id="form-search-status-unconfirm" name="form-search-status-unconfirm" type="checkbox" class="ace" {{? it.readonly && 1 != it.service/100}}disabled="disabled"{{?}} {{? 1 == it.service/100%100}}checked="checked"{{?}}/>
      <span class="lbl">Car Buying&nbsp;&nbsp;</span>
      <span class="lbl"><a href="getCarBuying?customerId={{=it.id }}&customerName={{=it.name}}" target="_blank">view</a></span>
      </label>
      <label style="margin-right: 10px; margin-top: 5px;">
      <input id="form-search-status-confirm" name="form-search-status-confirm" type="checkbox" class="ace" />
      <span class="lbl">Emergency Contact&nbsp;&nbsp;</span>
      <span class="lbl"><a href="getEmergencyContact?customerId={{=it.id }}&customerName={{=it.name}}" target="_blank">view</a></span>
      </label>
      <label style="margin-right: 10px; margin-top: 5px;">
      <input id="form-search-status-failed" name="form-search-status-failed" type="checkbox" class="ace" disabled="disabled" />
      <span class="lbl">Car Selling&nbsp;&nbsp;</span>
      </label>
      <label style="margin-right: 10px; margin-top: 5px;">
      <input id="form-search-status-paied" name="form-search-status-paied" type="checkbox" class="ace" disabled="disabled" />
      <span class="lbl">Car Rental&nbsp;&nbsp;</span>
      </label>
      <label style="margin-right: 10px; margin-top: 5px;">
      <input id="form-search-status-paied" name="form-search-status-paied" type="checkbox" class="ace" disabled="disabled"/>
      <span class="lbl">Cellphone&nbsp;&nbsp;</span>
      </label>
      <label style="margin-right: 10px; margin-top: 5px;">
      <input id="form-search-status-paied" name="form-search-status-paied" type="checkbox" class="ace" disabled="disabled"/>
      <span class="lbl">Airport Pickup&nbsp;&nbsp;</span>
      </label>
      <label style="margin-right: 10px; margin-top: 5px;">
      <input id="form-search-status-paied" name="form-search-status-paied" type="checkbox" class="ace" disabled="disabled"/>
      <span class="lbl">Temporary House&nbsp;&nbsp;</span>
      </label>

      <label style="margin-right: 10px; margin-top: 5px;">
      <input id="form-search-status-paied" name="form-search-status-paied" type="checkbox" class="ace" disabled="disabled"/>
      <span class="lbl">AIM&nbsp;&nbsp;</span>
      </label>
  </div>
	</div>
	<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right">Status:</label>
  <div class="col-sm-9">
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="form-status" name="form-status" type="radio" class="ace" {{? it.readonly && 1 != it.status}}disabled="disabled"{{?}} {{? 1 == it.status}}checked="checked"{{?}}/>
    <span class="lbl">Appointmented&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="form-status" name="form-status" type="radio" class="ace" />
    <span class="lbl">Sold by Lumei&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="form-status" name="form-status" type="radio" class="ace" />
    <span class="lbl">Buy from Other&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="form-status" name="form-status" type="radio" class="ace" />
    <span class="lbl">No Response&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="form-status" name="form-status" type="radio" class="ace" />
    <span class="lbl">Still in the Market&nbsp;&nbsp;</span>
    </label>
  </div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Notes&nbsp;&nbsp;&nbsp;&nbsp;
<a href="#" onclick="javascript:addNote();">+ Add Note</a>
</h5></label>
</div>

<div class="form-group" style="margin-left:8.3%">
<div class="col-xs-11">
<table id="sample-table-1" class="table table-striped table-bordered table-hover">
<thead>
	<div class="col-sm-2 form-group center">
	</div>
	<tr>
		<th>Service Type </th>
		<th>Created By</th>
		<th>Create Time</th>
		<th>Content</th>
		<th>Operation</th>
	</tr>
</thead>
<tbody>
{{~it.notes :p:index}}
	<div class="col-sm-2 form-group center">
	</div>
	<tr>
		<td>{{=p.noteServiceType}}</td>
		<td>{{=p.commitUserId}}</td>
		<td>{{=p.commitTime }}</td>
		<td>{{=p.content }}</td>
		<td><a href="getProfile?customerId={{=p.id }}">Detaill</a></td>
	</tr>
{{~}}
{{? !it||!it.notes||!it.notes.length}}
<tr ><td colspan="12">No notes</td></tr>
{{?}}
</tbody>
</table>
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
			<a class="btn btn-info btn-sm" id="save_btn">
				 <i class="ace-icon fa fa-check bigger-110"></i> Save
			</a>
		</div>
		<div class="col-sm-6 form-group center">
			<a class="btn btn-sm btn-default" type="reset" id="create_btn">
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
	<label class="col-sm-2 control-label no-padding-right" for="id">Created By:</label>
	<div class="col-sm-4">
		<input type="text" id="createdBy" name="createdBy" class="col-xs-12 col-sm-12" value=""/>
	</div>
	<label class="col-sm-2 control-label no-padding-right" for="id">Create Time:</label>
	<div class="col-sm-4">
		<input type="text" id="createTime" name="createTime" class="col-xs-12 col-sm-12" value=""/>
	</div>
</div>
<div class="form-group" style="width:600px">
	<label class="col-sm-2 control-label no-padding-right" for="id">Service:</label>
	<div class="col-sm-10">
		<select class="input-medium" id="noteservicetype-select" name="noteServiceType" {{? it.readonly }}disabled="disabled"{{?}}>
			{{~it.datadic.serviceType :p:index}}
				<option value="{{=p.key }}" {{? it.service == p.key}}selected="selected"{{?}}>
					{{=p.value }}
				</option>
			{{~}}
		</select>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="id">Content:</label>
	<div class="col-sm-10">
		  	<textarea class="form-control limited" id="content"  name="specialComment" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.specialComment || ''}}</textarea>
	</div>
</div>
</form>
</script>
