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
		<table class="jq-datagrid"  id="user_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:true,
			fit:true,//自动补全
		    url: '${z:u('superchange/super_change_bat_event')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'id',checkbox:true},
				{field:'name',title:'会员名',width:70},
				{field:'member_no',title:'会员编号',width:70},
				{field:'storeName',title:'所属门店',width:70},
				{field:'in_store_name',title:'实际领取店面',width:70},
				{field:'phone',title:'手机号',width:70},
				{field:'battery_type',title:'领取电池型号',width:70},
				{field:'get_bat_num',title:'领取电池数量',width:70},
				{field:'handle_date',title:'操作时间',width:100},
				{field:'membat_id',title:'记录编号',width:100},
				{field:'change',title:'操作',width:50,formatter:modify},
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
	    	url: '${z:u('superchange/super_change_bat_event')}',
	        queryParams:{
	        	memberName: memberName,
	        	memberNo: memberNo,
	        	memberPhone: memberPhone,
		    }
	        });
		    
	});
	
	
	function modify(value,row,index){
		var acc = '<a  onclick="acc('+row.id+','+row.membat_id+')"><img title="修改" src="${__static__}/admin/img/editNew.png"/></a>';
		return acc; 
	}
	
	
	
 	function acc(id,membatid){
		App.popup("${z:u('superchange/superchangenum')}?membatId="+membatid+"&id="+id,{title:"超级修改",width:450});
		$('#member_grid').datagrid('clearSelections'); 
	} 
</script>
