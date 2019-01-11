package cn.edu.hdu.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.service.impl.AccountServiceImpl;
import cn.edu.hdu.service.impl.ScoreServiceImpl;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class SystemTest extends AbstractTransactionalJUnit4SpringContextTests {
	static Logger logger=Logger.getLogger(SystemTest.class);

	@Resource
	private ScoreServiceImpl scoreService;
	
	@Test
	public void getNewMemberCount() throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
		Date startDate = dateFormat.parse("2013-6-1 13:24:16"); 
		Date endDate = new Date();
		String storeAccount = "A0065";
		int rows = 20;
		int page = 1;
		System.out.println(
				scoreService.getNewMemberCount(startDate, endDate, storeAccount, rows, page)
				);
	}
	
	@Test
	public void getNewMemberCountAll() throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
		Date startDate = dateFormat.parse("2013-6-1 13:24:16"); 
		Date endDate = new Date();
		int rows = 20;
		int page = 1;
		System.out.println(
				scoreService.getNewMemberCountAll(startDate, endDate, rows, page)
				);
	}
	
	@Test
	public void getNewMemberCountDetail() throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
		Date startDate = dateFormat.parse("2013-6-1 13:24:16"); 
		Date endDate = new Date();
		String account = "A0065";
		int rows = 20;
		int page = 1;
		System.out.println(
				scoreService.getNewMemberCountDetail(startDate, endDate, account,rows, page)
				);
	}
	
}


