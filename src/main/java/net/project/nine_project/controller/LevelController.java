package net.project.nine_project.controller;

import net.project.nine_project.domain.LevelRequest;
import net.project.nine_project.service.LevelService;
import net.project.nine_project.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/pri/order/level")
public class LevelController {
    @Autowired
    private LevelService levelService;

    @RequestMapping("save")
    //下单方法，两个参数，一个是评价的数据，一个是请求携带的数据，比如用户数据
    public JsonData saveOrder(@RequestBody LevelRequest levelRequest, HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        int rows = levelService.save(userId,levelRequest.getOrderId(),levelRequest.getContent());  //rows=0代表无效评价
        return rows ==0 ? JsonData.buildError("无效评价") : JsonData.buildSuccess();
    }

}
