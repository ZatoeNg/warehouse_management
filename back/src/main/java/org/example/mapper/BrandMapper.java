package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.Brand;

import java.util.List;

public interface BrandMapper {

//    查询所有品牌
    public List<Brand> findAllBrand();

//    添加品牌
    int brandAdd(@Param("brand") Brand brand);
}