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
													<span class="title">基本信息</span>
												</li>

												<li data-target="#step2">
													<span class="step">2</span>
													<span class="title">项目介绍</span>
												</li>

												<li data-target="#step3">
													<span class="step">3</span>
													<span class="title">项目图片</span>
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
												返回
											</button>

											<button class="btn btn-sm btn-success btn-next" data-last="确定">
												继续
												<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
											</button>

											<a style="margin-left: 20px;" data-dismiss="modal" href="#">
												取消
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
	<label class="col-sm-3 control-label no-padding-right">在活动专区显示:</label>
	<div class="col-sm-9">
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="amateurDisp" value="1" type="radio" class="ace" {{? it.readonly && 1 != it.amateurDisp }}disabled="disabled"{{?}} {{? 1 == it.amateurDisp}}checked="checked"{{?}}/>
				<span class="lbl"> 显示</span>
			</label>
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="amateurDisp" value="0" type="radio" class="ace" {{? it.readonly && 0 != it.amateurDisp }}disabled="disabled"{{?}} {{? 0 == it.amateurDisp}}checked="checked"{{?}}/>
				<span class="lbl"> 不显示</span>
			</label>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right">在投资专区显示:</label>
	<div class="col-sm-9">
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="investmentDisp" value="1" type="radio" class="ace" {{? it.readonly && 1 != it.investmentDisp }}disabled="disabled"{{?}} {{? 1 == it.investmentDisp}}checked="checked"{{?}}/>
				<span class="lbl"> 显示</span>
			</label>
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="investmentDisp" value="0" type="radio" class="ace" {{? it.readonly && 0 != it.investmentDisp }}disabled="disabled"{{?}} {{? 0 == it.investmentDisp}}checked="checked"{{?}}/>
				<span class="lbl"> 不显示</span>
			</label>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right">是否专享:</label>
	<div class="col-sm-9">
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="productCode" value="GE" type="radio" class="ace" {{? it.readonly && "GE" != it.productCode }}disabled="disabled"{{?}} {{? "GE" == it.productCode}}checked="checked"{{?}}/>
				<span class="lbl"> 普通产品</span>
			</label>
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="productCode" value="ZM" type="radio" class="ace" {{? it.readonly && "ZM" != it.productCode }}disabled="disabled"{{?}} {{? "ZM" == it.productCode}}checked="checked"{{?}}/>
				<span class="lbl"> 挣钱网专享</span>
			</label>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right">购买条件:</label>
	<div class="col-sm-9">
		{{~it.datadic.buyCondition :p:index}}
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="buyCondition" value="{{=p.key }}" type="radio" class="ace" {{? it.readonly && p.key != it.buyCondition }}disabled="disabled"{{?}} {{? p.key == it.buyCondition}}checked="checked"{{?}}/>
				<span class="lbl"> {{=p.value }}</span>
			</label>
		{{~}}
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right">产品系列:</label>
	<div class="col-sm-9">
		{{~it.datadic.productType :p:index}}
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="productType" value="{{=p.key }}" type="radio" class="ace" {{? it.readonly && p.key != it.productType }}disabled="disabled"{{?}} {{? p.key == it.productType}}checked="checked"{{?}}/>
				<span class="lbl"> {{=p.value }}</span>
			</label>
		{{~}}
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="activityName">活动名称:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="activityName" id="activityName" class="form-control col-sm-12" value="{{=it.activityName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productName">产品名称:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productName" id="productName" class="form-control col-sm-12" value="{{=it.productName || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productDesc">产品亮点介绍:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productDesc" id="productDesc" class="form-control col-sm-12" value="{{=it.productDesc || '' }}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="productAmount">产品总额:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="productAmount" id="productAmount" class="form-control col-sm-12" value="{{?it.productAmount || it.productAmount== 0}}{{=it.productAmount.formatter(2,4) }}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="annualReturnRate">预期年化收益率(%):</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="annualReturnRate" id="annualReturnRate" class="form-control col-sm-12" value="{{?it.annualReturnRate || it.annualReturnRate== 0}}{{=it.annualReturnRate.formatter(2,4) }}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="assetsAssignor">资产转让方:</label>
	<div class="col-sm-8">
			<select class="input-medium" id="assets-assignor-select" name="assetsAssignor" {{? it.readonly }}disabled="disabled"{{?}}>
				{{~it.datadic.institution :p:index}}
					<option value="{{=p.key }}" {{? it.assetsAssignor == p.key}}selected="selected"{{?}}>
						{{=p.value }}
					</option>
				{{~}}
			</select>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="dueTime">期限:</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="dueTime" id="dueTime" class="form-control col-sm-12" value="{{?it.dueTime || it.dueTime== 0}}{{=it.dueTime  }}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<div class="col-sm-4">
	<label class="line-height-1" style="margin-right: 10px;">
		<input id="dueTimeTypeMonth" name="dueTimeType" value="1" type="radio" class="ace" {{? it.readonly && 1 != it.dueTimeType }}disabled="disabled"{{?}} {{? 1 == it.dueTimeType}}checked="checked"{{?}}/>
		<span class="lbl"> 月</span>
	</label>
	<label class="line-height-1" style="margin-right: 10px;">
		<input id="dueTimeTypeDay" name="dueTimeType" value="2" type="radio" class="ace" {{? it.readonly && 2 != it.dueTimeType }}disabled="disabled"{{?}} {{? 2 == it.dueTimeType}}checked="checked"{{?}}/>
		<span class="lbl"> 天</span>
	</label>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="incomeDistributionMode">收益分配方式:</label>
	<div class="col-sm-8">
			<select class="input-medium" id="incomeDistributionMode" name="incomeDistributionMode" {{? it.readonly }}disabled="disabled"{{?}}>
			{{~it.datadic.incomeDistributionMode :p:index}}
				<option value="{{=p.key }}" {{? p.key == it.incomeDistributionMode}}selected="selected"{{?}}>{{=p.value }}</option>
			{{~}}
			</select>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="valueDateType">起息日:</label>
	<label class="col-sm-3 line-height-1">
		<input name="valueDateType" value="1" type="radio" class="ace" {{? it.readonly && 1 != it.valueDateType }}disabled="disabled"{{?}} {{? 1 == it.valueDateType}}checked="checked"{{?}}/>
		<span class="lbl"> 购买成功日+</span>
	</label>
	<div class="col-sm-4">
		<div class="clearfix">
			<input type="text" name="valueDateDealDay" id="valueDateDealDay" class="form-control col-sm-12" value="{{?it.valueDateDealDay || it.valueDateDealDay== 0}}{{=it.valueDateDealDay }}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
	<label class="col-sm-1 control-label-left no-padding-left" for="valueDateDealDay">天</label>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="valueDateType"></label>
	<label class="col-sm-3 line-height-1">
		<input name="valueDateType" value="2" type="radio" class="ace" {{? it.readonly && 2 != it.valueDateType }}disabled="disabled"{{?}} {{? 2 == it.valueDateType}}checked="checked"{{?}}/>
		<span class="lbl"> 指定日期</span>
	</label>
	<div class="col-sm-4">
	<div class="clearfix">
<span style="position: relative; z-index: 9999;">
		<input name="valueDateDesignatedDay" id="valueDateDesignatedDay" class="form-control date-picker" type="text" value="{{?it.valueDateDesignatedDay }}{{=new Date(it.valueDateDesignatedDay).toChString() || ''}}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}></input>
</span>
	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="minBidAmount">最小投标单位:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="minBidAmount" id="minBidAmount" class="form-control col-sm-12" value="{{?it.minBidAmount || it.minBidAmount== 0}}{{=it.minBidAmount.formatter(2,4) }}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="totalUnitCount">单位总份数:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="totalUnitCount" id="totalUnitCount" class="form-control col-sm-12" value="{{?it.totalUnitCount || it.totalUnitCount== 0}}{{=it.totalUnitCount.formatter(2,4) }}{{?}}" readonly="readonly"/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="oneselfMaxUnit">单人最大购买份数:</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="oneselfMaxUnit" id="oneselfMaxUnit" class="form-control col-sm-12" value="{{?it.oneselfMaxUnit || it.oneselfMaxUnit== 0}}{{=it.oneselfMaxUnit.formatter(2,4) }}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="investmentCost">投资费用(%):</label>
	<div class="col-sm-8">
		<div class="clearfix">
			<input type="text" name="investmentCost" id="investmentCost" class="form-control col-sm-12" value="{{?it.investmentCost || it.investmentCost== 0}}{{=it.investmentCost.formatter(2,4) }}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="">上线时间:</label>
	<div class="col-sm-8">
	<div class="clearfix">
		<input name="onlineTime" id="onlineTime" class="form-control date-timepicker" type="text" value="{{? it.onlineTime }}{{=new Date(it.onlineTime).toChString(true) || ''}}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}></input>
	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="">投标开始时间:</label>
	<div class="col-sm-8">
	<div class="clearfix">
		<input name="biddingStartTime" id="biddingStartTime" class="form-control date-timepicker" type="text" value="{{? it.biddingStartTime}}{{=new Date(it.biddingStartTime).toChString(true) || ''}}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}></input>
	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="">投标结束时间:</label>
	<div class="col-sm-8">
	<div class="clearfix">
		<input name="biddingEndTime" id="biddingEndTime" class="form-control date-timepicker" type="text" value="{{? it.biddingEndTime}}{{=new Date(it.biddingEndTime).toChString(true) || ''}}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}></input>
	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="">预计到账时间:</label>
	<div class="col-sm-8">
	<div class="clearfix">
		<input name="expectedReceivedTime" id="expectedReceivedTime" class="form-control date-timepicker" type="text" value="{{? it.expectedReceivedTime}}{{=new Date(it.expectedReceivedTime).toChString(true) || ''}}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}></input>
	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right">提前结束:</label>
	<div class="col-sm-9">
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="advanceOffline" value="1" type="radio" class="ace" {{? it.readonly && 1 != it.advanceOffline }}disabled="disabled"{{?}} {{? 1 == it.advanceOffline}}checked="checked"{{?}}/>
				<span class="lbl"> 是</span>
			</label>
			<label class="line-height-1" style="margin-right: 10px;">
				<input name="advanceOffline" value="0" type="radio" class="ace" {{? it.readonly && 0 != it.advanceOffline }}disabled="disabled"{{?}} {{? 0 == it.advanceOffline}}checked="checked"{{?}}/>
				<span class="lbl"> 否</span>
			</label>
	</div>
</div>
</form>
</script>

<script id="step_temp_2" type="text/x-dot-template">
<form class="form-horizontal" id="submit-form2">
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="projectDesc">项目简述:</label>
	<div class="col-sm-9">
	<div class="clearfix">
	<textarea class="form-control limited" id="projectDesc"  name="projectDesc" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.projectDesc || ''}}</textarea>
	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="projectDesc">资金用途:</label>
	<div class="col-sm-9">
	<div class="clearfix">
	<textarea class="form-control limited" id="fundUsage"  name="fundUsage" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.fundUsage || ''}}</textarea>
	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="assetChannelDesc">资产渠道方介绍:</label>
	<div class="col-sm-9">
	<div class="clearfix">
	<textarea class="form-control limited" id="assetChannelDesc"  name="assetChannelDesc" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.assetChannelDesc || ''}}</textarea>
	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="assetDesc">资产详情:</label>
	<div class="col-sm-9">
	<div class="clearfix">
	<textarea class="form-control limited" id="assetDesc"  name="assetDesc" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.assetDesc || ''}}</textarea>
	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="repaymentSource">还款来源:</label>
	<div class="col-sm-9">
	<div class="clearfix">
	<textarea class="form-control limited" id="repaymentSource"  name="repaymentSource" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.repaymentSource || ''}}</textarea>
	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="riskManagementMeasure">风控措施:</label>
	<div class="col-sm-9">
	<div class="clearfix">
	<textarea class="form-control limited" id="riskManagementMeasure"  name="riskManagementMeasure" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.riskManagementMeasure || ''}}</textarea>
	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="riskHint">风险提示:</label>
	<div class="col-sm-9">
	<div class="clearfix">
	<textarea class="form-control limited" id="riskHint"  name="riskHint" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.riskHint || ''}}</textarea>
	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="projectRiskManagement">项目风控:</label>
	<div class="col-sm-9">
	<div class="clearfix">
	<textarea class="form-control limited" id="projectRiskManagement"  name="projectRiskManagement" maxlength="1000" {{? it.readonly}}readonly="readonly"{{?}}>{{=it.projectRiskManagement || ''}}</textarea>
	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="platformAdvantage">平台优势:</label>
	<div class="col-sm-9">
	<div class="clearfix">
	<textarea class="form-control limited" id="platformAdvantage"  name="platformAdvantage" maxlength="1000" {{? it.readonly}}readOnly="readOnly"{{?}}>{{=it.platformAdvantage || ''}}</textarea>
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
