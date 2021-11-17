package net.project.nine_project.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StrategyRequest {
    @JsonProperty("sce_id")
    private Integer sceId;
    private String  title;
    private String content;
    private String picture;

    public String getPicture() {
        return picture;
    }

    @Override
    public String toString() {
        return "StrategyRequest{" +
                "sceId=" + sceId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSceId() {
        return sceId;
    }

    public void setSceId(Integer sceId) {
        this.sceId = sceId;
    }
}
