package cn.edu.hdu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zlzkj.core.base.BaseController;

@Controller
@RequestMapping(value="test")
public class TestController extends BaseController {
	
	@RequestMapping(value={"/"})
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "test/test";
	}
	
	@RequestMapping(value={"login"})
	public String login(Model model,
			String loginName,String password,String loginType,
			HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("GET")){
			return "test/login";
		}
		model.addAttribute("loginName", loginName);
		model.addAttribute("password", password);
		model.addAttribute("loginType", loginType);
		return "test/test";
	}
}
