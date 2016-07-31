<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="page-header">
	<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-10">
			<h1>
			<small>
				<i class="ace-icon fa fa-home home-icon"></i>
				<a href="<%=request.getContextPath() %>/">主页</a>
				<i class="ace-icon fa fa-angle-double-right"></i>
				<a href="#">固定收益产品管理</a>
				<i class="ace-icon fa fa-angle-double-right"></i>
				项目发起
			</small>
			</h1>
		</div>
		<div class="col-sm-2 center">
			<a class="btn btn-sm btn-success " id="create_btn">
				 <i class="ace-icon fa fa-plus bigger-110"></i> 新增
			</a>
		</div>
	</div>
	</div>
</div>
<div class="row">
<div class="col-xs-12">
		<form id="search_form" class="form-horizontal" action="#">
		<div class="row">
			<div class="col-sm-5 form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">产品名称</label>
				<div class="col-sm-9">
		    		<input id="form-field-1" class="form-control" type="text" name="productName"></input>
				</div>
			</div>
			<div class="col-sm-6 form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-2">产品系列</label>
				<div class="col-sm-4">
					<select id="form-field-2" class="form-control" name="productType">
						<option value="0">全部</option>
					</select>
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="form-field-3">产品状态</label>
				<div class="col-sm-4">
					<select id="form-field-3" class="form-control" name="status">
					</select>
				</div>
			</div>
			<div class="col-sm-1 form-group"></div>
			</div>
			<div class="row">
			<div class="col-sm-5 form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-4-1">产品总额</label>
				<div class="col-sm-4">
					<input id="form-field-4-1" class="form-control" type="text" name="productAmountStart"></input>
				</div>
				<label class="col-sm-1 control-label" for="form-field-4-2" style="text-align: center;">~</label>
				<div class="col-sm-4">
					<input id="form-field-4-2" class="form-control" type="text" name="productAmountEnd"></input>
				</div>
			</div>
			<div class="col-sm-6 form-group">
				<label class="col-sm-2 control-label no-padding-right" for="">操作时间</label>
				<div class="col-sm-4">
					<input id="form-field-5-1" class="date-timepicker form-control col-sm-12 " type="text" name="updateTimeStart"></input>
				</div>
				<label class="col-sm-2" for="" style="text-align: center;">~</label>
				<div class="col-sm-4">
					<input id="form-field-5-2" class="date-timepicker form-control col-sm-12 " type="text" name="updateTimeEnd"></input>
				</div>
			</div>
			<div class="col-sm-1 form-group center">
				<a class="btn btn-info btn-sm" id="search_btn">
					 <i class="ace-icon fa fa-search  bigger-110"></i> 查询
				</a>
			</div>
			</div>
		</form>
	</div>
</div>
<div class="row">
<div class="col-xs-12">
		<hr class="no-margin-top"></hr>
		<div class="row">
				<div class="col-xs-12 table-responsive" id="page"></div>
		</div>
</div>
</div>

<script id="grid_temp" type="text/x-dot-template">
	<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>产品名称</th>
			<th>产品总额(元)</th>
			<th>产品系列</th>
			<th>预期年收益</th>
			<th>上线时间</th>
			<th>操作时间</th>
			<th>状态</th>
			<th>执行</th>
		</tr>
	</thead>
	<tbody>
	{{~it.data :p:index}}
		<tr>
			<td>
			<a href="javascript:find('{{=p.id }}');">	{{=p.productName }}</a>
			</td>
			<td>
				{{=p.productAmount.formatter(2,4) }}
			</td>
			<td>
				{{=datadic['productType'][p.productType] }}
			</td>
			<td>
				{{=p.annualReturnRate.formatter(2,4) }}%
			</td>
			<td>
				{{=new Date(p.onlineTime).toChString(true) }}
			</td>
			<td>
				{{=new Date(p.updateTime).toChString(true) }}
			</td>
			<td class="center">
			{{? 1== p.status}}
				<span class="label label-sm label-primary">&nbsp;&nbsp;{{=datadic['productStatus'][p.status] }}&nbsp;&nbsp;</span>
			{{?? 2==p.status}}
				<span class="label label-sm label-danger">&nbsp;&nbsp;&nbsp;&nbsp;{{=datadic['productStatus'][p.status] }}&nbsp;&nbsp;&nbsp;&nbsp; </span>
			{{?? 3==p.status}}
				<span class="label label-sm label-warning">&nbsp;&nbsp;{{=datadic['productStatus'][p.status] }}&nbsp;&nbsp; </span>
			{{?? 4==p.status}}
				<span class="label label-sm label-success">{{=datadic['productStatus'][p.status] }} </span>
			{{?}}
			</td>
			<td>
			{{? 1 == p.status || 2 == p.status}}
			<div class="hidden-sm hidden-xs action-buttons">
				<a class="blue" href="javascript:update('{{=p.id }}');" data-rel="tooltip" title="修改" data-original-title="修改">
				<i class="ace-icon fa fa-pencil bigger-130"></i>修改
				</a>
				<a class="blue" href="javascript:del('{{=p.id }}');" data-rel="tooltip" title="删除" data-original-title="删除">
				<i class="ace-icon fa fa-trash-o bigger-130"></i>删除
				</a>
				<a class="blue" href="javascript:apply('{{=p.id }}');" data-rel="tooltip" title="提交" data-original-title="提交">
				<i class="ace-icon glyphicon glyphicon-share bigger-130"></i>提交
				</a>
			</div>
			<div class="hidden-md hidden-lg">
				<div class="inline position-relative">
					<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">
						<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i>
					</button>
					<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
						<li>
							<a class="blue" href="javascript:update('{{=p.id }}');" data-rel="tooltip" title="修改" data-original-title="修改">
								<i class="ace-icon fa fa-pencil bigger-130"></i>   修改
							</a>
						</li>
						<li>
							<a class="blue" href="javascript:del('{{=p.id }}');" data-rel="tooltip" title="删除" data-original-title="删除">
								<i class="ace-icon fa fa-undo bigger-130"></i>   删除
							</a>
						</li>
						<li>
							<a class="blue" href="javascript:apply('{{=p.id }}');" data-rel="tooltip" title="提交" data-original-title="提交">
								<i class="ace-icon glyphicon glyphicon-share bigger-130"></i>   提交
							</a>
						</li>
					</ul>
				</div>
			</div>
			{{?}}
			</td>
		</tr>
{{~}}
{{? !it.data.length}}
<tr ><td colspan="12">没有相关记录</td></tr>
{{?}}
	</tbody>
</table>
</script>

<%@ include file="producttemp.jsp" %>

<script src="<%=request.getContextPath()%>/resources/custjs/product/product.js"></script>
<script src="<%=request.getContextPath()%>/resources/custjs/product/findproduct.js"></script>
