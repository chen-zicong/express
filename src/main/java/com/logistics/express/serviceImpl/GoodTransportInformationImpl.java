package com.logistics.express.serviceImpl;

import com.logistics.express.dao.GoodTransportInformationMapper;
import com.logistics.express.entity.*;
import com.logistics.express.service.GoodTransportInformationService;
import com.logistics.express.unity.DateUnti;
import com.logistics.express.unity.GaodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("GoodTransportInformationService")
public class GoodTransportInformationImpl implements GoodTransportInformationService {
    @Autowired
    private GoodTransportInformationMapper goodTransportInformationMapper;

    @Override
    public int updateTransportInformation(GoodTransportInformation goodTransportInformation) {
        return goodTransportInformationMapper.updateByOrderNumberSelective(goodTransportInformation);
    }

    @Override
    public ApiResponse<List<GoodTransportInformation>> insertTransportInformation(PositionList positionList) {
        LocalDateTime localDateTime = LocalDateTime.now();
        GoodTransportInformation goodTransportInformation = null;
        ApiResponse<List<GoodTransportInformation>> response = null;
        List<GoodTransportInformation> goodTransportInformationList = new ArrayList<>();
        String[] cities = positionList.getCities();
        String orderNumber = positionList.getOrderNumber();

        goodTransportInformationMapper.deleteByOrderNumber(orderNumber);

        for (int i = 0; i < cities.length - 1; i++) {
            goodTransportInformation = new GoodTransportInformation();
            goodTransportInformation.setOrderNumber(orderNumber);
            String startLocal = cities[i];
            String endLocal = cities[i + 1];
            String start = GaodeUtil.getLonLat(startLocal);
            String end = GaodeUtil.getLonLat(endLocal);
            Long distance = GaodeUtil.getDistance(start, end) / 1000;
            // processPosition.setLocation(startLocal);
            goodTransportInformation.setInformation("货物已到达" + startLocal + ", 正在从" + startLocal + "发往" + endLocal + "途中");
            //  String date = DateUnti.dateToStr(time, DateUnti.DATE_HM_13);
            String date = DateUnti.LocalDateTimeToStr(localDateTime);
            goodTransportInformation.setDate(date);

            goodTransportInformationList.add(goodTransportInformation);
            int insert = goodTransportInformationMapper.insert(goodTransportInformation);
            if (insert == 0) {
                return response = new ApiResponse<>(0, "添加失败");
            }

            localDateTime = localDateTime.plusHours((long) (distance / 16.6));
        }


        goodTransportInformation = new GoodTransportInformation();
        goodTransportInformation.setOrderNumber(orderNumber);
        //  processPosition.setLocation(positions[positions.length - 1]);
        goodTransportInformation.setInformation("到达" + cities[cities.length - 1]);
        String date = DateUnti.LocalDateTimeToStr(localDateTime);
        goodTransportInformation.setDate(date);
        goodTransportInformationList.add(goodTransportInformation);
        int insert = goodTransportInformationMapper.insert(goodTransportInformation);
        if (insert == 0) {
            return response = new ApiResponse<>(0, "添加失败");
        }
        response = new ApiResponse<List<GoodTransportInformation>>(1, goodTransportInformationList, "位置信息列表");
        return response;
    }

    @Override
    public ApiResponse<List<GoodTransportInformation>> getTransportInformation(String orderNumber) {
        ApiResponse<List<GoodTransportInformation>> response = null;
        List<GoodTransportInformation> informationByOrderNumber = goodTransportInformationMapper.getInformationByOrderNumber(orderNumber);
        if (informationByOrderNumber.size() == 0) {
            return response = new ApiResponse<List<GoodTransportInformation>>(0, "获取失败,检查订单号是否错误");
        }

        return response = new ApiResponse<>(1, informationByOrderNumber, "成功");
    }

}

