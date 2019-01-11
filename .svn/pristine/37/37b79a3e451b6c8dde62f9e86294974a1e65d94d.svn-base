package cn.edu.hdu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.Code.UploadCode;
import cn.edu.hdu.dto.PictureDirDto;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.Menu;
import cn.edu.hdu.service.FileService;
import cn.edu.hdu.service.MenuService;
import cn.edu.hdu.service.RRoleMenuService;
import cn.edu.hdu.service.impl.ActivityServiceImpl;
import cn.edu.hdu.service.impl.GlobalCfgServiceImpl;

@Controller
public class IndexController {
//	private static Logger logger = Logger.getLogger(IndexController.class);
	@Resource
	private MenuService menuService;
	@Resource
	private FileService fileService;
	@Resource
	private RRoleMenuService roleMenuService;
	@Resource
	private ActivityServiceImpl activityService;
	@Resource
	private GlobalCfgServiceImpl gcService;
	
	@RequestMapping(value="/")
	public String index(Model model,HttpServletRequest request, HttpServletResponse response){
		Account account = (Account)request.getSession().getAttribute("account");
		Long roleId = account.getRole().getId();
		boolean isZongbu = false;
		if(roleId.equals(GlobalParam.ZONGBU_ROLE_ID)){
			isZongbu = true;
		}
		List<Menu> m = null;
		if(roleId.equals(GlobalParam.ADMIN_ROLE_ID)){
			m = menuService.getLeftMenu();
		}else{
			m  = menuService.getLeftMenu(roleId);
		}
		model.addAttribute("leftMenuList",m);
		model.addAttribute("isZongbu",isZongbu);
		return "adminPage/public/index";
	}
	
	@RequestMapping(value="/member_index")
	public String memberIndex(Model model,HttpServletRequest request, HttpServletResponse response){
		List<PictureDirDto> imgNames = fileService.getImgDir(UploadCode.MODULE_LUNBO);
		model.addAttribute("imgNames",imgNames );
		model.addAttribute("module",UploadCode.MODULE_LUNBO );
		model.addAttribute("activity",activityService.getReleasedActivity());
		request.getSession().setAttribute("globalCfg", gcService.findById(0L));;
		
		/*List<Menu> m = menuService.getLeftMenu();
		model.addAttribute("leftMenuList",m);*/
		return "memberPage/public/index";
	}
}
