<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<div class="jq-layout rel" data-options="fit:true">
	<div data-options="region:'north',border:false">
		<div id="grid-toolbar" class="clearfix p5" >
			
			<td align="right">门店名称：</td>
			<td>
				<input type="text" id="storeName" name="storeName" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">会员名称：</td>
			<td>
				<input type="text" id="memberName" name="memberName" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">会员电话：</td>
			<td>
				<input type="text" id="phoneNum" name="phoneNum" style="width:100px" placeholder="精确查询"/>
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
		<table class="jq-datagrid"  id="supercahngehistory_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:false,
			fit:true,//自动补全
		    url: '${z:u('superchange/superchangehistorylist')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'id',checkbox:true},
				{field:'name',title:'门店名称',width:50},
				{field:'membername',title:'会员名称',width:50},
				{field:'membat_id',title:'赠送编号',width:50},
				{field:'batevent_id',title:'领取编号',width:50},
				{field:'operation',title:'操作详情',width:330},
				{field:'handle_date',title:'日期',width:70},
			]]
			}";>
		</table>
	</div>
</div>

<form id="exportForm" action="${z:u('superchange/export_superchangehistory')}" method="post">
	<input type="hidden" id="hidden_storeName" name="storeName"/>
	<input type="hidden" id="hidden_memberName" name="memberName"/>
	<input type="hidden" id="hidden_phoneNum" name="phoneNum"/>
	<input type="hidden" id="hidden_startTime" name="startTime"/>
	<input type="hidden" id="hidden_endTime" name="endTime"/>
</form>

<script type="text/javascript">
	$("#search_btn").on("click", function() {//搜索查询		
	    var storeName = $("#storeName").val();
	    var memberName = $("#memberName").val();
	    var phoneNum = $("#phoneNum").val();
	    var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		
	    $("#supercahngehistory_grid").datagrid({
	    	url: '${z:u("superchange/superchangehistorylist")}',
	        queryParams:{
	        	storeName: storeName,
	        	memberName: memberName,
	        	phoneNum: phoneNum,
	        	startTime: startTime,
	        	endTime: endTime,
		    }
		});
	    
	});	
	
	$("#export_btn").on("click",function(){
		var storeName = $("#storeName").val();
	    var memberName = $("#memberName").val();
		var phoneNum = $("#phoneNum").val();
		var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		
		$("#hidden_storeName").val(storeName);
		$("#hidden_memberName").val(memberName);
		$("#hidden_phoneNum").val(phoneNum);
		$("#hidden_startTime").val(startTime);
		$("#hidden_endTime").val(endTime);
		$("#exportForm").submit();
	});
</script>
