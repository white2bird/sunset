package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 肥胖度=(实际体重-理想体重)/理想体重*100%
 */
public class FatDegreeCal {
    public static String desc = "是指肥胖的程度。表现实际体重与理想体重的差距。肥胖度是判定肥胖症的一个指标。";


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
    public static List<Double> returnStandList(){
        return Arrays.asList(-10D,-5D, 15D, 30D, 50D);
    }

    public static List<String> returnStandListName(){
        return Arrays.asList(
                "消瘦",
                "偏瘦",
                "标准",
                "偏胖",
                "肥胖",
                "重度"

        );
    }

    public static Map<String,?> calLevel(double actualWeight, double idealWeight){
        double bone = (actualWeight - idealWeight) / idealWeight * 100;
        Map<String, Object> stringObjectMap = SplitUtil.levelInfo(returnStandList(), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), returnColorList(), desc, bone);
        stringObjectMap.put("name", "肥胖度");
        stringObjectMap.put("unit", "%");
        return stringObjectMap;
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "实际体重远低于理想值，需排查消化吸收问题。",
                "体重略低于理想范围，建议提升肌肉质量。",
                "实际体重与理想体重匹配度良好。",
                "你的理想体重是66.50公斤，当前偏胖会增加疾病风险。",
        "肥胖度超阈值，体脂可能大量堆积在内脏区域。",
        "重度肥胖需紧急干预，器官负荷已达危险水平。"

        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "避免长时间有氧，侧重抗阻训练增肌（每周3次，每次8-10组动作）。",
                "中等强度有氧（跳舞、爬山）结合复合力量训练（硬拉、划船）。",
                "维持当前运动习惯，加入间歇训练（HIIT）提升代谢。",
                "以中等强度有氧为主（快走、骑行、慢跑），加入无氧运动（哑铃、跳蹲）。",
                "有氧运动时长增至60分钟/次，每周5-6次；加入循环训练（CIRCUIT）。",
                "医疗监护下的运动康复，水中运动为主。"

        );
    }
    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "增加易消化高蛋白食物（蒸蛋羹、豆腐），每日加餐2-3次坚果/酸奶。",
                "增加优质碳水（红薯、藜麦）和蛋白质（乳清蛋白），每日热量盈余200-300kcal。",
                "保持蛋白质摄入1.2-1.6g/kg体重，脂肪占比20-30%。",
        "控制额外热量摄入，主食增加杂粮杂豆（膳食纤维），搭配低脂高蛋白食物与蔬菜水果。",
        "采用高纤维饮食（每日≥30g），严格控制饱和脂肪（<7%总热量）。",
        "极低热量饮食（800kcal/日）需在医院执行，配合全面营养监测。"


        );
    }
}
