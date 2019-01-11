package com.zlzkj.app.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 核心拦截器，配置request的一些初始值
 * 
 * @author Simon
 *
 */
public class CoreInterceptor implements HandlerInterceptor {

	/**
	 * 拦截器白名单列表
	 */
	public String[] exclude;
	/**
	 * 限制后台只能由特定服务器发出的请求才能登陆
	 */
	public String[] adminOnly;

	public void setExclude(String[] exclude) {
		this.exclude = exclude;
	}
	
	public void setAdminOnly(String[] adminOnly){
		this.adminOnly = adminOnly;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI().substring(
				request.getContextPath().length());
		//后台登陆时，判断请求客户端是否为指定IP的客户端,项目限定后台必须在vpn下使用，非vpn下的登陆一律拦截
		if(uri.startsWith("/public/login") && request.getMethod().equals("POST")){//登陆请求
			//获取客户端IP
			String ip = this.getIpAddr(request);
		//	System.out.println(ip);
			if (adminOnly != null && adminOnly.length != 0) { //读取配置文件中的准入IP，vpn下的IP全部以10开头
				boolean isAllowedIp = false;
				for (String one : adminOnly) { 
					if (ip.startsWith(one)) { //10开头，vpn网络
						isAllowedIp = true;   //标记
					}
				}
				if(!isAllowedIp){  // 发现不允许时返回“非法访问”
					JSONObject o = new JSONObject();
					o.put("status", 0);
					o.put("info", "非法访问");
					response.setContentType("text/html");
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);
					PrintWriter pw = null;
					try {
						pw = response.getWriter();
						pw.write(JSON.toJSONString(o));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{  
				        pw.close();
				    } 
			        return false;
				}
			}
		}
		// 白名单放行
		if (exclude != null && exclude.length != 0) {
			for (String one : exclude) {
				if (uri.startsWith(one)) {
					return true;
				}
			}
		}
		
		//session验证，判断session是否过期
		Object account = request.getSession().getAttribute("account");
		Object member = request.getSession().getAttribute("member");
		//前端展示页面上发起的请求，若member为null，需要进行拦截
		if(member == null){
			if(uri.startsWith("/memop")){   //会员操作且session失效时
				response.sendRedirect(request.getContextPath() + "/memop/member_index");
				return false;
			}
		}else{  
			if(uri.startsWith("/memop")){
				return true;
			}
		}
		//后台发起的请求必须有account，若account为null，说明session失效或者盗用链接，则进行拦截
		if (account == null) {
			response.sendRedirect(request.getContextPath() + "/public/login");
			return false;
		} 
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
	
	 private String getIpAddr(HttpServletRequest request){  
        String ipAddress = request.getHeader("x-forwarded-for");  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getRemoteAddr();  
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                //根据网卡取本机配置的IP  
                InetAddress inet=null;  
                try {  
                    inet = InetAddress.getLocalHost();  
                } catch (UnknownHostException e) {  
                    e.printStackTrace();  
                }  
                ipAddress= inet.getHostAddress();  
            }  
        }  
      /*  //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
            if(ipAddress.indexOf(",")>0){  
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
            }  
        }*/ 
        //对于通过多个代理的情况，最后一个IP为代理IP,多个IP按照','分割 ，这里允许最后一个代理通过 
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
            if(ipAddress.indexOf(",")>0){  
                ipAddress = ipAddress.substring(ipAddress.indexOf(","));  
            }  
        }
        return ipAddress;  															 
    }  

}
