package com.sunset.utils;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalException extends  RuntimeException{

    // token 失效
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseBody
    public  ReturnJson tokenException(TokenExpiredException exception){
        log.info(exception.toString());
        log.info("Token异常："+exception.getMessage());
        return ReturnJson.fail(401, "令牌过期");
    }
    // 全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ReturnJson<Object> otherException(Exception exception){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取请求相关信息
        String requestUrl = request.getRequestURL().toString();
        String method = request.getMethod();
        String clientIp = request.getRemoteAddr();

        log.info("全局异常 - 请求URL: {}, 方法: {}, 客户端IP: {}", requestUrl, method, clientIp);
        log.info("异常信息: {}", exception.getMessage());

        exception.printStackTrace();
        return ReturnJson.fail(500,exception.getMessage());
    }


}
