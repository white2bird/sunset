package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StandWeightCal {

    public static String desc = "体重是身体形态的重要指标,也是与人体健康状况相关的重要参数,过高的体重经常与冠心病、高血压、糖尿病等某些现代文明病的发病密切相关,而过低的体重则可能提示营养不良及其他某些疾病的存在。标准体重是测定个人是否肥胖的标准之一，采用国际常用计算方式，根据身高测算得出。\n";
    public static List<Double> returnStandList(Double standWeight){
        return Arrays.asList(standWeight-5, standWeight +5);
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("不足", "标准", "优");
    }

    public static Map<String,?> calLevel(Double standWeight, Double weight) {
        Map<String, Object> stringObjectMap = SplitUtil.levelInfo(returnStandList(standWeight), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), returnColorList(), desc, weight);
        stringObjectMap.put("name", "标准体重");
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

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "低于标准体重",
                "达到标准体重",
                "超过标准体重"

        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "力量训练增肌为主",
                "综合训练维持",
                "有氧运动为主创造热量消耗"

        );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "增加优质蛋白和复合碳水",
                "保持均衡营养摄入",
                "适量减少主食摄入量"

        );
    }
}
