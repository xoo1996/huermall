package cn.edu.hdu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.hdu.Code.ReturnCode;
import cn.edu.hdu.pojo.Battery;
import cn.edu.hdu.pojo.Gift;
import cn.edu.hdu.pojo.PictureDir;
import cn.edu.hdu.service.impl.BatteryServiceImpl;
import cn.edu.hdu.service.impl.GiftServiceImpl;
import cn.edu.hdu.service.impl.RGiftPictureServiceImpl;
import cn.edu.hdu.util.DataUtil;

import com.zlzkj.core.base.BaseController;

@Controller
@RequestMapping(value="gift")
public class GiftController extends BaseController{
	private static Logger logger = Logger.getLogger(GiftController.class);

	@Resource
	private GiftServiceImpl giftService;
	
	@Resource
	private BatteryServiceImpl batteryService;
	
	@Resource
	private RGiftPictureServiceImpl gpService;
	
	@RequestMapping(value="gift_list")
	public String gifttList(String name,String symbol,Long changeNum,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/gift/gift_list";
		}else{
			int rowNum = DataUtil.getPage(request.getParameter("rows"));
			int page = DataUtil.getPage(request.getParameter("page"));
			return ajaxReturn(response,giftService.getGiftList(name, symbol,changeNum, rowNum, page));
		}
	}
	
	@RequestMapping(value="battery_list")
	public String batteryList(String name,String type,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/gift/battery_list";
		}else{
			int rowNum = DataUtil.getPage(request.getParameter("rows"));
			int page = DataUtil.getPage(request.getParameter("page"));
			return ajaxReturn(response,batteryService.getBatteryList(name, type, rowNum, page));
		}
	}
	
	@RequestMapping(value="add_gift_jsp")
	public String addGiftJsp(
			HttpServletRequest request, HttpServletResponse response){
		return "adminPage/gift/add_gift";
	}
	
	@RequestMapping(value="add_battery")
	public String addBattery(Battery battery,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/gift/add_battery";
		}else{
			boolean re = batteryService.addBattery(battery);
			if (re){
				return ajaxReturn(response,null,"添加成功",1);
			}else{
				return ajaxReturn(response,null,"添加失败",0);
			}
		}
	}
	
	@RequestMapping(value="add_gift")
	public String addGiftInfo(Gift gift,@RequestParam(value="photo",required=false) MultipartFile[] file,
			HttpServletRequest request, HttpServletResponse response){
		// 添加Gift,添加图片,添加关联
		try {
			if(gift.getName().equals("")){
				return ajaxReturn(response,null,ReturnCode.ADD_FAILED,ReturnCode.FAILED);
			}
			giftService.saveGift(gift, file);
			return ajaxReturn(response,null,ReturnCode.ADD_SUCCESS,ReturnCode.SUCCESS);
		} catch (IOException e) {
			return ajaxReturn(response,null,ReturnCode.ADD_FAILED,ReturnCode.FAILED);
		}
	}
	
	@RequestMapping(value="delete_gift")
	public String deleteGift(Long giftId,
			HttpServletRequest request, HttpServletResponse response){
		boolean re = giftService.deleteGift(giftId);
		if(re){
			return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
		}else{
			return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
		}
	}
	
	@RequestMapping(value="delete_battery")
	public String deleteBattery(Battery battery,
			HttpServletRequest request, HttpServletResponse response){
		boolean re = batteryService.deleteBattery(battery);
		if(re){
			return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
		}else{
			return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
		}
	}
	
	@RequestMapping(value="reset_store_num")
	public String resetStoreNum(Long id,Long storeNum,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/gift/reset_storenum";
		}else{
			boolean re = giftService.resetStoreNum(id, storeNum);
			if(re){
				return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
			}else{
				return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
			}
		}
	}
	
	@RequestMapping(value="batt_store_num")
	public String battStoreNum(Long id,Long storeNum,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/gift/batt_storenum";
		}else{
			boolean re = batteryService.resetStoreNum(id, storeNum);
			if(re){
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
			return "adminPage/gift/reset_score";
		}else{
			boolean re = giftService.resetScore(id, score);
			if(re){
				return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
			}else{
				return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
			}
		}
	}
	
	@RequestMapping(value="batt_score")
	public String battScore(Long id,Long score,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/gift/batt_score";
		}else{
			boolean re = batteryService.resetScore(id, score);
			if(re){
				return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
			}else{
				return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
			}
		}
	}
	
	@RequestMapping(value="show_gift")
	public String showGift(@RequestParam(value="id",required=true)Long giftId,Model model,
			HttpServletRequest request, HttpServletResponse response){
		Gift gift = giftService.findById(giftId);
		List<PictureDir> pdList = gpService.getPictureDirListBy(giftId);
		model.addAttribute("pictureList", pdList);
		model.addAttribute("gift", gift);
		return "adminPage/gift/giftDetail";
	}
	
	/**
	 * 获得礼品数量
	 * @param giftId
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="get_gift_num")
	public String getGiftNum(@RequestParam(value="id",required=true)Long giftId,Model model,
			HttpServletRequest request, HttpServletResponse response){
		Gift gift = giftService.findById(giftId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("num", gift.getStoreNum());
		map.put("score",gift.getScore() );
		return ajaxReturn( response, map);
	}
}
