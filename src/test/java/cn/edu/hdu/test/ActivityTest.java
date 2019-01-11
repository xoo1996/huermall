package cn.edu.hdu.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.service.impl.AccountServiceImpl;
import cn.edu.hdu.service.impl.ActivityServiceImpl;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ActivityTest extends AbstractTransactionalJUnit4SpringContextTests {
	static Logger logger=Logger.getLogger(ActivityTest.class);

	@Resource
	private ActivityServiceImpl activityService;
	
	@Test
	public void deleteActivity(){
		Long activityId = 98L;
		activityService.deleteActivity(activityId);
	}
	
	@Test
	public void getOptionList(){
		Object o = activityService.getOptionList();
		System.out.println(o);
	}
}


