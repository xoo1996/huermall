<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>
<form id="form" action="${__url__}" method="post"  >
    <table align="left" class="form-table ml30">
     <tr>
			<td>兑换电池：</td>
			<td>
				<select class="jq-combobox" style="width:160px" id="battGroup" name="battGroup" data-options="{
					method:'post',
					editable:true,
					url: '${z:u('public/getbatt')}'}">
					<c:if test="${id!=null }">
					<option value="${id}" selected="selected"></option>
					</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td>单件电池所需积分：</td>
			<td id="score">
			</td>
		</tr>
		<tr>
			<td>库存数量：</td>
				<td id="num">
			</td>
		</tr>
		<tr>
			<td>兑换数量：</td>
			<td>
				<input id="number" type="text" name="number" style="border:0px; background: #FFFFFF;"/>
			</td>
			<td>共需积分：</td>
			<td id="needscore">
			</td>
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
	$('#battGroup').combobox({
		onSelect: function(){
			var id= $("#battGroup").combobox("getValue");
			var realurl = '${z:u("member/test")}?id='+id;
			select(realurl);
		}
	});
	
	function select(realurl){
		$.ajax({
			type:'POST',
			url:realurl,
			success:function(data){
				$("#num").html(data.num);
				$("#score").html(data.score);
				score = data.score;
			},
			dataType:'json'
		});
	}
	$('#number').bind('input propertychange', function() {
	    	$('#needscore').html( $(this).val() * score );
			var num=$("#num").text();
			var number=$("#number").val();
			if(parseInt(num)<parseInt(number)){
			alert("库存数量不足");
			return false;}
	});

</script>

