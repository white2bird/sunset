package com.sunset.utils;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@Slf4j
@ControllerAdvice
public class GlobalException extends  RuntimeException{
    // token 失效
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseBody
    public  ReturnJson tokenException(){
        return ReturnJson.fail(401, "令牌过期");
    }
    // 全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ReturnJson<Object> otherException(Exception exception){
        log.info(exception.toString());
        return ReturnJson.fail(500,exception.getMessage());
    }


}
