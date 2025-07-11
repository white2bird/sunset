package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 17 脂肪重量
 */
public class FatMassCal {
    public static String desc = "脂肪量是指人体脂肪的总量，包括皮下脂肪与内脏脂肪。脂肪是人体重要的储能组织，是有氧运动的主要能量来源，同时具有保暖、隔热和保护内脏器官、参与激素合成等作用。脂肪量维持在标准值范围内，是保证身体健康的重要前提。\n";

    public static List<Double> returnStandList(){
        return Arrays.asList(5.0d, 10.1d);
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("偏低", "标准", "偏高");
    }

    public static Map<String,?> calLevel(Double bone){
        return SplitUtil.levelInfo(returnStandList(),returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), desc, bone);
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "脂肪重量不足影响生理功能",
                "脂肪重量在健康范围",
                "脂肪重量超标"


        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "减少有氧运动频率",
                "保持当前运动习惯",
                "增加有氧运动时长"
        );
    }
    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "增加健康脂肪（牛油果/坚果）",
                "脂肪摄入占比25-30%",
                "控制总热量，高纤维食物增饱腹感"

        );
    }
}
