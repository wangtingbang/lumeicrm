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

	$page = $('#page').igrid({
		//url : contextPath + '/consume/'+authType + '/postsalepay/listConsume',
		url : contextPath + '/customer/list',
		param : param,
		temp : "grid_temp"
	});
}
