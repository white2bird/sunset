package com.sunset.service;

import com.sunset.entity.Register;
import com.sunset.mapper.RegisterMapper;
import com.sunset.utils.ReturnJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class RegisterService {
    @Autowired
    RegisterMapper registerMapper;

    public int RegisterInsert(Register register) {
        String uuid = UUID.randomUUID().toString().toUpperCase();
        register.setUid(uuid);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        register.setCreate_time(dateTime);
        return registerMapper.RegisterInsert(register);
    }

    public Register RegisterFindPhone(String phone){
        return registerMapper.RegisterFindPhone(phone);
    }
}
