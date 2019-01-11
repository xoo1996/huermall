package cn.edu.hdu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.Code.ReturnCode;
import cn.edu.hdu.Code.UrlCode;
import cn.edu.hdu.config.ReturnMsg;
import cn.edu.hdu.config.webSend;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.Battery;
import cn.edu.hdu.pojo.GlobalCfg;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.service.AccountService;
import cn.edu.hdu.service.impl.BatteryServiceImpl;
import cn.edu.hdu.service.impl.GiftServiceImpl;
import cn.edu.hdu.service.impl.GlobalCfgServiceImpl;
import cn.edu.hdu.service.impl.MemberServiceImpl;
import cn.edu.hdu.service.impl.ScoreServiceImpl;
import cn.edu.hdu.util.DataUtil;

import com.alibaba.fastjson.JSON;
import com.zlzkj.app.util.LogStringBuilder;
import com.zlzkj.core.base.BaseController;
import com.zlzkj.core.sql.Row;

//新增

@Controller
@RequestMapping(value="member")
public class MemberController extends BaseController{
	private static Logger logger = Logger.getLogger(MemberController.class);
	
	@Resource
	private LogStringBuilder lsb;
	@Resource
	private AccountService accountService;
	@Resource
	private MemberServiceImpl memberService;
	@Resource
	private GlobalCfgServiceImpl globalCfgService;	
	@Resource
	private GiftServiceImpl giftService;
	@Resource
	private BatteryServiceImpl battService;
	@Resource
	private ScoreServiceImpl scoreService;
	
