package cn.edu.hdu.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.service.impl.AccountServiceImpl;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class AccountTest extends AbstractTransactionalJUnit4SpringContextTests {
	static Logger logger=Logger.getLogger(AccountTest.class);

	@Resource
	private AccountServiceImpl accountService;
	
	@Test
	public void validate(){
		String account ="king";
		String password = "1";
		Account acc = accountService.validate(account, password);
	}
	
	@Test
	public void validate1(){
		String account ="king";
		Account acc = accountService.validate(account, null);
	}
	
	@Test
	public void getAccountList(){
		String name ="king";
		String accountNo = "1";
		int rowNum = 20;
		int page = 1;
		System.out.println(accountService.getAccountList(name, accountNo, rowNum, page));
	}
	
	@Test
	public void getAccountList1(){
		String name =null;
		String accountNo = null;
		int rowNum = 20;
		int page = 1;
		System.out.println(accountService.getAccountList(name, accountNo, rowNum, page));
	}
	
	@Test
	public void resetAccountPwd(){
		Long id = 3L;
		String password = "1";
		System.out.println(accountService.resetAccountPwd(id, password));
	}
	
	@Test
	public void addAccount(){
		Account acc = new Account();
		acc.setAccount("panjianliang1");
		acc.setName("潘建梁");
		System.out.println(accountService.addAccount(acc));
		System.out.println("xixi ");
		System.out.println("xixi ");
		System.out.println("xixi ");
		
	}
}


