<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<form id="form" action="${__url__}" method="post" >
    <table align="left" class="form-table ml30">
    	<input id="tar" type="hidden" name="verifyResult"/>
     	<tr>
			<td>申请人编号：</td>
			<td>
				${verify.bsc011 }
			</td>
		</tr>
		<tr>
			<td>申请的积分值：</td>
			<td>
				${verify.applyScore }
			</td>
		</tr>
		<tr>
			<td>备注：</td>
			<td>
				${verify.content }
			</td>
		</tr>
		<tr id="append" style="visibility:hidden">
			<td>拟赠送积分值：</td>
			<td>
				<input value="${verify.applyScore }" type="text" name="newScore" style="border:0px; background: #FFFFFF;" />
			</td>
		</tr>
		<tr>
			<td colspan="2" class="alignC">
				<a id="pass" class="btn btn-small btn-success">通过</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="refuse" class="btn btn-small btn-success">不通过</button>
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

<script type="text/javascript">
	$(document).ready(function(){
		$("#pass").click(function(){
			if($(this).hasClass("pass_first")){
				$("#tar").val("pass");
				$("form").submit();
			}else{
				$("#append").css("visibility","visible");
				$(this).addClass("pass_first")
			}
		});
		
		$("#refuse").click(function(){
			$("#tar").val("refuse");
			$("form").submit();
		});
	});
</script>
