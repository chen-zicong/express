package com.logistics.express.dao;

import java.util.List;

import com.logistics.express.entity.GoodTransportNode;

public interface GoodTransportNodeMapper {
    int deleteByPrimaryKey(Integer goodTransportNodeId);

    int insert(GoodTransportNode record);

    int insertSelective(GoodTransportNode record);

    GoodTransportNode selectByPrimaryKey(Integer goodTransportNodeId);

    int updateByPrimaryKeySelective(GoodTransportNode record);

    int updateByPrimaryKey(GoodTransportNode record);
    
    List<GoodTransportNode> getTransportNode(String goodOrderNumber);
}