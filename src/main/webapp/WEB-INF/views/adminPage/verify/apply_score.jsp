<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<%
%>
<form id="form" action="${__url__}" method="post" >
     <div align="center" class="pt10">
     <div>
     	<div> <p class="pTitle" style="padding-left:20%;">邀请用户申请</p></div>
     </div>
     <hr />
     <table class="form-table ml30">
    	<tr style="padding:10px">
			<td >新用户姓名：</td>
			<td>
				<input type="text" id="newname" name="newname" style="border:0px; background: #FFFFFF;" class="jq-validatebox" data-options="required:true,validType:'chinese'"/>
			</td>
		</tr>
		<tr ><td height="5"></td><td></td></tr>
		<tr style="padding:10px">
			<td>联系方式：</td>
			<td>
				<input type="text" id="newphone"  name="newphone" style="border:0px; background: #FFFFFF;" class="jq-validatebox" data-options="required:true,validType:'allPhone'"/>
			</td>
		</tr>
		<tr ><td height="5"></td><td></td></tr>
		<tr style="padding:10px">
			<td>邀请人会员编号：</td>
			<td>
				<input type="text"  id="memberNo" name="memberNo" style="border:0px; background: #FFFFFF;" required="required"/>
			</td> 
	    </tr>
		<tr>
			<td colspan="2" class="alignC">
				<button type="submit" id="submit" class="btn btn-small btn-success">提交</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>   
		</table>    
	</div>
</form>

<script type="text/javascript">
$(document).ready(function(){
	$("#submit").click(function(){
		var newname=$("#newname").val();
		if(!checkName(newname)){
			App.alert("请输入中文！","warning");
			return false;
		}
		var newphone=$("#newphone").val();
		if(!checkPhone(newphone)){
			App.alert("手机号码格式不正确！","warning");
			return false;
		}
		var memberNo=$("#memberNo").val();
	    App.ajaxx("${__url__}",
	    		{type:"POST",param:{memberNo:memberNo,newphone:newphone,newname:newname}});
	    return false;
	});
})
function check(){
	
	var newname=$("#newname").val();
	
	var newphone=$("#newphone").val();
	if(checkPhone(newphone)){
		alert(1);
		App.alert("手机号码格式不正确！","warning");
	}
	var memberNo=$("#memberNo").val();
    App.ajaxx("${__url__}",
    		{type:"POST",param:{memberNo:memberNo,newphone:newphone,newname:newname}});
    return false;
}
function checkPhone(t){
	return /^1\d{10}$/.test(t) || /^0\d{2,3}(\-)?\d{7,8}$/.test(t)
}
function checkName(t){
	return /^[Α-￥]+$/i.test(t)
}
</script>

