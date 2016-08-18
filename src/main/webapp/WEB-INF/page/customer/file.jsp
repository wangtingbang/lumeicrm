<script src="<%=request.getContextPath()%>/resources/custjs/customer/file.js"></script>
<script id="file-temp" type="text/x-dot-template">
{{~it.data :p:index}}
<label class="control-label no-padding-top">
<a href="javascript:viewFile('{{=p.id||''}}');">
{{=p.fileName||''}}
</a>&nbsp;&nbsp;
<a href="javascript:deleteFile('{{=p.id||''}}');">
<i class="ace-icon fa fa-trash-o red"></i>
</a>
</label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
{{~}}
</script>