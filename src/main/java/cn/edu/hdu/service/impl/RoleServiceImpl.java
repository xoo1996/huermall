package cn.edu.hdu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zlzkj.app.util.HqlBuilder;
import com.zlzkj.core.sql.Row;

import cn.edu.hdu.dao.impl.RoleDaoImpl;
import cn.edu.hdu.pojo.Role;
import cn.edu.hdu.service.RoleService;
import cn.edu.hdu.util.DataUtil;

@Service(value="roleService")
public class RoleServiceImpl extends GenericService<Role> implements RoleService {

	public RoleDaoImpl getRoleDao() {
        return (RoleDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setRoleDao(RoleDaoImpl roleDao) {
	    this.setGenericDao(roleDao);
	}

	@Resource
	private RRoleMenuServiceImpl rmService;
	
	@Override
	public Row getRoleList(int rowNum, int page) {
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.from("Role role")
				.buildHql();
		String hql_sum = hb.select("count(*)")
				.from("Role role")
				.buildHql();
		List<Role> roleList = DataUtil.getPages(this.getQuery(hql_data), page, rowNum).list();
		List<Role> roleSum = this.findByHql(hql_sum, null);
		List<Row> rows = new ArrayList<Row>();
		for(Role role:roleList){
			Row row = new Row();
			row.put("id", role.getId());
			row.put("name", role.getRoleName());
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", roleSum);
		row.put("rows", rows);
		return row;
	}

	@Override
	public boolean addRole(Role role) {
		try {
			role.setCreateDate(new Date());
			this.save(role);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean editRole(Role role) {
		try {
			Role roleInDB = this.getRoleDao().findById(role.getId());
			roleInDB.setRoleName(role.getRoleName());
			this.getRoleDao().save(roleInDB);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean setRoleMenu(Long roleId, String[] ids) {
		try {
			List<Long> idList = new ArrayList<Long>();
			for(int i = 0;i< ids.length;i++){
				idList.add(Long.parseLong(ids[i]));
			}
			rmService.deleteRoleMenuByRoleId(roleId);
			rmService.addRoleMenu(roleId, idList);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public List<Row> getRoleOptionList() {
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.from("Role role where role.id > '0'")
				.buildHql();
		List<Role> roleList = this.getRoleDao().findByHql(hql_data, null);
		List<Row> rows = new ArrayList<Row>();
		for(Role role:roleList){
			Row row = new Row();
			row.put("id", role.getId());
			row.put("text", role.getRoleName());
			rows.add(row);
		}
		return rows;
	}
	
	
}
