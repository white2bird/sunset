package com.sunset.entity.weight;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_weight")
public class WeightEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private BigDecimal weight;

    private String uid;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField("blue_address")
    private String blueAddress;
}