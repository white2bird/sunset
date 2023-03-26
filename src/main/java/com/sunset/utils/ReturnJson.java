package com.sunset.utils;

import java.io.Serializable;

public class ReturnJson<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
    private long timestamp;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ReturnJson() {
        this.timestamp = System.currentTimeMillis();
    }
    public static <T>ReturnJson<T> success(T data,String message){
        ReturnJson<T> returnJson = new ReturnJson();
        returnJson.code = 200;
        returnJson.data = data;
        returnJson.message = message;
        return returnJson;
    }

    public static <T> ReturnJson <T> fail(Integer code, String message){
        ReturnJson<T> returnJson = new ReturnJson();
        returnJson.code = code;
        returnJson.message = message;
        return returnJson;
    }
}
