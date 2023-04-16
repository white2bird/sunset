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
    @Schema(description = "类型 【饮食，运动，减肥，亲子，旅游】")
    private  Integer type = 0;
    private  String type_name;
    @Schema(description = "是否第三方")
    private  Integer isthird = 0;
    @Schema(description = "阅读数")
    private  Integer read_num = 0;
    @Schema(description = "收藏数")
    private  Integer like_num = 0;
    @Schema(description = "评论数")
    private  Integer comment_num = 0;
    @Schema(description = "创建时间")
    private  String create_time;
}
