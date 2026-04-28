package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.Store;
import org.example.page.Page;
import org.example.vo.StoreCountVo;

import java.util.List;

public interface StoreMapper {

//    查询所有仓库
    public List<Store> findAllStore();

//    查询每个仓库商品数量
    public List<StoreCountVo> findStoreCount();

    //    查询行数
    public Integer getStoreRowCount(@Param("store") Store store);

    public List<Store> getStoreByPage(@Param("page") Page page, @Param("store") Store store);


   public int updateStore(@Param("store") Store store);

    public int deleteStoreById(@Param("storeId") Long storeId);

    public Store storeNumCheckByNum(@Param("storeNum") String storeNum);

    public int storeAdd(Store store);
}