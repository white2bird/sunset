package com.sunset.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName(value = "body_composition")
@AllArgsConstructor
@NoArgsConstructor
public class BodyComposition {

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "weight_time")
    private Date weightTime;

    @TableField(value = "user_id")
    private String userId;
    private Double height;                    // 身高
    @TableField(value = "birth_date")
    private Date birthDate;                   // 出生日期
    private Integer gender;                   // 性别 (1 for male, 0 for female)
    @TableField(value = "waist_circumference")
    private Double waistCircumference;        // 腰围
    private Double weight;                    // 体重
    @TableField(value = "daily_caloric_needs")
    private Double dailyCaloricNeeds;         // 进食量 (Daily Caloric Needs)
    private Double bmr;                       // 基础代谢率 (BMR)
    private Double bmi;                       // BMI
    @TableField(value = "body_fat")
    private Double bodyFat;                   // 体脂 (Body Fat)
    @TableField(value = "visceral_fat")
    private Double visceralFat;               // 内脏脂肪 (Visceral Fat)
    private Double muscle;                    // 肌肉率
    @TableField(value = "skeletal_muscle")
    private Double skeletalMuscle;            // 骨骼肌 (Skeletal Muscle)
    @TableField(value = "skeletal_muscle_percentage")
    private Double skeletalMusclePercentage;  // 骨骼肌率\
    @TableField(value = "bone_mass")
    private Double boneMass;                  // 骨量 (Bone Mass)
    @TableField(value = "body_water_percentage")
    private Double bodyWaterPercentage;       // 水分（%）(Total Body Water)
    @TableField(value = "protein_mass")
    private Double proteinMass;               // 蛋白质 (Protein Mass)
    @TableField(value = "fat_free_mass")
    private Double fatFreeMass;               // 去脂体重 (Fat-Free Mass)
    @TableField(value = "muscle_mass")
    private Double muscleMass;                // 肌肉量 (Muscle Mass)
    @TableField(value = "fat_mass")
    private Double fatMass;                   // 脂肪量 (Fat Mass)

    @TableField(value = "subcutaneous_fat")
    private Double subcutaneousFat;           // 皮下脂肪率 (Subcutaneous Fat)
    @TableField(value = "weight_control")
    private Double weightControl;             // 体重控制量 (Weight Control)
    @TableField(value = "fat_control")
    private Double fatControl;                // 脂肪控制量 (Fat Control)
    @TableField(value = "muscle_control")
    private Double muscleControl;             // 肌肉控制量 (Muscle Control)
    @TableField(value = "obesity_degree")
    private Integer obesityDegree;             // 肥胖等级 (Obesity Degree)
    @TableField(value = "ideal_weight")
    private Double idealWeight;               // 标准体重 (Ideal Weight)
    @TableField(value = "metabolic_age")
    private Integer metabolicAge;              // 生理年龄 (Metabolic Age)
    @TableField(value = "obesity_degree_indicator")
    private Double obesityDegreeIndicator;    // 肥胖度 (Obesity Degree Indicator)
    @TableField(value = "fat_burning_heart_rate")
    private Double fatBurningHeartRate;     // 燃脂心率


}
