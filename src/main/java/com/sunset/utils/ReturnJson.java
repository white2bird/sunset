package com.sunset.utils;

import lombok.Data;

import java.io.Serializable;
@Data
public class ReturnJson<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
    private long timestamp;
    public static <T>ReturnJson<T> success(T data,String message){
        ReturnJson<T> returnJson = new ReturnJson<>();
        returnJson.code = 200;
        returnJson.data = data;
        returnJson.message = message;
        return returnJson;
    }

    public static <T> ReturnJson <T> fail(Integer code, String message){
        ReturnJson<T> returnJson = new ReturnJson<>();
        returnJson.code = code;
        returnJson.message = message;
        return returnJson;
    }
}
