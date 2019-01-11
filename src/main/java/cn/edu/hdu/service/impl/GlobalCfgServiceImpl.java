package cn.edu.hdu.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.hdu.dao.impl.GlobalCfgDaoImpl;
import cn.edu.hdu.pojo.GlobalCfg;
import cn.edu.hdu.pojo.PriceConfig;
import cn.edu.hdu.service.GlobalCfgService;

@Service(value="globalCfgService")
public class GlobalCfgServiceImpl extends GenericService<GlobalCfg> implements GlobalCfgService {

	private static final Logger logger = Logger.getLogger(GlobalCfgServiceImpl.class);
	
	public GlobalCfgDaoImpl getGlobalCfgDao() {
        return (GlobalCfgDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setGlobalCfgDao(GlobalCfgDaoImpl globalCfgDao) {
	    this.setGenericDao(globalCfgDao);
	}

	@Override
	public boolean setInitPwd(String password) {
		try {
			List<GlobalCfg> cfgList = this.getGlobalCfgDao().findAll();
			if(cfgList.size() == 0){
				GlobalCfg cfg = new GlobalCfg();
				cfg.setId(0L);
				cfg.setInitPwd(password);
				this.getGlobalCfgDao().save(cfg);
			}else{
				GlobalCfg cfg = cfgList.get(0);
				cfg.setInitPwd(password);
				this.getGlobalCfgDao().save(cfg);
			}
			return true;
		} catch (Exception e) {
			logger.error("save failed", e);
			return false;
		}
		
	}
	
	@Override
	public String getInitPwd() {
		List<GlobalCfg> cfgList = this.getGlobalCfgDao().findAll();
		if(cfgList.size() > 0){
			return cfgList.get(0).getInitPwd();
		}
		return "";
	}

	@Override
	public boolean setRollAdvertisement(String ad) {
		try {
			List<GlobalCfg> cfgList = this.getGlobalCfgDao().findAll();
			if(cfgList.size() == 0){
				GlobalCfg cfg = new GlobalCfg();
				cfg.setId(0L);
				cfg.setRollAd(ad);
				this.getGlobalCfgDao().save(cfg);
			}else{
				GlobalCfg cfg = cfgList.get(0);
				cfg.setRollAd(ad);
				this.getGlobalCfgDao().save(cfg);
			}
			return true;
		} catch (Exception e) {
			logger.error("save failed", e);
			return false;
		}
	}

	@Override
	public boolean setScoreChangeMoney(String scoreMoney) {
		try {
			List<GlobalCfg> cfgList = this.getGlobalCfgDao().findAll();
			if(cfgList.size() == 0){
				GlobalCfg cfg = new GlobalCfg();
				cfg.setId(0L);
				cfg.setScoremoney(scoreMoney);
				this.getGlobalCfgDao().save(cfg);
			}else{
				GlobalCfg cfg = cfgList.get(0);
				cfg.setScoremoney(scoreMoney);
				this.getGlobalCfgDao().save(cfg);
			}
			return true;
		} catch (Exception e) {
			logger.error("save failed", e);
			return false;
		}
	}

	@Override
	public boolean setApplyUserScore(String applyScore) {
		try {
			List<GlobalCfg> cfgList = this.getGlobalCfgDao().findAll();
			if(cfgList.size() == 0){
				GlobalCfg cfg = new GlobalCfg();
				cfg.setId(0L);
				cfg.setApplyscore(applyScore);
				this.getGlobalCfgDao().save(cfg);
			}else{
				GlobalCfg cfg = cfgList.get(0);
				cfg.setApplyscore(applyScore);
				this.getGlobalCfgDao().save(cfg);
			}
			return true;
		} catch (Exception e) {
			logger.error("save failed", e);
			return false;
		}
	}

	@Override
	public boolean updateCfg(GlobalCfg cfg) {
		try {
			List<GlobalCfg> cfgList = this.getGlobalCfgDao().findAll();
			GlobalCfg cf=cfgList.get(0);
			cf.setChangeMoney(cfg.getChangeMoney());
			cf.setChangeScore(cfg.getChangeScore());
			this.save(cf);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean updateRate(GlobalCfg cfg) {
		try {
			List<GlobalCfg> cfgList = this.getGlobalCfgDao().findAll();
			GlobalCfg cf=cfgList.get(0);
			cf.setMostRate(cfg.getMostRate());
			this.save(cf);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateLogin(GlobalCfg cfg) {
		try {
			List<GlobalCfg> cfgList = this.getGlobalCfgDao().findAll();
			GlobalCfg cf=cfgList.get(0);
			cf.setOldLogin(cfg.getOldLogin());
			this.save(cf);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean setHuierMonth(Date actisdt, Date actiedt) {
		try {
			List<GlobalCfg> cfgList = this.getGlobalCfgDao().findAll();
			if(cfgList.size() == 0){
				GlobalCfg cfg = new GlobalCfg();
				cfg.setId(0L);
				cfg.setActisdt(actisdt);
				cfg.setActiedt(actiedt);
				this.getGlobalCfgDao().save(cfg);
			}else{
				GlobalCfg cfg = cfgList.get(0);
				cfg.setActisdt(actisdt);
				cfg.setActiedt(actiedt);
				this.getGlobalCfgDao().save(cfg);
			}
			return true;
		} catch (Exception e) {
			logger.error("save failed", e);
			return false;
		}
	}
}
