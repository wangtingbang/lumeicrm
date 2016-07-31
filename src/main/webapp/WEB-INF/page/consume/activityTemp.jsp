<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<script id="consume_create_temp" type="text/x-dot-template">
	<div class="widget-box" style="{{? 'firefox'==_browser}}min-width: 600px;{{?}} {{? 'webkit'==_browser}}max-width: 800px;min-width: 600px;{{?}}" >
									<div class="widget-header widget-header-blue widget-header-flat">
										<h4 class="widget-title lighter">{{=it.title}}</h4>
										<button type="button" class="close pull-right" data-dismiss="modal" aria-hidden="true" style="font-size: 32px; margin: 3px;">&times;</button>
									</div>
									<div class="widget-body">
										<div class="widget-main">
											<div id="fuelux-wizard" data-target="#step-container">
											<hr />
											<div class="step-content pos-rel" id="step-container">
												<div class="step-pane active" id="step1">
													{{=it.page1}}
												</div>
											</div>
											<hr />
											<div class="wizard-actions">
												<button id="wizard-actions-commit-btn" class="btn btn-sm btn-success btn-next" data-last="确定">
													确定
													<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
												</button>
												<a style="margin-left: 20px;" data-dismiss="modal" href="#">
													取消
												</a>
											</div>
										</div><!-- /.widget-main -->
									</div><!-- /.widget-body -->
								</div>
							</div>
	</script>

	<script id="consume_step_temp_1" type="text/x-dot-template">
	<form class="form-horizontal" id="consume-submit-form1">
	<input type="hidden" name="storeId" id="id" value="<%=SessionUtil.getAttributes("storeId") %>"/>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="storeName">门店全称:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="text" name="storeName" id="fullName" class="form-control col-sm-12" value="<%=SessionUtil.getAttributes("storeFullName") %>" readonly="readonly"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="brand">品牌:</label>
		<div class="col-sm-8">
				<div class="clearfix">
					<input type="text" name="storeBrand" id="storeBrand" class="form-control col-sm-12" value="<%=SessionUtil.getAttributes("storeBrand") %>" readonly="readonly"/>
				</div>
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="phone">投资人手机:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="hidden" name="userId" id="userId" />
				<input type="text" name="userPhone" id="phone" class="form-control col-sm-12" value="{{?it.phone}}{{=it.phone }}{{?}}" {{? it.readonly}}readonly="readonly"{{?}}/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="userName">投资人姓名:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="text" name="userName" id="userName" class="form-control col-sm-12" value="{{?it.userName}}{{=it.userName }}{{?}}" readonly="readonly"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="productName">投资产品:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<select id="buiedproduct-form-field" class="form-control" name="orderId">
				</select>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="availableAmount">可用余额:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="hidden" name="orderAmount" id="orderAmount"/>
				<input type="text" name="availableAmount" id="availableAmount" class="form-control col-sm-12" value="{{?it.availableAmount}}{{=it.availableAmount.formatter(2,4) }}{{?}}" readonly="readonly"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="consumeAmount">消费金额:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="text" name="amount" id="consumeAmount" class="form-control col-sm-12" {{? it.readonly}}readonly="readonly"{{?}}/>
			</div>
		</div>
	</div>
	</form>
	</script>
