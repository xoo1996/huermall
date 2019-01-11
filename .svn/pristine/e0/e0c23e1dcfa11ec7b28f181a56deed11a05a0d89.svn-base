<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>

<header class="bg-theme">
			<div class="wrap-header zerogrid">
				<div class="row">
					<div id="cssmenu">
						<c:if test="${sessionScope.member != null }">
							<ul>
								<li><a href="${__root__}/memop/member_info" target="_blank">会员名：${member.name }</a></li>
								<li><a href="javascript:volid(0);">积分：${member.score }</a></li>
								<li><a href="javascript:volid(0);">惠耳币：${member.coin }</a></li>
								<li><a href="javascript:volid(0);">电池数量：${member.batterynum }</a></li>
								<li><a href='${z:u("/public/logout/member")}'>退出</a></li>
							</ul>
						</c:if>
						<c:if test="${sessionScope.member == null }">
							<ul>
								<li><a href="${__root__ }/public/memlogin">请登陆！</a></li>
							</ul>
						</c:if>
						<%-- <c:if test="${sessionScope.globalCfg != null}">
							<div>
								<marquee scrollamount="5" style="max-width:400px;font-size:15px;color:white">
									${globalCfg.rollAd }
								</marquee>
							</div>
						</c:if> --%>
					</div>
					<a href='${__url__}' class="logo">
						<img style="width:auto;height:auto" src="${__static__}/admin/img/logo.png" />
					</a>
				</div>
			</div>
			
		</header>