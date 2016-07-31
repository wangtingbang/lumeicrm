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

	$('#search_btn').click();

});

function searchSubmit() {
	var param = {};
	var status= new Array();
	$($('#search_form').serializeArray()).each(function(k, v){
		if(v.value){
			if(v.name=="statusArray"){
				status.push(v.value);
			}else{
				param[v.name]=v.value;
			}
		}
	});
	param["status"]=status;
	$page = $('#page').igrid({
		url : contextPath + '/product/'+authType+'/listInvestList',
		param : param,
		rowlist : [20,50,200],
		temp : "grid_temp"
	});
}
