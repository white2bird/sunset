package com.sunset.config;

import com.sunset.entity.RegisterEntity;
import com.sunset.service.SignService;
import com.sunset.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Slf4j
public class InterceptorConfig extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String msToken = request.getHeader("ms_token");
        log.info("获取的token：" + msToken);
        if (msToken != null) {
            HashMap<String, String> map = TokenUtils.SelectToken(msToken);
            String uid = map.get("uid");
//            RegisterEntity uinfo =  FindUserInfo(uid);
            log.info("解析token：" + uid);
//            log.info("用户信息：" + uinfo);
        }


        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse
            response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info(String.valueOf(request));
    }
}
