<?xml version="1.0" encoding="UTF-8"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
	xmlns:c="urn:schemas-microsoft-com:office:component:spreadsheet"
	xmlns:html="http://www.w3.org/TR/REC-html40" xmlns:o="urn:schemas-microsoft-com:office:office"
	xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet" xmlns:x2="http://schemas.microsoft.com/office/excel/2003/xml"
	xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
		<Colors>
			<Color>
				<Index>3</Index>
				<RGB>#c0c0c0</RGB>
			</Color>
			<Color>
				<Index>4</Index>
				<RGB>#ff0000</RGB>
			</Color>
		</Colors>
	</OfficeDocumentSettings>
	<ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
		<WindowHeight>9000</WindowHeight>
		<WindowWidth>13860</WindowWidth>
		<WindowTopX>240</WindowTopX>
		<WindowTopY>75</WindowTopY>
		<ProtectStructure>False</ProtectStructure>
		<ProtectWindows>False</ProtectWindows>
	</ExcelWorkbook>
	<Styles>
		<Style ss:ID="Default" ss:Name="Default">
			<Alignment ss:Vertical="Bottom" />
			<Font ss:FontName="Droid Sans Fallback" />
		</Style>
		<Style ss:ID="Result" ss:Name="Result">
			<Alignment ss:Vertical="Bottom" />
			<Font ss:Bold="1" ss:FontName="Droid Sans Fallback" ss:Italic="1"
				ss:Underline="Single" />
		</Style>
		<Style ss:ID="Result2" ss:Name="Result2">
			<Alignment ss:Vertical="Bottom" />
			<Font ss:Bold="1" ss:FontName="Droid Sans Fallback" ss:Italic="1"
				ss:Underline="Single" />
			<NumberFormat ss:Format="Currency" />
		</Style>
		<Style ss:ID="Heading" ss:Name="Heading">
			<Alignment ss:Vertical="Bottom" />
			<Font ss:Bold="1" ss:FontName="Droid Sans Fallback" ss:Italic="1"
				ss:Size="16" />
		</Style>
		<Style ss:ID="Heading1" ss:Name="Heading1">
			<Alignment ss:Vertical="Bottom" ss:Rotate="90" />
			<Font ss:Bold="1" ss:FontName="Droid Sans Fallback" ss:Italic="1"
				ss:Size="16" />
		</Style>
		<Style ss:ID="co1" />
		<Style ss:ID="co2" />
		<Style ss:ID="co3" />
		<Style ss:ID="ta1" />
		<Style ss:ID="ce1" />
		<Style ss:ID="ce2">
			<Font ss:FontName="Arial" ss:Size="10" />
		</Style>
		<Style ss:ID="T1">
			<Font ss:FontName="Arial" ss:Size="10" ss:VerticalAlign="Subscript" />
		</Style>
		<Style ss:ID="T2">
			<Font ss:FontName="Droid Sans Fallback" ss:Size="10"
				ss:VerticalAlign="Subscript" />
		</Style>
		<Style ss:ID="T3">
			<Font ss:FontName="Times New Roman" ss:Size="12"
				ss:VerticalAlign="Subscript" />
		</Style>
	</Styles>
	<ss:Worksheet ss:Name="工作表1">
		<Table ss:StyleID="ta1">
			<Column ss:Width="146.6362" />
			<Column ss:Width="215.6031" />
			<Column ss:Span="1021" ss:Width="64.0063" />
			
			<Row ss:Height="12.8126">
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">订单ID</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">产品名称</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">手机号码</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">用户名称</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">4S店名称</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">操作</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">本金</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">用户收益</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">平台折扣收益</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">平台投资收益</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">门店收益</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">创建</Data>
				</Cell>
			</Row>
			
  <#list result as obj>
			<Row ss:Height="12.8126">
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">${obj.id!''}</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">${obj.productName!''}</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">${obj.userPhone!''}</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">${obj.userName!''}</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">${obj.storeName!''}</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String"><#if obj.opType == 1>提现</#if><#if obj.opType == 2>消费</#if></Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="Number">${(obj.amount/10000)?string('#.##')}</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="Number">${(obj.userProfit/10000)?string('#.##')}</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="Number">${(obj.dccjProfit/10000)?string('#.##')}</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="Number">${(obj.dccjInvestProfit/10000)?string('#.##')}</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="Number">${(obj.storeProfit/10000)?string('#.##')}</Data>
				</Cell>
				<Cell ss:StyleID="ce1">
					<Data ss:Type="String">${(obj.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}</Data>
				</Cell>
			</Row>
  </#list>
  			
		</Table>
		<x:WorksheetOptions />
	</ss:Worksheet>
</Workbook>