<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>

<head>

<meta charset="utf-8">
<title>惠耳听力会员系统</title>
<meta name="description" content="">
<meta name="author" content="">

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel='icon' href='${__static__}/admin/img/pic.ico ' type='image/x-ico' /> 
<link rel="stylesheet" href="${__static__}/member_index/css/zerogrid.css">
<link rel="stylesheet" href="${__static__}/member_index/css/style.css">
<link rel="stylesheet" href="${__static__}/member_index/css/responsiveslides.css">
<link rel="stylesheet" href="${__static__}/admin/css/admin.css">


<script src="${__static__}/member_index/js/jquery-latest.min.js"></script>
<script src="${__static__}/member_index/js/script.js"></script>
<script src="${__static__}/member_index/js/jquery183.min.js"></script>
<script src="${__static__}/member_index/js/responsiveslides.min.js"></script>


<script>
	$(function() {
		$("#slider").responsiveSlides({
			auto : true,
			pager : false,
			nav : true,
			speed : 500,
			namespace : "callbacks",
			before : function() {
				$('.events').append("<li>before event fired.</li>");
			},
			after : function() {
				$('.events').append("<li>after event fired.</li>");
			}
		});
	});
</script>

</head>

