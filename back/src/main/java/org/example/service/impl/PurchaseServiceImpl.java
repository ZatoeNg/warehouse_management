package org.example.service.impl;

import org.example.beans.Purchase;
import org.example.beans.Result;
import org.example.page.Page;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.example.mapper.PurchaseMapper;
import org.example.service.PurchaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    @Transactional
    public Result savePurchase(Purchase purchase) {

        int i = purchaseMapper.insertPurchase(purchase);
        if(i>0) return Result.ok("添加成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"添加失败");
    }

//    分页查询采购单
    @Override
    public Page queryPurchasePage(Page page, Purchase purchase) {
//        查询采购单行
        Integer purchaseRowCount = purchaseMapper.findPurchaseRowCount(purchase);

        List<Purchase> purchasePage = purchaseMapper.findPurchasePage(page, purchase);

//        组装分页信息
        page.setTotalNum(purchaseRowCount);
        page.setResultList(purchasePage);

        return page;
    }


    //   根据id删除采购单
    @Override
    @Transactional
    public Result deletePurchaseById(Integer buyId) {
        int i = purchaseMapper.removePurchaseById(buyId);
        if(i>0) return Result.ok("删除成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"删除失败");
    }

    //    根据id修改预计采购数量和实际数量
    @Override
    @Transactional
    public Result updatePurchaseById(Purchase purchase) {
        int i = purchaseMapper.setNumById(purchase);
        if(i>0) return Result.ok("修改成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"修改失败");
    }
}
