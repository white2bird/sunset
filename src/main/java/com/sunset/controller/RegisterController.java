package com.sunset.controller;

import com.sunset.entity.Register;
import com.sunset.service.RegisterService;
import com.sunset.utils.ReturnJson;
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


    @PostMapping("/register")
    public ReturnJson<String> log_register(@RequestBody Register register) {
        System.out.println(register.getPhone());
        return ReturnJson.success("phone","ok");
//        return registerService.add(register);
    }
}
