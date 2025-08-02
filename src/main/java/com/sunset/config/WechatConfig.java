package com.sunset.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WechatConfig {
    
    @Value("${wechat.appid}")
    private String appid;
    
    @Value("${wechat.appsecret}")
    private String appsecret;
    
    // 回调地址，需在微信开放平台配置
//    @Value("${wechat.redirect-uri}")
//    private String redirectUri;
    
    // 微信授权地址
//    private static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";
    
    // 获取access_token地址
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    
    // 获取用户信息地址
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    public String getAppid() {
        return appid;
    }

    public String getAppsecret() {
        return appsecret;
    }


    public String getAccessTokenUrl(String code) {
        return String.format(ACCESS_TOKEN_URL, appid, appsecret, code);
    }

    public String getUserUserInfoUrl(String accessToken, String openId) {
        return String.format(USER_INFO_URL, accessToken, openId);
    }

    public static void main(String[] args) {
        String code = new WechatConfig().getAccessTokenUrl("code");
        System.out.println(code);
    }
}
