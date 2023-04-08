package com.sunset.entity.Trends;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CommThree {
    @Schema(description = "昵称")
   private String nickname;
    @Schema(description = "评论")
    private String comment;
}
