package org.example.service.impl;

import org.example.beans.Result;
import org.example.beans.Role;
import org.example.beans.RoleAuth;
import org.example.dto.AssignAuthDto;
import org.example.mapper.RoleAuthMapper;
import org.example.page.Page;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.example.mapper.RoleMapper;
import org.example.service.RoleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//指定缓存的名称(数据保存到redis中的键的前缀，一般值给该标注的类的全路径)
@CacheConfig(cacheNames = "org.example.service.impl.RoleServiceImpl")
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    //    查询所有角色
    //    查询方法上标注Cacheable注解,all层下的role
    @Cacheable(key="'all:role'")
    @Override
    public List<Role> queryAllRole() {

        return roleMapper.getAllRole();
    }

    @Override
    public List<Role> queryUserRoleByUid(Integer userId) {
        return roleMapper.geUserRoleByUid(userId);
    }

    @Override
    public Page queryRolePage(Page page, Role role) {
//        查询角色行
        Integer roleRowCount = roleMapper.getRoleRowCount(role);

//        分页查询角色
        List<Role> rolePage = roleMapper.getRolePage(page, role);

//        组装
        page.setTotalNum(roleRowCount);
        page.setResultList(rolePage);

        return page;
    }

//    添加，清缓存，不清会导致序列号出现问题
    @CacheEvict(key="'all:role'")
    @Override
    public Result saveRole(Role role) {
//        判断是否存在
        Role roleByNameOrCode = roleMapper.getRoleByNameOrCode(role.getRoleName(), role.getRoleCode());

        if(roleByNameOrCode!=null)return Result.err(Result.CODE_ERR_BUSINESS,"角色存在");

        int i = roleMapper.insertRole(role);
        if(i>0)return Result.ok("添加成功");

        return Result.err(Result.CODE_ERR_BUSINESS,"添加失败");
    }

//    角色启用/禁用
    @CacheEvict(key ="'all:role'")
    @Override
    public Result setRoleState(Role role) {

        int i = roleMapper.setRoleStateByRid(role.getRoleId(), role.getRoleState());
        if(i>0)return Result.ok("角色启用/禁用成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"角色启用/禁用失败");
    }

    @Autowired
    private RoleAuthMapper roleAuthMapper;

//    删除角色
    @Transactional
    @CacheEvict(key ="'all:role'")
    @Override
    public Result deleteRoleById(Integer roleId) {


        int i = roleMapper.removRoleById(roleId);
        if(i>0){
            roleAuthMapper.removeRoleAuthByRid(roleId);
            return Result.ok("角色删除成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"角色删除失败");
    }

    //    查询角色分配的所有权限
    @Override
    public List<Integer> queryRoleAuthIds(Integer roleId) {

        return roleAuthMapper.findAuthIdsByRid(roleId);
    }


    @Autowired
    private StringRedisTemplate redisTemplate;

    //    给角色分配权限
    @Transactional
    @Override
    @CacheEvict(key = "'all:role'")
    public void saveRoleAuth(AssignAuthDto assignAuthDto) {

        roleAuthMapper.removeRoleAuthByRid(assignAuthDto.getRoleId());
        List<Integer> authIds = assignAuthDto.getAuthIds();

        for (Integer authId : authIds) {
            RoleAuth roleAuth = new RoleAuth();
            roleAuth.setRoleId(assignAuthDto.getRoleId());
            roleAuth.setAuthId(authId);
            roleAuthMapper.insertRoleAuth(roleAuth);
        }
    }

    @Override
    @Transactional
    @CacheEvict(key ="'all:role'")
    public Result setRoleByRid(Role role) {
        int i = roleMapper.setDescByRid(role);
        if(i>0){
            return Result.ok("角色修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"角色修改失败");
    }
}
