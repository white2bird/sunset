package com.sunset.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoEntity {
    private String id;
    private String nickname;
    @Schema(description = "头像")
    private String avator="/avator/sunset202303311711.png";
    @Schema(description = "简介")
    private String description;
    private String birthday = "1998-5-04";
    private int sex;
    @Schema(description = "身高")
    private String height = "0";
    @Schema(description = "体重")
    private String weight = "0";
    @Schema(description = "腰围")
    private String waistline = "0";
    @Schema(description = "显示ID【个人信息】")
    private String showid;
    private String uid;
    @Schema(description = "星座")
    private String constellation = "金牛座";
    private String create_time;
    private String update_time;
    private int state; // 判断是全量更新信息 还是仅更新 个人简介
}
