package com.logistics.express.service;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.Driver;
import com.logistics.express.entity.GoodTransportProcess;

public interface GoodTransportProcessService {

	//根据单号添加初始化运输过程信息
	public int addTransportProcessMessage(GoodTransportProcess goodTransportProcess);
	
	//删除订单
	public int deleteProcessMessage(String goodOrderNumber);
	
	//实时更新运输过程信息
	public int updateTransportProcessMessage(GoodTransportProcess goodTransportProcess);
	
	//查询运输过程信息
	public GoodTransportProcess getTransportProcessMessage(String goodOrderNumber);
	
	//获取货物运输信息列表
	public List<GoodTransportProcess> getTransportProcessList(Map<String,Object> map);
	
	//获取货物运输信息列表数量
	public int getTransProcessListCount();
	
	//根据司机Id获取所有运输信息
	public List<GoodTransportProcess> getTransProcessByDriverId(int driverId);
	
	//根据单号获取运输信息
	public GoodTransportProcess getProcessByOrder(String goodOrderNumber);

}
