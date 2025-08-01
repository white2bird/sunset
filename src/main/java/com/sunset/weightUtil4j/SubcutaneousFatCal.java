package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 18 皮下脂肪
 */
public class SubcutaneousFatCal {

    public static String desc = "皮下脂肪是贮存于皮下的脂肪组织，在真皮层以下，筋膜层以上，人体的脂肪大约有2/3贮存在皮下组织。\n";


    public static List<Double> returnStandList(boolean male){
        if(male){
            return Arrays.asList(12D, 20D, 25D);
        }
        return Arrays.asList(20D, 30D, 35D);
    }
    public static List<String> returnStandListName(){
        return Arrays.asList("偏低", "标准", "高");
    }
    public static List<String> returnColorList(){
        return Arrays.asList(
                "11B6EB",
                "5BCC70",
                "F9AB3E"
        );
    }

    public static Map<String,?> calLevel(Double subcutaneousFat, Boolean  male) {
        return SplitUtil.levelInfo(returnStandList(male), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(),returnColorList(), desc, subcutaneousFat);
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "皮下脂肪不足可能影响体温调节和激素分泌。",
                "皮下脂肪在健康区间，提供必要保护。",
                "皮下脂肪堆积影响体形和运动能力。",
                "皮下脂肪严重超标，需系统减脂。"

        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "避免长时间有氧，以抗阻训练为主（每周4次）。",
                "常规运动计划维持，注意保暖。",
                "增加有氧运动时长（45-60分钟），加入循环训练（8-10个动作连续进行）。",
                "咨询专业教练，以低强度有氧为主（快走、骑行、慢跑）。"

        );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "增加单不饱和脂肪（橄榄油、牛油果），每日热量摄入增加200-300kcal。",
                "脂肪摄入占比25-30%，以不饱和脂肪为主。",
                "控制总热量，用高纤维食物（西兰花、奇亚籽）增加饱腹感。",
                "日常饮食以蔬菜、谷类、优质蛋白为主，严格限制高糖高脂食物和酒精。"

        );
    }
}
