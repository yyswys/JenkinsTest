package net.project.nine_project.service.impl;

import net.project.nine_project.domain.Hotel;
import net.project.nine_project.mapper.HotelMapper;
import net.project.nine_project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public List<Hotel> listHotelByLocation(String location)
    {
        List<Hotel> list=hotelMapper.listHotelByLocation(location);
        list.sort(Comparator.comparing(Hotel::getPrice));
        list.sort(Comparator.comparing(Hotel::getLevel).reversed());
        return list;
    }

    @Override
    public Hotel findById(int hotelId)
    {
        return hotelMapper.findById(hotelId);
    }



}