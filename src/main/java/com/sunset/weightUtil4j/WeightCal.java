package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 1 体重
 */
public class WeightCal {

    public static String desc = "体重是指人体各部分的总重量。它受年龄、性别、种族、遗传及地理环境的影响因此，体重是在不断变化的，在某一个时期内相对保持恒定。";

    public static List<Double> standList = Arrays.asList(18.5D, 22.9D, 24.9D, 29.9, 35D);


    public static Map<String,?> calLevel(Double weight){
        Map<String, Object> stringObjectMap = SplitUtil.levelInfo(returnStandList(weight), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), returnColorList(), desc, weight);
        stringObjectMap.put("name", "体重");
        stringObjectMap.put("unit", "公斤");
        return stringObjectMap;
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
    /**
     * 返回bmi对应的标准数组列表
     * @param height 传入米
     * @return
     */
    public static List<Double> returnStandList(Double height){
        ArrayList<Double> doubles = new ArrayList<>();
        for (Double aDouble : standList) {
            doubles.add(aDouble * height * height*0.0001);
        }
        return doubles;
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("消瘦", "偏瘦", "标准", "偏胖", "肥胖", "重度");
    }

    /**
     *
     * @return
     */
    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "您的体重过轻，可能伴随营养不良风险，需关注营养摄入。",
                "您的体重略低于标准范围，需预防免疫力下降。",
                "恭喜！您的体重处于健康范围，请继续保持。\n",
                "您的身体偏胖，肥胖会增加高血压、糖尿病、脂肪肝等疾病风险。\n",
                "您已进入肥胖范围，亟需干预以降低代谢性疾病风险。\n",
                "重度肥胖对心肺功能造成严重负担，需医疗介入。\n"
        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
               "建议适度力量训练（如哑铃、弹力带）结合低强度有氧运动（如瑜伽），避免过量消耗。",
                "每周3次中等强度有氧运动（如快走、游泳）结合基础力量训练。",
                "维持每周150分钟有氧运动（如骑行、慢跑）和2次力量训练。",
                "以中等强度有氧运动为主（快走、骑行、慢跑），每周4-5次，每次45分钟；加入少量无氧运动（哑铃、跳蹲）。",
                "从低强度有氧开始（如水中步行），逐步增加时长；咨询教练制定个性化方案。",
                "在医生监督下进行运动，首选水中运动或器械训练避免关节损伤。"
        );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "增加优质蛋白（鸡蛋、鱼肉）和复合碳水（燕麦、全麦面包），每日可少量多餐补充坚果类健康脂肪。",
                "主食增加杂粮占比，每日补充乳制品和豆制品，确保蛋白质摄入≥1.2g/kg体重。",
                "均衡摄入蔬果、蛋白质和碳水，控制精制糖和饱和脂肪摄入量。",
                "控制额外热量摄入，主食增加杂粮杂豆（膳食纤维），搭配低脂高蛋白食物（鸡胸肉、鱼类）与蔬菜水果。",
                "严格控制每日热量缺口（300-500kcal），采用高蛋白（30%）、低碳水（40%）、低脂（30%）饮食结构。",
                "需营养师制定极低热量饮食（VLCD），配合维生素和矿物质补充剂。"

        );
    }


}
