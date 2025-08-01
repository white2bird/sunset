package com.sunset.service;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.sunset.config.RedisConfig;
import com.sunset.constants.RedisConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.aliyun.dysmsapi20170525.Client;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CommonService {

    private Client phoneClient;


    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init() throws Exception {
        Config config = new Config();
        // 配置 AccessKey ID，请确保代码运行环境设置了环境变量。
        config.setAccessKeyId(accessKeyId);
        // System.getenv()方法表示获取系统环境变量，请配置环境变量后，在此填入环境变量名称，不要直接填入AccessKey信息。
        config.setAccessKeySecret(accessKeySecret);
        // 配置 Endpoint
        config.endpoint = "dysmsapi.aliyuncs.com";
        this.phoneClient = new Client(config);
    }

    public void SendCodeWithRedis(String phone){
        String key = RedisConstants.PHONE_CODE + phone;
        int randomCode = randomCount(1111, 9999);
        redisTemplate.opsForValue().set(key, String.valueOf(randomCode));
        redisTemplate.expire(key, RedisConstants.EXPIRE_TIME_FIVE_MIN, TimeUnit.SECONDS);
        try{
            SendCode(phone, randomCode);
        }catch (Exception e){
            log.error("send code error {} {}", phone, randomCode, e);
        }
    }

    public static Integer randomCount(Integer start, Integer end){
        return (int)(Math.random()*(end - start +1) + start);
    }


    public void SendCode(String phone, Integer randomCode) throws Exception {

        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("柚轻松")
                .setTemplateCode("SMS_491380136")
                .setTemplateParam("{\"code\":\"" + randomCode+"\"}");
        // 获取响应对象
        SendSmsResponse sendSmsResponse = phoneClient.sendSms(sendSmsRequest);
    }
}
