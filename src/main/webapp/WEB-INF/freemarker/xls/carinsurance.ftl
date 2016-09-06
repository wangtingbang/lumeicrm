"Sales Name","Customer Name","Create Date","Insurance Company","Price","Term"
<#list result as obj>
"${obj.salesName!''}","${obj.customerName!''}","${(obj.createTime?string("yyyy-MM-dd"))!''}","${obj.insuranceCompany!''}","${obj.price!''}","${obj.terms!''}"
</#list>