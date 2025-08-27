package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 16 去脂重量
 */
public class FatFreeCal {

    public static String desc = "去脂体重又称瘦体重，是指除脂肪以外身体其他成分的重量，由身体细胞重量、细胞外水分和去脂的固体部分组成，其主要成分是骨骼与肌肉。去脂体重与身体运动能力正相关，去脂体重占总体重的比例越大，运动能力相对越强，运动员通常都拥有较大的去脂体重。\n";

    public static List<Double> returnStandList(){
        return Arrays.asList(61.2d, 68.9d);
    }

    public static List<String> returnColorList(){
        return Arrays.asList(
                "11B6EB",
                "5BCC70",
                "F9AB3E"
        );
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("偏低", "标准", "偏高");
    }

    public static Map<String,?> calLevel(Double bone){
        Map<String, Object> stringObjectMap = SplitUtil.levelInfo(returnStandList(), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), returnColorList(), desc, bone);
        stringObjectMap.put("name", "骨量");
        stringObjectMap.put("unit", "公斤");
        return stringObjectMap;
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "瘦体重不足反映肌肉量偏低。",
                "瘦体重与身高年龄匹配。",
                "瘦体重水平优秀，代谢率高。"


        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "重点进行抗阻训练（6-12RM负荷），每周3次全身训练。",
                "混合训练模式：力量（4组×8-10次）与有氧（心率120-150bpm）结合。",
                "可进行专项运动表现训练（爆发力/耐力）。"

        );
    }
    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "每日蛋白质摄入≥1.8g/kg体重，训练后补充必需氨基酸（BCAA）。",
                "蛋白质1.2-1.6g/kg体重，碳水4-6g/kg体重（根据活动量调整）。",
                "增加碳水摄入支持高强度训练（运动前1-2小时补充）。"


        );
    }
}
