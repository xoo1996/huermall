<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>惠耳听力助听器积分商城</title>
	<link rel='icon' href='${__static__}/admin/img/pic.ico ' type='image/x-ico' /> 	
	<link href="${__static__}/admin/ui/jqui.min.css" rel="stylesheet" type="text/css"/>
	<link href="${__static__}/admin/css/admin.css" rel="stylesheet" type="text/css"/>
	<link href="${__static__}/admin/css/login.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
		var logUrl="${__url__}";
		var whref=window.location.href;
		var fIndex=whref.indexOf('/',7);
		var nowUrl=whref.substring(fIndex);
		if(logUrl!=nowUrl){
			/* var whref=window.location.href;
			var fIndex=whref.indexOf('/',7);
			var lIndex=whref.indexOf('/',fIndex+1);
			var logUrl=whref.substring(fIndex,lIndex)+"/public/login";
			window.location.href */
			//alert("OK");
			//var lIndex=whref.indexOf('/',fIndex+1);
			window.location.href=logUrl;
		}
</script>
	
</head>
<body id="login">
	<div class="login-wrap">
		<form id="form" action="${__url__}" method="post">
			<table>
				<tr>
					<td>
					<div class="loginTd ico_user">
					  <input id=account type="text" name="account" value="" />
					  <input id="" type="text" name="" value="" style="display:none"/>
					</div>
					</td>
				</tr>
				<tr>
					<td>
					<div class="loginTd ico_password">
					<input id="password" type="password" name="password" value="" />					
					</div>
					</td>
				</tr>
				<tr>
				
					<td>
						<button class="btn btn-success" id="login-btn"  style="width:376px;">
						<img src="${__static__}/admin/img/logBtn.png" style="width:376px;"/>
						</button>
						<div id="msg"></div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script src="${__static__}/mod/jquery/jquery.min.js" type="text/javascript"></script>
	<script src="${__static__}/mod/jquery/jquery.form.js" type="text/javascript"></script>
	<script type="text/javascript">
	   if (window != top){
			top.location.href = location.href;
	   }
	   
		//换验证码
		$("#login_captcha").click(function(){
			var time = Math.round(new Date().getTime()/1000);
			if(this.src.indexOf('?')>0){
				this.src=this.src.slice(0,-11)+'?'+time;
			}else{
				this.src=this.src+'?'+time;
			}
		});

		//提交
		$("#form").ajaxForm({
		    beforeSubmit: function() {
		    	var $name = $("input[name='account']");
		    	var $pwd = $("input[name='password']");
		    	//var $loginCaptcha = $("input[name='loginCaptcha']");
		    	if ($name.val() == "") {
		    		$name.focus();
		    		return false;
		    	}
		    	if ($pwd.val() == "") {
		    		$pwd.focus();
		    		return false;
		    	}
		  /*  	if ($loginCaptcha.val() == "") {
		    		$loginCaptcha.focus();
		    		return false;
		    	}*/
		    },
		    success:function(data) {
				if(data.status==1){
					//$("#msg").removeClass("error").addClass("correct").html(data.info);
					//window.location.reload();
					$("#msg").removeClass("error").addClass("correct").html(data.info);
					window.location.href = "${z:u('/')}";
				}else{
					$("#msg").removeClass("correct").addClass("error").html(data.info);
				}
				
		    },
		    dataType:"json"
		});
	</script>
</body>
</html>