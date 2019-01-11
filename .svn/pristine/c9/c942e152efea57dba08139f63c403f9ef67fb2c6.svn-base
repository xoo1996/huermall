package cn.edu.hdu.dao.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;

import com.zlzkj.app.util.Param;

import cn.edu.hdu.dao.MemberDao;
import cn.edu.hdu.pojo.Member;

@Repository
public class MemberDaoImpl extends GenericDao<Member> implements MemberDao {

	@Override
	public List<Object[]> getMemberList(Param params, List<Long> barIds) {
		return null;
	}

	@Override
	public int updatePassword(String memberNo,String psd){
		String sql = "update member set password = '" +DigestUtils.md5Hex(psd) + "' where member_no = '" +memberNo +"'";
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public int updateScore(Long id, long score) {
		String sql = "update member set score = '" +score + "' where id = '" +id +"'";
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public int updateCoin(Long id, long coin) {
		String sql = "update member set coin = '" +coin + "' where id = '" +id +"'";
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
	@Override
	public void updateBattnum(String memberNo, long number) {
		String sql = "update member set batterynum = '" +number + "' where member_no = '" +memberNo +"'";
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public void updateGby(String memberNo, String gby) {
		String sql = "update member set gby = '" +gby + "' where member_no = '" +memberNo +"'";
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
	@Override
	public void updateBm(String memberNo, String bm) {
		String sql = "update member set bm = '" +bm + "' where member_no = '" +memberNo +"'";
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
	@Override
	public void changePhone(String oldPhone,String newPhone){
		String sql = "update member set phone = '" +newPhone + "' where phone = '" +oldPhone +"'";
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
}
