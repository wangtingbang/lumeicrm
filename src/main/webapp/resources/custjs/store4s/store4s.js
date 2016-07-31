$(function () {
	$('.date-timepicker').datetimepicker({
		language:'zh-CN',
		format:'YYYY-MM-DD HH:mm:ss'
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});

	$.iget(
			contextPath + '/store/'+authType+'/store4s/getBankDic',
			{},
			function(data){
				for(var i=0;i<data.length;i++){
					var obj123={};
					var keyname = data[i];
					var valuename = data[i];
					obj123[keyname]=valuename;
					datadic["bankType"] = $.extend({},datadic["bankType"], obj123);
				}
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}
	);

	$.iget(
			contextPath + '/store/'+authType+'/store4s/getDic',
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

	// var _brand = datadic["brand"];
	//
	// for(p in _brand){
	// 	$("#form-field-3").append("<option value='"+p+"'>"+_brand[p]+"</option>");
	// }

	function createSubmit(){
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
				$("#wizard-actions-commit-btn").on('click', function(e){
					if(!$('#submit-form1').valid()){
						$.ialert("填写的内容有错误!", 'error');
						return false;
					}
					$.iconfirm("确定添加该产品?",function(){
						var data =$('#submit-form1').serializeArray();
						// data = $.extend({},data,{datadic:{bankType:datadicArray(datadic["bankType"]),brand: datadicArray(datadic["brand"])}});
						$.ipost(
						contextPath + '/store/'+authType+'/store4s/create',
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
							$.ialert("添加失败! "+errmsg,"error");
						}
						);
					});
				}).on('stepclick', function(e){});

				$('#submit-form1').validate(validConfig);
			},
			contents:function(){
				var pagefn = doT.template($('#create_temp').text());
				var pagefn1 = doT.template($('#step_temp_1').text());
				var data = $.extend({},data,{datadic:{bankType:datadicArray(datadic["bankType"]),brand:datadicArray(datadic["brand"])}});
				return pagefn({
					title:'新增门店信息',
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
	var prdouctAmountStart = $("#form-field-4-1").val();
	if(prdouctAmountStart != null && prdouctAmountStart != ""){
		if(!jQuery.isNumeric( prdouctAmountStart )){
			$.ialert("请填写正确的产品总额数值", "error");
			return;
		}
	}
	var prdouctAmountEnd = $("#form-field-4-2").val();
	if(prdouctAmountEnd != null && prdouctAmountEnd != ""){
		if(!jQuery.isNumeric( prdouctAmountEnd )){
			$.ialert("请填写正确的产品总额数值", "error");
			return;
		}
	}
	$($('#search_form').serializeArray()).each(function(k, v){
		if(v.value){
			param[v.name]=v.value;
		}
	});
	$page = $('#page').igrid({
		url : contextPath + '/store/'+authType + '/store4s/list4s',
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
			fullName: {
				required: true,
				maxlength:32
			},
			shortName: {
				required: true,
				maxlength:32
			},
			brand: {
				required: true
			},
			province: {
				required: true,
				maxlength:32
			},
			city: {
				required: true,
				maxlength:32
			},
			address: {
				required: true,
				maxlength:255
			},
			contactName:{
				required:true,
				maxlength:16
			},
			contactPhone: {
				required: true,
				maxlength:16
			},
			bindUserName: {
				required: true,
				maxlength:16
			},
			bankType: {
				required: true
			},
			bankProvince: {
				required: true,
				maxlength:32
			},
			bankBranch: {
				required: true,
				maxlength:32
			},
			bankAccountNo: {
				required: true,
				digits:true,
				maxlength:32
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
							if(!$('#submit-form1').valid()){
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
				$.ialert(errmsg,"error");
			}
		);
}

function find(id){
	$.iget(
			contextPath + '/store/'+authType+'/store4s/find',
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
						var pagefn = doT.template($('#create_temp').text());
						var pagefn1 = doT.template($('#step_temp_1').text());
						var data1 = $.extend({},data,{datadic:{bankType:datadicArray(datadic["bankType"]),brand: datadicArray(datadic["brand"])}});
						return pagefn({
							title:'门店详情',
							page1:pagefn1(data1)
						});
					}
				});
			},
			function(errmsg){
				$.ialert(errmsg,"error");
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
