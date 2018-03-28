package com.logistics.express.service;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.Consignee;
import com.logistics.express.entity.Consignor;

public interface ConsignorService {

	//添加货主
	public int addConsignor(Consignor consignor);
	
	//修改货主信息
	public int editConsignor(Consignor consignor);
	
	//查询货主信息
	public Consignor getConsignorMsg(Map<String,Object> map);
	
	//后台获取收件人列表
	public List<Consignor> getConsignorList(Map<String,Object> map);
		
	//获取收件人数量
	public int getConsignorCount();
	
	//根据名字获取信息
	public List<Consignor> getConsignorByName(String consignorName);
}
