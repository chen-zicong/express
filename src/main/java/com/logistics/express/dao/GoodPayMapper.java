package com.logistics.express.dao;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.GoodPay;

public interface GoodPayMapper {
    int deleteByPrimaryKey(String goodOrderNumber);

    int insert(GoodPay record);

    int insertSelective(GoodPay record);

    GoodPay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodPay record);

    int updateByPrimaryKey(GoodPay record);
    
    List<GoodPay> getGoodPayList(Map<String,Object> map);
    
    int getCount();
}