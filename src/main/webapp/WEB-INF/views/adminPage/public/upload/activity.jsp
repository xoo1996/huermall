<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://zlzkj.com/tags" prefix="z"%>
<script src="${__static__}/admin/js/public.js" type="text/javascript"></script>


<form action="public/upload/${module }" method="post" enctype="multipart/form-data">  
    <table>  
        <tr>  
            <td width="100" align="right">照片：</td>  
            <td><input type="file" name="studentPhoto"/>  </td>  
        </tr>  
        <tr>  
            <td width="100" align="right">照片：</td>  
            <td><input type="file" name="studentPhoto"/>   </td>  
        </tr> 
        <tr>  
            <td width="100" align="right">照片：</td>  
            <td><input type="file" name="studentPhoto"/>   </td>  
        </tr> 
        
       <!--  <tr>  
            <td width="100" align="right">照片：</td>  
            <td><input type="file" name="studentPhoto"/>   </td>  
        </tr>  -->
        
        <input type="submit">
    </table>  
</form> 