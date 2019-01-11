package cn.edu.hdu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.hdu.Code.AutoVerifyCode;
import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.Code.ScoreEventType;
import cn.edu.hdu.Code.VerifyCode;
import cn.edu.hdu.config.ReturnMsg;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.GlobalCfg;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.pojo.PriceConfig;
import cn.edu.hdu.pojo.ScoreEvent;
import cn.edu.hdu.pojo.Verify;
import cn.edu.hdu.service.PriceconfigService;
import cn.edu.hdu.service.VerifyService;
import cn.edu.hdu.service.impl.GlobalCfgServiceImpl;
import cn.edu.hdu.service.impl.MemberServiceImpl;
import cn.edu.hdu.service.impl.ScoreServiceImpl;
import cn.edu.hdu.util.DataUtil;

import com.alibaba.fastjson.JSON;
import com.zlzkj.app.util.LogStringBuilder;
import com.zlzkj.core.base.BaseController;

@Controller
@RequestMapping(value="score")
public class ScoreController extends BaseController {
	private static Logger logger = Logger.getLogger(ScoreController.class);
	
	@Resource
	private LogStringBuilder lsb;
	@Resource
	private MemberServiceImpl memberService;
	@Resource
	private ScoreServiceImpl scoreService;
	@Resource
	private VerifyService verifyService;
	@Resource
	private PriceconfigService priceconfigService;
	
