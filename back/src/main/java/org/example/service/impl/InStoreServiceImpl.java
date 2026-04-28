package org.example.service.impl;

import org.example.beans.InStore;
import org.example.beans.Result;
import org.example.mapper.ProductMapper;
import org.example.mapper.PurchaseMapper;
import org.example.page.Page;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.example.mapper.InStoreMapper;
import org.example.service.InStoreService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InStoreServiceImpl implements InStoreService{

    @Autowired
    private InStoreMapper inStoreMapper;

    @Autowired
    private PurchaseMapper purchaseMapper;
    //    添加入库单
    @Transactional
    @Override
    public Result saveInStore(InStore inStore,Integer buyId) {
        int i = inStoreMapper.insertInStore(inStore);
        if(i>0){
            int j = purchaseMapper.setIsInById(buyId);
            if(j>0)return Result.ok("添加成功");
            return Result.err(Result.CODE_ERR_BUSINESS,"添加失败");
        }

        return Result.err(Result.CODE_ERR_BUSINESS,"添加失败");
    }

//    分页查询
    @Transactional
    @Override
    public Page queryInStorePage(Page page, InStore inStore) {
//        查询入库单行数
        Integer inStoreCount = inStoreMapper.findInStoreCount(inStore);

        List<InStore> inStorePage = inStoreMapper.findInStorePage(page, inStore);

        page.setTotalNum(inStoreCount);
        page.setResultList(inStorePage);

        return page;
    }


    @Autowired
    private ProductMapper productMapper;

    @Transactional
    @Override
    public Result confirmConfirm(InStore inStore) {

        int i = inStoreMapper.setIsInById(inStore.getInsId());
        if(i>0){
            int j = productMapper.setInventById(inStore.getProductId(), inStore.getInNum());
            if(j>0)return Result.ok("入库单确认完成");
            return Result.err(Result.CODE_ERR_BUSINESS,"入库确认失败");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"入库确认失败");
    }
}
