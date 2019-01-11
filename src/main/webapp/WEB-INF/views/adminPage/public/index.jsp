<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/public/admin/admin_header.jsp" %>
<body class="jq-layout" style="position: static;">
<!-- 头部 -->
	<%@include file="/WEB-INF/views/public/admin/admin_nav.jsp" %>
	<div id="left"
		data-options="region:'west',headerCls:'left-header',iconCls:'icon-chart-organisation',title:'子功能菜单'">
		<div class="jq-accordion" fit="true"
			data-options="animate:true,border:false,multiple:true">
			<c:forEach var="node" items="${leftMenuList}" varStatus="statusMain">
				<div class='${statusMain.first?"first":""} main-menu'
					title="${node.menuName }"
					data-options="iconCls:'icon-application-cascade'">
					<c:if test="${fn:length(node.menus) == 0}">
						<ul class="sub-menu" data-group="${node.menuName }">
							<li class='first' data-hash="/${node.url}"><a
								href='${z:u(node.url) }'>${node.menuName }</a></li>
						</ul>
					</c:if>
					<c:if test="${fn:length(node.menus) > 0}">
						<ul class="sub-menu" data-group="${node.menuName }">
							<c:forEach var="subNode" items="${node.menus}"
								varStatus="statusSub">
								<li class='${statusSub.first?"first":""}'
									data-hash="/${subNode.url}"><a href='${z:u(subNode.url) }'>${subNode.menuName }</a></li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</c:forEach>
		</div>
	</div>

	<div id="center" data-options="region:'center'">
		<div class="jq-layout rel" data-options="fit:true">
			<div data-options="region:'north',border:false">
				<div id="crumb" class="fix">
					<div class="fl">
						<em class="c1 icon icon-resultset-next"></em> <em class="c2"></em>
						<em class="c3"></em>
					</div>
					<div class="fr" style="padding-right: 1cm">
						<a href="javaScript:history.go(-1)">返回</a>
					</div>
				</div>
			</div>
			<div id="content" data-options="region:'center',border:false">

			</div>
		</div>
	</div>
	<div id="footer" data-options="region:'south'" style="height: 30px;">

		© 2016 杭州电子科技大学</div>

	<%@include file="/WEB-INF/views/public/admin/admin_js.jsp" %>
	
	<!-- ckeditor -->
	<script src="${__static__}/ckeditor/ckeditor.js" type="text/javascript"></script>
	<script type="text/javascript">
		//首次进入加载第一个左侧菜单
		/* if(window.location.hash==""){
			$("#sub-menu li.first").click();
		} */
		$(document).ready(function() {
			$(".main-menu.first li.first").click();
		})
		//if(${sessionScope.loginName} == null){
		$("#tabs").tabs();
		//查看账户信息
		$("#accountName")
				.on(
						"click",
						function() {
							App
									.popup(
											"${z:u('system/accountinfo')}?id=${sessionScope.account.id}",
											{
												title : "账户信息",
												width : 300
											});
						})
		//修改密码
		$("#editPws")
				.on(
						"click",
						function() {
							App
									.popup(
											"${z:u('system/reset_password')}?id=${sessionScope.account.id}",
											{
												title : "重置口令",
												width : 400
											});
						});
	</script>
</body>
</html>