package cn.edu.hdu.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.edu.hdu.dao.PictureDirDao;
import cn.edu.hdu.pojo.PictureDir;

@Repository
public class PictureDirDaoImpl extends GenericDao<PictureDir> implements PictureDirDao{

	@Override
	public Long saveImgPath(String path,String moduleName,Long seriesId) {
		PictureDir picDir = new PictureDir();
		picDir.setPath(path);
		picDir.setModuleName(moduleName);
		picDir.setSeriesId(seriesId);
		picDir.setUploadDate(new Date());
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		return (Long)session.save(picDir);
		
	}
	
	@Override
	public List<String> getImgPath(String moduleName) {
		String hql = "select pd.path from PictureDir pd where pd.moduleName = :moduleName";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql).setParameter("moduleName", moduleName);
		List<String> result = (List<String>)query.list();
		return result;
	}

}
