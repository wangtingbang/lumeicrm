function find(id){
	$.iget(
			contextPath + '/product/find',
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
						var data1 = $.extend({},data.prdRuProduct,{readonly:true,datadic:{productType:datadicArray(datadic["productType"]),incomeDistributionMode:datadicArray(datadic["incomeDistributionMode"]),institution: datadicArray(datadic["institution"]),buyCondition: datadicArray(datadic["buyCondition"])}});
						var data2 = $.extend({},data.prdRuProductExt,{readonly:true});
						var data3 = $.extend({},data.prdRuProductExt,{readonly:true});
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

/*渲染查看图片的colorbox*/
function pic_render(){
	var $overflow = '';
	var colorbox_params = {
//						rel: 'colorbox',
	reposition:true,
	scalePhotos:true,
	scrolling:false,
//						previous:null,
//						next:null,
	close:'&times;',
//						current:null,
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
