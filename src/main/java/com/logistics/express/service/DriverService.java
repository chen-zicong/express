package com.logistics.express.service;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.Driver;

public interface DriverService {
	
	public int deleteByPrimaryKey(int id);

	public int insert(Driver record);

	public int insertSelective(Driver record);

	public Driver selectByPrimaryKey(int id);

	public int updateByPrimaryKeySelective(Driver record);

	public int updateByPrimaryKey(Driver record);
	
	//获取司机列表
	public List<Driver> getDriver(Map<String,Object> map);
	
	//获取司机数量
	public int getDriverCount(int status);
	
	//审核司机
	public int checkDriver(Driver driver);
	
	//根据司机id获取司机信息
	public Driver getDriverById(int id);
	
	//司机注册
	public int driverRegister(Driver driver);
	
	//微信端验证司机身份
	public Driver getDriverStatus(Map<String,Object> map);
	
	//根据司机名称获取司机信息
	public List<Driver> getDriverByName(Map<String,Object> map);
	
	//根据司机id获取身份证照片路径
	public String getImgById(int id);
}
