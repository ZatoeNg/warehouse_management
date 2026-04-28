package org.example.mapper;

import org.example.beans.UserRole;

import java.util.List;

public interface UserRoleMapper {
//根据id删除用户角色关系
    public int removeUserRoleById(Integer userId);

//    添加用户角色关系
    public int insertUserRole(UserRole userRole);
}