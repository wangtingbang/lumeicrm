$(function () {
	$('#create_btn').click(function() {
		location.href = contextPath + '/customer/create';
	});

	$('#searchAll_btn').click(function() {
		searchSubmit();
	});
	
	$('#searchMine_btn').click(function() {
		var sales = $("#currentUserId").val();
		searchSubmit(sales);
	});
	
	$('#assign_btn').click(function() {
		var ids = new Array();
		$("[name=checkItem]:checkbox").each(function(){
			if($(this).prop("checked")){
				var id = $(this).val();
				ids.push(id);
			}
		});
		if(ids.length == 0){
			$.ialert("please checked one record atleast");
			return;
		}
	});
	
	$('#email_btn').click(function() {
		var emailAddress = new Array();
		var eaString="";
		$("[name=checkItem]:checkbox").each(function(){
			if($(this).prop("checked")){
				var ea = $(this).attr("data_email");
				alert(ea);
				emailAddress.push(ea);
				if(ea && ea!="" && ea !="null" && ea!="undefined"){
					eaString+=ea+";"
				}
			}
		});
		if(emailAddress.length == 0){
			$.ialert("please checked one record atleast");
			return;
		}
		window.location.href = 'mailto:' + eaString;
	});
	
	$('#searchMine_btn').click();

});

function searchSubmit(sales) {
	var param = {};
	$($('#search_form').serializeArray()).each(function(k, v){
			 param[v.name]=v.value;
	});
	if(sales){
		param["sales"]=sales;
	}
	$page = $('#page').igrid({
		url : contextPath + '/customer/list',
		param : param,
		temp : "grid_temp",
		rowlist : [10,50,100],
		afterRender : function(){
			$("#checkAll").change(function(){
				if($(this).prop("checked")){
					$("[name=checkItem]:checkbox").each(function(k,v){
						if(!$(this).prop("disabled")){
							$(this).prop("checked",true);
						}
					});
				}else{
					$("[name=checkItem]:checkbox").each(function(k,v){
						if(!$(this).prop("disabled")){
							$(this).prop("checked",false);
						}
					});
				}
			});
		}
	});
}

function viewProfile(customerId) {
	location.href = contextPath + '/customer/get?customerId='+customerId;
}

function serviceTypeDisp(serviceType){
	var serviceTypeString = '';
	var stArray = serviceType.split(",");
	for (i=0;i<stArray.length;i++){
		var s = datadic['serviceType'][stArray[i]];
		if(!s){
			continue;
		}
		serviceTypeString += s + ' / ';
	}
	serviceTypeString = serviceTypeString.substring(0, serviceTypeString.length-3)
	return serviceTypeString;
}