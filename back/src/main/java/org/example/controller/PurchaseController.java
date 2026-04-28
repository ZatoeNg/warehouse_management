package org.example.controller;


import org.example.beans.*;
import org.example.page.Page;
import org.example.service.InStoreService;
import org.example.service.PurchaseService;
import org.example.service.StoreService;
import org.example.utils.TokenUtils;
import org.example.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/purchase")
@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

//    添加采购单
    @RequestMapping("/purchase-add")
    public Result addPurchase(@RequestBody Purchase purchase){
        Result result = purchaseService.savePurchase(purchase);

        return result;
    }

//    查询所有仓库
    @Autowired
    private StoreService storeService;
    @RequestMapping("/store-list")
    public Result storeList(){
        List<Store> stores = storeService.queryAllStore();
        return Result.ok(stores);
    }

//    分页查询采购单
    @RequestMapping("/purchase-page-list")
    public Result purchasePageList(Page page,Purchase purchase){
        page = purchaseService.queryPurchasePage(page, purchase);

        return Result.ok(page);
    }

//    根据id删除采购单
    @RequestMapping("/purchase-delete/{buyId}")
    public Result deletePurchase(@PathVariable Integer buyId){
        Result result = purchaseService.deletePurchaseById(buyId);

        return result;
    }

//    修改采购单
    @RequestMapping("/purchase-update")
    public Result updatePurchase(@RequestBody Purchase purchase){
        Result result = purchaseService.updatePurchaseById(purchase);

        return result;
    }

    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private TokenUtils tokenUtils;
//    生成入库单
    @RequestMapping("/in-warehouse-record-add")
    public Result addInStore(@RequestBody Purchase purchase,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){

//        用户id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();

//        创建InStore
        InStore inStore = new InStore();

//        组装
        inStore.setCreateBy(userId);
        inStore.setStoreId(purchase.getStoreId());
        inStore.setProductId(purchase.getProductId());
        inStore.setInNum(purchase.getFactBuyNum());

        Result result = inStoreService.saveInStore(inStore, purchase.getBuyId());

        return result;
    }

}
