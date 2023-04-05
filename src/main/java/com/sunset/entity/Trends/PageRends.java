package com.sunset.entity.Trends;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PageRends {
    @Schema(description = "用户id")
    private String uid;
    @Schema(description = "页码")
    private Integer page_num;
    @Schema(description = "条数")
    private Integer page_rows;
}
