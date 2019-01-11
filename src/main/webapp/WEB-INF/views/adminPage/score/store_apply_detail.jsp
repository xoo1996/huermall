<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<%
%>
<form id="form" action="${__url__}" method="post"  >
	<input  type="hidden" name="memberNo" value="${member.memberNo }"/>
     <table align="center" class="form-table ml30">
    	<tr style="padding:10px">
			<td >申请门店：</td>
			<td>
				${ account.name}
			</td>
		</tr>
		<tr style="padding:10px">
			<td >申请人：</td>
			<td>
				${ member.name}
			</td>
		</tr>
		<tr style="padding:10px">
			<td >申请人会员编号：</td>
			<td>
				${ member.memberNo}
			</td>
		</tr>
		<tr style="padding:10px">
			<td >已赠送积分值：</td>
			<td>
				${ event.changeScore}
			</td>
		</tr>
		<tr ><td height="5"></td><td></td></tr>
		<tr style="padding:10px">
			<td>申请积分值：</td>
			<td>
				<input type="text" id="score"  name="score" style="border:0px; background: #FFFFFF;" class="jq-validatebox" data-options="required:true,validType:'integer'"/>
			</td>
			<td>赠送积分范围在0 ~ ${max} 之间</td>
		</tr>
		<tr ><td height="5"></td><td></td></tr>
		<tr style="padding:10px">
			<td>备注：</td>
			<td>
				<textarea cols="100" rows="5"  id="remark" name="remark" style="border:2px; background: #FFFFFF;" required="required"></textarea>
			</td> 
	    </tr>
		<tr>
			<td colspan="2" class="alignC">
				<button type="submit"  class="btn btn-small btn-success">提交</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>   
	</table>    
</form>

<script type="text/javascript">
	var givenScore = ${max}; 
	$("#score").blur(function(){
		var appleScore = $(this).val();
		if(appleScore != ""){
			if(appleScore >= 0 && appleScore <= givenScore){
			}else{
				App.alert("申请积分值超出范围！","warning");
			}
		}
	});

</script>


