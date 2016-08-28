$(function () {
	var dealStatusList = '<option value="0">--</option>';
	for(p in datadic['carSaleStatus']){
		dealStatusList += '<option value="'+p+'">'+datadic['carSaleStatus'][p]+'</option>';
	}
	$('#dealStatus').append(dealStatusList);
	
	$('.date-timepicker').datetimepicker({
		language: 'en',
		format:'YYYY-MM-DD'
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	
	$('#searchAll_btn').click(function() {
		searchSubmit();
	});
	
	$('#searchAll_btn').click();

});

function searchSubmit(sales) {
	var param = {};
	$($('#search_form').serializeArray()).each(function(k, v){
			 param[v.name]=v.value;
	});
	if(sales){
		param["salesId"]=sales;
	}
	var d = new Date();
	param["timezoneOffset"] = d.getTimezoneOffset();
	param["rating"]='0';
	$page = $('#page').igrid({
		url : contextPath + '/cardeal/list',
		param : param,
		temp : "grid_temp",
		rowlist : [10,50,100],
		afterRender : function(){
			$(".latest_notes").each(function(){
				$(this).qtip({
		             content: {
		                 text: $(this).next('.tooltiptext')
		             }
		         });
			});
		}
	});
}

function viewDetails(customerId, cardealId) {
	location.href = contextPath + '/cardeal/get?id='+cardealId+"&customerId="+customerId;
}
