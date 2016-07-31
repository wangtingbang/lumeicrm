(function($) {
  $.fn.igrid = function(options) {
	  var settings = $.extend({}, $.fn.igrid.defaults, options);
	  return this.each(function() {
		     var $this=$(this);
		     $this.data("settings",settings);
	        load($this);
	   });
  };

	function calpage(page,totalPage){
		var pageArray = new Array();
		var pagecnt = totalPage < 5 ? totalPage : 5;
		var pagestart = page + pagecnt > totalPage ? totalPage - pagecnt + 1 : page
		for (var i = 0; i < pagecnt; i++) {
			pageArray[i] = pagestart + i;
		}
		return pageArray
	}
	
	function render($this, data){
		var settings = $this.data("settings");
		var pagefn = doT.template($('#'+settings.temp).text());
		$this.html(pagefn({'data':data.result}));
		var pageData = $.extend({}, settings, {firstpage:data.page==1, page:data.page,  limit:data.limit, total:data.total, totalPage:data.totalPage, pageArray:calpage(data.page, data.totalPage)});
		var pagebarfn = doT.template($('#pagination_bar_temp').text());
		$this.append(pagebarfn(pageData));
		
		$this.find("ul li ").not(".disabled").find("a").click(function(){
			var v = ($(this).find("input").val());
			if(v>0&& v<=data.totalPage){
				var settings = $this.data("settings");
				settings.page=v;
				load($this);
			}
		});
		
		$this.find("select").change(function(){
			var v = $(this).val();
			var settings = $this.data("settings");
			settings.limit=v;
			settings.page=1;
			load($this);
		});
	}
  	
  function load($this) {
	  var settings = $this.data("settings");
	  var param=$.extend({}, settings.param, {page:settings.page,  limit:settings.limit, order: settings.order, ordername:settings.ordername});
	  $.ipost(
			  settings.url,
			  param,
			  function(result){
				  render($this, result);
				  if(settings.afterRender!=null && typeof(settings.afterRender)=='function'){
					  settings.afterRender(result.result);
				  }
			  },
			  function(msg){
				  $.ialert(msg,"error");
			  }
	  );
  };
  
  $.fn.igrid.defaults = {
     page: 1,
     limit: 10,
     pagenum:5,
     rowlist: [10,20,30],
     url:null,
     param:null,
     order:null,
     ordername:null,
     temp:null,
     afterRender:null
  };
})(jQuery); 