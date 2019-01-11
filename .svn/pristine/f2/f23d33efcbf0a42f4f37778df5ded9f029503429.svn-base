package cn.edu.hdu.dao;

import java.util.List;

import com.zlzkj.app.util.Param;

public interface MemberDao {

	public List<Object[]> getMemberList(Param params,List<Long> barIds);

	/**
	 * 更改会员密码
	 * @param memberno
	 * @param psd
	 * @return
	 */
	public int updatePassword(String memberNo,String psd);
	/**
	 * 积分兑换后更新用户积分
	 * @param id
	 * @param score
	 * @return 
	 */
	public int updateScore(Long id, long score);

	/**
	 * 更新电池数量
	 * @param memberNo
	 * @param l
	 */
	void updateBattnum(String memberNo, long l);
	/**
	 * 更新会员惠耳币
	 * @param id
	 * @param coin
	 * @return
	 */
	int updateCoin(Long id, long coin);

	/**
	 * 更新电池赠送年数
	 * @param memberNo
	 * @param gby
	 */
	void updateGby(String memberNo, String gby);

	/**
	 * 更新电池型号
	 * @param memberNo
	 * @param bm
	 */
	void updateBm(String memberNo, String bm);
	
	/**
	 * 修改手机号码
	 * @param oldPhone
	 * @param newPhone
	 * @return
	 */
	void changePhone(String oldPhone,String newPhone);
}
