package net.project.nine_project.service.impl;

import net.project.nine_project.domain.Hotel;
import net.project.nine_project.domain.Order1;
import net.project.nine_project.domain.Scenery;
import net.project.nine_project.domain.User;
import net.project.nine_project.mapper.HotelMapper;
import net.project.nine_project.mapper.OrderMapper;
import net.project.nine_project.mapper.SceneryMapper;
import net.project.nine_project.mapper.UserMapper;
import net.project.nine_project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    SceneryMapper sceneryMapper;
    @Autowired
    HotelMapper hotelMapper;
    @Autowired
    UserMapper userMapper;
    //下单操作，未来版本：优惠券，风控检查等
    @Override
    @Transactional
    public int save(int userId,int type1,int sceId,int hotelId,int state,Date presetTime,int peopleNumber) {
        //判断是否已经下过订单过
        Order1 order = new Order1();
        if (type1 == 1) {
            order = orderMapper.findByFour1(userId,type1, sceId,presetTime);
            if (order != null) {
                return 0;
            }
        } else if (type1 == 2) {
            order = orderMapper.findByFour2(userId,type1, hotelId,presetTime);
            if (order != null) {
                return 0;
            }
        } else {
            return 0;
        }
        if(state==0)
        {
            User user = userMapper.findByUserId(userId);
            //新增order的根据id查询简单详情
            if (type1 == 1)    //1为参观景点的费用；2为酒店的费用；
            {
                Scenery scenery = sceneryMapper.findById(sceId);
                if (scenery == null) {
                    return 0;
                }
                user.setOrderNumber(user.getOrderNumber()+1);
                userMapper.updateByLogin(user);
                Order1 newOrder = new Order1();
                newOrder.setSumFee(scenery.getPrice()*peopleNumber);
                newOrder.setOrderTime(new Date());
                newOrder.setOutTradeNo(UUID.randomUUID().toString());  //UUID:全局唯一标识符
                newOrder.setState(0);
                newOrder.setType1(1);
                newOrder.setUserId(userId);
                newOrder.setSceId(sceId);
                newOrder.setHotelId(0);
                newOrder.setImg(scenery.getCoverImg());
                newOrder.setTitle(scenery.getTitle());
                newOrder.setPresetTime(presetTime);
                newOrder.setPeopleNumber(peopleNumber);
                int rows = orderMapper.saveOrder(newOrder);
                return rows;
            }
            else if (type1 == 2)
            {
                Hotel hotel = hotelMapper.findById(hotelId);
                if (hotel == null) {
                    return 0;
                }
                user.setOrderNumber(user.getOrderNumber()+1);
                userMapper.updateByLogin(user);
                Order1 newOrder = new Order1();
                newOrder.setOrderTime(new Date());
                newOrder.setOutTradeNo(UUID.randomUUID().toString());  //UUID:全局唯一标识符
                newOrder.setState(0);
                newOrder.setType1(2);
                newOrder.setSumFee(hotel.getPrice()*peopleNumber);
                newOrder.setUserId(userId);
                newOrder.setSceId(0);
                newOrder.setHotelId(hotelId);
                newOrder.setImg(hotel.getCoverImg());
                newOrder.setTitle(hotel.getTitle());
                newOrder.setPresetTime(presetTime);
                newOrder.setPeopleNumber(peopleNumber);
                int rows = orderMapper.saveOrder(newOrder);
                return rows;
            }
        }
        else if(state==1)
        {
            User user = userMapper.findByUserId(userId);
            //新增order的根据id查询简单详情
            if (type1 == 1)    //1为参观景点的费用；2为酒店的费用；
            {
                Scenery scenery = sceneryMapper.findById(sceId);
                if (scenery == null) {
                    return 0;
                }
                if (user.getMoney() < scenery.getPrice()) {
                    System.out.println("余额不足");
                    return 0;
                }
                Integer money = user.getMoney() - scenery.getPrice();
                user.setMoney(money);
                user.setOrderNumber(user.getOrderNumber()+1);
                userMapper.changeMoney(user);
                Order1 newOrder = new Order1();
                newOrder.setSumFee(scenery.getPrice()*peopleNumber);
                newOrder.setOrderTime(new Date());
                newOrder.setOutTradeNo(UUID.randomUUID().toString());  //UUID:全局唯一标识符
                newOrder.setState(1);
                newOrder.setType1(1);
                newOrder.setUserId(userId);
                newOrder.setSceId(sceId);
                newOrder.setHotelId(0);
                newOrder.setImg(scenery.getCoverImg());
                newOrder.setTitle(scenery.getTitle());
                newOrder.setPresetTime(presetTime);
                newOrder.setPeopleNumber(peopleNumber);
                int rows = orderMapper.saveOrder(newOrder);
                return rows;
            } else if (type1 == 2) {
                Hotel hotel = hotelMapper.findById(hotelId);
                if (hotel == null) {
                    return 0;
                }
                if (user.getMoney() < hotel.getPrice()) {
                    System.out.println("余额不足");
                    return 0;
                }
                Integer money = user.getMoney() - hotel.getPrice();
                user.setMoney(money);
                user.setOrderNumber(user.getOrderNumber()+1);
                userMapper.changeMoney(user);
                Order1 newOrder = new Order1();
                newOrder.setOrderTime(new Date());
                newOrder.setOutTradeNo(UUID.randomUUID().toString());  //UUID:全局唯一标识符
                newOrder.setState(1);
                newOrder.setType1(2);
                newOrder.setSumFee(hotel.getPrice()*peopleNumber);
                newOrder.setUserId(userId);
                newOrder.setSceId(0);
                newOrder.setHotelId(hotelId);
                newOrder.setImg(hotel.getCoverImg());
                newOrder.setTitle(hotel.getTitle());
                newOrder.setPresetTime(presetTime);
                newOrder.setPeopleNumber(peopleNumber);
                int rows = orderMapper.saveOrder(newOrder);
                return rows;
            }
        }
        return 0;
    }

    @Override
    public List<Order1> listOrder(Integer userId) {
        return orderMapper.listOrderByUserId(userId);
    }

    @Override
    public Order1 findByOrderId(int orderId)
    {
        return orderMapper.findByOrderId(orderId);
    }

    public int payAndUpdate(Order1 order,int userId)
    {
        if(order.getState()!=0)
        {
            return -1;
        }
        User user = userMapper.findByUserId(userId);
        if (user.getMoney() < order.getSumFee())
        {
            System.out.println("余额不足");
            return -1;
        }
        Integer money = user.getMoney() - order.getSumFee();
        user.setMoney(money);
        userMapper.changeMoney(user);
        order.setState(1);
        orderMapper.payAndUpdate(order);
        return 0;
    }
}