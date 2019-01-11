<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>softphoneBar</title>
<script src="${__static__}/edb_bar/hojo/hojo.js"></script>
<script src="${__static__}/mod/jquery/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#login").click(function(){
		var loginName = hojo.byId("icc.login.loginName").value;
		var password = hojo.byId("icc.login.password").value;
		var loginType = "Local"; //手机
//	    var loginType = "sip"; //软电话
//	    var loginType = "gateway"; //IP话机或者语音网关
		window.location.href = "${z:u('test/login')}?loginName=" + loginName + "&password=" + password + "&loginType=" + loginType;
/* 		$.ajax({type:'POST',
				url:"${z:u('test/login')}",
				data:{'loginName':loginName,'password':password,'loginType':'Local'}}); */
	});
});

/* function logon() {
	var loginName = hojo.byId("icc.login.loginName").value;
	var password = hojo.byId("icc.login.password").value;
	var loginType = "Local"; //手机
//    var loginType = "sip"; //软电话
//    var loginType = "gateway"; //IP话机或者语音网关
	$.post("${z:u('test/login')}" + "?loginName=" + loginName + "&password=" + password + "&loginType=" + loginType);
//	window.location.href = "${z:u('test/login')}?loginName=" + loginName + "&password=" + password + "&loginType=" + loginType;
} */
</script>
</head>
<body>
	<div align="center">
		<form action="${z:u('test/login')}" method="post">
			<table border=1 cellpadding="0" cellspacing="0">
			<input type="hidden" name="loginType" value="Local"/>
			<tr>
				<td>用户名：</td><td><input type="text" id="icc.login.loginName" name="loginName" value=""/></td>
			</tr>
			<tr>
				<td>密码：</td><td><input type="password" id="icc.login.password" name="password"  value="" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<button >登录</button>
				</td>
			</tr>
		</table>
		</form>
        
	</div>
</body>
</html>
