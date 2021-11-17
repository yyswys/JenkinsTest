package net.project.nine_project.mapper;

import net.project.nine_project.domain.Strategy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrategyMapper {

    Strategy findDetailById(int strategyId);
    List<Strategy> listStrategyBySceId(int sceId);
    List<Strategy> listStrategyByUserId(int userId);
    int saveStrategy(Strategy strategy);
}