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

	searchSubmit();

});

function searchSubmit(){
	var param = {};

	$($('#search_form').serializeArray()).each(function(k, v){
			param[v.name]=v.value;
		// }
	});

	$.iget(
			contextPath + '/customer/service/carselling/get',
			{customerId:param['customerId']},
//			temp : "grid_temp"
				//*
			function(data){
				var pagefn = doT.template($('#step_temp_1').text());
				data = $.extend(data, {customerId:param['customerId']});
				var htmlpage = pagefn(data);
				$("#car-selling-content").html(htmlpage);
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}//*/
	);
}

function saveCarSelling(){
	$.ialert("save profile...");
	var param = {};
	$($('#submit-form1').serializeArray()).each(function(k, v){
		if(!(v.value === '' || v.value == null || v.value === 'undefined')){
			param[v.name]=v.value;
		}
	});
//	$.ialert(param);

	var customerId = $("#customerId").val();

	param['userId'] = customerId;
	$.ipost(
	contextPath + '/customer/service/carselling/save',
	param,
	function(){
		//TODO
		$.ialert("Saved!");
		searchSubmit();
	},
	function(errmsg){
		$.ialert("Save failed! "+errmsg,"error");
	}
	);
}
