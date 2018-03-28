package com.logistics.express.dao;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.GoodDetail;
import com.logistics.express.entity.GoodOrder;

public interface GoodDetailMapper {
    int deleteByPrimaryKey(String goodOrderNumber);

    int insert(GoodDetail record);

    int insertSelective(GoodDetail record);

    GoodDetail selectByPrimaryKey(Integer goodDetailId);

    int updateByPrimaryKeySelective(GoodDetail record);

    int updateByPrimaryKey(GoodDetail record);
    
    GoodDetail getGoodDetailByOrder(String goodOrderNumber);
    
    GoodDetail getImgByOrder(String goodOrderNumber);
    
}