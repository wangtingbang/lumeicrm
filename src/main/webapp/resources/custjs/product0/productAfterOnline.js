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
			"4":"审批通过",
			"5": "即将开始",
			"6": "立即抢购",
			"7": "抢购结束",
			"8": "收益中",
			"9":"还款中",
			"10": "还款结束"
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

	$('#search_btn').click(function() {
		searchSubmit();
	});

	$('#search_btn').click();
});

function searchSubmit() {
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
	var param = {};
	$($('#search_form').serializeArray()).each(function(k, v){
		if(v.value){
			param[v.name]=v.value;
		}
	});
	$page = $('#page').igrid({
		url : contextPath + '/product/listAfterOnlineByPage',
		param : param,
		temp : "grid_temp"
	});
}
