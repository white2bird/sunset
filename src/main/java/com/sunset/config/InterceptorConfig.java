package com.sunset.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sunset.utils.AuthMsToken;
import com.sunset.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Slf4j
public class InterceptorConfig extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        /* 解决跨域 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setCharacterEncoding("UTF-8");
        AuthMsToken authVaildate;
        if(handler instanceof HandlerMethod) {
            authVaildate = ((HandlerMethod) handler).getMethodAnnotation(AuthMsToken.class);
        }else{
            return true;
        }
        //没有声明需要权限,或者声明不验证权限
        if(authVaildate == null || !authVaildate.validate()){
            return true;
        }
        String msToken = request.getHeader("ms_token");
        log.info("获取的token：" + msToken);
        if (msToken != null) {
            HashMap<String, String> map = TokenUtils.SelectToken(msToken);
            String uid = map.get("uid");
            log.info("解析token：" + uid);
            return true;
        }else{
            // token过期
            throw new TokenExpiredException(null);
        }

    }
}
