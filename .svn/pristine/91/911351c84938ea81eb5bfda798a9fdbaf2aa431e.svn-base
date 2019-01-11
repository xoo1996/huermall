<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<div class="jq-layout rel" data-options="fit:true">
	<div data-options="region:'north',border:false">
		<div id="grid-toolbar" class="clearfix p5" >
			
			<td align="right">会员名：</td>
			<td>
				<input type="text" id="memberName" name="memberName" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">会员编号：</td>
			<td>
				<input type="text" id="memberNo" name="memberNo" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">手机号码：</td>
			<td>
				<input type="text" id="memberPhone" name="memberPhone" style="width:100px" placeholder="模糊查询"/>
			</td>
			<c:if test="${isadmin == true }">
				<td align="right">门店账号：</td>
				<td>
					<input type="text" id="storeAcc" name="storeAcc" style="width:100px" placeholder="模糊查询"/>
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
		    url: '${z:u('score/store_apply_list')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'id',checkbox:true},
				{field:'name',title:'会员名',width:70},
				{field:'member_no',title:'会员编号',width:70},
				{field:'phone',title:'手机号',width:70},
				{field:'folno',title:'订单号',width:70},
				{field:'handle_type',title:'操作类型',width:100},
				{field:'handle_date',title:'操作时间',width:100},
				{field:'content',title:'操作明细',width:200},
				{field:'account',title:'所属门店',width:100},
				{field:'isUsed',title:'是否已申请过积分',width:100},
				{field:'isusedCode',hidden:true,width:100},
				{field:'change',title:'门店积分申请通道',width:200,formatter:change}
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
	    var storeAcc = $("#storeAcc").val();
	    var memberPhone = $("#memberPhone").val();
	    $("#user_grid").datagrid({
	    	url: '${z:u("score/store_apply_list")}',
	        queryParams:{
	        	memberName: memberName,
	        	memberNo: memberNo,
	        	memberPhone: memberPhone,
	        	storeAcc:storeAcc
		    }
	    });
	});
	
	function change(value,row,index){
		var ac = '<a onclick="ac('+row.id + "," + row.isusedCode+')"><img title="申请积分" src="${__static__}/admin/img/user.png"/></a>';
		return ac; 
	}
	
	function ac(id,isused){
		if(isused == 6){
			App.popup("${z:u('score/store_apply_detail')}?id="+id,{title:"门店积分申请通道",width:650});
			$('#member_grid').datagrid('clearSelections'); 
		}else{
			App.alert("该用户已经赠送过积分！","warning");
		}
		
	}
	
</script>
