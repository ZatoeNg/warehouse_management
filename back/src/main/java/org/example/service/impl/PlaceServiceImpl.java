package org.example.service.impl;

import org.example.beans.Place;
import org.example.beans.Result;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.example.mapper.PlaceMapper;
import org.example.service.PlaceService;

import java.util.List;

@CacheConfig(cacheNames = "org.example.service.impl.PlaceServiceImpl")
@Service
public class PlaceServiceImpl implements PlaceService{

    @Autowired
    private PlaceMapper placeMapper;

    @Override
    @Cacheable(key = "'all:place'")
    public List<Place> queryAllPlace() {
        return placeMapper.findAllPlace();
    }

    @Override
    @CacheEvict(key = "'all:place'")
    public Result placeAdd(Place place) {
        int i = placeMapper.placeAdd(place);

        if (i > 0) return Result.ok("添加成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"添加失败");
    }
}
