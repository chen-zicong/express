package com.logistics.express.entity;

import java.util.Date;
/**
 * Description:货物运输地址节点
 * @author HAO
 *
 */
public class GoodTransportNode {
    private Integer goodTransportNodeId;

    private Date goodTransportNodeTime;

    private String goodTransportNodeAddress;

    private String goodOrderNumber;

    public Integer getGoodTransportNodeId() {
        return goodTransportNodeId;
    }

    public void setGoodTransportNodeId(Integer goodTransportNodeId) {
        this.goodTransportNodeId = goodTransportNodeId;
    }

    public Date getGoodTransportNodeTime() {
        return goodTransportNodeTime;
    }

    public void setGoodTransportNodeTime(Date goodTransportNodeTime) {
        this.goodTransportNodeTime = goodTransportNodeTime;
    }

    public String getGoodTransportNodeAddress() {
        return goodTransportNodeAddress;
    }

    public void setGoodTransportNodeAddress(String goodTransportNodeAddress) {
        this.goodTransportNodeAddress = goodTransportNodeAddress == null ? null : goodTransportNodeAddress.trim();
    }

    public String getGoodOrderNumber() {
        return goodOrderNumber;
    }

    public void setGoodOrderNumber(String goodOrderNumber) {
        this.goodOrderNumber = goodOrderNumber == null ? null : goodOrderNumber.trim();
    }
}