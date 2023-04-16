package com.sunset.entity.Know;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class KnowEntity {
    private  String id;
    @Schema(description = "标题")
    private  String title;
    @Schema(description = "第三方url")
    private  String url;
    @Schema(description = "封面")
    private  String cover_img;
    @Schema(description = "内容【富文本】")
    private  String content;
    @Schema(description = "类型 【运动，饮食....】")
    private  String type;
    @Schema(description = "是否第三方")
    private  String isthird;
    @Schema(description = "阅读数")
    private  String read_num;
    @Schema(description = "收藏数")
    private  String like_num;
    @Schema(description = "评论数")
    private  String comment_num;
    @Schema(description = "创建时间")
    private  String create_time;
}
