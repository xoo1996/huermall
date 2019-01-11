<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>

<div id="header" data-options="region:'north',border:false" class="fl">
	<div id="logo-right" class="fl">
		<img src="${__static__}/admin/img/logo.png" />
	</div>
	<div id="login-info" class="fl">
		<span class="first">欢迎，</span><span><a id="accountName"><i
				class="icon icon-user" style="padding-top: 5px;"></i>
				${sessionScope.account.name}</a></span><span>&nbsp;|&nbsp;</span> <span><a
			href='${z:u("/public/logout/admin")}'><i class="icon icon-changeC" style="padding-top: 5px;"></i> 更换账号</a></span><span>&nbsp;|&nbsp;</span> <span><a
			id="editPws"><i class="icon icon-editPw"
				style="padding-top: 5px;"></i> 修改密码</a></span><span>&nbsp;|&nbsp;</span> <span><a
			href='${z:u("/public/logout/admin")}'><i class="icon icon-out" style="padding-top: 5px;"></i> 退出</a></span><span>&nbsp;|&nbsp;</span>
		<span><a target="_blank" href="${__static__}/help.docx"><i
				class="icon icon-helphelp" style="padding-top: 5px;"></i>帮助</a></span>
		<c:if test="${isZongbu == true }">
			<div style="font-size:20px;color:red;margin-top:12px"> 
				<marquee behavior="alternate" scrollamount="1" style="max-width:400px;color:red">
					 <a id="invite1"><span id="invite"></span> 个邀请用户申请,</a><a id="teshu1"><span id="teshu"></span> 个特殊通道申请</a>
				</marquee>
			</div>
		</c:if>
	</div>
</div>
<script>
	$(document).ready(function(){
		getdata();
		window.setInterval(getdata, 10000); 
		
		
		$("#invite1").click(function(){
			var aa = $("li[data-hash='/verify/verifylist']");
			aa.click();
		})
		$("#teshu1").click(function(){
			var aa = $("li[data-hash='/verify/store_apply_list']");
			aa.click();
		})
	});
	
	<c:if test="${isZongbu == true }">
		function getdata(){
			$.ajax({
				url:"verify/get_data",
				success:function(result){
					var re = JSON.parse(result);
					$("#invite").text(re.invite);
					$("#teshu").text(re.teshu);
				}
			});
		}
	</c:if>
	
</script>