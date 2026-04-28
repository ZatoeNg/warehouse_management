package org.example.service;

import org.example.beans.ProductType;
import org.example.beans.Result;

import java.util.List;

public interface ProductTypeService{

//    查询所有分类树
    public List<ProductType> productTyprTree();

//    校验分类编码或名称是否存在
    public Result checkCode(String productTypeCode);

//    添加商品分类
    public Result saveProductType(ProductType productType);

    //    根据分类id删除分类及分类关系
    public Result removeProductType(Integer typeId);

    //    根据分类id修改分类
    public Result setProductType(ProductType productType);
}
