<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="/WEB-INF/views/public/admin/admin_header.jsp" %>
<%@include file="/WEB-INF/views/public/admin/admin_js.jsp" %>
<%
	String id = request.getParameter("id");
%>
<style>
.sp hr{
    /* border-top: 1px solid #333; */
    margin:10px 0 10px 10; 
    width:75%;
} 
.sp table tr{
text-align:left;
}
.sp td{
width:150px;
 margin:10px 0 10px 0; 
 font-size:15px;
}
.sp .pTitle{
padding-left:13%;
}
.sp h3{
color:#00852E;
}
.sp.form-table tr td:nth-child(1) {
    padding: 0 5px;
    text-align: left;
    width:60px;
}
.sp table td:nth-child(2){
   width:1px;
 }
.sp.form-table tr td:nth-child(3) {
    padding: 0 5px;
    text-align: left;
}
.sp.form-table tr td:nth-child(4) {
    padding: 0 5px;
    text-align: left;
    width:60px;
}
 .sp.form-table tr td:nth-child(6) {
    padding: 0 5px;
    text-align: left;
}
 .sp table td:nth-child(5){
   width:1px;
 }
 input[type="text"]{
 	height:auto;
 }
</style>
    <%-- <div class="sp form-table" align="center" style="font-size:14px">
        <div align="center"  style="width:100%" >
			<h3>会员信息</h3>
		</div>	
		<div  style="width:60%">
			<p class="pTitle">基本信息</p>
			<hr/>	
		   <table >
		   	<input id="memberId" type="hidden" value="${member.id }"/>
			<tr>
				<td>会员编号</td>
				<td width="1">：</td>
				<td>
				${member.memberNo}
				</td>
				<td>会员名称</td>
				<td width="1">：</td>
				<td>
				${member.name} 
				</td>
			</tr>
			<tr >
				<td>电池数量</td>
				<td width="1">：</td>
				<td>
				${member.batterynum } (个)
				</td>
				<td>积分</td>
				<td width="1">：</td>
				<td>
				${member.score } (分)
				</td>
			</tr>
		</table>
		</div>
		<div data-options="region:'center',border:false">
			<table class="jq-datagrid"  id="member_grid" data-options="{
				method:'post',
				pagination:true,
				singleSelect:false,
				fit:true,//自动补全
			    url: '${z:u('memop/member_score_event')}',
			    pageSize: 20,
			    pageList: [10,20,30,40,50],
				columns: [[
					{field:'score',title:'赠送积分',width:100},
					{field:'date',title:'日期',width:100}
				]]
				}";>
			</table>
		</div>
		<div id="grid-toolbar" class="clearfix p5" style="align:center;margin-top:50px;">
			<a id="changekey" class="btn btn-sm btn-info"><i class="icon icon-edit"></i>修改密码</a>&nbsp;&nbsp;
			<!-- <a id="change" class="btn btn-sm btn-info"><i class="icon icon-edit"></i>修改信息</a>  -->
	   </div>
	</div>
	 --%>
	<br/><br/><br/><br/><br/>
	<div class="sp form-table" align="center" style="font-size:14px">
        <div align="center"  style="width:100%" >
			<h3>会员信息</h3>
		</div>	
		<div  style="width:60%">
			<p class="pTitle">基本信息</p>
			<hr/>	<br/><br/>
		   <table >
		   	<input id="memberId" type="hidden" value="${member.id }"/>
			<tr style="height:60px;">
				<td>会员编号</td>
				<td width="1">：</td>
				<td>
				${member.memberNo}
				</td>
				<td>会员名称</td>
				<td width="1">：</td>
				<td>
				${member.name} 
				</td>
			</tr>
			<tr >
				<td>一个月内到账积分</td>
				<td width="1">：</td>
				<td>
				${total } (分)
				</td>
				<td>积分</td>
				<td width="1">：</td>
				<td>
				${member.score } (分)
				</td>
			</tr>
		</table>
	</div>	
	<br/><br/><br/><br/><br/><br/><br/>
	<div id="grid-toolbar" class="clearfix p5" style="align:center;margin-top:50px;">
			<a id="changekey" class="btn btn-sm btn-info"><i class="icon icon-edit"></i>修改密码</a>&nbsp;&nbsp;
			<a id="change" class="btn btn-sm btn-info"><i class="icon icon-edit"></i>即将到账积分</a> 
			<a id="event" class="btn btn-sm btn-info"><i class="icon icon-edit"></i>积分事件</a>
	 </div>
</div>	
<script type="text/javascript">
var a = $("#memberId").val();
$("#changekey").on("click", function(){
	App.popup("${z:u('memop/mem_reset_pwd')}?id="+a,{title:"修改密码",width:360});
});

$("#change").on("click", function(){
	App.popup("${z:u('memop/member_score_event')}",{title:"即将到账积分",width:660,height:550});
});

$("#event").on("click", function(){
	App.popup("${z:u('memop/score_event_single')}?memId=${member.id }",{title:"积分事件",width:660,height:550});
});
</script>
