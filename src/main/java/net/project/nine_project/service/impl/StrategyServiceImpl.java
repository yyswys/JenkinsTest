package net.project.nine_project.service.impl;

import net.project.nine_project.domain.Scenery;
import net.project.nine_project.domain.Strategy;
import net.project.nine_project.mapper.SceneryMapper;
import net.project.nine_project.mapper.StrategyMapper;
import net.project.nine_project.mapper.UserMapper;
import net.project.nine_project.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class StrategyServiceImpl implements StrategyService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private SceneryMapper sceneryMapper;

    @Autowired
    private StrategyMapper strategyMapper;


    @Override
    public Strategy findDetailById(int strategyId) {
        //需要mybatis关联复杂查询，后续完善
        return strategyMapper.findDetailById(strategyId);
    }

    @Override
    public List<Strategy> listStrategyBySceId(int sceId)
    {
        List<Strategy> list=strategyMapper.listStrategyBySceId(sceId);
        list.sort(Comparator.comparing(Strategy::getCreateTime).reversed());
        return list;
    }

    @Override
    public List<Strategy> listStrategyByUserId(int userId)
    {
        List<Strategy> list=strategyMapper.listStrategyByUserId(userId);
        list.sort(Comparator.comparing(Strategy::getCreateTime).reversed());
        return list;
    }

    @Override
    public Scenery strategyToScenery(int strategyId)
    {
        Strategy strategy=strategyMapper.findDetailById(strategyId);
        return sceneryMapper.findById(strategy.getSceId());
    }

    @Override
    public int saveStrategy(int userId,int sceId,String title,String content,String picture)
    {
        Scenery scenery=sceneryMapper.findById(sceId);
        if(scenery==null)
        {
            return -1;
        }
        Strategy strategy=new Strategy();
        strategy.setUserId(userId);
        strategy.setSceId(sceId);
        strategy.setTitle(title);
        strategy.setContent(content);
        strategy.setCreateTime(new Date());
        strategy.setPicture(picture);
        int rows=strategyMapper.saveStrategy(strategy);
        return rows;
    }
}
