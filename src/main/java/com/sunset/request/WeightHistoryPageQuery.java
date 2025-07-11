package com.sunset.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Schema(description = "称重历史记录")
public class WeightHistoryPageQuery {
    @NotNull(message = "page cannot be null")
    @Schema(description = "页码")
    private Integer pageNo;
    @NotNull(message = "pageSize cannot be null")
    @Schema(description = "页面大小")
    private Integer pageSize;
    @Schema(description = "开始时间")
    private Date start;
    @Schema(description = "结束时间")
    private Date end;
}
