<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<!DOCTYPE html>
<html ng-app="app">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no,minimal-ui">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" href="${__static__}/mobile/css/reset.css">
		<link rel="stylesheet" href="${__static__}/mobile/css/dropload.css">
		<style type="text/css">
			html {
				width: 100%;
				height: 100%;
				overflow-x: hidden;
			}
			.outerScroller {
				text-align: left;
				width: 100%;
				height: 100%;
				-webkit-tap-highlight-color: rgba(0,0,0,.05);
				-webkit-touch-callout: none;
				-webkit-user-select: none;
				background: #2B2B2B;
				background: -moz-linear-gradient(top, #2B2B2B 0%, #ffffff 100%); 
				background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#2B2B2B), color-stop(100%,#ffffff)); 
				background: -webkit-linear-gradient(top, #2B2B2B 0%,#ffffff 100%);
				
			}
			.img-box {
				text-algin:center;
				position: absolute;
				top: 15px;
			}
			.container {
				background: #f5f5f5;
				position:relative;
			    z-index:10000;
			    width:100%;
			    top:165px;
			}
			.level1 {
				background: #ffffff;
				position:relative;
			    z-index:10001;
			    width:100%;
			    height: 251px;
			    top:0;
			    /*阴影效果，考虑兼容性*/
				-webkit-box-shadow:0 0 5px #130c0e;
  				-moz-box-shadow:0 0 5px #130c0e;
  				box-shadow:0 0 5px #130c0e;
			}
			.level2 {
				background: #ffffff;
				position:relative;
			    z-index:10001;
			    width:100%;
			    height: 115px;
			    top: 50px;
			    /*阴影效果，考虑兼容性*/
				-webkit-box-shadow:0 0 5px #130c0e;
  				-moz-box-shadow:0 0 5px #130c0e;
  				box-shadow:0 0 5px #130c0e;
			}
			.level3 {
				background: #ffffff;
				position:relative;
			    z-index:10001;
			    width:100%;
			    height: 180px;
			    top: 100px;
			    /*阴影效果，考虑兼容性*/
				-webkit-box-shadow:0 0 5px #130c0e;
  				-moz-box-shadow:0 0 5px #130c0e;
  				box-shadow:0 0 5px #130c0e;
			}
			.bottom {
				position:relative;
			    z-index:10001;
			    width:100%;
			    height: 60px;
			    top: 100px;
			    text-align: center;
			    background: #f5f5f5;
			}
			.nav {
				position: relative;
				z-index: 10002;
				width: 100%;
				height: 50px;
			}
			.nav-title {
				position:absolute;
			    top:16px;
			    left:25px;
			    font-size: 18px;
			}
			.left-icon {
				position: absolute;
				top: 13px;
				left: 25px;
				width: 25px;
				height: 25px;
			}
			.icon-text {
				position:absolute;
			    top:15px;
			    left:73px;
			    font-size: 20px;
			    color: #000000;
			}
			.other-text {
				position:absolute;
			    top:17px;
			    left:25px;
			    font-size: 20px;
			    color: #000000;
			}
			.bottom-text {
				position:absolute;
				width: 100%;
				height: 21px;
			    bottom:10px;
				text-align: center;
			    font-size: 10px;
			}
			.right-icon {
				position: absolute;
				top: 17px;
				right: 13px;
				width: 18px;
				height: 18px;
				background: url(data/fig6.png);
				background-repeat: no-repeat; 
				background-size: contain;
			}
			.right-text {
				position:absolute;
			    top:17px;
			    right:43px;
			    font-size: 17px;
			}
			.opacity{
		        -webkit-animation: opacity 0.5s linear;
		        animation: opacity 0.5s linear;
		    }
		    @-webkit-keyframes opacity {
		        0% {
		            opacity:0;
		        }
		        100% {
		            opacity:1;
		        }
		    }
		    @keyframes opacity {
		        0% {
		            opacity:0;
		        }
		        100% {
		            opacity:1;
		        }
		    }
			
		</style>
		
	</head>
	<body>
		<div class="outerScroller">
			
				<div class="img-box">
					<img src="data/logo.png" />
				</div>
				<div class="container opacity">
					<div class="level1">
						<div class="nav">
							<div id="nav_title" class="nav-title"></div>
						</div>
						<hr align="right" style="height:1px; width:80%; border:none;background-color:#E8E8E8;" />
						<div id="chuzhi" class="nav" onclick="javascript:location.href='chuzhi.html' ">
							<div class="left-icon" style="background: url(data/fig1.png);background-size: contain;"></div>
							<div class="icon-text">我的储值</div>
							<div class="right-icon"></div>
							<div id="right_text1" class="right-text"></div>  
						</div>
						<hr align="right" style="height:1px; width:80%; border:0;background-color:#E8E8E8;" />
						<div id="jifen" class="nav">
							<div class="left-icon" style="background: url(data/fig2.png);background-size: contain;"></div>
							<div class="icon-text">我的积分</div>
							<div class="right-icon"></div>
							<div id="right_text2" class="right-text"></div>  
						</div>
						<hr align="right" style="height:1px; width:80%; border:0;background-color:#E8E8E8;" />
						<div id="juan" class="nav">
							<div class="left-icon" style="background: url(data/fig3.png);background-size: contain;"></div>
							<div class="icon-text">我的卷</div>
							<div class="right-icon"></div>
							<div id="right_text3" class="right-text"></div>  
						</div>
					</div>
					<div class="level2">
						<div id="xinxi" class="nav">
							<div class="left-icon" style="background: url(data/fig4.png);background-size: contain;"></div>
							<div class="icon-text">个人信息</div>
							<div class="right-icon"></div>
							<div class="right-text">完善</div>  
						</div>
						<hr align="right" style="height:1px; width:80%; border:0;background-color:#E8E8E8;" />
						<div id="zhangdan" class="nav">
							<div class="left-icon" style="background: url(data/fig5.png);background-size: contain;"></div>
							<div class="icon-text">账单</div>
							<div class="right-icon"></div>  
						</div>
					</div>
					<div class="level3">
						<div id="mendian" class="nav">
							<div class="other-text">门店及电话</div>
							<div class="right-icon"></div> 
						</div>
						<hr align="right" style="height:1px; width:80%; border:0;background-color:#E8E8E8;" />
						<div id="shuoming" class="nav">
							<div class="other-text">会员卡说明</div>
							<div class="right-icon"></div> 
						</div>
						<hr align="right" style="height:1px; width:80%; border:0;background-color:#E8E8E8;" />
						<div id="bangding" class="nav">
							<div class="other-text">绑定实体会员卡</div>
							<div class="right-icon"></div> 
						</div>
					</div>
					<div class="bottom">
						<div class="bottom-text">点评微生活</div>
					</div>
				</div>
			
		</div>	
		<script src="${__static__}/mobile/lib/zepto.min.js"></script>
		<script src="${__static__}/mobile/lib/dropload.min.js"></script>
		<script>
			window.jQuery = $;
		</script>
		<script src="${__static__}/mobile/js/jquery.jsonp.js"></script>

		<script>
			$(function(){
				//初始化
				$.get("${z:u('memop/member_info_json')}",function(data){
					$("#nav_title").html('会员卡号：&nbsp;&nbsp;'+data.memberNo);
			        $("#right_text1").html(data.coin);
			        $("#right_text2").html(data.score);
			        $("#right_text3").html(data.waitScore);
			        },'json');
			    // dropload
			    $('.outerScroller').dropload({
			        scrollArea : window,
			        domUp : {
			            domClass   : 'dropload-up',
			            domRefresh : '<div class="dropload-refresh">↓下拉刷新</div>',
			            domUpdate  : '<div class="dropload-update">↑释放更新</div>',
			            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
			        },
			        loadUpFn : function(me){
			            $.ajax({
			                type: 'GET',
			                url: '${__root__}/memop/member_info_json',
			                dataType: 'json',
			                success: function(data){
			                    // 为了测试，延迟1秒加载
			                    setTimeout(function(){
			                        $("#nav_title").html('会员卡号：&nbsp;&nbsp;'+data.memberNo);
			        				$("#right_text1").html(data.coin);
			        				$("#right_text2").html(data.score);
			        				$("#right_text3").html(data.waitScore);
			                        // 每次数据加载完，必须重置
			                        me.resetload();
			                    },1000);
			                },
			                error: function(xhr, type){
			                    alert('Ajax error!');
			                    // 即使加载出错，也得重置
			                    me.resetload();
			                }
			            });
			        },
			        threshold : 50
			    });
			});
		</script>

		
	</body>
</html>