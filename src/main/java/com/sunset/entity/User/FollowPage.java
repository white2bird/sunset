package com.sunset.entity.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FollowPage {
    @Schema(description = "页码")
    private Integer page_num;
    @Schema(description = "条数")
    private Integer page_rows;
}
