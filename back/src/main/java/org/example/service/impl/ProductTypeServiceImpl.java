package org.example.service.impl;

import org.example.beans.ProductType;
import org.example.beans.Result;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.example.mapper.ProductTypeMapper;
import org.example.service.ProductTypeService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "org.example.service.impl.ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService{

    @Autowired
    private ProductTypeMapper productTypeMapper;

    //    查询所有分类树
    @Override
    @Cacheable(key="'all:typeTree'")
    public List<ProductType> productTyprTree() {
        List<ProductType> allProductType = productTypeMapper.findAllProductType();

        List<ProductType> typeTreeList = allTypeToTypeTree(allProductType, 0);


        return typeTreeList;
    }

    //    将分类转树
    private List<ProductType> allTypeToTypeTree(List<ProductType> typeList,Integer pid){
        List<ProductType> firstLevelType = new ArrayList<>();

        for (ProductType productType : typeList) {
            if(productType.getParentId().equals(pid)){
                firstLevelType.add(productType);
            }
        }

        for (ProductType productType : firstLevelType) {
            List<ProductType> secondLevelTypes = allTypeToTypeTree(typeList, productType.getTypeId());
            productType.setChildProductCategory(secondLevelTypes);
        }

        return firstLevelType;
    }


    //    检验分类编码
    @Override
    public Result checkCode(String productTypeCode) {

        ProductType productType = new ProductType();
        productType.setTypeCode(productTypeCode);

        ProductType typeByCodeOrName = productTypeMapper.findTypeByCodeOrName(productType);

        return Result.ok(typeByCodeOrName==null);
    }

//    添加分类
    @CacheEvict(key = "'all:typeTree'")
    @Override
    @Transactional
    public Result saveProductType(ProductType productType) {
//        校验分类名称是否存在
        ProductType checkProductTypes = new ProductType();
        checkProductTypes.setTypeName(productType.getTypeName());

        ProductType typeByName = productTypeMapper.findTypeByCodeOrName(checkProductTypes);
        if(typeByName!=null)return Result.err(Result.CODE_ERR_BUSINESS,"分类名称已存在");

//        添加
        int i = productTypeMapper.insertProductType(productType);
        if(i>0) return Result.ok("添加成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"添加失败");
    }

    //    根据分类id删除分类及分类关系
    @CacheEvict(key = "'all:typeTree'")
    @Override
    @Transactional
    public Result removeProductType(Integer typeId) {
        int i = productTypeMapper.removeProductType(typeId);
        if(i>0) return Result.ok("删除成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"删除失败");
    }

// 根据分类id修改分类
    @CacheEvict(key = "'all:typeTree'")
    @Override
    @Transactional
    public Result setProductType(ProductType productType) {
//        判断分类名称是否存在
        ProductType productTypes = new ProductType();
        productTypes.setTypeName(productType.getTypeName());

        ProductType typeByCodeOrName = productTypeMapper.findTypeByCodeOrName(productTypes);

        if((typeByCodeOrName!=null && !typeByCodeOrName.getTypeId().equals(productType.getTypeId()))){
            return Result.err(Result.CODE_ERR_BUSINESS,"分类名称已存在");
        }

        int i = productTypeMapper.setProductTypeById(productType);
        if(i>0) return Result.ok("修改成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"修改失败");
    }

}
