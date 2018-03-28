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

import com.logistics.express.entity.Admin;
import com.logistics.express.entity.ApiResponse;
import com.logistics.express.entity.Consignee;
import com.logistics.express.service.ConsigneeService;

@Controller
@RequestMapping("consignee")
public class ConsigneeAction {

	@Autowired
	private ConsigneeService consigneeService;
	
	private Logger logger = Logger.getLogger(ConsigneeAction.class);
	
	/**
	 * 
	 * @param consignee
	 * @return
	 * Description:新增收件人信息
	 * @author:HAO
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse addConsignee(Consignee consignee){
		ApiResponse response = null;
		try{
			int result = consigneeService.addConsignee(consignee);
			if(result != 1){
				response = new ApiResponse(0,"操作失败");
			}else{
				response = new ApiResponse(1,"操作成功");
			}
		}catch(Exception e){
			logger.error("新增收件人信息时出现异常",e);
			response = new ApiResponse(-1,"未知异常");
		}
		return response;
	}
	
	/**
	 * 
	 * @param consignee
	 * @return
	 * Description:修改收件人信息
	 * @author:HAO
	 */
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse editConsignee(Consignee consignee){
		ApiResponse response = null;
		try{
			if(consigneeService.editConsignee(consignee)==1){
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
	 * Description:查询收件人信息
	 * @param consigneeName
	 * @param consigneePhone
	 * @return
	 */
	@RequestMapping(value="getConsigneeMsg",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse getConsigneeMessage(String consigneeName,String consigneePhone){
		ApiResponse response = null;
		Map<String,Object> map = new HashMap<>();
		map.put("consigneeName", consigneeName);
		map.put("consigneePhone", consigneePhone);
		Consignee consignee = consigneeService.getConsigneeMessage(map);
		if(consignee == null){
			response = new ApiResponse(0,"无信息");
		}else{
			response = new ApiResponse(1,consignee,"获取成功");
		}
		return response;
	}
	
	/**
	 * 
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * Description:获取收件人列表
	 * @author:HAO
	 */
	@RequestMapping(value="getConsigneeList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getConsigneeList(int page,int rows,String consigneeName){
		Map<String,Object> jo = new HashMap();
		Map<String,Object> map = new HashMap<>();
		//名字为空则为分页查询数据
		if(consigneeName == null || consigneeName.isEmpty()){
			if(page == 0){
				page = 1;
			}
			if(rows == 0){
				rows = 10;
			}
			map.put("start", (page-1)*10);
			map.put("pagesize",rows);
			List<Consignee> consigneeList = consigneeService.getConsigneeList(map);
			int count = consigneeService.getConsigneeCount();
			jo.put("total",count);
			jo.put("rows",consigneeList);
		}else{
			//不为空则按名字搜索
			List<Consignee> consigneeList = consigneeService.getConsigneeByName(consigneeName);
			jo.put("total",consigneeList.size());
			jo.put("rows",consigneeList);
		}
		return jo;
	}
}
