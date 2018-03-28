package com.logistics.express.entity;

import java.util.Date;
/**
 * Description:货物订单
 * @author HAO
 *
 */
public class GoodOrder {
    private String goodOrderNumber;//订单号

    private String consignorName;//货主姓名
    
    private String consignorPhone;//货主手机
    
    private int consigneeId;

    private String consigneeName;//收件人姓名
    
    private String consigneePhone;//收件人手机
    
    private String consigneeAddress;//收件人地址

    private String deliveryPlace;//发货地点

    private String receivePlace;//收货地点

    private Date deliveryTime;//发货时间
    
    private String deliverytime;

    private Date requireArriveTime;//要求到达时间
    
    private String requireArrivetime;
    
    private String deliveryWay;//发货方式
    
    private String goodTransportCarType;//运输车类型
    
    private int connectStatus;//0表示没有业务员接件，1表示已有业务员接件
    

    public int getConnectStatus() {
		return connectStatus;
	}

	public void setConnectStatus(int connectStatus) {
		this.connectStatus = connectStatus;
	}

	public String getDeliverytime() {
		return deliverytime;
	}

	public void setDeliverytime(String deliverytime) {
		this.deliverytime = deliverytime;
	}

	public String getRequireArrivetime() {
		return requireArrivetime;
	}

	public void setRequireArrivetime(String requireArrivetime) {
		this.requireArrivetime = requireArrivetime;
	}

	public int getConsigneeId() {
		return consigneeId;
	}

	public void setConsigneeId(int consigneeId) {
		this.consigneeId = consigneeId;
	}

	public String getGoodTransportCarType() {
		return goodTransportCarType;
	}

	public void setGoodTransportCarType(String goodTransportCarType) {
		this.goodTransportCarType = goodTransportCarType;
	}

	public String getDeliveryWay() {
		return deliveryWay;
	}

	public void setDeliveryWay(String deliveryWay) {
		this.deliveryWay = deliveryWay;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneePhone() {
		return consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getGoodOrderNumber() {
        return goodOrderNumber;
    }

    public void setGoodOrderNumber(String goodOrderNumber) {
        this.goodOrderNumber = goodOrderNumber == null ? null : goodOrderNumber.trim();
    }
    
    

    public String getConsignorName() {
		return consignorName;
	}

	public void setConsignorName(String consignorName) {
		this.consignorName = consignorName;
	}

	public String getConsignorPhone() {
		return consignorPhone;
	}

	public void setConsignorPhone(String consignorPhone) {
		this.consignorPhone = consignorPhone;
	}

	public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace == null ? null : deliveryPlace.trim();
    }

    public String getReceivePlace() {
        return receivePlace;
    }

    public void setReceivePlace(String receivePlace) {
        this.receivePlace = receivePlace == null ? null : receivePlace.trim();
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getRequireArriveTime() {
        return requireArriveTime;
    }

    public void setRequireArriveTime(Date requireArriveTime) {
        this.requireArriveTime = requireArriveTime;
    }
}