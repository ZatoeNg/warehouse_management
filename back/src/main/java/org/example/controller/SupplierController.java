package org.example.controller;


import org.example.beans.Result;
import org.example.beans.Store;
import org.example.beans.Supply;
import org.example.page.Page;
import org.example.service.StoreService;
import org.example.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/supplier")
@RestController
public class SupplierController {

    @Autowired
    private SupplyService supplyService;

    //    分页查询入库单
    @RequestMapping("/supplier-page-list")
    public Result supplierListPage(Page page, Supply supply){

        page = supplyService.querySupplyPage(page, supply);

        return Result.ok(page);
    }

    //    查询所有供应商
    @RequestMapping("/supplier-list")
    public Result supplierList(){
        List<Supply> supplys = supplyService.queryAllSupply();
        return Result.ok(supplys);
    }

    @CacheEvict("'all:store'")
    @RequestMapping("/supplier-update")
    public Result supplierUpdate(@RequestBody Supply supply)
    {

        return supplyService.updateSupply(supply);
    }

    @RequestMapping("/supplier-num-check")
    public Result supplyNumCheck(Supply supply)
    {
        Result result = supplyService.supplyNumCheck(supply);

        return result;
    }

    @CacheEvict("'all:store'")
    @RequestMapping("/supplier-add")
    public Result addSupply(@RequestBody Supply supply)
    {
        Result result = supplyService.addSupply(supply);

        return result;
    }

    @CacheEvict("'all:store'")
    @RequestMapping("/supplier-delete/{supplyNum}")
    public Result storeDelete(@PathVariable String supplyNum)
    {
        Result result = supplyService.deleteSupplyByNum(supplyNum);

        return result;

    }
}
