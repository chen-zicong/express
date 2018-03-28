package com.logistics.express.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.express.entity.Admin;
import com.logistics.express.entity.ApiResponse;
import com.logistics.express.service.AdminService;
import com.logistics.express.unity.MD5;


@Controller
public class LoginAction {

	@Autowired
	private AdminService adminService;
	
	private Logger logger = Logger.getLogger(LoginAction.class);
	
	/**
	 * Description:管理员登录
	 * @author:HAO
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse adminLogin(String adminPhone,String adminPassword,HttpServletRequest request){
		ApiResponse response = null;
		Map<String,Object> map = new HashMap<>();
		//检验手机号和密码是否为空
		if(StringUtils.isBlank(adminPhone)){
			return new ApiResponse(0,"手机号不能为空");
		}
		if(StringUtils.isBlank(adminPassword)){
			return new ApiResponse(0,"密码不能为空");
		}
		adminPassword = MD5.stringMD5(adminPassword);
		map.put("adminPhone", adminPhone);
		map.put("adminPassword", adminPassword);
		SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());//重新登录前注销
        UsernamePasswordToken token = new UsernamePasswordToken(adminPhone,adminPassword);//封装门令
        Subject subject = SecurityUtils.getSubject();
		try{
			Admin admin = adminService.login(map);
			if(admin != null){
				HttpSession session = request.getSession();
	             //把用户数据保存在session域对象中
				session.setAttribute("adminName", admin.getAdminName());
				session.setAttribute("adminRoleId", admin.getAdminRoleId());
				session.setAttribute("adminId", admin.getAdminId());
				session.setAttribute("adminPhone", admin.getAdminPhone());
				session.setAttribute("admin", admin);
				subject.login(token);
				response = new ApiResponse(1,"登录成功");
			}else{
				response = new ApiResponse(0,"无此用户");
			}
		}catch(Exception e){
			logger.error("登录时出现异常", e);
			response = new ApiResponse(-1,"登录失败");
		}
		return response;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * Description:退出登录
	 * @author:HAO
	 */
	@RequestMapping(value="logout",method=RequestMethod.POST)
	public String logout(HttpServletRequest request){
		 Subject subject = SecurityUtils.getSubject();
		 try{
			 //request.getSession().invalidate();
			 subject.logout();
		 }catch(Exception e){
			 logger.error("退出登录时出现异常", e);
		 }
		return "login.jsp";
	}
}
