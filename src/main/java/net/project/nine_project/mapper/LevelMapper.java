package net.project.nine_project.mapper;

import net.project.nine_project.domain.Level;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelMapper {
    int saveLevel(Level level);
}
