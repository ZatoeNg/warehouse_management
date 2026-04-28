package org.example.service;

import org.example.beans.Result;
import org.example.beans.Supply;
import org.example.page.Page;

import java.util.List;

public interface SupplyService{

//    查询所有供应商
    public List<Supply> queryAllSupply();

//    分页查询
    Page querySupplyPage(Page page, Supply supply);

//    更新
    Result updateSupply(Supply supply);

//    判断是否存在
    Result supplyNumCheck(Supply supply);

    //添加
    Result addSupply(Supply supply);

    //删除
    Result deleteSupplyByNum(String supplyNum);
}
