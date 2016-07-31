$(function () {
	$('.date-timepicker').datetimepicker({
		language:'zh-CN',
		format:'YYYY-MM-DD HH:mm:ss'
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});

	for(p in datadic["productType"]){
		$("#form-field-2").append("<option value='"+p+"'>"+datadic["productType"][p]+"</option>");
	}

	var _productStatus = {
			"0":"全部",
			"2":"退回",
			"3":"未审批",
			"4":"审批通过"
	}

	for(p in _productStatus){
		$("#form-field-3").append("<option value='"+p+"'>"+_productStatus[p]+"</option>");
	}

	$.iget(
			contextPath + '/product/listInstitution',
			{},
			function(data){
				var obj = $("#assetsAssignor-select");
				for(var i=0;i<data.institution.length;i++){
					var obj123={};
					var keyname = data.institution[i].id;
					var valuename = data.institution[i].companyName;
					obj123[keyname]=valuename;
					datadic["institution"] = $.extend({},datadic["institution"],obj123);
				}
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}
	);

	$('#search_btn').click(function() {
		searchSubmit();
	});

	$('#search_btn').click();
});

function searchSubmit() {
	var prdouctAmountStart = $("#form-field-4-1").val();
	if(prdouctAmountStart != null && prdouctAmountStart != ""){
		if(!jQuery.isNumeric( prdouctAmountStart )){
			$.ialert("请填写正确的产品总额数值", "error");
			return;
		}
	}
	var prdouctAmountEnd = $("#form-field-4-2").val();
	if(prdouctAmountEnd != null && prdouctAmountEnd != ""){
		if(!jQuery.isNumeric( prdouctAmountEnd )){
			$.ialert("请填写正确的产品总额数值", "error");
			return;
		}
	}
	var param = {};
	$($('#search_form').serializeArray()).each(function(k, v){
		if(v.value){
			param[v.name]=v.value;
		}
	});
	$page = $('#page').igrid({
		url : contextPath + '/product/listByPageForApproval',
		param : param,
		temp : "grid_temp"
	});
}

function approval(id, userName, nickName){
	$.imodal({
		title:"产品复核-审批意见",
		contents:function(){
			var pagefn = doT.template($('#approval_temp').text());
			return pagefn({id:id});
		},
		afterRender:function(){
			$('textarea.limited').inputlimiter({
				remText: '剩余%n字...',
				limitText: '最大允许: %n字.'
			});
			$('#approvalform').validate({
				errorElement: 'div',
				errorClass: 'help-block',
				focusInvalid: false,
				highlight: function (e) {
					$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
				},
				success: function (e) {
					$(e).closest('.form-group').removeClass('has-error');//.addClass('has-info');
					$(e).remove();
				},
				rules: {
					comments:{
						required: true,
						maxlength:50
					}
				}
			});
		},
		buttons:[
					{addClass: 'btn btn-sm btn-default pull-left', text: '<i class="ace-icon fa fa-times bigger-110"></i>取消', attr:'data-dismiss="modal"'},
					{addClass: 'btn btn-sm btn-danger', text: '<i class="ace-icon fa fa-ban  bigger-110"></i>拒绝', onClick: function(msgDom) {
						if(!$('#approvalform').valid()){
							return false;
						}
						var jobj = $('#approvalform').serializeArray();
						var data = {};
						$(jobj).each(function(k,v){
							data[v.name]=v.value;
						});

						data = $.extend({},data,{isApproved:false});
						$.iconfirm("确认退回?",function(){
							$.ipost(contextPath+'/product/approval', data, function (result) {
									$.ialert("操作成功!");
									$.imodalClose();
									$('#search_btn').click();
								},function(error){
									msgDom.imsg({
										title:"操作失败",
										text:error
									});
								});
						});
						}
					},
					{addClass: 'btn btn-sm btn-primary', text: '<i class="ace-icon fa fa-check  bigger-110"></i>通过', onClick: function(msgDom) {
						if(!$('#approvalform').valid()){
							return false;
						}
						var jobj = $('#approvalform').serializeArray();
						var data = {};
						$(jobj).each(function(k,v){
							data[v.name]=v.value;
						});

						data = $.extend({},data,{isApproved:true});
						$.iconfirm("确认审批通过?",function(){
							$.ipost(contextPath+'/product/approval', data, function (result) {
									$.ialert("操作成功!");
									$.imodalClose();
									$('#search_btn').click();
								},function(error){
									msgDom.imsg({
										title:"操作失败",
										text:error
									});
								});
							});
						}
					}
			]
	});
}
