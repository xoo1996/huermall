package cn.edu.hdu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zlzkj.app.util.LogStringBuilder;
import com.zlzkj.core.base.BaseController;

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.Code.UrlCode;
import cn.edu.hdu.config.webSend;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.BatEvent;
import cn.edu.hdu.pojo.Battery;
import cn.edu.hdu.pojo.MemBat;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.pojo.Store;
import cn.edu.hdu.pojo.SuperChangeHistory;
import cn.edu.hdu.printpdf.PrintGetEviPdf;
import cn.edu.hdu.service.AccountService;
import cn.edu.hdu.service.BatteryService;
import cn.edu.hdu.service.StoreService;
import cn.edu.hdu.service.impl.BatEventServiceImpl;
import cn.edu.hdu.service.impl.BatteryServiceImpl;
import cn.edu.hdu.service.impl.ConfigServiceImpl;
import cn.edu.hdu.service.impl.GiftServiceImpl;
import cn.edu.hdu.service.impl.GlobalCfgServiceImpl;
import cn.edu.hdu.service.impl.MemBatServiceImpl;
import cn.edu.hdu.service.impl.MemberServiceImpl;
import cn.edu.hdu.service.impl.ScoreServiceImpl;
import cn.edu.hdu.service.impl.StoreServiceImpl;
import cn.edu.hdu.util.DataUtil;
import cn.edu.hdu.util.FileUtil;
import cn.edu.hdu.util.IPUtil;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


import java.util.Map;

import org.junit.Test;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.layout.border.SolidBorder;
//新增
@Controller
@RequestMapping(value = "membat")
public class MemBatController extends BaseController {
	private static Logger logger = Logger.getLogger(MemberController.class);

	@Resource
	private LogStringBuilder lsb;
	@Resource
	private AccountService accountService;
	@Resource
	private MemberServiceImpl memberService;
	@Resource
	private GlobalCfgServiceImpl globalCfgService;
	@Resource
	private GiftServiceImpl giftService;
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

	/**
	 * 获取会员列表
	 * 
	 * @param memberName
	 * @param memberNo
	 * @param memberPhone
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/membatlist" })
	public String memBatList(String memberId, HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("POST")) {

			if ((memberId == null || memberId.equals(""))) {
				return null;
			}

			logger.info(lsb.append("memberId", memberId));
			Account account = (Account) request.getSession().getAttribute("account");
			int rows = DataUtil.getPage(request.getParameter("rows"));
			int page = DataUtil.getPage(request.getParameter("page"));

			return ajaxReturn(response, memBatService.getMemBatList(memberId, rows, page, account.getAccount()));

		} else {
			return "adminPage/member/memberlist";
		}
	}

	/*
	 * @RequestMapping(value={"/membatlist_admin"}) public String
	 * memBatListAdmin(String memberName,String memberNo,String
	 * memberPhone,String storeName, Model model,HttpServletRequest request,
	 * HttpServletResponse response) { if(request.getMethod().equals("POST")){
	 * //参数记录 logger.info(lsb.method() + lsb.append("memberName", memberName) +
	 * lsb.append("memberNo", memberNo) + lsb.append("memberPhone",
	 * memberPhone)); int rows = DataUtil.getPage(request.getParameter("rows"));
	 * int page = DataUtil.getPage(request.getParameter("page")); return
	 * ajaxReturn(response,memBatService.getMemBatListAdmin(memberName,
	 * memberNo, memberPhone,storeName,rows,page)); }else{
	 * model.addAttribute("store", accountService.getAjaxStore()); return
	 * "adminPage/member/memberlist_admin"; } }
	 */

