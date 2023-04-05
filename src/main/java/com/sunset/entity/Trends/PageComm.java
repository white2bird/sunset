package com.sunset.entity.Trends;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PageComm extends PageRends{
    @Schema(description = "用户id")
    private String trends_id;
}
