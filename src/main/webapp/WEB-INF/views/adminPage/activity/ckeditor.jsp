<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
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

    $("#subAjax").click(function () {
    	var options = {
                url: "${z:u('/activity/ckeditor')}",
                success: function () {
                    $("li.active").next("li").click();
                }
            };
            $("#form").ajaxForm(options);
    });
			
});
</script>
<title>Insert title here</title>
</head>
<body>
<div style="width:960px; margin:0 auto;">
 <div align="center" class="pt10">
     <div>
     	<div> <p class="pTitle" style="padding-left:0%">新建活动</p></div>
     </div>
     <hr style="width:100%"/>
	<div style="width:920px; margin-top:20px;">
	
		<form id="form" method="post" enctype="multipart/form-data">
		
		            <div style="text-align:left;margin-bottom:5px">
		            	<td>活动名称：</td>
						<td>
							<input type="text" name="name" style="border:0px; background: #FFFFFF;" />
						</td>
					</div>
					<div style="text-align:left;margin-bottom:10px">
						<td>活动封面：</td>
						<td>
							<input type="file"  name="file" style="border:0px; background: #FFFFFF;" />
						</td>
					</div>
					
	          	<textarea name="content" rows="" id="editor" cols="" ></textarea>
					<br/>
					<input id="subAjax" class="add btn btn-small" type="submit" value="确定" > 
			<!-- <tr>
				<td>活动名称：</td>
				<td>
					<input type="text" name="name" style="border:0px; background: #FFFFFF;" />
				</td>
			</tr>
			<tr>
				<td>活动封面：</td>
				<td>
					<input type="file"  name="file" style="border:0px; background: #FFFFFF;" />
				</td>
			</tr>
			<textarea name="content" rows="" id="editor" cols="" ></textarea><br/>
			<input id="subAjax"  type="submit" value="确定" > -->
		</form>
	</div>
</div>
</body>
