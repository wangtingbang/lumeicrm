<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<script id="store_temp" type="text/x-dot-template">
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
													关闭
												</a>
											</div>
										</div><!-- /.widget-main -->
									</div><!-- /.widget-body -->
								</div>
							</div>
	</script>

	<script id="store_step_temp_1" type="text/x-dot-template">
	<form class="form-horizontal" id="consume-submit-form1">
	<input type="hidden" name="id" id="id" value="{{=it.id}}"/>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="productName">门店简称:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="text" name="shortName" id="shortName" class="form-control col-sm-12" value="{{=it.shortName || '' }}" readonly="readonly"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="productDesc">门店全称:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="text" name="fullName" id="fullName" class="form-control col-sm-12" value="{{=it.fullName || '' }}" readonly="readonly"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="brand">品牌:</label>
		<div class="col-sm-8">
				<select class="input-medium" id="brand-select" name="brand" disabled="disabled">
					{{~it.datadic.brand :p:index}}
						<option value="{{=p.key }}" {{? it.brand == p.key}}selected="selected"{{?}}>
							{{=p.value }}
						</option>
					{{~}}
				</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="province">省份:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="text" name="province" id="province" class="form-control col-sm-12" value="{{?it.province}}{{=it.province }}{{?}}" readonly="readonly"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="city">城市:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="text" name="city" id="city" class="form-control col-sm-12" value="{{?it.city}}{{=it.city }}{{?}}" readonly="readonly"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="address">详细地址:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="text" name="address" id="address" class="form-control col-sm-12" value="{{?it.address}}{{=it.address }}{{?}}" readonly="readonly"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="contactName">联系人:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="text" name="contactName" id="contactName" class="form-control col-sm-12" value="{{?it.contactName}}{{=it.contactName }}{{?}}" readonly="readonly"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="contractPhone">联系电话:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="text" name="contactPhone" id="contactPhone" class="form-control col-sm-12" value="{{?it.contactPhone}}{{=it.contactPhone }}{{?}}" readonly="readonly"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="bindUserName">绑定登录用户:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="text" name="bindUserName" id="bindUserName" class="form-control col-sm-12" value="{{?it.bindUserName}}{{=it.bindUserName }}{{?}}" readonly="readonly"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="city">银行:</label>
		<div class="col-sm-8">
				<select class="input-medium" id="bank-type-select" name="bankType" disabled="disabled">
					{{~it.datadic.bankType :p:index}}
						<option value="{{=p.key }}" {{? it.bankType == p.key}}selected="selected"{{?}}>
							{{=p.key }}
						</option>
					{{~}}
				</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="bankBranch">开户行名称:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="text" name="bankBranch" id="bankBranch" class="form-control col-sm-12" value="{{?it.bankBranch}}{{=it.bankBranch }}{{?}}" readonly="readonly"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="bankProvince">开户行省份:</label>
		<div class="col-sm-8">
				<div class="clearfix">
					<input type="text" name="bankProvince" id="bankProvince" class="form-control col-sm-12" value="{{?it.bankProvince}}{{=it.bankProvince }}{{?}}" readonly="readonly"/>
				</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="bankCity">开户行城市:</label>
		<div class="col-sm-8">
				<div class="clearfix">
					<input type="text" name="bankCity" id="bankCity" class="form-control col-sm-12" value="{{?it.bankCity}}{{=it.bankCity }}{{?}}" readonly="readonly"/>
				</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="bankAccountNo">银行账号:</label>
		<div class="col-sm-8">
			<div class="clearfix">
				<input type="text" name="bankAccountNo" id="bankAccountNo" class="form-control col-sm-12" value="{{?it.bankAccountNo}}{{=it.bankAccountNo }}{{?}}" readonly="readonly"/>
			</div>
		</div>
	</div>
	</form>
	</script>
