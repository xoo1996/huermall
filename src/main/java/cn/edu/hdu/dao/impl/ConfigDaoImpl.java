package cn.edu.hdu.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.hdu.dao.ConfigDao;
import cn.edu.hdu.pojo.Config;

@Repository
public class ConfigDaoImpl extends GenericDao<Config> implements ConfigDao {

	@Override
	public int updateMembatVerifySwitchStatus(String status) {
		String sql = "update config set MEMBAT_VERIFY_SWITCH = '"+status +"' where id = '1' ";
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();		
	}



}
