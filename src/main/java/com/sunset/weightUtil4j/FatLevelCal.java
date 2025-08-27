package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FatLevelCal {

    public static String desc = "脂肪量是指人体脂肪的总量，包括皮下脂肪与内脏脂肪。脂肪是人体重要的储能组织，是有氧运动的主要能量来源，同时具有保暖、隔热和保护内脏器官、参与激素合成等作用。脂肪量维持在标准值范围内，是保证身体健康的重要前提。\n";

    public static List<Double> returnStandList(){
        return Arrays.asList(18.5d, 24d, 28d, 35D, 40D);
    }

    public static List<String> returnColorList(){
        return Arrays.asList(
                "AEDBFD",
                "11B6EB",
                "5BCC70",
                "F9AB3E",
                "F84F2E",
                "D53010"
        );
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("偏轻", "标准", "超重", "肥胖I级", "肥胖II级", "肥胖III级");
    }

    public static Map<String,?> calLevel(Double bone){
        Map<String, Object> stringObjectMap = SplitUtil.levelInfo(returnStandList(), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), returnColorList(), desc, bone);
        stringObjectMap.put("name", "肥胖等级");
        stringObjectMap.put("unit", "");
        return stringObjectMap;
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "肥胖等级不足提示体重过轻",
                "肥胖等级正常",
                "肥胖等级超标",
                "肥胖等级超标",
                "肥胖等级超标",
                "肥胖等级超标"


        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "轻度有氧+力量训练",
                "保持运动多样性",
                "中高强度有氧为主",
                "中高强度有氧为主",
                "中高强度有氧为主",
                "中高强度有氧为主"

        );
    }
    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "增加热量和营养密度",
                "均衡饮食",
                "控制精制碳水和高脂食物",
                "控制精制碳水和高脂食物",
                "控制精制碳水和高脂食物",
                "控制精制碳水和高脂食物"
        );
    }
}
