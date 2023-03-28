package com.sunset.controller;

import com.sunset.entity.Register;
import com.sunset.service.RegisterService;
import com.sunset.utils.ReturnJson;
import com.sunset.utils.TokenUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("/login")
    public ReturnJson<String> log_login(@RequestParam("token") String token){
        Map<String,String> map = TokenUtils.SelectToken(token);
        log.info(map.get("uid"));
        return ReturnJson.success(null,"ok");
    }
}
