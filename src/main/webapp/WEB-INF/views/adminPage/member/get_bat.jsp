<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>

<div  class="height1 jq-layout rel" data-options="fit:true"
	style="height:500px">
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="event_grid" data-options="{
			method:'post',
			pagination:false,
			singleSelect:true,
			<!-- fit:true,//自动补全 -->
		    url: '${z:u('membat/membatlist')}',
		    pageSize: 5,
		    pageList: [5],
			columns: [[
				{field:'id',checkbox:true},
				{field:'battery_type',title:'电池型号',width:180},
				{field:'battery_num',title:'电池库存',width:90},
				{field:'ready_get_bat',title:'已领电池',width:90},
				{field:'battery_re_qty',title:'客户电池余量',width:120},
				{field:'getnum',title:'领取电池数量',width:120,formatter:getnum},
				{field:'change',title:'操作',width:50,formatter:change},
				{field:'print',title:'打印',width:50,formatter:print}
			]],
			onLoadSuccess : function () {
		        $('#user_grid').datagrid('fixRownumber');
		    }
			}";>
		</table>
	</div>
</div>
<a id="hidden" target="_blank" style="display: hidden" ></a>

		<a style="margin-left:45%" class="btn btn-small btn-success closeDl">返回</a>
		
<script type="text/javascript">
	$(document).ready(function(){
		$('#event_grid').datagrid({
		    queryParams: {
		        memberId: '${memberId}'
		    },
		    onLoadSuccess: function (data) {
				$(".height1").css("height","450px");
		    } 
		});

	});
	
	function change(value,row,index){
		var acc = '<a  onclick="acc('+row.id+','+row.battery_num+','+row.battery_re_qty+')"><img title="电池领取" src="${__static__}/admin/img/battery.png"/></a>';
		return acc; 
	}
	
	function print(value,row,index){
		var acc = '<a  onclick="printpdf('+row.id+')"><img title="打印" src="${__static__}/admin/img/print.png"/></a>';
		return acc; 
	}
	function printpdf(id){
		var getnum = $("#get"+id).val();
		$.post("${z:u('membat/reprint_pdf')}?id="+id,function(data){
			var obj=jQuery.parseJSON(data);
			if(obj.status==0){
				alert("下载失败");
			}else if(obj.status==1){
				$("#hidden").attr("href",obj.info);
				document.getElementById("hidden").click();
			}
		});
	}
	
	function getnum(value,row,index){
		var acc = 
		'<a onclick="rem('+row.id+')"><img src="${__static__}/jquery-easyui-1.3.3/themes/icons/edit_remove.png"/></a>'+
		'<input id="get'+row.id+'" type="text" value="0" data-rule="quantity" style="width:50px" >'+
		'<a onclick="add('+row.id+','+row.battery_re_qty+')"><img src="${__static__}/jquery-easyui-1.3.3/themes/icons/edit_add.png"/></a>';
		return acc; 
	}
	
	function add(getid,battery_re_qty){
		if(Number($("#get"+getid).val())+6<=Number(battery_re_qty)){
		var a = Number($("#get"+getid).val())+Number(6);
		$("#get"+getid).val(a);
		}
	} 
	
	function rem(getid){
		if($("#get"+getid).val()>0){
		var a = Number($("#get"+getid).val())-Number(6);
		$("#get"+getid).val(a);
		}
	} 
	
	 function acc(id,battery_num,battery_re_qty){
		var getnum = $("#get"+id).val();
		$.messager.confirm('提示','是否领取'+$("#get"+id).val()+'颗电池?',function(r){
			if(r&& Number($("#get"+id).val())<=0){
		    	$.messager.alert('提示','电池数量有误');
		    }else if (r&&battery_num>=Number($("#get"+id).val())&&battery_re_qty>=Number($("#get"+id).val())){
		    	$("#event_grid").datagrid({
		        	url: '${z:u("membat/get_bat")}',
		            queryParams:{
		            	id: id,
		            	getBatNum:getnum
		    	    },
		    	    onLoadSuccess: function (data) {
		    	    	$.extend($.messager.defaults,{  
		    	    	    ok:"继续" 
		    	    	});
		    	    	$.messager.alert('提示','请进行电池领取凭证打印！','info',function () { 
		    	    		$.post("${z:u('membat/print_pdf')}?id="+id+"&&getBatNum="+getnum,function(data){
		    					var obj=jQuery.parseJSON(data);
		    					if(obj.status==0){
		    						alert("下载失败");
		    					}else if(obj.status==1){
		    						$("#hidden").attr("href",obj.info);
		    						document.getElementById("hidden").click();
		    					}
		    				});
		    	    	});
		    	    	$('.panel-tool-close').hide();
				    }
		        });
		    }else if(r&&battery_num<Number($("#get"+id).val())){
		    	$.messager.alert('提示','电池库存不足');
		    }
		    else if(r&&battery_re_qty<Number($("#get"+id).val())){
		    	$.messager.alert('提示','请求电池数量过多');
		    }
		}); 
	
	} 
	
</script>

