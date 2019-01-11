<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<div>
  	<div> <p class="pTitle" style="padding-left:10px;padding-bottom:10px" >积分</p></div>
</div>
<div  class="height1 jq-layout rel" data-options="fit:true" style="height:300px">
	<div data-options="region:'north',border:false">
		<div id="grid-toolbar" class="clearfix p5" >
			<td align="right">开始时间：</td>
			<td>
				<td>
				<input type="text" id="startTime1" class="jq-datetimebox" style="width:150px" />
			</td>
			<td align="right">结束时间：</td>
			<td>
				<td>
				<input type="text" id="endTime1" class="jq-datetimebox" style="width:150px"/>
			</td>
            <button class="btn btn-sm btn-info" id="search_btn1">查询</button>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="event_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:true,
			<!-- fit:true,//自动补全 -->
		    url: '${z:u('system/mem_statistic_detail')}',
		    pageSize: 10,
		    pageList: [5,10,20,30,40,50],
			columns: [[
				{field:'storeName',title:'门店名称',width:70},
				{field:'handleDate',title:'提交日期',width:70},
				{field:'memberName',title:'会员名',width:70},
				{field:'memberNo',title:'会员编号',width:100}
			]],
			onLoadSuccess : function () {
		        $('#user_grid').datagrid('fixRownumber');
		    }
			}";>
		</table>
	</div>
</div>


<script type="text/javascript">
	$("#search_btn1").on("click", function() {//搜索查询		
		var account = '${account}';
		var endTime = $("#endTime1").datetimebox("getValue");
    	var startTime = $("#startTime1").datetimebox("getValue");
		$("#event_grid").datagrid({
			url: '${z:u("system/mem_statistic_detail")}',
	        queryParams:{
	        	endDate: endTime,
            	startDate: startTime,
            	account:account
		    }
	   });
	});
</script>