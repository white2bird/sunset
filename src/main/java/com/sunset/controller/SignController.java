package com.sunset.controller;

import com.alibaba.fastjson.JSONObject;
import com.sunset.entity.*;
import com.sunset.service.SignService;
import com.sunset.utils.AuthMsToken;
import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
    public ReturnJson<String> sign_register(@RequestBody RegisterEntity registerEntity) {
        return signService.RegisterInsert(registerEntity);
    }

    @Operation(summary = "验证码登录/注册")
    @PostMapping("/code_login")
    public ReturnJson<String> sign_verLogin(@RequestBody LoginVerCode loginVerCode) {
        log.info("登录请求参数：" + loginVerCode);
        return signService.LoginVerToken(loginVerCode);
    }

    @Operation(summary = "密码登录")
    @PostMapping("/pwd_login")
    public ReturnJson<String> sign_pwdLogin(@RequestBody LoginPwd loginPwd) {
        return signService.LoginPwdToken(loginPwd);
    }

    @Operation(summary = "设置密码")
    @PostMapping("/set_pwd")
    @AuthMsToken
    public ReturnJson<String> sign_setpwd(@RequestBody PwdEntity pwdEntity, HttpServletRequest request) {
        return signService.SetPassword(pwdEntity, request);
    }

    @Operation(summary = "忘记密码>>查询手机号")
    @PostMapping("/get_isPhone")
    public ReturnJson<String> get_isPhone(@RequestBody LoginVerCode loginVerCode) {
        return signService.FindIsPhone(loginVerCode);
    }

    @Operation(summary = "忘记密码>>重置密码")
    @PostMapping("/reset_pwd")
    public ReturnJson<String> get_isPhone(@RequestBody LoginPwd loginPwd) {
        return signService.ResetPwd(loginPwd);
    }

    @Operation(summary = "换绑手机号")
    @PostMapping("/change_phone")
    @AuthMsToken
    public ReturnJson<String> get_isPhone(@RequestParam(name = "phone") String phone, HttpServletRequest request) {
        return signService.UpdatePhone(phone, request);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/get/userinfo")
    @AuthMsToken
    public ReturnJson<UserInfoEntity> getUserinfo(HttpServletRequest request) {
        return signService.GetUserInfo(request);
    }

    @Operation(summary = "更新用户信息")
    @PostMapping("/update/userinfo")
    @AuthMsToken
    public ReturnJson<String> updateUserinfo(@RequestBody UserInfoEntity userInfoEntity, HttpServletRequest request) {
        return signService.UpdateUserInfo(userInfoEntity, request);
    }

    @Operation(summary = "注销账号")
    @PostMapping("/distry_info")
    @AuthMsToken
    public ReturnJson<String> distryUserinfo(HttpServletRequest request) {
        return signService.DistryAccount(request);
    }
}
