<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<form id="form" action="${__url__}" method="post" >
    <table align="left" class="form-table ml30">
   		<tr>
			<td>当前活动：</td>
			<td>
					<c:if test="${empty act }">
						未配置活动！！！
					</c:if>
					<c:if test="${!empty act }">
						${act.name }
					</c:if>
				
			</td>
		</tr>
		
     	<tr>
			<td>活动：</td>
			<td>
				<select class="jq-combobox" style="width:170px" id="actId" name="actId" data-options="{
					method:'post',
					editable:true,
					url: '${z:u('public/getactivity')}'}">
				</select>
			</td>
		</tr>
		<tr> 
			<td colspan="2" class="alignC">
				<button type="submit" class="btn btn-small btn-success">确定</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="btn btn-small btn-success closeDl">返回</a>
			</td>
		</tr>      
	</table>
</form>

