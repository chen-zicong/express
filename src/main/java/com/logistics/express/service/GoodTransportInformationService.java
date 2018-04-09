package com.logistics.express.service;

import com.logistics.express.entity.ApiResponse;
import com.logistics.express.entity.GoodTransportInformation;
import com.logistics.express.entity.GoodTransportProcessPosition;
import com.logistics.express.entity.PositionList;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GoodTransportInformationService {
    int updateTransportInformation(GoodTransportInformation goodTransportInformation);
    ApiResponse<List<GoodTransportInformation>> insertTransportInformation(PositionList positionList);
    ApiResponse<List<GoodTransportInformation>> getTransportInformation(String orderNumber);
}
