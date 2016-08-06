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
		$.ialert("Saved!");
		location.href = contextPath + '/customer/list?active=customerList';
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
//			temp : "grid_temp"
				//*
			function(data){
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


				$page = $('#notesdiv').igrid({
					url : contextPath + '/customer/notes/listByPage',
					paginationBarTemp:"pagination_bar_temp2",
					param : {customerId:param['customerId']},
					temp : "notes_temp"
				});
				$page = $('#transaction-content').igrid({
					//url : contextPath + '/consume/'+authType + '/postsalepay/listConsume',
					url : contextPath + '/customer/transaction/listByPage',
					param : param,
					temp : "transaction_grid_temp"
				});

			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}//*/
	);
}

//*
function addTransaction(customerId, customerName){

	if(!customerId||customerId===''||customerId==='undefined'){
		$.ialert("Please save profile before add notes");
		return;
	}

	var notesfn = doT.template($('#add-tran-temp').text());

	var data = $.extend({},{userId:customerId,customerName:customerName,customerId:customerId,datadic:{serviceType:datadicArray(datadic["serviceType"])}});

	$.imodal({
		title:"Add Transaction",
		contents:notesfn(data),
		buttons:[
				{addClass: 'btn btn-sm btn-default', text: '<i class="ace-icon fa fa-times  bigger-110"></i>Cancel', attr:'data-dismiss="modal"'},
				{addClass: 'btn btn-sm btn-primary', text: '<i class="ace-icon fa fa-check  bigger-110"></i>Save', onClick: function(msgDom) {
					addTransaction2(msgDom);
				}
				}
		]
	});
}

function addTransaction2(msgDom) {

	var customerId = $("#customerId").val();
	var customerName = $("#customerName").val();
	if ($("#tran-servicetype-select").val() == 1) {
		location.href = contextPath + '/customer/getCarSelling?customerId='+customerId+"&customerName="+customerName;
	}else if ($("#tran-servicetype-select").val() ==2) {
		location.href = contextPath + '/customer/getEmergencyContact?customerId='+customerId+"&customerName="+customerName
	}


}

function deleteNotes(id){
	$.iconfirm("Confirm to delete this note?",function(){
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
//*/
function addCarSelling(customerId, customerName) {
	if(!customerId||customerId===''||customerId==='undefined'){
		$.ialert("Please save profile before add car buying service");
		return;
	}
	window.open(contextPath + '/customer/getCarSelling?customerId='+customerId+"&customerName="+customerName);
}

function addEmergencyContact(customerId, customerName) {
	if(!customerId||customerId===''||customerId==='undefined'){
		$.ialert("Please save profile before add emergency contact service");
		return;
	}

	//window.open.location.href = contextPath + '/customer/getProfile?customerId=';
	window.open(contextPath + '/customer/getEmergencyContact?customerId='+customerId+"&customerName="+customerName);
}

function viewTran(serviceType, customerId, customerName) {
	if(!customerId||customerId==null||customerId===''||customerId==='undefined'){
		customerId = $("#customerId").val();
		customerName = $("#customerName").val();
	}
	if (serviceType == 1) {
		location.href = contextPath + '/customer/getCarSelling?customerId='+customerId+"&customerName="+customerName;
	}else if (serviceType ==2) {
		location.href = contextPath + '/customer/getEmergencyContact?customerId='+customerId+"&customerName="+customerName
	}else{
		location.href = "#";
	}

}


function deleteProfile(id){
	$.iconfirm("Are you sure to delete?",function(){
		$.ipost(
			contextPath+'/customer/profile/delete',
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