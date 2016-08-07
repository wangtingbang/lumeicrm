
function addNote(){
	var serviceId = $("#id").val();
	if(!serviceId||serviceId===''||serviceId==='undefined'){
		$.ialert("Please save before add notes");
		return;
	}
	var notesfn = doT.template($('#add-notes-temp').text());

	var customerId = $("#customerId").val();
	var data = {serviceId:serviceId,customerId:customerId};

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
	var param = {};
	$($('#add-notes-form').serializeArray()).each(function(k, v){
			param[v.name]=v.value;
	});
	var serviceType = $("#noteservicetype-select").val();
	var data = $.extend({},param,{noteServiceType:serviceType});
	$.ipost(
		contextPath+'/customer/notes/save',
		data,
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
			contextPath+'/customer/notes/delete',
			{id:id},
			function(){
				listNotes();
			},
			function(errmsg){
				$.ialert(errmsg,"Fail");
			});
	});

}
