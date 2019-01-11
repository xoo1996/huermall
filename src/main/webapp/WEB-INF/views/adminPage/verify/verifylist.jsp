<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<div class="jq-layout rel" data-options="fit:true">

	<div data-options="region:'north',border:false">
		<div id="grid-toolbar" class="clearfix p5" >
			
			<td align="right">申请编号：</td>
			<td>
				<input type="text" id="verId" name="verId" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">会员编号：</td>
			<td>
				<input type="text" id="memNo" name="memNo" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">审批状态：</td>
			<td>
				<select class="jq-combobox" style="width:120px" id="status" name="status" data-options="{
					method:'post',
					url: '${z:u('public/verify_invite_type')}'}">
					<option value="2">待审批</option>
				</select>
			</td>
			<td align="right">审批发起人：</td>
			<td>
				<input type="text" id="sponsor" name="sponsor" style="width:100px" placeholder="模糊查询"/>
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
		    url: '${z:u('verify/verifylist')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'checkboxid',checkbox:true},
				{field:'id',title:'申请编号',width:100},
				{field:'start_date',title:'申请日期',width:100},
				{field:'member_no',title:'会员编号',width:100},
				{field:'type',title:'申请类型',width:100},
				{field:'content',title:'申请内容',width:300},
				{field:'status',title:'审批状态',width:100},
				{field:'verify_date',title:'审批日期',width:100},
				{field:'account',title:'审批发起人',width:100},
				{field:'handle',title:'操作',width:100,formatter:handle}
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
	    var verId = $("#verId").val();
	    var memNo = $("#memNo").val();
	    var sponsor = $("#sponsor").val();
	    var status = $("#status").combobox("getValue");
	    if(status == 0){
	    	status = "";
	    }
	    $("#user_grid").datagrid({
            url: '${z:u("verify/verifylist")}',
            queryParams: {
            	verId: verId,
            	memNo: memNo,
            	sponsor: sponsor,
            	status: status
		    }    
    	});
	});
	
	function handle(value,row,index){
		var ch = '<a onclick="ch('+row.checkboxid+')"><img title="审批通过" src="${__static__}/admin/img/detail.png"/></a>';
		var ac = '<a onclick="ac('+row.checkboxid+')"><img title="审批不通过" src="${__static__}/admin/img/user.png"/></a>';
		return ch+"&nbsp;&nbsp;"+ac; 
	}
	function ch(id){
		$.ajax({
			type:'POST',
			url:"${z:u('verify/wait_verify')}",
			data:{id:id},
			success:function(data){
				if(data == 4 || data == 5){
					App.alert("已审批过！","warning");
				}else{
					ch1(id);
				}
			},
			dataType:'json'
		});		
	}
	function ac(id){
		$.ajax({
			type:'POST',
			url:"${z:u('verify/wait_verify')}",
			data:{id:id},
			success:function(data){
				if(data == 4 || data == 5){
					App.alert("已审批过！","warning");
				}else{
					ac1(id);
				}
			},
			dataType:'json'
		});		
	}
	function ch1(id){
		App.popup("${z:u('verify/agree_apply')}?id="+id,{title:"审批通过",width:650});
		$('#member_grid').datagrid('clearSelections'); 
	}
	function ch2(id){
		App.ajaxx("${z:u('verify/agree_apply')}?id="+id,{type: "POST"});
		$('#member_grid').datagrid('clearSelections');
	}
	
	function ac1(id){
		App.popup("${z:u('verify/refuse')}?id="+id,{title:"审批不通过",width:650});
		$('#member_grid').datagrid('clearSelections'); 
	}
	function ac2(id){
		App.ajaxx("${z:u('verify/refuse')}?id="+id,{type: "POST"});
		$('#member_grid').datagrid('clearSelections');
	}
</script>
