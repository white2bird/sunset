package com.sunset.controller;

import com.sunset.entity.LoginPwd;
import com.sunset.entity.LoginVerCode;
import com.sunset.entity.PwdEntity;
import com.sunset.entity.RegisterEntity;
import com.sunset.service.SignService;
import com.sunset.utils.AuthMsToken;
import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "Sign")
@RestController
@RequestMapping("/sign")
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SignController {
    @Autowired
    SignService signService;
    @Operation(summary = "注册【待用】")
    @PostMapping("/register")
    @AuthMsToken
    public ReturnJson<String> sign_register (@RequestBody RegisterEntity registerEntity) {
      return  signService.RegisterInsert(registerEntity);
    }
    @Operation(summary = "验证码登录/注册")
    @PostMapping("/code_login")
    public ReturnJson<String> sign_verLogin(@RequestBody LoginVerCode loginVerCode){
        return  signService.LoginVerToken(loginVerCode);
    }
    @Operation(summary = "密码登录")
    @PostMapping("/pwd_login")
    public ReturnJson<String> sign_pwdLogin(@RequestBody LoginPwd loginPwd){
        return  signService.LoginPwdToken(loginPwd);
    }

    @Operation(summary = "设置密码")
    @PostMapping("/set_pwd")
    @AuthMsToken
    public ReturnJson<String> sign_setpwd(@RequestBody PwdEntity pwdEntity, HttpServletRequest request){
        return  signService.SetPassword(pwdEntity,request);
    }
}
