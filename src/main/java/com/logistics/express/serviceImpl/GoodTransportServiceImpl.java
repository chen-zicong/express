package com.logistics.express.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.express.dao.GoodTransportMapper;
import com.logistics.express.entity.GoodTransport;
import com.logistics.express.service.GoodTransportService;

@Service("goodTransportService")
public class GoodTransportServiceImpl implements GoodTransportService {

	@Autowired
	private GoodTransportMapper goodTransportMapper;
	
	@Override
	public int addGoodTransportMessage(GoodTransport goodTransport) {
	
		return goodTransportMapper.insertSelective(goodTransport);
	}

	@Override
	public int addConnectorMessage(Map<String, Object> map) {
		
		return goodTransportMapper.updateConnectorMessage(map);
	}

	@Override
	public int updateTransportMessage(GoodTransport goodTransport) {
		
		return goodTransportMapper.updateByOrderNumber(goodTransport);
	}

	@Override
	public int getDriverIdByOrder(String goodOrderNumber) {
		
		return goodTransportMapper.getDriverIdByOrder(goodOrderNumber);
	}

	@Override
	public List<GoodTransport> getConnectMessage(Map<String, Object> map) {
	
		return goodTransportMapper.getConnectMessage(map);
	}

	@Override
	public int getConnectMessageCount(int status) {
		
		return goodTransportMapper.getConnectMessageCount(status);
	}

	@Override
	public GoodTransport getOrderByNumber(String goodOrderNumber) {
		
		return goodTransportMapper.getOrderByNumber(goodOrderNumber);
	}

	@Override
	public int deleteTransportMessage(String goodOrderNumber) {
		
		return goodTransportMapper.deleteByPrimaryKey(goodOrderNumber);
	}

}
