package cn.edu.hdu.test;

import java.text.ParseException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import cn.edu.hdu.service.impl.ScoreServiceImpl;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ScoreEventTest extends AbstractTransactionalJUnit4SpringContextTests {
	static Logger logger=Logger.getLogger(ScoreEventTest.class);

	@Resource
	private ScoreServiceImpl scoreService;
	
	@Test
	public void getScoreEventByMemberNo(){
		String memNo = "281091";
		System.out.println(scoreService.getScoreList(null, memNo, null, null, null, "1", "5", null, null));
	}
	
	
	
}


