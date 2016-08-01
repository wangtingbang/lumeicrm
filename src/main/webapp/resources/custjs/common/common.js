$.iajax = function(options){
	var settings = $.extend({},options);
	var notification = {};
	settings.complete = function(){
		$.ispinner.hide(notification);
	};
	settings.beforeSend=function(){
		notification = $.ispinner.show();
	};
	settings.success = function(result){
		result = eval(result);
		if(result.retCode == "0"){
			options.success(result.retData);
		}else if($.trim(result) == "401.1"){
			$.ios('Login Timeout !');
			setTimeout(function(){location.href=contextPath+"/login";},3000);
		}else if($.trim(result) == "403"){
			$.ios('unauthorized access, we record it !');
		}else if($.trim(result) == "404"){
			$.ios('Not Found !');
		}else if(result.retCode == "001000"){
			$.ios(retCode[result.retCode]);
		}else{
			var retMsg = "";
			if(result.retMsg != undefined){
				retMsg = result.retMsg;
			}else{
				retMsg = retCode[result.retCode];
			}
			//options.failed(retMsg, result.retCode);
			$.ialert(retMsg);
		}
	};
	settings.error = function(result){
		$.ios(result.statusText);
	};
	$.ajax(settings);
};

$.iget = function(url,data,success,failed,dataType){
	$.iajax({
		url: url,
		data: data,
		success: success,
		failed:failed,
		dataType: dataType
	});
};

$.ipost = function(url,data,success,failed,dataType){
	$.iajax({
		type:'POST',
		url: url,
		data: data,
		success: success,
		failed:failed,
		dataType: dataType
	});
};

$.imsg = function(options){
	var info = {
			   clz : 'gritter-info'	
	};
	var success = {
			   clz : 'gritter-success'	
	};
	var warning = {
			   clz : 'gritter-warning'	
	};
	var error = {
			   clz : 'gritter-error'
	};
	var arg;
	if("success" == options.type){
		arg = success;
	}else if("warning" == options.type){
		arg = warning;
	}else if("error" == options.type){
		arg = error;
	}else{
		arg = info;
	}
	$.gritter.add({
		title: options.title,
		text: options.text,
		class_name: arg.clz
	});
};

(function($) {
	$.fn.imsg = function(options){
		var settings = $.extend({}, $.fn.imsg.defaults, options);
		if("success" == settings.type){
			settings.arg=success;
		}
		if("error" == settings.type){
			settings.arg=error;
		}
		var msgContent = load(settings);
		$(this).html(msgContent);
		if(settings.grt == true){
			$.gritter.add({
				title: settings.title,
				text: settings.text,
				class_name: settings.arg.clz?settings.arg.clz:'gritter-info'
			});
		}
	};
	$.fn.imsg.defaults = {
		title : "",
		text : "",
	   type : "warning",
	   arg :{
		   label : 'label-warning',
		   clz : 'gritter-warning'
	    },
		grt : false
	};
	var success = {
			   label : 'label-success',
			   clz : 'gritter-success'	
	};
	var error = {
			   label : 'label-danger',
			   clz : 'gritter-error'
	};
	function load(it) { var out='<div class="alert alert-warning alert-dismissible" role="alemp"><button type="button" class="close" data-dismiss="alert"><i class="ace-icon fa fa-times"></i></button><span class="label '+(it.arg.label)+'"><i class="ace-icon fa fa-exclamation-triangle bigger-120"></i>'+(it.title)+'</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span>'+(it.text)+'</span></div>';return out; }
})($); 

$.ialert = function(text,type){
	var defaults = {
			type: 'information' //alert information error warning notification success
	};
	var settings = $.extend({},defaults,{text:text,type:type});
    var n = noty({
        text        : settings.text,
        type        : settings.type,
        timeout     : 3000,
        closeWith   : ['click'],
        layout      : 'center',
        maxVisible  : 5
    });
}

 function _ILOCK(){
	 if( _ILOCK.lock==false){
		 _ILOCK.lock=true;
		 return false;
	 }
	 return true;
 }
 _ILOCK.lock=false;
 
 function _IUNLOCK(){
	 _ILOCK.lock=false;
 }
 
$.iconfirm = function(text,callback) {
	 if(_ILOCK()){
		 return;
	 }
		var defaults = {
				  title:"Confirm",
				  text: "",
//				  type: "warning", //success error
				  confirmButtonText: "Confirm",
				  showCancelButton:true,
				  cancelButtonText:"Cancel"
		};
		var settings = $.extend({},defaults,{text:text});
		swal(settings,function(isConfirm){
			 _IUNLOCK();
			 if (isConfirm) {
				    if(callback != null){
				        callback();
				    }
			 }
		});
}

$.ios = function(text,flag){
	iosOverlay({
		text: text,
		duration: 2e3,
		icon: flag?contextPath+"/resources/img/check.png":contextPath+"/resources/img/cross.png"
	});
}

