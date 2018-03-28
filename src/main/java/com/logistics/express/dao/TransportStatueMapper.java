package com.logistics.express.dao;

import com.logistics.express.entity.TransportStatue;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransportStatueMapper {

    int deleteByPrimaryKey(Integer driverId);

    int insert(TransportStatue record);

    int insertSelective(TransportStatue record);

    TransportStatue selectByPrimaryKey(Integer driverId);

    int updateByPrimaryKeySelective(TransportStatue record);

    int updateByPrimaryKey(TransportStatue record);
}