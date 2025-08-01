package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 10.肌肉率
 *   肌肉/体重
 */
public class MusclePercentCal {

    public static String desc = "肌肉包含两种成分，第一种是骨骼肌，就是使骨头活动的随意肌，第二种是平滑肌，就是为血管、胃、消化器官以及其他内脏充当衬里的不随意肌。人体体重的成分可分为非脂肪物质与脂肪物质二大部分，肌肉含量是非脂肪物质中去除掉约占体重4%~6%的无机质。肌肉是好东西，肌肉量越大，自己基础代谢就越高，越不容易胖。\n";

    public static List<Double> returnStandList(){
        return Arrays.asList(44d, 52.4d);
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

    public static Map<String,?> calLevel(Double muscle) {
        return SplitUtil.levelInfo(returnStandList(), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(),returnColorList(), desc, muscle);
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "肌肉率不足会导致代谢下降，易发“隐性肥胖”。",
                "肌肉率处于健康范围，体成分比例良好。",
                "肌肉率优秀，基础代谢水平较高。"

        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "采用渐进超负荷训练：每周3次全身力量训练，重点大肌群（腿/背/胸）。",
                "力量训练（每周3次）与有氧运动（每周2次）交替安排。",
                "力量训练（深蹲、硬拉、俯卧撑、引体向上）和有氧运动相结合。"

        );
    }
    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "每日蛋白质摄入≥1.8g/kg体重，训练后加餐（蛋白粉+香蕉）。",
                "蛋白质摄入1.2-1.6g/kg体重，碳水运动日适量增加。",
                "保持碳水和优质蛋白质摄入平衡，保证每日营养稳定。"

        );
    }


}
