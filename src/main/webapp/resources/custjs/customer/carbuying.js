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
			contextPath + '/customer/service/carbuying/get',
			{customerId:param['customerId']},
//			temp : "grid_temp"
				//*
			function(data){
				var pagefn = doT.template($('#step_temp_1').text());
				data = $.extend(data, {customerId:param['customerId']});
				var htmlpage = pagefn(data);
				$("#car-buying-content").html(htmlpage);
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}//*/
	);
}

function saveCarBuying(){
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
	contextPath + '/customer/service/carbuying/save',
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
