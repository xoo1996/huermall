<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<form id="form" action="${__url__}" method="post"  >
    <table align="left" class="form-table ml30">
     <tr>
			<td>用户名：</td>
			<td>
				<input type="text" name="name" id="name" style="border:0px; background: #FFFFFF;" class="jq-validatebox" data-options="required:true,validType:'chinese'"/>
			</td>
		</tr>
		<tr>
			<td>客户编号：</td>
			<td>
				<input type="text" name="memberNo" id="mno" style="border:0px; background: #FFFFFF;" required="required" onblur="searchTel()"/>
			</td>
		</tr>
		<tr>
			<td>身份证编号：</td>
			<td>
				<input class="jq-validatebox" type="text" name="idCardNo" style="border:0px; background: #FFFFFF;" data-options="required:false,validType:'idcard'"/>
			</td>
		</tr>
		<tr>
			<td>手机号码：</td>
			<td>
				<input type="text" name="phone" style="border:0px; background: #FFFFFF;"  class="jq-validatebox" data-options="required:true,validType:'allPhone'"/>
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
	function searchTel(){
		var ixxd= $("#mno").val();
		var realurl = '${z:u("member/search_tel")}?id='+ixxd;
		select(realurl);
	}
	
	function select(realurl){
		$.ajax({
			type:'POST',
			url:realurl,
			success:function(data){
				if(data.num){
					$("input[name=phone]").val(data.num);
				}else{
					alert("客户编号输入错误");
					$("input[name=memberNo]").val('');
					$("input[name=phone]").val('');
				}
			},
			dataType:'json'
		});
	}
</script>
