package com.logistics.express.service;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.GoodTransport;

public interface GoodTransportService {

	//根据单号建立初始运输信息
	public int addGoodTransportMessage(GoodTransport goodTransport);
	
	//删除订单
	public int deleteTransportMessage(String goodOrderNumber);
	
	//业务员接件后更新信息
	public int addConnectorMessage(Map<String,Object> map);
	
	//更新运输信息，如运输方式，运输单位和司机,货运价格等信息
	public int updateTransportMessage(GoodTransport goodTransport);
	
	//判断某订单是否有司机负责配送
	public int getDriverIdByOrder(String goodOrderNumber);
	
	//获取业务员接件信息
	public List<GoodTransport> getConnectMessage(Map<String,Object> map);
	
	//获取业务员接件信息数量
	public int getConnectMessageCount(int status);
	
	//根据订单号获取订单信息
	public GoodTransport getOrderByNumber(String goodOrderNumber);
	
}
