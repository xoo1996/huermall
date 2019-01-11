package cn.edu.hdu.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.edu.hdu.Code.MenuCode;
import cn.edu.hdu.dao.MenuDao;
import cn.edu.hdu.pojo.Menu;

@Repository
public class MenuDaoImpl extends GenericDao<Menu> implements MenuDao {

	@Override
	public List<Menu> getMenuOfLevel(Integer level) {
		String hql = "from Menu menu where menu.levelId = :levelId "
				+ "and menu.id > 0 and menu.isShow = :isShow order by menu.sortId asc";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		List<Menu> menuList = session.createQuery(hql)
				.setParameter("levelId", level)
				.setParameter("isShow", MenuCode.SHOW_YES).list();
		return menuList;
	}

	@Override
	public List<Menu> getMenuOfParentId(Long parentId) {
		String hql = "from Menu menu where menu.menu.menuId = :parentId order by menu.sortId asc";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		List<Menu> menuList = session.createQuery(hql)
				.setParameter("parentId", parentId)
				.list();
		return menuList;
	}
	
	public List<Menu> getAllNodesCanAlloc(){
		String hql = "select m from Menu m where m.menuId > 0 and m.isAllocatable = '1'"
				+ " order by m.levelId asc,m.sortId asc";
		List<Menu> menus = this.getHibernateTemplate().find(hql);
		return menus;
	}
	

	public List<Menu> getAllNodes(){
		String hql = "select m from Menu m where m.menuId > 0" 
				+ " order by m.levelId asc,m.sortId asc";
		List<Menu> menus = this.getHibernateTemplate().find(hql);
		return menus;
	}
	
	public Map<String,String> getNodesByRoleId(long roleId){
		String hql = "select menuId from RRoleMenu rm where rm.roleId = ?";
		List<Long> menuIds = this.getHibernateTemplate().find(hql, roleId);
		Map<String,String> ids = new HashMap<String,String>();
		for(Long id:menuIds){
			ids.put(Long.toString(id), Long.toString(id));
		}
		return ids;
	}

	@Override
	public List<Menu> getMenuOfLevel(Integer level, List<Long> menuIdList) {
		String hql = "from Menu menu where menu.levelId = :levelId "
				+ "and menu.id > 0 and menu.isShow = :isShow and menu.id in (:ids) order by menu.sortId asc";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		List<Menu> menuList = session.createQuery(hql)
				.setParameter("levelId", level)
				.setParameter("isShow", MenuCode.SHOW_YES)
				.setParameterList("ids", menuIdList)
				.list();
		return menuList;
	}

	@Override
	public List<Menu> getMenuOfParentId(Long parentId, List<Long> menuIdList) {
		String hql = "from Menu menu where menu.menu.menuId = :parentId and menu.menuId in (:ids) order by menu.sortId asc";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		List<Menu> menuList = session.createQuery(hql)
				.setParameter("parentId", parentId)
				.setParameterList("ids", menuIdList)
				.list();
		return menuList;
	}

}
