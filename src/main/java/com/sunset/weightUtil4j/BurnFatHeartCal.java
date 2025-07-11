package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BurnFatHeartCal {

    public static String desc = "燃脂心率是指进行有氧运动时，身体消耗脂肪的最佳心率范围。在您的燃脂心率范围内进行有氧运动，可以有效地燃烧脂肪，达到减肥的目的。\n";

    public static List<Double> returnStandList(){
        return Arrays.asList(120D, 140D);
    }



    public static List<String> returnStandListName(){
        return Arrays.asList(
                " ",
                "标准",
                "高"

        );

    }

    public static List<String> returnAnalyzeList(Integer value){
        String format = String.format("您的燃脂心率区间为%s-%s次/分钟，在此范围运动脂肪供能比例最高。", value, value + 30);

        return Arrays.asList(
                format
        );

    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "选择可持续30分钟以上的中低强度运动（快走、慢跑、椭圆机），保持心率在区间内。\n"


        );

    }
    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "运动前1小时补充低GI碳水（燕麦、苹果），避免运动中血糖波动。\n"


        );

    }

    public static void main(String[] args) {
        String format = String.format("您的燃脂心率区间为%s-%s次/分钟，在此范围运动脂肪供能比例最高。", 1, 1 + 30);
        System.out.println(format);
    }

    public static Map<String,?> calLevel(Double heartRate) {
        return SplitUtil.levelInfo(returnStandList(),returnStandListName(), returnAnalyzeList(heartRate.intValue()), returnSportAdvice(), returnEatAdvice(), desc, 0D);
    }
}
