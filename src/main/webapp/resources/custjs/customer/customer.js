$(function () {
	searchSubmit();
});

function searchSubmit(){
	var customerId = $("#customerId").val();

	$.iget(
			contextPath + '/customer/get/getCustomer',
			{customerId:customerId},
			function(data){
				if(data.id=="1"){
					$.ialert("customer not exist!");
					setTimeout("backtolist()",2000);
					return;
				}
				var pagefn = doT.template($('#profile_temp').text());
				var htmlpage = pagefn(data);
				$("#profile-content").html(htmlpage);
				var dealSelectList = '';
				for(p in datadic['serviceType']){
					dealSelectList += '<option value="'+p+'">'+datadic['serviceType'][p]+'</option>';
				}
				$('#service').append(dealSelectList);
				listNotes();
				$('#addDeal_btn').click(function() {
					var id = $("#id").val();
					if(!id||id=='0'||id=='1'||id==''
						||id=='null'||id=='undefined'){
						$.ialert("Please save before add deal");
						return;
					}
					addDeal(id);
				});
				fileList(customerId);
				fileupload(customerId,customerId);
				$page = $('#transaction-content').igrid({
					url : contextPath + '/transaction/list',
					param : {customerId:$("#id").val()},
					temp : "transaction_grid_temp",
					rowlist: [10],
					paginationBarTemp:"pagination_bar_temp2"
				});

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
	if(param['name'] == ''){
		$.ialert("Customer Name Required!","error");
		return;
	}
	if(param['wechat'] == ''){
		$.ialert("WeChat Required!","error");
		return;
	}
	var carSelling = $("#service-car-selling-checkbox").prop("checked");
	var emergencyContact = $("#service-emergency-contact-checkbox").prop("checked");

	var service = '';
	if(carSelling){
		service+='1,';
	}
	if(emergencyContact){
		service+='2,';
	}
	
	service = service.substring(0,service.length-1);
	param['service'] = service;

	$.ipost(
	contextPath + '/customer/save',
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

function deleteProfile(id){
	$.iconfirm("Do you want to delete it?",function(){
		$.ipost(
			contextPath+'/customer/delete',
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
	location.href=contextPath+"/customer/list";
}

function listNotes(){
	var id = $("#id").val();
	listNotes2(id,id);
}

function addNote(){
	var id = $("#id").val();
	if(!id||id=='0'||id=='1'||id==''
		||id=='null'||id=='undefined'){
		$.ialert("Please save before add notes");
		return;
	}
	addNote2(id,id,0);
}

function serviceTypeCheck(serviceType, serviceTypeString){
	var stArray = serviceTypeString.split(",");
	for (i=0;i<stArray.length;i++){
		if(serviceType==stArray[i]){
			return true;
		}
	}
	return false;
}

function addDeal(id){
	var deal = $("#service").val();
	if("1"==deal){
		location.href=contextPath+'/cardeal/create?customerId='+id;
	}
}

function viewTran(serviceType,serviceId,customerId){
	if('1'==serviceType){
		location.href = contextPath + '/cardeal/get?id='+serviceId+"&customerId="+customerId;
	}
}

function fileList2(){
	var customerId = $("#customerId").val();
	fileList(customerId);
}