package com.sunset.mapper;

import com.sunset.entity.LoginPwd;
import com.sunset.entity.RegisterEntity;
import com.sunset.entity.UserInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SignMapper {
    int RegisterInsert(RegisterEntity registerEntity);
    RegisterEntity FindUserPhone(String phone);
    RegisterEntity FindUserInfo(String uid);
    int UpdatePwd(String password,String uid);
    int ResetPwd(String phone, String password);
    int UpdatePhone(String uid,String phone);
    int UserInfoInsert(UserInfoEntity userInfoEntity);
    UserInfoEntity GetUserInfo(String uid);
}
