package net.project.nine_project.controller;

import net.project.nine_project.domain.Hotel;
import net.project.nine_project.service.HotelService;
import net.project.nine_project.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @RequestMapping("list")
    public JsonData hotelList(@RequestParam(value = "location",required= true)String location){
        List<Hotel> hotelListByLocation=hotelService.listHotelByLocation(location);
        return JsonData.buildSuccess(hotelListByLocation);
    }

    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "hotel_id",required= true)int hotelId){
        Hotel hotel=hotelService.findById(hotelId);//执行
        return JsonData.buildSuccess(hotel);
    }
}