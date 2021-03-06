<%@page import="com.lumei.crm.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script src="<%=request.getContextPath()%>/resources/custjs/customer/profile.js"></script>
<script src="<%=request.getContextPath()%>/resources/custjs/customer/notemgr.js"></script>

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
				<input id="customerId" name="customerId"  class="form-control" value="<%=SessionUtil.getAttributes("customerId") %>" readonly="readonly"/>
</form>

<div class="row">
<div id="profile-content"></div>
</div>

    <script id="notes_temp" type="text/x-dot-template">
	{{~it.data:p:index}}
<div class="itemdiv commentdiv">
<div class="body">
   	<div class="name blue" >
    	{{=p.createUserName||''}}
    </div>
    <div class="time">
		{{=datadic['serviceType'][p.noteServiceType]}}
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<i class="ace-icon fa fa-clock-o"></i>
    	<span class="blue">{{=new Date(p.createTime).toChString(true) ||''}}</span>
    </div>
    <div class="text" style="display:block;word-break:break-all;word-wrap: break-word;">
    	<i class="ace-icon fa fa-quote-left"></i>
    	{{=p.content}}
		<i class="ace-icon fa fa-quote-right" style="color:#DCE3ED;margin-left: 4px"></i>
    </div>
</div>
    {{? !p.readonly}}
    <div class="tools">
    	<div class="action-buttons bigger-125">
    	<a href="javascript:deleteNotes('{{=p.id}}');">
    	<i class="ace-icon fa fa-trash-o red"></i>
    	</a>
    	</div>
	</div>
    {{?}}
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
<input type="hidden" name="salesId" id="salesId" value="{{=it.salesId||'<%=SessionUtil.getCurrentUserId() %>'||''}}"/>
<input type="hidden" name="customernamehidden" id="customernamehidden" class="form-control col-sm-1 " value="{{=it.name|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>

<div class="col-sm-6">
	<div class="form-group"  style="margin-left:16.6%">
	<label class="col-sm-12"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Basic</h5></label>
	</div>
	
	<div class="form-group">
	<label class="col-sm-4 control-label no-padding-right" for="name">Customer Name:</label>
	<div class="col-sm-8">
    <div class="clearfix">
    <input type="text" name="name" id="name" class="form-control col-sm-1 " value="{{=it.name|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
 	</div>
	</div>
	
	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right" for="name">Potential Buying Date:</label>
    <div class="col-sm-4">
    <div class="clearfix">
    <input id="potentialBuyingDate" class="date-timepicker form-control col-sm-12 " type="text" name="potentialBuyingDate" value="{{=new Date(it.potentialBuyingDate).toChString(false)|| '' }}" {{? it.readonly}}disabled="disabled"{{?}}/>
    </div>
    </div>
	</div>

<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Rating:</label>
    <div class="col-sm-8">
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="ratingA" name="rating" type="radio" value="1" class="ace" {{? it.readonly}}disabled="disabled"{{?}} {{? !it.rating||1 == it.rating}}checked="checked"{{?}}/>
    <span class="lbl">A&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="ratingB" name="rating" type="radio" value="2" class="ace" {{? it.readonly}}disabled="disabled"{{?}} {{? 2 == it.rating}}checked="checked"{{?}} />
    <span class="lbl">B&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="ratingC" name="rating" type="radio" value="3" class="ace" {{? it.readonly}}disabled="disabled"{{?}} {{? 3 == it.rating}}checked="checked"{{?}}/>
    <span class="lbl">C&nbsp;&nbsp;</span>
    </label>
    </div>
</div>

    <div class="form-group">
    <label class="col-sm-4 control-label no-padding-right" for="name">Service:</label>
    <div class="col-sm-8">
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="service-car-selling-checkbox" name="service-car-selling-checkbox" type="checkbox" class="ace" {{? it.readonly}}readonly="readonly"{{?}} {{? it.readonly}}disabled="disabled"{{?}} {{? it.serviceInfo&&it.serviceInfo.s1&&it.serviceInfo.s1==1}}checked="checked"{{?}}>
    <span class="lbl">Car Selling&nbsp;&nbsp;</span>
    </label>

    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="service-emergency-contact-checkbox" name="service-emergency-contact-checkbox" type="checkbox" class="ace" {{? it.readonly}}readonly="readonly"{{?}} {{? it.readonly}}disabled="disabled"{{?}} {{? it.serviceInfo&&it.serviceInfo.s2&&it.serviceInfo.s2==1}}checked="checked"{{?}}/>
    <span class="lbl">Emergency Contact&nbsp;&nbsp;</span>
    </label>

    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="form-search-status-failed" name="form-search-status-failed" type="checkbox" class="ace" disabled="disabled" />
    <span class="lbl">Car Buying&nbsp;&nbsp;</span>
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
    </label>
    </div>
    </div>

	<div class="form-group">
    <label class="col-sm-4 control-label no-padding-right">Sales:</label>
    <div class="col-sm-8">
		<div class="clearfix">
    		<input type="text" name="sales" id="sales" class="form-control col-sm-1 " value="{{=it.sales||'<%=SessionUtil.getCurrentUserNickName() %>'||''}}" readonly="readonly"/>
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
    <label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Personal</h5></label>
    </div>
