package com.sunset.entity.Trends;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
public class ListComment {
    @Schema(description = "总数")
    private Long total;
    private List<CommTrends> list;
}
