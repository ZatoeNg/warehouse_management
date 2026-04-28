package org.example.controller;


import org.example.beans.CurrentUser;
import org.example.beans.OutStore;
import org.example.beans.Result;
import org.example.beans.Store;
import org.example.page.Page;
import org.example.service.OutStoreService;
import org.example.service.StoreService;
import org.example.utils.TokenUtils;
import org.example.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/outstore")
@RestController
public class OutStoreController {

    @Autowired
    private OutStoreService outStoreService;

    @Autowired
    private TokenUtils tokenUtils;
//    添加出库单
    @RequestMapping("/outstore-add")
    public Result addOutStore(@RequestBody OutStore outStore, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){

//        拿到当前id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        outStore.setTallyId(userId);
        outStore.setCreateBy(userId);

        Result result = outStoreService.saveOutStore(outStore);

        return result;

    }

    @Autowired
    private StoreService storeService;
//    查询所有仓库
    @RequestMapping("/store-list")
    public Result storeList(){
        List<Store> stores = storeService.queryAllStore();

        return Result.ok(stores);
    }

//    查询所有出库单
    @RequestMapping("/outstore-page-list")
    public Result outStoreListPage(Page page, OutStore outStore){
        page = outStoreService.queryOutStorePage(page, outStore);
        return  Result.ok(page);
    }

    @RequestMapping("/outstore-confirm")
    public  Result outstoreConfirm(@RequestBody OutStore outStore){
        Result result = outStoreService.outStoreConfirm(outStore);
        return result;
    }
}
