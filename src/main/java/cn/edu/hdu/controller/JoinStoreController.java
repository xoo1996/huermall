package cn.edu.hdu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zlzkj.app.util.LogStringBuilder;
import com.zlzkj.core.base.BaseController;

import cn.edu.hdu.pojo.Account;
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
@RequestMapping(value="joinstore")
public class JoinStoreController extends BaseController {
private static Logger logger = Logger.getLogger(JoinStoreController.class);

	
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

	
	@RequestMapping(value="/joinstoregetbathistory")
	public String superChangeHistoryList(String storeName,String storeNo,String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("POST")){
			//参数记录
			logger.info(lsb.method()
					+ lsb.append("storeName", storeName) 
					+ lsb.append("storeNo", storeNo));
			
			int rows = DataUtil.getPage(request.getParameter("rows"));
			int page = DataUtil.getPage(request.getParameter("page"));
			return ajaxReturn(response,batEventService.getJoinStoreGetBatHistoryList(storeName,storeNo,startTime,endTime,page,rows));
		}else{
			return "adminPage/joinstore/joinstorehistory";
		}
	}
	
	@RequestMapping(value = "export_joinstorehistory")
	public void exportJoinStoreHistory(@RequestParam(required = false) String storeName, @RequestParam(required = false) String storeNo,
			@RequestParam(required = false) String startTime,@RequestParam(required = false) String endTime,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {

		String fileName = "加盟店领取记录导出.xls";
		String path = request.getSession().getServletContext().getRealPath("");
		Account account=(Account)request.getSession().getAttribute("account");
		String filePath = path + "\\export\\" + account.getName() + "\\" + fileName;
		String sheetName = fileName;
		String titleStr = "编号,加盟店名称 ,会员名称 ,会员所属店,电池名称,领取数量,日期 ";
		String keyStr = "id,storeName,storeNo,getStoreName,batName,getNum,handleDate";
		
		List<Object[]> joinStoreHistory = batEventService.getJoinStoreGetBatHistoryList(storeName, storeNo, startTime, endTime);

		// 在服务器端创建excel文件
		ExcelTransport.createMainExcelFile(filePath, fileName, sheetName, titleStr, keyStr, joinStoreHistory);
		// 创建下载
		FileUtil.downloadLocal(response, fileName, filePath);
		// 下载
		FileUtil.deleteFileByFilePath(filePath);
	}
}
