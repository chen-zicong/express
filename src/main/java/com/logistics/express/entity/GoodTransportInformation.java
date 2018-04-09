package com.logistics.express.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class GoodTransportInformation {


    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String orderNumber;

    private String information;

    private String date;

    public GoodTransportInformation(Integer id, String orderNumber, String information, String date) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.information = information;
        this.date = date;
    }

    public GoodTransportInformation() {
        super();
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information == null ? null : information.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }
}