package com.sunset.utils;

public class UserIdThreadLocal {
    private static final ThreadLocal<String> USER_ID = new ThreadLocal<>();


    public static String getUserId(){
        return USER_ID.get();
    }

    public static void setUserId(String id){
        USER_ID.set(id);

    }

    public static void clear(){
        USER_ID.remove();
    }
}
