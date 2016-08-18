function fileupload(userId,serviceId){
	$('#files_btn').click(function() {
		if(!serviceId||serviceId=='0'||serviceId=='1'||serviceId==''
			||serviceId=='null'||serviceId=='undefined'){
			$.ialert("Please save before upload files");
			return;
		}
		$.ajaxFileUpload
        (
            {
                url: contextPath+'/files/upload?userId='+userId+'&serviceId='+serviceId,
                secureuri: false,
                fileElementId: 'file',
                dataType: 'json',
                success: function (data, status)
                {
                	$.ialert("Upload Success!");
                	setTimeout("fileList2()",2000);
                },
                error: function (data, status, e)
                {
                    $.ialert("Upload Failed!");
                    setTimeout("fileList2()",2000);
                }
            }
        );
	});
}

function fileList(id){
	$.iget(
			contextPath + '/files/list/'+id,
			{},
			function(data){
				var fn = doT.template($('#file-temp').text());
				$("#file").val("");
				$("#fileListDiv").html("");
				$("#fileListDiv").append(fn({data:data}));
			}
	);
}

function viewFile(id){
	location.href=contextPath + '/files/download/'+id;
}

function deleteFile(id){
	$.iget(
			contextPath + '/files/delete/'+id,
			{},
			function(data){
				$.ialert("Delete Success!");
				setTimeout("fileList2()",2000);
			}
	);
}
