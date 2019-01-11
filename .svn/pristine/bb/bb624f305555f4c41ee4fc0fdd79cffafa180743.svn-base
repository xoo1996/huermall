package cn.edu.hdu.Code;

import java.util.HashMap;
import java.util.Map;

public class VerifyCode {

	/**
	 * 邀请用户申请
	 */
	public static String INVITE_NEW ="0";
	/**
	 * 门店积分申请
	 */
	public static String STORE_APPLY_SCORE ="1";
	/**
	 * 审批状态：待审批
	 */
	public static String WAITING_VERIFY = "2";
	/**
	 * 审批状态：待发放积分
	 */
	public static String GIVE_SCORE = "3";
	/**
	 * 审批状态：通过
	 */
	public static String PASS = "4";
	/**
	 * 审批状态：不通过
	 */
	public static String REFUSE = "5";
	/**
	 * 特殊通道审批：未发放过积分
	 */
	public static String NOT_USED = "6";
	/**
	 * 特殊通道审批：等待积分发放
	 */
	public static String WAITING_SCIRE = "7";
	/**
	 * 特殊通道审批：已发放过积分
	 */
	public static String USED = "8";
	
	public static Map<String,String> strMap = new HashMap<String,String>();
	
	static{
		strMap.put(INVITE_NEW, "邀请用户申请");
		strMap.put(STORE_APPLY_SCORE, "门店积分申请");
		strMap.put(WAITING_VERIFY, "待审批");
		strMap.put(GIVE_SCORE, "待发放积分");
		strMap.put(PASS, "通过");
		strMap.put(REFUSE, "不通过");
		strMap.put(NOT_USED, "未申请过积分");
		strMap.put(WAITING_SCIRE, "等待总部审批");
		strMap.put(USED, "已发放过积分");
	}
	
	public static String getStr(String key){
		return strMap.get(key);
	}
}
