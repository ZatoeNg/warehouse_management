package org.example.controller;


import org.example.beans.Result;
import org.example.beans.Store;
import org.example.page.Page;
import org.example.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/store")
@RestController
public class StoreController {

    @Autowired
    private StoreService storeService;
    //    查询所有仓库
    @RequestMapping("/store-list")
    public Result storeList(){

        List<Store> store = storeService.queryAllStore();
        return Result.ok(store);

    }

    @RequestMapping("/store-page-list")
    public Result storePageList(Page page, Store store){
        page = storeService.getStoreByPage(page, store);

        return Result.ok(page);
    }

    @RequestMapping("/store-update")
    public Result storeUpdate(@RequestBody Store store){

        Result result =  storeService.updateStore(store);

        return result;
    }

    @RequestMapping("/store-delete/{storeId}")
    public Result storeDelete(@PathVariable Long storeId)
    {
        Result result = storeService.deleteStoreById(storeId);

        return result;

    }

    @RequestMapping("/store-num-check")
    public Result storeNumCheck(Store store){
        Result result = storeService.storeNumCheck(store);

        return result;
    }

    @RequestMapping("/store-add")
    public Result storeAdd(@RequestBody Store store){
        Result result = storeService.storeAdd(store);

        return result;
    }


}
