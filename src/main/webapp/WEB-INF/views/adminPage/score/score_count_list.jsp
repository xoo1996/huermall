<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<div class="jq-layout rel" data-options="fit:true">
	<div data-options="region:'north',border:false">
		<div id="grid-toolbar" class="clearfix p5" >
			
			<td align="right">开始时间：</td>
			<td>
				<input class="jq-datetimebox"  type="text" id="startDate" name="startDate" style="width:150px">
			</td>
			<td align="right">结束时间：</td>
			<td>
				<input class="jq-datetimebox"  type="text" id="endDate" name="endDate" style="width:150px">
			</td>
			<td align="right">门店：</td>
			<td>
				<input type="text" id="storeName" name="storeName" style="width:100px" placeholder="模糊查询"/>
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
		    url: '${z:u('score/score_count')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'name',title:'门店名称',width:200},
				{field:'account',title:'门店账号',width:200},
				{field:'total',title:'期间送出总积分数',width:200}
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
		var startDate = $("#startDate").combobox("getValue");  
		var endDate = $("#endDate").combobox("getValue");
	    var storeName = $("#storeName").val();
	    $("#user_grid").datagrid({
	        url: '${z:u("score/score_count")}',
	        queryParams:{
		    	storeName: storeName,
		    	startDate: startDate,
		    	endDate: endDate
		    }
	        });
		    
	});
	
</script>
