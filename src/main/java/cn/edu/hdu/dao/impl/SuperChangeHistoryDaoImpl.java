package cn.edu.hdu.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.edu.hdu.dao.SuperChangeHistoryDao;
import cn.edu.hdu.pojo.SuperChangeHistory;

@Repository
public class SuperChangeHistoryDaoImpl extends GenericDao<SuperChangeHistory> implements SuperChangeHistoryDao {
	
	public void addSuperChangeHistory(SuperChangeHistory superChangeHistory) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.save(superChangeHistory);
		session.flush();
	}
}
