package cn.edu.hdu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.Code.VerifyCode;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.GlobalCfg;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.pojo.Verify;
import cn.edu.hdu.service.MemberService;
import cn.edu.hdu.service.VerifyService;
import cn.edu.hdu.service.impl.GlobalCfgServiceImpl;
import cn.edu.hdu.service.impl.MemberServiceImpl;
import cn.edu.hdu.service.impl.ScoreServiceImpl;
import cn.edu.hdu.service.impl.VerifyServiceImpl;
import cn.edu.hdu.util.DataUtil;

import com.zlzkj.core.base.BaseController;
import com.zlzkj.core.sql.Row;

@Controller
@RequestMapping(value="verify")
public class VerifyController extends BaseController {

	@Resource
	private VerifyServiceImpl verifyService;
	@Resource
	private MemberServiceImpl memberService;
	@Resource
	private GlobalCfgServiceImpl globalCfgService;
	@Resource
	private ScoreServiceImpl scoreService;
	
	/**
	 * 审批列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/verifylist"})
	public String inviteApplyList(String verId,String memNo,String status,String sponsor,
			HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("POST")){
			int rows = DataUtil.getPage(request.getParameter("rows"));
			int page = DataUtil.getPage(request.getParameter("page"));
			String stat = status==null?VerifyCode.WAITING_VERIFY:status;
			return ajaxReturn(response,verifyService.getVerifyList(verId,memNo,stat,sponsor,
					VerifyCode.INVITE_NEW,page,rows));
		}else{
			return "adminPage/verify/verifylist";
		}
	}
	
	/**
	 * 门店积分申请
	 * @param type
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/store_apply_list"})
	public String storeApplyList(String verId,String storeName,String memNo,String memName,String status,
			HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("POST")){
			int rows = DataUtil.getPage(request.getParameter("rows"));
			int page = DataUtil.getPage(request.getParameter("page"));
			String stat = status==null?VerifyCode.WAITING_VERIFY:status;
			return ajaxReturn(response,verifyService.getStoreVerifyList(verId,storeName,memNo,memName,stat,
					VerifyCode.STORE_APPLY_SCORE,page,rows));
		}else{
			return "adminPage/verify/store_apply_list";
		}
	}
	/**
	 * 新建 邀请用户积分审批
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="apply_score")
	public String addApplyuser(Verify verify,String newname,String newphone,HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/verify/apply_score";
		}else{
			Account account=(Account)request.getSession().getAttribute("account");
			String name=memberService.findName(verify.getMemberNo());
			if(name==null) return ajaxReturn(response,null,"客户编号不存在",0);
			verify.setContent("新用户姓名："+newname+"，联系方式："+newphone+"，邀请人："+name);
			verify.setBsc011(account.getAccount());
			verify.setStartDate(new Date());
			verify.setType(VerifyCode.INVITE_NEW);
			boolean re = verifyService.addApplyuser(verify);
			if (re){
				return ajaxReturn(response,null,"添加成功",1);
			}else{
				return ajaxReturn(response,null,"添加失败",0);
			}
		}
	}
	
	@RequestMapping(value="wait_verify")
	public String isWaitingVerify(Long id,
			HttpServletRequest request, HttpServletResponse response){
		Verify verify = verifyService.findById(id);
			//当前处于待审批状态
		return ajaxReturn(response,verify.getStatus());
	}
	/**
	 * 审批通过
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="agree_apply")
	public String agree(String remark,String check,Long id,String newprice,String applyid,HttpServletRequest request, HttpServletResponse response){
		Verify verify = verifyService.findById(id);
		if(request.getMethod().equals("GET")){
			//当前处于待审批状态
			return "adminPage/verify/agree_apply";
		}else{
			if(check != null && !check.trim().equals("")){
				return ajaxReturn(response,0);
			}
			try{
				Account account=(Account)request.getSession().getAttribute("account");
				String name=memberService.findName(applyid);
				if(name==null) return ajaxReturn(response,null,"客户编号不存在",0);
				List<GlobalCfg> gcList = globalCfgService.findAll();
				Long apply=Long.parseLong(gcList.get(0).getApplyscore());
				Long coin=Long.parseLong(newprice)*apply/100;
				verifyService.ApaApply(remark,id,true,account.getAccount(),coin,applyid);
				return ajaxReturn(response,null,"审批成功",1);
			}catch(Exception e){
				return ajaxReturn(response,null,"审批失败",0);
			}
		}
	}
	
	
	/**
	 * 审批不通过
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="refuse")
	public String refuse(Long id,String remark,
			HttpServletRequest request, HttpServletResponse response){
		Verify verify = verifyService.findById(id);
		if(request.getMethod().equals("GET")){
			return "adminPage/verify/refuse_apply";
		}
		//当前处于待审批状态
		if(VerifyCode.WAITING_VERIFY.equals(verify.getStatus())){
			try{
				Account account=(Account)request.getSession().getAttribute("account");
				verifyService.ApaApply(remark,id,false,account.getAccount(),0L,"");
				return ajaxReturn(response,null,"审批成功",1);
			}catch(Exception e){
				return ajaxReturn(response,null,"审批失败",0);
			}
		}else return ajaxReturn(response,null,"该申请已审批",0);
		
	}
	
	
	/**
	 * 显示我的申请列表即状态
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="myapply_lsit")
	public String myApplyList(String verId,String verifyer,String status,
			HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/verify/myVerifylist";
		}else{
			int rows = DataUtil.getPage(request.getParameter("rows"));
			int page = DataUtil.getPage(request.getParameter("page"));
			Account account = (Account)request.getSession().getAttribute("account");
			return ajaxReturn(response,verifyService.getMyVerifyList(verId,verifyer,status,account.getAccount(), page, rows));
		}
	}
	
	/**
	 * 待审批到第一次审批（门店电话打来时）（同意，不同意）
	 * @param id
	 * @param verifyResult
	 * @param newScore
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="op_store_apply")
	public String opStoreApply1(Long id,String verifyResult,Long newScore,
			Model model,HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			Verify verify = verifyService.findById(id);
			model.addAttribute("verify", verify);
			//当前处于待审批状态
			if(VerifyCode.WAITING_VERIFY.equals(verify.getStatus())){
				return "adminPage/verify/store_verify1";
			}
			/*//已经审批过，现在要给积分阶段
			else if(VerifyCode.GIVE_SCORE.equals(verify.getStatus())){
				return "adminPage/verify/store_verify2";
			}*/else{ //其他通过或者未通过的审批记录，显示该记录
				return "adminPage/verify/store_verify3";
			}
		}else{
			Account account=(Account)request.getSession().getAttribute("account");
			try {
				verifyService.storeScoreVerify(account.getAccount(), id, verifyResult, newScore);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ajaxReturn(response,null,"系统异常，审批失败",0);
			}
			return ajaxReturn(response,null,"审批成功",1);
		}
	}
	
	/**
	 * 门店积分初审通过，现在第二次打电话进来要求给积分
	 * @param id
	 * @param verifyResult
	 * @param newScore
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	/*@Transactional
	@RequestMapping(value="op_store_apply2")
	public String opStoreApply2(Long id,String memberNo,String folno,
			HttpServletRequest request, HttpServletResponse response){
		try {
			List<Member> memberList = memberService.findByProperty("memberNo", memberNo);
			if(memberList  == null || memberList.size() == 0){
				return ajaxReturn(response,null,"该会员不存在，审批失败",0);
			}
			Account account=(Account)request.getSession().getAttribute("account");
			verifyService.storeScoreVerify(memberList.get(0),account.getAccount(),id,folno);
			Long score = verifyService.findById(id).getFinalScore();
			Verify verify = verifyService.findById(id);
			scoreService.logStoreApply(memberNo, score,verify.getBsc011());
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxReturn(response,null,"系统异常，审批失败",0);
		}
		return ajaxReturn(response,null,"积分发放成功",1);
	}*/
	
	@RequestMapping(value="get_data")
	public String getVerifyData(HttpServletRequest request, HttpServletResponse response){
		Account account=(Account)request.getSession().getAttribute("account");	
		if(!account.getRole().getId().equals(GlobalParam.ZONGBU_ROLE_ID)) //非总部不能显示审批
			return null;
		List<String> params = new ArrayList<String>();
		params.add(VerifyCode.PASS);
		params.add(VerifyCode.REFUSE);
		List<Verify> inviteList = verifyService.getUndoVerify(VerifyCode.INVITE_NEW, params);
		List<Verify> storeList = verifyService.getUndoVerify(VerifyCode.STORE_APPLY_SCORE, params);
		Row row = new Row();
		row.put("invite", inviteList.size());
		row.put("teshu", storeList.size());
		return ajaxReturn(response,row);
	}
	
}
