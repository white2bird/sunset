package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 7 骨骼肌重量
 */
public class SkeletalMuscleCal {

    public static String desc = "骨骼肌是分布于躯干、四肢的随意肌。运动系统的肌肉属于横纹肌，由于绝大部分附着于骨头，故又名骨骼肌。增加肌肉后基础代谢也会增强，因此安静的时候能 量消耗也增加。有足够的肌肉才能有良好的血液循环，消耗能量，减少脂肪，预防成人病。\n";

    public static Map<String, Object> calLevel(Double skeletalMuscle, double weight){
        List<Double> list = returnStandList(weight);
        List<String> nameList = returnStandListName();
        Map<String, Object> stringObjectMap = SplitUtil.levelInfo(list, nameList, returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), returnColorList(), desc, skeletalMuscle);
        stringObjectMap.put("name", "骨骼肌重量");
        stringObjectMap.put("unit", "公斤");
        return stringObjectMap;
    }

    public static List<Double> returnStandList(Double weight){
        List<Double> list = Arrays.asList(25d, 30d);
        for(int i = 0; i < list.size(); i++){
            double v = list.get(i) * weight * 0.01;
            BigDecimal bigDecimal = new BigDecimal(v);
            bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
            list.set(i, bigDecimal.doubleValue());
        }
        return list;
    }
    public static List<String> returnColorList(){
        return Arrays.asList(
                "11B6EB",
                "5BCC70",
                "F9AB3E"
        );
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("不足", "标准", "优");
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "骨骼肌重量不足降低代谢率",
                "骨骼肌重量达标",
                "骨骼肌重量优秀"

        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "复合力量训练为主（深蹲/卧推）",
                "力量训练（每周3次）结合有氧",
                "可进行高强度力量训练"
        );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "蛋白质≥1.8g/kg体重",
                "蛋白质1.2-1.6g/kg体重",
                "运动后补充快吸收碳水+蛋白质"

        );
    }
}
