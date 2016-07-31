<%@page import="com.lumei.crm.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="<%=request.getContextPath()%>/resources/custjs/customer/profile.js"></script>

<form id="search_form" class="form-horizontal" action="#">
	<div class="row">
		<div class="col-sm-6 form-group">
			<label class="col-sm-2 control-label no-padding-right" for="form-field-1">customerId</label>
			<div class="col-sm-9">
				<input id="customerId" name="customerId"  class="form-control" value="<%=SessionUtil.getAttributes("customerId") %>" readonly="readonly"/>
			</div>
		</div>
	</div>
</form>

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
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="name">Name:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="name" id="name" class="form-control col-sm-12" value="{{=it.name|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right">Gender:</label>
	<div class="col-sm-9">
			<label class="line-height-1" style="margin-right: 10px;">
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
	<label class="col-sm-3 control-label no-padding-right" for="phone">Phone:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="phone" id="phone" class="form-control col-sm-12" value="{{=it.phone|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="name">Email:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="email" id="email" class="form-control col-sm-12" value="{{=it.email|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="university">University:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="email" id="university" class="form-control col-sm-12" value="{{=it.university|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="name">WechatID:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="email" id="wechatId" class="form-control col-sm-12" value="{{=it.wechatId|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="name">Service:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="email" id="wechatId" class="form-control col-sm-12" value="{{=it.wechatId|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right">Status:</label>
	<div class="col-sm-9">
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="status" value="1" type="radio" class="ace" {{? it.readonly && 1 != it.status}}disabled="disabled"{{?}} {{? 1 == it.status}}checked="checked"{{?}}/>
				<span class="lbl">Appointmented</span>
			</label>
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="status" value="2" type="radio" class="ace" {{? it.readonly && 0 != it.status}}disabled="disabled"{{?}} {{? 0 == it.status}}checked="checked"{{?}}/>
				<span class="lbl"> Sold by lumei</span>
			</label>
	</div>
</div>


<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="name">Finace:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="finace" id="finace" class="form-control col-sm-12" value="{{=it.finace|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="name">Cash Deal:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="cashDeal" id="cashDeal" class="form-control col-sm-12" value="{{=it.cashDeal|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="name">Deposit:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="deposit" id="deposit" class="form-control col-sm-12" value="{{=it.deposit|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="name">When to Buy:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="whenToBuy" id="whenToBuy" class="form-control col-sm-12" value="{{=it.whenToBuy|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="name">State:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="state" id="state" class="form-control col-sm-12" value="{{=it.state|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="name">City:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="city" id="city" class="form-control col-sm-12" value="{{=it.city|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="name">ZIP Code:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="zip" id="zip" class="form-control col-sm-12" value="{{=it.zip|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="name">Street:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="street" id="street" class="form-control col-sm-12" value="{{=it.street|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
</form>
</script>
