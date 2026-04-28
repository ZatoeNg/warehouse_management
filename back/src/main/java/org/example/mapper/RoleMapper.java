package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.Role;
import org.example.page.Page;

import java.util.List;

public interface RoleMapper {

    public List<Role> getAllRole();

//    根据用户id查询用户分配的所有角色
    public List<Role> geUserRoleByUid(Integer userId);

//    根据角色名查角色id
    public Integer getRoleIdByName(String roleName);

//    查询角色行数的方法
    public Integer getRoleRowCount(Role role);

//    分页查询角色
    public List<Role> getRolePage(@Param("page") Page page,@Param("role") Role role);

//    根据角色名称或角色代码查询角色
    public Role getRoleByNameOrCode(String roleName,String roleCode);

//    添加角色
    public int insertRole(Role role);

//    根据id修改角色状态
    public int setRoleStateByRid(Integer roleId,String roleState);

//    根据id删角色
    public int removRoleById(Integer roleId);

//    根据id修改角色描述
    public int setDescByRid(Role role);
}