package cn.edu.hdu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zlzkj.core.base.BaseController;
import com.zlzkj.core.sql.Row;

import cn.edu.hdu.service.BatteryService;


@Controller
@RequestMapping(value="battery")
public class BatteryController extends BaseController {
	
	@Resource
	private BatteryService batteryservice;
	
	@RequestMapping(value="getbattery")
	public String getBattery(HttpServletRequest request, HttpServletResponse response){
		List<Row> rows = batteryservice.getBattOptionList();
		return ajaxReturn(response, rows);
	}
}
