package org.example.controller;

import org.example.beans.Auth;
import org.example.beans.CurrentUser;
import org.example.beans.Result;
import org.example.beans.Role;
import org.example.dto.AssignAuthDto;
import org.example.page.Page;
import org.example.service.AuthService;
import org.example.service.RoleService;
import org.example.utils.TokenUtils;
import org.example.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

//    查询所有角色
    @RequestMapping("/role-list")
    public Result roleList(){
        List<Role> roles = roleService.queryAllRole();

        return Result.ok(roles);
    }

//    分页查询角色
    @RequestMapping("/role-page-list")
    public Result roleListPage(Page page,Role role){
        page = roleService.queryRolePage(page, role);

        return Result.ok(page);

    }

    @Autowired
    private TokenUtils tokenUtils;
//    添加角色
    @RequestMapping("/role-add")
    public Result addRole(@RequestBody  Role role,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();

        role.setCreateBy(userId);

        Result result = roleService.saveRole(role);

        return result;
    }

//    角色启用/禁用
    @RequestMapping("/role-state-update")
    public Result updateRoleState(@RequestBody Role role){
        Result result = roleService.setRoleState(role);

        return result;
    }

//    role-delete/{roleId}
    @RequestMapping("/role-delete/{roleId}")
    public Result deleteRole(@PathVariable Integer roleId){
        Result result = roleService.deleteRoleById(roleId);

        return result;
    }

//    查询角色分配的所有权限
    @RequestMapping("/role-auth")
    public Result roleAll(Integer roleId){
        List<Integer> authIdList = roleService.queryRoleAuthIds(roleId);

        return Result.ok(authIdList);
    }

    @Autowired
    AuthService authService;
    @Autowired
    StringRedisTemplate redisTemplate;

//    给角色分配菜单权限
    @RequestMapping("/auth-grant")
    public Result grantAuth(@RequestBody  AssignAuthDto assignAuthDto){
        redisTemplate.delete("authTree:" + assignAuthDto.getRoleId());
        roleService.saveRoleAuth(assignAuthDto);
        authService.queryAuthTreeByUid(assignAuthDto.getRoleId());
        return Result.ok("权限分配成功");
    }

//    修改角色
    @RequestMapping("/role-update")
    public Result updateRole(@RequestBody Role role,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();

        role.setUpdateBy(userId);

        Result result = roleService.setRoleByRid(role);

        return result;

    }

}
