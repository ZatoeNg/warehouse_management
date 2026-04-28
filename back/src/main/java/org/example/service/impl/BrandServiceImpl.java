package org.example.service.impl;

import org.example.beans.Brand;
import org.example.beans.Result;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.example.mapper.BrandMapper;
import org.example.service.BrandService;

import java.util.List;

@CacheConfig(cacheNames = "org.example.service.impl.BrandServiceImpl")
@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandMapper brandMapper;

    @Override
    @Cacheable(key = "'all:brand'")
    public List<Brand> queryAllBrand() {
        return brandMapper.findAllBrand();
    }

    @Override
    @CacheEvict(key = "'all:brand'")
    public Result brandAdd(Brand brand) {

        int i = brandMapper.brandAdd(brand);

        if (i > 0) return Result.ok("添加成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"添加失败");
    }
}
