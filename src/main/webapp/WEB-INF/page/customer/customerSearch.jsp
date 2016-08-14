<script src="<%=request.getContextPath()%>/resources/custjs/customer/customerSearch.js"></script>
<script id="choose-customer-temp" type="text/x-dot-template">
        <form id="cust_search_form" class="form-horizontal" action="#">
        <div class="row">
        <div class="col-xs-12">
        
        <div class="col-sm-3 form-group">
        <label class="col-sm-4 control-label no-padding-right">Name</label>
        <div class="col-sm-8">
        <input class="form-control" type="text" name="name"/>
        </div>
        </div>
        	
		<div class="col-sm-3 form-group">
        <label class="col-sm-4 control-label no-padding-right">WeChat</label>
        <div class="col-sm-8">
        <input class="form-control" type="text" name="wechat"/>
        </div>
        </div>
        
        <div class="col-sm-3 form-group">
        <label class="col-sm-4 control-label no-padding-right">Phone</label>
        <div class="col-sm-8">
        <input class="form-control" type="text" name="phone"/>
        </div>
        </div>

        <div class="col-sm-4 form-group pull-right">
        <a class="btn btn-info btn-sm pull-right" style="margin-left:5px;" id="cust_searchAll_btn">
        <i class="ace-icon fa fa-search-plus bigger-110"></i> Search All
        </a>
        <a class="btn btn-info btn-sm pull-right" id="cust_searchMine_btn">
        <i class="ace-icon fa fa-search bigger-110"></i> Search Mine
        </a>
        </div>
		
		</div>
        </div>
        
        </form>
        
        <div class="row">
        <div class="col-xs-12">
        <hr class="no-margin-top"></hr>
        <div class="row">
        <div class="col-xs-12 table-responsive" id="cust_page"></div>
        </div>
        </div>
        </div>
</script>

<script id="cust_grid_temp" type="text/x-dot-template">
        <table id="sample-table-1" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
        <th>Name</th>
        <th>WeChat</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Operation</th>
        </tr>
        </thead>
        <tbody>
        {{~it.data :p:index}}
        <tr>
        <td>{{=p.name||''}}</td>
        <td>{{=p.wechat||''}}</td>
        <td>{{=p.phone||''}}</td>
        <td>{{=p.email||''}}</td>
        <td><a href="javascript:addDeal('{{=p.id }}');">Add Deal</a></td>
        </tr>
        {{~}}
        {{? !it.data.length}}
        <tr ><td colspan="12">No records</td></tr>
        {{?}}
        </tbody>
        </table>
</script>
