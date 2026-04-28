package org.example.service.impl;

import org.example.beans.Result;
import org.example.beans.Store;
import org.example.beans.Supply;
import org.example.page.Page;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.example.mapper.SupplyMapper;
import org.example.service.SupplyService;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@CacheConfig(cacheNames = "org.example.service.impl.SupplyServiceImpl")
@Service
public class SupplyServiceImpl implements SupplyService{

    @Autowired
    private SupplyMapper supplyMapper;

    @Override
    @Cacheable(key = "'all:supply'")
    public List<Supply> queryAllSupply() {
        return supplyMapper.findAllSupply();
    }



    @Override
    public Page querySupplyPage(Page page, Supply supply) {

        Integer supplyRowCount = supplyMapper.findSupplyRowCount(supply);

        List<Supply> supplyPage = supplyMapper.findSupplyPage(page, supply);

        page.setTotalNum(supplyRowCount);
        page.setResultList(supplyPage);

        return page;
    }

    @Transactional
    @Override
    @CacheEvict(key = "'all:supply'")
    public Result updateSupply(Supply supply) {


        int i = supplyMapper.updateSupply(supply);

        if (i > 0) return Result.ok("修改成功");

        return Result.err(Result.CODE_ERR_BUSINESS, "修改失败");
    }


    @Override
    public Result supplyNumCheck(Supply supply) {

        Store storeNum = supplyMapper.supplyNumCheckByNum(supply.getSupplyNum());
        if (storeNum != null) {
            return Result.ok("编号已存在", storeNum);
        }

        return Result.ok("编号可以使用", storeNum);
    }

    @CacheEvict(key = "'all:supply'")
    @Transactional
    @Override
    public Result addSupply(Supply supply) {


        int i = supplyMapper.addSupply(supply);

        if (i > 0) return Result.ok("添加成功");

        return Result.err(Result.CODE_ERR_BUSINESS, "添加失败");
    }

    @CacheEvict(key = "'all:supply'")
    @Override
    public Result deleteSupplyByNum(String supplyNum) {

        int i = supplyMapper.deleteSupplyByNum(supplyNum);

        if (i > 0) return Result.ok("删除成功");

        return Result.err(Result.CODE_ERR_BUSINESS, "删除失败");
    }

}
