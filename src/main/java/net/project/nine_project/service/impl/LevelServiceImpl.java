package net.project.nine_project.service.impl;

import net.project.nine_project.domain.*;
import net.project.nine_project.mapper.*;
import net.project.nine_project.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    LevelMapper levelMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    SceneryMapper sceneryMapper;
    @Autowired
    HotelMapper hotelMapper;

    @Override
    @Transactional
    public int save(int userId,int orderId,int content) {
        User user=userMapper.findByUserId(userId);
        Order1 order=orderMapper.findByOrderId(orderId);
        if(content>5 || content<0 || user==null || order==null || userId!=order.getUserId())
        {
            return 0;
        }
        Level newLevel= new Level();
        newLevel.setContent(content);
        order.setLevel(content);
        if (order.getType1()==1)//如果订单类型为景点，更新该景点的平均评分
        {
            Scenery scenery=sceneryMapper.findById(order.getSceId());
            if(scenery==null)
            {
                System.out.println("找不到景点！");
                return 0;
            }
            int levelSum1=scenery.getLevelSum();
            float average1=scenery.getLevel();
            average1=(average1*levelSum1+order.getLevel())/(levelSum1+1);
            levelSum1++;
            scenery.setLevel(average1);
            scenery.setLevelSum(levelSum1);
            sceneryMapper.update(scenery);
        }
        else if (order.getType1()==2)//订单类型为酒店，更新评分
        {
            Hotel hotel=hotelMapper.findById(order.getHotelId());
            if(hotel==null)
            {
                System.out.println("找不到酒店！");
                return 0;
            }
            int levelSum2=hotel.getLevelSum();
            float average2=hotel.getLevel();
            average2=(average2*levelSum2+order.getLevel())/(levelSum2+1);
            levelSum2++;
            hotel.setLevel(average2);
            hotel.setLevelSum(levelSum2);
            hotelMapper.update(hotel);
        }
        orderMapper.payAndUpdate(order);
        newLevel.setTime(new Date());
        newLevel.setUserId(userId);
        newLevel.setOrderId(orderId);
        int rows=levelMapper.saveLevel(newLevel);
        return rows;
    }
}