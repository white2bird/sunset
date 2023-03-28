package com.sunset.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ReturnJson<Object> otherException(Exception exception){
        return ReturnJson.fail(500,exception.getMessage());
    }
}
