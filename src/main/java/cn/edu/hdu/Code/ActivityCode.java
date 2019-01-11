package cn.edu.hdu.Code;

import java.util.HashMap;
import java.util.Map;

public class ActivityCode {

	public static String NOT_RELEASED ="0";
	
	public static String RELEASED ="1";
	
	public static Map<String,String> strMap = new HashMap<String,String>();
	
	static{
		strMap.put(NOT_RELEASED, "未发布");
		strMap.put(RELEASED, "已发布");
	}
}
