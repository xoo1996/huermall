package cn.edu.hdu.service;

import java.util.List;

import cn.edu.hdu.pojo.Menu;

public interface RRoleMenuService {

	/**
	 * 删除一个角色的全部关联菜单
	 * @param roleIdList
	 * @return
	 */
	public void deleteRoleMenuByRoleId(Long roleId);
	
	/**
	 * 为角色添加新的关联菜单
	 * @param roleId
	 * @param menuIdList
	 * @return
	 */
	public void addRoleMenu(Long roleId,List<Long> menuIdList);
	
	/**
	 * 根据给定的角色id获得该角色绑定的菜单
	 * @param roleId
	 * @return
	 */
	public List<Menu> getMenuByRoleId(Long roleId);
	
	public List<Long> getMenuIdByRoleId(Long roleId);

}
