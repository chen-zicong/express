package com.logistics.express.service;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.GoodPay;

public interface GoodPayService {

	//新增交付信息
	public int addGoodPay(GoodPay goodPay);
	
	//删除交付信息
	public int deleteGoodPay(String goodOrderNumber);
	
	//更新交付信息
	public int updateGoodPay(GoodPay goodPay);
	
	//后台获取交付信息
	public List<GoodPay> getGoodPayList(Map<String,Object> map);
	
	//获取交付信息数量
	public int getCount();
}
