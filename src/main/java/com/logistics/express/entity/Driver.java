package com.logistics.express.entity;
/**
 * Description:司机
 * @author HAO
 *
 */
public class Driver {
    private Integer id;

    private String driverName;

    private int driverSex;
    
    private String sex;

    private String driverIdCard;//司机身份证号

    private String driverIdPic;//身份证图片路径

    private String driverPhone;

    private Integer comId;//所属承运公司id

    private String plateNumber;//运输车牌号

    private String carType;//运输车类型
    
    private int status;//审核状态
    
    private String company;//所属承运公司名称
    

    public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getId() {
        return id;
    }
	
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public int getDriverSex() {
        return driverSex;
    }

    public void setDriverSex(int driverSex) {
        this.driverSex = driverSex;
    }

    public String getDriverIdCard() {
        return driverIdCard;
    }

    public void setDriverIdCard(String driverIdCard) {
        this.driverIdCard = driverIdCard == null ? null : driverIdCard.trim();
    }

    public String getDriverIdPic() {
        return driverIdPic;
    }

    public void setDriverIdPic(String driverIdPic) {
        this.driverIdPic = driverIdPic == null ? null : driverIdPic.trim();
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone == null ? null : driverPhone.trim();
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber == null ? null : plateNumber.trim();
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }
}