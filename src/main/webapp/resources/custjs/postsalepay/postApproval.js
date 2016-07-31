$(function () {
	$('.date-timepicker').datetimepicker({
		language:'zh-CN',
		format:'YYYY-MM-DD HH:mm:ss'
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});

	var _productStatus = {
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
	var param = {};
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
	$($('#search_form').serializeArray()).each(function(k, v){
		if(v.value){
			param[v.name]=v.value;
		}
	});
	$page = $('#page').igrid({
		url : contextPath + '/postsalepay/listByPageForApproval',
		param : param,
		temp : "grid_temp"
	});
}

function find(id){
	$.iget(
			contextPath + '/postsalepay/find',
			{id:id},
			function(data){
				$.imodal({
					headDisp:false,
					footDisp:false,
					afterRender:function(){
						$('#fuelux-wizard')
						.ace_wizard({})
						.on('change' , function(e, info){
						}).on('stepclick', function(e){});
						pic_render();
					},
					contents:function(){
						var pagefn = doT.template($('#create_temp').text());
						var pagefn1 = doT.template($('#step_temp_1').text());
						var pagefn2 = doT.template($('#step_temp_2').text());
						var pagefn3 = doT.template($('#step_temp_3').text());
						var data1 = $.extend({},data.prdRuPostsalepay,{readonly:true,datadic:{postsalepayIncomeDistributionMode:datadicArray(datadic["postsalepayIncomeDistributionMode"]),institution: datadicArray(datadic["institution"])}});
						var data2 = $.extend({},data.prdRuPostsalepayExt,{readonly:true});
						var data3 = $.extend({},data.prdRuPostsalepayExt,{readonly:true});
						return pagefn({
							title:'产品详情',
							page1:pagefn1(data1),
							page2:pagefn2(data2),
							page3:pagefn3(data3)
						});
					}
				});
			},
			function(errmsg){
				$.ialert(errmsg,"error");
			}
	);
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
							$.ipost(contextPath+'/postsalepay/approval', data, function (result) {
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
							$.ipost(contextPath+'/postsalepay/approval', data, function (result) {
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

/*渲染查看图片的colorbox*/
function pic_render(){
	var $overflow = '';
	var colorbox_params = {
	reposition:true,
	scalePhotos:true,
	scrolling:false,
	close:'&times;',
	maxWidth:'100%',
	maxHeight:'100%',
	onOpen:function(){
		$overflow = document.body.style.overflow;
		document.body.style.overflow = 'hidden';
	},
	onClosed:function(){
		document.body.style.overflow = $overflow;
	},
	onComplete:function(){
		$.colorbox.resize();
	}
	};
	$('.gallery').colorbox(colorbox_params);
	$("#cboxLoadingGraphic").append("<i class='ace-icon fa fa-spinner orange'></i>");//let's add a custom loading icon
}
