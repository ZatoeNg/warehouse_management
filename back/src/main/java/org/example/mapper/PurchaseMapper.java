package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.Purchase;
import org.example.page.Page;

import java.util.List;

public interface PurchaseMapper {
//    添加采购单
    public int insertPurchase(Purchase purchase);

//    查询采购单行
    public Integer findPurchaseRowCount(Purchase purchase);

//    分页查询采购单
    public List<Purchase> findPurchasePage(@Param("page") Page page, @Param("purchase") Purchase purchase);

    //    根据id删除采购单
    public int removePurchaseById(Integer buyId);

//    根据id修改预计采购数量和实际数量
    public int setNumById(Purchase purchase);

//    根据id修改采购单状态为已入库
    public int setIsInById(Integer buyId);

}