package com.logistics.express.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.express.entity.ApiResponse;
import com.logistics.express.entity.Driver;
import com.logistics.express.entity.GoodOrder;
import com.logistics.express.entity.TransportCompany;
import com.logistics.express.service.TransportCompanyService;
import com.logistics.express.unity.DateUnti;

@RequestMapping("company")
@Controller
public class TransportCompanyAction {
	
	@Autowired
	private TransportCompanyService transportCompanyService;
	
	private Logger logger = Logger.getLogger(TransportCompanyAction.class);
	
	
	/**
	 * Description:新增运输公司
	 * @param company
	 * @return
	 */
	@RequestMapping(value="addCompany",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse addCompany(TransportCompany company){
		ApiResponse response = null;
		try{
			if(company != null){
				int result = transportCompanyService.addCompany(company);
				if(result == 1){
					response = new ApiResponse(1,"操作成功");
				}else{
					response = new ApiResponse(0,"操作失败");
				}
			}else{
				response = new ApiResponse(0,"数据未填写完整");
			}
		}catch(Exception e){
			logger.error("新增运输公司时出现异常", e);
			response = new ApiResponse(-1,"内部错误");
		}
		return response;
	}
	
	/**
	 * 
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * Description:获取运输公司列表
	 * @author:HAO
	 */
	@RequestMapping(value="getCompanyList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getCompanyList(int page,int rows,String transportCompanyName){
		Map<String,Object> jo = new HashMap();
		Map<String,Object> map = new HashMap<>();
		//公司名称为空则分页查询公司列表
		if(transportCompanyName == null || transportCompanyName.isEmpty()){
			if(page == 0){
				page = 1;
			}
			if(rows == 0){
				rows = 10;
			}
			map.put("start", (page-1)*10);//页数
			map.put("pagesize",rows);//每页行数
			List<TransportCompany> companyList = transportCompanyService.getTransportCompany(map);
			int count = transportCompanyService.getCompanyCount();
			jo.put("total",count);
			jo.put("rows",companyList);
		}else{//公司名称不为空则按名称搜索
			List<TransportCompany> companyList = transportCompanyService.getCompanyByName(transportCompanyName);
			jo.put("total",companyList.size());
			jo.put("rows",companyList);
		}
		return jo;
	}
	
//	/**
//	 * Description:录入运输司机
//	 * @param company
//	 * @return
//	 */
//	@RequestMapping(value="addDriver",method=RequestMethod.POST)
//	@ResponseBody
//	public ApiResponse addDriver(Driver driver){
//		ApiResponse response = null;
//		try{
//			if(driver != null){
//				//由公司录入的司机无需审核
//				driver.setStatus(1);
//				int result = transportCompanyService.addDriverToCompany(driver);
//				if(result == 1){
//					response = new ApiResponse(1,"操作成功");
//				}else{
//					response = new ApiResponse(0,"操作失败");
//				}
//			}else{
//				response = new ApiResponse(0,"数据未填写完整");
//			}
//		}catch(Exception e){
//			logger.error("录入司机时出现异常", e);
//			response = new ApiResponse(-1,"内部错误");
//		}
//		return response;
//	}

}
