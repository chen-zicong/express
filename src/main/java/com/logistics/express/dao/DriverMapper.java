package com.logistics.express.dao;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.Driver;

public interface DriverMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Driver record);

    int insertSelective(Driver record);

    Driver selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Driver record);

    int updateByPrimaryKey(Driver record);
    
    List<Driver> getDriver(Map<String,Object> map);
    
    int getDriverCount(int status);
    
    String getNameById(int id);
    
    Driver getDriverById(int id);
    
    int driverRegister(Driver driver);
    
    int upProcessMessage(Map<String,Object> map);
    
    Driver getDriverStatus(Map<String,Object> map);
    
    List<Driver> getDriverByName(Map<String,Object> map);
    
    String getImgById(int id);
}