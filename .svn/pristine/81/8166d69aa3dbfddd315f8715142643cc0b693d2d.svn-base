package cn.edu.hdu.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import cn.edu.hdu.Code.UploadCode;
import cn.edu.hdu.service.FileService;
import cn.edu.hdu.service.MenuService;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class FileTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Resource
	private FileService fileService;
	
	@Test
	public void getLeftMenu(){
		System.out.println(fileService.getImgUrl(UploadCode.MODULE_LUNBO));
	}
	
	
}
