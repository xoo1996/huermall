package cn.edu.hdu.service;

public interface WaitscoreService {

	/**
	 * 产生待生效积分记录
	 * @param user
	 * @param l
	 * @param folno
	 * @param coin 
	 * @param score 
	 * @param pdtid 
	 * @param effect 
	 * @param balance 
	 */
	public boolean addWaitscore(String user, Long l, String folno, Long score, Long coin, String pdtid, String effect, String balance);

	/**
	 * 订单退机审核通过
	 * @param folno
	 */
	public void delWaitscore(String folno);

	/**
	 * 产生待生效惠耳币
	 * @param user
	 * @param l
	 * @param folno
	 * @param score
	 * @param coin
	 * @param pdtid
	 * @param applyid
	 * @return
	 */
	public boolean addWaitcoin(String user, Long l, String folno, Long score,
			Long coin, String pdtid, String applyid);

	/**
	 * 根据退机者客户编号判断是否是 被邀请人，如果是，删除邀请人待生效惠耳币
	 * @param applyid
	 */
	public void delApplycoin(String applyid);

	/**
	 * 耳背机退机
	 * @param folno
	 * @param rnt
	 * @param mon
	 * @param pdtid 
	 */
	public void delnor(String folno, int rnt, String mon, String pdtid);


}
