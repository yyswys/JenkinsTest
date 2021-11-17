package net.project.nine_project.mapper;

import net.project.nine_project.domain.Scenery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SceneryMapper {
    List<Scenery> listSceneryByLocation(@Param("location") String location);
    Scenery findById(int sceneryId);
    Scenery findByTitle(String sceneryTitle);
    int update(Scenery scenery);
}
