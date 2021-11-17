package net.project.nine_project.service.impl;

import net.project.nine_project.domain.Restaurant;
import net.project.nine_project.mapper.RestaurantMapper;
import net.project.nine_project.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantMapper restaurantMapper;

    @Override
    public List<Restaurant> listRestaurantByLocation(String location)
    {
        List<Restaurant> list=restaurantMapper.listRestaurantByLocation(location);
        list.sort(Comparator.comparing(Restaurant::getPrice));
        list.sort(Comparator.comparing(Restaurant::getLevel).reversed());
        return list;
    }

    @Override
    public Restaurant findById(int restaurantId)
    {
        return restaurantMapper.findById(restaurantId);
    }
}
