package cn.edu.hdu.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import cn.edu.hdu.service.MenuService;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class MenuTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Resource
	private MenuService menuService;
	
	@Test
	public void getLeftMenu(){
		System.out.println(menuService.getLeftMenu());
	}
	
	@Test
	public void getLeftMenu1(){
		System.out.println(menuService.getLeftMenu(10L));
	}
	@Test
	public void getMenuTreeNode(){
		System.out.println(menuService.getMenuTreeNode(1L));
	}
}
