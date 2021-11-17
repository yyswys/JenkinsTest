package net.project.nine_project.service;

import net.project.nine_project.domain.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> listHotelByLocation(String location);
    Hotel findById(int hotelId);
}