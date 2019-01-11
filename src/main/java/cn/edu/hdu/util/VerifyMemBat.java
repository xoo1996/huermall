package cn.edu.hdu.util;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;    
import org.springframework.stereotype.Component;

import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.MemBat;
import cn.edu.hdu.service.impl.ConfigServiceImpl;
import cn.edu.hdu.service.impl.MemBatServiceImpl;
import cn.edu.hdu.service.impl.VerifyBatServiceImpl; 


@Component("taskJob")  
public class VerifyMemBat {
	
	private Logger logger=Logger.getLogger(VerifyMemBat.class);
	
	@Resource
	private MemBatServiceImpl memBatService;
	
	@Resource
	private ConfigServiceImpl configService;
	
	@Resource
	private VerifyBatServiceImpl verifyBatService;
	
	@Scheduled(cron = "0 0/60 * * * ? ")  
	public void autoVerifyMembat() { 
		try{ 
			System.out.println("正在自动审批");
			
			String status = "0";
			status = configService.getMembatVerifySwitchStatus();
			
			if(status.equals("1")){
				List<Object[]> verifyBats = verifyBatService.getVerifyBatList("status","verifying");
				Date nowtime =new java.sql.Date(System.currentTimeMillis());
				
				verifyBatService.updateVerifyIdAndDate(verifyBats,"auto",String.valueOf(nowtime));
				verifyBatService.updateVerifyBatStatus(verifyBats,"pass");
				for(Object[] ob:verifyBats){
					MemBat memBat =new MemBat();

					memBat.setMemberId(String.valueOf(ob[3]));
					memBat.setStoreNo(String.valueOf(ob[2]));
					memBat.setBatteryType(String.valueOf(ob[4]));
					memBat.setBatteryQty(Long.valueOf(String.valueOf(ob[5])));
					memBat.setBatteryReQty(Long.valueOf(String.valueOf(ob[5])));
					memBat.setDate(nowtime);			
					memBat.setStatus("full");
					
					memBatService.addMenBat(memBat);
				}
			}else{
				System.out.println("Status:"+status+"||未开启自动审核");
			}

		}catch (Exception e) {
			
		}
	}  
}
