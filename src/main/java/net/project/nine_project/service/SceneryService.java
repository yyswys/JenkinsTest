package net.project.nine_project.service;

import net.project.nine_project.domain.Hotel;
import net.project.nine_project.domain.Scenery;

import java.util.List;

public interface SceneryService {

    List<Scenery> listSceneryByLocation(String location);
    Scenery findById(int sceneryId);
    List<Hotel> findByTitle(String sceneryTitle);
    Scenery findSceneryByTitle(String sceneryTitle);
}