	/**
	 * 新建会员
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="add_member")
	public String addMember(Member member ,HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/member/add_member";
		}else{
			logger.info(lsb.append("addMember") 
					+ lsb.append(JSON.toJSONString(member)));
			String url = UrlCode.URL;
			String method = "checkTel";
			String memberNo=member.getMemberNo();
			String phone=member.getPhone();
			Object[] opAddEntryArgs=new Object[] {memberNo,phone};
			String judge;
			try {
				judge = (String)webSend.sendService(opAddEntryArgs, url, method);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ajaxReturn(response,null,"系统错误！",0);
			}
			if(!"false".equals(judge)){
				Account account=(Account)request.getSession().getAttribute("account");
				List<GlobalCfg> gcList = globalCfgService.findAll();
				member.setScore(gcList.get(0).getOldLogin());
				member.setStoreId(account.getId());
				String re = memberService.addMember(member,false);
				//memberService.insertScore("",account.getAccount(),"老用户注册赠送积分","","","",""+gcList.get(0).getOldLogin(),"");
				if("1".equals(re)) scoreService.logOld(memberNo,gcList.get(0).getOldLogin(),account.getAccount());
				return ajaxReturn(response,null,ReturnCode.getString(re),1);
			}else{
				return ajaxReturn(response,null,"老系统连接错误",0);
			}
		}
	}
	
	/**
	 * 编辑会员
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="edit_mem")
	public String editMember(Model model,Member member, Long id, HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			Member mem = memberService.findById(id);
			model.addAttribute("edit", mem);
			return "adminPage/member/edit_mem";
		}else{
			try{
				if(member.getPhone() != null   //判断修改后的手机号是否已经被使用
						&& !member.getPhone().trim().equals("")){
					List<Member> memList = memberService.findByProperty("phone", member.getPhone().trim());
					Long mid=0L;
					if(memList.size() > 0){ //已经有用户注册过这个手机号码
						mid=memList.get(0).getId();
						if(!member.getId().equals(mid)) {
							 return ajaxReturn(response,null,"该手机号已被使用",0);
						}
					}
					if(!member.getId().equals(mid)){
						Member mem = memberService.findById(id);
						try{
							String url = UrlCode.URL;
							String method = "checkTel";
							Object[] opAddEntryArgs=new Object[] {mem.getMemberNo(),member.getPhone().trim()};
							webSend.sendService(opAddEntryArgs, url, method);
						}catch(Exception e){
							return ajaxReturn(response,null,"连接惠耳老系统错误",0);
						}
					}
				}
				memberService.updateMem(member);
				return ajaxReturn(response,null,"编辑成功",1);
			}catch(Exception e){
				return ajaxReturn(response,null,"编辑失败",0);
			}
		}
	}
	/**
	 * 删除会员
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="del_mem")
	public String delMember(Long id ,HttpServletRequest request, HttpServletResponse response){
		Account account=(Account)request.getSession().getAttribute("account");
		Long roleId = account.getRole().getId();
		if(this.isRoleCanDelete(roleId)){
			try{
				Member mem = memberService.findById(id);
				if(mem.getCoin()==0&&mem.getScore()==0){
					memberService.delete(mem);
					return ajaxReturn(response,null,"删除成功",1);
				}else{
					return ajaxReturn(response,null,"该会员信息正确不可删除",0);
				}
			}catch(Exception e){
				return ajaxReturn(response,null,"删除失败",0);
			}
		}else{
			return ajaxReturn(response,null,"您没有权限",0);
		}
	}
	/**
	 * 会员列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/memberlist"})
	public String memberList(String memberName,String memberNo,String memberPhone,
			HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("POST")){
			if((memberName == null || memberName.equals("")) 
					&& (memberNo==null || memberNo.equals("")) 
					&& (memberPhone==null || memberPhone.equals(""))){
				return null;
			}
			logger.info(lsb.append("memberName", memberName) 
					+ lsb.append("memberNo", memberNo)
					+ lsb.append("memberPhone", memberPhone));
			int rows = DataUtil.getPage(request.getParameter("rows"));
			int page = DataUtil.getPage(request.getParameter("page"));
			return ajaxReturn(response,memberService.getMemberList(null,memberName, memberNo, memberPhone,rows,page));
		}else{
			return "adminPage/member/memberlist";
		}
	}
	@RequestMapping(value={"/memberlist_admin"})
	public String memberListAdmin(String memberName,String memberNo,String memberPhone,String storeName,
			Model model,HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("POST")){
			//参数记录
			logger.info(lsb.method()
					+ lsb.append("memberName", memberName) 
					+ lsb.append("memberNo", memberNo)
					+ lsb.append("memberPhone", memberPhone));
			int rows = DataUtil.getPage(request.getParameter("rows"));
			int page = DataUtil.getPage(request.getParameter("page"));
			return ajaxReturn(response,memberService.getMemberListAdmin(memberName, memberNo, memberPhone,storeName,rows,page));
		}else{
			model.addAttribute("store", accountService.getAjaxStore());
			return "adminPage/member/memberlist_admin";
		}
	}
	/**
	 * 会员列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/editlist"})
	public String editList(String memberName,String memberNo,String memberPhone,
			Model model,HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("POST")){
			//参数记录
			logger.info(lsb.method()
					+ lsb.append("memberName", memberName) 
					+ lsb.append("memberNo", memberNo)
					+ lsb.append("memberPhone", memberPhone));
			int rows = DataUtil.getPage(request.getParameter("rows"));
			int page = DataUtil.getPage(request.getParameter("page"));
			Account account=(Account)request.getSession().getAttribute("account");
			String accountid = null;
			if(!account.getRole().getId().equals(GlobalParam.ADMIN_ROLE_ID) &&  
					!account.getRole().getId().equals(GlobalParam.ZONGBU_ROLE_ID)	){
				accountid = account.getId().toString();
			}
			return ajaxReturn(response,memberService.getEditMemberList(accountid,memberName, memberNo, memberPhone,rows,page));
		}else{
			Account account=(Account)request.getSession().getAttribute("account");
			Long roleId = account.getRole().getId();
			if(this.isRoleCanDelete(roleId)){
				model.addAttribute("delete", true);
			}else{
				model.addAttribute("delete", false);
			}
			return "adminPage/member/editlist";
		}
	}
	/**
	 * 设置默认密码
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/cfg_psd"})
	public String setCfgPwd(String password,Model model,
			HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("POST")){
			try {
				//参数记录
				logger.info(lsb.method()
						+ lsb.append("password", password));
				boolean re = globalCfgService.setInitPwd(password);
				if(re){
					return ajaxReturn(response,null,"修改成功",1);
				}else{
					return ajaxReturn(response,null,"修改失败",0);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ajaxReturn(response,null,"修改失败",0);
			}
			
		}else{
			model.addAttribute("initPwd", globalCfgService.getInitPwd());
			return "adminPage/member/resetPassword";
		}
	}
	
	/**
	 * 获取登陆会员的会员信息
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/mem_info"})
	public String getSelfMemberInfo(Model model,
			HttpServletRequest request, HttpServletResponse response) {
		Member member = (Member)request.getSession().getAttribute("member");
		model.addAttribute("member", member);
		System.out.println(request.getHeader("referer"));
		return "adminPage/member/mem_info";
		
	}
	
	/**
	 * 重置对应会员的密码，重置后密码为系统初始密码
	 * @param id
	 * @param psd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="update_pw")
	public String resetPassword(String memberNo,HttpServletRequest request,HttpServletResponse 
response){
		String method = request.getMethod();
		if(method.equals("POST")){
			//参数记录
			logger.info(lsb.method()
					+ lsb.append("memberNo", memberNo));
			try {
				int sum = memberService.updatePassword(memberNo);//修改记录条数
				if(sum == 0){
					return ajaxReturn(response, null, "数据库中不存在该会员！", ReturnMsg.FLAG_FAILURE);
				}else{
					return ajaxReturn(response, null, ReturnMsg.SUCCESS, ReturnMsg.FLAG_SUCCESS);
				}
			} catch (Exception e) {
				return ajaxReturn(response, null, ReturnMsg.ERROR, ReturnMsg.FLAG_FAILURE);
			}
			
		}else{
			return "adminPage/member/resetPw";
		}
	}
	
	
	/**
	 * 积分兑换
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="score_change")
	public String scoreChange(Long id ,HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/member/score_change";
		}else{
			try {
				String number=(String)request.getParameter("number");
				Long giftId=Long.parseLong(request.getParameter("giftGroup"));
				String giftGroup = giftService.findById(giftId).getGiftNo();
				Long score=giftService.NumScore(Long.parseLong(number),giftGroup);
				//参数记录
				String args = lsb.method()
						+ lsb.append("id", id.toString())
						+ lsb.append("number",number)
						+ lsb.append("giftId",giftId.toString())
						+ lsb.append("giftGroup",giftGroup)
						+ lsb.append("score",score.toString());
				if(score==null){
					logger.info(args
							+ lsb.append("相应礼品的库存数量不足"));
					return ajaxReturn(response,null,"相应礼品的库存数量不足",0);
				}
				boolean re = memberService.scoreChange(id,Long.parseLong(number),score);
				if (re){
					Account account=(Account)request.getSession().getAttribute("account");
					giftService.updateStore(Long.parseLong(number),giftGroup,id,account.getAccount());
					score=score*Long.parseLong(number);
					memberService.insertScore(score.toString(),account.getAccount(),"领取礼品"+number+"件",giftGroup,number,"","","","","");
					logger.info(args
							+ lsb.append("兑换成功"));
					return ajaxReturn(response,null,"兑换成功",1);
				}else{
					logger.info(args
							+ lsb.append("积分余额不足"));
					return ajaxReturn(response,null,"积分余额不足",0);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ajaxReturn(response,null,"系统错误",0);
			}
		}
	}
	
	/**
	 * 电池领取
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="battery_acc")
	public String batteryAccept(Long id ,HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){

			return "adminPage/member/battery_acc";
		}else{
			try {
				String number=(String)request.getParameter("number");
				String battGroup=(String)request.getParameter("battGroup");
				Long score=battService.NumScore(Long.parseLong(number),battGroup);
				Boolean re = memberService.scoreChange(id,Long.parseLong(number),score);
				//参数记录
				String args = lsb.method()
						+ lsb.append("id", id.toString())
						+ lsb.append("number",number)
						+ lsb.append("battGroup",battGroup)
						+ lsb.append("score",score.toString())
						+ lsb.append("re",re.toString());
				if (re){
					Account account=(Account)request.getSession().getAttribute("account");
					battService.updateStore(Long.parseLong(number),battGroup,id,account.getAccount());
					String url = UrlCode.URL;
					String method = "battChange";
					String bsc011=account.getAccount();
					Object[] opAddEntryArgs=new Object[] {number,bsc011,battGroup};
					webSend.sendService(opAddEntryArgs, url, method);
					score=score*Long.parseLong(number);
					memberService.insertScore(score.toString(),bsc011,"领取电池"+number+"颗",battGroup,number,score.toString(),"","","","");
					logger.info(args
							+ lsb.append("兑换成功"));
					return ajaxReturn(response,null,"兑换成功",1);
				}else{
					logger.info(args
							+ lsb.append("积分余额不足"));
					return ajaxReturn(response,null,"积分余额不足",0);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ajaxReturn(response,null,"系统错误",0);
			}
		}
	}
	
	
	@RequestMapping(value="test")
	public String test(String id ,HttpServletRequest request, HttpServletResponse response){
		try {
			Account account=(Account)request.getSession().getAttribute("account");
			String url = UrlCode.URL;
			String method = "battNum";
			String bsc011=account.getAccount();
			String type=id;
			//参数记录
			logger.info(lsb.method()
					+ lsb.append("url", url)
					+ lsb.append("method", method)
					+ lsb.append("bsc011", bsc011)
					+ lsb.append("type", type));
			Object[] opAddEntryArgs=new Object[] {bsc011,type};
			String battnum=(String)webSend.sendService(opAddEntryArgs, url, method);
			logger.info(battnum);
			//request.getSession().setAttribute("battnum", battnum);
			List<Battery> battList = battService.findByProperty("type", id);
			Row row = new Row();
			if(battList!= null)
				row.put("score", battList.get(0).getScore());
			row.put("num", battnum);
			
			return ajaxReturn(response, row);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ajaxReturn(response,null,"系统错误",0);
		}
	}
	
	@RequestMapping(value="search_tel")
	public String searchTel(String id ,HttpServletRequest request, HttpServletResponse response){
		try {
			//Account account=(Account)request.getSession().getAttribute("account");
			String url = UrlCode.URL;
			String method = "judgeOld";
			Object[] opAddEntryArgs=new Object[] {id};
			String tel=(String)webSend.sendService(opAddEntryArgs, url, method);
			if(!"false".equals(tel)){
				Row row = new Row();
				row.put("num", tel);
				return ajaxReturn(response, row);
			}else{
				return ajaxReturn(response,null,"输入客户编号有误",0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ajaxReturn(response,null,"系统错误",0);
		}
	}
	
	private boolean isRoleCanDelete(Long roleId){
		if(GlobalParam.ADMIN_ROLE_ID.equals(roleId) || GlobalParam.ZONGBU_ROLE_ID.equals(roleId)){
			return true;
		}else{
			return false;
		}
	}
}