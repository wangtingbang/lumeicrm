$(function () {
	$('#search_btn').click(function() {
		searchSubmit();
	});
	
	$('#search_option').change(function() {
		var v = $(this).val();
		if (v) {
			 var $dom = setSearch(v);
		} 
	});
	setSearch($('#search_option').val());
	$('#search_btn').click();
});

function setSearch(v) {
	var pagefn = doT.template($('#search_' + v
			+ '_temp').text());
	var ipt = $("#search_input").html('');
	var $dom = $(pagefn()).appendTo(ipt);
	return $dom;
}

function searchSubmit() {
	var param = {};
	$('#search_form').find(":input").each(function(k, v) {
		var name = $(v).attr('name');
		var val = $(v).val();
		if (name) {
			param[name] = $(v).val();
		}
	});
	 $page = $('#page').igrid({
				url : contextPath + '/auth/user/listUserRoleByPage',
				param : param,
				temp : "grid_temp"
			});
}