package com.logistics.express.dao;

import com.logistics.express.entity.GoodTransportInformation;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GoodTransportInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodTransportInformation record);

    int insertSelective(GoodTransportInformation record);

    GoodTransportInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodTransportInformation record);

    int updateByPrimaryKey(GoodTransportInformation record);

    int updateByOrderNumberSelective( GoodTransportInformation goodTransportInformation);

    List<GoodTransportInformation> getInformationByOrderNumber(String orderNumber);

    int deleteByOrderNumber (String orderNumber);

    int updateByOrderNumber(@RequestParam("goodTransportInformation") GoodTransportInformation goodTransportInformation,@RequestParam("goodTransportInformation") String OrderNumber);

}