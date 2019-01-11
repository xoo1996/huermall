package cn.edu.hdu.dao.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.edu.hdu.dao.AccountDao;
import cn.edu.hdu.pojo.Account;

@Repository
public class AccountDaoImpl extends GenericDao<Account> implements AccountDao {
	
	@Override
	public int resetAccountPwd(Long id,String password){
		String hql = "update Account u set u.password = ? where u.id = ?";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		int i = session.createQuery(hql).setParameter(0, DigestUtils.md5Hex(password)).setParameter(1, id).executeUpdate();
		return i;
	}

	@Override
	public int resetMemberPwd(String memberNo, String password) {
		String hql = "update Member m set m.password = ? where m.memberNo = ?";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		int i = session.createQuery(hql).setParameter(0, DigestUtils.md5Hex(password)).setParameter(1, memberNo).executeUpdate();
		return i;
	}

	@Override
	public void addAccount(Account account) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.save(account);
		session.flush();
	}
}
