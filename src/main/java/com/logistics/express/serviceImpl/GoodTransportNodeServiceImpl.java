package com.logistics.express.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.express.dao.GoodTransportNodeMapper;
import com.logistics.express.entity.GoodTransportNode;
import com.logistics.express.service.GoodTransportNodeService;
@Service("goodTransportNodeService")
public class GoodTransportNodeServiceImpl implements GoodTransportNodeService {

	@Autowired
	private GoodTransportNodeMapper goodTransportNodeMapper;
	
	@Override
	public List<GoodTransportNode> getTransportNode(String goodOrderNumber) {
		
		return goodTransportNodeMapper.getTransportNode(goodOrderNumber);
	}

}