$.ispinner = {
		show:function(){
			var opts = {
					lines: 13, // The number of lines to draw
					length: 11, // The length of each line
					width: 5, // The line thickness
					radius: 17, // The radius of the inner circle
					corners: 1, // Corner roundness (0..1)
					rotate: 0, // The rotation offset
					color: '#FFF', // #rgb or #rrggbb
					speed: 1, // Rounds per second
					trail: 60, // Afterglow percentage
					shadow: false, // Whether to render a shadow
					hwaccel: false, // Whether to use hardware acceleration
					className: 'spinner', // The CSS class to assign to the spinner
					zIndex: 2e9, // The z-index (defaults to 2000000000)
					top: 'auto', // Top position relative to parent in px
					left: 'auto' // Left position relative to parent in px
				};
				var target = document.createElement("div");
				document.body.appendChild(target);
				var spinner = new Spinner(opts).spin(target);
				return iosOverlay({
					text: "Loading ...",
					spinner: spinner
				});
		 
		},
		hide:function(notification){
			notification.hide();
		}
}

$.imodal = function(options){
	$.imodalFresh(options);
    $("#modal").modal('show');
};

$.imodalFresh = function(options){
	var defaults = {
			headDisp:true,
			footDisp:true,
			title:'',
	}
	var settings = $.extend({},defaults,options);
	$('#modal').html('');
	var pagefn = doT.template($('#modaltemp').text());
	$('#modal').html(pagefn(settings));
	$("#modal-body").append(settings.contents);
	if(settings.footDisp){
		$(settings.buttons).each(function(k,v){
			if(v.onClick){
				$("#imodalBtn"+k).click(function(){
					v.onClick($("#modalAlertMsgDiv"));
				})
			}
		});
	}
	if(settings.afterRender){
		settings.afterRender();
	}
}

$.imodalClose = function(options){
	 $("#modal").modal('hide');
};

(function($) {
	$.fn.iupload = function(options){
		var settings = $.extend({}, $.fn.iupload.defaults, options);
		var c = content(settings);
		$(this).html(c);
		Dropzone.autoDiscover = false;
		try {
			$("#dropzone").dropzone(settings.dropzone);
		} catch(e) {
		  alert('你的浏览器太落伍啦!文件上传不支持该浏览器,请更换其他浏览器!');
		}
	};
	$.fn.iupload.defaults = {
		url:"/",
		formId:"dropzone",
		text0:"文件上传",
		text1:"拖动文件到这里",
		text2:"(或者请猛击)",
		dropzone:{
			paramName: "file", // The name that will be used to transfer the file
			maxFilesize: 2, // MB
			addRemoveLinks : false,
			dictResponseError: '系统错误!',
			dictInvalidFileType:"无效的文件类型!",
			dictFileTooBig:"文件大小超过限制!",
			init: function() {
		        this.on("addedfile", function(file) {
		        	
		        });
		    },
			dictDefaultMessage :
				'<span class="bigger-150 bolder"><i class="ace-icon fa fa-caret-right red"></i>'+this.text0+'</span> '+this.text1+' \
				<span class="smaller-80 grey">'+this.text2+'</span> <br /> \
				<i class="upload-icon ace-icon fa fa-cloud-upload blue fa-3x"></i>',
			previewTemplate: "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"progress progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>"
		},
		success:function(){
			alert(456);
			return true;
		},
		remove:function(){
			return true;
		}
	};
	function content(it) { var out='<form id="dropzone" class="dropzone dz-clickable" action="'+(it.url)+'"><div class="dz-default dz-message"><span><span class="bigger-150 bolder"><i class="ace-icon fa fa-caret-right red"></i>'+(it.text0)+'</span>'+(it.text1)+'<span class="smaller-80 grey">'+(it.text2)+'</span><br></br><i class="upload-icon ace-icon fa fa-cloud-upload blue fa-3x"></i></span></div></form>';return out; }
})($); 

function logout(){
	location.href=contextPath+'/logout';
}

function resetpwd(){
	var resetpwdfn = doT.template($('#resetpwdtemp').text());
	$.imodal({
		title:"Reset Password",
		contents:resetpwdfn({}),
		buttons:[
				{addClass: 'btn btn-sm btn-default', text: '<i class="ace-icon fa fa-times  bigger-110"></i>Cancel', attr:'data-dismiss="modal"'},
				{addClass: 'btn btn-sm btn-primary', text: '<i class="ace-icon fa fa-check  bigger-110"></i>Confirm', onClick: function(msgDom) {
					resetpwd2(msgDom);
				}
				}
		]
	});
}

function resetpwd2(msgDom){
	$.ipost(contextPath+'/auth/resetpwd', $('#pwdForm').serialize(), function (result) {
		msgDom.imsg({
			type:'success',
			title:"Update Success !",
			text:result.msg
		});
	}, function(error){
		msgDom.imsg({
			title:"Update Fail !",
			text:error
		});
	});
}

jQuery.validator.addMethod("datetime", function (value, element) {
	return this.optional(element) || /^(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1])) (?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]:[0-5][0-9]$/.test(value);
}, "请输入合法的时间");

jQuery.extend(jQuery.validator.messages, {
    required: "必选字段",
	remote: "请修正该字段",
	email: "请输入正确格式的电子邮件",
	url: "请输入合法的网址",
	date: "请输入合法的日期",
	dateISO: "请输入合法的日期 (ISO).",
	number: "请输入合法的数字",
	digits: "只能输入整数",
	creditcard: "请输入合法的信用卡号",
	equalTo: "请再次输入相同的值",
	accept: "请输入拥有合法后缀名的字符串",
	maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
	minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
	rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
	range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
	max: jQuery.validator.format("请输入一个最大为{0} 的值"),
	min: jQuery.validator.format("请输入一个最小为{0} 的值")
});