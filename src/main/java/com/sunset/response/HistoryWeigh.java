package com.sunset.response;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryWeigh {
    private Long id;
    private String weightTime;

    private String userId;
    private Double height;                    // 身高
    private Date birthDate;                   // 出生日期
    private Integer gender;                   // 性别 (1 for male, 0 for female)
    private Double waistCircumference;        // 腰围
    private Double weight;                    // 体重
    private Double dailyCaloricNeeds;         // 进食量 (Daily Caloric Needs)
    private Double bmr;                       // 基础代谢率 (BMR)
    private Double bmi;                       // BMI
    private Double bodyFat;                   // 体脂 (Body Fat)
    private Double visceralFat;               // 内脏脂肪 (Visceral Fat)
    private Double muscle;                    // 肌肉率
    private Double skeletalMuscle;            // 骨骼肌 (Skeletal Muscle)
    private Double skeletalMusclePercentage;  // 骨骼肌率
    private Double boneMass;                  // 骨量 (Bone Mass)
    private Double bodyWaterPercentage;       // 水分（%）(Total Body Water)
    private Double proteinMass;               // 蛋白质 (Protein Mass)
    private Double fatFreeMass;               // 去脂体重 (Fat-Free Mass)
    private Double muscleMass;                // 肌肉量 (Muscle Mass)
    private Double fatMass;                   // 脂肪量 (Fat Mass)
    private Double subcutaneousFat;           // 皮下脂肪率 (Subcutaneous Fat)
    private Double weightControl;             // 体重控制量 (Weight Control)
    private Double fatControl;                // 脂肪控制量 (Fat Control)
    private Double muscleControl;             // 肌肉控制量 (Muscle Control)
    private Integer obesityDegree;             // 肥胖等级 (Obesity Degree)
    private Double idealWeight;               // 标准体重 (Ideal Weight)
    private Integer metabolicAge;              // 生理年龄 (Metabolic Age)
    private Double obesityDegreeIndicator;    // 肥胖度 (Obesity Degree Indicator)
    private Double fatBurningHeartRate;     // 燃脂心率
    private String levelName;
    private String color;
}
