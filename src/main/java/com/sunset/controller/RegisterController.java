package com.sunset.controller;

import com.sunset.entity.Register;
import com.sunset.service.RegisterService;
import com.sunset.utils.ReturnJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
        String phone = register.phone;
        String verCode = register.verCode;
        Register p = registerService.RegisterFindPhone(phone); // 查询手机号是否存在
        log.info(String.valueOf(p));
        if(p != null){
            return ReturnJson.fail(-1, "手机号已注册");
        }
        if (phone == null || phone == "") {
            return ReturnJson.fail(-1, "手机号不能为空");
        }
        if (verCode == null || verCode == "") {
            return ReturnJson.fail(-1, "验证码不能为空");
        }
        registerService.RegisterInsert(register);
        return ReturnJson.success(null, "ok");
    }
}
