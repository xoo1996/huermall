<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z" %>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>

<form id="form" action="${__url__}" method="post">
    <table class="form-table" align="center" style="margin:auto">
    	<tr>
			<td>
			   <input type="text" name="roleId" id="roleId" value="${roleId }" style="display:none;">
			   <input type="text" name="authorityNodes" id="authorityNodes" style="display:none;" />
			   
			   <div id="authorityTree" class="ztree"></div>
            </td>
        </tr>
		 <tr>
			<td style="text-align:center;padding:0px;">
				<button type="submit" class="btn btn-small btn-success">确定</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
			    <a class="btn btn-small btn-success closeDl">返回</a>
			</td>
		</tr>       
	</table>
</form>


<script type="text/javascript">
$(document).ready(function() {
	   createTree();
    });

   //展开全部节点
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	var treeObj = $.fn.zTree.getZTreeObj("authorityTree");
    treeObj.expandAll(true);
  
	var selNodeIds = isCheckStore();
	$("#authorityNodes").val(selNodeIds.toString());
    
}  
	/**
	 *生成权限树 
	 */
	function createTree() {
	    var roleId = $("#roleId").val();   
		var setting = {
			check : { enable : true },
			view : { selectedMulti : false, showLine : true },
			data : { simpleData : { enable : true} },
			//获取json数据 
			async : { 
				enable : true, 
				url : "${z:u('system/get_role_menu_tree')}?id="+roleId, 
				dataType : "json",
				type : "post"
				},
			callback: {    // 回调函数
		        onCheck: replace,    // 单击鼠标时
		        onAsyncSuccess:zTreeOnAsyncSuccess
			}			
		};
		$.fn.zTree.init($("#authorityTree"), setting); //tree 
	}	
	/**
	 * 判断是否选择权限
	 */
	function isCheckStore() {
		var selNodeIds = [];
		//测试树选中的节点
	    var treeObj = $.fn.zTree.getZTreeObj("authorityTree");
		var selectNodes = treeObj.getCheckedNodes(true); //选中的节点集合
		for ( var key in selectNodes) {
			selNodeIds.push(selectNodes[key]["id"]);
		}
		if (selNodeIds.length == 0) {
			return "";
		} else {
			return selNodeIds;
		}
	}
	/**
	*给form表单的输入框赋值
	*/
    function replace(event, treeId, treeNode){
		var selNodeIds = isCheckStore();
		$("#authorityNodes").val(selNodeIds.toString());
		//alert($("#authorityNodes").val());
	}
</script>