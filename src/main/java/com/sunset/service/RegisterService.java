package com.sunset.service;

import com.sunset.entity.Register;
import com.sunset.mapper.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    RegisterMapper registerMapper;

    public Boolean add(Register register){
        return registerMapper.add(register);
    }

}
