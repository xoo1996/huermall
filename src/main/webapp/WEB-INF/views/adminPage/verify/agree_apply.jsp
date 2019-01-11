<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<form id="form" action="${__url__}" method="post" >
    <table align="left" class="form-table ml30">
     	<tr>
			<td>被邀请人购买助听器价格为：</td>
			<td>
				<input type="text" name="newprice" style="border:0px; background: #FFFFFF;" class="jq-validatebox" data-options="required:true"/>
			</td>
		</tr>
		<tr>
			<td>被邀请人客户编号为：</td>
			<td>
				<input type="text" name="applyid" style="border:0px; background: #FFFFFF;" class="jq-validatebox" data-options="required:true"/>
			</td>
		</tr>
		<tr>
			<td>备注：</td>
			<td>
				<textarea style="width:190px" id="remark" name="remark" rows="5" cols="60"></textarea>
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
