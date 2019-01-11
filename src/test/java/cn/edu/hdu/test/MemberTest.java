package cn.edu.hdu.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.service.impl.AccountServiceImpl;
import cn.edu.hdu.service.impl.MemberServiceImpl;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class MemberTest extends AbstractTransactionalJUnit4SpringContextTests {
	static Logger logger=Logger.getLogger(MemberTest.class);

	@Resource
	private MemberServiceImpl memberService;
	
	@Test
	public void addMember(){
		Member member = new Member();
		member.setName("111");
		member.setMemberNo("111111");
		member.setIdCardNo("111111");
		System.out.println(memberService.addMember(member,false));
	}
	
}


