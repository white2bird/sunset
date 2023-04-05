package com.sunset.entity.Trends;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SetComm {
    @Schema(description = "评论")
    private String content;
    @Schema(description = "动态详情ID")
    private String trends_id;
}
