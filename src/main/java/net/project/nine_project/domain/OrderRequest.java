package net.project.nine_project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

//接收前端参数的类，未来里面还可以添加优惠券信息等
public class OrderRequest {
    //前端的下划线参数转成后端驼峰
    @JsonProperty("sce_id")
    private int sceId;
    @JsonProperty("hotel_id")
    private int hotelId;

    private int type1; //1为参观景点的费用；2为酒店的费用

    private int state;

    @JsonProperty("people_number")
    private Integer peopleNumber;

    @JsonProperty("preset_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date presetTime;

    public Date getPresetTime() {
        return presetTime;
    }

    public void setPresetTime(Date presetTime) {
        this.presetTime = presetTime;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "sceId=" + sceId +
                ", hotelId=" + hotelId +
                ", type1=" + type1 +
                ", state=" + state +
                ", peopleNumber=" + peopleNumber +
                ", presetTime=" + presetTime +
                '}';
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public int getSceId() {
        return sceId;
    }

    public int getType1() {
        return type1;
    }

    public void setType1(int type1) {
        this.type1 = type1;
    }

    public void setSceId(int sceId) {
        this.sceId = sceId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}