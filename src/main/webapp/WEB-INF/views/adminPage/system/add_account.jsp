<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<form id="form" action="${__url__}" method="post"  >
    <table align="left" class="form-table ml30">
     <tr>
			<td>账号名称：</td>
			<td>
				<input type="text" name="name" style="border:0px; background: #FFFFFF;" />
			</td>
		</tr>
		<tr>
			<td>账号编号：</td>
			<td>
				<input type="text" name="account" style="border:0px; background: #FFFFFF;"/>
			</td>
		</tr>
		<tr>
			<td>账号电话：</td>
			<td>
				<input type="text" name="gctTel" style="border:0px; background: #FFFFFF;"/>
			</td>
		</tr>
		<tr>
			<td>账号地址：</td>
			<td>
				<input type="text" name="gctAddr" style="border:0px; background: #FFFFFF;"/>
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

