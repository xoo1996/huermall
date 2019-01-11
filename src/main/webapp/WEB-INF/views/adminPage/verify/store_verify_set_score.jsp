<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<form id="form" action="${__url__}" method="post" >
    <table align="left" class="form-table ml30">
     	<tr>
			<td>申请人编号：</td>
			<td>
				<input type="text" name="newprice" style="border:0px; background: #FFFFFF;" />
			</td>
		</tr>
		
		
		<tr>
			<td colspan="2" class="alignC">
				<a id="set_score" class="btn btn-small btn-success closeDl">确定</a>
			</td>
		</tr> 
	      
	</table>
</form>
<script>
	$(document).ready(function(){
		$("#set_score").click(function(){
			$("#append").append("asdfasdfasd");
		});
	});
</script>