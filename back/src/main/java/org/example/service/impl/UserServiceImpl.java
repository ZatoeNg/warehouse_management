package org.example.service.impl;

import org.example.beans.Result;
import org.example.beans.User;
import org.example.beans.UserRole;
import org.example.dto.AssignRoleDto;
import org.example.mapper.RoleMapper;
import org.example.mapper.UserMapper;
import org.example.mapper.UserRoleMapper;
import org.example.page.Page;
import org.example.service.UserService;
import org.example.utils.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByCode(String userCode) {
        return userMapper.getUserByCode(userCode);
    }

    @Override
    public Page getUserByPage(Page page, User user) {

//        查询行数
        Integer userRowCount = userMapper.getUserRowCount(user);

//        分页查询用户
        List<User> userByPage = userMapper.getUserByPage(page, user);

//        组装分页信息
        page.setTotalNum(userRowCount);
        page.setResultList(userByPage);

        return page;
    }

//    添加用户
    @Override
    @Transactional
    public Result saveUser(User user) {

//        判断是否存在
        User userByCode = userMapper.getUserByCode(user.getUserCode());
        if(userByCode!=null){
            return Result.err(Result.CODE_ERR_BUSINESS,"账号已存在");
        }

//        加密密码
        String pwd = DigestUtil.hmacSign(user.getUserPwd());
        user.setUserPwd(pwd);

//        执行添加
        int i = userMapper.insertUser(user);
        if(i>0){
            return Result.ok("添加成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"添加失败");
    }

    //    启用或禁用用户
    @Override
    @Transactional
    public Result setUserState(User user) {
        int i = userMapper.setStateByUid(user.getUserId(), user.getUserState());
        if(i>0){
            return Result.ok("启用/禁用成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"启用/禁用失败");
    }

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

//    事务处理，分配角色
    @Transactional
    @Override
    public void assignRole(AssignRoleDto assignRoleDto) {

       userRoleMapper.removeUserRoleById(assignRoleDto.getUserId());

        List<String> roleCheckList = assignRoleDto.getRoleCheckList();
        for (String roleName : roleCheckList) {
            Integer roleIdByName = roleMapper.getRoleIdByName(roleName);
            UserRole userRole = new UserRole();
            userRole.setUserId(assignRoleDto.getUserId());
            userRole.setRoleId(roleIdByName);
            userRoleMapper.insertUserRole(userRole);
            }
        }

    @Override
    @Transactional
    public Result removeUserByIds(List<Integer> userIdList) {
        int i = userMapper.setIsDeleteByUids(userIdList);
        if(i>0) return Result.ok("删除成功");

        return Result.err(Result.CODE_ERR_BUSINESS,"删除失败");
    }

//    修改用户
    @Override
    @Transactional
    public Result setUserById(User user) {
        int i = userMapper.setUserNameByUid(user);
        if(i>0)return Result.ok("修改成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"修改失败");
    }

    @Override
    @Transactional
    public Result setPwdById(Integer userId) {
//        初始密码123456
        String pwd = DigestUtil.hmacSign("123456");

        int i = userMapper.setPwdByUid(userId, pwd);
        if(i>0)return Result.ok("重置成功");

        return Result.err(Result.CODE_ERR_BUSINESS,"重置失败");
    }

    @Override
    @Transactional
    public Result setPwdById(Integer userId, String userPwd) {
//
        String pwd = DigestUtil.hmacSign(userPwd);

        int i = userMapper.setPwdByUid(userId, pwd);
        if(i>0)return Result.ok("密码修改成功");

        return Result.err(Result.CODE_ERR_BUSINESS,"密码修改失败");
    }

    @Override
    public Result setDataById(User user) {

        int i = userMapper.setDataById(user);
        if(i>0)return Result.ok("修改成功");

        return Result.err(Result.CODE_ERR_BUSINESS,"修改失败");
    }


}
