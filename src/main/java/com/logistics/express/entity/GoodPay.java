package com.logistics.express.entity;

public class GoodPay {
    private Integer id;

    private String goodOrderNumber;//订单号

    private String consigneeName;//收件人姓名

    private String consigneePhone;//收件人手机

    private String consigneeAddress;//收件人地址

    private String consignorName;//货主姓名

    private String consignorPhone;//货主手机

    private Float payPrice;//交付费用

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodOrderNumber() {
        return goodOrderNumber;
    }

    public void setGoodOrderNumber(String goodOrderNumber) {
        this.goodOrderNumber = goodOrderNumber == null ? null : goodOrderNumber.trim();
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone == null ? null : consigneePhone.trim();
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName == null ? null : consignorName.trim();
    }

    public String getConsignorPhone() {
        return consignorPhone;
    }

    public void setConsignorPhone(String consignorPhone) {
        this.consignorPhone = consignorPhone == null ? null : consignorPhone.trim();
    }

    public Float getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Float payPrice) {
        this.payPrice = payPrice;
    }
}