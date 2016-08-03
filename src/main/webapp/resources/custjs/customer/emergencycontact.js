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
	});

	$.iget(
			contextPath + '/customer/service/emergencycontact/get',
			{customerId:param['customerId']},
			function(data){
				var pagefn = doT.template($('#step_temp_1').text());
				var htmlpage = pagefn(data);
				$("#emergency-contact-content").html(htmlpage);
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}//*/
	);
}

function saveEmergencyContact(){
	$.ialert("save profile...");
	var param = {};
	$($('#submit-form1').serializeArray()).each(function(k, v){
		if(!(v.value === '' || v.value == null || v.value === 'undefined')){
			param[v.name]=v.value;
		}
	});
//	$.ialert(param);

	var data = $('#submit-form1').serializeArray();
	var customerId = $("#customerId").val();
	data = $.extend({}, data, {userId:customerId});
	$.ipost(
	contextPath + '/customer/service/emergencycontact/save',
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
