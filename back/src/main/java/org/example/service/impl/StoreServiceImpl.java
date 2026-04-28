package org.example.service.impl;

import org.example.beans.Result;
import org.example.beans.Store;
import org.example.page.Page;
import org.example.vo.StoreCountVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.example.mapper.StoreMapper;
import org.example.service.StoreService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CacheConfig(cacheNames = "org.example.service.impl.StoreServiceImpl")
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    //    查询所有仓库
    @Cacheable(key = "'all:store'")
    @Override
    public List<Store> queryAllStore() {
        return storeMapper.findAllStore();
    }

    //    查询每个仓库商品数量
    @Override
    public List<StoreCountVo> queryStoreCount() {
        return storeMapper.findStoreCount();
    }


    @Override
    public Page getStoreByPage(Page page, Store store) {

//        查询行数
        Integer storeRowCount = storeMapper.getStoreRowCount(store);

//        分页查询用户
        List<Store> storeByPage = storeMapper.getStoreByPage(page, store);

//        组装分页信息
        page.setTotalNum(storeRowCount);
        page.setResultList(storeByPage);

        return page;
    }

    @Override
    @Transactional
    @CacheEvict(key = "'all:store'")
    public Result updateStore(Store store) {

        int i = storeMapper.updateStore(store);

        if (i > 0) return Result.ok("修改成功");

        return Result.err(Result.CODE_ERR_BUSINESS, "修改失败");
    }

    @Override
    @Transactional
    @CacheEvict(key = "'all:store'")
    public Result deleteStoreById(Long storeId) {

        int i = storeMapper.deleteStoreById(storeId);

        if (i > 0) return Result.ok("修改成功");

        return Result.err(Result.CODE_ERR_BUSINESS, "修改失败");
    }


    @Override
    public Result storeNumCheck(Store store) {

        Store storeNum = storeMapper.storeNumCheckByNum(store.getStoreNum());
        if (storeNum != null) {
            return Result.ok("编号已存在", storeNum);
        }

        return Result.ok("编号可以使用", storeNum);


    }

    @Transactional
    @Override
    @CacheEvict(key = "'all:store'")
    public Result storeAdd(Store store) {

        int i = storeMapper.storeAdd(store);

        if (i > 0) return Result.ok("添加成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"添加失败");
    }
}
