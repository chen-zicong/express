package com.logistics.express.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.express.dao.GoodOrderMapper;
import com.logistics.express.entity.GoodOrder;
import com.logistics.express.service.GoodOrderService;


@Service("goodOrderService")
public class GoodOrderServiceImpl implements GoodOrderService {

	@Autowired
	private GoodOrderMapper goodOrderMapper;
	
	@Override 
	public int addGoodOrder(GoodOrder goodOrder) {
		
		return goodOrderMapper.insertSelective(goodOrder);
	}

	@Override
	public List<GoodOrder> getOrderList(Map<String, Object> map) {
		
		return goodOrderMapper.getOrderList(map);
	}

	@Override
	public int getOrderCount() {
		
		return goodOrderMapper.getOrderCount();
	}

	@Override
	public Date getRequireArrivetime(String goodOrderNumber) {
		
		return goodOrderMapper.getRequireArrivetime(goodOrderNumber);
	}

	@Override
	public int updateOrder(GoodOrder goodOrder) {
		
		return goodOrderMapper.updateByPrimaryKeySelective(goodOrder);
	}

	@Override
	public GoodOrder getOrderByNumber(String goodOrderNumber) {
		
		return goodOrderMapper.getOrderByNumber(goodOrderNumber);
	}

	@Override
	public int deleteOrder(String goodOrderNumber) {
		
		return goodOrderMapper.deleteByPrimaryKey(goodOrderNumber);
	}

}
