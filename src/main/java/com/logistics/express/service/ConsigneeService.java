package com.logistics.express.service;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.Consignee;

public interface ConsigneeService {
	
	//添加收件人
	public int addConsignee(Consignee consignee);
	
	//修改收件人信息
	public int editConsignee(Consignee consignee);
	
	//获取收件人信息
	Consignee getConsigneeMessage(Map<String,Object> map);
	
	//后台获取收件人列表
	List<Consignee> getConsigneeList(Map<String,Object> map);
	
	//获取收件人数量
	int getConsigneeCount();
	
	//根据司机名字获取司机信息
	List<Consignee> getConsigneeByName(String consigneeName);
}
