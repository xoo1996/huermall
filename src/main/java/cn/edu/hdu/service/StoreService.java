package cn.edu.hdu.service;

import cn.edu.hdu.pojo.Store;

/**
 * Store Service
 * @author liangping
 *
 */
public interface StoreService {
	/**
	 * 通过店铺编号获得店铺对象
	 * @param gctId
	 * @return
	 */
	public Store findStoreByGctId(String gctId);
	
	public boolean addStore(Store store);

}
