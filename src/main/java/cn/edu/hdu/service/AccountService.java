package cn.edu.hdu.service;

import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.Store;

import com.zlzkj.core.sql.Row;


public interface AccountService {

	
	/**
	 * 验证账号密码是否匹配，只有得到一个账号时返回true，空或者大鱼1个时返回false
	 * @param account
	 * @param password
	 * @return
	 */
	public Account validate(String account,String password);
	
	/**
	 * 查询适合条件的账户
	 * @param name 
	 * @param accountNO
	 * @return 实例Row:{total,rows}
	 */
	public Row getAccountList(String name,String accountNo,int rowNum,int page);
	
	/**
	 * 新建用户
	 * @param account
	 * @return
	 */
	public boolean addAccount(Account account);
    /**
	 *  当前账号修改自己的密码
	 * @param loginName  登录名
	 * @return
	 */
	public int resetAccountPwd(Long id,String password);
	
	/**
	 * 修改会员密码
	 * @param memberNo
	 * @param password
	 * @return 修改的记录条数
	 */
	public int resetMemberPwd(String memberNo,String password);
	
	/**
	 * 设置账户角色
	 * @param account
	 * @return
	 */
	public boolean setRole(Account account);
	/**
	 *模糊输入
	 * @param text 输入的文本
	 * @return
	 */
	public String getAjaxStore();
	
	/**
	 * 通过id查找店铺
	 * @param id
	 * @return
	 */
	public Account findStoreById(Long id);
}
