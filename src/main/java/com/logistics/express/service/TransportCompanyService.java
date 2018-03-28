package com.logistics.express.service;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.Driver;
import com.logistics.express.entity.TransportCompany;

public interface TransportCompanyService {
	
	//新增运输公司
	public int addCompany(TransportCompany transportCompany);
	
	//获取运输公司列表
	public List<TransportCompany> getTransportCompany(Map<String,Object> map);
	
	//获取运输公司数量
	public int getCompanyCount();
	
	//根据Id获取公司名称
	public String getNameById(int id);
	
	//公司添加司机
	public int addDriverToCompany(Driver driver);

	//根据公司名称获取公司信息
	public List<TransportCompany> getCompanyByName(String name);
}
