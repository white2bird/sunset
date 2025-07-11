package com.sunset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 健康数据实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("health_data")
public class HealthData2 {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String userId;

    private Date measureTime;

    private BigDecimal weight;

    private BigDecimal bmi;

    private BigDecimal bodyFatPercentage;

    private BigDecimal visceralFat;

    private BigDecimal muscleMass;

    private BigDecimal boneMass;

    private BigDecimal waterPercentage;

    private BigDecimal protein;

    private BigDecimal subcutaneousFat;

    private BigDecimal bmr;

    private BigDecimal dailyCalorieNeeds;

    private BigDecimal idealWeight;

    private BigDecimal weightControl;

    private BigDecimal fatControl;

    private BigDecimal metabolicAge;

    private BigDecimal bodyAge;

    private BigDecimal physicalScore;

    private BigDecimal obesityDegree;

    private BigDecimal leanBodyMass;

    private Integer bodyType;
}