package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.OutStore;
import org.example.beans.Product;
import org.example.beans.ProductType;
import org.example.page.Page;

import java.util.List;

public interface OutStoreMapper {
//    添加出库单
    public int insertOutStore(OutStore outStore);

//    查询出库单行数
        public Integer findOutStoreRowCount(OutStore outStore);

//    分页查询
    public List<OutStore> findOutStorePage(@Param("page") Page page,@Param("outStore") OutStore outStore);

//    根据id修改出库单
    public int setIsOutById(Integer outStoreId);

    //    查询理货员名称
    String findOutStoreTallNameById(@Param("tallId")Integer tallId);
}