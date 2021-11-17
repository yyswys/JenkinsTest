package net.project.nine_project.service;

import net.project.nine_project.domain.Order1;
import net.project.nine_project.domain.User;

import java.util.Date;
import java.util.List;

public interface OrderService {

    //如果复杂信息可以封装到对象中做参数
    int save(int userId, int type1, int sceId, int hotelId, int state, Date presetTime,int peopleNumber);

    List<Order1> listOrder(Integer userId);

    Order1 findByOrderId(int orderId);

    // int changeMoney(User user);
    int payAndUpdate(Order1 order,int userId);
}