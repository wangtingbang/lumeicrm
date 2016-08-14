function listNotes2(customerId,serviceId){
	$page = $('#notesdiv').igrid({
		url : contextPath + '/notes/list',
		paginationBarTemp:"pagination_bar_temp2",
		param : {customerId:customerId,serviceId:serviceId},
		temp : "list_notes_temp",
		rowlist: [10]
	});
}

function addNote2(customerId,serviceId,serviceType){
	var notesfn = doT.template($('#add-notes-temp').text());
	var data = {customerId:customerId,serviceId:serviceId,serviceType:serviceType};
	$.imodal({
		title:"Notes",
		contents:notesfn(data),
		buttons:[
				{addClass: 'btn btn-sm btn-default', text: '<i class="ace-icon fa fa-times  bigger-110"></i>Cancel', attr:'data-dismiss="modal"'},
				{addClass: 'btn btn-sm btn-primary', text: '<i class="ace-icon fa fa-check  bigger-110"></i>Save', onClick: function(msgDom) {
					addNote3();
				}
				}
		]
	});
}

function addNote3(){
	var param = {};
	$($('#add-notes-form').serializeArray()).each(function(k, v){
			param[v.name]=v.value;
	});
	$.ipost(
		contextPath+'/notes/save',
		param,
		function (result) {
			listNotes();
			$.imodalClose();
		}, function(error){
			$.ialert(errmsg,"Fail");
		}
	);
}

function deleteNotes(id){
	$.iconfirm("Do you want to delete it?",function(){
		$.ipost(
			contextPath+'/notes/delete',
			{id:id},
			function(){
				listNotes();
			},
			function(errmsg){
				$.ialert(errmsg,"Fail");
			});
	});
}
