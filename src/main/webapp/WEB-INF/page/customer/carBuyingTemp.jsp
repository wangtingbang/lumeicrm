<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
													<span class="title">Car Buying</span>
												</li>

												<li data-target="#step2">
													<span class="step">2</span>
													<span class="title"></span>
												</li>

												<li data-target="#step3">
													<span class="step">3</span>
													<span class="title"></span>
												</li>
											</ul>
										</div>

										<hr />

										<div class="step-content pos-rel" id="step-container">
											<div class="step-pane active" id="step1">
{{=it.page1}}
											</div>
											<div class="step-pane" id="step2">
{{=it.page2}}
											</div>

											<div class="step-pane" id="step3">
{{=it.page3}}
											</div>
										</div>

										<hr />
										<div class="wizard-actions">
											<button class="btn btn-sm btn-prev" style="margin-right: 3px;">
												<i class="ace-icon fa fa-arrow-left"></i>
												Back
											</button>

											<button class="btn btn-sm btn-success btn-next" data-last="OK">
												Next
												<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
											</button>

											<a style="margin-left: 20px;" data-dismiss="modal" href="#">
												Close
											</a>
										</div>
									</div><!-- /.widget-main -->
								</div><!-- /.widget-body -->
							</div>
</script>

<script id="step_temp_1" type="text/x-dot-template">
<form class="form-horizontal" id="submit-form1">
<input type="hidden" name="id" id="id" value="{{=it.id}}"/>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="activityName">Sales Name:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="salesName" id="salesName" class="form-control col-sm-12" value="{{=it.salesName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="category">Category:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="category" id="category" class="form-control col-sm-12" value="{{=it.category || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="activityName">Makes:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="makes" id="makes" class="form-control col-sm-12" value="{{=it.makes || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="model">Model:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="model" id="model" class="form-control col-sm-12" value="{{=it.model || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="car description">Car Description:</label>
	<div class="col-sm-9">
	<div class="clearfix">
	<textarea class="form-control limited" id="carDescription"  name="carDescription" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.carDescription || ''}}</textarea>
	</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="isNew">Is New:</label>
	<label class="col-sm-3 line-height-1">
		<input name="isNew" value="1" type="radio" class="ace" {{? it.readonly && 1 != it.valueDateType }}disabled="disabled"{{?}} {{? 1 == it.isNew}}checked="checked"{{?}}/>
		<span class="lbl">new</span>
	</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="valueDateDealDay" id="valueDateDealDay" class="form-control col-sm-12" value="{{?it.valueDateDealDay || it.valueDateDealDay== 0}}{{=it.valueDateDealDay }}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label-left no-padding-left" for="valueDateDealDay">used</label>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Years:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Mileages:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">MSRP:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Exterior:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Interior:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Color:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="car description">Comments:</label>
	<div class="col-sm-9">
	<div class="clearfix">
	<textarea class="form-control limited" id="carDescription"  name="carDescription" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.carDescription || ''}}</textarea>
	</div>
	</div>
</div>


<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Down Payment:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Rate:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Terms:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Monthly Pay:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="car description">Comments:</label>
	<div class="col-sm-9">
	<div class="clearfix">
	<textarea class="form-control limited" id="carDescription"  name="carDescription" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.carDescription || ''}}</textarea>
	</div>
	</div>
</div>


<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Credit Card #:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Expiration Date:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">CVS:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Driver License:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>


<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Down Payment:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Terms:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Monthly Pay:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="car description">Comments:</label>
	<div class="col-sm-9">
	<div class="clearfix">
	<textarea class="form-control limited" id="carDescription"  name="carDescription" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.carDescription || ''}}</textarea>
	</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Trade In:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Makes:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Mileage:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Model:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Years:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Value:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Exterior:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Interior:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">Color:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>

</form>
</script>

<script id="step_temp_3" type="text/x-dot-template">
{{~[1,2,3,4,5] :p:index}}
<form method="post" action="" enctype="multipart/form-data" class="form-horizontal" id="pic{{=index+1}}">
<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="">
	{{?index==0}}
		活动介绍:
	{{?? index!=0}}
		图片{{=index+1}}:
	{{?}}
	</label>
	{{? !it.readonly}}
	<div id="input_pic_div{{=index+1}}" class="col-sm-5 {{? it["picUrl"+(index+1)]}}hidden{{?}}">
		<div class="clearfix">
			<input type="file" class="picurl" id="id-input-file-{{=(index+1)}}"  name="id-input-file-{{=(index+1)}}" />
		</div>
	</div>
	{{?}}
	<label class="col-sm-5" for="">
	{{? !it.readonly}}
	<a id="upload_pic_btn{{=index+1}}" class="green {{? it["picUrl"+(index+1)]}}hidden{{?}}" href="javascript:pic_upload({{=(index+1)}})"><i class="ace-icon fa fa-cloud-upload bigger-110"></i>上传</a>
	&nbsp;&nbsp;&nbsp;
	<a id="remove_pic_btn{{=index+1}}" class="orange {{? !(it["picUrl"+(index+1)] &&it["picUrl"+(index+1)] != null && it["picUrl"+(index+1)] != '' )}}hidden{{?}}" href="javascript:pic_remove({{=(index+1)}})"><i class="ace-icon fa fa-trash-o bigger-110"></i>删除</a>
	{{?}}
	&nbsp;&nbsp;&nbsp;
	<a id="gallery-pic{{=index+1}}" class="gallery {{? !it["picUrl"+(index+1)]}}hidden{{?}}" href="{{=fileServerPath+it["picUrl"+(index+1)]}}" data-rel="colorbox">
	<i class="ace-icon fa fa-eye bigger-110"></i>查看
	</a>
	{{? !it["picUrl"+(index+1)]}}
	<a class="red {{? !it.readonly}}hidden{{?}}">
		<i class="ace-icon fa fa-eye-slash bigger-110"></i>&nbsp;&nbsp;未上传
	</a>
	{{?}}
	</label>
</div>
</form>
{{~}}

<form class="form-horizontal" id="submit-form3">
	<input type="hidden" name="picUrl1" id="uploaded-picurl1" value="{{=it.picUrl1 || '' }}"/>
	<input type="hidden" name="picUrl2" id="uploaded-picurl2" value="{{=it.picUrl2 || '' }}"/>
	<input type="hidden" name="picUrl3" id="uploaded-picurl3" value="{{=it.picUrl3 || '' }}"/>
	<input type="hidden" name="picUrl4" id="uploaded-picurl4" value="{{=it.picUrl4 || '' }}"/>
	<input type="hidden" name="picUrl5" id="uploaded-picurl5" value="{{=it.picUrl5 || '' }}"/>
</form>
</script>
