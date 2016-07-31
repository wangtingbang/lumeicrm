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
	
	$('#appr_btn').click(function() {
		var applyData = new Array();
		 $("[name = checkItem]:checkbox").each(function () {
			 if($(this).prop("checked")){
				 var orderId = $(this).val();
				 var orderItem = orderData[orderId];
				 applyData.push(orderItem);
			 }
         });
		 if(applyData.length == 0){
			 $.ialert("请至少选择一条记录","error");
			 return;
		 }
		 proccessBatch(applyData);
	});
	
	$('#search_btn').click();

});

var orderData = {};

function searchSubmit() {
	var param = {};
	var status= new Array();
	$($('#search_form').serializeArray()).each(function(k, v){
		if(v.value){
			if(v.name=="status"){
				status.push(v.value);
			}else{
				param[v.name]=v.value;
			}
		}
	});
	param["status"]=status;
	$page = $('#page').igrid({
		url : contextPath + '/financing/'+authType+'/listInvRec',
		param : param,
		rowlist : [20,50,200],
		temp : "grid_temp",
		afterRender : function(resultData){
			for (var i = 0; i < resultData.length; i++) {
				orderData[resultData[i].id] = resultData[i];
			}
			$("#checkAll").change(function(){
				if($(this).prop("checked")){
					$("[name = checkItem]:checkbox").each(function(k, v){
						if(!$(this).prop("disabled")){
							$(this).prop("checked", true);
						}
					});
				}else{
					$("[name = checkItem]:checkbox").each(function(k, v){
						if(!$(this).prop("disabled")){
							$(this).prop("checked", false);
						}
					});
				}
			});
		}
	});
}

function proccess(id){
	var applyData = new Array();
	var orderItem = orderData[id];
	applyData.push(orderItem);
	proccessBatch(applyData);
}

function proccessBatch(dataArray){
	var principalAmount = 0;
	var profitAmount = 0;
	for (var i = 0; i < dataArray.length; i++) {
		principalAmount += dataArray[i].amount;
		profitAmount += dataArray[i].profit;
	}
	var data = {
			itemCount : dataArray.length,
			productName : dataArray[0].product.productName,
			totalAmount : principalAmount+profitAmount,
			principalAmount : principalAmount,
			profitAmount : profitAmount,
			dataArray : dataArray
	}
	
	$.imodal({
		title : authType == "ap"?"审批":"还款",
		contents:function(){
			var pagefn = doT.template($('#apply_temp').text());
			return pagefn(data);
		},
		afterRender:function(){
			$('textarea.limited').inputlimiter({
				remText: '剩余%n字...',
				limitText: '最大允许: %n字.'
			});
			if(authType == "ap"){
				$('#applyform').validate({
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
					rules: {
						comments:{
							required: true,
							maxlength:100
						}
					}
				});
			}
			
		},
		buttons: btnRender(data)
	});
}

function btnRender(data){
	var opBtn = [
					{addClass: 'btn btn-sm btn-primary', text: '<i class="ace-icon fa fa-times  bigger-110"></i>再想想', attr:'data-dismiss="modal"'},
					{addClass: 'btn btn-sm btn-default', text: '<i class="ace-icon fa fa-check  bigger-110"></i>确认提交', onClick: function() {
								_ILOCK();
								var param = {};
								var status= new Array();
								$($('#applyform').serializeArray()).each(function(k, v){
									if(v.value){
										if(v.name=="orderId"){
											status.push(v.value);
										}else{
											param[v.name]=v.value;
										}
									}
								});
								param["orderId"]=status;
								$.ipost(contextPath+'/financing/op/apply', param, function (result) {
									result = $.extend({},data,result);
									$.imodalFresh({
										title :"还款",
										contents:function(){
											var pagefn = doT.template($('#apply_result_temp').text());
											return pagefn(result);
										}
									});
									$('#search_btn').click();
									_IUNLOCK()
								},function(error){
									_IUNLOCK();
									$.ialert(error,"error");
								});
						}
					}
			];
	var apBtn = [
					{addClass: 'btn btn-sm btn-success', text: '<i class="ace-icon fa fa-check  bigger-110"></i>通过', onClick: function(msgDom) {
						if(!$('#applyform').valid()){
							return false;
						}
						_ILOCK();
								var param = {};
								var status= new Array();
								$($('#applyform').serializeArray()).each(function(k, v){
									if(v.value){
										if(v.name=="orderId"){
											status.push(v.value);
										}else{
											param[v.name]=v.value;
										}
									}
								});
								param["orderId"]=status;
							$.ipost(contextPath+'/financing/ap/approval', param, function (result) {
								$.imodalFresh({
									title :"审批",
									contents:function(){
										var pagefn = doT.template($('#approval_result_temp').text());
										return pagefn(result);
									}
								});										
								$('#search_btn').click();
								_IUNLOCK();
							},function(error){
								_IUNLOCK();
								$.imodalClose();
								$.ialert(error,"error");
							});
						}
					},
					{addClass: 'btn btn-sm btn-danger', text: '<i class="ace-icon fa fa-times  bigger-110"></i>残忍拒绝', onClick: function(msgDom) {
						if(!$('#applyform').valid()){
							return false;
						}
						_ILOCK();
						var param = {};
						var status= new Array();
						$($('#applyform').serializeArray()).each(function(k, v){
							if(v.value){
								if(v.name=="orderId"){
									status.push(v.value);
								}else{
									param[v.name]=v.value;
								}
							}
						});
						param["orderId"]=status;
						$.ipost(contextPath+'/financing/ap/reject', param,function (result) {
							$.imodalClose();
							$.ialert(" 残忍拒绝!");
							$('#search_btn').click();
							_IUNLOCK();
						},function(error){
							$.imodalClose();
							_IUNLOCK();
							$.ialert(error,"error");
						});
					}
				}
			];
	if(authType == "op"){
		return opBtn;
	}else if(authType == "ap"){
		return apBtn;
	}
}

