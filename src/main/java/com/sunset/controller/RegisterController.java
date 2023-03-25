package com.sunset.controller;

import com.sunset.entity.Register;
import com.sunset.service.RegisterService;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/sign")
@ResponseBody
public class RegisterController {
    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @PostMapping(value="/log_register",produces = "application/json;charset=UTF-8")
    public Boolean log_register(@RequestBody Register register) {
        return registerService.add(register);
    }
}
