package cn.edu.hdu.Code;

import java.util.HashMap;
import java.util.Map;

public class ReturnCode {

	private static Map<String,String> map = new HashMap<String,String>();
	
	/**
	 * 手机已绑定用户
	 */
	public static final String PHONE_HAVE_MEMBER ="00002";
	public static final String PHONE_HAVE_MEMBER_STR ="手机号已绑定用户";
	
	/**
	 * 身份证已绑定用户
	 */
	public static final String IDCARD_HAVE_MEMBER ="00001";
	public static final String IDCARD_HAVE_MEMBER_STR ="身份证已绑定用户";
	
	/**
	 * 添加成功：文字描述
	 */
	public static final String ADD_SUCCESS= "添加成功";
	/**
	 * 添加失败：文字描述
	 */
	public static final String ADD_FAILED= "添加失败";
	/**
	 * 删除成功：文字描述
	 */
	public static final String DELETE_SUCCESS= "删除成功";
	/**
	 * 删除失败：文字描述
	 */
	public static final String DELETE_FAILED= "删除失败";
	/**
	 * 修改成功：文字描述
	 */
	public static final String MODIFY_SUCCESS= "修改成功";
	/**
	 * 修改失败：文字描述
	 */
	public static final String MODIFY_FAILED= "修改失败";
	/**
	 * 发布成功
	 */
	public static final String RELEASE_SUCCESS= "发布成功";
	/**
	 * 发布失败
	 */
	public static final String RELEASE_FAILED= "发布失败";
	
	/**
	 * 超级管理员不能设置角色
	 */
	public static final String ADMIN_CANNOT_SET_ROLE= "超级管理员不能设置角色";
	/**
	 * 超级管理员不能设置角色
	 */
	public static final String ADMIN_ROLE_CANNOT_SET= "超级管理员权限不能被设置";
	/**
	 * 该角色有账户关联，请先删除账户
	 */
	public static final String ROLE_HAVE_MATCHED_ACCOUNT= "该角色有账户关联，请先删除账户";
	/**
	 * 成功：状态码1
	 */
	public static final int SUCCESS= 1;
	/**
	 * 成功：状态码1
	 */
	public static final String SUCCESS_STR= "1";
	/**
	 * 成功：信息
	 */
	public static final String SUCCESS_MSG= "成功";
	/**
	 * 失败：状态码2
	 */
	public static final int FAILED= 0;
	/**
	 * 失败：状态码2
	 */
	public static final String FAILED_STR= "0";
	/**
	 * 失败：信息
	 */
	public static final String FAILED_MSG= "失败";

	static{
		map.put(PHONE_HAVE_MEMBER, PHONE_HAVE_MEMBER_STR);
		map.put(IDCARD_HAVE_MEMBER, IDCARD_HAVE_MEMBER_STR);
		map.put(SUCCESS_STR, SUCCESS_MSG);
		map.put(FAILED_STR, FAILED_MSG);
	}
	
	public static Map<String,String> getMap(){
		return map;
	}
	
	public static String getString(String str){
		return getMap().get(str);
	}
}
