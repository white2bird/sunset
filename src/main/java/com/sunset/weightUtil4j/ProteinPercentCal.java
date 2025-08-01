package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 15 蛋白质率
 */
public class ProteinPercentCal {

    public static String desc = "蛋白质是是肌体细胞的重要组成部分，是人体组织更新和修补的主要原料和营养物质。蛋白质在体内不能贮存，多了肌体无法吸收不会导致过高(健美运动员除外)。过低，就是营养不良，会导致体重下降、肌肉萎缩、贫血、免疫力下降等。\n";

    public static List<Double> returnStandList(){
        return Arrays.asList(14D, 18D);
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

    public static Map<String,?> calLevel(double proteinMassPercent) {
        return SplitUtil.levelInfo(returnStandList(), returnStandListName(), returnAnalyzeList() , returnSportAdvice(), returnEatAdvice(),returnColorList(), desc, proteinMassPercent);
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "蛋白质率不足影响组织修复",
                "蛋白质率正常",
                "蛋白质率优秀"


        );
    }
    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "力量训练促进蛋白质利用",
                "保持当前运动量",
                "支持高强度训练"


        );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "增加乳清蛋白补充剂",
                "动植物蛋白均衡摄入",
                "训练后及时补充蛋白质"
        );
    }
}
