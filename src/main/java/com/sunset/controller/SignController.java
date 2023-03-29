package com.sunset.controller;

import com.sunset.entity.RegisterEntity;
import com.sunset.service.SignService;
import com.sunset.utils.ReturnJson;
import com.sunset.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Sign", description = "登录注册")
@RestController
@RequestMapping("/sign")
@ResponseBody
@Slf4j
public class SignController {
    private SignService registerService;

    public SignController(SignService registerService) {
        this.registerService = registerService;
    }
    @Operation(summary = "注册")
    @PostMapping("/register")
    public ReturnJson<String> log_registe (@RequestBody RegisterEntity registerEntity) {
      return  registerService.RegisterInsert(registerEntity);
    }
    @Operation(summary = "验证码登录")
    @PostMapping("/login")
    public ReturnJson<String> log_login(@RequestBody LoginVerCode loginVerCode){

//        Map<String,String> map = TokenUtils.SelectToken(loginVerCode.getToken());
//        log.info(map.get("uid"));
        return ReturnJson.success(null,"ok");
    }
    @Data
    public  class LoginVerCode{
        @Schema(description = "手机号")
        private String phone;
        @Schema(description = "验证码")
        private String verCode;
    }
}
