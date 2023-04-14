package com.sunset.entity.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FollowAll {
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "头像")
    private String avator;
    @Schema(description = "uid")
    private String uid;
}
