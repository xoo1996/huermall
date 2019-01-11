package cn.edu.hdu.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.hdu.dao.impl.GenericDao;

/**
 * @ClassName GenericService
 * @Description 通用服务类 <br/>
 *              （详细说明）
 * @author JOSONHu
 * @version [V1.0, 2014年8月11日 上午10:01:25]
 * @see
 * @since
 * @param <T>
 */
@Transactional
public class GenericService<T> {
	public final Logger logger = Logger.getLogger(getClass());

	public GenericService() {
	};
	
	private GenericDao<T> genericDao;

	public void setGenericDao(GenericDao<T> genericDao) {
		this.genericDao = genericDao;
	}

	public GenericDao<T> getGenericDao() {
		return genericDao;
	}

	public void persist(T t) {
		try {
			this.getGenericDao().persist(t);
		} catch (Exception re) {
			logger.error("GenericService persist error", re);
		}
	}

	public void attachDirty(T t) {
		try {
			this.getGenericDao().attachDirty(t);
		} catch (Exception re) {
			logger.error("GenericService attachDirty error", re);
		}
	}

	public void attachClean(T t) {
		try {
			this.getGenericDao().attachClean(t);
		} catch (Exception re) {
			logger.error("GenericService attachClean error", re);
		}
	}

	public boolean delete(T t) {
		try {
			this.getGenericDao().delete(t);
			return true;
		} catch (Exception re) {
			logger.error("GenericService delete error", re);
			return false;
		}
	}

	public boolean deleteList(List<T> ts) {
		try {
			for (T t : ts) {
				this.delete(t);
			}
			return true;
		} catch (Exception re) {
			logger.error("GenericService deleteList error", re);
			return false;
		}
	}

	public boolean deleteAll() {
		try {
			return this.getGenericDao().deleteAll();
		} catch (Exception re) {
			logger.error("GenericService deleteAll error", re);
			return false;
		}
	}

	public void save(T t) {
		try {
			this.getGenericDao().save(t);
		} catch (Exception re) {
			logger.error("GenericService save error", re);
		}
	}

	public void update(T t) {
		try {
			this.getGenericDao().update(t);
		} catch (Exception re) {
			logger.error("GenericService update error", re);
		}
	}

	public T saveOrUpdate(T t) {
		try {
			return this.getGenericDao().saveOrUpdate(t);
		} catch (Exception re) {
			logger.error("GenericService saveOrUpdate error", re);
			return null;
		}
	}

	public void saveOrUpdateEntity(Object entity) {
		try {
			this.getGenericDao().getSessionFactory().getCurrentSession()
					.saveOrUpdate(entity);
		} catch (Exception re) {
			logger.error("GenericService saveOrUpdateEntity error", re);
		}
	}

	public T merge(T t) {
		try {
			return this.getGenericDao().merge(t);
		} catch (Exception re) {
			logger.error("GenericService merge error", re);
			return null;
		}
	}

	public List<T> findParams(String filter, Object... values) {
		return this.getGenericDao().findParams(filter, values);
	}

	public List findByHql(String hql, Object... values) {
		return this.getGenericDao().findByHql(hql, values);
	}

	public T findById(Long id) {
		try {
			return this.getGenericDao().findById(id);
		} catch (Exception re) {
			logger.error("GenericService findById error", re);
			return null;
		}
	}

	public List<T> findByIds(List<String> ids) {
		try {
			return this.getGenericDao().findByIds(ids);
		} catch (Exception re) {
			logger.error("GenericService findByIds error", re);
			return null;
		}
	}

	public List<T> findByExample(T t) {
		try {
			return this.getGenericDao().findByExample(t);
		} catch (Exception re) {
			logger.error("GenericService findByExample error", re);
			return null;
		}
	}

	public T findOneByExample(T t) {
		try {
			return this.getGenericDao().findOneByExample(t);
		} catch (Exception re) {
			logger.error("GenericService findByExample error", re);
			return null;
		}
	}

	public boolean deleteById(Long id) {
		try {
			return this.getGenericDao().deleteById(id);
		} catch (Exception re) {
			logger.error("GenericService deleteById error", re);
			return false;
		}
	}

	public boolean deleteByIds(List<Long> ids) {
		try {
			return this.getGenericDao().deleteByIds(ids);
		} catch (Exception re) {
			logger.error("GenericService deleteByIds error", re);
			return false;
		}
	}

	public List<T> findAll() {
		return this.find("", "");
	}

	public List<T> findSort(String sortField) {
		return this.find(sortField, "");
	}

	public List<T> findFilter(String filter) {
		return this.find("", filter);
	}

	public List<T> find(String orderby, String filter) {
		try {
			return this.getGenericDao().find(orderby, filter);
		} catch (Exception re) {
			logger.error("GenericService find error", re);
			return null;
		}
	}

	public List<T> findByProperty(String propertyName, Object value) {
		try {
			
			List<T> obj = this.getGenericDao().findByProperty(propertyName, value);
			return obj;
		} catch (Exception re) {
			logger.error("GenericService findByProperty error", re);
			return null;
		}
	}

	public Query getQuery(String hsql) {
		Query query = this.getGenericDao().getQuery(hsql);
		return query;
	}

	public Query getSqlQuery(String sql) {
		Query query = this.getGenericDao().getSqlQuery(sql);
		return query;
	}

	public List<T> getPaginationDao(int pageNum, int pageSize, String orderby,
			String filterString) {
		try {
			return this.getGenericDao().getPaginationDao(pageNum, pageSize,
					orderby, filterString);
		} catch (Exception re) {
			logger.error("GenericService getPaginationDao error", re);
			return null;
		}
	}

	public List getPaginationBySql(int pageNum, int pageSize, String sql,
			boolean ishql) {
		try {
			return this.getGenericDao().getPaginationBySql(pageNum, pageSize,
					sql, ishql);
		} catch (Exception re) {
			logger.error("GenericService getPaginationBySql error", re);
			return null;
		}
	}

	public Map getPaginationMap(int pageNum, int pageSize, String orderby,
			String filterString) {
		try {
			return this.getGenericDao().getPaginationMap(pageNum, pageSize,
					orderby, filterString);
		} catch (Exception re) {
			logger.error("GenericService getPaginationMap error", re);
			return null;
		}
	}

	public long getDaoCount() {
		try {
			return this.getGenericDao().getDaoCount();
		} catch (Exception re) {
			logger.error("GenericService getDaoCount error", re);
			return 0;
		}
	}

	public long getDaoCount(String filterString, boolean initsearchHql) {
		try {
			return this.getGenericDao()
					.getDaoCount(filterString, initsearchHql);
		} catch (Exception re) {
			logger.error("GenericService getDaoCount error", re);
			return 0;
		}
	}

}
