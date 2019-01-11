package cn.edu.hdu.service;

import java.util.List;

import cn.edu.hdu.dto.MenuTreeDto;
import cn.edu.hdu.pojo.Menu;

public interface MenuService {
	/**
	 * 获取左侧菜单
	 * @return list实例
	 */
	List<Menu> getLeftMenu();
	
	/**
	 * 获取左侧菜单
	 * @return list实例
	 */
	List<Menu> getLeftMenu(List<Long> menuIdList);
	
	/**
	 * 获取左侧菜单
	 * @return list实例
	 */
	List<Menu> getLeftMenu(Long roleId);
	
	/**
	 * 根据角色，返回全部可选菜单
	 * @param roleId
	 * @return
	 */
	List<MenuTreeDto> getMenuTreeNode(Long roleId);
}
