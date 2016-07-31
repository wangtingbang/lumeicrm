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
		"8":"收益中",
		"9":"还款中",
		"10":"还款结束"
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
		url : contextPath + '/product/listZMProductByPage',
		param : param,
		temp : "grid_temp"
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
			valueDate = new Date();
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

function setZMPaid(id){
	$.iconfirm("确定修改产品状态？产品修改修改的同时对应的订单状态也将被修改",function(){
		$.ipost(
				contextPath + '/product/setZMPaid',
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
