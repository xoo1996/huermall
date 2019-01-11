package cn.edu.hdu.service;

import java.util.List;

import com.zlzkj.core.sql.Row;

import cn.edu.hdu.pojo.MemBat;
import cn.edu.hdu.pojo.SuperChangeHistory;


//新增
public interface MemBatService {
	
	/**
	 * 通过用户编号获取剩余电池数量
	 * @param memberNO
	 * @return
	 */
	public long getBatteryReQty(String memberNO,String batteryType);
	
	/**
	 * 通过用户编号获取电池数量
	 * @param memberNO
	 * @return
	 */
	public long getBatteryQty(String memberNO,String batteryType);
	
	/**
	 * 添加赠送电池信息
	 * @param memBat
	 */
	public void addMenBat(MemBat memBat);
	
	/**
	 * 删除赠送电池信息
	 * @param memBat
	 */
	public void delMenBat(Long id);
	
	/**
	 * 根据会员名，会员编号，会员手机号查询会员
	 * @param memberName
	 * @param memberId
	 * @param memberPhone
	 * @param rownum
	 * @param page
	 * @return
	 */
	public Row getMemBatList(String memberId,int rownum,int page,String account); 
	
	/**
	 * 根据会员名，会员编号，会员手机号查询会员，不查询电池库存
	 * @param memberName
	 * @param memberId
	 * @param memberPhone
	 * @param rownum
	 * @param page
	 * @return
	 */
	public Row superGetBatEventListNotGetBatStock(String memberName, String memberNo, String memberPhone, String storeName,
			String page, String rowNumbe); 

	/**
	 * 通过id领取电池
	 * @param id
	 */
	public void getBat(Long id,long getBatNum);

	public MemBat findMemBatById(Long id);
	
	public MemBat getMemBatByOrderId(String orderId);
	
	public MemBat getMemBatByChargeId(String chargeId);
	
	/**
	 * 通过id获得memberId
	 * @param id
	 * @return
	 */
	public String getMemberIdById(String id);
	
	/**
	 * 更新membat
	 * @param membat
	 * @param getbatnum 领取的电池数量
	 */
	public void updateMemBatNUM(MemBat membat);
	public void updateMemBatType(MemBat membat);
	
	/**
	 * 更新membat状态
	 * @param op 定位字段
	 * @param value 值
	 * @param status 更新状态
	 */
	public void updateMembatStatus(String op,String value,String status);
	
	/**
	 * 给定一个membat的list，根据传入的字段，相应值，修改相应的状态
	 * @param list
	 * @param op
	 * @param status
	 */
	public void updateMembatStatus(List<Object[]> list,String op,String status);
	
}
