package cn.edu.hdu.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.hdu.pojo.Activity;
import cn.edu.hdu.service.impl.ActivityServiceImpl;

import com.zlzkj.core.base.BaseController;

@Controller
@RequestMapping(value="service")
public class ServiceController extends BaseController {
	
	@Resource
	private ActivityServiceImpl activityService;
	
	
	
	@RequestMapping(value="show_activity")
	public String showActivity(@RequestParam(value="id",required=true)Long activityId,Model model,
			HttpServletRequest request, HttpServletResponse response){
		Activity entity = activityService.findById(activityId);
		model.addAttribute("activity", entity);
		return "memberPage/activity/activityDetail";
	}
}
