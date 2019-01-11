<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<div class="jq-layout rel" data-options="fit:true">
	<div data-options="region:'north',border:false">
		<div id="grid-toolbar" class="clearfix p5" >
			
			<td align="right">姓名：</td>
			<td>
				<input type="text" id="name" name="name" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">账户编号：</td>
			<td>
				<input type="text" id="accountNo" name="accountNo" style="width:100px" placeholder="模糊查询"/>
			</td>
            <button class="btn btn-sm btn-info" id="search_btn">查询</button>
            <a class="btn btn=sm btn-info" id="add">新建</a>
		</div>
	
	</div>
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="user_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:true,
			fit:true,//自动补全
		    url: '${z:u('system/account_list')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'checkboxid',checkbox:true},
				{field:'accountNo',title:'账号编号',width:100},
				{field:'name',title:'真实姓名',width:100},
				{field:'roleName',title:'角色',width:100},
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
    	var name = $("#name").val();
    	var accountNo = $("#accountNo").val();
    	$("#user_grid").datagrid({
    		url: '${z:u("system/account_list")}',
            queryParams:{
            	name: name,
            	accountNo: accountNo
    	    }
       });
	});
	
	function action(value,row,index){
		var del = '<a onclick="del('+row.checkboxid+')"><img title="删除用户" src="${__static__}/admin/img/delNew.png"/></a>';
		var resetPw = '<a onclick="resetPw('+row.checkboxid+')"><img title="重置口令" src="${__static__}/admin/img/password.png"/></a>';
		var setRole = '<a onclick="setRole('+row.checkboxid+')"><img title="设置角色" src="${__static__}/admin/img/setRole.png"/></a>';
		return del+"&nbsp;&nbsp;" + resetPw +"&nbsp;&nbsp;" + setRole; 
	}
	
	$("#add").on("click",function(){
		App.popup("${z:u('system/add_account')}",{
	        title: "添加新用户",width: 400});
	});
	
	function del(id){
		App.ajax("${z:u('system/delete_account')}?id="+id,{type: "POST"});
		$('#user_grid').datagrid('clearSelections'); 
	}
	
	function resetPw(id){
		App.popup("${z:u('system/reset_password')}?id="+id,{
	        title: "重置口令",width: 400});
		$('#user_grid').datagrid('clearSelections'); 
	}
	function setRole(id){
		App.popup("${z:u('system/setrole')}?id="+id,{
	        title: "设置角色",
	        width: 450
	    });
		$('#user_grid').datagrid('clearSelections'); 
	}
	
</script>
