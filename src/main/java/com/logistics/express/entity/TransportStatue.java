package com.logistics.express.entity;

import java.util.Date;

public class TransportStatue {
    private Integer driverId;

    private String plateNum;

    private Integer carStatue;

    private Date lastTime;

    private Double lng;

    private Double lat;

    private String nowLocation;

    private String beginLocation;

    private String goalLocation;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum == null ? null : plateNum.trim();
    }

    public Integer getCarStatue() {
        return carStatue;
    }

    public void setCarStatue(Integer carStatue) {
        this.carStatue = carStatue;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getNowLocation() {
        return nowLocation;
    }

    public void setNowLocation(String nowLocation) {
        this.nowLocation = nowLocation == null ? null : nowLocation.trim();
    }

    public String getBeginLocation() {
        return beginLocation;
    }

    public void setBeginLocation(String beginLocation) {
        this.beginLocation = beginLocation == null ? null : beginLocation.trim();
    }

    public String getGoalLocation() {
        return goalLocation;
    }

    public void setGoalLocation(String goalLocation) {
        this.goalLocation = goalLocation == null ? null : goalLocation.trim();
    }
}