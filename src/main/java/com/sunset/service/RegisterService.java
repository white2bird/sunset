package com.sunset.service;
import com.sunset.entity.Register;
import com.sunset.mapper.RegisterMapper;
import com.sunset.utils.ReturnJson;
import com.sunset.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
@Slf4j
@Service
public class RegisterService {
    @Autowired
    RegisterMapper registerMapper;

    public ReturnJson<String> RegisterInsert(Register register) {
        String phone = register.phone;
        String verCode = register.verCode;
        Register p = RegisterFindPhone(phone); // 查询手机号是否存在
        String token = TokenUtils.setToken(phone);
        if(p != null){
            return ReturnJson.fail(-1, token);//手机号已注册
        }
        if (phone == null || phone == "") {
            return ReturnJson.fail(-1, "手机号不能为空");
        }
        if (verCode == null || verCode == "") {
            return ReturnJson.fail(-1, "验证码不能为空");
        }
        String uuid = UUID.randomUUID().toString().toUpperCase();
        register.setUid(uuid);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        register.setCreate_time(dateTime);
        registerMapper.RegisterInsert(register);
        return ReturnJson.success(null, "ok");
    }

    public Register RegisterFindPhone(String phone){
        return registerMapper.RegisterFindPhone(phone);
    }
}
