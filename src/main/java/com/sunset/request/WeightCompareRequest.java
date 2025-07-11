package com.sunset.request;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class WeightCompareRequest {
    @Size(max = 2, min = 2, message = "对比数量错误")
    private List<Long> ids;
}
