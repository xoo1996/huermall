package cn.edu.hdu.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zlzkj.app.util.LogStringBuilder;
import com.zlzkj.core.base.BaseController;

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.MemBat;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.pojo.VerifyBat;
import cn.edu.hdu.service.AccountService;
import cn.edu.hdu.service.impl.BatEventServiceImpl;
import cn.edu.hdu.service.impl.BatteryServiceImpl;
import cn.edu.hdu.service.impl.ConfigServiceImpl;
import cn.edu.hdu.service.impl.MemBatServiceImpl;
import cn.edu.hdu.service.impl.MemberServiceImpl;
import cn.edu.hdu.service.impl.ScoreServiceImpl;
import cn.edu.hdu.service.impl.StoreServiceImpl;
import cn.edu.hdu.service.impl.VerifyBatServiceImpl;

@Controller
@RequestMapping(value="verify_bat")
public class VerifyBatController extends BaseController{
	
	private static Logger logger = Logger.getLogger(VerifyBatController.class);

	@Resource
	private LogStringBuilder lsb;
	@Resource
	private AccountService accountService;
	@Resource
	private BatteryServiceImpl battService;
	@Resource
	private ScoreServiceImpl scoreService;
	@Resource
	private MemBatServiceImpl memBatService;
	@Resource
	private BatEventServiceImpl batEventService;
	@Resource
	private StoreServiceImpl storeService;
	@Resource
	private ConfigServiceImpl configService;
	@Resource
	private MemberServiceImpl memberService;
	@Resource
	private VerifyBatServiceImpl verifyBatService;
	
	
	@RequestMapping(value = { "/add_verify_bat" })
	public String addMembatWithoutOrder(Long id,Member member,MemBat memBat, Model model,HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("GET")){
			Member mem = memberService.findById(id);
			model.addAttribute("edit", mem);
			return "memberPage/verifyBat/add_verify_bat_detail";
		}else{
			try{
				Member mem = memberService.findById(id);
				Account account = accountService.findStoreById(mem.getStoreId());
				VerifyBat  verifyBat = new VerifyBat();
				Account account2 = (Account) request.getSession().getAttribute("account");
				
				verifyBat.setStatus("verifying");
				verifyBat.setStoreId(account.getAccount());
				verifyBat.setMemId(String.valueOf(mem.getId()));
				verifyBat.setBatType(memBat.getBatteryType());
				verifyBat.setBatNum(String.valueOf(memBat.getBatteryQty()));
				verifyBat.setApplyId(account2.getAccount());
				verifyBat.setApplyDate(new java.sql.Date(System.currentTimeMillis()));
							
				verifyBatService.addVerifyBat(verifyBat);
				
				return ajaxReturn(response,null,"提交申请成功",1);
			}catch(Exception e){
				return ajaxReturn(response,null,"提交申请失败",0);
			}
		}
	}
	
	@RequestMapping(value="change_verify_status")
	public String changeVerifyStatus(String status,Model model, HttpServletRequest request,HttpServletResponse response){
		if(status.equals("on")){
			configService.changeMembatVerifySwitch("1");
			return ajaxReturn(response,null,"已开启",1);
		}
		else if(status.equals("off")){
			configService.changeMembatVerifySwitch("0");
			return ajaxReturn(response,null,"已关闭",1);
		}
		else {
			return ajaxReturn(response,null,"非法操作",0);
		}
	}
	
	@RequestMapping(value="verify")
	public String verify(String id,String flag, Model model, HttpServletRequest request,HttpServletResponse response){
		try{
			if(flag.equals("pass")){
				
				VerifyBat verifyBat = verifyBatService.findById(Long.valueOf(id));
				Account account = (Account) request.getSession().getAttribute("account");
				Date nowtime =new java.sql.Date(System.currentTimeMillis());
				
				verifyBat.setStatus("pass");
				verifyBat.setVerifyId(account.getAccount());
				verifyBat.setVerifyDate(nowtime);
				
				verifyBatService.update(verifyBat);

				MemBat memBat =new MemBat();

				memBat.setMemberId(verifyBat.getMemId());
				memBat.setStoreNo(verifyBat.getStoreId());
				memBat.setBatteryType(verifyBat.getBatType());
				memBat.setBatteryQty(Long.valueOf(verifyBat.getBatNum()));
				memBat.setBatteryReQty(Long.valueOf(verifyBat.getBatNum()));
				memBat.setDate(nowtime);			
				memBat.setStatus("full");
				
				memBatService.addMenBat(memBat);
				
				return ajaxReturn(response,null,"审核通过",1);
			}else{
				
				VerifyBat verifyBat = verifyBatService.findById(Long.valueOf(id));
				Account account = (Account) request.getSession().getAttribute("account");
				Date nowtime =new java.sql.Date(System.currentTimeMillis());
				
				verifyBat.setStatus("reject");
				verifyBat.setVerifyId(account.getAccount());
				verifyBat.setVerifyDate(nowtime);
				
				verifyBatService.update(verifyBat);
				
				return ajaxReturn(response,null,"审核不通过",1);
			}
								
		}catch(Exception e){
			return ajaxReturn(response,null,"审核失败",0);
		}
	}
	
	@RequestMapping(value="verifying_list")
	public String verifyList(String memberName, String memberNo, String memberPhone, String storeName, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("POST")) {
			String page = request.getParameter("page");
			String rowNumber = request.getParameter("rows");
	
			return ajaxReturn(response, verifyBatService.getVerifyingList(memberName, memberNo, memberPhone, storeName, page, rowNumber));
		} else {
	
			String switchstatus = configService.getMembatVerifySwitchStatus();
			if(switchstatus.equals("1")){
				switchstatus="true";
			}else{
				switchstatus="false";
			}
			model.addAttribute("switchstatus", switchstatus);
			return "adminPage/verifyBat/verifying_list";
		}
	}

	@RequestMapping(value="verify_list")
	public String verifyListQuery(String memberName, String memberNo, String memberPhone, String storeNo,String statusCheck, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		Account account = (Account) request.getSession().getAttribute("account");
		String isAllStore = account.getRole().getAllStore();
		
		if (request.getMethod().equals("POST")) {
			String page = request.getParameter("page");
			String rowNumber = request.getParameter("rows");
			if(!isAllStore.equals(GlobalParam.ALL_STORE_YES)){
				storeNo=account.getAccount();
			}
			return ajaxReturn(response, verifyBatService.getVerifyBatList(memberName, memberNo, memberPhone,storeNo,statusCheck, page, rowNumber));
		} else {
	
			
			model.addAttribute("isadmin", false);
			if (isAllStore.equals(GlobalParam.ALL_STORE_YES)) {
				model.addAttribute("isadmin", true);
			}
			return "adminPage/verifyBat/verify_list";
		}
	}

}
