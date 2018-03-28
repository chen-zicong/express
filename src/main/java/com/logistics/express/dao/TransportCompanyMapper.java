package com.logistics.express.dao;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.Driver;
import com.logistics.express.entity.TransportCompany;

public interface TransportCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TransportCompany record);

    int insertSelective(TransportCompany record);

    TransportCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TransportCompany record);

    int updateByPrimaryKey(TransportCompany record);
    
    List<TransportCompany> getTransportCompanyList(Map<String,Object> map);
    
    int getCompanyCount();
    
    String getNameById(int id);
    
    int addDriverToCompany(Driver driver);
    
    List<TransportCompany> getCompanyByName(String name);
}