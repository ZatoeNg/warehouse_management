package org.example.service.impl;

import com.alibaba.fastjson.JSON;
import org.example.beans.Auth;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.example.mapper.AuthMapper;
import org.example.service.AuthService;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

//指定缓存的名称
@CacheConfig(cacheNames = "org.example.service.impl.AuthServiceImpl")
@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /*
    * 向redis 缓存 -- 键authTree:userId,List<Auth>转JSON
    * */
    @Override
    public List<Auth> queryAuthTreeByUid(Integer userId) {
        String authTreeJson = redisTemplate.opsForValue().get("authTree:" + userId);

        if(StringUtils.hasText(authTreeJson)){
            List<Auth> authTreeList = JSON.parseArray(authTreeJson, Auth.class);
            return authTreeList;
        }

//        没有菜单缓存，获取菜单
        List<Auth> allAuthList = authMapper.findAuthByUid(userId);
        List<Auth> authTreeList = allAuthToAuthTree(allAuthList, 0);

//        向redis 缓存
        redisTemplate.opsForValue().set("authTree:"+userId,JSON.toJSONString(authTreeList));
        return authTreeList;
    }

//    查询所有菜单
    @Cacheable(key = "'all:authTree'")
    @Override
    public List<Auth> allAuthTree() {

        List<Auth> allAuthList = authMapper.findAllAuth();
        List<Auth> auths = allAuthToAuthTree(allAuthList,0);
        return auths;
    }

    //    获取所有菜单
    private List<Auth> allAuthToAuthTree(List<Auth> allAuthList,Integer pid){

//        上级菜单
        List<Auth> firsLevelAuthList = new ArrayList<>();
        for (Auth auth : allAuthList){
            if(auth.getParentId().equals(pid)){
                firsLevelAuthList.add(auth);
            }
        }

//        下级菜单
        for (Auth firsAuth : firsLevelAuthList) {
            List<Auth> secondLeveAuthList = allAuthToAuthTree(allAuthList, firsAuth.getAuthId());
            firsAuth.setChildAuth(secondLeveAuthList);
        }
        return firsLevelAuthList;
    }
}
