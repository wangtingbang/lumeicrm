<script id="list_notes_temp" type="text/x-dot-template">
{{~it.data:p:index}}
<div class="itemdiv commentdiv">
	<div class="body">
	   	<div class="name blue" >
	    	{{=p.createUserName||''}}
	    </div>
	    <div class="time">
			{{=datadic['serviceType'][p.noteServiceType]||'profile'}}
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
</script>

<script id="add-notes-temp" type="text/x-dot-template">
<form class="form-horizontal" id="add-notes-form">
<div class="form-group">
<input type="hidden" name="userId" value="{{=it.customerId||''}}" class="col-xs-12 col-sm-12"/>
<input type="hidden" name="serviceId" value="{{=it.serviceId||''}}" class="col-xs-12 col-sm-12" />
<input type="hidden" name="noteServiceType" value="{{=it.serviceType}}" class="col-xs-12 col-sm-12" />
</div>
<div class="form-group" style="min-width:500px;">
<label class="col-sm-2 control-label no-padding-right">Content:</label>
<div class="col-sm-10">
<textarea class="form-control limited" name="content" maxlength="500" {{? it.readonly}}readonly="readonly"{{?}}></textarea>
</div>
</div>
</form>
</script>