package cn.edu.hdu.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.edu.hdu.dao.PriceconfigDao;
import cn.edu.hdu.pojo.PriceConfig;
@Repository
public class PriceconfigDaoImpl extends GenericDao<PriceConfig> implements
		PriceconfigDao {

	public void updatePrice(PriceConfig pc) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		PriceConfig userBefore = (PriceConfig)session.load(PriceConfig.class, pc.getId());
		userBefore.setStartPrice(pc.getStartPrice());
		userBefore.setEndPrice(pc.getEndPrice());
		userBefore.setChangeScore(pc.getChangeScore());
		session.update(userBefore);
	}

	public PriceConfig getPrice(String id) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();	
		PriceConfig user = (PriceConfig)session.get(PriceConfig.class,Long.parseLong(id));
		
		return user;
	}

	@Override
	public List<PriceConfig> getSuitPCByPrice(Long price) {
		String hql = "from PriceConfig pc where pc.startPrice < :price and pc.endPrice >= :price";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setParameter("price", price).list();
	}

}
