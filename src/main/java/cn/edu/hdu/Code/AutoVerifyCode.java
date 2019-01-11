package cn.edu.hdu.Code;

import java.util.HashMap;
import java.util.Map;

public class AutoVerifyCode {
	/**
	 * 允许自动审批
	 */
	public static Byte yes =1;
	/**
	 * 不允许自动审批
	 */
	public static Byte no =0;
	
	public static Map<Byte,String> strMap = new HashMap<Byte,String>();
	
	static{
		strMap.put(yes, "是");
		strMap.put(no, "否");
	}
}
