package org.example.service;

import org.example.beans.Purchase;
import org.example.beans.Result;
import org.example.page.Page;

public interface PurchaseService{

//    添加采购单
    public Result savePurchase(Purchase purchase);

//    分页查询采购单
    public Page queryPurchasePage(Page page,Purchase purchase);

//   根据id删除采购单
    public Result deletePurchaseById(Integer buyId);

//    根据id修改预计采购数量和实际数量
    public Result updatePurchaseById(Purchase purchase);

}
