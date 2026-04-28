package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.User;
import org.example.page.Page;

import java.util.List;

public interface UserMapper {

//    根据账号查询用户信息
    public User getUserByCode(String userCode);

//    查询用户行数
    public Integer getUserRowCount(User user);

//分页查询用户的方法
    public List<User> getUserByPage(@Param("page") Page page, @Param("user") User user);

//    添加账号
    public int insertUser(User user);

// 根据id修改用户状态
    public int setStateByUid(Integer userId,String userState);

//    根据用户id修改用户状态为删除
    public int setIsDeleteByUids(List<Integer> userIdList);

//    根据id修改用户名称
    public int setUserNameByUid(User user);

//    根据用户id修改密码
    public int setPwdByUid(Integer userId,String userPwd);

//   根据用户id修改用户信息
    int setDataById(@Param("user") User user);
}
