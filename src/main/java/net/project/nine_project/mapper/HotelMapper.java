package net.project.nine_project.mapper;

import net.project.nine_project.domain.Hotel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelMapper {
    List<Hotel> listHotelByLocation(@Param("location") String location);
    Hotel findById(int hotelId);
    int update(Hotel hotel);
}

