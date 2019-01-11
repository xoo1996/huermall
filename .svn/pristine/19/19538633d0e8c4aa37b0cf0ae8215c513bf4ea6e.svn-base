package cn.edu.hdu.dao;

import java.util.List;

import cn.edu.hdu.pojo.Menu;

public interface MenuDao {

	/**
	 * 获得特定层级的菜单项
	 * @param level
	 * @return 返回一个list实例
	 */
	public List<Menu> getMenuOfLevel(Integer level);
	/**
	 * 获得父级菜单为指定id的菜单集合
	 * @param parentId
	 * @return 返回一个list实例
	 */
	public List<Menu> getMenuOfParentId(Long parentId);
	
	/**
	 * 获得特定层级的菜单项
	 * @param level
	 * @return 返回一个list实例
	 */
	public List<Menu> getMenuOfLevel(Integer level,List<Long> menuIdList);
	/**
	 * 获得父级菜单为指定id的菜单集合
	 * @param parentId
	 * @return 返回一个list实例
	 */
	public List<Menu> getMenuOfParentId(Long parentId,List<Long> menuIdList);
	
}
