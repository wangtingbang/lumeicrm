$(function () {
	$('.date-timepicker').datetimepicker({
		language: 'en',
		format:'YYYY-MM-DD'
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	
	$('#create_btn').click(function() {
		createDeal();
	});

	$('#searchAll_btn').click(function() {
		searchSubmit();
	});
	
	$('#searchMine_btn').click(function() {
		var sales = $("#currentUserId").val();
		searchSubmit(sales);
	});
	
	$('#export_btn').click(function() {
		exportData();
	});
	
	$('#assign_btn').click(function() {
		var ids = new Array();
		$("[name=checkItem]:checkbox").each(function(){
			if($(this).prop("checked")){
				var id = $(this).attr("data_id");
				ids.push(id);
			}
		});
		if(ids.length == 0){
			$.ialert("please checked one record atleast");
			return;
		}
		assign(ids);
	});
	
	$('#searchMine_btn').click();

});

function searchSubmit(sales) {
	var param = {};
	$($('#search_form').serializeArray()).each(function(k, v){
			 param[v.name]=v.value;
	});
	if(sales){
		param["salesId"]=sales;
	}
	var d = new Date();
	param["timezoneOffset"] = d.getTimezoneOffset();
	$page = $('#page').igrid({
		url : contextPath + '/carinsurance/list',
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
			$(".latest_notes").each(function(){
				$(this).qtip({
		             content: {
		                 text: $(this).next('.tooltiptext')
		             }
		         });
			});
		}
	});
}

function viewDetails(customerId, id) {
	location.href = contextPath + '/carinsurance/get?id='+id+"&customerId="+customerId;
}

function addDeal(customerId) {
	location.href = contextPath + '/carinsurance/create?customerId='+customerId;
}

function assign2(sales,ids){
	$.ipost(
			contextPath + '/carinsurance/assign',
			{sales:sales,ids:ids},
			function(data){
				$.ialert("Assign success!");
				setTimeout("$('#searchMine_btn').click();",2000);
			},
			function(errmsg){
				$.ialert("assign failed! "+errmsg,"error");
			}
	);
}

function exportData(){
	var pagefn = doT.template($('#export-temp').text());
	$.imodal({
				title:"Data Export Condition",
				contents:pagefn({}),
				buttons:[
						{addClass: 'btn btn-sm btn-default', text: '<i class="ace-icon fa fa-times  bigger-110"></i>Cancel', attr:'data-dismiss="modal"'},
						{addClass: 'btn btn-sm btn-primary', text: '<i class="ace-icon fa fa-check  bigger-110"></i>Export', onClick: function(msgDom) {
							var param = "";
							$($('#export-form').serializeArray()).each(function(k, v){
								param+= v.name+'='+v.value+'&';
							});
							var d = new Date();
							param+= 'timezoneOffset='+d.getTimezoneOffset();
							location.href = contextPath + '/carinsurance/export?'+param;
						}
						}
				],
				afterRender:function(){
					$('.date-timepicker').datetimepicker({
						language: 'en',
						format:'YYYY-MM-DD'
					}).next().on(ace.click_event, function(){
						$(this).prev().focus();
					});
				}
			});
}