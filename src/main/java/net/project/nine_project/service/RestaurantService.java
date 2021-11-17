package net.project.nine_project.service;
import net.project.nine_project.domain.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> listRestaurantByLocation(String location);
    Restaurant findById(int restaurantlId);
}
