package com.logistics.express.action;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


	@Controller
	@RequestMapping("/Base")
	public class BaseAction{
		@Resource
		 ServletContext application;
		/**
		 * @param folder
		 * @param file
		 * @param session
		 * @return 二级页面跳转
		 * String
		 */
		//方法参数folder通过@PathVariable指定其值可以从@RequestMapping的{folder}获取，同理file也一样
		@RequestMapping("/goURL/{folder}/{file}")
		public String goURL(@PathVariable String file,@PathVariable String folder,
				HttpSession session) {
			/*application.setAttribute("account", session.getAttribute("account"));*/
			return "forward:/WEB-INF/jsp/"+folder+"/"+file+".jsp";
		}
		
		@RequestMapping("/go")
		public String go(
				) {
			/*application.setAttribute("account", session.getAttribute("account"));*/
			return "main/index";
		}
	
}
