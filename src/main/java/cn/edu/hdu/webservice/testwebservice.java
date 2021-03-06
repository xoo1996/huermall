package cn.edu.hdu.webservice;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.junit.Test;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import cn.edu.hdu.Code.UrlCode;
import cn.edu.hdu.config.webSend;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.Battery;
import cn.edu.hdu.pojo.GlobalCfg;
import cn.edu.hdu.pojo.MemBat;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.service.BatteryService;
import cn.edu.hdu.service.PriceconfigService;
import cn.edu.hdu.service.ScoreService;
import cn.edu.hdu.service.WaitscoreService;
import cn.edu.hdu.service.impl.AccountServiceImpl;
import cn.edu.hdu.service.impl.BatteryServiceImpl;
import cn.edu.hdu.service.impl.GlobalCfgServiceImpl;
import cn.edu.hdu.service.impl.MemBatServiceImpl;
import cn.edu.hdu.service.impl.MemberServiceImpl;

public class testwebservice {
	WebApplicationContext context=ContextLoader.getCurrentWebApplicationContext();
	MemberServiceImpl memberService=(MemberServiceImpl)context.getBean("memberService");
	MemBatServiceImpl memBatService=(MemBatServiceImpl)context.getBean("memBatService");
	BatteryServiceImpl batteryService=(BatteryServiceImpl)context.getBean("batteryService");
	PriceconfigService priceconfigService=(PriceconfigService)context.getBean("priceconfigService");
	ScoreService scoreService=(ScoreService)context.getBean("scoreService");
	GlobalCfgServiceImpl globalCfgService=(GlobalCfgServiceImpl)context.getBean("globalCfgService");
	WaitscoreService waitscoreService=(WaitscoreService)context.getBean("waitscoreService");
	AccountServiceImpl accountService=(AccountServiceImpl)context.getBean("accountService");
	/**
	 * 更新积分值，新会员创建,老会员扣除积分
	 * @param user
	 * @param money
	 * @param changeScore
	 * @return
	 */
	public String test(String user,String money,String changeScore,String name,String phone,String folno,String coinnum,String account,String pdtid,String balance){
		boolean ex=memberService.judgeExist(user);
		Long score=0L,coin=0L;
		//新会员新建，老会员扣除消耗积分，最后都增加根据价格区间增加的积分
		Long l=priceconfigService.getSuitPCByPrice(Long.parseLong(money));
		//惠耳活动月
//		List<GlobalCfg> gcList = globalCfgService.findAll();
//		Date sdt=gcList.get(0).getActisdt();
//		Date edt=gcList.get(0).getActiedt();
//		java.util.Date today=new java.util.Date(System.currentTimeMillis());
//		if(edt.getTime()>=today.getTime()&&sdt.getTime()<=today.getTime()){
//			l=2*l;
//		}
		List<Member> memInDb = memberService.findByProperty("phone", phone);
		//会员号是新的，但是根据手机号查到会员信息；新用户；老会员
		if((!ex)&&(memInDb != null && memInDb.size() > 0)){
			user=memInDb.get(0).getMemberNo();
			memberService.addScore(user, l);
			scoreService.logCharge(user, l,score,account,folno);
		}else if(!ex){
			Member member=new Member();
			member.setMemberNo(user);
			member.setName(name);
			member.setPhone(phone);
			member.setScore(l);
			List<Account> acc=accountService.findByProperty("account", account);
			if(acc!=null&acc.size()>0){
				member.setStoreId(acc.get(0).getId()); 
			}
			//member.setIdCardNo(idcard);
			memberService.addMember(member,true);
			scoreService.logNewCharge(user, l,account,folno);
		}else{
			score=Long.parseLong(changeScore);
			coin=Long.parseLong(coinnum);
			memberService.addScore(user, l-score);
			memberService.addCoin(user, -coin);
			scoreService.logConsume(user, score,account);
			scoreService.logCharge(user, l,-score,account,folno);
			scoreService.logCoin(user,coin,account);
		}
		waitscoreService.addWaitscore(user,l,folno,score,coin,pdtid,"1",balance);
		//return "用户编号："+user+"付款金额："+money+"扣除积分:"+changeScore;
		return l.toString();
	}
	
	//新增
	/**
	 * 验证是否连接成功
	 * @return
	 */
	public String testreturn(){
		return "true";
	}
	
	//新增
	/**
	 * 修改手机号码
	 * @param oldPhone
	 * @param newPhone
	 * @return
	 */
	public String changePhone(String oldPhone,String newPhone){
		String s = memberService.changePhone(oldPhone, newPhone);
		if("true".equals(s)){
			return "true";
		}else{
			return "false";
		}
		
	}
	
