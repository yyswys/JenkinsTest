package net.project.nine_project.controller;

import net.project.nine_project.domain.Hotel;
import net.project.nine_project.domain.Restaurant;
import net.project.nine_project.service.HotelService;
import net.project.nine_project.service.RestaurantService;
import net.project.nine_project.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping("list")
    public JsonData restaurantList(@RequestParam(value = "location",required= true)String location){
        List<Restaurant> hotelListByLocation=restaurantService.listRestaurantByLocation(location);
        return JsonData.buildSuccess(hotelListByLocation);
    }

    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "restaurant_id",required= true)int restaurantId){
        Restaurant restaurant=restaurantService.findById(restaurantId);//执行
        return JsonData.buildSuccess(restaurant);
    }
}
