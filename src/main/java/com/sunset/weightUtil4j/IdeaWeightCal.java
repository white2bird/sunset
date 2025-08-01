package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 理想体重计算
 */
public class IdeaWeightCal {

    public static String desc = "理想体重是根据您当前的基础身体数据推导出的体重数值。处于标准范围内的最佳位置点，是平衡热量以及设定管理计划的一个重要参考指标。\n";
    public static List<Double> returnStandList(Double idleWeight){
        return Arrays.asList(idleWeight -5 , idleWeight +5);
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("不足", "标准", "优");
    }

    public static List<String> returnColorList(){
        return Arrays.asList(
                "11B6EB",
                "5BCC70",
                "F9AB3E"
        );
    }

    public static Map<String,?> calLevel(Double idealWeight, Double weight) {
        return SplitUtil.levelInfo(returnStandList(idealWeight), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(),returnColorList(), desc, weight);
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
               "当前体重低于理想值，可能损失肌肉量。",
                "体重处于理想范围，体成分比例最佳。",
               "体重超过理想范围，体脂可能超标。"

        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "侧重力量训练增肌（85%1RM负荷），限制有氧时长（<30分钟/次）。",
        "综合训练：力量维持肌肉+有氧促进心肺。",
        "增加有氧运动频率（每周5次），加入高强度间歇训练（HIIT）燃脂。"

        );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "热量盈余300-500kcal/日，蛋白质≥1.8g/kg体重，睡前补充酪蛋白。",
        "宏量营养素平衡：碳水50%、蛋白质20%、脂肪30%。",
        "创造热量缺口（每日300-500kcal），蛋白质占比25-30%防止肌肉流失。"

        );
    }
}
