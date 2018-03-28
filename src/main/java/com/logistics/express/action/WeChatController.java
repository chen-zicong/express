package com.logistics.express.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.logistics.express.service.WeChatService;
import com.logistics.express.entity.TextMessage;




@Controller
@RequestMapping("wechat")

public class WeChatController extends HttpServlet{
	
	private final String token="beamgod";
	@Resource
	private WeChatService wechatService;
	
	//@RequestMapping("get")
	@RequestMapping(method = {RequestMethod.GET })
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(">>>>>>>>>>>>>>>>b");
		String signature=req.getParameter("signature");
		String timestamp=req.getParameter("timestamp");
		String nonce=req.getParameter("nonce");
		String echostr=req.getParameter("echostr");
		System.out.println(">>>>>>>>>>>>>>>>o");
		PrintWriter out=resp.getWriter();
		if(wechatService.checkSignature(signature, timestamp, nonce, token)){
			System.out.println(signature);
			System.out.println(echostr);
			out.print(echostr);
			System.out.println("finish doGet");
		}else{
			System.out.println("不是微信传来的echostr");
	}
	}
	
	
	@RequestMapping(method={RequestMethod.POST })
	//@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(">>>>>>>>begin");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter out=resp.getWriter();
		try {
			System.out.println("这里开始try");
			Map<String,String>map= wechatService.xmlToMap(req);
			String toUserName=map.get("ToUserName");
			String fromUserName=map.get("FromUserName");
			String msgType=map.get("MsgType");
			//String content=map.get("Content");	
			String message=null;
			if("text".equals(msgType)){
				String content=map.get("Content");
				TextMessage text=new TextMessage();
				text.setFromUserName(toUserName);
				text.setToUserName(fromUserName);
				text.setMsgType("text");
				text.setCreateTime(new Date().getTime());
				//text.setContent("qu ni mei de"+content);
				text.setContent("您遇到的所有问题都可以建议邮箱留言，给您带来的不便我们表示真诚的歉意");
				message=wechatService.textMessageToXml(text);		
				//message = MessageUtil.initNewsMessage(toUserName,fromUserName);
				System.out.println(message+"这里回复发来的消息");
				System.out.println("这里输出message ");
				out.print(message);
			}else if("event".equals(msgType)){
				String event=map.get("Event");
				if(event.equals("subscribe")){
					//String content=map.get("Content");
					TextMessage text=new TextMessage();
					text.setFromUserName(toUserName);
					text.setToUserName(fromUserName);
					text.setMsgType("text");
					text.setCreateTime(new Date().getTime());
					//text.setContent("qu ni mei de"+content);
					text.setContent("亲，终于等到了您的关注！从此让你赚钱变得很简单!\n专业，专注，诚信，务实，做HR我们是认真的！  \n系统正在玩命开发中！！！");
					message=wechatService.textMessageToXml(text);		
					//message = MessageUtil.initNewsMessage(toUserName,fromUserName);
					System.out.println(message+"这里回复发来的消息");
					System.out.println("这里输出message ");
					out.print(message);
				}
			}
			System.out.println(">>>>>>>>over");
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			out.close();
		} 
	}
	
	@RequestMapping("test")
	public void aaa(HttpServletResponse response) throws IOException{
		PrintWriter outPrintWriter=response.getWriter();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("aaa", "aaa");
		outPrintWriter.write(jsonObject.toString());
	}
	
}
