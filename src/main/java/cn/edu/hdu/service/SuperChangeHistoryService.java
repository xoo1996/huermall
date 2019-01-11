package cn.edu.hdu.service;

import java.util.List;

import com.zlzkj.core.sql.Row;

import cn.edu.hdu.pojo.MemBat;
import cn.edu.hdu.pojo.SuperChangeHistory;

public interface SuperChangeHistoryService {
	
	/**
	 * 记录
	 * @param storeId
	 * @param memberId
	 * @param memBatId
	 * @param batEventId
	 * @param operation
	 * @param oldBatType
	 * @param newBatType
	 * @param oldQty
	 * @param newQty
	 * @param oldReQty
	 * @param newReQty
	 * @param oldGetNum
	 * @param newGetNum
	 */
	public void record(String storeId, String memberId, String memBatId, String batEventId, String operation,String oldBatType,String newBatType,String oldQty,String newQty,String oldReQty,String newReQty,String oldGetNum,String newGetNum);
	
	/**
	 * 显示
	 * @param storeName
	 * @param memberName
	 * @param page
	 * @param rowNumber
	 * @return
	 */
	public Row getSuperChangeHistoryList(String storeName, String memberName,String phoneNum,String startTime,String endTime,int page,int rowNumber);
	
	/**
	 * 根据条件获取历史修改记录列表
	 * @param storeName
	 * @param memberName
	 * @param phoneNum
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<Object[]> getSuperChangeHistoryList(String storeName, String memberName,String phoneNum,String startTime,String endTime);
	
	/**
	 * 拼接操作
	 * @param oldBatName
	 * @param newBatName
	 * @param oldQty
	 * @param newQty
	 * @param oldReQty
	 * @param newReQty
	 * @param oldGetNum
	 * @param newGetNum
	 * @return
	 */
	public String conCatOperation(String oldBatName,String newBatName,String oldQty,String newQty,String oldReQty,String newReQty,String oldGetNum,String newGetNum);

	/**
	 * 记录删除记录
	 * @param superChangeHistory
	 */
	public void recordDelMembat(SuperChangeHistory superChangeHistory);
}
