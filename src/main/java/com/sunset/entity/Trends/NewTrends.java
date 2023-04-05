package com.sunset.entity.Trends;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NewTrends {
    private String id;
    private String uid;
    @Schema(description = "动态")
    private String text;
    @Schema(description = "图片集合")
    private String images;
    @Schema(description = "点赞数量")
    private String star;
    @Schema(description = "发布时间")
    private String create_time;
}
