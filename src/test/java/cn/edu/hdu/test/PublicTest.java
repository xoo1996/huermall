package cn.edu.hdu.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import cn.edu.hdu.Code.UploadCode;
import cn.edu.hdu.service.FileService;
import cn.edu.hdu.service.MenuService;
import cn.edu.hdu.service.PublicService;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class PublicTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Resource
	private PublicService publicService;
	
	@Test
	public void getMathSymbolList(){
		System.out.println(publicService.getMathSymbolList());
	}
	
	
}
