package cn.edu.hdu.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.edu.hdu.dao.StoreDao;
import cn.edu.hdu.pojo.Store;

@Repository
public class StoreDaoImpl extends GenericDao<Store> implements StoreDao {

	@Override
	public void addStore(Store store) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.save(store);
		session.flush();		
	}
}
