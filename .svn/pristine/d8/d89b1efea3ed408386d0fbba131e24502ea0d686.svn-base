package cn.edu.hdu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.Code.ReturnCode;
import cn.edu.hdu.Code.UploadCode;
import cn.edu.hdu.dto.PictureDirDto;
import cn.edu.hdu.pojo.Activity;
import cn.edu.hdu.pojo.GlobalCfg;
import cn.edu.hdu.service.impl.ActivityServiceImpl;
import cn.edu.hdu.service.impl.FileServiceImpl;
import cn.edu.hdu.service.impl.GlobalCfgServiceImpl;
import cn.edu.hdu.service.impl.RActiPicImpl;
import cn.edu.hdu.util.DataUtil;
import cn.edu.hdu.util.FileUtil;

import com.zlzkj.core.base.BaseController;

@Controller
@RequestMapping(value="activity")
public class ActivityController extends BaseController {
	private static Logger logger = Logger.getLogger(ActivityController.class);
	
	@Resource
	private ActivityServiceImpl activityService;
	@Resource
	private FileServiceImpl fileService;
	@Resource
	private GlobalCfgServiceImpl gcService;
	@Resource
	private RActiPicImpl apService;
	
	@RequestMapping(value="/ckeditor")
	public String  createActivity(
			@RequestParam("file")MultipartFile file,Activity activity,HttpServletRequest request, HttpServletResponse response){
		//保存封面
		Long seriesId = (Long)request.getSession().getAttribute("seriesId");
		Long id = 0L; //记录图片路径的记录id
		String moduleDir = UploadCode.DIR_ACTI_COVER;
		String moduleName = UploadCode.MODULE_ACTI_COVER;
		String fileName = file.getOriginalFilename();
		try {
			String filePath = FileUtil.getFilePath(moduleDir, fileName,seriesId);
			FileUtil.saveImg(file, filePath);
			id = fileService.saveImgPath(filePath,moduleName, seriesId);
			//保存活动信息
			activity.setCoverUrl(FileUtil.getUrl(moduleName, id)); //保存封面的请求路径
			activityService.createActivity(activity);
			//保存图片和活动的对应关系：R_ACTI_PICTURE TODO
			
			apService.saveRActiPict(activity.getId(), seriesId);
			return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
		}catch(Exception e){
			return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
		}
	}
	
	@RequestMapping(value="/ckeditorjsp")
	public String  createActivityJsp(HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("seriesId", new Date().getTime());
		return "adminPage/activity/ckeditor";
	}
	
	@RequestMapping(value="/imgupload")
	public void uploadActivityImgInCkEditor(
			@RequestParam("upload") MultipartFile file,HttpServletRequest request, HttpServletResponse response){
		Long seriesId = (Long)request.getSession().getAttribute("seriesId");
		String url = null;
		String callback = request.getParameter("CKEditorFuncNum"); 
		Long id = 0L; //记录图片路径的记录id
		try {
			String moduleDir = UploadCode.DIR_ACTI_CONTENT;
			String moduleName = UploadCode.MODULE_ACTI_CONTENT;
			String fileName = file.getOriginalFilename();
			String filePath = FileUtil.getFilePath(moduleDir,fileName ,seriesId);
			FileUtil.saveImg(file, filePath); //保存到本地
			id = fileService.saveImgPath(filePath,moduleName, seriesId);//保存本地路径
			url = FileUtil.getUrl(moduleName, id);
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			PrintWriter out=response.getWriter();
			out.println("<script type=\"text/javascript\">");    
	        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + url + "','')");    
	        out.println("</script>");  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/activity_list")
	public String  getActivityList(String name,String status,Model model,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/activity/activitylist";
		}
		int rows = DataUtil.getPage(request.getParameter("rows"));
		int page = DataUtil.getPage(request.getParameter("page"));
		return ajaxReturn(response,activityService.getActivityList(name, status, rows, page));
	}
	
