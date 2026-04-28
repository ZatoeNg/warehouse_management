package org.example.controller;


import org.example.beans.ProductType;
import org.example.beans.Result;
import org.example.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/productCategory")
@RestController
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

//    查询商品分类
    @RequestMapping("/product-category-tree")
    public Result productCategoryTree(){
        List<ProductType> productTypes = productTypeService.productTyprTree();

        return Result.ok(productTypes);
    }

//    校验分类编码是否存在
    @RequestMapping("/verify-type-code")
    public Result checkTypeCode(String typeCode){
        Result result = productTypeService.checkCode(typeCode);

        return result;
    }

//    添加商品分类
    @RequestMapping("/type-add")
    public Result addProductType(@RequestBody ProductType productType){
        Result result = productTypeService.saveProductType(productType);

        return result;
    }

//    根据分类id删除分类及分类关系
    @RequestMapping("/type-delete/{typeId}")
    public Result TypeDelete(@PathVariable Integer typeId){
        Result result = productTypeService.removeProductType(typeId);

        return result;
    }

//    根据分类id修改分类
    @RequestMapping("/type-update")
    public Result typeUpdate(@RequestBody ProductType productType){

        Result result = productTypeService.setProductType(productType);

        return result;
    }
}
