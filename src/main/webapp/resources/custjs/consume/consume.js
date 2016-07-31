$(function () {
	$('.date-timepicker').datetimepicker({
		language:'zh-CN',
		format:'YYYY-MM-DD HH:mm:ss'
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});

	$.iget(
			contextPath + '/consume/'+authType+'/postsalepay/getDic',
			{},
			function(data){
				for(var i=0;i<data.bankType.length;i++){
					var obj123={};
					var keyname = data.bankType[i];
					var valuename = data.bankType[i];
					obj123[keyname]=valuename;
					datadic["bankType"] = $.extend({},datadic["bankType"], obj123);
				}
				for(var i=0;i<data.brand.length;i++){
					var obj123={};
					var keyname = data.brand[i];
					var valuename = data.brand[i];
					obj123[keyname]=valuename;
					datadic["brand"] = $.extend({},datadic["brand"], obj123);
				}
				for(p in datadic["brand"]){
					$("#form-field-3").append("<option value='"+p+"'>"+datadic["brand"][p]+"</option>");
				}
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}
	);

	function createSubmit(){
		var product = new Array();
		$.imodal({
			headDisp:false,
			footDisp:false,
			afterRender:function(){
				$('#phone').on("change", function(){
					var phone = $(this).val();
					$.ipost(
						contextPath + '/consume/'+authType+'/postsalepay/findBuiedProduct',
						{phone:phone},
						function(data){
							// var resp = data;k
							var result = data;
							if($("#buiedproduct-form-field").children() != null){
								$("#buiedproduct-form-field").children().remove();
							}
							for(var i=0; i < result.length; i++){
								$("#buiedproduct-form-field").append("<option value='"+result[i].id+"'>"+result[i].productName+"---" +result[i].id+"---￥"+ result[i].availableAmount.formatter(2,4)+"</option>");
								var prd = {};
								prd[result[i].id] = result[i];
								datadic["buiedproduct"] = $.extend({},datadic["buiedproduct"], prd);
							}
							$("#userName").val(result[0].userName);
							$("#userId").val(result[0].userId);
							$("#availableAmount").val(datadic["buiedproduct"][result[0].id].availableAmount.formatter(2,4));
							$("#orderAmount").val(datadic["buiedproduct"][result[0].id].amount.formatter(2,4));
						},
						function(errmsg){
							if(phone != null && phone != "" ){
								$.ialert( errmsg,"error");
							}
						}
					);
				});
				$("#buiedproduct-form-field").change(function(){
					$("#availableAmount").val(datadic["buiedproduct"][$(this).val()].availableAmount.formatter(2,4));
					$("#orderAmount").val(datadic["buiedproduct"][$(this).val()].amount.formatter(2,4));
				});
				$("#wizard-actions-commit-btn").on('click', function(e){
					if(!$('#consume-submit-form1').valid()){
						$.ialert("填写的内容有错误!", 'error');
						return false;
					}
					$.iconfirm("确定添加?",function(){
						var data =$('#consume-submit-form1').serializeArray();
						var orderId = $("#buiedproduct-form-field").val();
						var aaa = datadic["buiedproduct"][orderId]["productId"];

						data.push({name:"productId", value:datadic["buiedproduct"][orderId]["productId"]});
						data.push({name:"productName", value:datadic["buiedproduct"][orderId]["productName"]});
						var param = $.extend({}, {order:data});
						$("#wizard-actions-commit-btn").attr("disabled", "true");
						$.ipost(
						contextPath + '/consume/'+authType+'/postsalepay/create',
						data,
						function(){
							$.ialert("添加成功!");
							$.imodalClose();
							searchSubmit();
							$.iconfirm("继续添加?",function(){
								createSubmit();
							});
						},
						function(errmsg){
							$("#wizard-actions-commit-btn").attr("disabled", "false");
							$.ialert("添加失败! "+errmsg,"error");
						}
						);
					});
				}).on('stepclick', function(e){});

				$('#consume-submit-form1').validate(validConfig);
			},
			contents:function(){
				var pagefn = doT.template($('#consume_create_temp').text());
				var pagefn1 = doT.template($('#consume_step_temp_1').text());
				var data = $.extend({},data,{datadic:{bankType:datadicArray(datadic["bankType"]),brand:datadicArray(datadic["brand"])}});
				return pagefn({
					title:'新增消费订单',
					page1:pagefn1(data),
				});
			}
		});
	}
	$('#create_btn').click(function() {
		createSubmit();
	});

	$('#search_btn').click(function() {
		searchSubmit();
	});

	$('#search_btn').click();

});

function searchSubmit() {
	var param = {};
	var consumeTimeStart = $("#form-search-consume-time-start").val();
	var consumeTimeEnd = $("#form-search-consume-time-end").val();

	var statusUnconfirm = $("#form-search-status-unconfirm").prop("checked");
	var statusConfirm = $("#form-search-status-confirm").prop("checked");
	var statusFailed = $("#form-search-status-failed").prop("checked");
	var statusPaied = $("#form-search-status-paied").prop("checked");
	var statusArray = new Array();
	var storeProfitStatusArray = new Array();
	var idx = 0;
	if(statusUnconfirm){
		statusArray[idx] = 1; //status:未确认
		idx++;
	}
	if(statusConfirm){
		statusArray[idx] = 4;// status:已确认
		idx++;
	}
	if(statusFailed){
		statusArray[idx] = 3; // status:失败
		idx++;
	}
	if(statusArray.length == 0){
		statusArray[0] = 0;
	}

	if(statusPaied){
		storeProfitStatusArray[0] = 3; // storeProfitPaymentStatus:已结息
	}else{
		storeProfitStatusArray[0] = 0;
	}

	if(consumeTimeStart!=null && consumeTimeEnd!=null && consumeTimeStart.length>0 && consumeTimeEnd.length>0){
		if(consumeTimeStart >= consumeTimeEnd){
			$.ialert("结束时间要晚于开始时间", "error");
		}
	}

	$($('#search_form').serializeArray()).each(function(k, v){
		// if(v.value){
			param[v.name]=v.value;
		// }
	});

	param = $.extend(param, {statusArray:statusArray,storeProfitPaymentStatusList:storeProfitStatusArray});
	$page = $('#page').igrid({
		url : contextPath + '/consume/'+authType + '/postsalepay/listConsume',
		param : param,
		temp : "grid_temp"
	});
}

