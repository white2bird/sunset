package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * bmi计算
 */
public class BMiCal {


    public static String desc = "BMI指数(〈即身体质量指数，简称体质指数又称体重，英文为Body Mass Index，简称BMI)，是用体重公斤数除以身高米数平方得出的数字，是目前国际上常用的衡量人体胖瘦程度以及是否健康的一个标准。\n";

    public static Map<String,?> calLevel(Double bmi){
        List<Double> list = returnStandList();
        List<String> nameList = returnStandListName();
        List<String> analList = returnAnalyzeList();
        List<String> sportList = returnSportAdvice();
        List<String> eatList = returnEatAdvice();
        return SplitUtil.levelInfo(list, nameList, analList, sportList, eatList, returnColorList(), desc, bmi);
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
     * @return
     */
    public static List<Double> returnStandList(){
        List<Double> list = Arrays.asList(18.5D, 20.5D, 23D, 25.5, 29.9);
        return list;
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("消瘦", "偏瘦", "标准", "偏胖", "肥胖", "重度");
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "BMI过低可能反映肌肉量不足或营养缺乏。",
                "BMI接近标准下限，建议优化体成分比例。",
                "BMI值理想，反映体重与身高比例协调。",
                "BMI偏高，需警惕内脏脂肪堆积。",
                "BMI进入肥胖区间，疾病风险显著升高。",
                "重度肥胖需医疗干预，可能伴随睡眠呼吸暂停等并发症。"

        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "重点进行抗阻训练（深蹲、卧推）增肌，辅以轻度有氧运动。",
                "有氧运动（慢跑）与力量训练（俯卧撑）按3:2比例搭配。",
                "保持多样化运动：有氧（跳绳）、力量（引体向上）、柔韧（拉伸）结合。",
                "以中等强度有氧为主（快走、骑行、慢跑），每周4-5次；加入无氧运动（哑铃、跳蹲）。",
                "从低强度有氧开始（椭圆机、固定单车），逐步延长运动时间至60分钟/次。",
                "必须在医生指导下运动，首选非承重运动（游泳、瑜伽）。"

        );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "增加热量摄入，优先选择高营养密度食物（牛油果、三文鱼）。",
                "适量增加健康脂肪摄入（亚麻籽、橄榄油），蛋白质摄入量≥1.0g/kg体重。",
                "遵循“膳食宝塔”原则，每日蔬果摄入≥500g。",
                "控制额外热量摄入，主食增加杂粮杂豆（膳食纤维），搭配低脂高蛋白食物与蔬菜水果。",
                "采用低升糖指数（GI）饮食，戒除含糖饮料，晚餐碳水减半。",
                "需专业营养支持，可能需处方减重药物或手术介入。"
                );
    }

}
