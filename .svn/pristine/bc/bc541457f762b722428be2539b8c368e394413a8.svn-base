package cn.edu.hdu.service;

import com.zlzkj.core.sql.Row;

import cn.edu.hdu.pojo.PriceConfig;

public interface PriceconfigService {

	/**
     * 新建价格区间配置
     * @param priceconfig
     * @return
     */
	public boolean addPrice(PriceConfig priceconfig);

	/**
	 * 获得积分事件列表
	 * @param page
	 * @param rowNumber
	 * @return
	 */
	public Row getScoreList(int page, int rowNumber);

	/**
	 * 更新价格区间配置
	 * @param pc
	 */
	public void updatePrice(PriceConfig pc);

	/**
	 * 获得价格区间配置列表
	 * @param id
	 * @return
	 */
	public PriceConfig getPrice(String id);
	
	/**
	 * 根据购买消费的金额产生金粉
	 * @param price
	 * @return
	 */
	public Long getSuitPCByPrice(Long price);
}

