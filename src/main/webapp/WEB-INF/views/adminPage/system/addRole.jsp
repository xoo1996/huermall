<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>

<form id="form" action="${__url__}" method="post" enctype="multipart/form-data">
    <table class="form-table ml30">
    	<!-- <tr>
			<td>编号：</td>
			<td>
				<input name="id" type="text"/>
			</td>
		</tr> -->
		<tr>
			<td>角色名称：</td>
			<td>
			   <input name="roleName" type="text" class="jq-validatebox" data-options="required:true"/>
			</td>
		</tr>
		<tr>
			<td>备注：</td>
			<td>
			   <input name="remark" type="text"/>
			</td>
		</tr>
	<!-- 	<tr>
			<td>创建时间：</td>
			<td>
			   <input name="cDate" type="text" class="datetimebox">
			</td>
			
		</tr>
		<tr>
			<td>修改时间：</td>
			<td>
			   <input name="mDate" type="text"  class="datetimebox">
			</td>
			
		</tr>	 -->
		 <tr>
		   		
			<td colspan="2" class="alignC">
				<button type="submit" class="btn btn-small btn-success">确定</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="btn btn-small btn-success closeDl">返回</a>
			</td>
		</tr>       
	</table>
</form>
