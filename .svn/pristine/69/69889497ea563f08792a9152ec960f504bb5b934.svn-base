package cn.edu.hdu.dao.impl;

/*
 * 
 * 文 件 名 : GenericDao.java CopyRright (c) 2014-reap: 文件编号： 创 建 人： hxb 日 期：
 * 2014-07-26 修 改 人： 日 期： 描 述： Dao层通用调用你方法在这里实现 版 本 号：V1.00
 */

import static org.hibernate.criterion.Example.create;

import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @ClassName GenericDao
 * @Description Dao层通用类 保存持久化层通用的方法 <br/>
 *              通用方法包括：persist,attachDirty,save,update,merger,find,findAll,
 *              delete等
 * @author JOSONHu
 * @version [V1.0, 2014年8月11日 上午9:11:17]
 * @see
 * @since
 * @param <T>
 */
public abstract class GenericDao<T> extends HibernateDaoSupport {
	private Class<T> t;
	public final Logger log = Logger.getLogger(getClass());
	protected String defaultSortField = "";

	@Autowired
	public void setSessionFactory0(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public GenericDao() {
		t = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

	}

	public void persist(T transientInstance) {
		if (log.isDebugEnabled()) {
			log.debug("persisting " + t.getName() + " instance");
		}
		try {
			this.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.persist(transientInstance);
			if (log.isDebugEnabled()) {
				log.debug("persist successful");
			}
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(T instance) {
		if (log.isDebugEnabled()) {
			log.debug("attaching dirty " + t.getName() + " instance");
		}
		try {
			this.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.saveOrUpdate(instance);
			if (log.isDebugEnabled()) {
				log.debug("attachdirty successful");
			}
		} catch (RuntimeException re) {
			log.error("attach dirty failed", re);
			throw re;
		}
	}

	public void attachClean(T instance) {
		if (log.isDebugEnabled()) {
			log.debug("attaching clean " + t.getName() + " instance");
		}
		try {
			this.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.lock(instance, LockMode.NONE);
			if (log.isDebugEnabled()) {
				log.debug("attach clean successful");
			}
		} catch (RuntimeException re) {
			log.error("attach clean failed", re);
			throw re;
		}
	}

	public boolean delete(T persistentInstance) {
		if (log.isDebugEnabled()) {
			log.debug("deleting " + t.getName() + " instance");
		}
		try {
			this.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.delete(persistentInstance);
			if (log.isDebugEnabled()) {
				log.debug("delete successful");
			}
			return true;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public boolean deleteAll() {
		if (log.isDebugEnabled()) {
			log.debug("deleting all " + t.getName() + " instance");
		}
		try {
			String queryString = "delete from " + t.getName();
			this.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createQuery(queryString).executeUpdate();
			if (log.isDebugEnabled()) {
				log.debug("delete all successful");
			}
			return true;
		} catch (RuntimeException re) {
			log.error("delete all failed", re);
			throw re;
		}
	}

	public void save(T instance) {
		if (log.isDebugEnabled()) {
			log.debug("saving " + t.getName() + " instance");
		}
		try {
			this.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.save(instance.getClass().getName(), instance);
			if (log.isDebugEnabled()) {
				log.debug("save successful");
			}
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void update(T instance) {
		if (log.isDebugEnabled()) {
			log.debug("updating " + t.getName() + " instance");
		}
		try {
			this.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.update(instance);
			if (log.isDebugEnabled()) {
				log.debug("update successful");
			}
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public T saveOrUpdate(T instance) {
		if (log.isDebugEnabled()) {
			log.debug("save or update " + t.getName() + " instance");
		}
		try {
			this.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.saveOrUpdate(instance);
			if (log.isDebugEnabled()) {
				log.debug("save or update successful");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("save or update failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public T merge(T detachedInstance) {
		if (log.isDebugEnabled()) {
			log.debug("merging " + t.getName() + " instance");
		}
		try {
			T result = (T) this.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public T findById(java.lang.Long id) {
		if (log.isDebugEnabled()) {
			log.debug("getting " + t.getName() + " instance with id: " + id);
		}
		try {
			T instance = (T) this.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().get(t.getName(), id);
			if (instance == null) {
				if (log.isDebugEnabled()) {
					log.debug("get successful, no instance found");
				}
			} else {
				if (log.isDebugEnabled()) {
					log.debug("get successful, instance found");
				}
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<T> findByIds(List<String> ids) {
		if (log.isDebugEnabled()) {
			log.debug("finding " + t.getName() + " instance by ids");
		}
		try {
			String hql = "from " + t.getName() + " where id in (:ids)";
			List<T> results = (List<T>) this.getHibernateTemplate()
					.getSessionFactory().getCurrentSession().createQuery(hql)
					.setParameterList("ids", ids).list();
			if (log.isDebugEnabled()) {
				log.debug("find by ids successful, result size: "
						+ results.size());
			}
			return results;
		} catch (RuntimeException re) {
			log.error("find by ids failed", re);
			throw re;
		}
	}

	public List<T> findByExample(T instance) {
		if (log.isDebugEnabled()) {
			log.debug("finding " + t.getName() + " instance by example");
		}
		try {
			List<T> results = (List<T>) this.getHibernateTemplate()
					.getSessionFactory().getCurrentSession()
					.createCriteria(t.getName()).add(create(instance)).list();
			if (log.isDebugEnabled()) {
				log.debug("find by example successful, result size: "
						+ results.size());
			}
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public T findOneByExample(T instance) {
		if (log.isDebugEnabled()) {
			log.debug("finding " + t.getName() + " instance by example");
		}
		try {
			T results = (T) this.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createCriteria(t.getName())
					.add(create(instance)).uniqueResult();
			if (log.isDebugEnabled()) {
				log.debug("find one by example successful");
			}
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public boolean deleteById(Long id) {
		if (log.isDebugEnabled()) {
			log.debug("deleting " + t.getName() + " instance with id: " + id);
		}
		try {
			T instance = this.findById(id);
			if (instance != null) {
				this.delete(instance);
				if (log.isDebugEnabled()) {
					log.debug("delete by id successful");
				}
			} else
				log.warn("delete by id ignored,instance with id:" + id
						+ " not found");
			return true;
		} catch (RuntimeException re) {
			log.error("delete by id failed", re);
			throw re;
		}
	}

	public boolean deleteByIds(List<java.lang.Long> ids) {
		if (log.isDebugEnabled()) {
			log.debug("deleting " + t.getName() + " instance with ids");
		}
		try {
			for (Long id : ids) {
				T instance = this.findById(id);
				if (instance != null) {
					this.delete(instance);
					if (log.isDebugEnabled()) {
						log.debug("delete by id successful");
					}
				} else
					log.warn("delete by id ignored,instance with id:" + id
							+ " not found");
			}
			return true;
		} catch (RuntimeException re) {
			log.error("delete by id failed", re);
			throw re;
		}
	}

	public List<T> find(String filter) {
		if (log.isDebugEnabled()) {
			log.debug("finding " + t.getName() + " instance with filter: "
					+ filter);
		}
		return this.find("", filter);
	}

	/**
	 * 通过参数方式查询
	 * 
	 * @param filter
	 * @param objects
	 * @return
	 */
	public List<T> findParams(String query, Object... objects) {
		return this.getHibernateTemplate().find(query, objects);
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

	public List<T> find(String sortField, String filter) {
		if (log.isDebugEnabled()) {
			log.debug("finding " + t.getName() + " instance with sortField: "
					+ sortField + ", filter: " + filter);
		}
		try {
			String queryString = "";
			if (filter != null && filter.length() > 0) {
				queryString = "from " + t.getName() + " as model where "
						+ filter;
			} else {
				queryString = "from " + t.getName() + " as model";
			}
			if (sortField != null && sortField.length() > 0) {
				queryString += " order by " + sortField;
			}
			List<T> results = this.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createQuery(queryString).list();
			if (log.isDebugEnabled()) {
				log.debug("find successful, result size: " + results.size());
			}
			return results;
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
	}

	public List findByHql(String hql, Object... params) {
		if (log.isDebugEnabled()) {
			log.debug("findByHql " + t.getName() + " instance with Hql: " + hql);
		}
		try {

			List results = this.getHibernateTemplate().find(hql, params);

			return results;
		} catch (RuntimeException re) {
			log.error("findByHql failed", re);
			throw re;
		}
	}

	public List<T> findByProperty(String propertyName, Object value) {
		
		if (log.isDebugEnabled()) {
			log.debug("finding " + t.getName() + " instance with property: "
					+ propertyName + ", value: " + value);
		}
		try {
			String queryString = null;
			List<T> results = null;
			if (value == null) {
				queryString = "from " + t.getName() + " as model where model."
						+ propertyName + " is null";
				results = this.getHibernateTemplate().find(queryString);
			} else {
				queryString = "from " + t.getName() + " as model where model."
						+ propertyName + " = ?";
				results = this.getHibernateTemplate().find(queryString, value);
			}
			if (log.isDebugEnabled()) {
 				log.debug("find by property successful, result size: "
						+ results.size());
			}
			return results;
		} catch (RuntimeException re) {
			log.error("find by property failed", re);
			throw re;
		}
	}

	public List<T> getPaginationDao(int pageNum, int pageSize, String orderby,
			String filterString) {
		if (log.isDebugEnabled()) {
			log.debug("getPaginationDao " + t.getName());
		}
		try {
			String whereString = "";
			if (filterString != null && filterString.length() > 0)
				whereString = " where " + filterString;
			String hql = "select t from " + t.getName() + " t " + whereString;
			if (orderby != null && orderby.length() > 0)
				hql = hql + " order by t." + orderby;
			Query query = this.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createQuery(hql)
					.setFirstResult(pageNum).setMaxResults(pageSize);
			List<T> results = query.list();
			if (log.isDebugEnabled()) {
				log.debug("getPaginationDao successful, result size: "
						+ results.size());
			}
			return results;
		} catch (RuntimeException re) {
			log.error("getPaginationDao failed", re);
			throw re;
		}
	}

	public List getPaginationBySql(int pageNum, int pageSize, String sql,
			boolean ishql) {
		if (log.isDebugEnabled()) {
			log.debug("getPaginationBySql " + t.getName());
		}
		try {
			Query query;
			if (ishql)
				query = this.getHibernateTemplate().getSessionFactory()
						.getCurrentSession().createQuery(sql)
						.setFirstResult(pageNum).setMaxResults(pageSize);
			else
				query = this.getHibernateTemplate().getSessionFactory()
						.getCurrentSession().createSQLQuery(sql)
						.setFirstResult(pageNum).setMaxResults(pageSize)
						.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List results = query.list();
			if (log.isDebugEnabled()) {
				log.debug("getPaginationBySql successful, result size: "
						+ results.size());
			}
			return results;
		} catch (RuntimeException re) {
			log.error("getPaginationBySql failed", re);
			throw re;
		}
	}

	public Map getPaginationMap(int pageNum, int pageSize, String orderby,
			String filterString) {
		if (log.isDebugEnabled()) {
			log.debug("getPaginationMap " + t.getName());
		}
		try {
			List list = this.getPaginationDao(pageNum, pageSize, orderby,
					filterString);
			HashMap rvMap = new HashMap();
			rvMap.put("root", list);
			long total = this.getDaoCount(filterString, false);
			rvMap.put("totalProperty", total);
			if (log.isDebugEnabled()) {
				log.debug("getPaginationMap successful");
			}
			return rvMap;
		} catch (RuntimeException re) {
			log.error("getPaginationMap failed", re);
			throw re;
		}
	}

	public Query getQuery(String hsql) {
		Query query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(hsql);
		return query;
	}

	public Query getSqlQuery(String sql) {
		Query query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		return query;
	}

	public long getDaoCount() {
		if (log.isDebugEnabled()) {
			log.debug("getDaoCount " + t.getName());
		}
		try {
			String hql = "select count(*) from " + t.getName();
			long l = (Long) this.getHibernateTemplate().find(hql).get(0);
			if (log.isDebugEnabled()) {
				log.debug("getDaoCount successful, result size: "
						+ String.valueOf(l));
			}
			return l;
		} catch (RuntimeException re) {
			log.error("getDaoCount failed", re);
			throw re;
		}
	}

	public long getDaoCount(String filterString, boolean initsearchHql) {
		if (log.isDebugEnabled()) {
			log.debug("getDaoCount " + t.getName());
		}
		try {
			String whereString = "";
			long l;
			if (initsearchHql) {
				whereString = initSearchHql_whereString(filterString);
				String hql = "select count(*) from " + t.getName() + " "
						+ whereString;
				Map map = initSearchValues(filterString);
				Object[] perchs = map.keySet().toArray(new String[0]);
				l = (Long) this.getHibernateTemplate().find(hql, perchs).get(0);
			} else {
				if (filterString != null && filterString.length() > 0)
					whereString = " where " + filterString;
				String hql = "select count(*) from " + t.getName() + " "
						+ whereString;
				l = (Long) this.getHibernateTemplate().find(hql).get(0);
			}
			if (log.isDebugEnabled()) {
				log.debug("getDaoCount successful, result size: "
						+ String.valueOf(l));
			}
			return l;
		} catch (RuntimeException re) {
			log.error("getDaoCount failed", re);
			throw re;
		}
	}

	public String initSearchHql_whereString(String filters) {
		if (log.isDebugEnabled()) {
			log.debug("initSearchHql_whereString " + t.getName());
		}
		try {
			String hqlFilter = " where  1=1 ";
			HashSet perchSet = new HashSet(0);
			if (filters == null || filters.equals("")) {
				return hqlFilter;
			}
			String filterString = filters.replaceAll("[\"]", "");
			filterString = filterString.substring(1, filterString.length() - 1);
			String[] arrFilter = filterString.split(",");
			for (String filter : arrFilter) {
				if (filter.startsWith("except"))
					continue;
				if (filter.startsWith("null")) {
					String[] optionString = filter.split(":", 2);
					String[] arrOption = optionString[0].split("\\$");
					if (optionString[1].equals("true")) {
						hqlFilter += arrOption[1]
								+ " "
								+ arrOption[2].replaceAll("[-]", ".")
										.replaceAll("&", "") + " is not null ";
					}
					continue;
				}
				String optionString = filter.split(":", 2)[0];
				String filterValue = filter.split(":", 2)[1];
				String[] arrOption = optionString.split("\\$");
				if (!(filterValue.equals("")) || arrOption[2].equals("!=")) {
					String perchString = arrOption[1].replaceAll("[-]", "");
					if (perchSet.contains(perchString)) {
						perchString += "_1";
					}
					perchSet.add(perchString);
					hqlFilter += arrOption[0]
							+ " "
							+ arrOption[1].replaceAll("[-]", ".").replaceAll(
									"&", "")
							+ " "
							+ arrOption[2]
							+ " "
							+ arrOption[3].replace(arrOption[3], ":"
									+ perchString) + " ";
				}
			}
			if (log.isDebugEnabled()) {
				log.debug("function-initSearchHql success:" + hqlFilter);
			}
			return hqlFilter;
		} catch (RuntimeException re) {
			log.error("function-initSearchHql error", re);
			return null;
		}
	}

	public Map initSearchValues(String filters) {
		if (log.isDebugEnabled()) {
			log.debug("initSearchValues " + t.getName());
		}
		try {
			Map<String, Object> valuesMap = new HashMap<String, Object>();
			if (filters == null || filters.equals("")) {
				return valuesMap;
			}
			String filterString = filters.replaceAll("[\"]", "");
			filterString = filterString.substring(1, filterString.length() - 1);
			String[] arrFilter = filterString.split(",");
			for (String filter : arrFilter) {
				if (filter.startsWith("except"))
					continue;
				if (filter.startsWith("null"))
					continue;
				String optionString = filter.split(":", 2)[0];
				String valueString = filter.split(":", 2)[1];
				String[] arrOption = optionString.split("\\$");
				if (!(valueString.equals("")) || arrOption[2].equals("!=")) {
					String perchString = arrOption[1].replaceAll("[-]", "");
					String valueType = arrOption.length > 4 ? arrOption[4]
							: "String";
					valueString = arrOption[3].replace("value", valueString);
					Object value = valueTransform(valueString, valueType);
					if (valuesMap.containsKey(perchString)) {
						perchString += "_1";
					}
					valuesMap.put(perchString, value);
				}
			}
			return valuesMap;
		} catch (RuntimeException re) {
			log.error("initSearchValues error", re);
			return null;
		}
	}

	public Object valueTransform(String valueString, String valueType) {
		if (valueType.equals("String")) {
			return valueString;
		} else if (valueType.equals("Short")) {
			return Short.parseShort(valueString);
		} else if (valueType.equals("Integer")) {
			return Integer.parseInt(valueString);
		} else if (valueType.equals("Double")) {
			return Double.parseDouble(valueString);
		} else if (valueType.equals("Long")) {
			return Long.parseLong(valueString);
		} else if (valueType.equals("Boolean")) {
			return Boolean.parseBoolean(valueString);
		} else if (valueType.equals("Date")) {
			try {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(valueString.replace("T", " "));
			} catch (ParseException re) {
				log.error("error while transfer to date from " + valueString,
						re);
				return null;
			}
		} else
			return valueString;
	}
}
