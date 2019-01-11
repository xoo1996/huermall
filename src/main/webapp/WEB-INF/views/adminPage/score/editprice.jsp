<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<form id="form" action="${__url__}" method="post"  >
    <table align="left" class="form-table ml30">
    	<tr>
			<td>起始价格：</td>
			<td>
				<input type="text" name="startPrice" value="${edit.startPrice }" style="border:0px; background: #FFFFFF;" required="required"/>
			</td>
		</tr>
		<tr>
			<td>截止价格：</td>
			<td>
				<input type="text" name="endPrice" value="${edit.endPrice }" style="border:0px; background: #FFFFFF;" required="required"/>
			</td>
		</tr>
		<tr>
			<td>积分兑换值：</td>
			<td>
				<input type="text" name="changeScore" value="${edit.changeScore }" style="border:0px; background: #FFFFFF;" required="required"/>
			</td> 
	    </tr>
		<tr>
			<td colspan="2" class="alignC">
				<button type="submit" class="btn btn-small btn-success">保存</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="btn btn-small btn-success closeDl">取消</a>
			</td>
		</tr>       
	      
	</table>
</form>

