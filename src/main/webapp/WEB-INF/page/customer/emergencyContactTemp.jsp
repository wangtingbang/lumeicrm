<%@page import="com.lumei.crm.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="<%=request.getContextPath()%>/resources/custjs/customer/emergencycontact.js"></script>

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
				Emergency Contact
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
			<div class="col-sm-9">
				<input id="customerId" name="customerId"  class="form-control" value="<%=SessionUtil.getAttributes("customerId") %>" readonly="readonly"/>
			</div>
		</div>
	</div>
</form>

<div id="emergency-contact-content"></div>

<script id="step_temp_1" type="text/x-dot-template">
<form class="form-horizontal" id="submit-form1">
<input type="hidden" name="userId" id="userId" value="{{=it.userId}}"/>
<input type="hidden" name="id" id="id" value="{{=it.id}}"/>
<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-10"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Basic Information</h5></label>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="salesName">Sales Name:</label>
	<div class="col-sm-3">
		<div class="clearfix">
			<input type="text" name="salesName" id="salesName" class="form-control col-sm-12" value="{{=it.salesName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-2 control-label no-padding-right" for="category">DOB:</label>
	<div class="col-sm-3">
		<div class="clearfix">
			<input type="text" name="dob" id="dob" class="form-control col-sm-12" value="{{=it.dob || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="activityName">Chinese ID:</label>
	<div class="col-sm-3">
		<div class="clearfix">
			<input type="text" name="chineseId" id="chineseId" class="form-control col-sm-12" value="{{=it.chineseId || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-2 control-label no-padding-right" for="passport">Passport:</label>
	<div class="col-sm-3">
		<div class="clearfix">
			<input type="text" name="passport" id="passport" class="form-control col-sm-12" value="{{=it.passport || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="university">University:</label>
	<div class="col-sm-3">
	<div class="clearfix">
	<input type="text" class="form-control limited" id="university"  name="university" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.university || ''}}"/>
	</div>
	</div>
  <label class="col-sm-2 control-label no-padding-right" for="school">School:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="school"  name="school" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.school || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="department">Department:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="department"  name="department" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.university || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="schoolEmail">School Email:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="schoolEmail"  name="schoolEmail" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.schoolEmail || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="personalEmail">Personal Email:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="personalEmail"  name="personalEmail" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.personalEmail || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="wechatId">Wechat Id:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="wechatId"  name="department" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.wechatId || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-10"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Chinese Address</h5></label>
</div>
<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="chineseHomeAddress">Home Address:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="chineseHomeAddress"  name="chineseHomeAddress" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.chineseHomeAddress || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="chineseHomeZip">Home ZIP:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="chineseHomeZip"  name="chineseHomeZip" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.chineseHomeZip || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="chineseHomeTelephone">Home Telephone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="chineseHomeTelephone"  name="chineseHomeTelephone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.chineseHomeTelephone || ''}}"/>
  </div>
  </div>
	</div>

<div class="form-group"  style="margin-left:8.3%">
	<label class="col-sm-10"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">USA Address</h5></label>
	</div>
