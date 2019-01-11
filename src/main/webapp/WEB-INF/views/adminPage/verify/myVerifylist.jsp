<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<div class="jq-layout rel" data-options="fit:true">
	<div data-options="region:'north',border:false">
		<div id="grid-toolbar" class="clearfix p5" >
			<td align="right">申请编号：</td>
			<td>
				<input type="text" id="verId" name="verId" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">审批状态：</td>
			<td>
				<select class="jq-combobox" style="width:120px" id="status" name="status" data-options="{
					method:'post',
					url: '${z:u('public/verify_invite_type')}'}">
				</select>
			</td> 
			<td align="right">审批人：</td>
			<td>
				<input type="text" id="verifyer" name="verifyer" style="width:100px" placeholder="模糊查询"/>
			</td>
            <button class="btn btn-sm btn-info" id="search_btn">查询</button>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="user_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:true,
			fit:true,//自动补全
		    url: '${z:u('verify/myapply_lsit')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'checkboxid',checkbox:true},
				{field:'id',title:'申请编号',width:100},
				{field:'start_date',title:'申请时间',width:130},
				{field:'content',title:'申请内容',width:300},
				{field:'status',title:'审批状态',width:100},
				{field:'verify_date',title:'审批时间',width:130},
				{field:'account',title:'审批人',width:100},
				{field:'remark',title:'备注',width:100}
			]],
			onLoadSuccess : function () {
		        $('#user_grid').datagrid('fixRownumber');
		    }
			}";>
		</table>
	</div>
</div>
<script type="text/javascript">
	$("#search_btn").on("click", function() {//搜索查询		
		var verId = $("#verId").val();
	    var verifyer = $("#verifyer").val();
	    var status = $("#status").combobox("getValue");
	    if(status == 0){
	    	status = "";
	    }
	    $("#user_grid").datagrid({
            url: '${z:u("verify/myapply_lsit")}',
            queryParams: {
            	verId: verId,
            	verifyer: verifyer,
            	status: status
		    }    
    	});
	});
</script>
