"Sales Name","Customer Name","WeChat","Phone","Email","Modified Date"
<#list result as obj>
"${obj.sales!''}","${obj.name!''}","${obj.wechat!''}","${obj.phone!''}","${obj.email!''}","${(obj.updateTime?string("yyyy-MM-dd"))!''}"
</#list>