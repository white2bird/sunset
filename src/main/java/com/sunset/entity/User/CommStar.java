package com.sunset.entity.User;

import lombok.Data;

@Data
public class CommStar {
    private String id;
    private String uid;
    private String trends_id; // 动态id
    private String comment_id;
    private String create_time;
}
