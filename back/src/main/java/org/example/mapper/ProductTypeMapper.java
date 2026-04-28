package org.example.mapper;

import org.example.beans.ProductType;

import java.util.List;

public interface ProductTypeMapper {
//    查询所有分类
    public List<ProductType> findAllProductType();

    //    根据分类编码或者名称查分类
    public ProductType findTypeByCodeOrName(ProductType productType);

//    添加分类
    public int insertProductType(ProductType productType);

    //    根据分类id删除分类及分类关系
    public int removeProductType(Integer typeId);

//    根据分类id修改分类
    public int setProductTypeById(ProductType productType);
}