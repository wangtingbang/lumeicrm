$(function () {
	$('.date-timepicker').datetimepicker({
		language: 'en',
		format:'YYYY-MM-DD HH:mm:ss'
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});

	function createSubmit(){
		// location.href = contextPath + '/customer/getProfile?customerId=';
		window.open(contextPath + '/customer/getProfile?customerId=&customerName=');
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



	var ratingA = $("#form-search-rating-a").prop("checked");
	var ratingB = $("#form-search-rating-b").prop("checked");
	var ratingC = $("#form-search-rating-c").prop("checked");

	var ratingArray = new Array();
	var idx = 0;
	if(ratingA){
		ratingArray[idx] = 1;
		idx++;
	}
	if(ratingB){
		ratingArray[idx] = 2;
		idx++;
	}
	if(ratingC){
		ratingArray[idx] = 3;
		idx++;
	}

	if(!(ratingA||ratingB||ratingC)){
		ratingArray[0] = 1;
		ratingArray[1] = 2;
		ratingArray[2] = 3;
	}

	param = $.extend(param, {statusList:statusArray, ratingList:ratingArray});

	$page = $('#page').igrid({
		url : contextPath + '/customer/list',
		param : param,
		temp : "grid_temp"
	});
}


function viewProfile(customerId, customerName) {
	// location.href = contextPath + '/customer/getProfile?customerId='+customerId+'&customerName='+customerName;
	window.open(contextPath + '/customer/getProfile?customerId='+customerId+'&customerName='+customerName);
}