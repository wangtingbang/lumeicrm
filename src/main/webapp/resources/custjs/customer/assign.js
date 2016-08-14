function assign(ids){
var pagefn = doT.template($('#assign-temp').text());
$.imodal({
			title:"Assign To",
			contents:pagefn({}),
			buttons:[
					{addClass: 'btn btn-sm btn-default', text: '<i class="ace-icon fa fa-times  bigger-110"></i>Cancel', attr:'data-dismiss="modal"'},
					{addClass: 'btn btn-sm btn-primary', text: '<i class="ace-icon fa fa-check  bigger-110"></i>Assign', onClick: function(msgDom) {
						var sales = $("#assignee").val();
						assign2(sales,ids);
						$.imodalClose();
					}
					}
			],
			afterRender:function(){
				$.iget(
						contextPath + '/auth/user/listAllUserEnabled',
						{},
						function(data){
							var userList = '';
							for(p in data){
								userList += '<option value="'+data[p].id+'">'+data[p].nickName+'</option>';
							}
							$('#assignee').append(userList);
						},
						function(errmsg){
							$.ialert(errmsg,"error");
						}
				);
			}
		});
}