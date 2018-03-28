package com.logistics.express.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.express.dao.ConsigneeMapper;
import com.logistics.express.entity.Consignee;
import com.logistics.express.service.ConsigneeService;
@Service("consigneeService")
public class ConsigneeServiceImpl implements ConsigneeService {

	@Autowired
	private ConsigneeMapper consigneeMapper;
	
	@Override
	public int addConsignee(Consignee consignee) {
		
		return consigneeMapper.insertSelective(consignee);
	}

	@Override
	public int editConsignee(Consignee consignee) {
		
		return consigneeMapper.updateByPrimaryKeySelective(consignee);
	}

	@Override
	public Consignee getConsigneeMessage(Map<String, Object> map) {
		
		return consigneeMapper.getConsigneeMessage(map);
	}

	@Override
	public List<Consignee> getConsigneeList(Map<String, Object> map) {
		
		return consigneeMapper.getConsigneeList(map);
	}

	@Override
	public int getConsigneeCount() {
	
		return consigneeMapper.getConsigneeCount();
	}

	@Override
	public List<Consignee> getConsigneeByName(String consigneeName) {
		
		return consigneeMapper.getConsigneeByName(consigneeName);
	}
	
	

}
