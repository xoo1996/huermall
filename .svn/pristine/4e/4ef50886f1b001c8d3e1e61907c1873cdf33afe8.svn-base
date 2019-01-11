<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>

 table td{
   text-align:left!important;
 }
  table td:nth-child(2){
   width:1px;
 }
 .alignC{
   text-align:center!important;
 }
</style>
     <div align="center" class="pt10">
     <div>
     	<div> <p class="pTitle" >配置轮播图片</p></div>
    <!--  	<div><button id="add" class="btn btn-small">添加</button></div> -->
     </div>
     <hr style="width:700px"/>
    	 <c:if test="${fn:length(pddList) > 0}">
	          <table style="width:700px;" class="form-table">
	          <tbody class="haha">
	          		<tr	style="text-align:center"> 
		          		<td>序号</td>
			          	<td>图片</td>
			          	<td>当前活动</td>
			          	<td>操作</td>
	          		</tr>
		          <c:forEach items="${pddList }" var="pd" varStatus="statusMain">
		            <tr>
		            	<td width="10%" class="insss" value="${pd.id }">
							${pd.picIndex }
					   </td >
		               <td width="15%">
		               	<img alt="" src="${pd.url }" style="width:120px;height:120px">
		               </td>
		               <td width="20%">
		               	<c:if test="${pd.act!=null }">
		               		${pd.act.name }
		               	</c:if>
							
					   </td >
					   <td width="40%">
						<button class="delete btn btn-small" value="${pd.id }">删除</button>
					  	<button class="add btn btn-small" value="${pd.id }">插入</button>
					  	<button class="match btn btn-small" value="${pd.id }">配置活动</button>
					   </td>
					</tr>
					</c:forEach>
	          	</tbody>
			  </table>
		   </c:if>
		   <c:if test="${fn:length(pddList) == 0}">
				<button class="add btn btn-small" value="1">插入</button>
			</c:if>	
     </div>

<script>
	function getCurrentIndex(){
		var list = $(".haha").find(".insss");
		var n = list.length;
    	var i;
    	var data = "";
 		for(i = 0 ;i < n ;i++){
 			var id = list.eq(i).attr("value");
 			var index = list.eq(i).text();
 			var s = id + "=" + index;
 			data += s + ",";
 		}
 		return data;
	}
	
	$('.haha').sortable({
        update : function(event, ui){       //更新排序之后
            var list = $(".haha").find(".insss");
        	var n = list.length;
        	var i;
     		for(i = 0 ;i < n ;i++){	
     			list.eq(i).text(i + 1);
     		}
    		var data = getCurrentIndex();
    		$.post("${z:u('activity/correct_lunbo')}?data=" + data,function(){
    			$("li.active").click();
    		});
        }
	});
	$(".delete").on("click",function(){
		var str = this.value;
		var s = str.split("/");
		var length = s.length;
		var id = this.value;
		App.ajax_lunbo("${z:u('activity/delete_lunbo')}?id="+id,{type: "POST"});
	});

	$(".add").on("click",function(){
		var index = $(this).parent().siblings(".insss").text();
		App.popup_lunbo("${z:u('activity/save_lunbo_jsp')}?index="+index,{
	        title: "添加轮播图片",width: 600,height:500});
		$("li.active").click();
	});
	
	$(".match").on("click",function(){
		var id = $(this).val();
		App.popup_lunbo("${z:u('activity/match_activity')}?id="+id,{
	        title: "配置活动",width: 340,height:230});
		/* $("li.active").click(); */
	});
</script>

 <%-- <script>
	// You can also use "$(window).load(function() {"
	$(function () {
	  // Slideshow 
	  $("#slider").responsiveSlides({
		auto: false,
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
	  
	   $("img").on("click",function(){
		var str = this.src;
		var s = str.split("/");
		var length = s.length;
		App.ajax_lunbo("${z:u('activity/delete_lunbo')}?id="+s[length-1],{type: "POST"});
	}); 
	/* $(".delete").on("click",function(){
		
		var str = this.src;
		var s = str.split("/");
		var length = s.length;
		App.ajax_lunbo("${z:u('activity/delete_lunbo')}?id="+s[length-1],{type: "POST"});
	}); */

	$("#add").on("click",function(){
		App.popup_lunbo("${z:u('activity/save_lunbo_jsp')}",{
	        title: "重置口令",width: 600,height:500});
		$("li.active").click();
	});
	});
</script>

<div class="slider">
	<!-- Slideshow -->
	<div class="callbacks_container">
		<ul class="rslides" id="slider">
		<c:forEach items="${pathList }" var="url">
			<li>
				<img src="${url }" alt=""  style="width:400px;height:250px">
				<div class="caption">
					<h1>CAR DEALER WEBSITE NAME</h1>
					<span >Lorem ipsum dolor sit amet</span>
				</div>
			</li>
		</c:forEach>
		</ul>
	</div>
	<div class="clear"></div>
	<button id="add" class="btn btn-small">添加</button>
</div>  --%>