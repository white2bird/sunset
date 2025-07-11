package com.sunset.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Schema(description = "历史趋势接口")
public class HistoryTrendRequest {
    @NotNull(message = "时间类型缺失")
    @Schema(description = "1:近一周 2：近一月 3：近90天 4：自定义（需要传递时间参数 start end）")
    private Integer timeType;
//    @Schema(description = "查询类型： 1：体重 2：bmi 3：体质率")
//    @NotNull(message = "查询类型参数确实")
//    private Integer queryType;
    @Schema(description = "开始时间")
    private Date start;
    @Schema(description = "结束时间")
    private Date end;

}