<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right">Gender:</label>
  <div class="col-sm-4">
      <label class="line-height-1" style="margin-right: 10px; margin-top: 5px;">
        <input name="gender" value="1" type="radio" class="ace" {{? it.readonly}}disabled="disabled"{{?}} {{? !it.gender|| 1 == it.gender}}checked="checked"{{?}} {{? it.readonly}}readonly="readonly"{{?}}/>
        <span class="lbl"> F</span>
      </label>
      <label class="line-height-1" style="margin-right: 10px;">
        <input name="gender" value="0" type="radio" class="ace" {{? it.readonly}}disabled="disabled"{{?}} {{? 0 == it.gender}}checked="checked"{{?}} {{? it.readonly}}readonly="readonly"{{?}}/>
        <span class="lbl"> M</span>
      </label>
  </div>
</div>
<div class="form-group"><div class="col-sm-6"/></div>
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
    <input type="text" name="university" id="university" class="form-control col-sm-1 " value="{{=it.university|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
    </div>
    <label class="col-sm-1 control-label no-padding-right" for="wechatId">Wechat:</label>
    <div class="col-sm-4">
    <div class="clearfix">
    <input type="text" name="wechatId" id="wechatId" class="form-control col-sm-1 " value="{{=it.wechatId|| '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
    </div>
    </div>
    </div>
    </div>

    <div class="form-group"  style="margin-left:8.3%">
	<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Address</h5></label>
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
<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Car Selling Info</h5></label>
</div>

	<div class="form-group">
  <label class="col-sm-2 control-label no-padding-right">Car Selling Status:</label>
  <div class="col-sm-9">
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="status1" name="carSellingStatus" type="radio" value="1" class="ace" {{? it.readonly}}readonly="readonly"{{?}} {{? it.readonly}}disabled="disabled"{{?}} {{? !it.status||1 == it.status}}checked="checked"{{?}}/>
    <span class="lbl">Appointmented&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="status2" name="carSellingStatus" type="radio" value="2" class="ace" {{? it.readonly}}readonly="readonly"{{?}} {{? it.readonly}}disabled="disabled"{{?}} {{? 2 == it.status}}checked="checked"{{?}} />
    <span class="lbl">Sold by Lumei&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="status3" name="carSellingStatus" type="radio" value="3" class="ace" {{? it.readonly}}readonly="readonly"{{?}} {{? it.readonly}}disabled="disabled"{{?}} {{? 3 == it.status}}checked="checked"{{?}}/>
    <span class="lbl">Buy from Other&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="status4" name="carSellingStatus" type="radio" value="4" class="ace" {{? it.readonly}}readonly="readonly"{{?}} {{? it.readonly}}disabled="disabled"{{?}} {{? 4 == it.status}}checked="checked"{{?}}/>
    <span class="lbl">No Response&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="status5" name="carSellingStatus" type="radio" value="5" class="ace" {{? it.readonly}}readonly="readonly"{{?}} {{? it.readonly}}disabled="disabled"{{?}} {{? 5 == it.status}}checked="checked"{{?}} />
    <span class="lbl">Still in the Market&nbsp;&nbsp;</span>
    </label>
  </div>
