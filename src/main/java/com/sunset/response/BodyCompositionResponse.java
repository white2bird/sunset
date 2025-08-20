package com.sunset.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BodyCompositionResponse {
    private BodyCompositionItem obesityDegree;          // 肥胖度
    private BodyCompositionItem bodyFat;               // 体脂率
    private BodyCompositionItem fatMass;               // 脂肪重量
    private BodyCompositionItem subcutaneousFat;       // 皮下脂肪
    private BodyCompositionItem fatFreeMass;          // 去脂重量
    private BodyCompositionItem musclePercentage;      // 肌肉率
    private BodyCompositionItem muscleMass;            // 肌肉重量
    private BodyCompositionItem skeletalMusclePercentage; // 骨骼肌率
    private BodyCompositionItem skeletalMuscle;        // 骨骼肌重量
    private BodyCompositionItem visceralFat;           // 内脏脂肪
    private BodyCompositionItem bodyWaterPercentage;   // 水分
    private BodyCompositionItem boneMass;              // 骨量
    private BodyCompositionItem proteinMass;           // 蛋白质
    private BodyCompositionItem metabolicAge;          // 身体年龄

    @Data
    public static class BodyCompositionItem {
        private Double currentValue;       // 当前值
        private String currentStatus;      // 当前状态
        private String currentColor;
        private String previousColor;
        private String previousStatus;     // 之前状态
        private Double previousValue;      // 之前值
        private BigDecimal change;             // 变化 (e.g., "1.9 ↓", "0.8 ↓", "无变化")
    }
}

