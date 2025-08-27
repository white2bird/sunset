package com.sunset.weightUtil4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyCaloricNeedsCal {

    public static String desc = "进食量是指当日的睡前体重与早上空腹体重之差。其反映了晚上身体内的水分滞留量与存食量，一般为正值。持续监测体重变化量，对比体重变化曲线，掌握自身体重变化规律，据此养成膳食回顾的习惯，可以指导调整每日饮食，有利于实现体重自我管理。维持合理的体重，能够降低糖尿病、高血压、高脂血症等多种慢性病患病风险，有益健康。";

    public static List<Double> returnStandList(Double v){
        return Arrays.asList(v-1,v+1);
    }

    public static List<String> returnColorList(){
        return Arrays.asList(
                "11B6EB",
                "5BCC70",
                "F9AB3E"
        );
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("", "");
    }

    public static Map<String,?> calLevel(Double value){

        Map<String, Object> result = new HashMap<>();
        BigDecimal bmr = new BigDecimal(value);
        bmr = bmr.setScale(2, RoundingMode.HALF_UP);
        result.put("value", bmr);
        result.put("standDataList",  null);
        result.put("standDataNameList", null);
        result.put("level", 1);
        result.put("levelName", "正常");
        result.put("analyze", null);
        result.put("sportAdvice", null);
        result.put("eatAdvice", null);
        result.put("desc", desc);
        result.put("name", "进食量");
        result.put("unit", "kcal");
        result.put("color", returnColorList().get(0));
        result.put("colorList", returnColorList());
        return result;
    }

}
