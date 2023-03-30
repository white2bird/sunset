package com.sunset.entity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterEntity {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public String uid;
    @Schema(description = "手机号")
    public String phone;
    @Schema(description = "密码")
    public String password;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public String create_time;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public String update_time;
    @Schema(description = "验证码")
    public String verCode;

}
