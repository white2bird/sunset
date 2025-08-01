package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 8.骨骼肌率
 */
public class SkeletalMusclePercentageCal {

    public static String desc = "分布于躯干、四肢的随意肌，增加肌肉可提升基础代谢。\n";

    public static Map<String, Object> calLevel(Double skeletalMusclePercentage){
        List<Double> list = returnStandList();
        List<String> nameList = returnStandListName();
        return SplitUtil.levelInfo(list, nameList, returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(),returnColorList(), desc, skeletalMusclePercentage);
    }


    public static List<String> returnColorList(){
        return Arrays.asList(
                "11B6EB",
                "5BCC70",
                "F9AB3E"
        );
    }
    public static List<Double> returnStandList(){
        return Arrays.asList(25d, 30d);
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("不足", "标准", "优");
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "骨骼肌不足会降低代谢率，增加脂肪堆积风险。",
                "骨骼肌量达标，有助于维持代谢健康。",
                "骨骼肌率优秀，属于易燃脂体质。"

        );
    }
    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "重点进行复合力量训练（深蹲、硬拉、卧推），每周3-4次，每组8-12次。",
                "维持力量训练（每周2-3次），结合有氧运动保持心肺功能。",
                "力量训练（深蹲、硬拉、俯卧撑、引体向上）和有氧运动相结合。"

        );
    }
    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "增加优质蛋白摄入（1.6-2.0g/kg体重），训练后30分钟内补充乳清蛋白+快吸收碳水。",
                "每日均衡摄入蛋白质（鸡蛋、瘦肉）和碳水（燕麦、糙米）。",
                "保持碳水和优质蛋白质摄入平衡，保证每日营养稳定。"

        );
    }
}
