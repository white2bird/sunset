package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 身体年龄
 */
public class BodyAgeCal {

    public static String desc = "身体年龄是以基础代谢为基础计算出来的。而基础代谢是综合体重、身体脂肪率、肌肉率等多种指数后得出的。所以身体年龄是有个高于和低于实际年龄的综合判断身体状况的标准。\n";


    public static Map<String, Object> calLevel(Integer bodyAge, Integer age){
        return SplitUtil.levelInfo(returnStandList(bodyAge), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), desc, age.doubleValue());
    }

    public static List<Double> returnStandList(Integer age){
        return Arrays.asList(age.doubleValue()-5, age.doubleValue()+5);
    }
    public static List<String> returnStandListName(){
        return Arrays.asList("年轻", "偏大", "较大");
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "身体年龄低于实际年龄，反映良好健康状态。",
                "身体年龄与实际年龄相符。",
                "身体年龄比真实年龄偏大，需改善生活习惯。"

        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "保持当前运动习惯，尝试新运动模式（攀岩、舞蹈）维持兴趣。",
                "每周累计150分钟中高强度运动（心率≥120bpm）。",
                "每天行走6000步，保持有氧运动。"

        );
    }
    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "坚持地中海式饮食：丰富蔬果+鱼类+橄榄油+坚果。",
                "控制加工食品摄入（香肠、罐头），增加天然食物比例。",
                "维持营养均衡，蛋白质摄入适中（15-20%总热量）。"

        );
    }

}
