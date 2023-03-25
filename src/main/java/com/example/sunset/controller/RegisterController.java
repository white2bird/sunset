package com.example.sunset.controller;

import com.example.sunset.entity.Register;
import com.example.sunset.service.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
public class RegisterController {
    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @PostMapping("/register")
    public Boolean add(Register register) {
        return registerService.add(register);
    }
}
