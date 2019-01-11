package cn.edu.hdu.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.hdu.Code.UploadCode;
import cn.edu.hdu.Code.VerifyCode;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.Battery;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.pojo.PictureDir;
import cn.edu.hdu.service.AccountService;
import cn.edu.hdu.service.BatteryService;
import cn.edu.hdu.service.FileService;
import cn.edu.hdu.service.MemberService;
import cn.edu.hdu.service.PublicService;
import cn.edu.hdu.service.impl.ActivityServiceImpl;
import cn.edu.hdu.service.impl.FileServiceImpl;
import cn.edu.hdu.service.impl.GiftServiceImpl;
import cn.edu.hdu.service.impl.RoleServiceImpl;
import cn.edu.hdu.util.FileUtil;
import cn.edu.hdu.util.StrUtil;

import com.zlzkj.core.base.BaseController;
import com.zlzkj.core.sql.Row;

@Controller
@RequestMapping(value="public")
public class PublicController extends BaseController{
//	private static Logger logger = Logger.getLogger(PublicController.class);
	@Resource
	private AccountService accountService;
	@Resource
	private FileServiceImpl fileService;
	@Resource
	private MemberService memberService;
	@Resource
	private RoleServiceImpl roleService;
	@Resource
	private GiftServiceImpl giftService;
	@Resource
	private ActivityServiceImpl activityService;
	@Resource
	private PublicService publicService;
	@Resource
	private BatteryService battService;
	/**
	 * 后台：登陆
	 * @param account
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="login")
	public String adminLogin(String account, String password,HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/public/login";
		}else{
			//得到验证结果
			Account acc = accountService.validate(account, password);
			if(acc != null){
				request.getSession().setAttribute("account", acc);
				return ajaxReturn(response,null,"登录成功",1);
			}else{
				return ajaxReturn(response,null,"账号或密码错误",0);
			}
		}
	}
	
	/**
	 * 后台：登陆
	 * @param account
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="memlogin")
	public String memberLogin(String memberNo, String password,HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "memberPage/public/login";
		}else{
			//得到验证结果
			Member member = memberService.validate(memberNo, password);
			if(member != null){
				request.getSession().setAttribute("member", member);
				return ajaxReturn(response,null,"登录成功",1);
			}else{
				return ajaxReturn(response,null,"账号或密码错误",0);
			}
		}
	}
	
	/**
	 * 退出登录
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="logout/{type}")
	public String logout(@PathVariable String type,Model model, HttpServletRequest request, HttpServletResponse response){
		Enumeration<String> em = request.getSession().getAttributeNames();
		while(em.hasMoreElements()){   //移除session的属性，起到退出登录的效果
			request.getSession().removeAttribute(em.nextElement());
		}
		if(type.equals("admin")){
			return "redirect:/public/login"; //返回到登录页面
		}else{
			return "redirect:/member_index"; //返回到用户页面
		}
	}
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
/*	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request, HttpServletResponse response){
		
		List<String> imgNames = fileService.getImgPath(UploadFile.LUNBO);
		model.addAttribute("imgNames",imgNames );
		model.addAttribute("module",UploadFile.LUNBO );
		List<Menu> m = menuService.getLeftMenu();
		model.addAttribute("leftMenuList",m);
		return "public/index";
	}*/
	
	/**
	 * 后台：图片上传
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
/*	@RequestMapping(value="upload/{moduleName}")
	public String upLoadImg(Model m,
			@PathVariable String moduleName,@RequestParam("studentPhoto") MultipartFile[] files,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		for(int i = 0 ;i < files.length; i++){
			FileUtil.saveImg(file, );
	        fileService.saveImgPath(moduleName, fileName);
		}
        return ajaxReturn(response,null,"上传成功",1);
	}*/
	
