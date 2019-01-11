package cn.edu.hdu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.Code.ReturnCode;
import cn.edu.hdu.dao.impl.GlobalCfgDaoImpl;
import cn.edu.hdu.dto.MenuTreeDto;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.GlobalCfg;
import cn.edu.hdu.pojo.Role;
import cn.edu.hdu.service.AccountService;
import cn.edu.hdu.service.impl.AccountServiceImpl;
import cn.edu.hdu.service.impl.MenuServiceImpl;
import cn.edu.hdu.service.impl.RoleServiceImpl;
import cn.edu.hdu.util.DataUtil;

import com.alibaba.fastjson.JSON;
import com.zlzkj.core.base.BaseController;
import com.zlzkj.core.sql.Row;

@Controller
@RequestMapping(value="param")
public class ParameterController extends BaseController{
	private static Logger logger = Logger.getLogger(ParameterController.class);
	@Resource
	private AccountServiceImpl accountService;
	@Resource
	private RoleServiceImpl roleService;
	@Resource
	private MenuServiceImpl menuService;
	@Resource
	private GlobalCfgDaoImpl globalService;
	
	@RequestMapping(value="auto_verify")
	public String autoVerify(
			HttpServletRequest request, HttpServletResponse response){
		List list = new ArrayList<Row>();
		Row row1 = new Row();
		row1.put("id", "1");
		row1.put("text", "是");
		Row row2 = new Row();
		row2.put("id", "0");
		row2.put("text", "否");
		list.add(row1);
		list.add(row2);
		return ajaxReturn(response,list);
	}
}
