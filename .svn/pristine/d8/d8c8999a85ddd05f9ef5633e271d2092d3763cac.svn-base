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
			<td align="right">门店名称：</td>
			<td>
				<input type="text" id="storeName" name="storeName" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">会员编号：</td>
			<td>
				<input type="text" id="memNo" name="memNo" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">会员名称：</td>
			<td>
				<input type="text" id="memName" name="memName" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">审批状态：</td>
			<td>
				<select class="jq-combobox" style="width:120px" id="status" name="status" data-options="{
					method:'post',
					url: '${z:u('public/verify_invite_type')}'}">
					<option value="2">待审批</option>
				</select>
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
		    url: '${z:u('verify/store_apply_list')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'checkboxid',checkbox:true},
				{field:'id',title:'申请编号',width:50},
				{field:'start_date',title:'申请日期',width:150},
				{field:'storeAcc',title:'门店编号',width:80},
				{field:'account',title:'门店名称',width:80},
				{field:'memNo',title:'会员编号',width:80},
				{field:'memName',title:'会员名称',width:80},
				{field:'score',title:'申请积分',width:80},
				{field:'final_score',title:'决定赠送积分',width:100},
				{field:'content',title:'申请内容',width:300},
				{field:'status',title:'审批状态',width:100},
				{field:'verift_date',title:'审批日期',width:150},
				{field:'handle',title:'操作',width:100,formatter:handle}
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
	    var storeName = $("#storeName").val();
	    var memNo = $("#memNo").val();
	    var memName = $("#memName").val();
	    var status = $("#status").combobox("getValue");
	    if(status == 0){
	    	status = "";
	    }
	    $("#user_grid").datagrid({
            url: '${z:u("verify/store_apply_list")}',
            queryParams: {
            	verId: verId,
            	storeName: storeName,
            	memNo: memNo,
            	memName: memName,
            	status: status
		    }    
    	});
	});
	
	function handle(value,row,index){
		var op = '<a onclick="ac('+row.checkboxid+')"><img title="操作" src="${__static__}/admin/img/editNew.png"/></a>';
		return op; 
	}
	
	function ac(id){
		App.popup("${z:u('verify/op_store_apply')}?id="+id,{title:"审批",width:650});
		$('#member_grid').datagrid('clearSelections'); 
	}
</script>
