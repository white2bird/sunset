package com.sunset.entity.Know;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PageKnow {
    @Schema(description = "类型")
    private String type;
    @Schema(description = "页码")
    private Integer page_num;
    @Schema(description = "条数")
    private Integer page_rows;
}
