<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<form id="form" action="gift/add_gift" method="post" enctype="multipart/form-data" >
    <table align="left" class="form-table ml30">
     	<tr>
			<td>名称：</td>
			<td>
				<input type="text" name="name" style="border:0px; background: #FFFFFF;" />
			</td>
		</tr>
		<tr>
			<td>礼品编号：</td>
			<td>
				<input type="text" name="giftNo" style="border:0px; background: #FFFFFF;" />
			</td>
		</tr>
		<tr class="photo">  
            <td width="100" align="right">照片：</td>  
            <td><input type="file" name="photo"/>  </td>  <td><input type="button" class="btn btn-small addphoto" value="添加"/></td>
        </tr> 
        
		<tr>
			<td>库存：</td>
			<td>
				<input type="text" name="storeNum" style="border:0px; background: #FFFFFF;"/>
			</td>
		</tr>
		<tr>
			<td>积分：</td>
			<td>
				<input type="text" name="score" style="border:0px; background: #FFFFFF;"/>
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

<script type="text/javascript">
	$(".addphoto").on("click", function() {
		$("tr.photo:last").after('<tr class="photo"><td width="100" align="right">照片：</td><td><input type="file" name="photo"/></td></tr>');
	});
</script>

