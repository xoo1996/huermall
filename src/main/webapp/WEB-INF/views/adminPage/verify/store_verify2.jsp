<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<form id="form" action="verify/op_store_apply2" method="post" >
    <table align="left" class="form-table ml30">
     	<tr>
			<td>申请人编号：</td>
			<td>
				${verify.bsc011 }
			</td>
		</tr>
		<tr>
			<td>申请的积分值：</td>
			<td>
				${verify.finalScore }
			</td>
		</tr>
		<tr>
			<td>备注：</td>
			<td>
				${verify.content }
			</td>
		</tr>
		<tr>
			<td>会员编号：</td>
			<td>
				<input  type="text" name="memberNo" style="border:0px; background: #FFFFFF;" required="required"/>
			</td>
		</tr>
		<tr>
			<td>订单编号：</td>
			<td>
				<input  type="text" name="folno" style="border:0px; background: #FFFFFF;" class="jq-validatebox" data-options="required:true,validType:'order'" />
			</td>
		</tr>
		<tr>
			<td colspan="2" class="alignC">
				<button type="submit" class="btn btn-small btn-success">确认已付款，发放积分</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="btn btn-small btn-success closeDl">关闭</a>
			</td>
		</tr>     
		
		
		
		
		<!-- <tr>
			<td>邀请人购买助听器价格为：</td>
			<td>
				<input type="text" name="newprice" style="border:0px; background: #FFFFFF;" />
			</td>
		</tr>
		<tr>
			<td colspan="2" class="alignC">
				<button type="submit" class="btn btn-small btn-success">确定</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="btn btn-small btn-success closeDl">返回</a>
			</td>
		</tr>      -->  
	      
	</table>
</form>
