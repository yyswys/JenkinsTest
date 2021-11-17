package net.project.nine_project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Order1 {
    private Integer id;
    private Integer type1;
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    private Integer state;
    @JsonProperty("sum_fee")
    private Integer sumFee;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("sce_id")
    private Integer sceId;
    @JsonProperty("hotel_id")
    private Integer hotelId;
    @JsonProperty("order_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime;
    private String title;
    private String img;
    private Integer level;
    @JsonProperty("preset_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date presetTime;
    @JsonProperty("people_number")
    private Integer peopleNumber;

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    @Override
    public String toString() {
        return "Order1{" +
                "id=" + id +
                ", type1=" + type1 +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", state=" + state +
                ", sumFee=" + sumFee +
                ", userId=" + userId +
                ", sceId=" + sceId +
                ", hotelId=" + hotelId +
                ", orderTime=" + orderTime +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", level=" + level +
                ", presetTime=" + presetTime +
                ", peopleNumber=" + peopleNumber +
                '}';
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public Date getPresetTime() {
        return presetTime;
    }

    public void setPresetTime(Date presetTime) {
        this.presetTime = presetTime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType1() {
        return type1;
    }

    public void setType1(Integer type1) {
        this.type1 = type1;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSumFee() {
        return sumFee;
    }

    public void setSumFee(Integer sumFee) {
        this.sumFee = sumFee;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSceId() {
        return sceId;
    }

    public void setSceId(Integer sceId) {
        this.sceId = sceId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
