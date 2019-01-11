package cn.edu.hdu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zlzkj.app.util.DataUtil;

import cn.edu.hdu.dao.ScoreDao;
import cn.edu.hdu.pojo.ScoreEvent;

@Repository
public class ScoreDaoImpl extends GenericDao<ScoreEvent> implements ScoreDao {

	@Override
	public List<Object[]> executeSqlList(String sql,Object...objects) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		if(objects != null){
			for(int i = 1;i <= objects.length;i++){
				query.setParameter(i, objects[i]);
			}
		}
		
		return session.createSQLQuery(sql).list();
	}

	@Override
	public String executeSqlCount(String sql) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		return query.uniqueResult().toString();
	}

	@Override
	public List<Object[]> executeSqlData(String sql, String page, String rowNumber) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		DataUtil.getPages(query, page, rowNumber);
		return query.list();
	}

	
}
