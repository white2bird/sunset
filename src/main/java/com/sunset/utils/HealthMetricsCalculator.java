package com.sunset.utils;

import org.apache.ibatis.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 健康指标计算器（支持24项健康指标计算）
 * 包含有/无生物电阻抗数据的两种计算模式
 */
public class HealthMetricsCalculator {
    // 基础数据
    private double weight;      // 体重(kg)
    private double height;      // 身高(cm)
    private int age;            // 年龄
    private String gender;      // 性别("male"/"female")
    private Double impedance;   // 生物电阻抗(Ω)
    private String activityLevel; // 活动水平

    // 活动水平系数
    private static final Map<String, Double> ACTIVITY_FACTORS = new HashMap<String, Double>(){{
        put("sedentary", 1.2);
        put("light", 1.375);
        put("moderate", 1.55);
        put("high", 1.725);
    }};







    // 健康数据存储
    private Map<String, Double> metrics = new HashMap<>();

    // 构造方法（无阻抗数据）
    public HealthMetricsCalculator(double weight, double height, int age,
                                   String gender, String activityLevel) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        this.activityLevel = activityLevel;
        calculateAll();
    }

    // 构造方法（有阻抗数据）
    public HealthMetricsCalculator(double weight, double height, int age,
                                   String gender, String activityLevel,
                                   double impedance) {
        this(weight, height, age, gender, activityLevel);
        this.impedance = impedance;
        calculateAllWithImpedance();
    }

    // 计算所有基础指标
    private void calculateAll() {
        // 1. 基础指标
        metrics.put("weight", weight);
        metrics.put("bmi", calculateBMI());
        metrics.put("bmr", calculateBMR());
        metrics.put("daily_caloric_needs", calculateDailyCaloricNeeds());

        // 2. 体重相关
        metrics.put("ideal_weight", calculateIdealWeight());
        metrics.put("standard_weight", calculateStandardWeight());
        metrics.put("weight_control", calculateWeightControl());

        // 3. 估算指标
        metrics.put("body_fat_percentage", estimateBodyFatPercentage());
        metrics.put("fat_mass", calculateFatMass());
        metrics.put("visceral_fat", estimateVisceralFat());
        metrics.put("fat_free_mass", calculateFatFreeMass());
        metrics.put("muscle_mass", estimateMuscleMass());
        metrics.put("skeletal_muscle", calculateSkeletalMuscle());
        metrics.put("bone_mass", estimateBoneMass());
        metrics.put("water_percentage", estimateWaterPercentage());
        metrics.put("protein_mass", estimateProteinMass());
        metrics.put("subcutaneous_fat", estimateSubcutaneousFat());

        // 4. 控制量和评估
        metrics.put("fat_control", calculateFatControl());
        metrics.put("muscle_control", calculateMuscleControl());
        metrics.put("obesity_degree", calculateObesityDegree());
        metrics.put("metabolic_age", calculateMetabolicAge());
    }

    // 使用阻抗数据计算更精确的指标
    private void calculateAllWithImpedance() {
        calculateAll(); // 先计算基础指标

        // 使用阻抗数据重新计算更精确的指标
        metrics.put("body_fat_percentage", calculateBodyFatWithImpedance());
        metrics.put("fat_mass", calculateFatMass());
        metrics.put("visceral_fat", calculateVisceralFatWithImpedance());
        metrics.put("fat_free_mass", calculateFatFreeMass());
        metrics.put("muscle_mass", calculateMuscleMassWithImpedance());
        metrics.put("skeletal_muscle", calculateSkeletalMuscle());
        metrics.put("bone_mass", calculateBoneMassWithImpedance());
        metrics.put("water_percentage", calculateWaterPercentageWithImpedance());
        metrics.put("protein_mass", calculateProteinMassWithImpedance());
        metrics.put("subcutaneous_fat", calculateSubcutaneousFatWithImpedance());
    }

    // ========== 计算方法实现 ==========

    // 1. BMI计算
    private double calculateBMI() {
        double heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }

    // 2. 基础代谢率(BMR) - Harris-Benedict公式
    private double calculateBMR() {
        if (gender.equals("male")) {
            return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            return 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
    }

    // 3. 每日所需卡路里
    private double calculateDailyCaloricNeeds() {
        Double factor = ACTIVITY_FACTORS.get(activityLevel.toLowerCase());
        if (factor == null) factor = 1.55; // 默认中等活动
        return metrics.get("bmr") * factor;
    }

    // 4. 标准体重(BMI=22)
    private double calculateStandardWeight() {
        double heightInMeters = height / 100;
        return 22 * heightInMeters * heightInMeters;
    }

    // 5. 理想体重(身高-105/110)
    private double calculateIdealWeight() {
        return gender.equals("male") ? height - 105 : height - 110;
    }

    // 6. 体重控制量
    private double calculateWeightControl() {
        return weight - metrics.get("ideal_weight");
    }

    // 7. 估算体脂率(无阻抗)
    private double estimateBodyFatPercentage() {
        double bmi = metrics.get("bmi");
        if (gender.equals("male")) {
            return (1.20 * bmi) + (0.23 * age) - 16.2;
        } else {
            return (1.20 * bmi) + (0.23 * age) - 5.4;
        }
    }

    // 8. 计算体脂率(有阻抗)
    private double calculateBodyFatWithImpedance() {
        // 简化的BIA公式，实际设备会有更复杂的算法
        double lbm = (height * 9.058 / 100) * (height / 100);
        lbm += weight * 0.32 + 12.226;
        lbm -= impedance * 0.0068;
        lbm -= age * 0.0542;

        if (gender.equals("female")) {
            lbm += 2.898;
        }

        double fatMass = weight - lbm;
        return (fatMass / weight) * 100;
    }

    // 9. 脂肪量
    private double calculateFatMass() {
        return weight * (metrics.get("body_fat_percentage") / 100);
    }

    // 10. 估算内脏脂肪(无阻抗)
    private double estimateVisceralFat() {
        double waistCircumference = height * 0.45; // 估算腰围
        return 0.05 * waistCircumference + 0.12 * metrics.get("bmi") - 0.03 * age - 1.5;
    }

    // 11. 计算内脏脂肪(有阻抗)
    private double calculateVisceralFatWithImpedance() {
        // 使用阻抗数据优化计算
        return estimateVisceralFat() * (1 + (impedance - 500) * 0.0002);
    }

    // 12. 去脂体重
    private double calculateFatFreeMass() {
        return weight - metrics.get("fat_mass");
    }

    // 13. 估算肌肉量(无阻抗)
    private double estimateMuscleMass() {
        return metrics.get("fat_free_mass") * 0.75;
    }

    // 14. 计算肌肉量(有阻抗)
    private double calculateMuscleMassWithImpedance() {
        // 简化的BIA肌肉量计算
        return metrics.get("fat_free_mass") * (0.75 + (impedance - 500) * 0.0001);
    }

    // 15. 骨骼肌
    private double calculateSkeletalMuscle() {
        return metrics.get("muscle_mass") * 0.6;
    }

    // 16. 估算骨量(无阻抗)
    private double estimateBoneMass() {
        return weight * 0.015;
    }

    // 17. 计算骨量(有阻抗)
    private double calculateBoneMassWithImpedance() {
        // 阻抗越大，骨量通常越小
        return weight * (0.015 - (impedance - 500) * 0.00001);
    }

    // 18. 估算水分率(无阻抗)
    private double estimateWaterPercentage() {
        return gender.equals("male") ? weight * 0.6 : weight * 0.55;
    }

    // 19. 计算水分率(有阻抗)
    private double calculateWaterPercentageWithImpedance() {
        // 简化的BIA水分计算
        return (0.396 * height / impedance) + (0.143 * weight) + 8.399;
    }

    // 20. 估算蛋白质(无阻抗)
    private double estimateProteinMass() {
        return metrics.get("fat_free_mass") * 0.18;
    }

    // 21. 计算蛋白质(有阻抗)
    private double calculateProteinMassWithImpedance() {
        return metrics.get("fat_free_mass") * (0.18 + (impedance - 500) * 0.00005);
    }

    // 22. 估算皮下脂肪(无阻抗)
    private double estimateSubcutaneousFat() {
        return metrics.get("body_fat_percentage") * 0.8;
    }

    // 23. 计算皮下脂肪(有阻抗)
    private double calculateSubcutaneousFatWithImpedance() {
        return metrics.get("body_fat_percentage") - metrics.get("visceral_fat");
    }

    // 24. 脂肪控制量
    private double calculateFatControl() {
        double idealFat = metrics.get("ideal_weight") * 0.15; // 理想脂肪占比15%
        return metrics.get("fat_mass") - idealFat;
    }

    // 25. 肌肉控制量
    private double calculateMuscleControl() {
        double idealMuscle = metrics.get("ideal_weight") * 0.4; // 理想肌肉占比40%
        return metrics.get("muscle_mass") - idealMuscle;
    }

    // 26. 肥胖度
    private double calculateObesityDegree() {
        return ((weight - metrics.get("standard_weight")) / metrics.get("standard_weight")) * 100;
    }

    // 27. 代谢年龄
    private double calculateMetabolicAge() {
        double avgBMR = gender.equals("male")
                ? 88.362 + (13.397 * 70) + (4.799 * 170) - (5.677 * age) // 以70kg,170cm为标准
                : 447.593 + (9.247 * 60) + (3.098 * 160) - (4.330 * age); // 以60kg,160cm为标准

        return (metrics.get("bmr") / avgBMR) * age;
    }

    // 获取所有指标
    public Map<String, Double> getMetrics() {
        return new HashMap<>(metrics);
    }

    // 获取单个指标
    public Double getMetric(String key) {
        return metrics.get(key);
    }
}