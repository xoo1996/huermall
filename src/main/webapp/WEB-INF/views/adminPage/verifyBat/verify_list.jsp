<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>

<div class="jq-layout rel" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height:36px;">
		<div id="grid-toolbar" class="clearfix p5" style="float:left;">
			
			<td align="right">会员名：</td>
			<td>
				<input type="text" id="memberName" name="memberName" style="width:100px" placeholder="精确查询"/>
			</td>
			<td align="right">手机号码：</td>
			<td>
				<input type="text" id="memberPhone" name="memberPhone" style="width:100px" placeholder="精确查询"/>
			</td>
			<td align="right">状态：</td>
			<td>
				<select class="jq-combobox" style="width:100%" id="status_check" name="type" >
					<option value="" selected="selected"></option>
					<option value ="verifying">审核中</option>
 					<option value ="pass">通过</option>
  					<option value="reject">未通过</option>
				</select>
			</td>
			<c:if test="${isadmin }">
				<td align="right">门店编号：</td>
				<td>
					<input type="text" id="storeNo" name="storeNo" style="width:100px" placeholder="精确查询"/>
				</td>
			</c:if>
            <button class="btn btn-sm btn-info" id="search_btn">查询</button>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="user_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:true,
			fit:true,//自动补全
		    url: '${z:u('verify_bat/verify_list')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'id',title:'审核号',width:70},
				{field:'storeId',title:'赠送店铺',width:70},
				{field:'memName',title:'会员名称',width:70},
				{field:'phone',title:'会员手机号',width:70},
				{field:'batType',title:'电池型号',width:70},
				{field:'batNum',title:'赠送数量',width:70},
				{field:'applyDate',title:'申请时间',width:100},
				{field:'status',title:'状态',width:50},
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
	    var memberName = $("#memberName").val();
	    var memberNo = $("#memberNo").val();
	    var memberPhone = $("#memberPhone").val();
	    var storeNo = $("#storeNo").val();
	    var statusCheck=$("#status_check").combobox("getValue");
	   
	    $("#user_grid").datagrid({
	    	url: '${z:u('verify_bat/verify_list')}',
	        queryParams:{
	        	memberName: memberName,
	        	memberNo: memberNo,
	        	memberPhone: memberPhone,
	        	storeNo: storeNo,
	        	statusCheck:statusCheck
		    }
	        });
		    
	});
 	

	
</script>
