package com.sunset.controller;

import com.sunset.entity.Register;
import com.sunset.service.RegisterService;
import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Sign", description = "登录注册")
@RestController
@RequestMapping("/sign")
@ResponseBody
@Slf4j
public class RegisterController {
    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ReturnJson<String> log_register(@RequestBody Register register) {
      return  registerService.RegisterInsert(register);
    }
}
