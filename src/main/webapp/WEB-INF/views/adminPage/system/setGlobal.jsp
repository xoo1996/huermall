<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<form id="form" action="${__url__}" method="post"  >
	 <div align="center" class="pt10">
     <div>
     	<div> <p class="pTitle" >全局变量设置</p></div>
     </div>
      <hr style="width:570px"/> 
     <table>
		<tr>
			<td>自动审批：</td>
			<td>
				<select class="jq-combobox" style="width:120px" id="autoVerify" name="autoVerify" data-options="{
					method:'post',
					url: '${z:u('param/auto_verify')}'}">
					<option value="${global.autoVerify}" selected="selected"></option>
				</select>
			</td>
	    </tr>
	    <br/>
		<tr>
			<td colspan="2" class="alignC">
				<button id="update"  class="btn btn-small btn-success">确定</button>
			</td>
		</tr>
	</table>
	</div>
</form>
<script type="text/javascript">
	$("#update").on("click", function() {		
		App.ajaxx("${z:u("system/global")}?autoVerify="+$("#autoVerify").combobox("getValue"),{type: "POST"});
		return false;
	});
</script>


