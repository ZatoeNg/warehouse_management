package org.example.service.impl;

import org.example.beans.OutStore;
import org.example.beans.Result;
import org.example.mapper.ProductMapper;
import org.example.page.Page;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.example.mapper.OutStoreMapper;
import org.example.service.OutStoreService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OutStoreServiceImpl implements OutStoreService{

    @Autowired
    private OutStoreMapper outStoreMapper;

//    添加出库单
    @Transactional
    @Override
    public Result saveOutStore(OutStore outStore) {
        int i = outStoreMapper.insertOutStore(outStore);
        if(i>0) return Result.ok("添加成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"添加失败");
    }

    @Override
    public Page queryOutStorePage(Page page, OutStore outStore) {
        Integer outStoreRowCount = outStoreMapper.findOutStoreRowCount(outStore);


        List<OutStore> outStorePage = outStoreMapper.findOutStorePage(page, outStore);

            for (OutStore store : outStorePage) {
                String outStoreTallNameById = outStoreMapper.findOutStoreTallNameById(store.getTallyId());
                store.setTallyName(outStoreTallNameById);
            }


        page.setTotalNum(outStoreRowCount);
        page.setResultList(outStorePage);

        return page;
    }

    //    确认出库
    @Autowired
    private ProductMapper productMapper;

//    根据商品id查出商品库存
    @Transactional
    @Override
    public Result outStoreConfirm(OutStore outStore) {

        int inventById = productMapper.findInventById(outStore.getProductId());
        if(inventById<outStore.getOutNum()){
            return Result.err(Result.CODE_ERR_BUSINESS,"商品库存不足");
        }

        int i = outStoreMapper.setIsOutById(outStore.getOutsId());
        if(i>0){
            int j = productMapper.setInventById(outStore.getProductId(), -outStore.getOutNum());
            if(j>0) return Result.ok("出库成功");

            return Result.err(Result.CODE_ERR_BUSINESS,"出库失败");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"出库失败");
    }

}
