package cn.edu.hdu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zlzkj.core.base.BaseController;

import cn.edu.hdu.Code.ScoreEventType;
import cn.edu.hdu.Code.UrlCode;
import cn.edu.hdu.config.webSend;
import cn.edu.hdu.pojo.BatEvent;
import cn.edu.hdu.pojo.Battery;
import cn.edu.hdu.pojo.MemBat;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.service.BatteryService;
import cn.edu.hdu.service.MemBatService;
import cn.edu.hdu.service.SuperChangeHistoryService;
import cn.edu.hdu.service.impl.BatEventServiceImpl;
import cn.edu.hdu.service.impl.GlobalCfgServiceImpl;
import cn.edu.hdu.service.impl.MemberServiceImpl;
//新增
@Controller
@RequestMapping(value="bat_event")
public class BatEventController extends BaseController{
	
	@Resource
	private MemberServiceImpl memberService;
	
	@Resource
	private BatEventServiceImpl batEventService;
	
	@Resource
	private GlobalCfgServiceImpl globalCfgService;
	
	@Resource
	private MemBatService memBatService;
	
	@Resource
	private BatteryService batteryservice;
	
	@Resource 
	private SuperChangeHistoryService superChangeHistoryService;
	
	/**
	 * 输出电池领取事件
	 * @param memId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="bat_event")
	public String scoreEvent(String memberNo,String id,
			Model model,HttpServletRequest request, HttpServletResponse response){
		String method = request.getMethod();
		if(method.equals("POST")){
			if(id == null){
				return ajaxReturn(response,null,"会员编号为空",0);
			}
			String page = request.getParameter("page");
			String rowNumber = request.getParameter("rows");
			return ajaxReturn(response,batEventService.getBatEventList(id,null, null,null,null, page, rowNumber,null));
		}else{
			model.addAttribute("id",id );
			return "adminPage/member/bat_event";
		}
	}
	

}
