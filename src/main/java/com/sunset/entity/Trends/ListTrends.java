package com.sunset.entity.Trends;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ListTrends {
    @Schema(description = "总数")
    private Long total;
    private List<ObjTrends> list;
}
