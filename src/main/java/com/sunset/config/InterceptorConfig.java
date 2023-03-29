package com.sunset.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class InterceptorConfig implements HandlerInterceptor {
    public boolean preHandler(HttpServletRequest request, HttpServletResponse response, Object handler){
        log.info(String.valueOf(request));
        return true;
    }

    public void postHandler(HttpServletRequest request, HttpServletResponse
            response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info(String.valueOf(request));
    }
}
