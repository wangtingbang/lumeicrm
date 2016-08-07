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
			contextPath + '/customer/service/carselling/get',
			{serviceId:param['serviceId']},
			function(data){
				if(data.id=="0"){
					$.ialert("Transaction not exist!");
					setTimeout("backtoprofile()",2000);
					return;
				}
				var pagefn = doT.template($('#step_temp_1').text());
				var htmlpage = pagefn(data);
				$("#car-selling-content").html(htmlpage);
				listNotes();
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

function saveCarSelling(){
	var param = {};
	$($('#submit-form1').serializeArray()).each(function(k, v){
			param[v.name]=v.value;
	});
	param['userId'] = $("#customerId").val();
	$.ipost(
	contextPath + '/customer/service/carselling/save',
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

function deleteCarSelling(id){
	$.iconfirm("Do you want to delete it?",function(){
		$.ipost(
			contextPath+'/customer/service/carselling/delete',
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