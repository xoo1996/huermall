<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>

<style type="text/css">
.testswitch {  
    position: relative;  
    vertical-align: middle;
    float: left;   
    width: 90px;  
    margin: 0;  
    -webkit-user-select:none;   
    -moz-user-select:none;   
    -ms-user-select: none;  
}  
  
.testswitch-checkbox {  
    display: none;  
}  
  
.testswitch-label {  
    display: block;   
    overflow: hidden;   
    cursor: pointer;  
    border: 2px solid #999999;   
    border-radius: 20px;  
}  
  
.testswitch-inner {  
    display: block;   
    width: 200%;   
    margin-left: -100%;  
    transition: margin 0.3s ease-in 0s;  
}  
  
.testswitch-inner::before, .testswitch-inner::after {  
    display: block;   
    float: right;   
    width: 50%;   
    height: 30px;   
    padding: 0;   
    line-height: 30px;  
    font-size: 14px;   
    color: white;   
    font-family:   
    Trebuchet, Arial, sans-serif;   
    font-weight: bold;  
    box-sizing: border-box;  
}  
  
.testswitch-inner::after {  
    content: attr(data-on);  
    padding-left: 10px;  
    background-color: #64b76e;   
    color: #FFFFFF;  
}  
  
.testswitch-inner::before {  
    content: attr(data-off);  
    padding-right: 10px;  
    background-color: #EEEEEE;   
    color: #999999;  
    text-align: right;  
}  
  
.testswitch-switch {  
    position: absolute;   
    display: block;   
    width: 22px;  
    height: 22px;  
    margin: 4px;  
    background: #FFFFFF;  
    top: 0;   
    bottom: 0;  
    right: 56px;  
    border: 2px solid #999999;   
    border-radius: 20px;  
    transition: all 0.3s ease-in 0s;  
}  
  
.testswitch-checkbox:checked + .testswitch-label .testswitch-inner {  
    margin-left: 0;  
}  
  
.testswitch-checkbox:checked + .testswitch-label .testswitch-switch {  
    right: 0px;   
}  
</style>
<div class="jq-layout rel" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height:36px;">
		<div id="grid-toolbar" class="clearfix p5" style="float:left;">
			
			<td align="right">会员名：</td>
			<td>
				<input type="text" id="memberName" name="memberName" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">会员编号：</td>
			<td>
				<input type="text" id="memberNo" name="memberNo" style="width:100px" placeholder="模糊查询"/>
			</td>
			<td align="right">手机号码：</td>
			<td>
				<input type="text" id="memberPhone" name="memberPhone" style="width:100px" placeholder="精确查询"/>
			</td>
            <button class="btn btn-sm btn-info" id="search_btn">查询</button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                      自动审批：
		</div>
		
		<div class="testswitch" style="float:left; margin-left:10px;"> 
            <input class="testswitch-checkbox" id="onoffswitch" checked="checked" type="checkbox">  
            <label class="testswitch-label" for="onoffswitch">  
                <span class="testswitch-inner" data-on="ON" data-off="OFF"></span>  
                <span class="testswitch-switch"></span>  
            </label>  
        </div> 
	
	</div>
	<div data-options="region:'center',border:false">
		<table class="jq-datagrid"  id="user_grid" data-options="{
			method:'post',
			pagination:true,
			singleSelect:true,
			fit:true,//自动补全
		    url: '${z:u('verify_bat/verifying_list')}',
		    pageSize: 20,
		    pageList: [10,20,30,40,50],
			columns: [[
				{field:'id',title:'审核号',width:70},
				{field:'storeId',title:'赠送店铺',width:70},
				{field:'memName',title:'会员名称',width:70},
				{field:'phone',title:'会员手机号',width:70},
				{field:'batType',title:'电池型号',width:70},
				{field:'batNum',title:'赠送数量',width:70},
				{field:'applyDate',title:'申请时间',width:100},
				{field:'operation',title:'操作',width:50,formatter:operation},
			]],
			onLoadSuccess : function () {
		        $('#user_grid').datagrid('fixRownumber');
		    }
			}";>
		</table>
	</div>
</div>
<script type="text/javascript">
	
	var status;
	$(document).ready(function() {  
		document.getElementById("onoffswitch").checked=${switchstatus};
	    $("#onoffswitch").on('click', function(){  
	        clickSwitch()  
	    });  
	  
	    var clickSwitch = function() {  
	        if ($("#onoffswitch").is(':checked')) {  
	        	status = "on";
	            console.log("在ON的状态下");
	            $.post("${z:u('verify_bat/change_verify_status')}?status="+status);
	        } else {  
	        	status = "off";
	            console.log("在OFF的状态下");
	            $.post("${z:u('verify_bat/change_verify_status')}?status="+status);
	        }  
	    };  
	}); 




	$("#search_btn").on("click", function() {//搜索查询		
	    var memberName = $("#memberName").val();
	    var memberNo = $("#memberNo").val();
	    var memberPhone = $("#memberPhone").val();
	    $("#user_grid").datagrid({
	    	url: '${z:u('verify_bat/verifying_list')}',
	        queryParams:{
	        	memberName: memberName,
	        	memberNo: memberNo,
	        	memberPhone: memberPhone,
		    }
	        });
		    
	});
	var pass="pass";
	var reject="reject";
	
	function operation(value,row,index){

		var acc = '<a  onclick="verifyAddMemBat('+row.id+','+pass+')"><img title="通过" src="${__static__}/admin/img/ok.png"/></a>';
		var ac = '<a onclick="verifyAddMemBat('+row.id+','+"reject"+')"><img title="不通过" src="${__static__}/admin/img/no.png"/></a>';
		return acc + "&nbsp;&nbsp;" +ac; 
	}
		

 	
	function verifyAddMemBat(id,flag){
		$.messager.confirm('警告','请再确认！',function(r){
            if (r){

                $.post("${z:u('verify_bat/verify')}?flag="+flag+"&id="+id,function(result){
                	var obj=jQuery.parseJSON(result);
        			if(obj.status==1){
                        $('#user_grid').datagrid('reload');
                    } else {
                        $.messager.alert('错误','操作失败');
                    }
                });
            }
        });
	}
	
</script>
