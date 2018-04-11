package com.logistics.express.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.logistics.express.entity.GoodOrder;

public interface GoodOrderService {

	//货主录入订单
	public int addGoodOrder(GoodOrder goodOrder);
	
	//删除订单
	public int deleteOrder(String goodOrderNumber);
	
	//后台获取订单列表
	public List<GoodOrder> getOrderList(Map<String,Object> map);
	
	//获取订单数量
	public int getOrderCount();
	
	//根据订单号获取货物要求到达时间
	public Date getRequireArrivetime(String goodOrderNumber);
	
	//更新订单状态
	public int updateOrder(GoodOrder goodOrder);
	
	//根据订单号获取订单信息
	public GoodOrder getOrderByNumber(String goodOrderNumber);

	public GoodOrder checkOrderNumber(String OrderNumber);
	
}
