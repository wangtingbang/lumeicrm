$(function () {
	$('.date-timepicker').datetimepicker({
		language:'zh-CN',
		format:'YYYY-MM-DD HH:mm:ss'
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});

	for(p in datadic["productType"]){
		$("#form-field-2").append("<option value='"+p+"'>"+datadic["productType"][p]+"</option>");
	}
	var _productStatus = {
		"0":"全部",
		"1":"未提交",
		"2":"退回",
		"3":"未审批",
		"4":"审批通过"
	}
	for(p in _productStatus){
		$("#form-field-3").append("<option value='"+p+"'>"+_productStatus[p]+"</option>");
	}

	$.iget(
			contextPath + '/product/listInstitution',
			{},
			function(data){
				var obj = $("#assetsAssignor-select");
				for(var i=0;i<data.institution.length;i++){
					var obj123={};
					var keyname = data.institution[i].id;
					var valuename = data.institution[i].companyName;
					obj123[keyname]=valuename;
					datadic["institution"] = $.extend({},datadic["institution"],obj123);
				}
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}
	);

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
				$('#dueTime').blur(function(){
					setExpectedReceivedTime();
				});
				$('#biddingEndTime').change(function(){
					setExpectedReceivedTime();
				});
				$('#biddingEndTime').blur(function(){
					setExpectedReceivedTime();
				});
				$('#valueDateDesignatedDay').change(function(){
					setExpectedReceivedTime();
				});
				$('#valueDateDesignatedDay').blur(function(){
					setExpectedReceivedTime();
				});
				$('#valueDateDealDay').blur(function(){
					setExpectedReceivedTime();
				});
				$('#dueTimeTypeMonth').change(function(){
					setExpectedReceivedTime();
				});
				$('#dueTimeTypeDay').change(function(){
					setExpectedReceivedTime();
				});

				$('#fuelux-wizard')
				.ace_wizard({})
				.on('change' , function(e, info){
					if(info.direction=="next"){
						if(info.step == 1){
							if(!$('#submit-form1').valid()){
								$.ialert("填写的内容有错误!", 'error');
								return false;
							}
						}
						if(info.step == 2){
							if(!$('#submit-form2').valid()){
								$.ialert("填写的内容有错误!", 'error');
								return false;
							}
						}
					}
				})
				.on('finished', function(e) {
					if(!$('#submit-form3').valid()){
						$.ialert("填写的内容有错误!", 'error');
						return false;
					}
					$.iconfirm("确定添加该产品?",function(){
						var d1 =$('#submit-form1').serializeArray();
						d1.push({name:'assetsAssignorName', value:datadic["institution"][$('#assets-assignor-select').val()]});
						var d2 = $('#submit-form2').serializeArray();
						var d3 = $('#submit-form3').serializeArray();
						var data = d1.concat(d2).concat(d3);
						$.ipost(
						contextPath + '/product/create',
						data,
						function(){
							$.ialert("添加项目成功!");
							$.imodalClose();
							searchSubmit();
							$.iconfirm("继续添加项目?",function(){
								createSubmit();
							});
						},
						function(errmsg){
							$.ialert("添加项目失败! "+errmsg,"error");
						}
						);
					});
				}).on('stepclick', function(e){});

				$('#submit-form1').validate(validConfig);
				$('#submit-form2').validate(validConfig);
				$('#submit-form3').validate(validConfig);

				validate_pic();

				pic_render();

			},
			contents:function(){
				var pagefn = doT.template($('#create_temp').text());
				var pagefn1 = doT.template($('#step_temp_1').text());
				var pagefn2 = doT.template($('#step_temp_2').text());
				var pagefn3 = doT.template($('#step_temp_3').text());
				var data = {amateurDisp:1, investmentDisp:1, productType:1,dueTimeType:1, valueDateType:1,advanceOffline:1, productCode:'GE',buyCondition:0 };
				var data = $.extend({},data,{datadic:{productType:datadicArray(datadic["productType"]),incomeDistributionMode:datadicArray(datadic["incomeDistributionMode"]),institution: datadicArray(datadic["institution"]),buyCondition: datadicArray(datadic["buyCondition"])}});
				return pagefn({
					title:'新增产品',
					page1:pagefn1(data),
					page2:pagefn2({}),
					page3:pagefn3({})
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
			amateurDisp:'required',
			investmentDisp:'required',
			productType:'required',
			productName: {
				required: true,
				maxlength:25
			},
			productDesc: {
				required: true,
				maxlength:255
			},
			productAmount: {
				required: true,
				number:true,
				max:9999999999999
			},
			annualReturnRate: {
				required: true,
				number:true,
				max:100
			},
			assetsAssignor:'required',
			dueTime: {
				required: true,
				digits:true
			},
			dueTimeType:'required',
			incomeDistributionMode:'required',
			valueDateType:'required',
			valueDateDealDay:{
				required:function(){
					return $('input:radio[name="valueDateType"]:checked').val() == 1;
				},
				number:function(){
					return $('input:radio[name="valueDateType"]:checked').val() == 1;
				}
			},
			valueDateDesignatedDay:{
				required:function(){
					if($('input:radio[name="valueDateType"]:checked').val() != 2){
						return false;
					}
				},
				date:function(){
					return $('input:radio[name="valueDateType"]:checked').val() == 2;
				}
			},
			// onlineTime:{
			// 	required:function(){
			// 		return $('input:text[name="onlineTime"]').val() > new Date();
			// 	},
			// 	date:function(){
			// 		return $('input:text[name="onlineTime"]').val() > new Date();
			// 	}
			// },
			minBidAmount: {
				required: true,
				number:true,
				max:9999999999999
			},
			totalUnitCount: {
				required: true,
				digits:true
			},
			oneselfMaxUnit: {
				required: true,
				digits:true
			},
			investmentCost: {
				required: true,
				number:true,
				max:100
			},
			onlineTime: {
				required: true,
				datetime:true
			},
			biddingStartTime: {
				required: true,
				datetime:true
			},
			biddingEndTime: {
				required: true,
				datetime:true
			},
			expectedReceivedTime: {
				required: true,
				datetime:true
			},
			advanceOffline:'required',
			projectDesc: {
				required: true,
				maxlength:1000
			},
			riskManagementMeasure: {
				required: true,
				maxlength:1000
			},
			riskHint: {
				required: true,
				maxlength:1000
			},
			assetChannelDesc: {
				required: true,
				maxlength:1000
			},
			assetDesc: {
				required: true,
				maxlength:1000
			},
			fundUsage: {
				required: true,
				maxlength:1000
			},
			projectRiskManagement: {
				required: true,
				maxlength:1000
			},
			repaymentSource: {
				required: true,
				maxlength:1000
			},
			platformAdvantage: {
				required: true,
				maxlength:1000
			}
		},
		messages: {
			valueDateType:{
				required:'请选择 [购买成功日+] 或 [指定日期]'
			},
			valueDateDesignatedDay:{
				required:'请选择晚于今天的日期'
			}
		}
}

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
		url : contextPath + '/product/listByPage',
		param : param,
		temp : "grid_temp"
	});
}

