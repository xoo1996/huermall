package cn.edu.hdu.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.zlzkj.app.util.LogStringBuilder;
import com.zlzkj.core.base.BaseController;
import com.zlzkj.core.sql.Row;

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.Code.UrlCode;
import cn.edu.hdu.config.webSend;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.BatEvent;
import cn.edu.hdu.pojo.Battery;
import cn.edu.hdu.pojo.MemBat;
import cn.edu.hdu.pojo.SuperChangeHistory;
import cn.edu.hdu.service.AccountService;
import cn.edu.hdu.service.SuperChangeHistoryService;
import cn.edu.hdu.service.impl.BatEventServiceImpl;
import cn.edu.hdu.service.impl.BatteryServiceImpl;
import cn.edu.hdu.service.impl.GlobalCfgServiceImpl;
import cn.edu.hdu.service.impl.MemBatServiceImpl;
import cn.edu.hdu.service.impl.MemberServiceImpl;
import cn.edu.hdu.service.impl.StoreServiceImpl;
import cn.edu.hdu.util.DataUtil;
import cn.edu.hdu.util.ExcelTransport;
import cn.edu.hdu.util.FileUtil;

@Controller
@RequestMapping(value="superchange")
public class SuperChangeController extends BaseController {
	
	private static Logger logger = Logger.getLogger(SuperChangeController.class);

	
	@Resource
	private LogStringBuilder lsb;
	
	@Resource 
	private SuperChangeHistoryService superChangeHistoryService;
	@Resource
	private BatteryServiceImpl batteryservice;
	@Resource
	private MemBatServiceImpl memBatService;
	@Resource
	private BatEventServiceImpl batEventService;
	@Resource
	private StoreServiceImpl storeService;
	@Resource
	private AccountService accountService;
	@Resource
	private MemberServiceImpl memberService;
	@Resource
	private GlobalCfgServiceImpl globalCfgService;
	
