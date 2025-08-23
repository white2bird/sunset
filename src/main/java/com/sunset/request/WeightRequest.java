package com.sunset.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "称重")
public class WeightRequest {
    private Long id;

    @Schema(description = "体重")
    private BigDecimal weight;

    @Schema(description = "蓝牙地址")
    @NotNull(message = "蓝牙地址不能为空")
    private String blueAddress;

    public WeightRequest(BigDecimal weight, String blueAddress) {
        this.weight = weight;
        this.blueAddress = blueAddress;
    }
}
