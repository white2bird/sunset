package com.sunset.entity;

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

    @TableField(value = "user_id")
    private String userId;

    @TableField(value = "measure_time")
    private Date measureTime;

    private BigDecimal weight;

    private BigDecimal bmi;

    @TableField(value = "body_fat_percentage")
    private BigDecimal bodyFatPercentage;

    @TableField(value = "visceral_fat")
    private BigDecimal visceralFat;

    @TableField(value = "muscle_mass")
    private BigDecimal muscleMass;

    @TableField(value = "bone_mass")
    private BigDecimal boneMass;

    @TableField(value = "water_percentage")
    private BigDecimal waterPercentage;

    private BigDecimal protein;

    @TableField(value = "subcutaneous_fat")
    private BigDecimal subcutaneousFat;

    private BigDecimal bmr;

    @TableField(value = "daily_calorie_needs")
    private BigDecimal dailyCalorieNeeds;

    @TableField(value = "ideal_weight")
    private BigDecimal idealWeight;

    @TableField(value = "weight_control")
    private BigDecimal weightControl;

    @TableField(value = "fat_control")
    private BigDecimal fatControl;

    @TableField(value = "metabolic_age")
    private BigDecimal metabolicAge;

    @TableField(value = "body_age")
    private BigDecimal bodyAge;

    @TableField(value = "physical_score")
    private BigDecimal physicalScore;

    @TableField(value = "obesity_degree")
    private BigDecimal obesityDegree;

    @TableField(value = "lean_body_mass")
    private BigDecimal leanBodyMass;

    @TableField(value = "body_type")
    private Integer bodyType;
}