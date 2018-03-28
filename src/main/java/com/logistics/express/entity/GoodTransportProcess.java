package com.logistics.express.entity;

import java.util.Date;
/**
 * Description:货物运输过程
 * @author HAO
 *
 */
public class GoodTransportProcess {
    private Integer goodTransportProcessId;

    private Integer goodTransportProcessStatus;

    private String goodTransportProcessPosition;//当前运输位置

    private String goodTransportProcessException;
    
    private String goodOrderNumber;//订单号
    
    private Date goodTransportProcesstime;//当前运输时间
    
    private String goodTransportProcessTime;
    
    private int goodTransportDriverId;//运输司机id
    
    private String driverName;//运输司机姓名
    
    private String driverPhone;//运输司机手机
    
    private int auditeStatus;//审核状态

    public int getAuditeStatus() {
		return auditeStatus;
	}

	public void setAuditeStatus(int auditeStatus) {
		this.auditeStatus = auditeStatus;
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

	public int getGoodTransportDriverId() {
		return goodTransportDriverId;
	}

	public void setGoodTransportDriverId(int goodTransportDriverId) {
		this.goodTransportDriverId = goodTransportDriverId;
	}

	public Date getGoodTransportProcesstime() {
		return goodTransportProcesstime;
	}

	public void setGoodTransportProcesstime(Date goodTransportProcesstime) {
		this.goodTransportProcesstime = goodTransportProcesstime;
	}

	public String getGoodTransportProcessTime() {
		return goodTransportProcessTime;
	}

	public void setGoodTransportProcessTime(String goodTransportProcessTime) {
		this.goodTransportProcessTime = goodTransportProcessTime;
	}

	public String getGoodOrderNumber() {
		return goodOrderNumber;
	}

	public void setGoodOrderNumber(String goodOrderNumber) {
		this.goodOrderNumber = goodOrderNumber;
	}

	public Integer getGoodTransportProcessId() {
        return goodTransportProcessId;
    }

    public void setGoodTransportProcessId(Integer goodTransportProcessId) {
        this.goodTransportProcessId = goodTransportProcessId;
    }

    public Integer getGoodTransportProcessStatus() {
        return goodTransportProcessStatus;
    }

    public void setGoodTransportProcessStatus(Integer goodTransportProcessStatus) {
        this.goodTransportProcessStatus = goodTransportProcessStatus;
    }

    public String getGoodTransportProcessPosition() {
        return goodTransportProcessPosition;
    }

    public void setGoodTransportProcessPosition(String goodTransportProcessPosition) {
        this.goodTransportProcessPosition = goodTransportProcessPosition == null ? null : goodTransportProcessPosition.trim();
    }

    public String getGoodTransportProcessException() {
        return goodTransportProcessException;
    }

    public void setGoodTransportProcessException(String goodTransportProcessException) {
        this.goodTransportProcessException = goodTransportProcessException == null ? null : goodTransportProcessException.trim();
    }
}