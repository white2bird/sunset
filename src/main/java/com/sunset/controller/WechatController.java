package com.sunset.controller;

import com.sunset.service.WechatService;
import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
import java.util.UUID;

@Tag(name = "微信登录接口")
@RestController
public class WechatController {
    
    @Autowired
    private WechatService wechatService;
    

    
    /**
     * 微信登录回调处理
     */
    @Operation(summary = "微信登录回调处理（绑定或者直接返回token）")
    @GetMapping("/wechat/callback")
    public ReturnJson<Map<String, Object>> wechatCallback(@RequestParam("code") String code,
                                                          @RequestParam(value = "state", required = false) String state) {
            // 1. 通过code获取access_token
            Map<String,  Object> accessToken = wechatService.getLocalTokenOrBindPhone(code);
            return ReturnJson.success(accessToken);

    }

    @Operation(summary = "绑定接口")
    @GetMapping("/wechat/bindphone")
    public ReturnJson<Map<String, Object>> wechatBindPhone(@RequestParam("openid") String openid,
                                              @RequestParam("phone") String phone,
                                                           @RequestParam("verCode") String verCode) {
        // 1. 通过code获取access_token
        Map<String,  Object> accessToken = wechatService.bindPhone(openid, phone, verCode);
        return ReturnJson.success(accessToken);
    }
}
