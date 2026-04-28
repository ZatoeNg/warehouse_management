package org.example.controller;


import org.example.beans.InStore;
import org.example.beans.Result;
import org.example.beans.Store;
import org.example.page.Page;
import org.example.service.InStoreService;
import org.example.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/instore")
@RestController
public class InStoreController {

    @Autowired
    private InStoreService inStoreService;

    @Autowired
    private StoreService storeService;
//    查询所有仓库
    @RequestMapping("/store-list")
    public Result StoreList(){
        List<Store> stores = storeService.queryAllStore();

        return Result.ok(stores);
    }

//    分页查询入库单
    @RequestMapping("/instore-page-list")
    public Result inStoreListPage(Page page, InStore inStore){
        page = inStoreService.queryInStorePage(page, inStore);

        return Result.ok(page);
    }

//    确认入库
    @RequestMapping("/instore-confirm")
    public Result confirmInStore(@RequestBody InStore inStore){
        Result result = inStoreService.confirmConfirm(inStore);

        return result;
    }
}
