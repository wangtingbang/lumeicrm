$(function () {
	$('#create_btn').click(function() {
		$.imodal({
			title:"Add User",
			contents:function(){
				var pagefn = doT.template($('#create_temp').text());
				return pagefn({});
			},
			buttons:[
						{addClass: 'btn btn-sm btn-default', text: '<i class="ace-icon fa fa-times  bigger-110"></i>Cancel', attr:'data-dismiss="modal"'},
						{addClass: 'btn btn-sm btn-primary', text: '<i class="ace-icon fa fa-check  bigger-110"></i>Confirm', onClick: function(msgDom) {
								$.ipost(contextPath+'/auth/user/create', $('#createuserform').serialize(), function (result) {
									msgDom.imsg({
										type:'success',
										title:"Create Success !",
										text:result.msg
									});
									$('#search_btn').click();
								},function(error){
									msgDom.imsg({
										title:"Create Fail !",
										text:error
									});
								});
							}
						}
				]
		});
	});
	
	$('#search_btn').click(function() {
		searchSubmit();
	});
	
	$('#search_option').change(function() {
		var v = $(this).val();
		if (v) {
			 var $dom = setSearch(v);
		} 
	});
	setSearch($('#search_option').val());
	$('#search_btn').click();
});

function setSearch(v) {
	var pagefn = doT.template($('#search_' + v
			+ '_temp').text());
	var ipt = $("#search_input").html('');
	var $dom = $(pagefn()).appendTo(ipt);
	return $dom;
}

function searchSubmit() {
	var param = {};
	$('#search_form').find(":input").each(function(k, v) {
		var name = $(v).attr('name');
		var val = $(v).val();
		if (name) {
			param[name] = $(v).val();
		}
	});
	 $page = $('#page').igrid({
				url : contextPath + '/auth/user/listUserByPage',
				param : param,
				temp : "grid_temp"
			});
}

function updateuser(id, userName, nickName){
	$.imodal({
		title:"Edit User",
		contents:function(){
			var pagefn = doT.template($('#update_user_temp').text());
			return pagefn({id:id, userName: userName, nickName:nickName});
		},
		buttons:[
					{addClass: 'btn btn-sm btn-default', text: '<i class="ace-icon fa fa-times  bigger-110"></i>Cancel', attr:'data-dismiss="modal"'},
					{addClass: 'btn btn-sm btn-primary', text: '<i class="ace-icon fa fa-check  bigger-110"></i>Confirm', onClick: function(msgDom) {
						$.ipost(contextPath+'/auth/user/update', $('#updateuserform').serialize(), function (result) {
								msgDom.imsg({
									type:'success',
									title:"Update Success !",
									text:result.msg
								});
								$('#search_btn').click();
							},function(error){
								msgDom.imsg({
									title:"Update Fail !",
									text:error
								});
							});
						}
					}
			]
	});
}

function resetuserpwd(id,name){
	 $.iconfirm("Are you sure reset "+name+"'s password?", function(){
			$.ipost(
					contextPath+"/auth/user/resetuserpwd",
					{userId:id},
					function(result){
						$.ialert("Reset Success !");
					},
					function(error){
						$.ialert("Reset Fail !");
					}
			);
		 });
}

function lockSubmit(id){
	$.ipost(
		contextPath+"/auth/user/lock",
		{userId:id},
		function(result){
			$.ialert("Update Success !");
			$('#search_btn').click();
		},
		function(error){
			$.ialert("Update Fail !");
		}
	);
}

function userRole(id, name){
	var pagefn = doT.template($('#user_role_temp').text());
	$.imodal({
		title:"Add Roles - "+name,
		contents:pagefn({id:id}),
		buttons:[
		         {addClass: 'btn btn-sm btn-default', text: '<i class="ace-icon fa fa-times  bigger-110"></i>Cancel', attr:'data-dismiss="modal"'}
			]
	});
	getRole();
	listUsrRole(id);
	$('#usrRoleBtn').click(function () {
		if($("#role_select").val()==""){
			alert("no role selected !");
			return false;
		}else{
			$.ipost(contextPath+"/auth/user/addUserRole", 
				$('#userRoleForm').serialize(), function (result) {
				listUsrRole(id);
			});
		}
	});
}

function getRole(){
	var url = contextPath+"/auth/user/listAllRole";
	$.iget(url,"",function(obj){
		var a = obj.allRoles;
		$("#role_select").append(opts(a));
		$("#role_select").chosen({allow_single_deselect:true, width: "100%"}); 
	});
}

function opts(it) {
	var out = '';
	var arr1 = it;
	if (arr1) {
		var value, index = -1, l1 = arr1.length - 1;
		while (index < l1) {
			value = arr1[index += 1];
			out += '<option value=\'' + (value.key) + '\'>' + (value.desc)
					+ '</option>';
		}
	}
	return out;
}

function listUsrRole(usrId){
	$("#usr_role_org").html('');
	var url = contextPath+"/auth/user/listUserRoleByUserId?userId="+usrId;
	$.iget(url,"",function(obj){
		var a = obj.list;
		$("#usr_role_org").append(roles(a));
	});
}

function roles(it) {
	var out = '';
	var arr1 = it;
	if (arr1) {
		var value, index = -1, l1 = arr1.length - 1;
		while (index < l1) {
			value = arr1[index += 1];
			out += '<div class="btn-group" style="margin:1px 2px;"><button class="btn btn-sm btn-yellow">'
					+ (value.roleName)
					+ '</button><button data-toggle="dropdown" class="btn btn-sm btn-yellow dropdown-toggle"><i class="ace-icon fa fa-angle-down icon-only"></i></button><ul class="dropdown-menu dropdown-yellow"><li><a href="javascript:delUsrRole(\''
					+ (value.id)+'\',\''+(value.userId)
					 + '\');">Delete</a></li></ul></div>';
		}
	}
	return out;
}

function delUsrRole(id,userId){
		var url = contextPath+"/auth/user/delUserRole?id="+id;
		$.iget(url, {}, function(result){
			listUsrRole(userId);
		});
}