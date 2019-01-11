package cn.edu.hdu.dao;

import java.util.List;

import cn.edu.hdu.pojo.PriceConfig;

public interface PriceconfigDao {

	public void updatePrice(PriceConfig pc);
	
	public PriceConfig getPrice(String id);
	
	/**
	 * 根据购买消费的金额产生金粉
	 * @param price
	 * @return
	 */
	public List<PriceConfig> getSuitPCByPrice(Long price);
}
