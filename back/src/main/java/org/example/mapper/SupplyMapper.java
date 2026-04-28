package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.Store;
import org.example.beans.Supply;
import org.example.page.Page;

import java.util.List;

public interface SupplyMapper {
//    查询所有供应商
    public List<Supply> findAllSupply();

    Integer findSupplyRowCount(@Param("supply") Supply supply);

    List<Supply> findSupplyPage(@Param("page")Page page,@Param("supply") Supply supply);

    int updateSupply(@Param("supply") Supply supply);

    Store supplyNumCheckByNum(@Param("supplyNum") String supplyNum);

    int addSupply(@Param("supply") Supply supply);


    int deleteSupplyByNum(@Param("supplyNum") String supplyNum);
}