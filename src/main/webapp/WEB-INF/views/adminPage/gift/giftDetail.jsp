<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>

<head>
	<link rel="stylesheet" href="${__static__}/member_index/css/zerogrid.css">
	<link rel="stylesheet" href="${__static__}/member_index/css/style.css">
	<link rel="stylesheet" href="${__static__}/member_index/css/responsiveslides.css">
	<script src="${__static__}/member_index/js/jquery-latest.min.js"></script>
	<script src="${__static__}/member_index/js/script.js"></script>
	<script src="${__static__}/member_index/js/jquery183.min.js"></script>
	<script src="${__static__}/member_index/js/responsiveslides.min.js"></script>
    <script>
		// You can also use "$(window).load(function() {"
		$(function () {
		  // Slideshow 
		  $("#slider").responsiveSlides({
			auto: true,
			pager: false,
			nav: true,
			namespace: "callbacks",
			before: function () {
			  $('.events').append("<li>before event fired.</li>");
			},
			after: function () {
			  $('.events').append("<li>after event fired.</li>");
			}
		  });
		});
	</script>
    
</head>
<body>
<div class="wrap-body">



<!--////////////////////////////////////Container-->
<section id="container">
	<div class="">
		<div class="wrap-container clearfix">
			<div id="main-content">
				<div class="wrap-box" style="background: #fff; box-shadow: 2px 2px 5px 0px rgba(0,0,0,0.3);"><!--Start Box-->
					<div class="zerogrid">
						<div class="header">
							<h1>${gift.name }</h1>
						</div>	
						<div class="row">
							<div class="col-2-3">
								<div class="wrap-col">
									<div class="slider">
										<!-- Slideshow -->
										<div class="callbacks_container">
											<ul class="rslides" id="slider">
												<c:forEach var="node" items="${pictureList}" varStatus="statusMain">
													<tr>
														<li><img alt="" src="/huiermall/public/special/img/show_gift/${node.id }"  style="width:780px;height:500px;"></li>
													</tr>
												</c:forEach>
											</ul>
										</div>
										<div class="clear"></div>
									</div>
								</div>
							</div>
							<div class="col-1-3">
								<div class="wrap-col">
									<p class="price">${gift.name } </p>
									<ul class="specs">
										<li><strong>所需积分</strong> <span>${gift.score}</span></li>
										<!-- <li><strong>Model</strong> <span>Continental</span></li>
										<li><strong>Registration year</strong> <span>2010</span></li>
										<li><strong>Mileage</strong> <span>23,400 km</span></li>
										<li><strong>Power</strong> <span>230 HP</span></li>
										<li><strong>Fuel</strong> <span>diesel</span></li>
										<li><strong>Gearbox</strong> <span>manual</span></li>
										<li><strong>Num of seats</strong> <span>4</span></li>
										<li><strong>Doors</strong> <span>2/3</span></li>
										<li><strong>Emission Class</strong> <span>EURO3</span></li>
										<li><strong>Vehicle Type</strong> <span>Cabrio/Roadster</span></li>
										<li><strong>Color</strong> <span>Green</span></li>
										<li><strong>Airbags</strong> <span>5</span></li>
										<li><strong>Climate Control</strong> <span>Air Conditioner</span></li>
										<li><strong>Damage vehicle</strong> <span>None</span></li> -->
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<!--////////////////////////////////////Footer-->
<!-- <footer>
	<div class="zerogrid">
		<div class="wrap-footer">
			<div class="row">
				<h3>Contact</h3>
				<span>Phone / +80 999 99 9999 </span></br>
				<span>Email / info@domain.com  </span></br>
				<span>Studio / Moonshine St. 14/05 Light City </span></br>
				<span><strong>Copyright &copy; 2016.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></strong></span>
			</div>
		</div>
	</div>
</footer> -->

</div>
</body>
