	@RequestMapping(value="/superchangehistorylist")
	public String superChangeHistoryList(String storeName,String memberName,String phoneNum,String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("POST")){
			//参数记录
			logger.info(lsb.method()
					+ lsb.append("storeName", storeName) 
					+ lsb.append("memberName", memberName)
					+ lsb.append("phoneNum", phoneNum)
					+ lsb.append("startTime", startTime)
					+ lsb.append("endTime", endTime));
			
			int rows = DataUtil.getPage(request.getParameter("rows"));
			int page = DataUtil.getPage(request.getParameter("page"));
			return ajaxReturn(response,superChangeHistoryService.getSuperChangeHistoryList(storeName, memberName,phoneNum,startTime,endTime,page,rows));
		}else{
			return "adminPage/superchange/super_change_history_list";
		}
	}
	
	/**
	 *超级修改batevent列表
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/super_change_bat_event" })
	public String superChangeBatEvent(String memberName, String memberNo, String memberPhone, String storeName, Model model,
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
			return ajaxReturn(response, batEventService.superGetBatEventList(null, memberName, memberNo, memberPhone,
					storeName, page, rowNumber, accId));
		} else {
			Account account = (Account) request.getSession().getAttribute("account");
			String isAllStore = account.getRole().getAllStore();
			model.addAttribute("isadmin", false);
			if (isAllStore.equals(GlobalParam.ALL_STORE_YES)) {
				model.addAttribute("isadmin", true);
			}
			return "adminPage/superchange/super_change_bat_event";
		}
	}
	
	/**
	 *超级修改membat列表
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/super_change_mem_bat" })
	public String superChangeMemBat(String memberName, String memberNo, String memberPhone, String storeName, Model model,
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
			return ajaxReturn(response, memBatService.superGetBatEventListNotGetBatStock(memberName, memberNo, memberPhone, storeName, page, rowNumber));
		} else {
			Account account = (Account) request.getSession().getAttribute("account");
			String isAllStore = account.getRole().getAllStore();
			model.addAttribute("isadmin", false);
			if (isAllStore.equals(GlobalParam.ALL_STORE_YES)) {
				model.addAttribute("isadmin", true);
			}
			return "adminPage/superchange/super_change_mem_bat";
		}
	}
	
	private String temp = "";
	private Long temGetNum=0L;
	@RequestMapping(value="superchangenum")
	public String superChangeNum(String membatId,String id,BatEvent reBatEvent,MemBat reMemBat,String memnm,String memberno,Model model,HttpServletRequest request, HttpServletResponse response){
		if(membatId!=null){
			temp=membatId;
		}
		if(request.getMethod().equals("GET")){
			BatEvent batEvent = batEventService.findById(Long.parseLong(id));
			MemBat memBat = memBatService.findMemBatById(Long.parseLong(membatId));
			Battery battery = batteryservice.getBatteryByType(memBat.getBatteryType());
			model.addAttribute("edit1", batEvent);
			model.addAttribute("edit2",memBat);
			model.addAttribute("edit3",battery);
			model.addAttribute("memnm",memberService.findNameById(Long.parseLong(memBat.getMemberId())));
			model.addAttribute("memberno", memberService.findMemberNoById(Long.parseLong(memBat.getMemberId())));
			return "adminPage/superchange/superchangenum";
		}else{
			try{
				if(reMemBat.getBatteryQty()%1 != 0 || reMemBat.getBatteryQty()<0 ){				
						return ajaxReturn(response,null,"输入赠送数量有误",0);
				}
				if(reBatEvent.getGetBatNum()%1 != 0 || reBatEvent.getGetBatNum()<0 ){				
					return ajaxReturn(response,null,"输入领取数量有误",0);
				}
				if(reMemBat.getBatteryReQty()%1 != 0 || reMemBat.getBatteryReQty()<0 ){				
					return ajaxReturn(response,null,"输入剩余数量有误",0);
				}
				
				//以下为存储历史记录阶段
				BatEvent be =batEventService.findById(reBatEvent.getId());
				temGetNum=be.getGetBatNum();
				MemBat temmemBat = memBatService.findMemBatById(Long.parseLong(temp));
				Battery tembattery = batteryservice.getBatteryByType(be.getBatteryType());
				String operation = superChangeHistoryService.conCatOperation(tembattery.getName(), tembattery.getName(), String.valueOf(temmemBat.getBatteryQty()), String.valueOf(reMemBat.getBatteryQty()), String.valueOf(temmemBat.getBatteryReQty()), String.valueOf(reMemBat.getBatteryReQty()), String.valueOf(be.getGetBatNum()),String.valueOf(reBatEvent.getGetBatNum()));
				superChangeHistoryService.record(be.getInStoreNo(), be.getMemberId(), 
						be.getMemBatId(),String.valueOf( be.getId()), 
						operation,
						be.getBatteryType(), be.getBatteryType(), 
						String.valueOf(temmemBat.getBatteryQty()), String.valueOf(reMemBat.getBatteryQty()), 
						String.valueOf(temmemBat.getBatteryReQty()), String.valueOf(reMemBat.getBatteryReQty()), 
						String.valueOf(be.getGetBatNum()),String.valueOf(reBatEvent.getGetBatNum()));
						
				//以下为修改记录阶段
				

				
				//更新当前batevent
				reBatEvent.setMemBatId(temp);
				batEventService.updataBatEventNUM(reBatEvent);
//				Battery battery = batteryservice.getBatteryByName(rebattery.getName());
				
				//更新对应的membat
				reMemBat.setId(Long.parseLong(temp));
				memBatService.updateMemBatNUM(reMemBat);
				
				Object[] objects1 =  new Object[] {reBatEvent.getGetBatNum()-temGetNum,be.getGetStoreNo(),be.getBatteryType()};
				Object[] objects2 =  new Object[] {reBatEvent.getGetBatNum(),reBatEvent.getId()};
				try {
					webSend.sendService(objects1, UrlCode.URL, "battChange");
					webSend.sendService(objects2, UrlCode.URL, "batEventChange");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				
				return ajaxReturn(response,null,"编辑成功",1);
			}catch(Exception e){
				return ajaxReturn(response,null,"编辑失败",0);
			}
		}
	}
	
	@RequestMapping(value="superchangetype")
	public String superChangeType(String id,MemBat reMemBat,Battery rebattery,Model model,HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			MemBat memBat = memBatService.findMemBatById(Long.parseLong(id));
			Battery battery = batteryservice.getBatteryByType(memBat.getBatteryType());
			model.addAttribute("edit2",memBat);
			model.addAttribute("edit3",battery);
			model.addAttribute("memnm",memberService.findNameById(Long.parseLong(memBat.getMemberId())));
			model.addAttribute("memberno", memberService.findMemberNoById(Long.parseLong(memBat.getMemberId())));
			return "adminPage/superchange/superchangetype";
		}else{
			try{
				
				//以下为存储历史记录阶段
				List<BatEvent> be =batEventService.getBatEventList(String.valueOf(reMemBat.getId()));
				if(be.size()!=0)
					return ajaxReturn(response,null,"已领电池不能修改型号",0);
				MemBat temmemBat = memBatService.findMemBatById(reMemBat.getId());
				Battery oldbattery = batteryservice.getBatteryByType(temmemBat.getBatteryType());
				Battery newbattery = batteryservice.getBatteryByType(rebattery.getType());
				String operation = superChangeHistoryService.conCatOperation(oldbattery.getName(), newbattery.getName(), String.valueOf(temmemBat.getBatteryQty()), String.valueOf(temmemBat.getBatteryQty()), String.valueOf(temmemBat.getBatteryReQty()), String.valueOf(temmemBat.getBatteryReQty()), "","");
				superChangeHistoryService.record(temmemBat.getStoreNo(), temmemBat.getMemberId(), 
						String.valueOf(reMemBat.getId()),"--", 
						operation,
						temmemBat.getBatteryType(), rebattery.getType(), 
						String.valueOf(temmemBat.getBatteryQty()), String.valueOf(temmemBat.getBatteryQty()), 
						String.valueOf(temmemBat.getBatteryReQty()), String.valueOf(temmemBat.getBatteryReQty()), 
						"--","--");
						
				
				//更新对应的membat
				reMemBat.setBatteryType(rebattery.getType());
				memBatService.updateMemBatType(reMemBat);					
				
				return ajaxReturn(response,null,"编辑成功",1);
			}catch(Exception e){
				return ajaxReturn(response,null,"编辑失败",0);
			}
		}
	}
	
	/**
	 * 导出超级修改历史记录
	 * @param storeName
	 * @param memberName
	 * @param phoneNum
	 * @param startTime
	 * @param endTime
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "export_superchangehistory")
	public void exportSuperChangeHistory(@RequestParam(required = false) String storeName, @RequestParam(required = false) String memberName,
			@RequestParam(required = false) String phoneNum,@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String fileName = "超级修改历史记录导出.xls";
		String path = request.getSession().getServletContext().getRealPath("");
		Account account=(Account)request.getSession().getAttribute("account");
		String filePath = path + "\\export\\" + account.getName() + "\\" + fileName;
		String sheetName = fileName;
		String titleStr = "编号,门店名称,会员名称,赠送编号,领取编号,操作详情,日期";
		String keyStr = "id,storeName,memberName,memBatId,batEventId,operation,handleDate";
		//List<JSONObject> appUserList = appUserService.selectAppUserListByPhoneAndCreateTime(phone, startTime, endTime);
		List<Object[]> superChangeHistoryList = superChangeHistoryService.getSuperChangeHistoryList(storeName, memberName, phoneNum, startTime, endTime);

		// 在服务器端创建excel文件
		ExcelTransport.createMainExcelFile(filePath, fileName, sheetName, titleStr, keyStr, superChangeHistoryList);
		// 创建下载
		FileUtil.downloadLocal(response, fileName, filePath);
		// 下载
		FileUtil.deleteFileByFilePath(filePath);
	}
	
	@RequestMapping(value="del_membat")
	public String delMemBatSuperChange(String id, Model model, HttpServletRequest request,HttpServletResponse response){
		try{
			System.out.println(id);
			
			MemBat memBat = memBatService.findById(Long.parseLong(id));
			
			if(memBat.getBatteryQty() != memBat.getBatteryReQty())
				return ajaxReturn(response,null,"禁止删除领取过电池的记录",2);
								
			SuperChangeHistory superChangeHistory = new SuperChangeHistory();
			
			String operation = "删除";
			if(memBat.getOrderId() !=null){
				operation += "订单号为："+memBat.getOrderId();
			}else{
				operation += "收费号为："+memBat.getChargeId();
			}
			operation += " 收费时间为：" +memBat.getDate()+"的记录";
			
			superChangeHistory.setStoreId(memBat.getStoreNo());
			superChangeHistory.setMemberId(memBat.getMemberId());
			superChangeHistory.setMemBatId(String.valueOf(memBat.getId()));
			superChangeHistory.setBatEventId("--");
			superChangeHistory.setOperation(operation);
			superChangeHistory.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
			superChangeHistory.setOldBatType(memBat.getBatteryType());
			superChangeHistory.setNewBatType("--");
			superChangeHistory.setOldQty(String.valueOf(memBat.getBatteryQty()));
			superChangeHistory.setNewQty("--");
			superChangeHistory.setOldReQty(String.valueOf(memBat.getBatteryReQty()));
			superChangeHistory.setNewReQty("--");
			superChangeHistory.setOldGetNum("--");
			superChangeHistory.setNewGetNum("--");
			
			superChangeHistoryService.recordDelMembat(superChangeHistory);
			
			memBatService.delMenBat(Long.parseLong(id));
			
			return ajaxReturn(response,null,"删除成功",1);
		}catch(Exception e){
			return ajaxReturn(response,null,"删除失败",0);
		}
	}
	
}
