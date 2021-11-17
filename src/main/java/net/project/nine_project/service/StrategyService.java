package net.project.nine_project.service;

import net.project.nine_project.domain.Scenery;
import net.project.nine_project.domain.Strategy;

import java.util.List;

public interface StrategyService {
    Strategy findDetailById(int strategyId);
    List<Strategy> listStrategyBySceId(int sceId);
    List<Strategy> listStrategyByUserId(int userId);
    Scenery strategyToScenery(int strategyId);
    int saveStrategy(int userId,int sceId,String title,String content,String picture);
}
