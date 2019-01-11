<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<div class="jq-layout rel" data-options="fit:true">
	<div data-options="region:'north',border:false">
		<div id="grid-toolbar" class="clearfix p5" >
            <a class="btn btn=sm btn-info" id="add">新增价格区间</a>
            <a class="btn btn=sm btn-info" id="set">设置积分抵扣金额比例</a>
            <a class="btn btn=sm btn-info" id="config">设置赠送惠耳币比例</a>
            <a class="btn btn=sm btn-info" id="change">设置代金券</a>
            <a class="btn btn=sm btn-info" id="rate">设置最大积分抵扣比例</a>
            <a class="btn btn=sm btn-info" id="login">设置老用户注册赠送积分值</a>
		</div>
	
	</div>
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="user_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:true,
			fit:true,//自动补全
		    url: '${z:u('score/price_config')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'id',title:'记录编号',width:100},
				{field:'start_price',title:'起始价格',width:100},
				{field:'end_price',title:'截止价格',width:100},
				{field:'change_score',title:'积分值',width:100},
				{field:'action',title:'编辑',width:200,formatter:action}
			]]
			}";>
		</table>
	</div>
</div>
<script type="text/javascript">
	$("#search_btn").on("click", function() {//搜索查询
    $("#user_grid").datagrid({
            url: '${z:u("score/price_config")}'});
	});
	function action(value,row,index){
		var edit = '<a onclick="edit('+row.id+')">编辑</a>';
		return edit; 
	}
	function edit(id){
		App.popup("${z:u('score/editprice')}?id="+id,{title:"编辑",width:400});
		$('#user_grid').datagrid('clearSelections'); 
	}
	$("#add").on("click",function(){
		App.popup("${z:u('score/add_price')}",{title:"新增价格区间",width:400});
	});
	$("#set").on("click",function(){
		App.popup("${z:u('score/set_scale')}",{title:"设置积分抵扣金额比例",width:500});
	});
	$("#config").on("click",function(){
		App.popup("${z:u('score/set_apply')}",{title:"设置赠送惠耳币比例",width:500});
	});
	$("#change").on("click",function(){
		App.popup("${z:u('score/set_change')}",{title:"设置代金券",width:500});
	});
	$("#rate").on("click",function(){
		App.popup("${z:u('score/set_rate')}",{title:"设置最大积分抵扣比例",width:500});
	});
	$("#login").on("click",function(){
		App.popup("${z:u('score/set_login')}",{title:"设置老用户注册赠送积分值",width:500});
	});
</script>
