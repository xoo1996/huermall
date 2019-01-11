package cn.edu.hdu.Code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalStatic {

	/**
	 * 大于等于
	 */
	public static String EQUAL_GREATER = "1";
	/**
	 * 小于等于
	 */
	public static String EQUAL_SMALLER = "2";
	/**
	 * 等于
	 */
	public static String EQUAL = "3";
	
	private static Map<String,String> map = new HashMap<String,String>();
	
	private static List<String> set = new ArrayList<String>();
	
	static{
		set.add(EQUAL_GREATER);
		set.add(EQUAL_SMALLER);
		set.add(EQUAL);
		
		map.put(EQUAL_GREATER, ">=");
		map.put(EQUAL_SMALLER, "<=");
		map.put(EQUAL, "=");
	}
	
	public static Map<String,String> getMap(){
		return map;
	}
	
	public static List<String> getSet(){
		return set;
	}
}
