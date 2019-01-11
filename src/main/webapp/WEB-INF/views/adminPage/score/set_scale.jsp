<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<%
String old=(String)request.getSession().getAttribute("old");
%>
<form id="form" action="${__url__}" method="post"  >
    <table align="center" class="form-table ml30">
    <tr>
			<td>目前值：</td>
			<td>
				<input type="text" name="old" style="border:0px; background: #FFFFFF;" value="<%=old %>" disabled="disabled"/>
			</td>
		</tr>
    	<tr>
			<td>设置积分抵扣金额比例：</td>
			<td>
				<input type="text" name="scoreMoney" style="border:0px; background: #FFFFFF;" required="required"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="alignC">
				<button type="submit" class="btn btn-small btn-success">提交</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>       
	</table>
</form>

