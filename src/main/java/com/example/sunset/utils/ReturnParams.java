package com.example.sunset.utils;

public class ReturnParams {
    private int code;
    private String message;
    private T data;
    private String date_time;
    private  long timestamp;

    public int getCode() {
        return code;
    }
    public String getMessage(){
        return message;
    }
    public String getDate_time(){
        return date_time;
    }
    public long getTimestamp(){
        return timestamp;
    }
}
