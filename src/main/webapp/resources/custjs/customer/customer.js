$(function () {
	$('.date-timepicker').datetimepicker({
		language:'zh-CN',
		format:'YYYY-MM-DD HH:mm:ss'
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});

	function createSubmit(){
		location.href = contextPath + '/customer/getProfile?customerId=';
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

	$($('#search_form').serializeArray()).each(function(k, v){
		// if(v.value){
			param[v.name]=v.value;
		// }
	});

	var statusAppointmented = $("#form-search-status-appointmented").prop("checked");
	var statusSoldbylumei = $("#form-search-status-soldbylumei").prop("checked");
	var statusBuyfromother = $("#form-search-status-buyfromother").prop("checked");
	var statusNoresponse = $("#form-search-status-noresponse").prop("checked");
	var statusStillinthemarket = $("#form-search-status-stillinthemarket").prop("checked");

	var statusArray = new Array();
	var idx = 0;
	if(statusAppointmented){
		statusArray[idx] = 1;
		idx++;
	}
	if(statusSoldbylumei){
		statusArray[idx] = 2;
		idx++;
	}
	if(statusBuyfromother){
		statusArray[idx] = 3;
		idx++;
	}
	if(statusNoresponse){
		statusArray[idx] = 4;
		idx++;
	}
	if(statusStillinthemarket){
		statusArray[idx] = 5;
		idx++;
	}

	if(!(statusAppointmented||statusSoldbylumei||statusBuyfromother||statusNoresponse||statusStillinthemarket)){
		statusArray[0] = 1;
		statusArray[1] = 2;
		statusArray[2] = 3;
		statusArray[3] = 4;
		statusArray[4] = 5;
	}


	param = $.extend(param, {statusList:statusArray});

	$page = $('#page').igrid({
		//url : contextPath + '/consume/'+authType + '/postsalepay/listConsume',
		url : contextPath + '/customer/list',
		param : param,
		temp : "grid_temp"
	});
}
