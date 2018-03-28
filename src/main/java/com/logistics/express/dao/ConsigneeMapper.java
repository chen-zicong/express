package com.logistics.express.dao;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.Consignee;

public interface ConsigneeMapper {
    int deleteByPrimaryKey(Integer consigneeId);

    int insert(Consignee record);

    int insertSelective(Consignee record);

    Consignee selectByPrimaryKey(Integer consigneeId);

    int updateByPrimaryKeySelective(Consignee record);

    int updateByPrimaryKey(Consignee record);
    
    Consignee getConsigneeMessage(Map<String,Object> map);
    
    List<Consignee> getConsigneeList(Map<String,Object> map);
    
    int getConsigneeCount();
    
    List<Consignee> getConsigneeByName(String consigneeName);
}