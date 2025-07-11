package com.sunset.utils;

public class BodyCalculator {
    private Gender gender;

    private Integer age;

    private Double weight;

    private Double height;

    private Double waist;

    public  BodyCalculator(String genderStr, Integer age, Double weight, Double height, Double waist){
        this.gender = Gender.valueOf(genderStr);
        this.age =  age;
        this.weight = weight;
        this.height = height;
        this.waist =  waist;
    }

    public Double fatBurningHeartRate() {
        return 220D-age;
    }


    public enum Gender {
        MALE, FEMALE
    }
    public double calculateDailyCalories(String activityLevel) {
        double bmr = calculateActualBMR();
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

    // 计算基础代谢率(BMR)
    public double calculateActualBMR() {
        if (gender == Gender.MALE) {
            return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            return 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
    }

    public double calculateBMI() {
        return weight / (height * height / 10000);
    }

    // 计算体脂率
    public double calculateBodyFatPercentage() {
        double bmi = calculateBMI();
        int genderFactor = (gender == Gender.MALE) ? 1 : 0;
        return (1.2 * bmi + 0.23 * age - 5.4 - 10.8 * genderFactor)/100;
    }


    // 计算内脏脂肪等级
    public double calculateVisceralFat() {
        double bmi = calculateBMI();
        return 0.05 * waist + 0.12 * bmi - 0.03 * age - 1.5;
    }

    /**
     * 肌肉率
     * @return
     */
    public Double muscleMassPercent(){
        return muscleMass() / weight;
    }

    /**
     * 骨骼肌
     */
    public Double skeletalMuscle(){
        return muscleMass() * 0.6;
    }
    /**
     * 骨骼肌率
     */
    public Double skeletalMusclePercent(){
        return skeletalMuscle() / weight;
    }

    public Double boneMass(){
        return weight * 0.042;
    }



    /**
     * 蛋白质
     * @return
     */
    public double ProteinMass(){
        return fatFreeMass() * 0.3;
    }

    /**
     * 肌肉量
     * @return
     */
    public double muscleMass(){
        return fatFreeMass() * 0.75;
    }

    /**
     * 去脂体重
     * @return
     */
    public double fatFreeMass(){
        double v = calFatMass();
        return weight - v;
    }

    /**
     * 含水量
     * @return
     */
    public double waterMass(){
        return waterPercent() * weight;
    }

    /**
     * 脂肪量
     * @return
     */
    public double calFatMass(){
        double v = calculateBodyFatPercentage();
        return v * weight;
    }

    public double subcutaneousFatPercent(){
        return calculateBodyFatPercentage() - calculateVisceralFat()/100;
    }



    public double waterPercent() {
        // Given constants
        double a = 0.15;
        double b = 0.2;
        double c = -0.1;
        double d = 2.5;
        double constant = 45;

        // Input data
        double height = 170;  // in cm
        double weight = 65;   // in kg
        int age = 30;         // age in years
        int gender = 1;       // 1 for male, 0 for female
        double impedance = 500;  // in Ohms

        // Calculate the body water percentage
        double bodyWaterPercentage = calculateBodyWaterPercentage(a, b, c, d, constant, height, weight, age, gender, impedance);

        // Output the result
        return bodyWaterPercentage/100;
    }

    public double calculateBodyWaterPercentage(double a, double b, double c, double d, double constant,
                                                      double height, double weight, int age, int gender, double impedance) {
        // Calculate each term in the formula
        double term1 = a * (Math.pow(height, 2) / impedance);
        double term2 = b * weight;
        double term3 = c * age;
        double term4 = d * gender;

        // Calculate total water percentage
        return term1 + term2 + term3 + term4 + constant;
    }

    /**
     * 肥胖等级
     * @return
     */
    public Integer getFatLevel(){
        double obesityDegree = fatPercent() * 100;
        if (obesityDegree < -20) {
            return 0; // 偏廋
        } else if (obesityDegree <= 10 && obesityDegree >= -20) {
            return 1; // 正常
        } else {
            return 2; // 肥胖
        }
    }

    /**
     * 体重控制量
     * @return
     */
    public double weighControl(){
        return weight - calculateIdealWeight();
    }

    /**
     * 脂肪控制量
     * //
     * 体重       脂肪
     * 理想体重   理想脂肪  == 》 理想脂肪 = 理想体重 / 体重 * 脂肪
     * @return
     */
    public double fatControl(){
        double fat = calFatMass();
        double idealFat = calculateIdealWeight()/weight * fat;
        return fat - idealFat;
    }

    /**
     * 肌肉控制量
     * @return
     */
    public double muscleControl(){
        double muscle = muscleMass();
        double idealMuscle = calculateIdealWeight()/weight * muscle;
        return muscle - idealMuscle;
    }

    // 计算标准体重
    public double calculateStandardWeight() {
        return 22 * (height * height/10000);
    }


    // 计算理想体重
    public double calculateIdealWeight() {
        if (gender == Gender.MALE) {
            return height  - 110;
        } else {
            return height - 115;
        }
    }

    /**
     * 生理年龄
     * @return
     */
    public int metabolicAge(){
        return (int)(calculateActualBMR() / getAverageBMRForAge() * age);
    }

    /**
     * 肥胖度
     */
    public double fatPercent(){
        return (weight - calculateStandardWeight()) /calculateStandardWeight();
    }



    public double getAverageBMRForAge() {
        if (gender == Gender.MALE) {
            if ( age < 20) {
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
            if ( age < 20) {
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
}
