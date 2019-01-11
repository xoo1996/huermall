package cn.edu.hdu.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import cn.edu.hdu.service.impl.PriceconfigServiceImpl;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class PriceCFGTest extends AbstractTransactionalJUnit4SpringContextTests {
	static Logger logger=Logger.getLogger(PriceCFGTest.class);

	@Resource
	private PriceconfigServiceImpl pcService;
	
	@Test
	public void getSuitPCByPrice(){
		Long price = 5L;
		System.out.println(pcService.getSuitPCByPrice(price));
		price = 10L;
		System.out.println(pcService.getSuitPCByPrice(price));
		price = 15L;
		System.out.println(pcService.getSuitPCByPrice(price));
		price = 20L;
		System.out.println(pcService.getSuitPCByPrice(price));
		price = 50L;
		System.out.println(pcService.getSuitPCByPrice(price));
	}
	
}


