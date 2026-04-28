package org.example.controller;

import org.apache.ibatis.annotations.Param;
import org.example.beans.*;
import org.example.dto.AssignRoleDto;
import org.example.page.Page;
import org.example.service.RoleService;
import org.example.service.UserRoleService;
import org.example.service.UserService;
import org.example.utils.TokenUtils;
import org.example.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.example.utils.WarehouseConstants.HEADER_TOKEN_NAME;

@RequestMapping("/user")
@RestController
public class UserController {

//    注入UserService
    @Autowired
    private UserService userService;

//    分页查询用户的url
    @RequestMapping("/user-list")
    public Result userList(Page page, User user){
        page = userService.getUserByPage(page, user);

        return Result.ok(page);

    }

    @Autowired
    private TokenUtils tokenUtils;
//    添加用户
    @RequestMapping("/addUser")
    public Result addUser(@RequestBody User user, @RequestHeader(HEADER_TOKEN_NAME)String token){

        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        user.setCreateBy(userId);

//        执行业务
        Result result = userService.saveUser(user);

        return result;
    }

//    更新用户状态
    @RequestMapping("/updateState")
    public Result updateUserState(@RequestBody User user){
        Result result = userService.setUserState(user);

        return result;
    }


    @Autowired
    private RoleService roleService;

//查询用户已分配的角色的url
//   @PathVariable:将路径的userId赋值给参数userId
    @RequestMapping("/user-role-list/{userId}")
    public Result userRoleList(@PathVariable Integer userId){
        List<Role> roles = roleService.queryUserRoleByUid(userId);

        return Result.ok(roles);
    }

    @Autowired
    private StringRedisTemplate redisTemplate;
//    给用户分配角色
    @RequestMapping("/assignRole")
    public Result assignRole(@RequestBody  AssignRoleDto assignRoleDto){
        userService.assignRole(assignRoleDto);
        redisTemplate.delete("authTree:"+assignRoleDto.getUserId());
        return Result.ok("分配角色成功");
    }

//    根据用户id删除单个用户
    @RequestMapping("/deleteUser/{userId}")
    public Result deleteUserById(@PathVariable Integer userId){

        Result result = userService.removeUserByIds(Arrays.asList(userId));


        return result;
    }

//    批量删除用户
    @RequestMapping("/deleteUserList")
    public Result deleteUserByIds(@RequestBody List<Integer> userIds){

        Result result = userService.removeUserByIds(userIds);

        return result;
    }

//    修改用户名称
    @RequestMapping("/updateUser")
    public Result updateUser(@RequestBody  User user,@RequestHeader(HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();

        user.setUpdateBy(userId);

        Result result = userService.setUserById(user);

        return result;
    }

//    重置密码
    @RequestMapping("/updatePwd/{userId}")
    public Result resetPwd(@PathVariable  Integer userId){
        Result result = userService.setPwdById(userId);

        return result;
    }

    //    修改密码
    @RequestMapping("/updateUserPwd")
    public Result updateUserPwd(@RequestBody User user){
        Result result = userService.setPwdById(user.getUserId(),user.getUserPwd());
        return result;
    }

//    修改用户信息
    @RequestMapping("/updateUserData")
    public Result updateUserData(@RequestBody User user,@RequestHeader(HEADER_TOKEN_NAME) String token)
    {

        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        user.setUpdateBy(currentUser.getUserId());

        System.out.println(currentUser.getUserName());
        return userService.setDataById(user);
    }
}
