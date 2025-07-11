package com.sunset.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Schema(name = "蓝牙请求结构体")
public class BlueRequest {
    @NotNull(message = "蓝牙地址不能为空")
    @Schema(name = "需要绑定的蓝牙地址")
    private String blueAddress;
}
