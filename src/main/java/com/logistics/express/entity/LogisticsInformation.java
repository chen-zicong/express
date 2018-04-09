package com.logistics.express.entity;

import java.util.List;

public class LogisticsInformation {
    private String orderNumber ;
    private List<GoodTransportProcessPosition> positionList ;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<GoodTransportProcessPosition> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<GoodTransportProcessPosition> positionList) {
        this.positionList = positionList;
    }

}
