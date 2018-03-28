package com.logistics.express.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.express.dao.GoodTransportProcessMapper;
import com.logistics.express.entity.Driver;
import com.logistics.express.entity.GoodTransportProcess;
import com.logistics.express.service.GoodTransportProcessService;

@Service("goodTransportProcessService")
public class GoodTransportProcessServiceImpl implements
		GoodTransportProcessService {

	@Autowired
	private GoodTransportProcessMapper goodTransportProcessMapper;
	@Override
	public int addTransportProcessMessage(
			GoodTransportProcess goodTransportProcess) {
		
		return goodTransportProcessMapper.insertSelective(goodTransportProcess);
	}

	@Override
	public int updateTransportProcessMessage(
			GoodTransportProcess goodTransportProcess) {
		
		return goodTransportProcessMapper.updateByPrimaryKeySelective(goodTransportProcess);
	}

	@Override
	public GoodTransportProcess getTransportProcessMessage(
			String goodOrderNumber) {
		
		return goodTransportProcessMapper.getTransportProcessMsg(goodOrderNumber);
	}

	@Override
	public List<GoodTransportProcess> getTransportProcessList(
			Map<String, Object> map) {
		
		return goodTransportProcessMapper.getTransportProcessList(map);
	}

	@Override
	public int getTransProcessListCount() {
		
		return goodTransportProcessMapper.getTransProcessListCount();
	}

	@Override
	public List<GoodTransportProcess> getTransProcessByDriverId(int driverId) {

		return goodTransportProcessMapper.getTransProcessByDriverId(driverId);
	}

	@Override
	public GoodTransportProcess getProcessByOrder(String goodOrderNumber) {
		
		return goodTransportProcessMapper.getProcessByOrder(goodOrderNumber);
	}

	@Override
	public int deleteProcessMessage(String goodOrderNumber) {
		
		return goodTransportProcessMapper.deleteByPrimaryKey(goodOrderNumber);
	}


}
