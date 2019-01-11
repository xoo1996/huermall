package cn.edu.hdu.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.hdu.dao.VerifyBatDao;
import cn.edu.hdu.pojo.VerifyBat;

@Repository
public class VerifyBatDaoImpl extends GenericDao<VerifyBat> implements VerifyBatDao {
	@Override
	public int updateVerifyBatStatus(String op,String value,String status){
		String sql = "update verify_bat set status = '" +status + "' where "+op+"='"+value+"'";
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();	
	}
	
	@Override
	public List<Object[]> getVerifyBatList(String op, String value) {
		String sql = "select * from verify_bat where "+op+" = '"+value+"' ";
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
	}
	
	@Override
	public int updateVerifyBatVerifyIdAndDate(String op, String value, String verifyId, String date) {
		String sql = "update verify_bat set verify_id = '" +verifyId + "' , verify_date =to_date('"+date+"','yyyy-mm-dd hh24:mi:ss') where "+op+"='"+value+"'";
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();	
		
	}

}
