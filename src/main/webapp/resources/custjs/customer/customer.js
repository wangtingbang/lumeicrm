$(function () {
	$('.date-timepicker').datetimepicker({
		language: 'en',
		format:'YYYY-MM-DD',
		pickTime: false
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});

	function createSubmit(){
		// location.href = contextPath + '/customer/getProfile?customerId=';
		window.open(contextPath + '/customer/getProfile?customerId=&customerName=');
	}
	$('#create_btn').click(function() {
		createSubmit();
	});

	$('#search_btn').click(function() {
		searchSubmit();
	});

	$('#search_btn').click();

});

function searchSubmit() {
	var param = {};
	var rating = new Array();
	rating.push(-1);
	$($('#search_form').serializeArray()).each(function(k, v){
		 if(v.name=="ratingArray"){
			 rating.push(v.value);
		 }else{
			 param[v.name]=v.value;
		 }
	});
	param["rating"]=rating;

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
			$("#send_email_btn").click(function(){
				var emailAddress = new Array();
				var eaString="";
				$("[name=checkItem]:checkbox").each(function(){
					if($(this).prop("checked")){
						var ea = $(this).val();
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
		}
	});
}


function viewProfile(customerId, customerName) {
	// location.href = contextPath + '/customer/getProfile?customerId='+customerId+'&customerName='+customerName;
	window.open(contextPath + '/customer/getProfile?customerId='+customerId+'&customerName='+customerName);
}