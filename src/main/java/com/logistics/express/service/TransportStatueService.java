package com.logistics.express.service;

import com.logistics.express.entity.TransportStatue;

public interface TransportStatueService {
	
	public int deleteByPrimaryKey(Integer driverId);

    public int insert(TransportStatue record);

    public int insertSelective(TransportStatue record);

    public TransportStatue selectByPrimaryKey(Integer driverId);

    public int updateByPrimaryKeySelective(TransportStatue record);

    public int updateByPrimaryKey(TransportStatue record);

}
