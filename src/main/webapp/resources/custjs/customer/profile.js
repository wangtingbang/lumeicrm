$(function () {
	$('.date-timepicker').datetimepicker({
		language:'zh-CN',
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
	$.ialert("save profile...");
	var param = {};
	$($('#submit-form1').serializeArray()).each(function(k, v){
		if(!(v.value === '' || v.value == null || v.value === 'undefined')){
			param[v.name]=v.value;
		}
	});
//	$.ialert(param);

	var data = $('#submit-form1').serializeArray();
	$.ipost(
	contextPath + '/customer/profile/create',
	param,
	function(){
		//TODO
		$.ialert("Saved!");
		searchSubmit();
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

				$page = $('#page').igrid({
					//url : contextPath + '/consume/'+authType + '/postsalepay/listConsume',
					url : contextPath + '/customer/notes/listByPage?page=1&limit=10',
					param : param,
					temp : "grid_temp"
				});

			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}//*/
	);
}


function addNote(){
	var notesfn = doT.template($('#add-notes-temp').text());
	var data = $.extend({},{datadic:{serviceType:datadicArray(datadic["serviceType"])}});
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
	$.ipost(
		contextPath+'/customer/notes/create',
		$('#add-notes-form').serialize(),
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
