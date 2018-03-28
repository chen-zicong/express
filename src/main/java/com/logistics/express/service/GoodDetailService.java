package com.logistics.express.service;

import com.logistics.express.entity.GoodDetail;

public interface GoodDetailService {

	//货主录入订单需求细节，如物品名称尺寸重量等
	public int addGoodDetail(GoodDetail goodDetail);
	
	//根据订单号获取货物细节信息
	public GoodDetail getGoodDetailByOrder(String goodOrderNumber);
	
	//根据订单号获取图片路径
	public GoodDetail getImgByOrder(String goodOrderNumber);
	
	//删除订单
	public int deleteDetail(String goodOrderNumber);
}
