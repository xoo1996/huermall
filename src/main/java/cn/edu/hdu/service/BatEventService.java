package cn.edu.hdu.service;

import java.util.Date;
import java.util.List;

import com.zlzkj.core.sql.Row;

import cn.edu.hdu.pojo.BatEvent;
//新增
public interface BatEventService {
	/**
	 *  显示换电池事件
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
    public Row getBatList(String memberName,String memberId,String memberPhone,String eventType,String storeName,
    		String row,String rowNumber,String accId,List<String> list);
    
    /**
     * 记录领取电池事件
     * @param memberNO
     * @param inStoreNo
     * @param getStoreNo
     * @param batteryType
     * @param getBatNum
     * @param memBatId
     */
    public void record(String memberNO, String inStoreNo, String getStoreNo, String batteryType, long getBatNum,String memBatId);
    
    
    
	public void sendToOldSystem(String memberId, String inStoreNo, String getStoreNo, String batteryType, long getBatNum,String memBatId,String thisBatEventId);
    /**
     * 获取电池事件列表
     * @param memberNO
     * @param inStoreNo
     * @param batteryType
     * @param handleDate
     * @param page
     * @param rowNumber
     * @return
     */
    public Row getBatEventList(String memberNO,String inStoreNo,String batteryType,String page,String rowNumber);
    
    
    public String getBatEventCurrentId(String memberNO,String inStoreNo,String getStoreNo,String batteryType, long getBatNum,String memBatId);

    /**
     * 
     * @param memberName
     * @param memberId
     * @param memberPhone
     * @param storeName
     * @param page
     * @param rowNumber
     * @param account
     * @return
     */
    public Row getBatEventList(String memberId,String memberName, String memberNo,
			String memberPhone,String storeName,
			String page,String rowNumber,String account);
    
    /**
     * 通过id查找会员编号
     * @param id
     * @return
     */
    public String findMemberIdById(long id);
    
    /**
     * getBatEventList的超级修改版本
     * @param memberId
     * @param memberName
     * @param memberNo
     * @param memberPhone
     * @param storeName
     * @param page
     * @param rowNumber
     * @param account
     * @return
     */
	Row superGetBatEventList(String memberId, String memberName, String memberNo, String memberPhone, String storeName,
			String page, String rowNumber, String account);
	
	/**
	 * 更新batevevnt
	 * @param batEvent
	 */
	public void updataBatEventNUM(BatEvent batEvent);
	public void updataAllBatEventByMemBatId(String membatId,String batteryType);
	public List<BatEvent> getBatEventList(String membatId);
	
	public Row getJoinStoreGetBatHistoryList(String storeName, String storeNo,String startTime,String endTime, int page, int rowNumber);
	
	public List<Object[]> getJoinStoreGetBatHistoryList(String storeName, String storeNo,String startTime,String endTime);

}