	//新增
	/**
	 * 更新电池信息，没有会员则添加
	 * @param user
	 * @param name
	 * @param phone
	 * @param account
	 * @param gby
	 * @param bm
	 */
	public String testgbi(String user,String name,String phone,String account,String gby,String bm,String orderId,String chargeId){
		System.out.println("user:"+user+" name:"+name+" phone:"+phone+" account:"+account+" gby:"+gby+" bm:"+bm);
		if(phone==null||"".equals(phone)) return "false"; //不能没有手机号
		//判断是否已经存在订单
		boolean isex = false;
		try{
		if(orderId!=null){
			MemBat memBat = memBatService.getMemBatByOrderId(orderId);
			if(memBat!=null)
				isex = true;
		}
		if(chargeId!=null){
			MemBat memBat = memBatService.getMemBatByChargeId(chargeId);
			if(memBat!=null)
				isex = true;
		}
		
		if(user==null||"".equals(user))
			user = phone; //直接将手机作为用户的编号
		
		Long score=0L,coin=0L;
		List<Member> memInDb = memberService.findByProperty("phone", phone);
		boolean ex = false;
		if(memInDb.size()>0) ex = true;//如果根据手机号得到member，则用户已经存在
		
		List<Account> acc=accountService.findByProperty("account", account);	//所属门店
		
		if(!ex&&!isex){
			Member member=new Member();
			member.setMemberNo(user);
			member.setName(name);
			member.setPhone(phone);
			member.setScore(score);
			member.setCoin(coin); 
			member.setStoreId(acc.get(0).getId());
			memberService.addMember(member,true);  //根据用户手机添加
			String memberId = memberService.findByProperty("phone", phone).get(0).getId().toString();
			if(bm!=null&&!bm.equals("")){
				long batteryQty = Long.parseLong(gby);
				long batteryReQty = batteryQty;
				MemBat memBat = new MemBat();
				memBat.setMemberId(memberId);
				memBat.setStoreNo(account);
				memBat.setBatteryType(bm);
				memBat.setBatteryQty(batteryQty);
				memBat.setBatteryReQty(batteryReQty);
				if(batteryReQty>0){
					memBat.setStatus("full");
				}else{
					memBat.setStatus("empty");
				}
			
			
				if(orderId!=null){
					memBat.setOrderId(orderId);
				}
				if(chargeId!=null){
					memBat.setChargeId(chargeId);
				}
				memBat.setDate(new java.sql.Date(System.currentTimeMillis()));
				
				//向两个membat表中插入数据
				try{
					if(!bm.equals("0"))
						memBatService.addMenBat(memBat);
				}catch (Exception e) {
					return "false";
				}
				
				
				
				return "true";
			}
		}else if(ex&&!isex){
			if(bm!=null&&!bm.equals("")){
				long batteryQty = Long.parseLong(gby);
				long batteryReQty = batteryQty;
				MemBat memBat = new MemBat();
				memBat.setMemberId(memInDb.get(0).getId().toString());
				memBat.setStoreNo(account);
				memBat.setBatteryType(bm);
				memBat.setBatteryQty(batteryQty);
				memBat.setBatteryReQty(batteryReQty);
				if(batteryReQty>0){
					memBat.setStatus("full");
				}else{
					memBat.setStatus("empty");
				}
				
				if(orderId!=null){
					memBat.setOrderId(orderId);
				}
				if(chargeId!=null){
					memBat.setChargeId(chargeId);
				}
				memBat.setDate(new java.sql.Date(System.currentTimeMillis()));
				try{
					if(!bm.equals("0"))
						memBatService.addMenBat(memBat);
				}catch (Exception e) {
					return "false";
				}
			}
			return "true";
		}else if(isex){
			return "ex";
		}
		return "false";
		}catch (Exception e) {
			return "false";
		}
	}
	
	//新增
	public String testdel(String orderId,String chargeId){
		if(orderId!=null){
			try{
				memBatService.delMemBat(orderId, "order_id");
			}catch (Exception e) {
				return "false";
			}
			
		}
		if(chargeId!=null){
			String b; 
			try{
				memBatService.delMemBat(chargeId, "charge_id");
			}catch (Exception e) {
				return "false";
			}
		}	
		
		return "true";
		
	}
	
	//新增
	public String getbi(String orderId,String chargeId){
		MemBat memBat = null;
		if(orderId!=null){
			memBat = memBatService.getMemBatByOrderId(orderId);
		}
		if(chargeId!=null){
			memBat = memBatService.getMemBatByChargeId(chargeId);
		}
		if(memBat != null){
			String num = (memBat.getBatteryQty()-memBat.getBatteryReQty())+"";
			String ss = memBat.getBatteryType()+","+num;
			return ss;
		}else{
			return null;
		}
	}

	/**
	 * 返回会员积分值
	 * @param memberno
	 * @return
	 */
	public String rescore(String phone){
		String score="0",coin="0";
		if(phone!=null){
			score=memberService.findScore(phone);
			coin=memberService.findCoin(phone);
			if(score==null) score= "0";
			if(coin==null) coin= "0";
		}
		List<GlobalCfg> gcList = globalCfgService.findAll();
		Long changeScore=gcList.get(0).getChangeScore();
		Long changeMoney=gcList.get(0).getChangeMoney();
		Long mostRate=gcList.get(0).getMostRate();
		return score+","+changeScore+","+changeMoney+","+mostRate+","+coin;
	}
	
	/**
	 * 订单退机审核通过
	 * @param folno
	 * @return
	 */
	public String delwaitscore(String folno,String applyid){
		try{
			// 退机
			waitscoreService.delWaitscore(folno);
			waitscoreService.delApplycoin(applyid);
			return "1";
		}catch(Exception e){
			return "0";
		}
	}
	
	/**
	 * 耳背机退机审核通过
	 * @param folno
	 * @return
	 */
	public String norRec(String folno,int rnt,String mon,String pdtid,String chgcltid){
		try{
			// 退机
			waitscoreService.delnor(folno,rnt,mon,pdtid);
			waitscoreService.delApplycoin(chgcltid);
			return "1";
		}catch(Exception e){
			return "0";
		}
	}
	
	/**
	 * 更新老会员惠耳币
	 * @param user
	 * @param money
	 * @param changeScore
	 * @return
	 */
	public String repair(String user,String phone,String coinnum,String account){
		List<Member> memInDb = memberService.findByProperty("phone", phone);
		if(memInDb != null && memInDb.size() > 0){
			Long coin=Long.parseLong(coinnum);
			memberService.addCoin(user, -coin);
			scoreService.logrepair(user,coin,account);
		}
		return "";
	}
	
	
}
