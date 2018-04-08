package com.logistics.express.dao;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.Consignee;
import com.logistics.express.entity.Consignor;

public interface ConsignorMapper {
    int deleteByPrimaryKey(Integer consignorId);

    int insert(Consignor record);

    int insertSelective(Consignor record);

    Consignor selectByPrimaryKey(Integer consignorId);

    int updateByPrimaryKeySelective(Consignor record);

    int updateByPrimaryKey(Consignor record);

    Consignor getConsignorMsg(Map<String, Object> map);

    List<Consignor> getConsignorList(Map<String, Object> map);

    int getConsignorCount();

    List<Consignor> getConsignorByName(String consignorName);
}