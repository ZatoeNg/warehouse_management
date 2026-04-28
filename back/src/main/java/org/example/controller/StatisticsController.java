package org.example.controller;

import org.example.beans.Result;
import org.example.service.StoreService;
import org.example.vo.StoreCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/statistics")
@RestController
public class StatisticsController {

    @Autowired
    private StoreService storeService;

//    图表
    @RequestMapping("/store-invent")
    public Result storeInvent(){
        List<StoreCountVo> storeCountVos = storeService.queryStoreCount();

        return Result.ok(storeCountVos);
    }

}
