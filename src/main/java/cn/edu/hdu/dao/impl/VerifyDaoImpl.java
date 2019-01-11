package cn.edu.hdu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zlzkj.app.util.DataUtil;

import cn.edu.hdu.dao.VerifyDao;
import cn.edu.hdu.pojo.Verify;

@Repository
public class VerifyDaoImpl extends GenericDao<Verify> implements VerifyDao {

	/**
	 * 更新审批的状态
	 * @param id
	 * @param status
	 */
	@Override
	public void updateStatus(Long id, String status) {
		String sql = "update verify set status = '" +status + "' where id = '" +id +"'";
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
	public List<Object[]> executeSqlData(String sql, String page, String rowNumber) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		DataUtil.getPages(query, page, rowNumber);
		return query.list();
	}

}
