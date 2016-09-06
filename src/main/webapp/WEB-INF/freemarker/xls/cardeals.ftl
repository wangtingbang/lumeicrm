"Sales Name","Customer Name","Source","Remark","Car Info","New/Used","Car Sale Status","Status Date","Modified Date"
<#list result as obj>
"${obj.salesName!''}","${obj.customerName!''}","${obj.sourceString!''}","${obj.remark!''}","${obj.years!''}/${obj.model!''}","<#if obj.isNew == 1>New</#if><#if obj.isNew == 2>Used</#if>","${obj.dealStatusString!''}","${(obj.dealDate?string("yyyy-MM-dd"))!''}","${(obj.updateTime?string("yyyy-MM-dd"))!''}"
</#list>