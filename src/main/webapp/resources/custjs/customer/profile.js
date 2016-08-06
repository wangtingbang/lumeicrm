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
function addNote(noteId, noteCrtUId, noteCrtUName, noteCrtTm, noteCont){

	var customerId = $("#customerId").val();
	if(!customerId||customerId===''||customerId==='undefined'){
		$.ialert("Please save profile before add notes");
		return;
	}
	var userId = $("#userId").val();
	var serviceType = $("#noteservicetype-select").val();
	var serviceType1 = $("#noteServiceType").val();
	var content = $("#content").val();

	var notesfn = doT.template($('#add-notes-temp').text());

	var t =  new Date(noteCrtTm).toChString(true);
	if(!noteCrtUId||noteCrtUId===''){
		noteCrtUId = $("#currentUserId").val();
	}
	if(!noteCrtUName||noteCrtUName===''){
		noteCrtUName = $("#currentUserName").val();
	}
	var note = {noteId:noteId, createUserId:noteCrtUId,createUserName:noteCrtUName,createTime:noteCrtTm, content:noteCont};
	var data = $.extend({},{userId:customerId,datadic:{serviceType:datadicArray(datadic["serviceType"])}},note);

	$.imodal({
		title:"Notes",
		contents:notesfn(data),
		buttons:[
				{addClass: 'btn btn-sm btn-default', text: '<i class="ace-icon fa fa-times  bigger-110"></i>Cancel', attr:'data-dismiss="modal"'},
				{addClass: 'btn btn-sm btn-primary', text: '<i class="ace-icon fa fa-check  bigger-110"></i>Save', onClick: function(msgDom) {
					addNote2(msgDom);
				}
				}
		]
	});
}

function addNote2(msgDom){

	var customerId = $("#customerId").val();
	var userId = $("#userId").val();
	var serviceType = $("#noteservicetype-select").val();
	var content = $("#content").val();
	var noteId = $("#noteId").val();
	var createTime = $("#createTime").val();
	var createUserId = $("#createUserId").val();
	var data = {id:noteId,userId:customerId, noteServiceType:serviceType,content:content,createTime:createTime, createUserId:createUserId};
	$.ipost(
		contextPath+'/customer/notes/save',
		data,
		function (result) {
			msgDom.imsg({
				type:'success',
				title:"Success !",
				text:result.msg
			});
		}, function(error){
			msgDom.imsg({
				title:"Fail!",
				text:error
			});
		}
	);
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