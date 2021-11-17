package net.project.nine_project.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LevelRequest {
    //前端的下划线参数转成后端驼峰
    @JsonProperty("order_id")
    private int orderId;

    private int content;

    public int getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "LevelRequest{" +
                "orderId=" + orderId +
                ", content=" + content +
                '}';
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }
}
