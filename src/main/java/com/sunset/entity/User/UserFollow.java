package com.sunset.entity.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFollow {
    private String id;
    private String uid;
    @Schema(description = "关注")
    private String following = "0";
    @Schema(description = "粉丝")
    private String followers = "0";
    @Schema(description = "获赞")
    private String star = "0";
}
