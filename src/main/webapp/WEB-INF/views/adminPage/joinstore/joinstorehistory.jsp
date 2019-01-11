<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<div class="jq-layout rel" data-options="fit:true">
	<div data-options="region:'north',border:false">
		<div id="grid-toolbar" class="clearfix p5" >
			
			<td align="right">加盟店名称：</td>
			<td>
				<input type="text" id="storeName" name="storeName" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">加盟店编号：</td>
			<td>
				<input type="text" id="storeNo" name="storeNo" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td>
				开始时间：<input type="text" id="startTime" name="startTime"	class="jq-datebox" style="width: 150px" />
			</td>
			<td>
				结束时间：<input type="text" id="endTime" name="endTime" class="jq-datebox" style="width: 150px" />
			</td>
            <button class="btn btn-sm btn-info" id="search_btn">查询</button>
            <button class="btn btn-sm btn-info" id="export_btn">导出excel</button>
		</div>
	
	</div>
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="joinstoregetbathistory_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:false,
			fit:true,//自动补全
		    url: '${z:u('joinstore/joinstoregetbathistory')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'id',checkbox:true},
				{field:'instorename',title:'加盟店名称',width:100},
				{field:'membername',title:'会员名称',width:100},
				{field:'getstorename',title:'会员所属店',width:100},
				{field:'batname',title:'电池名称',width:100},
				{field:'getnum',title:'领取数量',width:100},
				{field:'handle_date',title:'日期',width:100},
			]]
			}";>
		</table>
	</div>
</div>
<form id="exportForm" action="${z:u('joinstore/export_joinstorehistory')}" method="post">
	<input type="hidden" id="hidden_storeName" name="storeName"/>
	<input type="hidden" id="hidden_storeNo" name="storeNo"/>
	<input type="hidden" id="hidden_startTime" name="startTime"/>
	<input type="hidden" id="hidden_endTime" name="endTime"/>
</form>
<script type="text/javascript">
	$("#search_btn").on("click", function() {//搜索查询		
	    var storeName = $("#storeName").val();
	    var storeNo = $("#storeNo").val();
	    var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		
	    $("#joinstoregetbathistory_grid").datagrid({
	    	url: '${z:u("joinstore/joinstoregetbathistory")}',
	        queryParams:{
	        	storeName: storeName,
	        	storeNo: storeNo,
	        	startTime: startTime,
	        	endTime: endTime,
		    }
		});
	    
	});	
	
	$("#export_btn").on("click",function(){
		var storeName = $("#storeName").val();
	    var storeNo = $("#storeNo").val();
		var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		
		$("#hidden_storeName").val(storeName);
		$("#hidden_storeNo").val(storeNo);
		$("#hidden_startTime").val(startTime);
		$("#hidden_endTime").val(endTime);
		$("#exportForm").submit();
	});
</script>
