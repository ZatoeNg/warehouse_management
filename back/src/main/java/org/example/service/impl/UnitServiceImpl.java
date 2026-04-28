package org.example.service.impl;

import org.example.beans.Result;
import org.example.beans.Store;
import org.example.beans.Unit;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.example.mapper.UnitMapper;
import org.example.service.UnitService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CacheConfig(cacheNames = "org.example.service.impl.UnitServiceImpl")
@Service
public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitMapper unitMapper;

    @Cacheable(key = "'all:unit'")
    @Override
    public List<Unit> queryAllUnit() {
        return unitMapper.findAllUnit();
    }


    @Transactional
    @Override
    @CacheEvict(key = "'all:unit'")
    public Result unitAdd(Unit unit) {

        int i = unitMapper.unitAdd(unit);

        if (i > 0) return Result.ok("添加成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"添加失败");
    }
}
