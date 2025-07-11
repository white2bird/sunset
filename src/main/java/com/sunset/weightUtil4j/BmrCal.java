package com.sunset.weightUtil4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2 基础代谢计算 不需要
 */
public class BmrCal {

    public static String desc = "基础代谢量是指为维持呼吸、心脏跳动和保持体温等基本生命活动而消耗的最低能量值，一般占一天总消耗量的70%左右。基础代谢越高的人，属于脂肪易燃烧的体质，也就不容易胖，相反的，基础代谢越低的人，属于脂肪难燃烧的体质，也就越容易胖。\n";

    public static List<Double> returnStandList(){
        return Arrays.asList(1599.7d);
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("", "");
    }

    public static Map<String,?> calLevel(Double value){

        Map<String, Object> result = new HashMap<>();
        result.put("value", value);
        result.put("standDataList",  null);
        result.put("standDataNameList", null);
        result.put("level", null);
        result.put("levelName", "");
        result.put("analyze", null);
        result.put("sportAdvice", null);
        result.put("eatAdvice", null);
        result.put("desc", desc);
        return result;
    }


}
