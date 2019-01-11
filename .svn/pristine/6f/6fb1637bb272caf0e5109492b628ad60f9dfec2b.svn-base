package com.zlzkj.app.util;

import java.util.HashMap;

@SuppressWarnings("serial")
public class Param extends HashMap<String,String>{

	/**
	 * 判断是不是所有参数都是null或者空串。是：true;否：false。
	 * @param args
	 * @return
	 */
	public static boolean isAllNullOrEmpty(String... args){
		for(String arg:args){//发现有一个为非空并且不是空串
			if(arg != null && !arg.trim().equals("")){
				return false;
			}
		}
		return true;
	}
}
