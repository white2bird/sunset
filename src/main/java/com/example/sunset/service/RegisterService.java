package com.example.sunset.service;

import com.example.sunset.entity.Register;
import com.example.sunset.mapper.RegisterMapper;
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
