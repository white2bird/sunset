package com.sunset.utils;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;

@Slf4j
public class TokenUtils {
    private static String key = "sunset_server123456.";
    private static long jt_date = 1000 * 60 * 60 * 60; // 毫秒

    public static String setToken(String uid) {
        Date t = new Date(System.currentTimeMillis() + jt_date);
        return JWT.create()
                .withClaim("uid", uid)
                .withClaim("stamp", System.currentTimeMillis())
                .withExpiresAt(t)
                .sign(Algorithm.HMAC256(key));
    }

    public static HashMap<String, String> SelectToken(String token) {
        HashMap<String, String> map = new HashMap<>();
        if(token != null){
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(key)).build(); // 传入签名
            DecodedJWT verify = jwtVerifier.verify(token);// 传入token
            Claim uid = verify.getClaim("uid");
        map.put("uid", uid.asString());
        }else{
            map.put("uid", null);
        }
        return map;
    }
}
