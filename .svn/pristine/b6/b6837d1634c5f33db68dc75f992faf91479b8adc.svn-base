package cn.edu.hdu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.hdu.dao.impl.RRoleMenuDaoImpl;
import cn.edu.hdu.pojo.Menu;
import cn.edu.hdu.pojo.RRoleMenu;
import cn.edu.hdu.service.RRoleMenuService;

@Service(value="roleMenuService")
public class RRoleMenuServiceImpl extends GenericService<RRoleMenu> implements RRoleMenuService {
	
	@Resource
	private RoleServiceImpl roleService;
	
	public RRoleMenuDaoImpl getRRoleMenuDao() {
        return (RRoleMenuDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setRRoleMenuDao(RRoleMenuDaoImpl rRoleMenutDao) {
	    this.setGenericDao(rRoleMenutDao);
	}
	
	
	@Override
	public void deleteRoleMenuByRoleId(Long roleId) {
		try {
			String hql = "delete from RRoleMenu rm where rm.roleId = :roleId";
			Query query = this.getQuery(hql).setParameter("roleId", roleId);
			int deleteSum = query.executeUpdate();
		} catch (HibernateException e) {
			throw e;
		}
		
	}

	@Override
	public void addRoleMenu(Long roleId, List<Long> menuIdList) {
		try {
			for(Long menuId:menuIdList){
				RRoleMenu rm = new RRoleMenu();
				rm.setMenuId(menuId);
				rm.setRoleId(roleId);
				this.getRRoleMenuDao().save(rm);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Menu> getMenuByRoleId(Long roleId) {
		List<Long> menuIdList = this.getMenuIdByRoleId(roleId);
		List<Menu> menuList = new ArrayList<Menu>();
		if(menuIdList.size() != 0){
			menuList = roleService.findByHql("from Menu m where m.roleId in(?)", menuIdList);
		}
		return menuList;
	}
	
	@Override
	public List<Long> getMenuIdByRoleId(Long roleId) {
		List<RRoleMenu> rmList = this.findByProperty("roleId", roleId); 
		List<Long> menuIdList = new ArrayList<Long>();
		for(RRoleMenu rm:rmList){
			menuIdList.add(rm.getMenuId());
		}
		return menuIdList;
	}
	

}
