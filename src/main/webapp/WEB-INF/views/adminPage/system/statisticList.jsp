<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<div class="jq-layout rel" data-options="fit:true">
	<div data-options="region:'north',border:false">
		<div id="grid-toolbar" class="clearfix p5" >
			<td align="right">开始时间：</td>
			<td>
				<td>
				<input type="text" id="startTime" class="jq-datetimebox" style="width:150px" />
			</td>
			<td align="right">结束时间：</td>
			<td>
				<td>
				<input type="text" id="endTime" class="jq-datetimebox" style="width:150px"/>
			</td>
			
			<td align="right">门店编号：</td>
			<td>
				<input type="text" id="name" name="name" style="width:100px" placeholder="模糊查询"/>
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
		    url: '${z:u('system/statistic_list')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'account',title:'门店账号',width:100},
				{field:'name',title:'门店名称',width:100},
				{field:'total',title:'注册人数',width:100},
				{field:'action',title:'操作',width:150,formatter:action}
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
    	var endTime = $("#endTime").datetimebox("getValue");
    	var startTime = $("#startTime").datetimebox("getValue");
    	var name = $("#name").val();
    	$("#user_grid").datagrid({
    		url: '${z:u("system/statistic_list")}',
            queryParams:{
            	endDate: endTime,
            	startDate: startTime,
            	account:name
    	    }
       });
	});
	
	function action(value,row,index){
		var account = "'" + row.account + "'";
		var detail = '<a onclick="detail('+account+')"><img title="详情" src="${__static__}/admin/img/detail.png"/></a>';
		return detail; 
	}
	
	function detail(account){
		App.popup("${z:u('system/mem_statistic_detail')}?account="+account,{
	        title: "详情",width: 1000});
		$('#user_grid').datagrid('clearSelections'); 
	}
	

</script>
