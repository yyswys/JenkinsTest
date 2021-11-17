package net.project.nine_project.mapper;

import net.project.nine_project.domain.Order1;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderMapper {
    //查找用户是否买过此商品
    Order1 findByFour1(@Param("user_id")int userId,@Param("type1")int type1,@Param("sce_id")int sceId,@Param("preset_time")Date presetTime);
    Order1 findByFour2(@Param("user_id")int userId,@Param("type1")int type1,@Param("hotel_id")int hotelId,@Param("preset_time")Date presetTime);


    //下单
    int saveOrder(Order1 order);

    //订单列表
    List<Order1> listOrderByUserId(@Param("user_id") Integer userId);

    Order1 findByOrderId(Integer orderId);

    int payAndUpdate(Order1 order);

}