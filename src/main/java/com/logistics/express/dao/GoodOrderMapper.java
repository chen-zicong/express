package com.logistics.express.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.logistics.express.entity.GoodOrder;

public interface GoodOrderMapper {
    int deleteByPrimaryKey(String goodOrderNumber);

    int insert(GoodOrder record);

    int insertSelective(GoodOrder record);

    GoodOrder selectByPrimaryKey(String goodOrderNumber);

    int updateByPrimaryKeySelective(GoodOrder record);

    int updateByPrimaryKey(GoodOrder record);
    
    List<GoodOrder> getOrderList(Map<String,Object> map);
    
    int getOrderCount();
    
    Date getRequireArrivetime(String goodOrderNumber);
    
    GoodOrder getOrderByNumber(String goodOrderNumber);
}