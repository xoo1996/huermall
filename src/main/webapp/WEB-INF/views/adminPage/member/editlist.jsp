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
            <button class="btn btn-sm btn-info" id="search_btn">查询</button>
		</div>
	
	</div>
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="member_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:false,
			fit:true,//自动补全
		    url: '${z:u('member/editlist')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'id',checkbox:true},
				{field:'name',title:'会员名',width:100},
				{field:'member_no',title:'会员编号',width:100},
				{field:'phone',title:'手机号码',width:100},
				<!-- {field:'batterynum',title:'电池数量',width:100}, -->
				{field:'change',title:'操作',width:200,formatter:change}
			]]
			}";>
		</table>
	</div>
</div>
<script type="text/javascript">
	$("#search_btn").on("click", function() {//搜索查询		
    var memberName = $("#memberName").val();
    var memberNo = $("#memberNo").val();
    var memberPhone = $("#memberPhone").val();
    $("#member_grid").datagrid({
    	url: '${z:u("member/editlist")}',
        queryParams:{
        	memberName: memberName,
        	memberNo: memberNo,
        	memberPhone: memberPhone
	    }
	});
	});
	
	
	function change(value,row,index){
		var str = "";
		var ch = '<a onclick="ch('+row.id+')"><img title="编辑" src="${__static__}/admin/img/editNew.png"/></a>';
		str += ch;
		var ac = '<a onclick="ac('+row.id+')"><img title="删除" src="${__static__}/admin/img/delNew.png"/></a>';
		<c:if test="${delete == true }">
			var ac = '<a onclick="ac('+row.id+')"><img title="删除" src="${__static__}/admin/img/delNew.png"/></a>';
			str += "&nbsp;&nbsp;"+ ac;
		</c:if>
		var addmembat = '<a onclick="addmembat('+row.id+')"><img title="无订单赠送" src="${__static__}/admin/img/add.png"/></a>';
		str += "&nbsp;&nbsp;"+ addmembat; 
		return str; 
	}
	
	function ch(id){
		App.popup("${z:u('member/edit_mem')}?id="+id,{title:"编辑",width:650});
		$('#member_grid').datagrid('clearSelections'); 
	}
	
	function ac(id){
		App.ajax("${z:u('member/del_mem')}?id="+id,{type: "POST"});
		$('#member_grid').datagrid('clearSelections'); 
	}
	
	function addmembat(id){
		App.popup("${z:u('verify_bat/add_verify_bat')}?id="+id,{title:"无订单赠送提交",width:650});
		$('#member_grid').datagrid('clearSelections'); 
	}
	
	
</script>
