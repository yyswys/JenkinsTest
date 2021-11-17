package net.project.nine_project.service.impl;

import net.project.nine_project.domain.Hotel;
import net.project.nine_project.domain.Scenery;
import net.project.nine_project.mapper.HotelMapper;
import net.project.nine_project.mapper.SceneryMapper;
import net.project.nine_project.service.SceneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SceneryServiceImpl implements SceneryService {

    @Autowired
    private SceneryMapper sceneryMapper;

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public List<Scenery> listSceneryByLocation(String location)
    {
        List<Scenery> list= sceneryMapper.listSceneryByLocation(location);
        list.sort(Comparator.comparing(Scenery::getPrice));
        list.sort(Comparator.comparing(Scenery::getLevel).reversed());
        return list;
    }

    @Override
    public Scenery findById(int sceneryId)
    {
        return sceneryMapper.findById(sceneryId);
    }

    @Override
    public List<Hotel> findByTitle(String sceneryTitle)
    {
        Scenery scenery=sceneryMapper.findByTitle(sceneryTitle);
        List<Hotel> list=hotelMapper.listHotelByLocation(scenery.getLocation());
        list.sort(Comparator.comparing(Hotel::getPrice));
        list.sort(Comparator.comparing(Hotel::getLevel).reversed());
        return list;
    }

    @Override
    public Scenery findSceneryByTitle(String sceneryTitle)
    {
        Scenery scenery=sceneryMapper.findByTitle(sceneryTitle);
        return scenery;
    }
}