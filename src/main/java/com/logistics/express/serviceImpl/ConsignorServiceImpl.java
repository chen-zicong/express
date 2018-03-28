package com.logistics.express.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.express.dao.ConsignorMapper;
import com.logistics.express.entity.Consignor;
import com.logistics.express.service.ConsignorService;

@Service("consignorService")
public class ConsignorServiceImpl implements ConsignorService {
	
	@Autowired
	private ConsignorMapper consignorMapper;

	@Override
	public int addConsignor(Consignor consignor) {
		
		return consignorMapper.insertSelective(consignor);
	}

	@Override
	public int editConsignor(Consignor consignor) {
		
		return consignorMapper.updateByPrimaryKeySelective(consignor);
	}

	@Override
	public Consignor getConsignorMsg(Map<String, Object> map) {
		
		return consignorMapper.getConsignorMsg(map);
	}

	@Override
	public List<Consignor> getConsignorList(Map<String, Object> map) {
	
		return consignorMapper.getConsignorList(map);
	}

	@Override
	public int getConsignorCount() {
		
		return consignorMapper.getConsignorCount();
	}

	@Override
	public List<Consignor> getConsignorByName(String consignorName) {
		
		return consignorMapper.getConsignorByName(consignorName);
	}

}
