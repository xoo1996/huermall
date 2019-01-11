package cn.edu.hdu.service;

import java.util.List;

import cn.edu.hdu.pojo.Role;

import com.zlzkj.core.sql.Row;

public interface RoleService {

	/**
	 * 返回角色列表: 显示角色列表
	 * @param rowNum
	 * @param page
	 * @return
	 */
	public Row getRoleList(int rowNum,int page);
	
	/**
	 * 返回角色列表: 显示角色下拉菜单列表
	 * @param rowNum
	 * @param page
	 * @return
	 */
	public List<Row> getRoleOptionList();
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public boolean addRole(Role role);
	
	/**
	 * 编辑角色信息
	 * @param role
	 * @return
	 */
	public boolean editRole(Role role);
	
	/**
	 * 为角色配置菜单权限
	 * @param ids
	 * @return
	 */
	public boolean setRoleMenu(Long roleId,String[] ids);
	
}
