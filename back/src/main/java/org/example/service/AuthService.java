package org.example.service;

import org.example.beans.Auth;

import java.util.List;

public interface AuthService{

//    查询用户菜单树
    public List<Auth> queryAuthTreeByUid(Integer userId);

//    查询所有权限菜单
    public List<Auth> allAuthTree();

}
