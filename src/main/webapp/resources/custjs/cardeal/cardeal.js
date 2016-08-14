$(function () {
	searchSubmit();
});

function searchSubmit(){
	var serviceId = $("#serviceIdSearch").val();
	var customerId = $("#customerIdSearch").val();
	$.iget(
			contextPath + '/cardeal/get/getCardeal',
			{customerId:customerId,id:serviceId},
			function(data){
				if(data.id=="1"){
					$.ialert("Deals not exist!");
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
				for(p in datadic['carSaleStatus']){
					if(data.dealStatus==p){
						dealStatusList += '<option selected="selected" value="'+p+'">'
						+datadic['carSaleStatus'][p]+'</option>';
					}else{
						dealStatusList += '<option value="'+p+'">'+datadic['carSaleStatus'][p]+'</option>';
					}
				}
				$('#dealStatus').append(dealStatusList);
				var ratingList = '';
				for(p in datadic['customerRating']){
					if(data.rating==p){
						ratingList += '<option selected="selected" value="'+p+'">'
						+datadic['customerRating'][p]+'</option>';
					}else{
						ratingList += '<option value="'+p+'">'+datadic['customerRating'][p]+'</option>';
					}
				}
				$('#rating').append(ratingList);
				var depositList = '';
				for(p in datadic['deposit']){
					if(data.deposit==p){
						depositList += '<option selected="selected" value="'+p+'">'
						+datadic['deposit'][p]+'</option>';
					}else{
						depositList += '<option value="'+p+'">'+datadic['deposit'][p]+'</option>';
					}
				}
				$('#deposit').append(depositList);
				
				$("#isNew input[name='isNew']").each(function(n,p){
					$(this).click(function(){
						isNewCheck();
					});
				});
				
				isNewCheck();
				
				$("#method input[name='method']").each(function(n,p){
					$(this).click(function(){
						methodCheck();
					});
				});
				
				methodCheck();
				
				listNotes();
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}
	);
}

function methodCheck(){
	var v = $("#method input[name='method']:checked").val();
	if(1==v){
		$("#downPayment").val("");
		$("#rate").val("");
		$("#terms").val("");
		$("#monthlyPay").val("");
		
		$("#downPaymentdiv").hide();
		$("#ratediv").hide();
		$("#termsdiv").hide();
		$("#monthlyPaydiv").hide();
	}else if(2==v){
		$("#downPaymentdiv").show();
		$("#ratediv").show();
		$("#termsdiv").show();
		$("#monthlyPaydiv").show();
	}else if(3==v){
		$("#rate").val("");
		
		$("#downPaymentdiv").show();
		$("#ratediv").hide();
		$("#termsdiv").show();
		$("#monthlyPaydiv").show();
	}
}

function isNewCheck(){
	var v = $("#isNew input[name='isNew']:checked").val();
	if(1==v){
		$("#mileages").val("");
		$("#msrpdiv").show();
		$("#mileagesdiv").hide();
	}else if(2==v){
		$("#msrp").val("");
		$("#mileagesdiv").show();
		$("#msrpdiv").hide();
	}
}

function saveProfile(){
	var param = {};
	$($('#submit-form').serializeArray()).each(function(k, v){
			param[v.name]=v.value;
	});
	if(param['dealDate'] == ''){
		$.ialert("Date Required!","error");
		return;
	}
	if(param['deposit'] == ''){
		$.ialert("Deposit Required!","error");
		return;
	}
	if(param['creditCardNo'] == ''){
		$.ialert("CC# Required!", "error");
		return;
	}

	$.ipost(
	contextPath + '/cardeal/save',
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
			contextPath+'/cardeal/delete',
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
	location.href=contextPath+"/cardeal/list";
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
	addNote2(customerId,id,1);
}