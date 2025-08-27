package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 13 水含量
 */
public class WaterCal {

    public static String desc = "含水量表示人体内水分的绝对值。水是人体中含量最多的一种物质，是人体的重要组成成分,水分具有参与构成机体各种体液、参与机体代谢、调节体温、润滑机体等重要的生理作用。机体含水量应保持在正常范围内，如果含水量过高，则可能是水肿指征，是机体发生疾病的表现;过低，则可能出现便秘、骨关节炎、肾结石、皮肤干燥等健康问题。含水量还与肌肉量有密切关系，含水量变低可能标志着肌肉量下降、体脂率上升\n";

    public static List<Double> returnStandList(boolean male){
        if(male){
            return Arrays.asList(50D, 65D);
        }
        return Arrays.asList(45D, 60D);
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

    public static Map<String,?> calLevel(Double bodyWaterPercentage, Double water,  boolean male) {
        Map<String, Object> stringObjectMap = SplitUtil.levelInfo(returnStandList(male), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), returnColorList(), desc, bodyWaterPercentage);
        BigDecimal bigDecimal = new BigDecimal(water).setScale(2, RoundingMode.HALF_UP);

        stringObjectMap.put("value", bigDecimal);
        stringObjectMap.put("name", "水含量");
        stringObjectMap.put("unit", "kg");
        return stringObjectMap;
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "水含量不足影响营养输送",
                "水含量正常",
                "水含量充足"
        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "避免高温下运动，注意补水",
                "保持常规运动习惯",
                "可增加运动强度"



        );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "增加高水分食物（黄瓜/番茄）",
                "规律饮水（每2小时200ml）",
                "运动后补充含电解质饮料"

        );
    }
}
