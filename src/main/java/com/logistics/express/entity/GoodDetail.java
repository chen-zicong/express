package com.logistics.express.entity;
/**
 * Description:货物细节
 * @author HAO
 *
 */
public class GoodDetail {
    private Integer goodDetailId;

    private String goodDetailName;//货物名称

    private String goodDetailStandard;//获取规格

    private String goodDetailModel;

    private String goodDetailSize;//尺寸

    private String goodDetailWeight;//重量

    private String goodDetailLength;//长度

    private Integer goodDetailAmount;//数量

    private double goodDetailValue;//单价 

    private String goodDetailPrimitivePhoto;//原始照片
    
    private String goodDetailPackPhoto;//打包照片

    private Long goodDetailInsureValue;

    private Integer goodDetailWearingStatus;

    private Integer goodDetailInsureStatus;
    
    private String goodOrderNumber;//订单号
    

    public String getGoodDetailPackPhoto() {
		return goodDetailPackPhoto;
	}

	public void setGoodDetailPackPhoto(String goodDetailPackPhoto) {
		this.goodDetailPackPhoto = goodDetailPackPhoto;
	}

	public String getGoodOrderNumber() {
		return goodOrderNumber;
	}

	public void setGoodOrderNumber(String goodOrderNumber) {
		this.goodOrderNumber = goodOrderNumber;
	}

	public Integer getGoodDetailId() {
        return goodDetailId;
    }

    public void setGoodDetailId(Integer goodDetailId) {
        this.goodDetailId = goodDetailId;
    }

    public String getGoodDetailName() {
        return goodDetailName;
    }

    public void setGoodDetailName(String goodDetailName) {
        this.goodDetailName = goodDetailName == null ? null : goodDetailName.trim();
    }

    public String getGoodDetailStandard() {
        return goodDetailStandard;
    }

    public void setGoodDetailStandard(String goodDetailStandard) {
        this.goodDetailStandard = goodDetailStandard == null ? null : goodDetailStandard.trim();
    }

    public String getGoodDetailModel() {
        return goodDetailModel;
    }

    public void setGoodDetailModel(String goodDetailModel) {
        this.goodDetailModel = goodDetailModel == null ? null : goodDetailModel.trim();
    }

    public String getGoodDetailSize() {
        return goodDetailSize;
    }

    public void setGoodDetailSize(String goodDetailSize) {
        this.goodDetailSize = goodDetailSize == null ? null : goodDetailSize.trim();
    }

    public String getGoodDetailWeight() {
        return goodDetailWeight;
    }

    public void setGoodDetailWeight(String goodDetailWeight) {
        this.goodDetailWeight = goodDetailWeight == null ? null : goodDetailWeight.trim();
    }

    public String getGoodDetailLength() {
        return goodDetailLength;
    }

    public void setGoodDetailLength(String goodDetailLength) {
        this.goodDetailLength = goodDetailLength == null ? null : goodDetailLength.trim();
    }

    public Integer getGoodDetailAmount() {
        return goodDetailAmount;
    }

    public void setGoodDetailAmount(Integer goodDetailAmount) {
        this.goodDetailAmount = goodDetailAmount;
    }

    public double getGoodDetailValue() {
        return goodDetailValue;
    }

    public void setGoodDetailValue(Long goodDetailValue) {
        this.goodDetailValue = goodDetailValue;
    }

    public String getGoodDetailPrimitivePhoto() {
        return goodDetailPrimitivePhoto;
    }

    public void setGoodDetailPrimitivePhoto(String goodDetailPrimitivePhoto) {
        this.goodDetailPrimitivePhoto = goodDetailPrimitivePhoto == null ? null : goodDetailPrimitivePhoto.trim();
    }

    public Long getGoodDetailInsureValue() {
        return goodDetailInsureValue;
    }

    public void setGoodDetailInsureValue(Long goodDetailInsureValue) {
        this.goodDetailInsureValue = goodDetailInsureValue;
    }

    public Integer getGoodDetailWearingStatus() {
        return goodDetailWearingStatus;
    }

    public void setGoodDetailWearingStatus(Integer goodDetailWearingStatus) {
        this.goodDetailWearingStatus = goodDetailWearingStatus;
    }

    public Integer getGoodDetailInsureStatus() {
        return goodDetailInsureStatus;
    }

    public void setGoodDetailInsureStatus(Integer goodDetailInsureStatus) {
        this.goodDetailInsureStatus = goodDetailInsureStatus;
    }
}