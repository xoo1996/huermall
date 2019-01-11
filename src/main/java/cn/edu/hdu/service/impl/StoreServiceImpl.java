package cn.edu.hdu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.dao.impl.AccountDaoImpl;
import cn.edu.hdu.dao.impl.StoreDaoImpl;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.GlobalCfg;
import cn.edu.hdu.pojo.Role;
import cn.edu.hdu.pojo.Store;
import cn.edu.hdu.service.StoreService;

@Service("storeService")
public class StoreServiceImpl extends GenericService<Store> implements StoreService {
	
	@Resource
	private BatteryServiceImpl batteryService;
	
	@Resource
	private GlobalCfgServiceImpl globalCfgService;
	
	public StoreDaoImpl getStoreDao() {
        return (StoreDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setStoreDao(StoreDaoImpl storeDao) {
	    this.setGenericDao(storeDao);
	}

	@Override
	public Store findStoreByGctId(String gctId) {
		List<Store> store =this.findByProperty("gctId", gctId); 
		return store.get(0);  
	}

	@Override
	public boolean addStore(Store store) {
		try {
			List<Store> storeInDB = (List<Store>)this.findByProperty("gctId", store.getGctId());
			
			if(storeInDB.size() == 0){
				this.getStoreDao().addStore(store);
			}else{
				return false;
			}
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
