package cn.edu.hdu.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zlzkj.app.util.CheckMobileUtil;
import com.zlzkj.core.base.BaseController;
import com.zlzkj.core.sql.Row;

import cn.edu.hdu.config.ReturnMsg;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.pojo.Waitscore;
import cn.edu.hdu.service.FileService;
import cn.edu.hdu.service.MenuService;
import cn.edu.hdu.service.RRoleMenuService;
import cn.edu.hdu.service.impl.MemberServiceImpl;
import cn.edu.hdu.service.impl.ScoreServiceImpl;

@Controller
@RequestMapping(value = "memop")
public class MemberOperateController extends BaseController {
	// private static Logger logger = Logger.getLogger(IndexController.class);
	@Resource
	private ScoreServiceImpl scoreService;
	@Resource
	private MenuService menuService;
	@Resource
	private FileService fileService;
	@Resource
	private RRoleMenuService roleMenuService;
	@Resource
	private MemberServiceImpl memberService;

	@RequestMapping(value = "/login")
	public String index(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		return "forward:/public/memlogin";
	}

	@RequestMapping(value = "/show_activity")
	public String show_activity(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		return "forward:/service/show_activity";
	}

	@RequestMapping(value = "/member_index")
	public String member_index(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		return "forward:/member_index";
	}

	@RequestMapping(value = "/member_info")
	public String memberInfo(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		//判断访问设备类型
		boolean isFromMobile = false;
		isFromMobile = CheckMobileUtil.check(request);
		if (isFromMobile) {
			System.out.println("移动端访问");
			return "memberPage/mobile/test";
		} else {
			System.out.println("pc端访问");
		}
		Member member = (Member) request.getSession().getAttribute("member");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		Date oneMonthBefore = calendar.getTime();
		List<String> params = new ArrayList<String>();
		List<Waitscore> seList = scoreService
				.findByHql(
						"from Waitscore ws where ws.effect = 0 and ws.memberNo = ? order by ws.createdt desc",
						member.getMemberNo());
		Long total = 0L;
		for (Waitscore se : seList) {
			total += se.getScore();
		}

		model.addAttribute("member", member);
		model.addAttribute("seList", seList);
		model.addAttribute("total", total);
		return "adminPage/member/mem_info";
	}

	@RequestMapping(value = "/member_info_json")
	public String memberInfoJson(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		Row row = new Row();
		Member member = (Member) request.getSession().getAttribute("member");
		row.put("name", member.getName());// 姓名
		row.put("coin", member.getCoin());// 惠耳币
		row.put("memberNo", member.getMemberNo());// 会员编号
		row.put("score", member.getScore());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		Date oneMonthBefore = calendar.getTime();
		List<String> params = new ArrayList<String>();
		List<Waitscore> seList = scoreService
				.findByHql(
						"from Waitscore ws where ws.memberNo = ? order by ws.createdt desc",
						member.getMemberNo());
		Long total = 0L;
		for (Waitscore se : seList) {
			total += se.getScore();
		}
		row.put("waitScore", total);// 待生效积分
		System.out.println(row);
		response.setHeader("Access-Control-Allow-Origin", "*");
		return ajaxReturn(response, row);
	}

	@RequestMapping(value = "/member_score_event")
	public String delayScoreEvent(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getMethod().equals("GET")) {
			return "adminPage/member/mem_delay_score";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 0时0分0秒");
		Member member = (Member) request.getSession().getAttribute("member");
		List<Waitscore> seList = scoreService
				.findByHql(
						"from Waitscore ws where ws.effect = 0 and ws.memberNo = ? order by ws.createdt desc",
						member.getMemberNo());
		Row row = new Row();
		List<Row> rows = new ArrayList<Row>();
		int total = seList.size();
		Calendar cal = Calendar.getInstance();
		for (Waitscore se : seList) {
			Row r = new Row();
			Date hdate = se.getCreatedt();
			if (hdate != null) { // 显示积分到账时间
				cal.setTime(hdate);
				cal.add(Calendar.DATE, 7);
				r.put("date", sdf.format(cal.getTime()));
			}
			r.put("score", se.getScore());
			rows.add(r);
		}
		row.put("rows", rows);
		row.put("total", total);
		return ajaxReturn(response, row);
	}

	/**
	 * 会员登陆后修改成自己的密码
	 * 
	 * @param memberNo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "mem_reset_pwd")
	public String updatePassword(Long id, String pwd,
			HttpServletRequest request, HttpServletResponse response) {
		String method = request.getMethod();
		if (method.equals("POST")) {
			try {
				memberService.memResetPwd(id, pwd);
			} catch (Exception e) {
				return ajaxReturn(response, null, ReturnMsg.ERROR,
						ReturnMsg.FLAG_FAILURE);
			}
			return ajaxReturn(response, null, ReturnMsg.SUCCESS,
					ReturnMsg.FLAG_SUCCESS);
		} else {
			return "adminPage/member/resetPw";
		}
	}
	
	/**
	 * 会员在会员页面查询 当前会员所有的会员事件
	 * @param memId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="score_event_single")
	public String getScoreEventByMember(Long memId,
			Model model,HttpServletRequest request, HttpServletResponse response){
		String method = request.getMethod();
		if(method.equals("POST")){
			if(memId == null){
				return ajaxReturn(response,null,"会员编号为空",0);
			}
			String page = request.getParameter("page");
			String rowNumber = request.getParameter("rows");
			String memNo = memberService.findById(memId).getMemberNo();
			return ajaxReturn(response,scoreService.getScoreList(null, memNo, null,null,null
					,page,rowNumber,null,null));
		}else{
			model.addAttribute("memId",memId);
			return "adminPage/member/mem_score_event";
		}
	}
}