	@Resource
	private GlobalCfgServiceImpl globalCfgService;
	/**
	 * 积分事件列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/scorelist"})
	public String scoreEventlist(String memberName,String memberNo,String memberPhone,String eventType,String storeName,
			Model model,HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("POST")){
			String page = request.getParameter("page");
			String rowNumber = request.getParameter("rows");
			Account account=(Account)request.getSession().getAttribute("account");
			String accId = account.getAccount();
			String isAllStore = account.getRole().getAllStore();
			//log
			logger.info(lsb.method()
					+ lsb.append("memberName", memberName) 
					+ lsb.append("memberNo", memberNo)
					+ lsb.append("memberPhone", memberPhone)
					+ lsb.append("eventType", eventType)
					+ lsb.append("storeName", storeName)
					+ lsb.append("accId", accId)
					+ lsb.append("isAllStore", isAllStore));
			
			//总部或超级管理员账号，查询时不需要操作账户限制
			if(isAllStore.equals(GlobalParam.ALL_STORE_YES))
				accId = null;
			return ajaxReturn(response,scoreService.getScoreList(memberName, memberNo, memberPhone,eventType,storeName
					,page,rowNumber,accId,null));
		}else{
			Account account=(Account)request.getSession().getAttribute("account");
			String isAllStore = account.getRole().getAllStore();
			model.addAttribute("isadmin", false);
			if(isAllStore.equals(GlobalParam.ALL_STORE_YES)){
				model.addAttribute("isadmin", true);
			}
			return "adminPage/score/scorelist";
		}
	}
	/**
	 * 新建价格区间配置
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="add_price")
	public String addPrice(PriceConfig priceconfig ,HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			return "adminPage/score/add_price";
		}else{
			logger.info(lsb.method()
					+ lsb.append(JSON.toJSONString(priceconfig)));
			Long lowLimit = priceconfig.getStartPrice();
			Long upLimit = priceconfig.getEndPrice();
			if(lowLimit >= upLimit){
				return ajaxReturn(response,null,"起始价格不能高于截止价格",0);
			}
			boolean re = priceconfigService.addPrice(priceconfig);
			if (re){
				return ajaxReturn(response,null,"添加成功",1);
			}else{
				return ajaxReturn(response,null,"添加失败",0);
			}
		}
	}
	/**
	 * 设置积分抵扣金额比例
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="set_scale")
	public String setScale(String scoreMoney, HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			List<GlobalCfg> gcList = globalCfgService.findAll();
			String score=gcList.get(0).getScoremoney();
			request.getSession().setAttribute("old", score);
			return "adminPage/score/set_scale";
		}else{
			logger.info(lsb.method()
					+ lsb.append("scoreMoney" , scoreMoney));
			boolean re = globalCfgService.setScoreChangeMoney(scoreMoney);
			logger.info(lsb.method()
					+ lsb.append("re" , re));
			if (re){
				return ajaxReturn(response,null,"设置成功",1);
			}else{
				return ajaxReturn(response,null,"设置失败",0);
			}
		}
	}
	/**
	 * 设置用户邀请赠送积分
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="set_apply")
	public String setApply(String applyScore ,HttpServletRequest request, HttpServletResponse response){
		if(request.getMethod().equals("GET")){
			List<GlobalCfg> gcList = globalCfgService.findAll();
			String score=gcList.get(0).getApplyscore();
			request.getSession().setAttribute("old", score);
			return "adminPage/score/set_apply";
		}else{
			logger.info(lsb.method()
					+ lsb.append("applyScore" , applyScore));
			boolean re = globalCfgService.setApplyUserScore(applyScore);
			logger.info(lsb.method()
					+ lsb.append("re" , re));
			if (re){
				return ajaxReturn(response,null,"设置成功",1);
			}else{
				return ajaxReturn(response,null,"设置失败",0);
			}
		}
	}
	/**
	 * 价格区间配置列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/price_config"})
	public String config(HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("POST")){
			int page = DataUtil.getPage(request.getParameter("page"));
			int rowNumber = DataUtil.getPage(request.getParameter("rows"));
			return ajaxReturn(response,priceconfigService.getScoreList(page,rowNumber));
		}else{
			return "adminPage/score/price_config";
		}
	}
	/**
	 * 编辑 用户信息
	 * @param model
	 * @param user
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="editprice")
	public String editPrice(Model model,PriceConfig pc,Long id, HttpServletRequest request, HttpServletResponse response){
		String method = request.getMethod();
		if(method.equals("POST")){
			logger.info(lsb.method()
					+ lsb.append("pc" , JSON.toJSONString(pc))
					+ lsb.append("id",id));
			try {
				priceconfigService.updatePrice(pc);
			} catch (Exception e) {
				return ajaxReturn(response, null, ReturnMsg.ERROR, ReturnMsg.FLAG_FAILURE);
			}
			return ajaxReturn(response, null,ReturnMsg.SUCCESS , ReturnMsg.FLAG_SUCCESS);
		}else{
			PriceConfig pc1 = priceconfigService.getPrice(id.toString());
			model.addAttribute("edit", pc1);
			return "adminPage/score/editprice";
		}
	}
	/**
	 * 设置最大积分抵扣比例
	 * @param scoreMoney
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="set_rate")
	public String setRate(Model model,GlobalCfg cfg, HttpServletRequest request, HttpServletResponse response){
		String method = request.getMethod();
		if(method.equals("POST")){
			logger.info(lsb.method()
					+ lsb.append("cfg" , JSON.toJSONString(cfg)));
			try {
				globalCfgService.updateRate(cfg);
			} catch (Exception e) {
				return ajaxReturn(response, null, ReturnMsg.ERROR, ReturnMsg.FLAG_FAILURE);
			}
			return ajaxReturn(response, null,ReturnMsg.SUCCESS , ReturnMsg.FLAG_SUCCESS);
		}else{
			List<GlobalCfg> gcList = globalCfgService.findAll();
			GlobalCfg cfg1=gcList.get(0);
			model.addAttribute("edit", cfg1);
			return "adminPage/score/set_rate";
		}
	}
	/**
	 * 设置代金券
	 * @param scoreMoney
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="set_change")
	public String setChange(Model model,GlobalCfg cfg, HttpServletRequest request, HttpServletResponse response){
		String method = request.getMethod();
		if(method.equals("POST")){
			logger.info(lsb.method()
					+ lsb.append("cfg" , JSON.toJSONString(cfg)));
			try {
				globalCfgService.updateCfg(cfg);
			} catch (Exception e) {
				return ajaxReturn(response, null, ReturnMsg.ERROR, ReturnMsg.FLAG_FAILURE);
			}
			return ajaxReturn(response, null,ReturnMsg.SUCCESS , ReturnMsg.FLAG_SUCCESS);
		}else{
			List<GlobalCfg> gcList = globalCfgService.findAll();
			GlobalCfg cfg1=gcList.get(0);
			model.addAttribute("edit", cfg1);
			return "adminPage/score/set_change";
		}
	}
	/**
	 * 设置老用户注册赠送积分值
	 * @param scoreMoney
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="set_login")
	public String setLogin(Model model,GlobalCfg cfg, HttpServletRequest request, HttpServletResponse response){
		String method = request.getMethod();
		if(method.equals("POST")){
			logger.info(lsb.method()
					+ lsb.append("cfg" , JSON.toJSONString(cfg)));
			try {
				globalCfgService.updateLogin(cfg);
			} catch (Exception e) {
				return ajaxReturn(response, null, ReturnMsg.ERROR, ReturnMsg.FLAG_FAILURE);
			}
			return ajaxReturn(response, null,ReturnMsg.SUCCESS , ReturnMsg.FLAG_SUCCESS);
		}else{
			List<GlobalCfg> gcList = globalCfgService.findAll();
			GlobalCfg cfg1=gcList.get(0);
			model.addAttribute("edit", cfg1);
			return "adminPage/score/set_login";
		}
	}
	
	/**
	 * 门店积分申请通道
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="store_apply_list")
	public String storeApplyList(String memberName,String memberNo,String memberPhone,String storeAcc,
			Model model,HttpServletRequest request, HttpServletResponse response){
		String method = request.getMethod();
		if(method.equals("POST")){
			String page = request.getParameter("page");
			String rowNumber = request.getParameter("rows");
			Account account=(Account)request.getSession().getAttribute("account");
			String acc = null;
			String isAllStore = account.getRole().getAllStore();
			//总部或超级管理员账号，查询时不需要操作账户限制
			if(isAllStore.equals(GlobalParam.ALL_STORE_YES)){
				if(storeAcc == null){// 管理员活动部在查询，同时搜索条件中没有具体门店，此时就搜全部门店给他
					acc = null;
				}else{ //否则把某一门店的记录给他
					acc = storeAcc;
				}
			}else{ //门店人员在查询 Account 对象中存储着门店的账号，直接拿来用
				acc = account.getAccount();
			}
				
			return ajaxReturn(response,scoreService.getScoreApplyList(memberName, memberNo, memberPhone,ScoreEventType.CHARGE_SCORE
					,page,rowNumber,acc));
		}else{
			Account account=(Account)request.getSession().getAttribute("account");
			String isAllStore = account.getRole().getAllStore();
			boolean isadmin = false;
			if(isAllStore.equals(GlobalParam.ALL_STORE_YES)){
				isadmin = true;
			}
			model.addAttribute("isadmin", isadmin);
			return "adminPage/score/store_apply_list";
		}
	}
	
	@RequestMapping(value="store_apply_detail")
	public String storeApplyDetail(Model model,Long id,
			Long score,String remark,
			HttpServletRequest request, HttpServletResponse response){
		String method = request.getMethod();
		if(method.equals("POST")){
			try {
				logger.info(lsb.method()
						+ lsb.append("id" , id)
						+ lsb.append("score",score)
						+ lsb.append("remark",remark));
				if(score == null){
					return ajaxReturn(response,null,"积分为空，添加失败",0);
				}
				//将收费产生积分事件这条记录的标识设置成已经使用过
				ScoreEvent se = scoreService.findById(id);
				if(score < 0 || score > GlobalParam.PRESENT_SCORE_MAX){
					return ajaxReturn(response,null,"积分超出范围，添加失败",0);
				}
				List<Member> mList = memberService.findByProperty("memberNo", se.getMemberNo());
				Member member = mList.get(0);
				se.setHaveGivenScore(VerifyCode.WAITING_SCIRE);
				Verify verify = new Verify();
				Account account=(Account)request.getSession().getAttribute("account");
				verify.setApplyScore(score);
				verify.setContent("类型：" + VerifyCode.getStr(VerifyCode.STORE_APPLY_SCORE) + ","
						+"会员名：" + member.getName() + "," 
						+"会员编号：" + member.getMemberNo() + ","
						+"订单号：" + se.getFolno() + ","
						+"备注：" + remark);
				verify.setBsc011(account.getAccount());
				verify.setType(VerifyCode.STORE_APPLY_SCORE);
				verify.setStartDate(new Date());
				verify.setStatus(VerifyCode.WAITING_VERIFY);
				verify.setMemberNo(se.getMemberNo());
				verify.setFolno(se.getFolno());
				verify.setScoreEventId(id);
				boolean re = verifyService.addApplyuser(verify);
				
				//判断是否需要进行自动审批
				GlobalCfg gc = globalCfgService.findById(0L);
				Byte autoVerify = gc.getAutoVerify();  //获得数据库中配置
				if(autoVerify == AutoVerifyCode.yes){
					String acc = account.getAccount(); //审批人，默认是申请人吧
					Long verifyId = verify.getId(); //verify实体id
					String result = "pass";  //审批结果
					Long presentScore = verify.getApplyScore();  //赠送的积分值
					verifyService.storeScoreVerify(acc ,verifyId,result,presentScore);
				}
				if (re){
					return ajaxReturn(response,null,"添加成功",1);
				}else{
					return ajaxReturn(response,null,"添加失败",0);
				}
			} catch (Exception e) {
				return ajaxReturn(response, null, ReturnMsg.ERROR, ReturnMsg.FLAG_FAILURE);
			}
		}else{
			ScoreEvent event = scoreService.findById(id);
			//记录已经被用过，返回null
			if(!VerifyCode.NOT_USED.equals(event.getHaveGivenScore()))
				return null;
			Account account=(Account)request.getSession().getAttribute("account");
			model.addAttribute("event", event);
			model.addAttribute("account", account);
			model.addAttribute("max", GlobalParam.PRESENT_SCORE_MAX);
			List<Member> mList = memberService.findByProperty("memberNo", event.getMemberNo());
			model.addAttribute("member", (mList == null || mList.size()==0)?null:mList.get(0));
			return "adminPage/score/store_apply_detail";
		}
	}
	
	/**
	 * 快捷按钮，页面弹窗查询积分事件
	 * @param memId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="score_event")
	public String scoreEvent(String memId,Long id,String eventType,
			Model model,HttpServletRequest request, HttpServletResponse response){
		String method = request.getMethod();
		if(method.equals("POST")){
			if(memId == null){
				return ajaxReturn(response,null,"会员编号为空",0);
			}
			String page = request.getParameter("page");
			String rowNumber = request.getParameter("rows");
	//		Account account=(Account)request.getSession().getAttribute("account");
		//	String acc = account.getAccount();
			//超级管理员不限定记录的归属门店
	//		String isAllStore = account.getRole().getAllStore();
		/*	if(isAllStore.equals(GlobalParam.ALL_STORE_YES)){
			//总部或超级管理员账号，查询时不需要操作账户限制
				acc = null;
			}*/
			//查询的事件类型
			List<String> list = new ArrayList<String>();
			if(eventType.equals("coin")){
				list.add(ScoreEventType.COIN_CHANGE);
				list.add(ScoreEventType.COIN_APPLY);
				list.add(ScoreEventType.REC_EXCHANGE);
			}else{
				list.add(ScoreEventType.SCORE_GIFT);
				list.add(ScoreEventType.CHARGE_SCORE);
				//list.add(ScoreEventType.SCORE_EXCHANGE);
				list.add(ScoreEventType.SCORE_CASH);
				list.add(ScoreEventType.STORE_SCORE);
				list.add(ScoreEventType.OLD_SCORE);
			}
			return ajaxReturn(response,scoreService.getScoreList(null, memId, null,null,null
					,page,rowNumber,null,list));
		}else{
			model.addAttribute("memId",memberService.findById(id).getMemberNo() );
			return "adminPage/score/score_event";
		}
	}
	
}
