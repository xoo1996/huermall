<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<form id="form" action="${__url__}" method="post"  >
    <table align="left" class="form-table ml30">
    	<tr>
			<td>会员名：</td>
			<td>
				<input disabled type="text" name="name" value="${edit.name }" style="border:0px; background: #FFFFFF;" class="jq-validatebox" data-options="required:true,validType:'chinese'"/>
			</td>
		</tr>
		<tr>
			<td>会员编号：</td>
			<td>
				<input disabled type="text" name="memberNo" value="${edit.memberNo }" style="border:0px; background: #FFFFFF;" required="required"/>
			</td>
		</tr>
		<tr>
			<td>手机号码：</td>
			<td>
				<input disabled type="text" name="phone" value="${edit.phone }" style="border:0px; background: #FFFFFF;" class="jq-validatebox" data-options="required:false,validType:'allPhone'"/>
			</td> 
	    </tr>
	    <tr>
			<td>赠送电池型号：</td>
			<td>
				<select class="jq-combobox" style="width:170px" id="batId" name="batteryType" data-options="{
					method:'post',
					editable:true,
					url: '${z:u('battery/getbattery')}'}">
				</select>
			</td>
		</tr> 
		<tr>
			<td>赠送电池数量：</td>
			<td>
				<input type="text" name="batteryQty" style="border:0px; background: #FFFFFF;" required="required"/>
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

