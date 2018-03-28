package com.logistics.express.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.express.dao.GoodDetailMapper;
import com.logistics.express.entity.GoodDetail;
import com.logistics.express.service.GoodDetailService;

@Service("goodDetailService")
public class GoodDetailServiceImpl implements GoodDetailService {

	@Autowired
	private GoodDetailMapper goodDetailMapper;
	
	@Override
	public int addGoodDetail(GoodDetail goodDetail) {
		
		return goodDetailMapper.insertSelective(goodDetail);
	}

	@Override
	public GoodDetail getGoodDetailByOrder(String goodOrderNumber) {
		
		return goodDetailMapper.getGoodDetailByOrder(goodOrderNumber);
	}

	@Override
	public GoodDetail getImgByOrder(String goodOrderNumber) {
	
		return goodDetailMapper.getImgByOrder(goodOrderNumber);
	}

	@Override
	public int deleteDetail(String goodOrderNumber) {
		
		return goodDetailMapper.deleteByPrimaryKey(goodOrderNumber);
	}

}
