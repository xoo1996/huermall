package com.zlzkj.app.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;


public class DataUtil {
	
	/**
	 * 获取一个类的所有属性名称
	 * @param o
	 * @return  String数组
	 */
	public static String[] getClassFiles(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}
	
	/**
	 * 将属性名称和属性值组装成键值对，用于前端分页显示
	 * @param files
	 * @param values
	 * @return
	 */
	public static Map<String ,Object> pacage(String[] files,Object[] values){
		HashMap<String, Object> row = new HashMap<String,Object>();
		if(files.length == values.length){
			for(int i = 0; i<files.length;i++){
				row.put(files[i], values[i]);
			}
			return row;
		}else{
			 row.put("error", "true");
			 return row;
		}
	}
	
	
	/**
	 * 根据对象中的某一属性的名称获取该属性对应的值
	 * @param fieldName
	 * @param o
	 * @return
	 */
	public Object getFieldValueByName(String fieldName, Object o) {  
	       try {    
	           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	           String getter = "get" + firstLetter + fieldName.substring(1);    
	           Method method = o.getClass().getMethod(getter, new Class[] {});    
	           Object value = method.invoke(o, new Object[] {});    
	           return value;    
	       } catch (Exception e) {    
	              
	           return null;    
	       }    
	   }
	
	/**
	 * 为hibernate的query设置分页属性，由于根据原始参数设置分页属性还是有一点点
	 * 麻烦，故将该方法抽象出来
	 * @param query
	 * @param page
	 * @param rows
	 */
	public static void getPages(Query query,String page,String rows){
		if(page!=null && rows!=null)
		{
			query.setFirstResult((Integer.parseInt(page)-1)*Integer.parseInt(rows)); 
			query.setMaxResults(Integer.parseInt(rows)); 
		}
	}
	
	/**
	 * 获取request中的参数
	 * 由于request中的参数类型是Map<String,String[]>
	 * 而我们需要的类型是Map<String,String>
	 * 所以需要改造下
	 * @param request
	 * @return
	 */
	public static Param getParameters( HttpServletRequest request){
		Param params = new Param();
		Map<String,String[]> map = request.getParameterMap();
		Set<String> keys = map.keySet();
		for( Iterator<String>   it = keys.iterator(); it.hasNext(); ){
			String key = it.next().toString();
			params.put(key, request.getParameter(key));
		}
		return params;
	}
	
	public static int getFirstResult(int page,int rows){
		return rows*(page - 1);
	}
}
