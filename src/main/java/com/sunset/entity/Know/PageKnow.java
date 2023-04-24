package com.sunset.entity.Know;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PageKnow {
    @Schema(description =  "是否筛选有图")
    private boolean isimg;
    @Schema(description = "类型")
    private String type;
    @Schema(description = "是否第三方")
    private String isthird;
    @Schema(description = "搜索的title关键字")
    private String keyword;
    @Schema(description = "页码")
    private Integer page_num;
    @Schema(description = "条数")
    private Integer page_rows;
}
