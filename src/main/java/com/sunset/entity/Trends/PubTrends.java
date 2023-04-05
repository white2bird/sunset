package com.sunset.entity.Trends;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class PubTrends implements Serializable {
    @Schema(description = "动态")
    private String text;
    @Schema(description = "上传的多图")
    private String[] images;
}