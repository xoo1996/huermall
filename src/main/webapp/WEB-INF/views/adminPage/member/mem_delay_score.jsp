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
		    url: '${z:u('memop/member_score_event')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'score',title:'赠送积分',width:100},
				{field:'date',title:'积分到账日期',width:100}
			]]
			}";>
		</table>
	</div>
</div>
<a style="margin-left:45%" class="btn btn-small btn-success closeDl">返回</a>
