package com.logistics.express.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.express.entity.ApiResponse;
import com.logistics.express.entity.Consignee;
import com.logistics.express.entity.Consignor;
import com.logistics.express.service.ConsignorService;

@Controller
@RequestMapping("consignor")
public class ConsignorAction {
	
	@Autowired
	private ConsignorService consignorService;
	
	private Logger logger = Logger.getLogger(ConsignorAction.class);
	
	/**
	 * 
	 * @param consignor
	 * @return ApiResponse
	 * Description:新增货主信息
	 * @author:HAO
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse addConsignor(Consignor consignor){
		ApiResponse response = null;
		try{
			int result = consignorService.addConsignor(consignor);
			if(result != 1){
				response = new ApiResponse(0,"操作失败");
			}else{
				response = new ApiResponse(1,"操作成功");
			}
		}catch(Exception e){
			logger.error("新增收件人信息出现异常",e);
			response = new ApiResponse(-1,"未知异常");
		}
		return response;
	}
	
	/**
	 * 
	 * @param consignor
	 * @return ApiResponse
	 * Description:修改货主信息
	 * @author:HAO
	 */
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse editConsignor(Consignor consignor){
		ApiResponse response = null;
		try{
			if(consignorService.editConsignor(consignor)==1){
				response = new ApiResponse(1,"操作成功");
			}else{
				response = new ApiResponse(0,"操作失败");
			}
		}catch(Exception e){
			logger.error("修改收件人信息出现异常",e);
			response = new ApiResponse(-1,"未知异常");
		}
		
		return response;
	}
	
	/**
	 * Description:查询货主信息
	 * @param consignorName
	 * @param consignorPhone
	 * @return ApiResponse
	 */
	@RequestMapping(value="getConsignorMsg",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse getConsignorMsg(String consignorName,String consignorPhone){
		ApiResponse response = null;
		Map<String,Object> map = new HashMap<>();
		map.put("consignorName", consignorName);
		map.put("consignorPhone", consignorPhone);
		Consignor consignor = consignorService.getConsignorMsg(map);
		if(consignor == null){
			response = new ApiResponse(0,"无信息");
		}else{
			response = new ApiResponse(1,consignor,"获取成功");
		}
		return response;
	}
	
	/**
	 * 
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * Description:获取货主列表
	 * @author:HAO
	 */
	@RequestMapping(value="getConsignorList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getConsignorList(int page,int rows,String consignorName){
		Map<String,Object> jo = new HashMap();
		Map<String,Object> map = new HashMap<>();
		//名字为空则为分页查询
		if(consignorName == null || consignorName.isEmpty()){
			if(page == 0){
				page = 1;
			}
			if(rows == 0){
				rows = 10;
			}
			map.put("start", (page-1)*10);
			map.put("pagesize",rows);
			List<Consignor> consignorList = consignorService.getConsignorList(map);
			int count = consignorService.getConsignorCount();
			jo.put("total",count);
			jo.put("rows",consignorList);
		}else{//名字不为空则按名字搜索
			List<Consignor> consignorList = consignorService.getConsignorByName(consignorName);
			jo.put("total",consignorList.size());
			jo.put("rows",consignorList);
		}
		
		return jo;
	}

}
