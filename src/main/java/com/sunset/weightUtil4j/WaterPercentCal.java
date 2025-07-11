package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 12 水分
 */
public class WaterPercentCal {

    public static String desc = "身体水分有着输送营养成分，回收废物，保持体温等重要技能。水分率男性比女性高，随着年龄增加有减少倾向。体脂多的人水分少。一天之中因水分的摄取和身体活动以及身体状况不好等多少会有点变动。\n";

    public static List<Double> returnStandList(boolean male){
        if(male){
            return Arrays.asList(50D, 65D);
        }
        return Arrays.asList(45D, 60D);
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("不足", "标准", "优");
    }

    public static Map<String,?> calLevel(Double bodyWaterPercentage,  boolean male) {
        return SplitUtil.levelInfo(returnStandList(male), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), desc, bodyWaterPercentage);
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "水分不足可能引发疲劳和代谢紊乱。",
                "水分含量标准，代谢循环良好。",
                "身体水分代谢效率优秀。"

        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "运动前后称重补水（每减1kg体重补1.5L水），避免高温下长时间运动。",
                "每天至少6000步，保持有氧运动；及时补水。",
                "可进行长时间耐力运动（马拉松、骑行），注意补充含钠饮水。"

                );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "每日饮水≥2.5L，增加高水分食物（黄瓜、西瓜）；限制咖啡因饮料。",
                "少量多次饮水（每次200ml），运动饮用电解质水。",
                "每日饮水3L+，增加富含电解质食物（香蕉、菠菜）。"

        );
    }
}