	/**
	 * 图片上传
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="uploadjsp/{moduleName}")
	public String upLoadImgJsp(Model m,@PathVariable String moduleName) throws IOException{
		m.addAttribute("module", moduleName);
		return "adminPage/public/upload/" + moduleName;
	}
	
	/**
	 * 图片下载
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="special/img/{moduleName}/{id}")
	public void downloadImg(@PathVariable String moduleName,Model m,@PathVariable Long id,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		OutputStream os = response.getOutputStream();  
        try {  
        	PictureDir pd = fileService.findById(id);
            response.reset();  
            response.setHeader("Content-Disposition", "attachment; filename=" + StrUtil.getLastSubString(pd.getPath(), "/"));  
            response.setContentType("image/jpeg; charset=utf-8");  
            File file = new File(pd.getPath());
            if(file.exists()){
	            os.write(FileUtils.readFileToByteArray(file));  
	            os.flush();  
            }
        } finally {  
            if (os != null) {  
                os.close();  
            }  
        } 
	}
	
	/**
	 * 图片下载
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="blackImg/{size}")
	public void getBlackImg(@PathVariable String size,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String[] widthHeight = size.split("x");
		int width = Integer.parseInt(widthHeight[0]);
		int height = Integer.parseInt(widthHeight[1]);
		BufferedImage bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, width, height);
		OutputStream os = response.getOutputStream();  
        try {  
            response.setHeader("Content-Disposition", "attachment; filename=" + size);  
            response.setContentType("image/jpeg; charset=utf-8");  
            ImageIO.write(bi, "jpg", os);
        } finally {  
            if (os != null) {  
                os.close();  
            }  
        } 
	}
	
	/**
	 * 获取角色列表:下拉框
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getrole")
	public String getRoleList(HttpServletRequest request, HttpServletResponse response){
		List<Row> rows = roleService.getRoleOptionList();
		return ajaxReturn(response, rows);
	}
	
	/**
	 * 获取礼品列表:下拉框
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getgift")
	public String getGiftList(HttpServletRequest request, HttpServletResponse response){
		List<Row> rows = giftService.getGiftOptionList();
		return ajaxReturn(response, rows);
	}
	
	/**
	 * 获取电池列表:下拉框
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getbatt")
	public String getBattList(HttpServletRequest request, HttpServletResponse response){
		List<Row> rows = battService.getBattOptionList();
		return ajaxReturn(response, rows);
	}
	
	/**
	 * 获取活动列表:下拉框
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getactivity")
	public String getActivityList(HttpServletRequest request, HttpServletResponse response){
		List<Row> rows = activityService.getOptionList();
		return ajaxReturn(response, rows);
	}
	
	/**
	 * 获取大于小于列表:下拉框
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="math_symbol")
	public String getMathSymbolList(HttpServletRequest request, HttpServletResponse response){
		
		return ajaxReturn(response, publicService.getMathSymbolList());
	}
	
	/**
	 * 获取大于小于列表:下拉框
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="score_event_type")
	public String getScoreEventTypeList(HttpServletRequest request, HttpServletResponse response){
		
		return ajaxReturn(response, publicService.getScoreEventTypeList());
	}
	
	
	@RequestMapping(value="ajax_getstore")
	public String getAjaxStore(HttpServletRequest request, HttpServletResponse response){
		return ajaxReturn(response, accountService.getAjaxStore());
	}
	
	@RequestMapping(value="verify_invite_type")
	public String getVerifyInviteType(HttpServletRequest request, HttpServletResponse response){
		List<Row> rows = new ArrayList<Row>();
		Row row3 = new Row();
		row3.put("id", 0);
		row3.put("text", "全部类型");
		rows.add(row3);
		Row row = new Row();
		row.put("id", VerifyCode.WAITING_VERIFY);
		row.put("text", VerifyCode.getStr(VerifyCode.WAITING_VERIFY));
		rows.add(row);
		Row row1 = new Row();
		row1.put("id", VerifyCode.PASS);
		row1.put("text", VerifyCode.getStr(VerifyCode.PASS));
		rows.add(row1);
		Row row2 = new Row();
		row2.put("id", VerifyCode.REFUSE);
		row2.put("text", VerifyCode.getStr(VerifyCode.REFUSE));
		rows.add(row2);
		return ajaxReturn(response, rows);
	}
	
	@RequestMapping(value="verify_store_type")
	public String getVerifyStoreType(HttpServletRequest request, HttpServletResponse response){
		List<Row> rows = new ArrayList<Row>();
		Row row = new Row();
		row.put("id", VerifyCode.WAITING_VERIFY);
		row.put("text", VerifyCode.getStr(VerifyCode.WAITING_VERIFY));
		rows.add(row);
		Row row1 = new Row();
		row1.put("id", VerifyCode.PASS);
		row1.put("text", VerifyCode.getStr(VerifyCode.PASS));
		rows.add(row1);
		Row row2 = new Row();
		row2.put("id", VerifyCode.REFUSE);
		row2.put("text", VerifyCode.getStr(VerifyCode.REFUSE));
		rows.add(row2);
		return ajaxReturn(response, rows);
	}
	
	
	
}
