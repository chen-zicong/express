package com.logistics.express.dao;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.GoodTransport;

public interface GoodTransportMapper {
    int deleteByPrimaryKey(String goodOrderNumber);

    int insertSelective(GoodTransport record);

    GoodTransport selectByPrimaryKey(Integer goodTransportId);

    int updateByOrderNumber(GoodTransport record);

    int updateConnectorMessage(Map<String,Object> map);
    
    int getDriverIdByOrder(String goodOrderNumber);
    
    List<GoodTransport> getConnectMessage(Map<String,Object> map);
    
    int getConnectMessageCount(int status);
    
    GoodTransport getOrderByNumber(String goodOrderNumber);
}