package cn.edu.hdu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.hdu.Code.ReturnCode;
import cn.edu.hdu.Code.UrlCode;
import cn.edu.hdu.config.ReturnMsg;
import cn.edu.hdu.config.webSend;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.Battery;
import cn.edu.hdu.pojo.GlobalCfg;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.service.AccountService;
import cn.edu.hdu.service.BatteryService;
import cn.edu.hdu.service.GlobalCfgService;
import cn.edu.hdu.service.impl.BatteryServiceImpl;
import cn.edu.hdu.service.impl.GiftServiceImpl;
import cn.edu.hdu.service.impl.GlobalCfgServiceImpl;
import cn.edu.hdu.service.impl.MemberServiceImpl;
import cn.edu.hdu.service.impl.ScoreServiceImpl;
import cn.edu.hdu.util.DataUtil;

import com.alibaba.fastjson.JSON;
import com.zlzkj.core.base.BaseController;
import com.zlzkj.core.sql.Row;

@Controller
@RequestMapping(value="mobile")
public class MobileController extends BaseController{

	@Resource
	private AccountService accountService;
	
	@RequestMapping(value="member_info")
	public String getMemberInfo(HttpServletRequest request, HttpServletResponse response){
		return "memberPage/mobile/test";
	}
	
	@RequestMapping(value="member_info_json")
	public String getMemberInfoJSON(HttpServletRequest request, HttpServletResponse response){
		Row row = new Row();
		row.put("name","测试姓名");//姓名
		row.put("coin", "100");//惠耳币
		row.put("memberNo", "12138");//会员编号
		row.put("score", "10000");
		row.put("waitScore", "10000");
		System.out.print(JSON.toJSONString(row));
		return ajaxReturn(response, row);
		
	}
	

}
