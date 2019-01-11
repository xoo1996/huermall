package cn.edu.hdu.controller;

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

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.Code.ReturnCode;
import cn.edu.hdu.dto.MenuTreeDto;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.GlobalCfg;
import cn.edu.hdu.pojo.Role;
import cn.edu.hdu.pojo.Store;
import cn.edu.hdu.service.impl.AccountServiceImpl;
import cn.edu.hdu.service.impl.GlobalCfgServiceImpl;
import cn.edu.hdu.service.impl.MenuServiceImpl;
import cn.edu.hdu.service.impl.RoleServiceImpl;
import cn.edu.hdu.service.impl.ScoreServiceImpl;
import cn.edu.hdu.service.impl.StoreServiceImpl;
import cn.edu.hdu.util.DataUtil;

import com.alibaba.fastjson.JSON;
import com.zlzkj.app.util.LogStringBuilder;
import com.zlzkj.core.base.BaseController;

@Controller
@RequestMapping(value="system")
public class SystemController extends BaseController{
	private static Logger logger = Logger.getLogger(SystemController.class);
	@Resource
	private LogStringBuilder lsb;
	@Resource
	private AccountServiceImpl accountService;
	@Resource
	private RoleServiceImpl roleService;
	@Resource
	private MenuServiceImpl menuService;
	@Resource
	private GlobalCfgServiceImpl globalService;
	@Resource
	private ScoreServiceImpl scoreService;
	@Resource
	private StoreServiceImpl storeService;
	/**
	 * 显示账户列表
	 * @param name
	 * @param accountNo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="account_list")
	public String accountList(String name,String accountNo,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/system/accountlist";
		}else{
			int rows = DataUtil.getPage(request.getParameter("rows"));
			int page = DataUtil.getPage(request.getParameter("page"));
			return ajaxReturn(response,accountService.getAccountList(name, accountNo, rows, page));
		}
	}
	
	@RequestMapping(value="add_account")
	public String addAccount(Account account,Store store,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/system/add_account";
		}else{
			boolean re = accountService.addAccount(account);
			store.setGctId(account.getAccount());
			store.setGctNm(account.getName());
			boolean re2 = storeService.addStore(store);
			if (re&&re2){
				return ajaxReturn(response,null,ReturnCode.ADD_SUCCESS,ReturnCode.SUCCESS);
			}else{
				return ajaxReturn(response,null,ReturnCode.ADD_FAILED,ReturnCode.FAILED);
			}
		}
	}
	
	@RequestMapping(value="delete_account")
	public String deleteAccount(Account account,
			HttpServletRequest request, HttpServletResponse response){
		boolean re = accountService.delete(account);
		if (re){
			return ajaxReturn(response,null,ReturnCode.DELETE_SUCCESS,ReturnCode.SUCCESS);
		}else{
			return ajaxReturn(response,null,ReturnCode.DELETE_FAILED,ReturnCode.FAILED);
		}
	}
	
	@RequestMapping(value="reset_password")
	public String resetAccountPwd(Long id,@RequestParam(value="psd",required=false) String newPwd,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/system/resetPw";
		}else{
			int updateNum = accountService.resetAccountPwd(id, newPwd);
			if (updateNum == 1){
				return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
			}else{
				return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
			}
		}
	}

	@RequestMapping(value="setrole")
	public String setRole(Account account,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			if(account.getId().equals(GlobalParam.ADMIN_ID)){
				return ajaxReturn(response,null,ReturnCode.ADMIN_CANNOT_SET_ROLE,ReturnCode.FAILED);
			}
			return "adminPage/system/setRole";
		}else{
			if(account.getRole().getId().equals(GlobalParam.ADMIN_ROLE_ID)){
				return ajaxReturn(response,null,ReturnCode.ADMIN_ROLE_CANNOT_SET,ReturnCode.FAILED);
			}
			boolean re = accountService.setRole(account);
			if (re){
				return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
			}else{
				return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
			}
		}
	}
	
	@RequestMapping(value="accountinfo")
	public String getAccountInfo(Long id,Model model,
			HttpServletRequest request, HttpServletResponse response){
		Account acc = accountService.findById(id);
		model.addAttribute("account", acc);
		return "adminPage/system/userDetail";
	}
	
	@RequestMapping(value="rolelist")
	public String getRoleList(HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/system/rolelist";
		}else{
			int rows = DataUtil.getPage(request.getParameter("rows"));
			int page = DataUtil.getPage(request.getParameter("page"));
			return ajaxReturn(response,roleService.getRoleList(rows, page));
		}
	}
	
	@RequestMapping(value="addrole")
	public String addRole(Role role,HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/system/addRole";
		}else{
			boolean re = false;
			try {
				re = roleService.addRole(role);
			} catch (Exception e) {
				return ajaxReturn(response,null,ReturnCode.ADD_FAILED,ReturnCode.FAILED);
			}
			if (re){
				return ajaxReturn(response,null,ReturnCode.ADD_SUCCESS,ReturnCode.SUCCESS);
			}else{
				return ajaxReturn(response,null,ReturnCode.ADD_FAILED,ReturnCode.FAILED);
			}
		}
	}
	
	@RequestMapping(value="edit_role")
	public String editRole(Role role,Model model,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			Role r = roleService.findById(role.getId());
			model.addAttribute("role", r);
			return "adminPage/system/editRole";
		}else{
			boolean re = false;
			try {
				re = roleService.editRole(role);
			} catch (Exception e) {
				return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
			}
			if (re){
				return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
			}else{
				return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
			}
		}
	}
	
	@RequestMapping(value="delete_role")
	public String deleteRole(Long id,
			HttpServletRequest request, HttpServletResponse response){
		if(id == null){
			return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
		}
		boolean re = false;
		try {
			//如果有账户关联了这个角色，则不让删除该角色，先删除账户
			List a = accountService.findByProperty("role.id", id);
			if(a != null && a.size() >0){
				return ajaxReturn(response,null,ReturnCode.ROLE_HAVE_MATCHED_ACCOUNT,ReturnCode.FAILED);
			}
			re = roleService.deleteById(id);
		} catch (Exception e) {
			return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
		}
		if (re){
			return ajaxReturn(response,null,ReturnCode.MODIFY_SUCCESS,ReturnCode.SUCCESS);
		}else{
			return ajaxReturn(response,null,ReturnCode.MODIFY_FAILED,ReturnCode.FAILED);
		}
		
	}
	
	@RequestMapping(value="set_role_menu")
	public String setRoleMenu(Model model,Long id,String authorityNodes,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("POST")){
			try {
				String[] ids = authorityNodes.split(","); 
				roleService.setRoleMenu(id, ids);   //更新操作
			} catch (Exception e) {
				logger.info(e);
				return ajaxReturn(response, null, ReturnCode.ADD_FAILED, ReturnCode.FAILED);
			}
			return ajaxReturn(response, null, ReturnCode.ADD_SUCCESS, ReturnCode.SUCCESS);
		}else{
			model.addAttribute("roleId", id);
			return "adminPage/system/setAuthority";
		}
	}
	
	@RequestMapping(value="get_role_menu_tree")
	public String getRoleMenuTree(Long id,
			HttpServletRequest request, HttpServletResponse response){
		List<MenuTreeDto> mtdList = null;
		try {
			mtdList = menuService.getMenuTreeNode(id);  //返回全部节点以及当前角色所选择了的节点
		} catch (Exception e) {
			logger.info(e);
			return ajaxReturn(response, null, ReturnCode.ADD_FAILED,ReturnCode.FAILED);
		}
		logger.info("mtdList=" + JSON.toJSONString(mtdList));
		return ajaxReturn(response, mtdList);
	}
	
	@RequestMapping(value="global")
	public String setGlobal(Model model,Byte autoVerify,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("POST")){
			try {
				GlobalCfg gc = globalService.findById(0L);
				gc.setAutoVerify(autoVerify);
				globalService.update(gc);
				return ajaxReturn(response, null, ReturnCode.MODIFY_SUCCESS, ReturnCode.SUCCESS);
			} catch (Exception e) {
				logger.info(e);
				return ajaxReturn(response, null, ReturnCode.ADD_FAILED, ReturnCode.FAILED);
			}
		}else{
			GlobalCfg gc = globalService.findById(0L);
			if(gc != null){
				model.addAttribute("global",gc);
			}
			return "adminPage/system/setGlobal";
		}
	}
	
	/**
	 *  统计一段时间内门店的新用户注册情况
	 * @param startDate  开始时间
	 * @param endDate    结束时间
	 * @param account    所选门店账号
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="statistic_list")
	public String statisticList(String startDate,String endDate,String account, 
			HttpServletRequest request, HttpServletResponse response) throws ParseException{
		if(request.getMethod().equals("GET")){
			return "adminPage/system/statisticList";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sDate = null;
		Date eDate = null;
		if(startDate != null && !startDate.equals("")) {
			sDate =  sdf.parse(startDate);
		}
		if(endDate != null && !endDate.equals("")) {
			eDate =  sdf.parse(endDate);
		}
		Object obj = null;
		int rows = DataUtil.getPage(request.getParameter("rows"));
		int page = DataUtil.getPage(request.getParameter("page"));
		if(account == null || "".equals(account.trim())){
			obj = scoreService.getNewMemberCountAll(sDate, eDate, rows, page);
		}else{
			obj = scoreService.getNewMemberCount(sDate, eDate, account, rows, page);
		}
		return ajaxReturn(response,obj);



	}
	
	@RequestMapping(value="mem_statistic_detail")
	public String getMemStatisticDetail(Model model,String startDate,String endDate,String account, 
			HttpServletRequest request, HttpServletResponse response) throws ParseException{
		if(request.getMethod().equals("GET")){
			model.addAttribute("account", account);
			return "adminPage/system/mem_statistic_detail";
		}
		if(account ==  null){
			return ajaxReturn(response,null);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sDate = null;
		Date eDate = null;
		if(startDate != null && !startDate.equals("")) {
			sDate =  sdf.parse(startDate);
		}
		if(endDate != null && !endDate.equals("")) {
			eDate =  sdf.parse(endDate);
		}
		Object obj = null;
		int rows = DataUtil.getPage(request.getParameter("rows"));
		int page = DataUtil.getPage(request.getParameter("page"));
		obj = scoreService.getNewMemberCountDetail(sDate, eDate, account, rows, page);
		return ajaxReturn(response,obj);
	}
}