	@RequestMapping(value="/edit_activity_jsp")
	public String  editActivityContentJsp(Model model,Long id,HttpServletRequest request, HttpServletResponse response){
		Activity act = activityService.findById(id);
		model.addAttribute("activity", act);
		return "adminPage/activity/edit_activity";
	}
	
	@RequestMapping(value="/edit_activity")
	public String  editActivityContent(Model model,
			@RequestParam("file")MultipartFile file,Activity activity,HttpServletRequest request, HttpServletResponse response){
		Long seriesId = (Long)request.getSession().getAttribute("seriesId");
		Long id = 0L; //记录图片路径的记录id
		String moduleDir = UploadCode.DIR_ACTI_COVER;
		String moduleName = UploadCode.MODULE_ACTI_COVER;
		String fileName = file.getOriginalFilename();
		try {
			//有封面时保存封面图片
			if(!file.getOriginalFilename().equals("")){
				String filePath = FileUtil.getFilePath(moduleDir,fileName, seriesId);
				FileUtil.saveImg(file, filePath);
				id = fileService.saveImgPath(filePath,moduleName, seriesId);
				activity.setCoverUrl(FileUtil.getUrl(moduleName, id)); //保存封面的请求路径
			}
			//保存活动信息
			activityService.updateActivity(activity);
			//保存图片和活动的对应关系：R_ACTI_PICTURE 
			apService.saveRActiPict(activity.getId(), seriesId);
			model.addAttribute("next", "/huiermall/activity/ckeditorjsp");
			return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
		}catch(Exception e){
			return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
		}
	}
	
	@RequestMapping(value="/release_activity")
	public String  releaseActivity(@RequestParam(value="id",required=true)Long activityId,HttpServletRequest request, HttpServletResponse response){
		boolean re = activityService.releaseActivity(activityId);
		if (re){
			return ajaxReturn(response,null,ReturnCode.RELEASE_SUCCESS,ReturnCode.SUCCESS);
		}else{
			return ajaxReturn(response,null,ReturnCode.RELEASE_FAILED,ReturnCode.FAILED);
		}
	}
	
	@RequestMapping(value="/delete_activity")
	public String  deleteActivity(@RequestParam(value="id",required=true)Long activityId,HttpServletRequest request, HttpServletResponse response){
		boolean re = activityService.deleteActivity(activityId);
		if (re){
			return ajaxReturn(response,null,ReturnCode.DELETE_SUCCESS,ReturnCode.SUCCESS);
		}else{
			return ajaxReturn(response,null,ReturnCode.DELETE_FAILED,ReturnCode.FAILED);
		}
	}
	
