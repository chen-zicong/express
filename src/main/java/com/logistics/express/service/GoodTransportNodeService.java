package com.logistics.express.service;

import java.util.List;

import com.logistics.express.entity.GoodTransportNode;

public interface GoodTransportNodeService {
	
	//根据单号查询货物节点信息
	public List<GoodTransportNode> getTransportNode(String goodOrderNumber);

}
