<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>

<div  class="height1 jq-layout rel" data-options="fit:true"
	style="height:300px">
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="event_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:true,
			<!-- fit:true,//自动补全 -->
		    url: '${z:u('bat_event/bat_event')}',
		    pageSize: 15,
		    pageList: [15],
			columns: [[
				{field:'id',checkbox:true},
				{field:'name',title:'会员姓名',width:70},
				{field:'in_store_name',title:'实际领取店面',width:70},
				{field:'storeName',title:'赠送电池店面',width:70},
				{field:'battery_type',title:'电池型号',width:100},
				{field:'get_bat_num',title:'领取电池数量',width:100},
				{field:'handle_date',title:'领取电池时间',width:100},
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
		        id: '${id}'
		    },
		    onLoadSuccess: function (data) {
				$(".height1").css("height","550px");
		    } 
		});

	});

</script>
