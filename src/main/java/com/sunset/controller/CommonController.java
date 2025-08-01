package com.sunset.controller;

import com.sunset.service.CommonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CommonController {

    @Resource
    private CommonService commonService;

    @GetMapping("/sendCode")
    public void sendCode(@RequestParam  String phone){
        commonService.SendCodeWithRedis(phone);
    }
}
