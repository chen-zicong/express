package com.logistics.express.service;

import com.logistics.express.entity.ApiResponse;
import com.logistics.express.entity.GoodTransportInformation;
import com.logistics.express.entity.PositionList;

import java.util.List;

public interface GoodTransportInformationService {
    int updateTransportInformation(List<GoodTransportInformation> goodTransportInformationList,String  orderNumber);
    ApiResponse<List<GoodTransportInformation>> insertTransportInformation(PositionList positionList);
    ApiResponse<List<GoodTransportInformation>> getTransportInformation(String orderNumber);

}
