package com.sunset.entity.User;

import lombok.Data;

@Data
public class FollowComm {
    private String id;
    private String uid;
    private String trends_id; // 动态id
    private String create_time;
}
