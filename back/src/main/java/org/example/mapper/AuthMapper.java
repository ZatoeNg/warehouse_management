package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.Auth;

import java.util.List;

public interface AuthMapper {
//    根据用户ID查权限菜单
    public List<Auth> findAuthByUid(@Param("userId") Integer userId);

//    查权限菜单
    public List<Auth> findAllAuth();

}