package net.project.nine_project.controller;

import net.project.nine_project.domain.Order1;
import net.project.nine_project.domain.OrderRequest;
import net.project.nine_project.service.OrderService;
import net.project.nine_project.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pri/order")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("save")
    //下单方法，两个参数，一个是下单的数据，一个是请求携带的数据，比如用户数据
    public JsonData saveOrder(@RequestBody OrderRequest orderRequest, HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        int rows = orderService.save(userId,orderRequest.getType1(),orderRequest.getSceId(),orderRequest.getHotelId(),orderRequest.getState(),orderRequest.getPresetTime(),orderRequest.getPeopleNumber());  //rows=0代表此为下单失败
        return rows ==0 ? JsonData.buildError("下单失败") : JsonData.buildSuccess();
    }

    @RequestMapping("list")
    public JsonData listOrder(HttpServletRequest request){   //获得用户数据User,因为已经登录
        Integer userId = (Integer) request.getAttribute("user_id");
        List<Order1> OrderList=orderService.listOrder(userId);
        return JsonData.buildSuccess(OrderList);
    }

    @RequestMapping("pay")
    public JsonData pay(@RequestParam(value = "order_id",required= true)int orderId, HttpServletRequest request){   //获得用户数据User,因为已经登录
        Integer userId = (Integer) request.getAttribute("user_id");
        Order1 order=orderService.findByOrderId(orderId);
        if(userId != order.getUserId())
        {
            return JsonData.buildError("付款失败");
        }
        int rows = orderService.payAndUpdate(order,userId);
        return rows !=0 ? JsonData.buildError("付款失败") : JsonData.buildSuccess();
    }
}