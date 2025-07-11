package com.sunset.entity.blue;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_blue")
public class BlueEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "blue_address")
    private String blueAddress;
    private String uid;
}
