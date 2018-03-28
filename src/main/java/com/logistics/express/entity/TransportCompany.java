package com.logistics.express.entity;
/**
 * Description:运输公司
 * @author HAO
 *
 */
public class TransportCompany {
    private Integer id;

    private String transportCompanyName;//承运公司名称

    private String transportCompanyAddress;//承运公司地址

    private String transportCompanyContact;//承运公司联系人

    private String transportCompanyContactPhone;//承运公司联系电话

    private Integer transportCompanyCrewNumber;//职员人数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransportCompanyName() {
        return transportCompanyName;
    }

    public void setTransportCompanyName(String transportCompanyName) {
        this.transportCompanyName = transportCompanyName == null ? null : transportCompanyName.trim();
    }

    public String getTransportCompanyAddress() {
        return transportCompanyAddress;
    }

    public void setTransportCompanyAddress(String transportCompanyAddress) {
        this.transportCompanyAddress = transportCompanyAddress == null ? null : transportCompanyAddress.trim();
    }

    public String getTransportCompanyContact() {
        return transportCompanyContact;
    }

    public void setTransportCompanyContact(String transportCompanyContact) {
        this.transportCompanyContact = transportCompanyContact == null ? null : transportCompanyContact.trim();
    }

    public String getTransportCompanyContactPhone() {
        return transportCompanyContactPhone;
    }

    public void setTransportCompanyContactPhone(String transportCompanyContactPhone) {
        this.transportCompanyContactPhone = transportCompanyContactPhone == null ? null : transportCompanyContactPhone.trim();
    }

    public Integer getTransportCompanyCrewNumber() {
        return transportCompanyCrewNumber;
    }

    public void setTransportCompanyCrewNumber(Integer transportCompanyCrewNumber) {
        this.transportCompanyCrewNumber = transportCompanyCrewNumber;
    }
}