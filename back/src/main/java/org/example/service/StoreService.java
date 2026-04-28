package org.example.service;

import org.example.beans.Result;
import org.example.beans.Store;
import org.example.page.Page;
import org.example.vo.StoreCountVo;

import java.util.List;

public interface StoreService{
//查询所有仓库
    public List<Store> queryAllStore();

//    查询每个仓库商品数量
    public List<StoreCountVo> queryStoreCount();

    public Page getStoreByPage(Page page, Store store);

    public Result updateStore(Store store);

    public Result deleteStoreById(Long storeId);

    public Result storeNumCheck(Store store);

    public Result storeAdd(Store store);
}
