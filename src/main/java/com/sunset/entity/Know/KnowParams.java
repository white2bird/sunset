package com.sunset.entity.Know;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class KnowParams {
    @Schema(description = "标题")
    private  String title;
    @Schema(description = "第三方url")
    private  String url;
    @Schema(description = "封面")
    private  String cover_img;
    @Schema(description = "内容【富文本】")
    private  String content;
    @Schema(description = "类型 【运动，饮食....】")
    private  Integer type;
    @Schema(description = "是否第三方")
    private  Integer isthird;
}
