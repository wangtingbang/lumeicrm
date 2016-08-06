$(function () {
	$('.date-timepicker').datetimepicker({
		language: 'en',
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
				if(!data.expirationDate){
					var expirationDate = new Date();
					expirationDate.setFullYear(expirationDate.getFullYear() + 1);

					data['expirationDate'] = expirationDate;
				}
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
	var param = {};
	$($('#submit-form1').serializeArray()).each(function(k, v){
		if(!(v.value === '' || v.value == null || v.value === 'undefined')){
			param[v.name]=v.value;
		}
	});

	var customerId = $("#customerId").val();
	param = $.extend({}, param, {userId:customerId});
	$.ipost(
	contextPath + '/customer/service/emergencycontact/save',
	param,
	function(){
		$.ialert("Saved!");
		searchSubmit();
	},
	function(errmsg){
		$.ialert("Save failed! "+errmsg,"error");
	}
	);
}

function useService(id) {

	var customerId = $("#customerId").val();
	$.iconfirm("Confirm to use?",function(){
		$.ipost(
			contextPath+'/customer/service/emergencycontact/use',
			{id:id,userId:customerId},
			function(){
				$.ialert("Success!");
				searchSubmit();
			},
			function(errmsg){
				$.ialert(errmsg,"Fail");
			});
	});
}