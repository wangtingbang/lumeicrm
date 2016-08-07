$(function () {
	$('.date-timepicker').datetimepicker({
		language: 'en',
		format:'YYYY-MM-DD',
		pickTime: false
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
	var rating = new Array();
	$($('#search_form').serializeArray()).each(function(k, v){
		 if(v.name=="ratingArray"){
			 rating.push(v.value);
		 }else{
			 param[v.name]=v.value;
		 }
	});
	param["rating"]=rating;

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