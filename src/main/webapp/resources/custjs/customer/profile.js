$(function () {
	$('.date-timepicker').datetimepicker({
		language:'zh-CN',
		format:'YYYY-MM-DD HH:mm:ss'
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	
	$('#search_btn').click(function() {
		searchSubmit();
	});

	$('#search_btn').click();
	
	searchSubmit();

});

function searchSubmitBak() {
	var param = {};

	$($('#search_form').serializeArray()).each(function(k, v){
		// if(v.value){
			param[v.name]=v.value;
		// }
	});

	$page = $('#page').igrid({
		//url : contextPath + '/consume/'+authType + '/postsalepay/listConsume',
		url : contextPath + '/profile/get',
		param : param,
		temp : "grid_temp"
	});
}

function searchSubmit(){
	var param = {};

	$($('#search_form').serializeArray()).each(function(k, v){
			param[v.name]=v.value;
		// }
	});
	
	$.iget(
			contextPath + '/customer/profile/get',
			{customerId:param[cusotmerId]},
			function(data){
				$.imodal({
					headDisp:false,
					footDisp:false,
					afterRender:function(){
						$('#fuelux-wizard')
						.ace_wizard({})
						.on('change' , function(e, info){
						}).on('stepclick', function(e){});
						pic_render();
					},
					contents:function(){
						var pagefn = doT.template($('#create_temp').text());
						var pagefn1 = doT.template($('#step_temp_1').text());
						var pagefn2 = doT.template($('#step_temp_2').text());
						var pagefn3 = doT.template($('#step_temp_3').text());
						var data1 = $.extend({},data.prdRuProduct,{readonly:true,datadic:{productType:datadicArray(datadic["productType"]),incomeDistributionMode:datadicArray(datadic["incomeDistributionMode"]),institution: datadicArray(datadic["institution"]),buyCondition: datadicArray(datadic["buyCondition"])}});
						var data2 = $.extend({},data.prdRuProductExt,{readonly:true});
						var data3 = $.extend({},data.prdRuProductExt,{readonly:true});
						return pagefn({
							title:'产品详情',
							page1:pagefn1(data1),
							page2:pagefn2(data2),
							page3:pagefn3(data3)
						});
					}
				});
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}
	);
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
