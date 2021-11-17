package net.project.nine_project.mapper;

import net.project.nine_project.domain.Hotel;
import net.project.nine_project.domain.Restaurant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RestaurantMapper {
    List<Restaurant> listRestaurantByLocation(@Param("location") String location);
    Restaurant findById(int restaurantId);
}
