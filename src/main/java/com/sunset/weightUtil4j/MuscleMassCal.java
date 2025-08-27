package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 9 肌肉重量
 */
public class MuscleMassCal {

    public static String desc = "肌肉量表示身体骨骼肌的绝对重量。其与肌糖原总量、肌力、关节稳定性、基础代谢率正相关。肌肉含量对健康有重要影响，保持一定量的肌肉，可以减少代谢相关的慢病患病率，降低运动损伤风险，维持良好的身体姿态，保证基础代谢率。肌肉含量较充足的人，衰老的速度也较慢。\n";

    public static Map<String,?> calLevel(Double muscle) {
        Map<String, Object> stringObjectMap = SplitUtil.levelInfo(returnStandList(), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), returnColorList(), desc, muscle);
        stringObjectMap.put("name", "肌肉重量");
        stringObjectMap.put("unit", "公斤");
        return stringObjectMap;
    }

    public static List<String> returnColorList(){
        return Arrays.asList(
                "11B6EB",
                "5BCC70",
                "F9AB3E"
        );
    }

    public static List<Double> returnStandList(){
        return Arrays.asList(26.8d, 34.4d);
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("不足", "标准", "优");
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "肌肉重量不足导致代谢下降",
                "肌肉重量健康",
                "肌肉重量优秀"

        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "渐进超负荷训练：每周3次全身力量",
                "力量与有氧交替安排",
                "可增加训练强度"

        );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "蛋白质≥1.8g/kg体重",
                "蛋白质1.2-1.6g/kg体重",
                "选择低脂蛋白来源（虾仁）"
        );
    }


}
