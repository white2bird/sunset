package com.sunset.entity.Trends;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
public class ObjTrends {
    private String id;
    private String uid;
    @Schema(description = "头像")
    private String avator;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "动态")
    private String text;
    @Schema(description = "图片集合")
    private List images;
    @Schema(description = "点赞数量")
    private String star;
    @Schema(description = "是否点赞")
    private boolean isstar;
    @Schema(description = "评论数量")
    private long comment_num;
    @Schema(description = "前三评论")
    private List<CommThree> comment_list;
    @Schema(description = "发布时间")
    private String create_time;
}