package com.logistics.express.dao;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.GoodTransportProcess;

public interface GoodTransportProcessMapper {
    int deleteByPrimaryKey(String goodOrderNumber);

    int insert(GoodTransportProcess record);

    int insertSelective(GoodTransportProcess record);

    GoodTransportProcess selectByPrimaryKey(Integer goodTransportProcessId);

    int updateByPrimaryKeySelective(GoodTransportProcess record);

    int updateByPrimaryKey(GoodTransportProcess record);
    
    GoodTransportProcess getTransportProcessMsg(String goodOrderNumber);
    
    List<GoodTransportProcess> getTransportProcessList(Map<String,Object> map);
    
    int getTransProcessListCount();
    
    List<GoodTransportProcess> getTransProcessByDriverId(int driverId);
    
    GoodTransportProcess getProcessByOrder(String goodOrderNumber);
}