<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>

<!-- 设置角色-->
<form id="form" action="${__url__}" method="post"  style="width:380px">
    <table align="left" class="form-table ml30">
		 <tr>
		    <td>选择需要设置的角色：</td>
			<td>
			  <select class="jq-combobox" name="role.id" data-options="{
					method:'post',
					editable:true,
					url: '${z:u('public/getrole')}'}">
					<c:if test="${role.roleId!=null }">
					<option value="${ role.id}" selected="selected"></option>
					</c:if>
			  </select>
			</td>
		 </tr>
		 <tr>
		   <td></td>
		   <td></td>
		 </tr>   
		 <tr>
			<td colspan="2" class="alignC">
				<button type="submit" class="btn btn-small btn-success">确定</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    <a class="btn btn-small btn-success closeDl">返回</a>
			</td>
		</tr>   
	</table>
</form>
