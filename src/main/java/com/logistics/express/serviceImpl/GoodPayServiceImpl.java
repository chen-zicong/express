package com.logistics.express.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.express.dao.GoodPayMapper;
import com.logistics.express.entity.GoodPay;
import com.logistics.express.service.GoodPayService;

@Service("goodPayService")
public class GoodPayServiceImpl implements GoodPayService {

	@Autowired
	private GoodPayMapper goodPayMapper;
	
	@Override
	public int addGoodPay(GoodPay goodPay) {
		return goodPayMapper.insertSelective(goodPay);
	}

	@Override
	public int deleteGoodPay(String goodOrderNumber) {
		
		return goodPayMapper.deleteByPrimaryKey(goodOrderNumber);
	}

	@Override
	public int updateGoodPay(GoodPay goodPay) {
		
		return goodPayMapper.updateByPrimaryKeySelective(goodPay);
	}

	@Override
	public List<GoodPay> getGoodPayList(Map<String, Object> map) {
		
		return goodPayMapper.getGoodPayList(map);
	}

	@Override
	public int getCount() {
		
		return goodPayMapper.getCount();
	}

}
