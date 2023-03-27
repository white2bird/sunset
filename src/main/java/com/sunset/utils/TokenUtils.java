package com.sunset.utils;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.HashMap;

@Slf4j
public class TokenUtils {
    private  static final String key = "sunset_server_123456.";
    public static String setToken(String uid){
        Calendar insCalendar = Calendar.getInstance();
        insCalendar.add(Calendar.SECOND, 7); // 过期时间
        String token = JWT.create().withClaim("uid",uid).withExpiresAt(insCalendar.getTime()).sign(Algorithm.HMAC256(key));
        return token;
    }

    public static HashMap<String, String> SelectToken(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(key)).build(); // 传入签名
        DecodedJWT verify = jwtVerifier.verify(token);// 传入token
        Claim uid = verify.getClaim("uid");
        HashMap<String,String> map = new HashMap();
        map.put("uid",uid.asString());
        return map;
    }
}
