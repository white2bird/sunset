package com.sunset.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserInfoEntity {
    private String id;
    private String nickname;
    @Schema(description = "头像")
    private String avator;
    @Schema(description = "简介")
    private String description;
    private String birthday;
    private int sex;
    @Schema(description = "身高")
    private String height;
    @Schema(description = "体重")
    private String weight;
    @Schema(description = "腰围")
    private String waistline;
    @Schema(description = "显示ID【个人信息】")
    private String showid;
    private String uid;
    private String create_time;
    private String update_time;
}
