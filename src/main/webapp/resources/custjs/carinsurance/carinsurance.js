$(function () {
	searchSubmit();
});

function searchSubmit(){
	var serviceId = $("#serviceIdSearch").val();
	var customerId = $("#customerIdSearch").val();
	var carDealId = $("#carDealIdSearch").val();
	$.iget(
			contextPath + '/carinsurance/get/getCarInsurance',
			{customerId:customerId,id:serviceId,carDealId:carDealId},
			function(data){
				if(data.id=="1"){
					$.ialert("Insurance not exist!");
					setTimeout("backtolist()",2000);
					return;
				}
				var pagefn = doT.template($('#profile_temp').text());
				var htmlpage = pagefn(data);
				$("#profile-content").html(htmlpage);
				$('.date-timepicker').datetimepicker({
					language: 'en',
					format:'YYYY-MM-DD'
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				
				var dealStatusList = '';
				for(p in datadic['carInsurStatus']){
					if(data.status==p){
						dealStatusList += '<option selected="selected" value="'+p+'">'
						+datadic['carInsurStatus'][p]+'</option>';
					}else{
						dealStatusList += '<option value="'+p+'">'+datadic['carInsurStatus'][p]+'</option>';
					}
				}
				$('#status').append(dealStatusList);
				
				fileList(serviceId);
				fileupload(customerId,serviceId);
				
				listNotes();
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}
	);
}


function saveProfile(){
	var param = {};
	$($('#submit-form').serializeArray()).each(function(k, v){
			param[v.name]=v.value;
	});
	$.ipost(
	contextPath + '/carinsurance/save',
	param,
	function(data){
		$.ialert("Save success!");
		$("#serviceIdSearch").val(data);
		setTimeout("searchSubmit()",2000);
	},
	function(errmsg){
		$.ialert("Save failed! "+errmsg,"error");
	}
	);
}

function deleteProfile(id){
	$.iconfirm("Do you want to delete it?",function(){
		$.ipost(
			contextPath+'/carinsurance/delete',
			{id:id},
			function(){
				$.ialert("Delete success!");
				setTimeout("backtolist()",2000);
			},
			function(errmsg){
				$.ialert("Delete failed! "+errmsg,"error");
			});
	});
}

function backtolist(){
	location.href=contextPath+"/carinsurance/list";
}

function listNotes(){
	var id = $("#id").val();
	listNotes2(0,id);
}

function addNote(){
	var id = $("#id").val();
	var customerId = $("#customerId").val();
	if(!id||id=='0'||id=='1'
		||id==''||id=='null'||id=='undefined'){
		$.ialert("Please save before add notes");
		return;
	}
	addNote2(customerId,id,9);
}

function fileList2(){
	var serviceId = $("#serviceIdSearch").val();
	fileList(serviceId);
}