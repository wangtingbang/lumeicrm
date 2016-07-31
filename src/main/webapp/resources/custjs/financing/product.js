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
		    "9": "还款中",
		    "10": "还款结束"
		}
		for(p in _productStatus){
			$("#form-field-3").append("<option value='"+p+"'>"+_productStatus[p]+"</option>");
		}

	$('#search_btn').click(function() {
		searchSubmit();
	});

	$('#search_btn').click();

});

function searchSubmit() {
	var param = {};
	$($('#search_form').serializeArray()).each(function(k, v){
		if(v.value){
			param[v.name]=v.value;
		}
	});
	$page = $('#page').igrid({
		url : contextPath + '/financing/'+authType+'/listProduct',
		param : param,
		temp : "grid_temp"
	});
}

function invrec(id){
	location.href = contextPath + '/financing/'+authType+'/listInvRec?pid='+id+"&active=productRepayment";
}

function repayappr(id){
	location.href = contextPath + '/financing/'+authType+'/listInvRec?pid='+id+"&active=approvalProductRepayment";
}

