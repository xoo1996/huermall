<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	CKEDITOR.replace("editor");
	
    function CKupdate() 
    {
        for (instance in CKEDITOR.instances)
        {
        	CKEDITOR.instances[instance].updateElement();
        }
    }

});
</script>
<div style="width:960px; margin:0 auto;">
	<div style="width:920px; margin-top:20px;">
		<form id="form" method="post" action="/huiermall/activity/edit_activity" enctype="multipart/form-data">
			<input type="hidden" name="coverUrl" value="${activity.coverUrl }"/>
			<tr>
				<td>活动名称：</td>
				<td>
					<input type="text" name="name" value="${activity.name}" style="border:0px; background: #FFFFFF;" />
				</td>
			</tr>
			<tr>
				<td>活动封面：</td>
				<td>
					<input type="file"  name="file" style="border:0px; background: #FFFFFF;" />
				</td>
			</tr>
			<textarea name="content" rows="" id="editor" cols="" >${activity.content}</textarea><br/>
			<input type="submit" value="确定" >
		</form>
	</div>
</div>
