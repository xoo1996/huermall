package com.zlzkj.app.util;

public class LogStringBuilder {

	public String append(String value){
		StringBuffer sb = new StringBuffer();
		sb.append(value + ",");
		return sb.toString();
	}
	
	public String append(String key,String value){
		StringBuffer sb = new StringBuffer();
		sb.append(key + "=" + value + ",");
		return sb.toString();
	}
	
	public String append(String key,Object value){
		StringBuffer sb = new StringBuffer();
		sb.append(key + "=" + value + ",");
		return sb.toString();
	}
	
	public String method(){
		StringBuffer sb = new StringBuffer();
		
		 StackTraceElement[] stacks = Thread.currentThread().getStackTrace();   
		 /*  location = "类名："+stacks[2].getClassName() + "\n函数名：" + stacks[2].getMethodName()  
	        + "\n文件名：" + stacks[2].getFileName() + "\n行号："  
	        + stacks[2].getLineNumber() + "";   */
		
		sb.append("method" + "=" + stacks[2].getMethodName() + ",");
		return sb.toString();
	}
}
