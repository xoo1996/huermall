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
			<td align="right">所属门店名称：</td>
			<td>
				<input type="text" id="storeName" name="StoreName" style="width:100px" placeholder="模糊查询"/>
			</td>
            <button class="btn btn-sm btn-info" id="search_btn">查询</button>
           <!--  <a class="btn btn=sm btn-info" id="add">老用户注册</a> -->
		</div>
	
	</div>
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="member_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:false,
			fit:true,//自动补全
		    url: '${z:u('member/memberlist_admin')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'id',checkbox:true},
				{field:'name',title:'会员名',width:100},
				{field:'member_no',title:'会员编号',width:100},
				{field:'store',title:'门店',width:100},
				{field:'phone',title:'手机号码',width:100},
			<!-- 	{field:'batterynum',title:'电池数量',width:100}, -->
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
    var storeName = $("#storeName").val();
    $("#member_grid").datagrid({
    	url: '${z:u("member/memberlist_admin")}',
        queryParams:{
        	memberName: memberName,
        	memberNo: memberNo,
        	memberPhone: memberPhone,
        	storeName:storeName
	    }
        });
	});
	
	$("#add").on("click",function(){
		App.popup("${z:u('member/add_member')}",{
			title: "用户新增",
			width:360
		});
	});
	
	function change(value,row,index){
		var event1 = '<a onclick="event1('+row.id+')"><img title="电池事件" src="${__static__}/admin/img/detail.png"/></a>';
		//var ch = '<a onclick="ch('+row.id+')"><img title="积分兑换" src="${__static__}/admin/img/detail.png"/></a>';
		var ac = '<a onclick="ac('+row.id+')"><img title="电池领取" src="${__static__}/admin/img/battery.png"/></a>';
		return event1 + "&nbsp;&nbsp;" +ac; 
	}
	
	function event1(id){
		App.popup("${z:u('bat_event/bat_event')}?id="+id,{title:"会员事件",width:1050,height:650});
		$('#member_grid').datagrid('clearSelections'); 
	}
	
	/* function ch(id){
		App.popup("${z:u('member/score_change')}?id="+id,{title:"积分兑换",width:650});
		$('#member_grid').datagrid('clearSelections'); 
	} */
	
	function ac(id){
		App.popup("${z:u('membat/get_bat')}?id="+id,{title:"电池领取",width:800,height:550});
		$('#member_grid').datagrid('clearSelections');  
		
		
	}
</script>
