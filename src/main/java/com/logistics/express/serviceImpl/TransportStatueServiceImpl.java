package com.logistics.express.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.logistics.express.dao.TransportStatueMapper;
import com.logistics.express.entity.TransportStatue;
import com.logistics.express.service.TransportStatueService;


@Service("transportStatueService")
public class TransportStatueServiceImpl implements TransportStatueService{
	@Resource 
	TransportStatueMapper transportStatueDao;
	
	@Override
	public int deleteByPrimaryKey(Integer driverId){
	
		return transportStatueDao.deleteByPrimaryKey(driverId);
	}

	@Override
    public int insert(TransportStatue record){
		
		return transportStatueDao.insert(record);
	}

	@Override
    public int insertSelective(TransportStatue record){
		
		return transportStatueDao.insertSelective(record);
	}

	@Override
    public TransportStatue selectByPrimaryKey(Integer driverId){
		
		return transportStatueDao.selectByPrimaryKey(driverId);
	}

	@Override
    public int updateByPrimaryKeySelective(TransportStatue record){
		
		return transportStatueDao.updateByPrimaryKeySelective(record);
	}

	@Override
    public int updateByPrimaryKey(TransportStatue record){
		
		return transportStatueDao.updateByPrimaryKey(record);
	}
	

}
