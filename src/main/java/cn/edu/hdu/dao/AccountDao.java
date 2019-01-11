package cn.edu.hdu.dao;

import cn.edu.hdu.pojo.Account;

public interface AccountDao {

	
	/**
	 * 修改账户密码
	 * @param loginName  账户名
	 * @param password   新密码
	 * @return 修改记录数
	 */
	public int resetAccountPwd(Long id,String password);
	
	/**
	 * 修改客户密码
	 * @param memberNo
	 * @param password
	 * @return 修改记录数
	 */
	public int resetMemberPwd(String memberNo,String password);
	
	public void addAccount(Account account);
	
}
