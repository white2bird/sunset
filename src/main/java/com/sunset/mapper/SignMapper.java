package com.sunset.mapper;

import com.sunset.entity.Sign.RegisterEntity;
import com.sunset.entity.User.UserInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SignMapper {
    // 注册 【废弃】
    int RegisterInsert(RegisterEntity registerEntity);
    //根据手机号校验用户是否存在
    RegisterEntity FindUserPhone(String phone);
    //根据 token 解析用户 id 获取用户信息
    RegisterEntity FindUserInfo(String uid);
    // 设置密码
    int UpdatePwd(String password,String update_time,String uid);
    // 忘记密码
    int ResetPwd(String phone, String password);
    // 换绑手机号
    int UpdatePhone(String uid,String phone);
    // 注册成功初始化用户信息
    int UserInfoInsert(UserInfoEntity userInfoEntity);
    // 获取用户信息
    UserInfoEntity GetUserInfo(String uid);
    // 更新用户信息
    int UpdateUserInfo(UserInfoEntity userInfoEntity);
    // 更新用户简介
    int UpdateUserDesc(UserInfoEntity userInfoEntity);
    // 删除用户
    int DeleteUser(String uid);
    // 删除用户信息
    int DeleteUserInfo(String uid);

}
