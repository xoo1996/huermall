<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>

<div class="jq-layout rel" data-options="fit:true">
	<div data-options="region:'north',border:false">
		<div id="grid-toolbar" class="clearfix p5 " style="padding-right:4%;text-align: right;">		
            <a id="add" class="new_a"><img style="width:20px" src="${__static__}/admin/img/add.png"/> 添加</a>&nbsp;&nbsp;             			
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid" id="role_grid" data-options="{
			method:'post',
			fit: true, 
			url: '${z:u('system/rolelist')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
		    singleSelect:false,
			columns: [[
				{field:' id',checkbox:true},
				{field:'id',title:'编号',width:80},
				{field:'name',title:'角色名称',width:60},
				{field:'action',title:'操作',width:80,formatter:action}
			]]				
			}">
		</table>
	</div>
</div>
<script type="text/javascript">
function action(value,row,index){
	var edit = '<a class="edit" onclick="edit('+row.id+')"><img title="修改角色" src="${__static__}/admin/img/editNew.png"/></a>';
	var del = '<a onclick="del('+row.id+')"><img title="删除角色" src="${__static__}/admin/img/delNew.png"/></a>';
	var setAu = '<a class="edit" onclick="setAu('+row.id+')"><img title="设置权限" src="${__static__}/admin/img/cfg.png"/></a>';
	return edit+"&nbsp;&nbsp;"+del+"&nbsp;&nbsp;"+setAu;
}
function edit(id){
			App.popup("${z:u('system/edit_role')}?id="+id,{title: "编辑",width:350});
			$('#role_grid').datagrid('clearSelections');
}

function del(id){
	App.ajax("${z:u('system/delete_role')}?id="+id,{type: "POST"});
	$('#user_grid').datagrid('clearSelections'); 
}

function setAu(id){
	App.popup("${z:u('system/set_role_menu')}?id="+id,{title: "权限配置",
		width: 400,
		height: 500});
	$('#role_grid').datagrid('clearSelections');
}

//添加角色
$("#add").on("click", function(){
	App.popup("${z:u('system/addrole')}",{
		title: "新增",width:350
	});
});

//修改角色、设置权限
$("#edit,#setAuthority").on("click", function(){
	var row = $(".jq-datagrid").datagrid("getSelections");
	
	if(row.length == 0){
		App.alert("请先选择一条记录","warning");
	}if(row.length>1){
		App.alert("只能选择一条数据","warning");
	}
	else{
		var eleId = $(this).attr("id");
		if(eleId == "edit"){
			App.popup("${z:u('authority/editrole')}?id="+row[0].id,{title: "编辑",width:350});
		}else if(eleId=="setAuthority"){
			App.popup("${z:u('authority/setau')}?id="+row[0].id,{title: "权限配置",
				width: 400,
				height: 500});
		}
	}
}); 
</script>
