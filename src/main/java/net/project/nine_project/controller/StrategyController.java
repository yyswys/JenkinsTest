package net.project.nine_project.controller;

import net.project.nine_project.domain.Scenery;
import net.project.nine_project.domain.Strategy;
import net.project.nine_project.service.StrategyService;
import net.project.nine_project.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/strategy")
public class StrategyController {
    @Autowired
    private StrategyService strategyService;

    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "strategy_id",required= true)int strategyId){
        Strategy strategy=strategyService.findDetailById(strategyId);//执行
        return JsonData.buildSuccess(strategy);
    }

    @RequestMapping("list_strategy_by_sce_id")
    public JsonData listStrategyBySceId(@RequestParam(value = "sce_id",required= true)int sceId){
        List<Strategy> strategyListBySceId=strategyService.listStrategyBySceId(sceId);
        return JsonData.buildSuccess(strategyListBySceId);
    }

    @RequestMapping("list_strategy_by_user_id")
    public JsonData listStrategyByUserId(@RequestParam(value = "user_id",required= true)int userId){
        List<Strategy> strategyListByUserId=strategyService.listStrategyByUserId(userId);
        return JsonData.buildSuccess(strategyListByUserId);
    }

    @GetMapping("strategy_to_scenery")
    public JsonData strategyToScenery(@RequestParam(value = "strategy_id",required= true)int strategyId){
        Scenery scenery=strategyService.strategyToScenery(strategyId);//执行
        return JsonData.buildSuccess(scenery);
    }
}