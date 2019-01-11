package cn.edu.hdu.service;

import java.util.Date;
import java.util.List;

import com.zlzkj.core.sql.Row;

import cn.edu.hdu.pojo.ScoreEvent;

public interface ScoreService {
	/**
	 *  显示积分操作列表，信息经过封装之后返回给视图
	 * @param memberName 会员名称
	 * @param memberId   会员唯一主键
	 * @param memberPhone  会员手机号
	 * @param eventType   查询的事件类型
	 * @param row
	 * @param rowNumber
	 * @param acc             当前登陆账号，门店、总部
	 * @param list        事件类型集合，用于查询时用
	 * @return
	 */
    public Row getScoreList(String memberName,String memberId,String memberPhone,String eventType,String storeName,
    		String row,String rowNumber,String accId,List<String> list);
 
    /**
	 *  当门店需要发起特殊通道审批时，返回付款积分事件列表
	 * @param memberName 会员名称
	 * @param memberId   会员唯一主键
	 * @param memberPhone  会员手机号
	 * @param eventType   查询的事件类型
	 * @param row
	 * @param rowNumber
	 * @param acc  要查询数据的门店账号
	 * @return
	 */
    public Row getScoreApplyList(String memberName,String memberId,String memberPhone,String eventType
    		,String row,String rowNumber,String acc);
    /**
     * 记录积分兑换礼品的事件
     * @param id
     * @param name
     * @param score
     * @param number
     */
    public void record(Long id, String name, Long score, long number, String account);
    /**
     * 记录积分抵现金消耗积分的事件
     * @param memberNo
     * @param score
     * @param account 
     */
    public void logConsume(String memberNo, Long score, String account);
	/**
	 * 记录收费产生积分的事件
	 * @param memberNo
	 * @param score
	 * @param sco
	 * @param account 
	 * @param folno 
	 */
    public void logCharge(String memberNo, Long score,Long sco, String account, String folno);

    /**
     * 新用户新建记录收费产生积分
     * @param user
     * @param l
     * @param account 
     * @param folno 
     */
	public void logNewCharge(String user, Long l, String account, String folno);
	
	/**
	 * 特殊通道申请积分时间
	 * @param memberNo
	 * @param score
	 */
	public void logStoreApply(String memberNo, Long score,String account);

	/**
	 *	老用户注册积分事件记录
	 * @param memberNo
	 * @param score
	 */
	public void logOld(String memberNo, long score,String account);

	/**
	 * 记录惠耳币事件
	 * @param user
	 * @param coin
	 * @param account 
	 */
	public void logCoin(String user, Long coin, String account);

	/**
	 * 邀请新用户赠送惠尔币
	 * @param memberNo
	 * @param coin
	 * @param account
	 */
	public void logApplyCoin(String memberNo, Long coin, String account);
	
	/***
	 * 统计一段时间内单个门店送出的所有积分的数目
	 * @param startDate
	 * @param endDate
	 * @param storeName
	 * @return
	 */
	public int getStoreScoreCount(String startDate,String endDate,String storeName);
	
	/**
	 * 统计一段时间内门店送出的所有积分的数目
	 * @param startDate
	 * @param endDate
	 * @param storeName
	 * @return
	 */
	public Row getStoreScoreAccountList(String startDate, String endDate,
			String storeName,int rowNum,int page);
	/**
	 * 退机返回购买抵扣积分
	 * @param memberNo
	 * @param score
	 * @param coin
	 * @param account
	 */
	public void logRec(String memberNo, Long score, Long coin, String account);

	/**
	 * 退机扣除特殊通道赠送的待生效积分
	 * @param memberNo
	 * @param score
	 * @param account
	 */
	public void logRecSpe(String memberNo, Long score, String account);

	/**
	 * 退机扣除邀请人赠送的待生效惠耳币
	 * @param memberNo
	 * @param coin
	 * @param account
	 */
	public void logRecApp(String memberNo, Long coin, String account);
	/**
	 * 计算一段时间内注册的新会员数量
	 * @param startDate 开始时间点
	 * @param endDate   结束时间点
	 * @param storeAccount 所选门店账号
	 * @param rows
	 * @param page
	 * @return  返回一条记录
	 */
	public Row getNewMemberCount(Date startDate,Date endDate,String storeAccount,
			int rows,int page);
	
	/**
	 * 计算一段时间内所有门店的注册的新会员数量
	 * @param startDate 开始时间点
	 * @param endDate   结束时间点
	 * @param rows
	 * @param page
	 * @return 返回列表
	 */
	public Row getNewMemberCountAll(Date startDate,Date endDate,
			int rows,int page);
	
	/**
	 * 计算一段时间内指定门店的注册的新会员数量
	 * @param startDate 开始时间点
	 * @param endDate   结束时间点
	 * @param storeAccount 所选门店账号
	 * @param rows
	 * @param page
	 * @return 返回列表
	 */
	public Row getNewMemberCountDetail(Date startDate,Date endDate,String storeAccount,
			int rows,int page);

	/**
	 * 记录维修抵扣惠耳币事件
	 * @param memberNo
	 * @param coin
	 * @param account
	 */
	public void logrepair(String memberNo, Long coin, String account);
}
