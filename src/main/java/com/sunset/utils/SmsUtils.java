package com.sunset.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsUtils {



    public boolean sendSmsCode(String phoneNumber, String code) {
      return true;
    }
}