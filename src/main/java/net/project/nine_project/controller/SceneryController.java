package net.project.nine_project.controller;

import net.project.nine_project.domain.Hotel;
import net.project.nine_project.domain.Scenery;
import net.project.nine_project.service.SceneryService;
import net.project.nine_project.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/scenery")
public class SceneryController {
    @Autowired
    private SceneryService sceneryService;

    @RequestMapping("list")
    public JsonData sceneryList(@RequestParam(value = "location",required= true)String location){
        List<Scenery> sceneryListByLocation=sceneryService.listSceneryByLocation(location);
        return JsonData.buildSuccess(sceneryListByLocation);
    }

    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "scenery_id",required= true)int sceneryId){
        Scenery scenery=sceneryService.findById(sceneryId);//执行
        return JsonData.buildSuccess(scenery);
    }

    @GetMapping("find_scenery_by_title")
    public JsonData findSceneryByTitle(@RequestParam(value = "scenery_title",required= true)String sceneryTitle){
        Scenery scenery=sceneryService.findSceneryByTitle(sceneryTitle);
        return JsonData.buildSuccess(scenery);
    }

    @GetMapping("recommend_hotel_by_scenery_title")
    public JsonData recommendHotelBySceneryTitle(@RequestParam(value = "scenery_title",required= true)String sceneryTitle){
        List<Hotel> hotelListByLocation=sceneryService.findByTitle(sceneryTitle);
        return JsonData.buildSuccess(hotelListByLocation);
    }
}