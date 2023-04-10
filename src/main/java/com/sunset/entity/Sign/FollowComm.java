package com.sunset.entity.Sign;

import lombok.Data;

@Data
public class FollowComm {
    private String id;
    private String uid;
    private String trends_id; // 动态id
    private String commnet_id; // 评论id
    private int type;
    private String create_time;
}
