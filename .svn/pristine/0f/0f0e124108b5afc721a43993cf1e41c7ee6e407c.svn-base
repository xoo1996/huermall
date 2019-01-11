<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>

<!DOCTYPE html>
<html lang="en">


<%@include file="/WEB-INF/views/public/member/member_header.jsp" %>	
<style>
.drow {
	padding-top:10px;
}
.drow a{
	padding-left:10px;
}

</style>
<body>
	<div class="wrap-body">
		<%@include file="/WEB-INF/views/public/member/member_nav.jsp" %>	
		<div class="slider">
			<!-- Slideshow -->
			<div class="callbacks_container">
				<ul class="rslides" id="slider">
					<c:if test="${fn:length(imgNames) > 0}">
						<c:forEach var="picture" items="${imgNames}" varStatus="statusSub">
						<c:if test="${picture.activityId != null }">
							<li> <a target="_blank" href="<%=request.getContextPath() %>/service/show_activity?id=${picture.activityId }" ><img src="${picture.url}"></a></li>
						</c:if>
						<c:if test="${picture.activityId == null }">
							<li> <img src="${picture.url}"></li>
						</c:if>
							
						</c:forEach>
					</c:if>
				</ul>
			</div>
			<div class="clear"></div>
		</div>

		<!--////////////////////////////////////Container-->
		<section id="container">
			<div class="wrap-container clearfix">
				<div id="main-content">
					<div class="wrap-box">
						<!--Start Box-->
						<div class="zerogrid">
							<div class="header">
								<h2>近期活动</h2>
							</div>
							<div class="row">
							<c:forEach var="subNode" items="${activity}">
								<div class="col-1-3">
									<div class="wrap-col">
										<div class="item t-center">
											<div class="item-container">
												<a href="/huiermall/service/show_activity?id=${subNode.id }"  target="_blank">
													<div class="item-caption">
														<div class="item-caption-inner">
															<div class="item-caption-inner1">
																<span>${ subNode.name}</span>
															</div>
														</div>
													</div> 
													<img src="${subNode.coverUrl }"/>
												</a>
											</div>
											<div class="item-info">
												<a href="single.html"><h3>${ subNode.name}</h3></a>
												<p>${ subNode.name}</p>
											</div>
										</div>	
									</div>
								</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		
		<footer>
			<div class="drow">
				<a href="#">惠耳助听器</a>
				<a href="#">惠耳助听器</a>
				<a href="#">惠耳助听器</a>
				<a href="#">惠耳助听器</a>
			</div>
			<div class="zerogrid">
				<div class="wrap-footer">
					<div class="row">
						<span><strong>Copyright &copy; 惠耳</strong></span>
					</div>
				</div>
			</div>
		</footer>

	</div>
</body>

<script src="${__static__}/admin/ui/jqui.min.js" type="text/javascript"></script>
<script src="${__static__}/mod/jquery/jquery.hashchange.min.js"
	type="text/javascript"></script>
<script src="${__static__}/admin/js/admin.min.js" type="text/javascript"></script>
</html>

