package com.logistics.express.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.express.entity.Admin;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		  //获取请求的URL
		  String url = request.getRequestURI();
		  String identify=((HttpServletRequest) request).getParameter("identify");
		  //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
		  if(url.indexOf("login")>=0){
		   return true;
		  }
		  if(url.indexOf("wechat")>=0){
			   return true;
			  }
		  if(url != null && url.endsWith(".css")||(url != null && url.endsWith(".js"))||
	        		(url != null && url.endsWith(".woff"))||(url != null && url.endsWith(".ttf"))
	        		||(url != null && url.endsWith(".jpg"))||(url != null && url.endsWith(".png"))
	        		||(url != null && url.endsWith(".gif"))||(url != null && url.endsWith(".json"))
	        		||(url != null && url.endsWith(".swf"))||(url != null && url.endsWith(".woff2"))){
	        	return true;
	     }
//		  //获取Session
//		  HttpSession session = request.getSession();
//		  Admin admin = (Admin) session.getAttribute("admin");
		  Subject subject = SecurityUtils.getSubject();
		  if(subject.getPrincipal() != null){
		   return true;
		  }
		  //微信端请求放行
		  if(identify != null){
			  return true;
		  }
		  //不符合条件的，跳转到登录界面
		  request.getRequestDispatcher("/WEB-INF/jsp/main/Login.jsp").forward(request, response);

		  return false;

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

	

}
