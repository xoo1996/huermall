package cn.edu.hdu.service;

import com.zlzkj.core.sql.Row;

import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.pojo.Verify;

public interface VerifyService {

	/**
	 * 获取审核列表
	 * @param type
	 * @param rowNumber 
	 * @param page 
	 * @return
	 */
	Object getVerifyList(String id,String memNo,String status,String sponsor,
			String type, int page, int rowNumber);
	
	/**
	 * 门店积分申请
	 * @param type
	 * @param page
	 * @param rowNum
	 * @return
	 */
	public Object getStoreVerifyList(String verId,String storeName,String memNo,String memName,String status,String type,int page,int rowNum);

	/**
	 * 新建 邀请用户积分审批
	 * @param verify
	 * @return
	 */
	boolean addApplyuser(Verify verify);

	/**
	 * 审批
	 * @param id
	 * @param b
	 * @param account 
	 * @param coin 
	 * @return
	 * @throws Exception 
	 */
	public boolean ApaApply(String remark,Long id, boolean yn, String account, Long coin,
			String applyid) throws Exception;
	
	/**
	 * 得到登陆账户自己发起的审批申请
	 * @param page
	 * @param rows
	 * @return
	 */
	public Row getMyVerifyList(String verId,String verifyer,String status,String account,int page,int rows);

	
	public void storeScoreVerify(String editor,Long id,String resultStr,Long finalScore) throws Exception;

	
	
	/*public void storeScoreVerify(Member member, String editor, Long id, String folno)
			throws Exception;
	*/
}
