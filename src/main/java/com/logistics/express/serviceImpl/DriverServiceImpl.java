package com.logistics.express.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.express.dao.DriverMapper;
import com.logistics.express.entity.Driver;
import com.logistics.express.service.DriverService;

@Service("driverService")
public class DriverServiceImpl implements DriverService{
	
	
	@Resource
	DriverMapper driverDAO;
	
	
	@Override
	public int deleteByPrimaryKey(int id){
		
		return driverDAO.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Driver record){
		
		return driverDAO.insert(record);
	}

	@Override
	public int insertSelective(Driver record){
		
		return driverDAO.insertSelective(record);
	}

	@Override
	public Driver selectByPrimaryKey(int id){
		
		return driverDAO.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Driver record){
		
		return driverDAO.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Driver record){
		
		return driverDAO.updateByPrimaryKey(record);
	}

	@Override
	public List<Driver> getDriver(Map<String, Object> map) {
		
		return driverDAO.getDriver(map);
	}

	@Override
	public int getDriverCount(int status) {
		
		return driverDAO.getDriverCount(status);
	}

	@Override
	public int checkDriver(Driver driver) {
		
		return driverDAO.updateByPrimaryKeySelective(driver);
	}

	@Override
	public Driver getDriverById(int id) {
		
		return driverDAO.getDriverById(id);
	}

	@Override
	public int driverRegister(Driver driver) {
	
		return driverDAO.driverRegister(driver);
	}

	@Override
	public Driver getDriverStatus(Map<String, Object> map) {
		
		return driverDAO.getDriverStatus(map);
	}

	@Override
	public List<Driver> getDriverByName(Map<String, Object> map) {
		
		return driverDAO.getDriverByName(map);
	}

	@Override
	public String getImgById(int id) {
		
		return driverDAO.getImgById(id);
	}
	
	

}
