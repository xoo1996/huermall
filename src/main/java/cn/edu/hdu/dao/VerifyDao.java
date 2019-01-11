package cn.edu.hdu.dao;

public interface VerifyDao {
	/**
	 * 更新审批的状态
	 * @param id
	 * @param status
	 */
	public void updateStatus(Long id, String status);
}
