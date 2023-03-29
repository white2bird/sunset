package com.sunset.service;
import com.sunset.entity.RegisterEntity;
import com.sunset.mapper.SignMapper;
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
public class SignService {
    @Autowired
    SignMapper signMapper;

    public ReturnJson<String> RegisterInsert(RegisterEntity registerEntity) {
        String phone = registerEntity.phone;
        String verCode = registerEntity.verCode;
        RegisterEntity p = FindUserPhone(phone); // 查询手机号是否存在
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
        registerEntity.setUid(uuid);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        registerEntity.setCreate_time(dateTime);
        signMapper.RegisterInsert(registerEntity);
        return ReturnJson.success(null, "ok");
    }

    public RegisterEntity FindUserPhone(String phone){
        return signMapper.FindUserPhone(phone);
    }
}
