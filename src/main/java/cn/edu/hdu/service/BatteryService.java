package cn.edu.hdu.service;

import java.util.List;

import cn.edu.hdu.pojo.Battery;

import com.zlzkj.core.sql.Row;

public interface BatteryService {

	/**
	 * 电池列表
	 * @param name
	 * @param type
	 * @param rowNum
	 * @param page
	 * @return
	 */
	Row getBatteryList(String name, String type, int rowNum, int page);

	/**
	 * 新建电池
	 * @param battery
	 * @return
	 */
	public boolean addBattery(Battery battery);

	/**
	 * 删除电池
	 * @param battery
	 * @return
	 */
	boolean deleteBattery(Battery battery);

	/**
	 * 设置库存
	 * @param id
	 * @param storeNum
	 * @return
	 */
	boolean resetStoreNum(Long id, Long storeNum);

	/**
	 * 设置积分
	 * @param id
	 * @param score
	 * @return
	 */
	boolean resetScore(Long id, Long score);

	/**
	 * 获得电池下拉框
	 * @return
	 */
	List<Row> getBattOptionList();

	/**
	 * 判断相应电池兑换数量是否大于存库数量，返回电池积分值
	 * @param number
	 * @param type
	 * @return
	 */
	Long NumScore(Long number, String type);

	/**
	 * 更新电池库存
	 * @param number
	 * @param type
	 * @param id
	 */
	void updateStore(long number, String type, Long id, String account);

	public Battery getBatteryByType(String type);
	
	public Battery getBatteryByName(String name);

}
