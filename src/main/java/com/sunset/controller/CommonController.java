package com.sunset.controller;

import com.sunset.service.CommonService;
import com.sunset.utils.ReturnJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CommonController {

    @Resource
    private CommonService commonService;

    @GetMapping("/sendCode")
    public ReturnJson sendCode(@RequestParam  String phone){
        commonService.SendCodeWithRedis(phone);
        return ReturnJson.success(null, "发送成功");
    }
}
