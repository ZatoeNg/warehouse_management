package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.InStore;
import org.example.page.Page;

import java.util.List;

public interface InStoreMapper {

//    添加入库单
    public int insertInStore(InStore inStore);

//    查询行数
    public Integer findInStoreCount(InStore inStore);

//    分页查询
    public List<InStore> findInStorePage(@Param("page") Page page,@Param("inStore") InStore inStore);

//    根据id修改入库单
    public int setIsInById(Integer inStoreId);
}