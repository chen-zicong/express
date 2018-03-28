package com.logistics.express.action;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.logistics.express.entity.Driver;
import com.logistics.express.entity.TransportStatue;
import com.logistics.express.service.DriverService;
import com.logistics.express.service.TransportStatueService;
import com.logistics.express.wechat.util.WeChatUtil;


@Controller
@RequestMapping("trans")
public class TransportStatueController {
	
	@Resource
	DriverService driverServie;
	@Resource
	TransportStatueService transportStatueService;
	
	@RequestMapping("driverLocation")
	public String driverLocation(){
		
		
		return "";
	}
	
	
	//发送请求道微信端获取个人openid，并跳转到注册页面
	@RequestMapping("driverRegisterPage")
	public String driverRegisterPage(HttpServletRequest request,HttpSession session){
		//WeChatUtil wechat=new WeChatUtil();
		//JSONObject josnJsonObject=new JSONObject();
		//josnJsonObject=wechat.getPersonalInformaion(request);
		//session.setAttribute("openid", josnJsonObject.get("openid"));
		session.setAttribute("id", "2");
		return "";
	}
	
	
	//注册成功或者失败，跳转到相应页面
	/**@RequestMapping("driverRegister")
	public String driverRegister(HttpServletRequest request,HttpSession session,
			@RequestParam(value="idCard",required=true) String idCard,
			@RequestParam(value="name",required=true) String name,
			@RequestParam(value="openid",required=true) String openid,
			@RequestParam(value="phone",required=true) String phone,
			@RequestParam(value="sex",required=true) String sex){
		Driver check=driverServie.selectByPrimaryKey(openid); 
		if(check!=null)
			return "";
		Driver driver=new Driver();
		driver.setId(driverServie.countDriver()+1);
		driver.setDriverIdCard(idCard);
		driver.setDriverName(name);
		driver.setDriverPhone(phone);
		driver.setDriverSex(Integer.parseInt(sex));
		driverServie.insertSelective(driver);
		return "";
	}
	*/
	
	@RequestMapping("driverPage")
	public String driverPage(HttpServletRequest request,HttpSession session){
		//WeChatUtil wechat=new WeChatUtil();
		//JSONObject josnJsonObject=new JSONObject();
		//josnJsonObject=wechat.getPersonalInformaion(request);
		//session.setAttribute("openid", josnJsonObject.get("openid"));
		session.setAttribute("id", 2);
		return "home/driverLocation";
	}
	
	//更新司机位置
	@RequestMapping("changeLocation")
	public void changeLocation(HttpServletRequest request,HttpSession session,
			@RequestParam(value="Lng",required=true) double lng,
			@RequestParam(value="Lat",required=true) double lat){
		
		int id=(int) session.getAttribute("id");
		//TransportStatue transportStatue=transportStatueService.selectByPrimaryKey("openid");
		TransportStatue transportStatue=new TransportStatue();
		transportStatue.setDriverId(id);
		transportStatue.setLat(lat);
		transportStatue.setLng(lng);
		System.out.println(">>>>>>>>>>>>>>>>"+lat);
		transportStatueService.updateByPrimaryKeySelective(transportStatue);
	}
	
	//货物位置
	@RequestMapping("goodsLocation")
	public String goodsLocation(HttpServletRequest request,HttpSession session){
		
		return "home/LogisticaLocation";
		
	}
	
	

}
