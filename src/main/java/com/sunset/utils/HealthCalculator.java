package com.sunset.utils;

import java.util.HashMap;
import java.util.Map;

public class HealthCalculator {
    // 用户基本信息
    private double weight;      // 体重(kg)
    private double height;      // 身高(cm)
    private int age;            // 年龄
    private String gender;      // 性别("male"或"female")
    private double impedance;   // 生物电阻抗(如果有)

    // 24项健康数据
    private Map<String, Double> healthData = new HashMap<>();

    public HealthCalculator(double weight, double height, int age, String gender) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        calculateAll();
    }

    public HealthCalculator(double weight, double height, int age, String gender, double impedance) {
        this(weight, height, age, gender);
        this.impedance = impedance;
        calculateAllWithImpedance();
    }

    // 计算所有健康指标(无阻抗数据)
    private void calculateAll() {
        // 1. 基础指标
        healthData.put("weight", weight);
        healthData.put("bmi", calculateBMI());

        // 2. 理想体重相关
        healthData.put("ideal_weight", calculateIdealWeight());
        healthData.put("weight_control", calculateWeightControl());
        healthData.put("fat_control", calculateFatControl());

        // 3. 其他估算指标
        healthData.put("body_fat_percentage", estimateBodyFatPercentage());
        healthData.put("visceral_fat", estimateVisceralFat());
        healthData.put("muscle_mass", estimateMuscleMass());
        healthData.put("bone_mass", estimateBoneMass());
        healthData.put("water_percentage", estimateWaterPercentage());
        healthData.put("bmr", calculateBMR());
        healthData.put("daily_calorie_needs", calculateDailyCalorieNeeds());

        // 4. 体型评估
        healthData.put("body_type", assessBodyType());
    }

    // 计算所有健康指标(有阻抗数据)
    private void calculateAllWithImpedance() {
        calculateAll(); // 先计算基础指标

        // 使用阻抗数据更精确计算
        healthData.put("body_fat_percentage", calculateBodyFatPercentageWithImpedance());
        healthData.put("muscle_mass", calculateMuscleMassWithImpedance());
        healthData.put("bone_mass", calculateBoneMassWithImpedance());
        healthData.put("water_percentage", calculateWaterPercentageWithImpedance());
        healthData.put("protein", calculateProtein());
        healthData.put("subcutaneous_fat", calculateSubcutaneousFat());
        healthData.put("metabolic_age", calculateMetabolicAge());
        healthData.put("body_age", calculateBodyAge());
        healthData.put("physical_score", calculatePhysicalScore());
        healthData.put("obesity_degree", calculateObesityDegree());
        healthData.put("lean_body_mass", calculateLeanBodyMass());
    }

    // ========== 计算方法 ==========

    // 计算BMI
    private double calculateBMI() {
        double heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }

    // 计算理想体重(简单版)
    private double calculateIdealWeight() {
        double heightInMeters = height / 100;
        return 22 * heightInMeters * heightInMeters; // BMI=22为标准
    }

    // 体重控制量
    private double calculateWeightControl() {
        return weight - calculateIdealWeight();
    }

    // 脂肪控制量
    private double calculateFatControl() {
        double idealFat = calculateIdealWeight() * 0.15; // 假设理想脂肪占比15%
        double currentFat = weight * (healthData.get("body_fat_percentage") / 100);
        return currentFat - idealFat;
    }

    // 估算体脂率(无阻抗)
    private double estimateBodyFatPercentage() {
        // 使用基于年龄和性别的公式估算
        double bmi = calculateBMI();
        if (gender.equals("male")) {
            return (1.20 * bmi) + (0.23 * age) - 16.2;
        } else {
            return (1.20 * bmi) + (0.23 * age) - 5.4;
        }
    }

    // 计算体脂率(有阻抗)
    private double calculateBodyFatPercentageWithImpedance() {
        // 使用生物电阻抗法计算
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

    // 估算内脏脂肪
    private double estimateVisceralFat() {
        double bmi = calculateBMI();
        double waistCircumference = 0.5 * height; // 简单估算腰围
        if (gender.equals("male")) {
            return (waistCircumference * 0.74) - (weight * 0.082 + 34.89);
        } else {
            return (waistCircumference * 0.74) - (weight * 0.082 + 22.35);
        }
    }

    // 估算肌肉量
    private double estimateMuscleMass() {
        // 肌肉量估算公式
        return weight * (1 - (healthData.get("body_fat_percentage") / 100) - 0.15); // 减去脂肪和骨骼重量
    }

    // 计算肌肉量(有阻抗)
    private double calculateMuscleMassWithImpedance() {
        // 使用阻抗数据更精确计算
        return weight * (1 - (healthData.get("body_fat_percentage") / 100)) * 0.85;
    }

    // 估算骨骼重量
    private double estimateBoneMass() {
        // 骨骼重量约占体重的3.5%-6%
        return weight * 0.05; // 取中间值
    }

    // 计算骨骼重量(有阻抗)
    private double calculateBoneMassWithImpedance() {
        // 使用阻抗数据更精确计算
        return weight * 0.0425 + impedance * 0.0012;
    }

    // 估算水分率
    private double estimateWaterPercentage() {
        // 水分约占体重的50%-65%
        double base = gender.equals("male") ? 60 : 55;
        double bmi = calculateBMI();
        if (bmi > 25) {
            return base - (bmi - 25);
        } else if (bmi < 18.5) {
            return base + (18.5 - bmi);
        }
        return base;
    }

    // 计算水分率(有阻抗)
    private double calculateWaterPercentageWithImpedance() {
        // 使用阻抗数据计算
        return (0.396 * height / impedance) + (0.143 * weight) + 8.399;
    }

    // 计算基础代谢率(BMR)
    private double calculateBMR() {
        // 使用Mifflin-St Jeor公式
        if (gender.equals("male")) {
            return 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            return 10 * weight + 6.25 * height - 5 * age - 161;
        }
    }

    // 计算每日所需卡路里
    private double calculateDailyCalorieNeeds() {
        // 基于活动水平的估算(这里使用中等活动水平1.55)
        return calculateBMR() * 1.55;
    }

    // 计算蛋白质含量(有阻抗)
    private double calculateProtein() {
        return weight * 0.15; // 假设蛋白质占体重15%
    }

    // 计算皮下脂肪
    private double calculateSubcutaneousFat() {
        return healthData.get("body_fat_percentage") * 0.8; // 假设80%脂肪为皮下脂肪
    }

    // 计算代谢年龄
    private double calculateMetabolicAge() {
        double bmrRatio = calculateBMR() / getAverageBMRForAge();
        return age * bmrRatio;
    }

    // 获取同龄人平均BMR
    private double getAverageBMRForAge() {
        // 简化处理，实际应根据统计数据
        if (gender.equals("male")) {
            return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            return 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
    }

    // 计算身体年龄
    private double calculateBodyAge() {
        // 基于BMI、体脂率等综合计算
        double bodyAge = age;
        double bmi = calculateBMI();

        if (bmi > 25) {
            bodyAge += (bmi - 25) * 1.5;
        } else if (bmi < 18.5) {
            bodyAge += (18.5 - bmi) * 0.8;
        }

        double fatPercentage = healthData.get("body_fat_percentage");
        if (gender.equals("male")) {
            if (fatPercentage > 20) bodyAge += (fatPercentage - 20) * 0.3;
        } else {
            if (fatPercentage > 25) bodyAge += (fatPercentage - 25) * 0.3;
        }

        return bodyAge;
    }

    // 计算身体得分
    private double calculatePhysicalScore() {
        double score = 100;

        // BMI扣分
        double bmi = calculateBMI();
        if (bmi < 18.5 || bmi > 25) {
            score -= Math.abs(bmi - 21.7) * 2; // 21.7是理想BMI中间值
        }

        // 体脂率扣分
        double fatPercentage = healthData.get("body_fat_percentage");
        if (gender.equals("male")) {
            if (fatPercentage < 8 || fatPercentage > 20) {
                score -= Math.abs(fatPercentage - 14) * 1.5; // 14是男性理想体脂中间值
            }
        } else {
            if (fatPercentage < 21 || fatPercentage > 33) {
                score -= Math.abs(fatPercentage - 27) * 1.5; // 27是女性理想体脂中间值
            }
        }

        // 水分扣分
        double water = healthData.get("water_percentage");
        if (water < 50 || water > 65) {
            score -= Math.abs(water - 57.5) * 0.8;
        }

        return Math.max(0, score);
    }

    // 计算肥胖程度
    private double calculateObesityDegree() {
        double standardWeight = calculateIdealWeight();
        return (weight / standardWeight) * 100;
    }

    // 计算瘦体重
    private double calculateLeanBodyMass() {
        return weight * (1 - healthData.get("body_fat_percentage") / 100);
    }

    // 评估体型
    private double assessBodyType() {
        double bmi = calculateBMI();
        double waistToHeightRatio = 0.5; // 简化处理，实际应测量腰围

        if (bmi < 18.5) {
            return 1; // 偏瘦型
        } else if (bmi >= 18.5 && bmi < 24) {
            if (waistToHeightRatio < 0.5) {
                return 2; // 标准型
            } else {
                return 3; // 隐形肥胖型
            }
        } else {
            return 4; // 肥胖型
        }
    }

    // 获取所有健康数据
    public Map<String, Double> getHealthData() {
        return healthData;
    }

    // 打印所有健康数据
    public void printHealthData() {
        System.out.println("========== 健康数据报告 ==========");
        System.out.printf("体重: %.1f kg\n", healthData.get("weight"));
        System.out.printf("BMI: %.1f\n", healthData.get("bmi"));
        System.out.printf("体脂率: %.1f%%\n", healthData.get("body_fat_percentage"));
        System.out.printf("内脏脂肪等级: %.1f\n", healthData.get("visceral_fat"));
        System.out.printf("肌肉量: %.1f kg\n", healthData.get("muscle_mass"));
        System.out.printf("骨量: %.1f kg\n", healthData.get("bone_mass"));
        System.out.printf("水分率: %.1f%%\n", healthData.get("water_percentage"));
        System.out.printf("蛋白质: %.1f kg\n", healthData.get("protein"));
        System.out.printf("皮下脂肪率: %.1f%%\n", healthData.get("subcutaneous_fat"));
        System.out.printf("基础代谢率: %.1f kcal\n", healthData.get("bmr"));
        System.out.printf("每日所需热量: %.1f kcal\n", healthData.get("daily_calorie_needs"));
        System.out.printf("理想体重: %.1f kg\n", healthData.get("ideal_weight"));
        System.out.printf("体重控制量: %.1f kg\n", healthData.get("weight_control"));
        System.out.printf("脂肪控制量: %.1f kg\n", healthData.get("fat_control"));
        System.out.printf("代谢年龄: %.1f 岁\n", healthData.get("metabolic_age"));
        System.out.printf("身体年龄: %.1f 岁\n", healthData.get("body_age"));
        System.out.printf("身体得分: %.1f/100\n", healthData.get("physical_score"));
        System.out.printf("肥胖程度: %.1f%%\n", healthData.get("obesity_degree"));
        System.out.printf("瘦体重: %.1f kg\n", healthData.get("lean_body_mass"));
        System.out.printf("体型评估: %.0f (1-偏瘦,2-标准,3-隐形肥胖,4-肥胖)\n", healthData.get("body_type"));
    }
}