	/**
	 * 电池领取
	 * 
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "get_bat")
	public String getBat(Long id, Long getBatNum, String batteryType, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getMethod().equals("GET")) {
			model.addAttribute("memberId", id);
			return "adminPage/member/get_bat";
		} else {
			try {
				MemBat memBat = memBatService.findMemBatById(id);
				if (memBat.getBatteryReQty() > 0) {
					int rows = DataUtil.getPage(request.getParameter("rows"));
					int page = DataUtil.getPage(request.getParameter("page"));

					Account account = (Account) request.getSession().getAttribute("account");
					batEventService.record(memBat.getMemberId(), account.getAccount(), memBat.getStoreNo(),memBat.getBatteryType(), getBatNum,String.valueOf(id));
					// System.out.println(account.getAccount()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					
					//获取record那条记录的id
					String batEvenetThisId = batEventService.getBatEventCurrentId(memBat.getMemberId(), account.getAccount(),  memBat.getStoreNo(), memBat.getBatteryType(), getBatNum, String.valueOf(id));
					batEventService.sendToOldSystem(memBat.getMemberId(), account.getAccount(),  memBat.getStoreNo(), memBat.getBatteryType(), getBatNum, String.valueOf(id),batEvenetThisId);
					memBatService.getBat(id, getBatNum);
					return ajaxReturn(response,
							memBatService.getMemBatList(memBat.getMemberId(), rows, page, account.getAccount()));
				} else {
					return ajaxReturn(response, null, "电池数量不足", 0);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ajaxReturn(response, null, "系统错误", 0);
			}
		}
	}

	
	@RequestMapping(value = "print_pdf")
	public String printPdf(Long id, Long getBatNum,HttpServletRequest request,HttpServletResponse response) {
		try {
			MemBat memBat = memBatService.findMemBatById(id);
			Account account = (Account) request.getSession().getAttribute("account");
			Store store = storeService.findStoreByGctId(account.getAccount());
			Battery battery = battService.getBatteryByType(memBat.getBatteryType());
			
			//姓名
			String CusName = memberService.findNameById(Long.parseLong(memBat.getMemberId()));
			//店铺名称
			String GctStoreNm = store.getGctNm();
			//领取时间
			String NowTime = new java.sql.Date(System.currentTimeMillis()).toString();
			//电池名称
			String BatNm = battery.getName();
			//电池编号
			//String BatteryType = memBat.getBatteryType();
			//电池数量
			String BatteryQty = String.valueOf(memBat.getBatteryQty());
			//累计领取数量
			String TotalGetBatQty = String.valueOf(memBat.getBatteryQty()- memBat.getBatteryReQty()-getBatNum);
			//领取电池数量
			String GetBatQty = String.valueOf(getBatNum);
			//剩余电池数量
			String BatteryReQty = String.valueOf(memBat.getBatteryReQty());
			//店铺地址
			String GctAddr = store.getGctAddr();
			//店铺电话 
			String GctTel = store.getGctTel();
			//模板名称
			String TempletPdfNm = "huierbat";
			//输出位置
			String PdfFilePath ="\\docs\\BatEvi";
			String PdfFilePath2 ="/docs/BatEvi";
			//输出名称
			String OutPdfPreNm ="BatEvi";
			
			String randnm = PrintGetEviPdf.printGetEviPdf(CusName,GctStoreNm,NowTime,BatNm,BatteryQty,TotalGetBatQty,GetBatQty,BatteryReQty,GctAddr,GctTel,TempletPdfNm,PdfFilePath,OutPdfPreNm,request);
			
			//获得相应的端口和ip
			String myport = String.valueOf(request.getLocalPort());
			System.out.println(myport);
			//String myip = InetAddress.getLocalHost().getHostAddress();
			//String myip = IPUtil.getMyIp("localip");
			String myip ="10.0.0.249";
			System.out.println(myip);
			
			return ajaxReturn(response, null, "http://" + myip + ":" + myport + PdfFilePath2+"/"+OutPdfPreNm + randnm + ".pdf",1);


		} catch (Exception e) {
			e.printStackTrace();
			return ajaxReturn(response, null, "失败", 0);
		}

	}
	
	@RequestMapping(value = "reprint_pdf")
	public String rePrintPdf(Long id,HttpServletRequest request,HttpServletResponse response) {
		try {
			MemBat memBat = memBatService.findMemBatById(id);
			Account account = (Account) request.getSession().getAttribute("account");
			Store store = storeService.findStoreByGctId(account.getAccount());
			Battery battery = battService.getBatteryByType(memBat.getBatteryType());
			
			//姓名
			String CusName = memberService.findNameById(Long.parseLong(memBat.getMemberId()));
			//店铺名称
			String GctStoreNm = store.getGctNm();
			//领取时间
			String NowTime = new java.sql.Date(System.currentTimeMillis()).toString();
			//电池名称
			String BatNm = battery.getName();
			//电池编号
			String BatteryType = memBat.getBatteryType();
			//电池数量
			String BatteryQty = String.valueOf(memBat.getBatteryQty());
			//已领取电池数量
			String GetBatQty = String.valueOf(memBat.getBatteryQty()- memBat.getBatteryReQty());
			//剩余电池数量
			String BatteryReQty = String.valueOf(memBat.getBatteryReQty());
			//店铺地址
			String GctAddr = store.getGctAddr();
			//店铺电话 
			String GctTel = store.getGctTel();
			//模板名称
			String TempletPdfNm = "rehuierbat";
			//输出位置
			String PdfFilePath ="\\docs\\BatEvi";
			String PdfFilePath2 ="/docs/BatEvi";
			//输出名称
			String OutPdfPreNm ="ReBatEvi";
			
			String randnm = PrintGetEviPdf.printReGetEviPdf(CusName,GctStoreNm,NowTime,BatNm,BatteryType,BatteryQty,GetBatQty,BatteryReQty,GctAddr,GctTel,TempletPdfNm,PdfFilePath,OutPdfPreNm,request);
			
			//获得相应的端口和ip
			String myport = String.valueOf(request.getLocalPort());
			//String myip = InetAddress.getLocalHost().getHostAddress();
			//String myip = IPUtil.getMyIp("localip");
			String myip ="10.0.0.249";
			
			return ajaxReturn(response, null, "http://" + myip + ":" + myport + PdfFilePath2+"/"+OutPdfPreNm + randnm + ".pdf",1);

		} catch (Exception e) {
			e.printStackTrace();
			return ajaxReturn(response, null, "失败", 0);
		}

	}
	
	

	/**
	 * 积分事件列表
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/bat_event_list" })
	public String scoreEventlist(String memberName, String memberNo, String memberPhone, String storeName, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("POST")) {
			String page = request.getParameter("page");
			String rowNumber = request.getParameter("rows");
			Account account = (Account) request.getSession().getAttribute("account");
			String accId = account.getAccount();
			String isAllStore = account.getRole().getAllStore();
			// log
			logger.info(lsb.method() + lsb.append("memberName", memberName) + lsb.append("memberNo", memberNo)
					+ lsb.append("memberPhone", memberPhone) + lsb.append("storeName", storeName)
					+ lsb.append("accId", accId) + lsb.append("isAllStore", isAllStore));

			// 总部或超级管理员账号，查询时不需要操作账户限制
			if (isAllStore.equals(GlobalParam.ALL_STORE_YES))
				accId = null;
			return ajaxReturn(response, batEventService.getBatEventList(null, memberName, memberNo, memberPhone,
					storeName, page, rowNumber, accId));
		} else {
			Account account = (Account) request.getSession().getAttribute("account");
			String isAllStore = account.getRole().getAllStore();
			model.addAttribute("isadmin", false);
			if (isAllStore.equals(GlobalParam.ALL_STORE_YES)) {
				model.addAttribute("isadmin", true);
			}
			return "adminPage/member/bat_event_list";
		}
	}
	
}
