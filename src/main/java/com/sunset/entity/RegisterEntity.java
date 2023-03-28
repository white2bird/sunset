package com.sunset.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RegisterEntity {
    public String uid;
    @Schema(description = "手机号")
    public String phone;
    @Schema(description = "密码")
    public String password;
    public String create_time;
    public String update_time;
    @Schema(description = "验证码")
    public String verCode;

}
