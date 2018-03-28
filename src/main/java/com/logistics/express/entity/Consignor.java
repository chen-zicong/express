package com.logistics.express.entity;
/**
 * Description:货主
 * @author HAO
 *
 */
public class Consignor {
    private Integer consignorId;

    private String consignorName;//货主姓名

    private String consignorPhone;//货主手机

    private String consignorAddress;//货主地址

    private String consignorSex;

    public Integer getConsignorId() {
        return consignorId;
    }

    public void setConsignorId(Integer consignorId) {
        this.consignorId = consignorId;
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

    public String getConsignorAddress() {
        return consignorAddress;
    }

    public void setConsignorAddress(String consignorAddress) {
        this.consignorAddress = consignorAddress == null ? null : consignorAddress.trim();
    }

    public String getConsignorSex() {
        return consignorSex;
    }

    public void setConsignorSex(String consignorSex) {
        this.consignorSex = consignorSex == null ? null : consignorSex.trim();
    }
}