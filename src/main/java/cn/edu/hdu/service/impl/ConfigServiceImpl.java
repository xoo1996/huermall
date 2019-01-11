package cn.edu.hdu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.hdu.dao.impl.ConfigDaoImpl;
import cn.edu.hdu.dao.impl.MemBatDaoImpl;
import cn.edu.hdu.dao.impl.SuperChangeHistoryDaoImpl;
import cn.edu.hdu.pojo.BatEvent;
import cn.edu.hdu.pojo.Config;
import cn.edu.hdu.pojo.MemBat;
import cn.edu.hdu.service.ConfigService;

@Service("configService")
public class ConfigServiceImpl extends GenericService<Config> implements ConfigService{
	
	public ConfigDaoImpl getConfigDao() {
        return (ConfigDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setConfigDao(ConfigDaoImpl configDao) {
	    this.setGenericDao(configDao);
	}



	@Override
	public void changeMembatVerifySwitch(String status) {
		getConfigDao().updateMembatVerifySwitchStatus(status);	
	}

	@Override
	public String getMembatVerifySwitchStatus() {
		Long longid = 1l;
		List<Config> configs=this.findByProperty("id", longid);	
		
		return configs.get(0).getMembatVerifySwitch();
		
	}

}
