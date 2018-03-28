package com.logistics.express.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/test")

public class TestController {
	
	@RequestMapping("test1")
	public String aaa(){
		return "index";
	}
	
	@RequestMapping("test2")
	public String test2(){
		return "home/LogisticaLocation";
	}
	
	@RequestMapping("test3")
	public String test3(){
		return "home/test";
	}
	
	@RequestMapping("test4")
	public String test4(){
		return "home/driverLocation";
	}

}
