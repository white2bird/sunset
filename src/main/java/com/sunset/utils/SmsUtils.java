package com.sunset.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsUtils {

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.sms.signName}")
    private String signName;

    @Value("${aliyun.sms.templateCode}")
    private String templateCode;

    public boolean sendSmsCode(String phoneNumber, String code) {
      return true;
    }
}