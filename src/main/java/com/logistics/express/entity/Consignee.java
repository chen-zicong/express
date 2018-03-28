package com.logistics.express.entity;
/**
 * Description:收件人
 * @author HAO
 *
 */
public class Consignee {
    private Integer consigneeId;

    private String consigneeName;//收件人姓名

    private String consigneePhone;//收件人手机

    private String reservepersonName;

    private String reservepersonPhone;

    private String consigneeAddress;//收件人地址

    public Integer getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(Integer consigneeId) {
        this.consigneeId = consigneeId;
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

    public String getReservepersonName() {
        return reservepersonName;
    }

    public void setReservepersonName(String reservepersonName) {
        this.reservepersonName = reservepersonName == null ? null : reservepersonName.trim();
    }

    public String getReservepersonPhone() {
        return reservepersonPhone;
    }

    public void setReservepersonPhone(String reservepersonPhone) {
        this.reservepersonPhone = reservepersonPhone == null ? null : reservepersonPhone.trim();
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }
}