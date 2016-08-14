function createDeal(){
var pagefn = doT.template($('#choose-customer-temp').text());
$.imodal({
			title:"Choose Customer",
			contents:pagefn({}),
			buttons:[
					{addClass: 'btn btn-sm btn-default', text: '<i class="ace-icon fa fa-times  bigger-110"></i>Cancel', attr:'data-dismiss="modal"'}
			]
		});

$('#cust_searchAll_btn').click(function() {
	custSearchSubmit();
});
$('#cust_searchMine_btn').click(function() {
	var sales = $("#currentUserId").val();
	custSearchSubmit(sales);
});
$('#cust_searchMine_btn').click();
}

function custSearchSubmit(sales) {
	var param = {};
	$($('#cust_search_form').serializeArray()).each(function(k, v){
			 param[v.name]=v.value;
	});
	if(sales){
		param["sales"]=sales;
	}
	$page = $('#cust_page').igrid({
		url : contextPath + '/customer/list',
		param : param,
		temp : "cust_grid_temp",
		rowlist : [10,50,100]
	});
}
