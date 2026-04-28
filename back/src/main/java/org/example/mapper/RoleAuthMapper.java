package org.example.mapper;

import org.example.beans.RoleAuth;

import java.util.List;

public interface RoleAuthMapper {

//    根据角色id删除角色权限关系

    public void removeRoleAuthByRid(Integer roleId);

//    根据角色id查询角色分配的菜单
    public List<Integer> findAuthIdsByRid(Integer roleId);


//    添加角色权限关系
    public int insertRoleAuth(RoleAuth roleAuth);
}