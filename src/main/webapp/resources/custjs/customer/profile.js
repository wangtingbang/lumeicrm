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

function saveProfile(){
	var param = {};
	$($('#submit-form1').serializeArray()).each(function(k, v){
		if(!(v.value === '' || v.value == null || v.value === 'undefined')){
			param[v.name]=v.value;
		}
	});

	var carSelling = $("#service-car-selling-checkbox").prop("checked");
	var emergencyContact = $("#service-emergency-contact-checkbox").prop("checked");

	var service = '';
	if(carSelling){
		service+='1';
	}else{
		service+='0';
	}
	if(emergencyContact){
		service+='1';
	}else{
		service+='0';
	}


	param['service'] = service;

	$.ipost(
	contextPath + '/customer/profile/save',
	param,
	function(data){
		$.ialert("Save success!");
		$("#customerId").val(data);
		setTimeout("searchSubmit()",2000);
	},
	function(errmsg){
		$.ialert("Save failed! "+errmsg,"error");
	}
	);
}

function searchSubmit(){
	var param = {};

	$($('#search_form').serializeArray()).each(function(k, v){
			param[v.name]=v.value;
		// }
	});

	$.iget(
			contextPath + '/customer/profile/get',
			{customerId:param['customerId']},
			function(data){
				if(data.id=="0"){
					$.ialert("customer not exist!");
					setTimeout("backtolist()",2000);
					return;
				}
				var pagefn = doT.template($('#step_temp_1').text());
				var htmlpage = pagefn(data);
				$("#profile-content").html(htmlpage);
				$('.date-timepicker').datetimepicker({
					language: 'en',
					format:'YYYY-MM-DD',
					pickTime: false
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});

				listNotes();
				$page = $('#transaction-content').igrid({
					url : contextPath + '/customer/transaction/listByPage',
					param : {customerId:$("#id").val()},
					temp : "transaction_grid_temp"
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

function addTransaction(customerId, customerName){

	if(!customerId||customerId===''||customerId==='undefined'){
		$.ialert("Please save profile before create transaction");
		return;
	}

	var notesfn = doT.template($('#add-tran-temp').text());

	var data = $.extend({},{customerId:customerId,customerName:customerName});

	$.imodal({
		title:"Create Transaction",
		contents:notesfn(data),
		buttons:[
				{addClass: 'btn btn-sm btn-default', text: '<i class="ace-icon fa fa-times  bigger-110"></i>Cancel', attr:'data-dismiss="modal"'},
				{addClass: 'btn btn-sm btn-primary', text: '<i class="ace-icon fa fa-check  bigger-110"></i>Create', onClick: function(dom) {
					addTransaction2(data);
				}
				}
		]
	});
}

function addTransaction2(data) {
	var customerId = data.customerId;
	var customerName = data.customerName;
	if ($("#tran-servicetype-select").val() == 1) {
		location.href = contextPath + '/customer/getCarSelling?customerId='+customerId+"&customerName="+customerName;
	}else if ($("#tran-servicetype-select").val() ==2) {
		location.href = contextPath + '/customer/getEmergencyContact?customerId='+customerId+"&customerName="+customerName
	}
}

function deleteNotes(id){
	$.iconfirm("Do you want to delete it?",function(){
		$.ipost(
			contextPath+'/customer/notes/delete',
			{id:id},
			function(){
				$.ialert("Success!");
				searchSubmit();
			},
			function(errmsg){
				$.ialert(errmsg,"Fail");
			});
	});
}

function viewTran(serviceType, serviceId) {
	customerId = $("#customerId").val();
	customerName = $("#customernamehidden").val();
	if (serviceType == 1) {
		location.href = contextPath + '/customer/getCarSelling?customerId='+customerId+"&customerName="+customerName+"&serviceId="+serviceId;
	}else if (serviceType ==2) {
		location.href = contextPath + '/customer/getEmergencyContact?customerId='+customerId+"&customerName="+customerName+"&serviceId="+serviceId;
	}else{
		location.href = "#";
	}

}

function deleteProfile(id){
	$.iconfirm("Do you want to delete it?",function(){
		$.ipost(
			contextPath+'/customer/profile/delete',
			{id:id},
			function(){
				$.ialert("Delete success!");
				setTimeout("backtolist()",2000);
			},
			function(errmsg){
				$.ialert(errmsg,"Fail");
			});
	});
}

function backtolist(){
	location.href=contextPath+"/customer/list";
}
