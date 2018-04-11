package com.logistics.express.entity;

import java.util.ArrayList;
import java.util.List;

public class UpdateGoodTransportInformation {
    private List<GoodTransportInformation> goodTransportInformationList = new ArrayList<>();
    private String orderNumber;

    public UpdateGoodTransportInformation() {
    }

    public List<GoodTransportInformation> getGoodTransportInformationList() {
        return goodTransportInformationList;
    }

    public void setGoodTransportInformationList(List<GoodTransportInformation> goodTransportInformationList) {
        this.goodTransportInformationList = goodTransportInformationList;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
