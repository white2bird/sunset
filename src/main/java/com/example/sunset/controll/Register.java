package com.example.sunset.controll;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
public class Register {
@PostMapping("/register")
    public String Register(){
        return "成功";
    }
}
