package org.example.service;

import org.example.beans.Auth;
import org.example.beans.Result;
import org.example.beans.Role;
import org.example.beans.RoleAuth;
import org.example.dto.AssignAuthDto;
import org.example.page.Page;

import java.util.List;

public interface RoleService{

//    查询所有角色
    public List<Role> queryAllRole();

//    根据用户id查询用户已分配角色
    public List<Role> queryUserRoleByUid(Integer userId);

//    分页查询角色
    public Page queryRolePage(Page page,Role role);

//    添加角色
    public Result saveRole(Role role);

//    启用/禁用用户
    public Result setRoleState(Role role);

//    删除角色
    public Result deleteRoleById(Integer roleId);

//    查询角色分配的所有权限
    public List<Integer> queryRoleAuthIds(Integer roleId);

//    给角色分配权限
    public void saveRoleAuth(AssignAuthDto assignAuthDto);

 //    根据id修改角色描述
    public Result setRoleByRid(Role role);
}
