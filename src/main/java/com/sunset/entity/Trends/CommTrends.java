package com.sunset.entity.Trends;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CommTrends {
    private String id;
    @Schema(description = "用户id")
    private String uid;
    private String to_uid;
    @Schema(description = "动态详情id")
    private String trends_id;
    @Schema(description = "评论")
    private String content;
    @Schema(description = "用户昵称")
    private String nickname;
    @Schema(description = "用户头像")
    private String avator;
    @Schema(description = "评论获赞数")
    private String star;
    @Schema(description = "发表评论时间")
    private String create_time;
}
