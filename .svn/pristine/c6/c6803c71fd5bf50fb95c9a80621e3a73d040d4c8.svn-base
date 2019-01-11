package cn.edu.hdu.service;

import java.util.Date;

import cn.edu.hdu.pojo.GlobalCfg;


public interface GlobalCfgService {
	/**
	 * 设置新建会员时的初始密码
	 * @param password
	 * @return
	 */
	public boolean setInitPwd(String password);
	
	/**
	 * 获取全局初始密码
	 * @return
	 */
	public String getInitPwd();
	/**
	 * 设置滚动广告内容
	 * @param ad
	 * @return
	 */
	public boolean setRollAdvertisement(String ad);
	/**
	 * 设置积分抵扣金额比例
	 * @param ad
	 * @return
	 */
	public boolean setScoreChangeMoney(String scoreMoney);
	/**
	 * 设置用户邀请赠送积分
	 * @param ad
	 * @return
	 */
	public boolean setApplyUserScore(String applyScore);
	/**
	 * 更新代金券
	 * @param cfg
	 * @return
	 */
	boolean updateCfg(GlobalCfg cfg);
	/**
	 * 更新积分抵扣比例
	 * @param cfg
	 * @return
	 */
	boolean updateRate(GlobalCfg cfg);
	/**
	 * 设置老用户注册赠送积分
	 * @param cfg
	 * @return
	 */
	boolean updateLogin(GlobalCfg cfg);

	/**
	 * 设置惠耳月时间
	 * @param actisdt
	 * @param actiedt
	 * @return
	 */
	boolean setHuierMonth(Date actisdt, Date actiedt);
}
