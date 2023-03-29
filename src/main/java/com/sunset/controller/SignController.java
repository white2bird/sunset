package com.sunset.controller;

import com.sunset.entity.LoginVerCode;
import com.sunset.entity.RegisterEntity;
import com.sunset.service.SignService;
import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Sign", description = "登录注册")
@RestController
@RequestMapping("/sign")
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SignController {
    @Autowired
    SignService signService;
    @Operation(summary = "注册")
    @PostMapping("/register")
    public ReturnJson<String> sign_registe (@RequestBody RegisterEntity registerEntity) {
      return  signService.RegisterInsert(registerEntity);
    }
    @Operation(summary = "验证码登录")
    @PostMapping("/login")
    public ReturnJson<String> sign_verLogin(@RequestBody LoginVerCode loginVerCode){
        return  signService.LoginVerToken(loginVerCode);
    }
}
