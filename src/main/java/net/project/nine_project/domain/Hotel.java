package net.project.nine_project.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hotel {
    private Integer id;
    private String title;
    private String detail;
    private Integer price;
    @JsonProperty("cover_img")
    private String coverImg;
    @JsonProperty("level")
    private float level;
    private String location;
    @JsonProperty("level_sum")
    private  int levelSum;

    public int getLevelSum() {
        return levelSum;
    }

    public void setLevelSum(int levelSum) {
        this.levelSum = levelSum;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", coverImg='" + coverImg + '\'' +
                ", level=" + level +
                ", location='" + location + '\'' +
                ", levelSum=" + levelSum +
                '}';
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }


}
