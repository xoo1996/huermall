<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<form id="form" action="/huiermall/activity/save_lunbo" method="post" enctype="multipart/form-data" >
    <table align="left" class="form-table ml30">
            <td><input type="hidden" name="index" value="${index }"/>  </td>  
		<tr>  
            <td width="100" align="right">照片：</td>  
            <td><input type="file" name="file"/>  </td>  
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

