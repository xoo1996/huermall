<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<form id="form" action="${__url__}" method="post"  >
	 <div align="center" class="pt10">
     <div>
     	<div> <p class="pTitle" >会员密码设置</p></div>
     </div>
      <hr style="width:570px"/> 
     <table>
		<tr>
			<td>初始密码：</td>
			<td>
				<input type="text" id="password" name="password" value="${initPwd }" style="border:1px; background: #FFFFFF;"/>
			</td> 
	    </tr>
	    <tr ><td height="5"></td><td></td></tr>
		<tr>
			<td colspan="2" class="alignC">
				<button id="reset"  class="btn btn-small btn-success">初始密码重置</button>
			</td>
		</tr>       
		<tr ><td height="15"></td><td></td></tr>
	    <tr>
			<td>会员编号：</td>
			<td>
				<input type="text" id="memberNo" name="password" style="border:1px; background: #FFFFFF;"/>
			</td> 
	    </tr>
	    <tr ><td height="5"></td><td></td></tr>
		<tr>
			<td colspan="2" class="alignC">
				<button id="update"  class="btn btn-small btn-success">会员重置初始密码</button>
			</td>
		</tr>
	</table>
	</div>
</form>
<script type="text/javascript">
	$("#reset").on("click", function() {		
		App.ajaxx("${z:u('member/cfg_psd')}?password="+$("#password").val(),{type: "POST"});
		return false;
	});
	$("#update").on("click", function() {		
		App.ajaxx("${z:u('member/update_pw')}?memberNo="+$("#memberNo").val(),{type: "POST"});
		return false;
	});
</script>