</div>
    <!-- finance status-->
    <div class="form-group">
    <label class="col-sm-2 control-label no-padding-right">Finance Status:</label>
    <div class="col-sm-9">
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="financeStatus1" name="financeStatus" type="radio" value="1" class="ace" {{? it.readonly}}readonly="readonly"{{?}} {{? it.readonly}}disabled="disabled"{{?}} {{? !it.financeStatus||1 == it.financeStatus}}checked="checked"{{?}}/>
    <span class="lbl">Pending&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="financeStatus2" name="financeStatus" type="radio" value="2" class="ace" {{? it.readonly}}readonly="readonly"{{?}} {{? it.readonly}}disabled="disabled"{{?}} {{? 2 == it.financeStatus}}checked="checked"{{?}} />
    <span class="lbl">Approved&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="financeStatus3" name="financeStatus" type="radio" value="3" class="ace" {{? it.readonly}}readonly="readonly"{{?}} {{? it.readonly}}disabled="disabled"{{?}} {{? 3 == it.financeStatus}}checked="checked"{{?}}/>
    <span class="lbl">Unqualified&nbsp;&nbsp;</span>
    </label>
    </div>
    </div>
    <!-- finance status end-->

    <!-- finance status-->
    <div class="form-group">
    <label class="col-sm-2 control-label no-padding-right">Lease Status:</label>
    <div class="col-sm-9">
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="leaseStatus1" name="leaseStatus" type="radio" value="1" class="ace" {{? it.readonly}}readonly="readonly"{{?}} {{? it.readonly}}disabled="disabled"{{?}} {{? !it.leaseStatus||1 == it.leaseStatus}}checked="checked"{{?}}/>
    <span class="lbl">Pending&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="leaseStatus2" name="leaseStatus" type="radio" value="2" class="ace" {{? it.readonly}}readonly="readonly"{{?}} {{? it.readonly}}disabled="disabled"{{?}} {{? 2 == it.leaseStatus}}checked="checked"{{?}} />
    <span class="lbl">Approved&nbsp;&nbsp;</span>
    </label>
    <label style="margin-right: 10px; margin-top: 5px;">
    <input id="leaseStatus3" name="leaseStatus" type="radio" value="3" class="ace" {{? it.readonly}}readonly="readonly"{{?}} {{? it.readonly}}disabled="disabled"{{?}} {{? 3 == it.leaseStatus}}checked="checked"{{?}}/>
    <span class="lbl">Unqualified&nbsp;&nbsp;</span>
    </label>
    </div>
    </div>
    <!-- finance status end-->

    <div class="form-group"  style="margin-left:8.3%">
	<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Others</h5></label>
	</div>

    <div class="form-group">
    <label class="col-sm-2 control-label no-padding-right">Created By:</label>
    <div class="col-sm-4">
    	<div class="clearfix">
    	<input type="hidden" name="createUserId" id="createUserId" value="{{=it.createUserId||'<%=SessionUtil.getCurrentUserId() %>'||''}}" readonly="readonly"/>
    	<input type="hidden" name="createTime" id="createTime" value="{{=new Date(it.createTime).toChString(true)||''}}" readonly="readonly"/>
    	<input type="text" name="createUserName" id="createUserName" class="form-control col-sm-1 " value="{{=it.createUserName||'<%=SessionUtil.getCurrentUserNickName() %>'||''}}" readonly="readonly"/>
    	</div>
    </div>
    <label class="col-sm-1 control-label no-padding-right" for="name">Updated By:</label>
    <div class="col-sm-4">
    	<div class="clearfix">
    	<input type="hidden" name="updateUserId" id="updateUserId" value="{{=it.updateUserId||'<%=SessionUtil.getCurrentUserId() %>'||''}}" readonly="readonly"/>
    	<input type="text" name="updateUserName" id="updateUserName" class="form-control col-sm-1 " value="{{=it.updateUserName||'<%=SessionUtil.getCurrentUserNickName() %>'||''}}" readonly="readonly"/>
    	</div>
    </div>
    </div>
<div class="form-group"  style="margin-left:8.3%">
	<label class="col-sm-11"><h5 class="header ligth blue" style="margin-top:0px;margin-bottom:0px">Transaction&nbsp;&nbsp;&nbsp;&nbsp;
    {{? !it.readonly}}<a href="javascript:addTransaction('{{=it.id}}','{{=it.name }}');"><i class="ace-icon fa fa-credit-card"></i>&nbsp; Create Transaction</i></a>{{?}}</h5></label>
</div>
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
			<a class="btn btn-sm btn-default" type="reset" id="create_btn"  {{? !it.readonly}}onclick="javascript:deleteProfile('{{=it.id}}');"{{?}}>
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
    <th>Service Type</th>
    <th>Transaction Date</th>
    <th>Operation</th>
    </tr>
    </thead>
    <tbody>
    {{~it.data:p:index}}
    <div class="col-sm-2 form-group center">
    </div>
    <tr>
    <td>{{=datadic['serviceType'][p.serviceType]}}</td>
    <td>{{=new Date(p.createTime).toChString(false) ||''}}</td>
    <td><a href="javascript:viewTran('{{=p.serviceType}}','{{=p.serviceId}}');">Detail</a></td>
    </tr>
    {{~}}
    {{? !it||!it.data||!it.data.length}}
    <tr ><td colspan="12">No Transaction</td></tr>
    {{?}}
    </tbody>
    </table>
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
<select class="input-large" id="noteservicetype-select" name="noteServiceType" {{? it.readonly }}disabled="disabled"{{?}}>
    <option value="1" >Car Selling</option>
    <option value="2" >Emergency Contact</option>
    <option value="3" >Car Buying</option>
    <option value="4" >Car Rental</option>
    <option value="5" >Cellphone</option>
    <option value="6" >Airport Pickup</option>
    <option value="7" >Temporary House</option>
    <option value="8" >AIM</option>
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


<script id="add-tran-temp" type="text/x-dot-template">
<form class="form-horizontal" id="add-notes-form">
<div class="form-group" style="width:600px">
<label class="col-sm-2 control-label no-padding-right" for="id">Service:</label>
<div class="col-sm-10">
<select class="input-large" id="tran-servicetype-select" name="tranServiceType" {{? it.readonly }}disabled="disabled"{{?}}>
<option value="1" >Car Selling</option>
<option value="2" >Emergency Contact</option>
<option value="3" >Car Buying</option>
<option value="4" >Car Rental</option>
<option value="5" >Cellphone</option>
<option value="6" >Airport Pickup</option>
<option value="7" >Temporary House</option>
<option value="8" >AIM</option>
</select>
</div>
</div>
</form>
</script>
