package cn.edu.hdu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlzkj.app.util.HqlBuilder;
import com.zlzkj.core.sql.Row;

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.dao.impl.AccountDaoImpl;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.GlobalCfg;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.pojo.Role;
import cn.edu.hdu.pojo.Store;
import cn.edu.hdu.service.AccountService;
import cn.edu.hdu.util.DataUtil;

@Service(value="accountService")
public class AccountServiceImpl extends GenericService<Account> implements AccountService {

	@Resource
	private GlobalCfgServiceImpl globalCfgService;
	
	public AccountDaoImpl getAccountDao() {
        return (AccountDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setAccountDao(AccountDaoImpl accountDao) {
	    this.setGenericDao(accountDao);
	}
	
	
	@Override
	public Account validate(String account, String password) {
		boolean result = false;
		Account acc = null;
		List<Account> accounts = this.findByProperty("account", account);
		List<Account> phones = this.findByProperty("mobile", account);
		if(accounts.size() == 1 ){//判断用账号登陆
			acc = accounts.get(0);
			if(acc.getPassword().equals(DigestUtils.md5Hex(password))){
				result = true;
			}
		}
		if(result == true){
			return acc;
		}
		if(phones.size() == 1){//判断用手机号登陆
			acc = phones.get(0);
			if(acc.getPassword().equals(DigestUtils.md5Hex(password))){
				result = true;
			}
		}
		if(result == true){
			return acc;
		}else{
			return null;
		}
	}
	
	@Override
	public Row getAccountList(String name, String accountNo,int rowNum,int page) {
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.from("Account account")
				.like("account.name", name)
				.like("account.account", accountNo)
				.buildHql();
		Query query = this.getQuery(hql_data);
		hb.setParam(query);
		List<Account> accList = DataUtil.getPages(query, page, rowNum).list();
		String hql_sum = hb.select("count(*)")
				.from("Account account")
				.like("account.name", name)
				.like("account.account", accountNo)
				.buildHql();
		query = this.getQuery(hql_sum);
		hb.setParam(query);
		Object accSum = query.uniqueResult();
		List<Row> rows = new ArrayList<Row>();
		for(Account acc:accList){
			Row row = new Row();
			row.put("checkboxid", acc.getId());
			row.put("name", acc.getName());
			row.put("roleName", acc.getRole().getRoleName());
			row.put("accountNo", acc.getAccount());
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rows);
		return row;
	}

	@Override
	public int resetAccountPwd(Long id,String password){
		return this.getAccountDao().resetAccountPwd(id,password);
	}

	@Override
	public int resetMemberPwd(String memberNo, String password) {
		logger.info("Parameters:" + "memberNo=" + memberNo + "，new password=" + password);
		return this.getAccountDao().resetMemberPwd(memberNo,password);
	}

	@Override
	public boolean addAccount(Account account) {
		try {
			List<Account> accsInDB = (List<Account>)this.findByProperty("account", account.getAccount());
			if(accsInDB.size() == 0){
				Role role = new Role();
				role.setId(GlobalParam.DEFAULT_ROLE_ID);
				account.setRole(role);
				List<GlobalCfg> gcList = globalCfgService.findAll();
				account.setPassword(DigestUtils.md5Hex(gcList.get(0).getInitPwd()));
				this.getAccountDao().addAccount(account);
			}else{
				return false;
			}
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean setRole(Account account) {
		try {
			Account accDB = this.findById(account.getId());
			accDB.setRole(account.getRole());
			this.saveOrUpdate(accDB);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getAjaxStore() {
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.from("Account account")
				.in("account.role.id", "idList")
				.buildHql();
		List<Long> idList = new ArrayList<Long>();
		idList.add(GlobalParam.ADMIN_ROLE_ID);
		idList.add(GlobalParam.MENDIAN_ROLE_ID);
		List<Account> accList = DataUtil.getPages(this.getQuery(hql_data), 0, 0)
									.setParameterList("idList", idList)
									.list();
		String re = "[";
		for(int i = 0;i<accList.size();i++){
			if(i > 0){
				re += ",";
			}
			re += "\"" + accList.get(i).getName() + "\"";
		}
		re +="]";
		return re;
	}

	@Override
	public Account findStoreById(Long id) {
		List<Account> account =this.findByProperty("id", id); 
		return account.get(0); 
	}

	

}
