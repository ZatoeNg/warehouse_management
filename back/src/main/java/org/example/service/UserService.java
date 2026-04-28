package org.example.service;

import org.example.beans.Result;
import org.example.beans.User;
import org.example.dto.AssignRoleDto;
import org.example.page.Page;

import java.util.List;

/*
* user_info表的service接口
* */
public interface UserService {

    public User queryUserByCode(String userCode);

    //    分页查询用户
    public Page getUserByPage(Page page, User user);

    //    添加用户
    public Result saveUser(User user);

//    启用或禁用用户
    public Result setUserState(User user);

//    给用户分配角色
    public void assignRole(AssignRoleDto assignRoleDto);

//    删除用户
    public Result removeUserByIds(List<Integer> userIdList);

//    修改用户名称
    public Result setUserById(User user);

//    根据id修改用户密码
    public Result setPwdById(Integer userId);

    //    根据id修改用户密码
    Result setPwdById(Integer userId, String userPwd);

//    根据id修改用户信息
    Result setDataById(User user);
}