<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="usaHomeAddress">Home Address:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="usaHomeAddress"  name="usaHomeAddress" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.usaHomeAddress || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="usaHomeCity">Home City:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="usaHomeCity"  name="usaHomeCity" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.usaHomeCity || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="usaHomeState">Home State:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="usaHomeState"  name="usaHomeState" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.usaHomeState || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="usaHomeZip">Home ZIP:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="usaHomeZip"  name="usaHomeZip" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.usaHomeZip || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="usaHomeTelephone">Home Telephone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="usaHomeTelephone"  name="usaHomeTelephone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.usaHomeTelephone || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="usaHomeCellphone">Home Cellphone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="usaHomeCellphone"  name="usaHomeCellphone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.usaHomeCellphone || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-10"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Parents - Father</h5></label>
</div>
<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="fatherChineseName">Chinese Name:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="fatherChineseName"  name="fatherChineseName" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.fatherChineseName || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="fatherEnglishName">English Name:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="fatherEnglishName"  name="fatherEnglishName" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.fatherEnglishName || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="fatherAddress">Address:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="fatherAddress"  name="fatherAddress" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.fatherAddress || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="fatherTelephone">Telephone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="fatherTelephone"  name="fatherTelephone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.fatherTelephone || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="fatherCellphone">Cellphone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="fatherCellphone"  name="fatherCellphone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.fatherCellphone || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="fatherWechatId">Wechat Id:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="fatherWechatId"  name="fatherWechatId" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.fatherWechatId || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-10"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Parents - Mother</h5></label>
</div>
<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="motherChineseName">Chinese Name:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="motherChineseName"  name="motherChineseName" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.motherChineseName || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="motherEnglishName">English Name:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="motherEnglishName"  name="motherEnglishName" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.motherEnglishName || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="motherAddress">Address:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="motherAddress"  name="motherAddress" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.motherAddress || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="motherTelephone">Telephone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="motherTelephone"  name="motherTelephone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.motherTelephone || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="motherCellphone">Cellphone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="motherCellphone"  name="motherCellphone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.motherCellphone || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="motherWechatId">Wechat Id:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="motherWechatId"  name="motherWechatId" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.motherWechatId || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-10"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Contact - Landlord</h5></label>
</div>
<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="landlordChineseName">Chinese Name:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="landlordChineseName"  name="landlordChineseName" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.landlordChineseName || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="landlordEnglishName">English Name:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="landlordEnglishName"  name="landlordEnglishName" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.landlordEnglishName || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="landlordAddress">Address:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="landlordAddress"  name="landlordAddress" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.landlordAddress || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="landlordCity">City:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="landlordCity"  name="landlordCity" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.landlordCity || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="landlordState">State:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="landlordState"  name="landlordState" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.landlordState || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="landlordZip">ZIP:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="landlordZip"  name="landlordZip" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.landlordZip || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="landlordTelephone">Telephone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="landlordTelephone"  name="landlordTelephone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.landlordTelephone || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="landlordCellphone">Cellphone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="landlordCellphone"  name="landlordCellphone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.landlordCellphone || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="landlordWechatId">Wechat Id:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="landlordWechatId"  name="landlordWechatId" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.landlordWechatId || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-10"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Contact - Roommate</h5></label>
</div>
<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="roommateChineseName">Chinese Name:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="roommateChineseName"  name="roommateChineseName" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.roommateChineseName || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="roommateEnglishName">English Name:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="roommateEnglishName"  name="roommateEnglishName" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.roommateEnglishName || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="roommateAddress">Address:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="roommateAddress"  name="roommateAddress" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.roommateAddress || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="roommateCity">City:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="roommateCity"  name="roommateCity" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.roommateCity || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="roommateState">State:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="roommateState"  name="roommateState" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.roommateState || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="roommateZip">ZIP:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="roommateZip"  name="roommateZip" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.roommateZip || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="roommateTelephone">Telephone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="roommateTelephone"  name="roommateTelephone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.roommateTelephone || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="roommateCellphone">Cellphone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="roommateCellphone"  name="roommateCellphone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.roommateCellphone || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-10"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Contact - Neighbor</h5></label>
</div>
<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="neighborChineseName">Chinese Name:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="neighborChineseName"  name="neighborChineseName" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.neighborChineseName || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="neighborEnglishName">English Name:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="neighborEnglishName"  name="neighborEnglishName" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.neighborEnglishName || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="neighborAddress">Address:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="neighborAddress"  name="neighborAddress" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.neighborAddress || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="neighborCity">City:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="neighborCity"  name="neighborCity" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.neighborCity || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="neighborState">State:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="neighborState"  name="neighborState" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.neighborState || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="neighborZip">ZIP:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="neighborZip"  name="neighborZip" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.neighborZip || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="neighborTelephone">Telephone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="neighborTelephone"  name="neighborTelephone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.neighborTelephone || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="neighborCellphone">Cellphone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="neighborCellphone"  name="neighborCellphone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.neighborCellphone || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-10"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Contact - Other</h5></label>
</div>
<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="otherChineseName">Chinese Name:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="otherChineseName"  name="otherChineseName" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.otherChineseName || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="otherEnglishName">English Name:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="otherEnglishName"  name="otherEnglishName" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.otherEnglishName || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="otherAddress">Address:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="otherAddress"  name="otherAddress" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.otherAddress || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="otherCity">City:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="otherCity"  name="otherCity" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.otherCity || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="otherState">State:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="otherState"  name="otherState" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.otherState || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="otherZip">ZIP:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="otherZip"  name="otherZip" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.otherZip || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="otherTelephone">Telephone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="otherTelephone"  name="otherTelephone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.otherTelephone || ''}}"/>
  </div>
  </div>
  <label class="col-sm-2 control-label no-padding-right" for="otherCellphone">Cellphone:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  <input type="text" class="form-control limited" id="otherCellphone"  name="otherCellphone" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}} value="{{=it.otherCellphone || ''}}"/>
  </div>
  </div>
</div>

<div class="form-group"  style="margin-left:8.3%">
<label class="col-sm-10"><h5 class="header ligth blue" style="margin-top:0px,margin-bottom:0px">Special Information</h5></label>
</div>
<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="special">Special:</label>
	<div class="col-sm-3">
      <label style="margin-right: 10px; margin-top: 5px;">
      <input id="special" name="special" type="checkbox" class="ace" {{? it.readonly && 1 != it.service/100}}disabled="disabled"{{?}} {{? 1 == it.service/100%100}}checked="checked"{{?}}/>
      <span class="lbl">Yes</span>
      </label>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right" for="specialComment">Comment:</label>
  <div class="col-sm-3">
  <div class="clearfix">
  	<textarea class="form-control limited" id="specialComment"  name="specialComment" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.specialComment || ''}}</textarea>
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
			<a class="btn btn-info btn-sm" id="save_btn" onclick="javascript:saveEmergencyContact();">
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
