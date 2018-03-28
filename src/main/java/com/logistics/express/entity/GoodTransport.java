package com.logistics.express.entity;

import java.util.Date;
/**
 * Description:货物运输
 * @author HAO
 *
 */
public class GoodTransport {
    private Integer goodTransportId;

    private Integer goodTransportConsigneeId;

    private String goodTransportConnectorName;//接件员姓名

    private String goodTransportConnectorPhone;//接件人手机

    private Date goodTransportConnectionTime;
    
    private Date goodTransportTransferTime;

    private Date goodTransportDispatchTime;

    private String goodTransportDispatchWay;//发运方式

    private Integer goodTransportFirmId;

    private Integer goodTransportPremiumId;

    private Integer goodTransportDriverId;//运输司机id

    private Date goodTransportArriveEstimatedTime;

    private String goodTransportConsigneeName;//收件人姓名

    private String goodTransportConsigneePhone;//收件人手机

    private Date goodTransportRecieveTime;
    
    private String goodOrderNumber;//订单号
    
    private String goodTransportCarBrand;
    
    private String goodTransportCarVersion;
    
    private String goodTransportCarStatus;
    
    private String goodTransportCarLicence;
    
    private float goodTransportprice;
    
    private String requireArrivetime;
    
    private int auditeStatus;//审核状态
    
    private String goodTransportPrice;//货运价格
    
    private String driverName;//司机姓名
    
    private String driverPhone;//司机手机
    
    private String unAuditeReason;//未审核通过原因
    
    public String getUnAuditeReason() {
		return unAuditeReason;
	}

	public void setUnAuditeReason(String unAuditeReason) {
		this.unAuditeReason = unAuditeReason;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}

	public String getGoodTransportPrice() {
		return goodTransportPrice;
	}

	public void setGoodTransportPrice(String goodTransportPrice) {
		this.goodTransportPrice = goodTransportPrice;
	}

	public int getAuditeStatus() {
		return auditeStatus;
	}

	public void setAuditeStatus(int auditeStatus) {
		this.auditeStatus = auditeStatus;
	}

	public String getRequireArrivetime() {
		return requireArrivetime;
	}

	public void setRequireArrivetime(String requireArrivetime) {
		this.requireArrivetime = requireArrivetime;
	}

	public float getGoodTransportprice() {
		return goodTransportprice;
	}

	public void setGoodTransportprice(float goodTransportPrice) {
		this.goodTransportprice = goodTransportPrice;
	}

	public String getGoodTransportCarBrand() {
		return goodTransportCarBrand;
	}

	public void setGoodTransportCarBrand(String goodTransportCarBrand) {
		this.goodTransportCarBrand = goodTransportCarBrand;
	}

	public String getGoodTransportCarVersion() {
		return goodTransportCarVersion;
	}

	public void setGoodTransportCarVersion(String goodTransportCarVersion) {
		this.goodTransportCarVersion = goodTransportCarVersion;
	}

	public String getGoodTransportCarStatus() {
		return goodTransportCarStatus;
	}

	public void setGoodTransportCarStatus(String goodTransportCarStatus) {
		this.goodTransportCarStatus = goodTransportCarStatus;
	}

	public String getGoodTransportCarLicence() {
		return goodTransportCarLicence;
	}

	public void setGoodTransportCarLicence(String goodTransportCarLicence) {
		this.goodTransportCarLicence = goodTransportCarLicence;
	}

	public Date getGoodTransportTransferTime() {
		return goodTransportTransferTime;
	}

	public void setGoodTransportTransferTime(Date goodTransportTransferTime) {
		this.goodTransportTransferTime = goodTransportTransferTime;
	}

	public String getGoodOrderNumber() {
		return goodOrderNumber;
	}

	public void setGoodOrderNumber(String goodOrderNumber) {
		this.goodOrderNumber = goodOrderNumber;
	}

	public Integer getGoodTransportId() {
        return goodTransportId;
    }

    public void setGoodTransportId(Integer goodTransportId) {
        this.goodTransportId = goodTransportId;
    }

    public Integer getGoodTransportConsigneeId() {
        return goodTransportConsigneeId;
    }

    public void setGoodTransportConsigneeId(Integer goodTransportConsigneeId) {
        this.goodTransportConsigneeId = goodTransportConsigneeId;
    }


    public String getGoodTransportConnectorName() {
        return goodTransportConnectorName;
    }

    public void setGoodTransportConnectorName(String goodTransportConnectorName) {
        this.goodTransportConnectorName = goodTransportConnectorName == null ? null : goodTransportConnectorName.trim();
    }

    public String getGoodTransportConnectorPhone() {
        return goodTransportConnectorPhone;
    }

    public void setGoodTransportConnectorPhone(String goodTransportConnectorPhone) {
        this.goodTransportConnectorPhone = goodTransportConnectorPhone == null ? null : goodTransportConnectorPhone.trim();
    }

    public Date getGoodTransportConnectionTime() {
        return goodTransportConnectionTime;
    }

    public void setGoodTransportConnectionTime(Date goodTransportConnectionTime) {
        this.goodTransportConnectionTime = goodTransportConnectionTime;
    }

    public Date getGoodTransportDispatchTime() {
        return goodTransportDispatchTime;
    }

    public void setGoodTransportDispatchTime(Date goodTransportDispatchTime) {
        this.goodTransportDispatchTime = goodTransportDispatchTime;
    }

    public String getGoodTransportDispatchWay() {
        return goodTransportDispatchWay;
    }

    public void setGoodTransportDispatchWay(String goodTransportDispatchWay) {
        this.goodTransportDispatchWay = goodTransportDispatchWay;
    }

    public Integer getGoodTransportFirmId() {
        return goodTransportFirmId;
    }

    public void setGoodTransportFirmId(Integer goodTransportFirmId) {
        this.goodTransportFirmId = goodTransportFirmId;
    }

    public Integer getGoodTransportPremiumId() {
        return goodTransportPremiumId;
    }

    public void setGoodTransportPremiumId(Integer goodTransportPremiumId) {
        this.goodTransportPremiumId = goodTransportPremiumId;
    }

    public Integer getGoodTransportDriverId() {
        return goodTransportDriverId;
    }

    public void setGoodTransportDriverId(Integer goodTransportDriverId) {
        this.goodTransportDriverId = goodTransportDriverId;
    }

    public Date getGoodTransportArriveEstimatedTime() {
        return goodTransportArriveEstimatedTime;
    }

    public void setGoodTransportArriveEstimatedTime(Date goodTransportArriveEstimatedTime) {
        this.goodTransportArriveEstimatedTime = goodTransportArriveEstimatedTime;
    }

    public String getGoodTransportConsigneeName() {
        return goodTransportConsigneeName;
    }

    public void setGoodTransportConsigneeName(String goodTransportConsigneeName) {
        this.goodTransportConsigneeName = goodTransportConsigneeName == null ? null : goodTransportConsigneeName.trim();
    }

    public String getGoodTransportConsigneePhone() {
        return goodTransportConsigneePhone;
    }

    public void setGoodTransportConsigneePhone(String goodTransportConsigneePhone) {
        this.goodTransportConsigneePhone = goodTransportConsigneePhone == null ? null : goodTransportConsigneePhone.trim();
    }

    public Date getGoodTransportRecieveTime() {
        return goodTransportRecieveTime;
    }

    public void setGoodTransportRecieveTime(Date goodTransportRecieveTime) {
        this.goodTransportRecieveTime = goodTransportRecieveTime;
    }

	public GoodTransport() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}