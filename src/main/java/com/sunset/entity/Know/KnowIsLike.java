package com.sunset.entity.Know;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class KnowIsLike  extends KnowEntity{
    @Schema(description = "是否收藏")
    private  boolean islike = false;
}