	@RequestMapping(value="roll_ad")
	public String setRollAdvertisement(String rollAd,Model model,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			List<GlobalCfg> gcList = gcService.findAll();
			model.addAttribute("rollAd", gcList.size()>0?gcList.get(0).getRollAd():"");
			return "adminPage/activity/edit_rollAd";
		}else{
			boolean re = gcService.setRollAdvertisement(rollAd);
			if (re){
				return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
			}else{
				return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
			}
		}
	}
	
	@RequestMapping(value="huier_month")
	public String setHuierMonth(String actisdt,String actiedt,Model model,
			HttpServletRequest request, HttpServletResponse response) throws ParseException{
		if(request.getMethod().equals("GET")){
			List<GlobalCfg> gcList = gcService.findAll();
			model.addAttribute("actisdt", gcList.size()>0?gcList.get(0).getActisdt():"");
			model.addAttribute("actiedt", gcList.size()>0?gcList.get(0).getActiedt():"");
			return "adminPage/activity/huier_month";
		}else{
			SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
			Date sdt=date.parse(actisdt);
			Date edt=date.parse(actiedt);
			boolean re = gcService.setHuierMonth(sdt,edt);
			if (re){
				return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
			}else{
				return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
			}
		}
	}
	
	@RequestMapping(value="show_activity")
	public String showActivity(@RequestParam(value="id",required=true)Long activityId,Model model,
			HttpServletRequest request, HttpServletResponse response){
		Activity entity = activityService.findById(activityId);
		model.addAttribute("activity", entity);
		return "memberPage/activity/activityDetail";
	}
	
	@RequestMapping(value="admin_show_activity")
	public String showAdminActivity(@RequestParam(value="id",required=true)Long activityId,Model model,
			HttpServletRequest request, HttpServletResponse response){
		Activity entity = activityService.findById(activityId);
		model.addAttribute("activity", entity);
		return "adminPage/activity/activityDetail";
	}
	
	/**
	 * 编辑轮播图片
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="lunbo")
	public String editLunbo(Model model,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			List<PictureDirDto> pddList = fileService.getImgDir(UploadCode.MODULE_LUNBO);
			model.addAttribute("pddList", pddList);
			return "adminPage/activity/edit_lunbo";
		}else{
			boolean re = true;
			if (re){
				return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
			}else{
				return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
			}
		}
	}
	
	/**
	 * 删除轮播图片，由于轮播图片没有与其他诸如活动等实体相关联，所以只需要删除图片即可
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="delete_lunbo")
	public String deleteLunboImg(Model model,long id,
			HttpServletRequest request, HttpServletResponse response){
		boolean re = fileService.deleteById(id); //删除图片路径
		fileService.deleteLocalImg(id);  //删除轮播图片
		fileService.correctLunboImgIndex();//更新索引值
		if (re){
			return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
		}else{
			return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
		}
		
	}
	
	@RequestMapping(value="save_lunbo_jsp")
	public String saveLunboImgJsp(Long index,Model model,
			HttpServletRequest request, HttpServletResponse response){
		if(index != null)
			model.addAttribute("index", index);
		else
			model.addAttribute("index", GlobalParam.DEFAULT_LUNBO_INDEX);
		return "adminPage/activity/addlunbo";
	}
	
	/**
	 * 保存上传的轮播图片，针对一张图片的上传
	 * @param file
	 * @param index
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="save_lunbo")
	public String saveLunboImg(MultipartFile file,Long index,
			HttpServletRequest request, HttpServletResponse response){
		Long seriesId = 0L;
		if(file.getOriginalFilename().equals("")){
			return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
		}
		try {
			String moduleDir = UploadCode.DIR_LUNBO;
			String moduleName = UploadCode.MODULE_LUNBO;
			String fileName = file.getOriginalFilename();
			String filePath = FileUtil.getFilePath(moduleDir,fileName);
			FileUtil.saveImg(file, filePath); //保存到本地
			fileService.saveImgPath(filePath,moduleName, seriesId,index);//保存本地路径
			fileService.correctLunboImgIndex();//更新索引值
			return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
		}catch(Exception e){
			return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
		}
	}
	
	@RequestMapping(value="correct_lunbo")
	public String correctImgIndex(String data,
			HttpServletRequest request, HttpServletResponse response){
		if(data == null){
			return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
		}
		String[] dataArray = data.split(",");
		try {
			fileService.correctLunboImgIndex(dataArray);
			return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
		}catch(Exception e){
			return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
		}
	}
	
	
	@RequestMapping(value="match_activity")
	public String lunboMatchActivity(Model model,Long id,Long actId,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			actId = fileService.findById(id).getActivityId();
			Activity act = null;
			if(actId != null){
				act = activityService.findById(actId);
				model.addAttribute("act", act);
			}
			return "adminPage/activity/lunbo_match_act";
		}else{
			boolean re = activityService.matchLunboActivity(id, actId);
			if (re){
				return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
			}else{
				return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
			}
		}
	}
	

	@RequestMapping(value="reset_score")
	public String resetScore(Long id,Long score,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/activity/reset_score";
		}else{
			boolean re = activityService.resetScore(id, score);
			if(re){
				return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
			}else{
				return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
			}
		}
	}
}
