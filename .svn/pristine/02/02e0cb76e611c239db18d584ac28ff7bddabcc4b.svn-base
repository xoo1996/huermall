<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<div class="jq-layout rel" data-options="fit:true">
	<div data-options="region:'north',border:false">
		<div id="grid-toolbar" class="clearfix p5" >
			
			<td align="right">礼品名称：</td>
			<td>
				<input type="text" id="name" name="name" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td>兑换数量：</td>
			<td>
				<select class="jq-combobox" style="width:60px" id="symbol" name="symbol" data-options="{
					method:'post',
					url: '${z:u('public/math_symbol')}'}">
					<option value="1" selected="selected"></option>
				</select>
			</td>
			<td>
				<input type="text" id="changeNum" name="changeNum" style="width:100px" placeholder="数量"/>
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
		    url: '${z:u('gift/gift_list')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'checkboxid',checkbox:true},
				{field:'id',title:'编号',width:100},
				{field:'name',title:'名称',width:100},
				{field:'changeNum',title:'兑换数量',width:100},
				{field:'storeNum',title:'库存数量',width:100},
				{field:'score',title:'积分',width:100},
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
    	var symbol = $("#symbol").combobox("getValue");
    	var changeNum = $("#changeNum").val();
    	$("#user_grid").datagrid({
            url: '${z:u("gift/gift_list")}?name=' + name + '&changeNum='+changeNum + '&symbol='+symbol
       });
	});
	
	function action(value,row,index){
		var detail = '<a href="gift/show_gift?id=' +row.checkboxid + '" target="_blank"><img title="查看详情" src="${__static__}/admin/img/detail.png"/></a>';
		var del = '<a onclick="del('+row.checkboxid+')"><img title="删除" src="${__static__}/admin/img/delNew.png"/></a>';
		var resetPw = '<a onclick="setStoreNum('+row.checkboxid+')"><img title="设置库存" src="${__static__}/admin/img/password.png"/></a>';
		var setscore = '<a onclick="setScore('+row.checkboxid+')"><img title="设置礼品积分" src="${__static__}/admin/img/user.png"/></a>';
		return detail + "&nbsp;&nbsp;" +  del+"&nbsp;&nbsp;" + resetPw+"&nbsp;&nbsp;" +setscore; 
	}
	
	$("#add").on("click",function(){
		App.popup("${z:u('gift/add_gift_jsp')}",{
	        title: "新建礼品",width: 600});
	});
	
	function del(id){
		App.ajax("${z:u('gift/delete_gift')}?giftId="+id,{type: "POST"});
		$('#user_grid').datagrid('clearSelections'); 
	}
	
	function setStoreNum(id){
		App.popup("${z:u('gift/reset_storeNum')}?id="+id,{
	        title: "设置库存",width: 600});
		$('#user_grid').datagrid('clearSelections'); 
	}
	
	function setScore(id){
		App.popup("${z:u('gift/reset_score')}?id="+id,{
	        title: "设置礼品积分",width: 600});
		$('#user_grid').datagrid('clearSelections'); 
	}
	
</script>
