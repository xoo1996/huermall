<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<div class="jq-layout rel" data-options="fit:true">
	<div data-options="region:'north',border:false">
		<div id="grid-toolbar" class="clearfix p5" >
			<td align="right">活动名称：</td>
			<td>
				<input type="text" id="name" name="name" style="width:100px" placeholder="模糊查询"/>
			</td>
            <button class="btn btn-sm btn-info" id="search_btn">查询</button>
             <a class="btn btn=sm btn-info" id="edit_rollad">修改滚动广告内容</a>
             <a class="btn btn=sm btn-info" id="edit_huier">惠耳月时间设置</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="user_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:true,
			fit:true,//自动补全
		    url: '${z:u('activity/activity_list')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'checkboxid',checkbox:true},
				{field:'name',title:'活动名称',width:100},
				{field:'updateDate',title:'最近修改日期',width:100},
				{field:'status',title:'状态',width:100},
				{field:'releaseDate',title:'发布日期',width:100},
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
    	$("#user_grid").datagrid({
            url: '${z:u("activity/activity_list")}?name=' + name
       });
	});
	
	function action(value,row,index){
		var show = '<a target="_blank" href="activity/admin_show_activity?id=' +row.checkboxid + '"><img title="活动详情" src="${__static__}/admin/img/detail.png"/></a>';
		var del = '<a onclick="edit('+row.checkboxid+')"><img title="编辑" src="${__static__}/admin/img/editNew.png"/></a>';
		var resetPw = '<a onclick="release('+row.checkboxid+')"><img title="活动发布" src="${__static__}/admin/img/setRole.png"/></a>';
		var setRole = '<a onclick="del('+row.checkboxid+')"><img title="活动删除" src="${__static__}/admin/img/delNew.png"/></a>';
		var setscore = '<a onclick="setScore('+row.checkboxid+')"><img title="设置活动积分" src="${__static__}/admin/img/editNew.png"/></a>';
		return show + "&nbsp;&nbsp;" + del+"&nbsp;&nbsp;" + resetPw +"&nbsp;&nbsp;" + setRole +"&nbsp;&nbsp;" + setscore; 
	}
	
	function edit(id){
		App.popup("${z:u('activity/edit_activity_jsp')}?id="+id,{
	        title: "编辑活动",width: 1000,height:500});
	}
	
	$("#edit_rollad").on("click", function() {
		App.popup_lunbo("${z:u('activity/roll_ad')}",{
	        title: "设置滚动广告",width: 400,height:200});
	});
	
	$("#edit_huier").on("click", function() {
		App.popup_lunbo("${z:u('activity/huier_month')}",{
	        title: "设置惠耳月时间",width: 400,height:200});
	});
	
	function release(id){
		App.release("${z:u('activity/release_activity')}?id="+id,{type: "POST"});
		$('#user_grid').datagrid('clearSelections'); 
	}

	function setScore(id){
		App.popup("${z:u('activity/reset_score')}?id="+id,{
	        title: "设置活动积分",width: 400});
		$('#user_grid').datagrid('clearSelections'); 
	}
	
	function del(id){
		App.ajax("${z:u('activity/delete_activity')}?id="+id,{type: "POST"});
		$('#user_grid').datagrid('clearSelections'); 
	}
	/*
	function setRole(id){
		App.popup("${z:u('system/setrole')}?id="+id,{
	        title: "设置角色",
	        width: 450
	    });
		$('#user_grid').datagrid('clearSelections'); 
	} */
	
</script>
