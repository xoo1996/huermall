<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
     <div>
     	<div> <p class="pTitle" style="padding-left:10px;padding-bottom:10px" >积分</p></div>
     </div>
<div  class="height1 jq-layout rel" data-options="fit:true"
	style="height:300px">
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="event_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:true,
			<!-- fit:true,//自动补全 -->
		    url: '${z:u('score/score_event')}',
		    pageSize: 5,
		    pageList: [5,10,20,30,40,50],
			columns: [[
				{field:'name',title:'会员名',width:70},
				{field:'member_no',title:'会员编号',width:70},
				{field:'phone',title:'手机号',width:70},
				{field:'handle_type',title:'操作类型',width:100},
				{field:'handle_date',title:'操作时间',width:100},
				{field:'content',title:'操作明细',width:200}
			]],
			onLoadSuccess : function () {
		        $('#user_grid').datagrid('fixRownumber');
		    }
			}";>
		</table>
	</div>
</div>
<div >
   	<div> <p class="pTitle" style="padding-left:10px;padding-bottom:10px" >惠耳币</p></div>
   </div>
<div class="height1 jq-layout rel" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="event_grid1" data-options="{
			method:'post',
			pagination:true,
			singleSelect:true,
			<!-- fit:true,//自动补全 -->
		    url: '${z:u('score/score_event')}',
		    pageSize: 5,
		    pageList: [5,10,20,30,40,50],
			columns: [[
				{field:'name',title:'会员名',width:70},
				{field:'member_no',title:'会员编号',width:70},
				{field:'phone',title:'手机号',width:70},
				{field:'handle_type',title:'操作类型',width:100},
				{field:'handle_date',title:'操作时间',width:100},
				{field:'content',title:'操作明细',width:200}
			]],
			onLoadSuccess : function () {
		        $('#user_grid').datagrid('fixRownumber');
		    }
			}";>
		</table>
	</div>
</div>
		<a style="margin-left:45%" class="btn btn-small btn-success closeDl">返回</a>
<script>
	$(document).ready(function(){
		$('#event_grid').datagrid({
		    queryParams: {
		        memId: '${memId}',
		        eventType: 'score'
		    },
		    onLoadSuccess: function (data) {
				$(".height1").css("height","200px");
		    } 
		});
	//	$('#event_grid').datagrid('resize',{width:1000,height:100});
		$('#event_grid1').datagrid({
		    queryParams: {
		        memId: '${memId}',
		        eventType: 'coin'
		    } ,
		    onLoadSuccess: function (data) {
				$(".height1").css("height","200px");
		    } 
		});
	//	$('#event_grid1').datagrid('resize',{width:1000,height:200});
	});
</script>