function del(id){
	$.iconfirm("确定删除?",function(){
		$.iget(
				contextPath + '/product/del',
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


function update(id){
	$.iget(
			contextPath + '/product/find',
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

						$('#dueTime').blur(function(){
							setExpectedReceivedTime();
						});
						$('#biddingEndTime').change(function(){
							setExpectedReceivedTime();
						});
						$('#biddingEndTime').blur(function(){
							setExpectedReceivedTime();
						});
						$('#valueDateDesignatedDay').change(function(){
							setExpectedReceivedTime();
						});
						$('#valueDateDesignatedDay').blur(function(){
							setExpectedReceivedTime();
						});
						$('#valueDateDealDay').blur(function(){
							setExpectedReceivedTime();
						});
						$('#dueTimeTypeMonth').change(function(){
							setExpectedReceivedTime();
						});
						$('#dueTimeTypeDay').change(function(){
							setExpectedReceivedTime();
						});

						$('#fuelux-wizard')
						.ace_wizard({})
						.on('change' , function(e, info){
							if(info.direction=="next"){
								if(info.step == 1){
									if(!$('#submit-form1').valid()){
										$.ialert("填写的内容有错误!", 'error');
										return false;
									}
								}
								if(info.step == 2){
									if(!$('#submit-form2').valid()){
										$.ialert("填写的内容有错误!", 'error');
										return false;
									}
								}
							}
						})
						.on('finished', function(e) {
							if(!$('#submit-form3').valid()){
								$.ialert("填写的内容有错误!", 'error');
								return false;
							}
							$.iconfirm("确定更新?",function(){
								var d1 =$('#submit-form1').serializeArray();
								d1.push({name:'assetsAssignorName', value:datadic["institution"][$('#assets-assignor-select').val()]});
								var d2 = $('#submit-form2').serializeArray();
								var d3 = $('#submit-form3').serializeArray();
								var data = d1.concat(d2).concat(d3);
								$.ipost(
									contextPath + '/product/update',
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
						$('#submit-form2').validate(validConfig);
						$('#submit-form3').validate(validConfig);

						validate_pic();

						pic_render();
					},
					contents:function(){
						var pagefn = doT.template($('#create_temp').text());
						var pagefn1 = doT.template($('#step_temp_1').text());
						var pagefn2 = doT.template($('#step_temp_2').text());
						var pagefn3 = doT.template($('#step_temp_3').text());
						var data1 = $.extend({},data.prdRuProduct,{datadic:{productType:datadicArray(datadic["productType"]),incomeDistributionMode:datadicArray(datadic["incomeDistributionMode"]),institution: datadicArray(datadic["institution"]),buyCondition: datadicArray(datadic["buyCondition"])}});
						var data2 = $.extend({},data.prdRuProductExt);
						return pagefn({
							title:'修改产品',
							page1:pagefn1(data1),
							page2:pagefn2(data2),
							page3:pagefn3(data2)
						});
					}
				});
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}
		);
}

function apply(id){
	$.iconfirm("确定提交审核?",function(){
		$.ipost(
				contextPath + '/product/apply',
				{id:id},
				function(){
					$.ialert("提交成功!");
					searchSubmit();
				},
				function(errmsg){
					$.ialert(errmsg,"error");
				});
	});
}

function pic_upload(index){
	var isfile = $('#id-input-file-'+index).val();
	if(! isfile){
		$.ialert("请先选择文件");
	}else{
		$.iget(
			contextPath+'/product/uploadUrl?v='+new Date(),
			null,
			function(uploadUrl){
				$.ajaxFileUpload({
					url:fileServerPath + uploadUrl,
					secureuri:true,
					fileElementId:'id-input-file-'+index,//file标签的id
					success: function (data, status) {
						$('#uploaded-picurl'+index).val(data.retData[0].url);
						document.getElementById('gallery-pic'+index).href= fileServerPath + data.retData[0].url;
						$("#input_pic_div"+index).addClass("hidden");
						$("#upload_pic_btn"+index).addClass("hidden");
						$("#remove_pic_btn"+index).removeClass("hidden");
						$("#gallery-pic"+index).removeClass("hidden");
					},
					error: function (data, status, e) {
						$.ialert("上传失败!");
					}
			});
		},
		function(errmsg){
			$.ialert(errmsg);
			return;
		});
	}
}

function pic_remove(index){
	$("#input_pic_div"+index).removeClass("hidden");
	$("#upload_pic_btn"+index).removeClass("hidden");
	$("#remove_pic_btn"+index).addClass("hidden");
	$("#gallery-pic"+index).addClass("hidden");
	$("#uploaded-picurl"+index).val('');
}

/**
*/
function validate_pic(){
	$('.picurl').ace_file_input({
		no_file:'',
		btn_choose:'选择',
		btn_change:'选择',
		droppable:false,
		onchange:null,
//		icon_remove:null,
		thumbnail:false, //| true | large
		//whitelist:'gif|png|jpg|jpeg',
		allowExt:  ['jpg', 'jpeg', 'png', 'gif', 'tif', 'tiff', 'bmp','JPG', 'JPEG', 'PNG', 'GIF', 'TIF', 'TIFF', 'BMP'],
		denyExt:  ['exe', 'php', 'sql','EXE', 'PHP', 'SQL'],
		maxSize: 5242880,//5242880bytes(5M)
		//blacklist:'exe|php|sql'
	}).on('file.error.ace', function(event, info) {
		if(info.error_count['ext'] ) $.ialert('文件类型不支持，请选择 jpg, jpeg, png, gif, tif, tiff, bmp中的一种上传', 'error');
		if(info.error_count['size']) $.ialert('文件太大，请上传小于5M的文件', 'error');
});
}

function setExpectedReceivedTime(){
	var valueDate = null;
	var valueDateType = $("input:radio[name='valueDateType']:checked").val();
	var dueTime = $('#dueTime').val();
	var biddingEndTimeStr = $("#biddingEndTime").val();
	var valueDateDealDay = $("#valueDateDealDay").val();
	var valueDateDesignatedDayStr = $("#valueDateDesignatedDay").val();

	if( dueTime && ( valueDateDealDay&&biddingEndTimeStr || valueDateDesignatedDayStr ) ){
		if (valueDateType == 1) {
			var biddingEndTime = new Date(biddingEndTimeStr.replace(/-/g,"/"));
			valueDate = biddingEndTime;//new Date();
			valueDate.setDate(biddingEndTime.getDate()+parseInt(valueDateDealDay));
		} else if (valueDateType == 2) {
			valueDate = new Date(valueDateDesignatedDayStr.replace(/-/g,"/"));
		}

		var dueTimeType = $("input:radio[name='dueTimeType']:checked").val();
		var expectedReceivedTime = new Date();
		if (dueTimeType == 1) {
			expectedReceivedTime = valueDate;
			expectedReceivedTime.setMonth(valueDate.getMonth()+parseInt(dueTime));
			expectedReceivedTime.setDate(expectedReceivedTime.getDate()+parseInt(3))
		} else if (dueTimeType == 2) {
			expectedReceivedTime = valueDate;
			expectedReceivedTime.setDate(valueDate.getDate() + parseInt(dueTime)+parseInt(3));
		}
		expectedReceivedTime.setHours(0);
		expectedReceivedTime.setMinutes(0);
		expectedReceivedTime.setSeconds(0);
		$('#expectedReceivedTime').val(expectedReceivedTime.toChString(true));
	}
}
