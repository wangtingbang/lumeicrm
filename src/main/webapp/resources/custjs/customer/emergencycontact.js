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

				}
				var a = !param['customerId'];
				if(!param['customerId']&&!$("#serviceId-this")&&!$("#serviceId-this").val()){
					data['total']="6";
					data['used']="0";

					/*
					var expirationDate = new Date();
					expirationDate.setFullYear(expirationDate.getFullYear() + 1);
					data['expirationDate'] = expirationDate;//*/
				}
				var pagefn = doT.template($('#step_temp_1').text());
				var htmlpage = pagefn(data);
				$("#emergency-contact-content").html(htmlpage);

				$('.date-timepicker').datetimepicker({
					language: 'en',
					format:'YYYY-MM-DD',
					pickTime: false
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});

				listNotes();

				$page = $('#event-content').igrid({
					url : contextPath + '/customer/service/emergencycontact/useList',
					param : $.extend({},param,{serviceId:$("#serviceId-this").val()}),
					temp : "event_grid_temp"
				});
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}//*/
	);
}

function listNotes(){
	var id = $("#id").val();
	$page = $('#notesdiv').igrid({
		url : contextPath + '/customer/notes/listByPage',
		paginationBarTemp:"pagination_bar_temp2",
		param : {serviceId:id},
		temp : "notes_temp",
		rowlist: [5]
	});
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
		$.ialert("Save success!");
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

function deleteEmergencyContact(id){
	$.iconfirm("Are you sure to delete?",function(){
		$.ipost(
			contextPath+'/customer/service/emergencycontact/delete',
			{id:id},
			function(){
				$.ialert("Success!");
				// searchSubmit();
				var customerId = $("#customerId");
				var customerName = $("#customerName");
				location.href = contextPath+'/customer/getProfile?customerId='+customerId+'&customerName='+customerName;
			},
			function(errmsg){
				$.ialert(errmsg,"Fail");
			});
	});
}