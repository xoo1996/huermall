<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<form id="form" action="${__url__}" method="post"  >
    <table align="left" class="form-table ml30">
     <tr>
			<td>兑换礼品：</td>
			<td>
				<select class="jq-combobox" style="width:160px" id="giftGroup" name="giftGroup" data-options="{
					method:'post',
					editable:true,
					url: '${z:u('public/getgift')}'}">
					<c:if test="${id!=null }">
					<option value="${id}" selected="selected"></option>
					</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td >单件礼品所需积分：</td><td id="sscore"></td>
		</tr>
		<tr>
			<td >库存：</td><td id="kucun"></td>
		</tr>
		<tr>
			<td>兑换数量：</td>
			<td>
				<input id="number" type="text" name="number" style="border:0px; background: #FFFFFF;"/>
			</td><td>消耗积分：</td><td id="jifen"></td>
		</tr>
		<tr>
			
		</tr>
		<tr>
			<td colspan="2" class="alignC">
				<button type="submit" class="btn btn-small btn-success">兑换</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="btn btn-small btn-success closeDl">取消</a>
			</td>
		</tr>       
	      
	</table>
</form>

<script type="text/javascript">
	var score = 0;
	$('#giftGroup').combobox({
		onSelect: function(){
			var id= $("#giftGroup").combobox("getValue");
			var realurl = '${z:u("gift/get_gift_num")}?id='+id;
			select(realurl);
		}
	});
	
	function select(realurl){
		$.ajax({
			type:'POST',
			url:realurl,
			success:function(data){
				$("#kucun").html(data.num);
				score = data.score;
				$('#sscore').html(score );
			},
			dataType:'json'
		});
	}
	
	$('#number').bind('input propertychange', function() {
	//	if(score != 0)
	    	$('#jifen').html( $(this).val() * score );
	});
</script>