var validConfig = {
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: false,
		highlight: function (e) {
			$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
		},
		success: function (e) {
			$(e).closest('.form-group').removeClass('has-error');//.addClass('has-info');
			$(e).remove();
		},
		errorPlacement: function (error, element) {
			if(element.is(':checkbox') || element.is(':radio')) {
				var controls = element.closest('div[class*="col-"]');
				if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
				else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
			}
			else if(element.is('.select2')) {
				error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
			}
			else if(element.is('.chosen-select')) {
				error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
			}
			else error.insertAfter(element.parent());
		},
		rules: {
			phone: {
				required: true,
				digits:true,
				maxlength:32
			},
			amount: {
				required: true,
				number:true,
				maxlength:32,
				max:function(){
					return parseFloat($("#availableAmount").val());
				}
			}
		},
		messages: {
			valueDateType:{
				required:'请选择 [购买成功日+] 或 [指定日期]'
			}
		}
}



function update(id){
	$.iget(
			contextPath + '/store/'+authType+'/store4s/find',
			{id:id},
			function(data){
				$.imodal({
					headDisp:false,
					footDisp:false,
					afterRender:function(){
						$('.date-picker').datepicker({
							autoclose: true,
							todayHighlight: true,
							language:'zh-CN',
							format:'yyyy-mm-dd'
						}).next().on(ace.click_event, function(){
							$(this).prev().focus();
						});
						$('.date-timepicker').datetimepicker({
							language:'zh-CN',
							format:'YYYY-MM-DD HH:mm:ss'
						}).next().on(ace.click_event, function(){
							$(this).prev().focus();
						});
						$('textarea.limited').inputlimiter({
							remText: '剩余%n字...',
							limitText: '最大允许: %n字.'
						});
						$('#productAmount').blur(function(){
							if(typeof(eval($(this).val())) == 'number' && typeof(eval($('#minBidAmount').val())) == 'number'){
								$('#totalUnitCount').val($(this).val()/$('#minBidAmount').val());
							}
						});
						$('#minBidAmount').blur(function(){
							if(typeof(eval($('#productAmount').val())) == 'number' && typeof(eval($(this).val())) == 'number'){
								$('#totalUnitCount').val($('#productAmount').val()/$(this).val());
							}
						});
						$('#totalUnitCount').blur(function(){
							if(typeof(eval($('#productAmount').val())) == 'number' && typeof(eval($('#minBidAmount').val())) == 'number'){
								$(this).val($('#productAmount').val()/$('#minBidAmount').val());
							}
						});
						// $('#fuelux-wizard')
						// .ace_wizard({})
						$("#wizard-actions-commit-btn").on('click', function(e){
							if(!$('#consume-submit-form1').valid()){
								$.ialert("填写的内容有错误!", 'error');
								return false;
							}
							$.iconfirm("确定更新?",function(){
								var d1 =$('#submit-form1').serializeArray();
								d1.push({name:'assetsAssignorName', value:datadic["institution"][$('#assets-assignor-select').val()]});
								var data = d1;
								$.ipost(
									contextPath + '/store/'+authType+'/store4s/update',
									data,
									function(){
										$.ialert("更新产品成功!");
										$.imodalClose();
										searchSubmit();
									},
									function(errmsg){
										$.ialert("更新产品失败! "+errmsg,"error");
									}
								);
							});
						}).on('stepclick', function(e){});

						$('#submit-form1').validate(validConfig);
					},
					contents:function(){
						var pagefn = doT.template($('#create_temp').text());
						var pagefn1 = doT.template($('#step_temp_1').text());
						var data1 = $.extend({},data,{datadic:{bankType:datadicArray(datadic["bankType"]),brand: datadicArray(datadic["brand"])}});
						return pagefn({
							title:'修改门店信息',
							page1:pagefn1(data1),
						});
					}
				});
			},
			function(errmsg){
				$.ialert("发生错误："+errmsg,"error");
			}
		);
}

function findStore(id){
	$.iget(
			contextPath + '/consume/'+authType+'/postsalepay/findStore',
			{id:id},
			function(data){
				$.imodal({
					headDisp:false,
					footDisp:false,
					afterRender:function(){
						$('#fuelux-wizard')
						.ace_wizard({})
						.on('change' , function(e, info){
						}).on('stepclick', function(e){});
					},
					contents:function(){
						var pagefn = doT.template($('#store_temp').text());
						var pagefn1 = doT.template($('#store_step_temp_1').text());
						var data1 = $.extend({},data,{datadic:{bankType:datadicArray(datadic["bankType"]),brand: datadicArray(datadic["brand"])}});
						return pagefn({
							title:'门店详情',
							page1:pagefn1(data1)
						});
					}
				});
			},
			function(errmsg){
				$.ialert("发生错误：" + errmsg,"error");
			}
	);
}

function del(id){
	$.iconfirm("确定删除?",function(){
		$.iget(
				contextPath + '/postsalepay/delete',
				{id:id},
				function(){
					$.ialert("删除成功!");
					searchSubmit();
				},
				function(errmsg){
					$.ialert(errmsg,"error");
				});
	});
}
