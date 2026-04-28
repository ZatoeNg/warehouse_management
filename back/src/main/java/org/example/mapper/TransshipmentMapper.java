package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.Product;
import org.example.beans.Transshipment;
import org.example.page.Page;

import java.util.List;

public interface TransshipmentMapper {

    Product CheckStoreProduct(@Param("transshipment") Transshipment transshipment);


    Integer findTransMapperRowCount(@Param("trans")Transshipment transshipment);


    List<Transshipment> findTransPage(@Param("page") Page page,@Param("trans") Transshipment transshipment);

    //    根据ID查找仓库名称
    String findStoreNameById(@Param("sourceStoreId") Integer sourceStoreId);

//    根据ID查找商品
    Product findProductById(Integer sourceProductId);

//    添加调货单
    int saveTrans(@Param("trans") Transshipment transshipment);

//    确认调货状态
    int setTransState(@Param("trans")Transshipment transshipment);

//    根据ID查商品ID
    Transshipment findTransById(@Param("trans")Transshipment transshipment);

    Transshipment findTransByIds(@Param("trans")Transshipment transshipment);
}
