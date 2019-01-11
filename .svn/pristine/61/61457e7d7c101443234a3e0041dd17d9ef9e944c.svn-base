package cn.edu.hdu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.Code.MenuCode;
import cn.edu.hdu.dao.MenuDao;
import cn.edu.hdu.dao.impl.MenuDaoImpl;
import cn.edu.hdu.dto.MenuTreeDto;
import cn.edu.hdu.pojo.Menu;
import cn.edu.hdu.service.MenuService;
import cn.edu.hdu.service.RRoleMenuService;

@Service(value="menuService")
public class MenuServiceImpl extends GenericService<Menu> implements MenuService {

	@Resource
	private RRoleMenuService rmService;
	
	public MenuDaoImpl getMenuDao() {
        return (MenuDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setMenuDao(MenuDaoImpl menuDao) {
	    this.setGenericDao(menuDao);
	}

	@Override
	public List<Menu> getLeftMenu() {
		//获取一级菜单
		List<Menu> topMenuList = this.getMenuDao().getMenuOfLevel(MenuCode.LEVEL0);
		//遍历每个一级菜单，若有子菜单，则添加到自己的集合中
		for(Menu menu:topMenuList){
			long pid = menu.getMenuId();
			List<Menu> children = this.getMenuDao().getMenuOfParentId(pid);
			menu.setMenus(children);
		}
		return topMenuList;
	}
	
	@Override
	public List<Menu> getLeftMenu(List<Long> menuIdList) {
		//获取一级菜单
		List<Menu> topMenuList = new ArrayList<Menu>();
		if(menuIdList.size() > 0){
			topMenuList = this.getMenuDao().getMenuOfLevel(MenuCode.LEVEL0,menuIdList);
			//遍历每个一级菜单，若有子菜单，则添加到自己的集合中
			for(Menu menu:topMenuList){
				long pid = menu.getMenuId();
				List<Menu> children = this.getMenuDao().getMenuOfParentId(pid,menuIdList);
				menu.setMenus(children);
			}
		}
		return topMenuList;
	}
	
	@Override
	public List<Menu> getLeftMenu(Long roleId) {
		List<Long> menuIdList = rmService.getMenuIdByRoleId(roleId);
		return this.getLeftMenu(menuIdList);
	}

	@Override
	public List<MenuTreeDto> getMenuTreeNode(Long roleId) {
		List<Menu> all = null; // 权限树的全部节点
    	Map<String,String> checked = null; //权限树的所有已选节点
    	boolean isAdmin = false; //判断是否为超级管理员
    	if(roleId.intValue() == GlobalParam.ADMIN_ROLE_ID){
    		isAdmin = true;
    		all = this.getAllNodes();
    	}else{
    		all = this.getAllNodesCanAlloc();
    		checked = this.getNodesByRoleId(roleId);
    	}
    	List<MenuTreeDto> finalResult = new ArrayList<MenuTreeDto>();
    	for(Menu menu:all){
    		MenuTreeDto m = new MenuTreeDto();
    		m.setFile(menu.getUrl());
    		m.setId(menu.getMenuId());
    		m.setName(menu.getMenuName());
    		m.setpId(menu.getMenu().getMenuId());
    		if(isAdmin){//超级管理员，默认勾选
    			m.setChecked(true);
    		}else{      //选了的菜单进行勾选
    			if(checked.get(menu.getMenuId().toString()) != null){
        			m.setChecked(true);
        		}
    		}
    		
    		finalResult.add(m);
    	}
    	return finalResult;
	}

	/**
	 * 获取所有可以被分配的、有含义的节点
	 * @return
	 */
    private List<Menu> getAllNodesCanAlloc(){
    	return this.getMenuDao().getAllNodesCanAlloc();
    }
    
	/**
	 * 获取所有有含义的节点
	 * @return
	 */
    private List<Menu> getAllNodes(){
    	return this.getMenuDao().getAllNodes();
    }
    
    /**
     * 根据角色获取属于这个角色的节点编号
     * @param roleId
     * @return
     */
    private Map<String,String> getNodesByRoleId(long roleId){
    	return this.getMenuDao().getNodesByRoleId(roleId);
    }

	

}
