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
				<input type="text" id="memberPhone" name="memberPhone" style="width:100px" placeholder="精确查询"/>
			</td>
            <button class="btn btn-sm btn-info" id="search_btn">查询</button>
		</div>
	
	</div>
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="user_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:true,
			fit:true,//自动补全
		    url: '${z:u('superchange/super_change_mem_bat')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'id',checkbox:true},
				{field:'name',title:'会员名',width:70},
				{field:'phone',title:'手机号',width:70},
				{field:'storeName',title:'所属门店',width:70},
				{field:'battery_type',title:'电池型号',width:70},
				{field:'battery_qty',title:'赠送电池数量',width:70},
				{field:'battery_re_qty',title:'剩余电池数量',width:100},
				{field:'handle_date',title:'操作时间',width:100},
				{field:'change',title:'操作',width:50,formatter:operation},
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
	    $("#user_grid").datagrid({
	    	url: '${z:u('superchange/super_change_mem_bat')}',
	        queryParams:{
	        	memberName: memberName,
	        	memberNo: memberNo,
	        	memberPhone: memberPhone,
		    }
	        });
		    
	});
	
	function operation(value,row,index){
		var acc = '<a  onclick="acc('+row.id+')"><img title="修改" src="${__static__}/admin/img/editNew.png"/></a>';
		var ac = '<a onclick="delMemBat('+row.id+')"><img title="删除赠送记录" src="${__static__}/admin/img/delNew.png"/></a>';
		return acc + "&nbsp;&nbsp;" +ac; 
	}
		
 	function acc(id){
		App.popup("${z:u('superchange/superchangetype')}?id="+id,{title:"超级修改",width:450});
		$('#member_grid').datagrid('clearSelections'); 
	} 
 	
	function delMemBat(id){
		$.messager.confirm('警告','风险操作：将删除本条赠送记录',function(r){
            if (r){
                $.post("${z:u('superchange/del_membat')}?id="+id,function(result){
                	var obj=jQuery.parseJSON(result);
        			if(obj.status==1){
                        $('#user_grid').datagrid('reload');
                    } else {
                        $.messager.alert('错误','禁止删除领取过电池的记录');
                    }
                });
            }
        });
	}
	
</script>
