package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 5 内脏脂肪等级
 */
public class VisceralFatLevelCal {

    public static String desc = "内脏脂肪与皮下脂肪不同，它围绕着人的脏器起着支撑、稳定和保护的作用。内脏脂肪过多,会增加心脏病、糖尿病、脂肪肝、高血压、高脂血症等疾病的风险，进而增加中风和心肌梗死等心脑血管事件的发生率。因此，内脏型肥胖与高血糖、高血脂、高血压，被称为“死亡四重奏”。\n";

    public static Map<String, Object> calLevel(Double level){
        Map<String, Object> stringObjectMap = SplitUtil.levelInfo(returnStandList(), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), returnColorList(), desc, level);
        stringObjectMap.put("name", "内脏脂肪等级");
        stringObjectMap.put("unit", "");
//        stringObjectMap.put("value", level);
        return stringObjectMap;
    }

    public static List<Double> returnStandList() {
        return Arrays.asList(9D, 14D);
    }

    public static List<String> returnColorList(){
        return Arrays.asList(
                "11B6EB",
                "5BCC70",
                "F9AB3E"
        );
    }

    public static List<String> returnStandListName() {
        return Arrays.asList("标准", "偏高", "过高");
    }

    public static List<String> returnAnalyzeList() {
        return Arrays.asList(
                "内脏脂肪水平健康",
                "内脏脂肪偏高需警惕脂肪肝",
                "内脏脂肪严重超标"
        );
    }

    public static List<String> returnSportAdvice() {
        return Arrays.asList(
                "每周3次力量+有氧结合",
                "增加有氧时长（60分钟/次）+核心训练",
                "每日45分钟以上有氧+HIIT"
        );
    }

    public static List<String> returnEatAdvice() {
        return Arrays.asList(
                "保证优质蛋白，补充维生素D/C/E",
                "严格限制反式脂肪，增加可溶性纤维",
                "地中海饮食模式：蔬菜+鱼类+橄榄油"
        );
    }

}
