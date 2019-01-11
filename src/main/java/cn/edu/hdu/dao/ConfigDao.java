package cn.edu.hdu.dao;

public interface ConfigDao {
	
	/**
	 * 更新自动审批membat的开关
	 * @param status
	 */
	public int updateMembatVerifySwitchStatus(String status);
	
}
