package com.sunset.utils;

public class BodyCompositionCalculator {
    private final double height; // 身高(m)
    private final double weight; // 体重(kg)
    private final int age;       // 年龄
    private final Gender gender; // 性别
    private final double waist;  // 腰围(cm)

    public enum Gender {
        MALE, FEMALE
    }

    public BodyCompositionCalculator(double height, double weight, int age, Gender gender, double waist) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
        this.waist = waist;
    }

    // 计算基础代谢率(BMR)
    public double calculateBMR() {
        if (gender == Gender.MALE) {
            return 88.362 + (13.397 * weight) + (4.799 * height * 100) - (5.677 * age);
        } else {
            return 447.593 + (9.247 * weight) + (3.098 * height * 100) - (4.330 * age);
        }
    }

    // 计算BMI
    public double calculateBMI() {
        return weight / (height * height);
    }

    // 计算体脂率
    public double calculateBodyFatPercentage() {
        double bmi = calculateBMI();
        int genderFactor = (gender == Gender.MALE) ? 1 : 0;
        return 1.2 * bmi + 0.23 * age - 5.4 - 10.8 * genderFactor;
    }

    // 计算内脏脂肪等级
    public double calculateVisceralFat() {
        double bmi = calculateBMI();
        return 0.05 * waist + 0.12 * bmi - 0.03 * age - 1.5;
    }

    // 计算肌肉率
    public double calculateMusclePercentage() {
        double fatMass = calculateFatMass();
        double leanBodyMass = weight - fatMass;
        double muscleMass = leanBodyMass * 0.75;
        return (muscleMass / weight) * 100;
    }

    // 计算骨骼肌率
    public double calculateSkeletalMusclePercentage() {
        double fatMass = calculateFatMass();
        double leanBodyMass = weight - fatMass;
        double muscleMass = leanBodyMass * 0.75;
        double skeletalMuscleMass = muscleMass * 0.6;
        return (skeletalMuscleMass / weight) * 100;
    }

    // 计算骨量
    public double calculateBoneMass() {
        return weight * 0.042;
    }

    // 计算水分率
    public double calculateWaterPercentage() {
        double fatMass = calculateFatMass();
        double leanBodyMass = weight - fatMass;
        double waterMass = leanBodyMass * 0.7; // 去脂体重的70%为水分
        return (waterMass / weight) * 100;
    }

    // 计算蛋白质率
    public double calculateProteinPercentage() {
        double fatMass = calculateFatMass();
        double leanBodyMass = weight - fatMass;
        double proteinMass = leanBodyMass * 0.3;
        return (proteinMass / weight) * 100;
    }

    // 计算去脂体重
    public double calculateLeanBodyMass() {
        return weight - calculateFatMass();
    }

    // 计算肌肉量
    public double calculateMuscleMass() {
        return calculateLeanBodyMass() * 0.75;
    }

    // 计算含水量
    public double calculateWaterMass() {
        return weight * (calculateWaterPercentage() / 100);
    }

    // 计算脂肪量
    public double calculateFatMass() {
        return weight * (calculateBodyFatPercentage() / 100);
    }

    // 计算皮下脂肪率
    public double calculateSubcutaneousFatPercentage() {
        return calculateBodyFatPercentage() - calculateVisceralFat();
    }

    // 计算标准体重
    public double calculateStandardWeight() {
        return 22 * (height * height);
    }

    // 计算理想体重
    public double calculateIdealWeight() {
        if (gender == Gender.MALE) {
            return (height * 100) - 110;
        } else {
            return (height * 100) - 115;
        }
    }

    // 计算肥胖度(%)
    public double calculateObesityDegree() {
        double standardWeight = calculateStandardWeight();
        return ((weight - standardWeight) / standardWeight) * 100;
    }

    // 计算体重控制量
    public double calculateWeightControl() {
        return weight - calculateStandardWeight();
    }

    // 计算脂肪控制量
    public double calculateFatControl() {
        double idealBodyFatPercentage = (gender == Gender.MALE) ? 15 : 25;
        double idealFatMass = weight * (idealBodyFatPercentage / 100);
        return calculateFatMass() - idealFatMass;
    }

    // 计算肌肉控制量
    public double calculateMuscleControl() {
        double idealMusclePercentage = (gender == Gender.MALE) ? 40 : 30;
        double idealMuscleMass = weight * (idealMusclePercentage / 100);
        return calculateMuscleMass() - idealMuscleMass;
    }

    // 计算生理年龄
    public double calculatePhysiologicalAge() {
        double averageBMR = getAverageBMRForAge();
        double actualBMR = calculateBMR();
        return (actualBMR / averageBMR) * age;
    }

    // 获取同龄人平均BMR
    private double getAverageBMRForAge() {
        if (gender == Gender.MALE) {
            if (age >= 15 && age < 20) {
                return 1830 - 10 * (age - 15);
            } else if (age >= 20 && age < 25) {
                return 1770 - 8 * (age - 20);
            } else if (age >= 25 && age < 30) {
                return 1740 - 7 * (age - 25);
            } else if (age >= 30 && age < 35) {
                return 1710 - 6.5 * (age - 30);
            } else if (age >= 35 && age < 40) {
                return 1680 - 6 * (age - 35);
            } else if (age >= 40 && age < 45) {
                return 1650 - 5.5 * (age - 40);
            } else if (age >= 45 && age < 50) {
                return 1620 - 5 * (age - 45);
            } else if (age >= 50 && age < 55) {
                return 1590 - 4.5 * (age - 50);
            } else if (age >= 55 && age < 60) {
                return 1560 - 4 * (age - 55);
            } else if (age >= 60 && age < 65) {
                return 1530 - 3.5 * (age - 60);
            } else if (age >= 65 && age < 70) {
                return 1500 - 3 * (age - 65);
            } else { // 70岁以上
                return 1470 - 2 * (age - 70);
            }
        } else {
            if (age >= 15 && age < 20) {
                return 1520 - 8 * (age - 15);
            } else if (age >= 20 && age < 25) {
                return 1490 - 7 * (age - 20);
            } else if (age >= 25 && age < 30) {
                return 1470 - 6.5 * (age - 25);
            } else if (age >= 30 && age < 35) {
                return 1450 - 6 * (age - 30);
            } else if (age >= 35 && age < 40) {
                return 1430 - 5.5 * (age - 35);
            } else if (age >= 40 && age < 45) {
                return 1410 - 5 * (age - 40);
            } else if (age >= 45 && age < 50) {
                return 1390 - 4.5 * (age - 45);
            } else if (age >= 50 && age < 55) {
                return 1370 - 4 * (age - 50);
            } else if (age >= 55 && age < 60) {
                return 1350 - 3.5 * (age - 55);
            } else if (age >= 60 && age < 65) {
                return 1330 - 3 * (age - 60);
            } else if (age >= 65 && age < 70) {
                return 1310 - 2.5 * (age - 65);
            } else { // 70岁以上
                return 1290 - 2 * (age - 70);
            }
        }
    }

    public Integer getObesityLevelInteger() {
        double obesityDegree = calculateObesityDegree();
        if (obesityDegree < -20) {
            return 0; // 偏廋
        } else if (obesityDegree <= 10 && obesityDegree >= -10) {
            return 1; // 正常
        } else {
            return 2; // 肥胖
        }
    }


    // 获取肥胖等级描述
    public String getObesityLevel() {
        double obesityDegree = calculateObesityDegree();
        if (obesityDegree < -20) {
            return "偏瘦";
        } else if (obesityDegree <= 10 && obesityDegree >= -10) {
            return "正常";
        } else {
            return "肥胖";
        }
    }

    // 基于活动水平计算每日所需热量
    public double calculateDailyCalories(String activityLevel) {
        double bmr = calculateBMR();
        switch (activityLevel.toLowerCase()) {
            case "sedentary": // 久坐
                return bmr * 1.2;
            case "lightly active": // 轻度活动
                return bmr * 1.375;
            case "moderately active": // 中度活动
                return bmr * 1.55;
            case "very active": // 高强度活动
                return bmr * 1.725;
            default:
                throw new IllegalArgumentException("未知的活动水平: " + activityLevel);
        }
    }

    // 计算标准腰围
    public static double calculateStandardWaist(double height, Gender gender) {
        if (gender == Gender.MALE) {
            return (height * 100) / 2 - 10.5; // 取中间值
        } else {
            return (height * 100) / 2 - 13.5; // 取中间值
        }
    }

    // 主方法，用于测试
    public static void main(String[] args) {
        // 示例：39岁男性，身高1.75m，体重76.5kg，腰围90cm
        BodyCompositionCalculator calculator = new BodyCompositionCalculator(
            1.75, 76.5, 48, Gender.FEMALE, 90
        );
        Gender gender1 = Gender.valueOf("MALE");

        System.out.println(gender1);

        System.out.println("基础代谢率(BMR): " + calculator.calculateBMR() + " kcal/天");
        System.out.println("基础平均代谢率(BMR): " + calculator.getAverageBMRForAge() + " kcal/天");
        System.out.println("BMI: " + calculator.calculateBMI());
        System.out.println("体脂率: " + calculator.calculateBodyFatPercentage() + "%");
        System.out.println("内脏脂肪等级: " + calculator.calculateVisceralFat());
        System.out.println("肌肉率: " + calculator.calculateMusclePercentage() + "%");
        System.out.println("骨骼肌率: " + calculator.calculateSkeletalMusclePercentage() + "%");
        System.out.println("骨量: " + calculator.calculateBoneMass() + " kg");
        System.out.println("水分率: " + calculator.calculateWaterPercentage() + "%");
        System.out.println("蛋白质率: " + calculator.calculateProteinPercentage() + "%");
        System.out.println("去脂体重: " + calculator.calculateLeanBodyMass() + " kg");
        System.out.println("肌肉量: " + calculator.calculateMuscleMass() + " kg");
        System.out.println("含水量: " + calculator.calculateWaterMass() + " kg");
        System.out.println("脂肪量: " + calculator.calculateFatMass() + " kg");
        System.out.println("皮下脂肪率: " + calculator.calculateSubcutaneousFatPercentage() + "%");
        System.out.println("标准体重: " + calculator.calculateStandardWeight() + " kg");
        System.out.println("理想体重: " + calculator.calculateIdealWeight() + " kg");
        System.out.println("肥胖度: " + calculator.calculateObesityDegree() + "%");
        System.out.println("肥胖等级: " + calculator.getObesityLevel());
        System.out.println("体重控制量: " + calculator.calculateWeightControl() + " kg");
        System.out.println("脂肪控制量: " + calculator.calculateFatControl() + " kg");
        System.out.println("肌肉控制量: " + calculator.calculateMuscleControl() + " kg");
        System.out.println("生理年龄: " + calculator.calculatePhysiologicalAge() + " 岁");
        System.out.println("久坐所需热量: " + calculator.calculateDailyCalories("sedentary") + " kcal/天");
        System.out.println("轻度活动所需热量: " + calculator.calculateDailyCalories("lightly active") + " kcal/天");
        System.out.println("中度活动所需热量: " + calculator.calculateDailyCalories("moderately active") + " kcal/天");
        System.out.println("高强度活动所需热量: " + calculator.calculateDailyCalories("very active") + " kcal/天");
        System.out.println("标准腰围: " + calculateStandardWaist(1.75, Gender.MALE) + " cm");
    }
}    