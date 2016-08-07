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
			{serviceId:param['serviceId']},
			function(data){
				if(data.id=="0"){
					$.ialert("Transaction not exist!");
					setTimeout("backtoprofile()",2000);
					return;
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
					param : {serviceId:param['serviceId']},
					temp : "event_grid_temp",
					rowlist: [100],
					paginationBarTemp:"pagination_bar_temp3"
				});
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}
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
			param[v.name]=v.value;
	});
	param['userId'] = $("#customerId").val();
	$.ipost(
	contextPath + '/customer/service/emergencycontact/save',
	param,
	function(data){
		$.ialert("Save success!");
		$("#serviceId").val(data);
		setTimeout("searchSubmit()",2000);
	},
	function(errmsg){
		$.ialert("Save failed! "+errmsg,"error");
	}
	);
}

function useService(id) {
	if(!id||id===''||id==='undefined'||id==='null'){
		$.ialert("Please save Emergency Contact before use");
		return;
	}
	$.iconfirm("Do you want to use Emergency Contact Service?",function(){
		$.ipost(
			contextPath+'/customer/service/emergencycontact/use',
			{serviceId:id},
			function(data){
				$.ialert("Success!");
				setTimeout("searchSubmit()",2000);
			},
			function(errmsg){
				$.ialert(errmsg,"Fail");
				setTimeout("searchSubmit()",2000);
			});
	});
}

function deleteEmergencyContact(id){
	$.iconfirm("Do you want to delete it?",function(){
		$.ipost(
				contextPath+'/customer/service/emergencycontact/delete',
				{id:id},
				function(){
					$.ialert("Delete success!");
					setTimeout("backtoprofile()",2000);
					},
				function(errmsg){
					$.ialert(errmsg,"Fail");
				});
	});
}

function backtoprofile(){
	var customerId = $("#customerId").val();
	location.href = contextPath+'/customer/getProfile?customerId='+customerId;
}