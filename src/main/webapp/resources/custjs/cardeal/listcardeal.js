$(function () {
	var dealStatusList = '<option value="0">--</option>';
	for(p in datadic['carSaleStatus']){
		if("1"==p){
			dealStatusList += '<option selected="selected" value="'+p+'">'
			+datadic['carSaleStatus'][p]+'</option>';
		}else{
			dealStatusList += '<option value="'+p+'">'+datadic['carSaleStatus'][p]+'</option>';
		}
	}
	$('#dealStatus').append(dealStatusList);
	
	var dealStatusList = '<option value="0">--</option>';
	for(p in datadic['customerRating']){
		dealStatusList += '<option value="'+p+'">'+datadic['customerRating'][p]+'</option>';
	}
	$('#rating').append(dealStatusList);
	
	$('.date-timepicker').datetimepicker({
		language: 'en',
		format:'YYYY-MM-DD'
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	
	$('#create_btn').click(function() {
		createDeal();
	});

	$('#searchAll_btn').click(function() {
		searchSubmit();
	});
	
	$('#searchMine_btn').click(function() {
		var sales = $("#currentUserId").val();
		searchSubmit(sales);
	});
	
	$('#assign_btn').click(function() {
		var ids = new Array();
		$("[name=checkItem]:checkbox").each(function(){
			if($(this).prop("checked")){
				var id = $(this).val();
				ids.push(id);
			}
		});
		if(ids.length == 0){
			$.ialert("please checked one record atleast");
			return;
		}
	});
	
	$('#searchMine_btn').click();

});

function searchSubmit(sales) {
	var param = {};
	$($('#search_form').serializeArray()).each(function(k, v){
			 param[v.name]=v.value;
	});
	if(sales){
		param["salesId"]=sales;
	}
	$page = $('#page').igrid({
		url : contextPath + '/cardeal/list',
		param : param,
		temp : "grid_temp",
		rowlist : [10,50,100],
		afterRender : function(){
			$("#checkAll").change(function(){
				if($(this).prop("checked")){
					$("[name=checkItem]:checkbox").each(function(k,v){
						if(!$(this).prop("disabled")){
							$(this).prop("checked",true);
						}
					});
				}else{
					$("[name=checkItem]:checkbox").each(function(k,v){
						if(!$(this).prop("disabled")){
							$(this).prop("checked",false);
						}
					});
				}
			});
		}
	});
}

function viewDetails(customerId, cardealId) {
	location.href = contextPath + '/cardeal/get?id='+cardealId+"&customerId="+customerId;
}

function addDeal(customerId) {
	location.href = contextPath + '/cardeal/create?customerId='+customerId;
}
