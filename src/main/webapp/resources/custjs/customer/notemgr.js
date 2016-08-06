
function addNote(noteId, noteCrtUId, noteCrtUName, noteCrtTm, noteCont){

	var customerId = $("#customerId").val();
	if(!customerId||customerId===''||customerId==='undefined'){
		$.ialert("Please save profile before add notes");
		return;
	}
	var userId = $("#userId").val();
	var serviceType = $("#noteservicetype-select").val();
	var serviceId = $("#id").val();
	var content = $("#content").val();

	var notesfn = doT.template($('#add-notes-temp').text());

	var t =  new Date(noteCrtTm).toChString(true);
	if(!noteCrtUId||noteCrtUId===''){
		noteCrtUId = $("#currentUserId").val();
	}
	if(!noteCrtUName||noteCrtUName===''){
		noteCrtUName = $("#currentUserName").val();
	}
	var note = {serviceId:serviceId, noteId:noteId, createUserId:noteCrtUId,createUserName:noteCrtUName,createTime:noteCrtTm, content:noteCont};
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
	var serviceId = $("#serviceId").val();
	var content = $("#content").val();
	var noteId = $("#noteId").val();
	var createTime = $("#createTime").val();
	var createUserId = $("#createUserId").val();
	var data = {id:noteId,userId:customerId, noteServiceType:serviceType,serviceId:serviceId,content:content,createTime:createTime, createUserId:createUserId};
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