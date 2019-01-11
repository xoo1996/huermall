<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<div  class="height1 jq-layout rel" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid" id="member_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:false,
			height:450,
		<!-- 	fit:true,//自动补全 -->
		    pageSize: 10,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'handle_type',title:'操作类型',width:100},
				{field:'handle_date',title:'操作日期',width:100},
				{field:'content',title:'操作明细',width:300}
			]]
			}";>
		</table>
	</div>
</div>
<a style="margin-left:45%" class="btn btn-small btn-success closeDl">返回</a>

<script type="text/javascript">
	$(document).ready(function(){
		$("#member_grid").datagrid({
	    	url: '${z:u("memop/score_event_single")}',
	        queryParams:{
	        	memId: ${memId}
		    }
	        });
	});
</script>
