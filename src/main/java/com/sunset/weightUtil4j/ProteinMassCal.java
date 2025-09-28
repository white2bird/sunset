package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 14 蛋白质质量
 */
public class ProteinMassCal {

    public static String desc = "蛋白质是组成人体细胞、组织的重要成分，约占人体全部质量的18%。机体所有重要的组成部门都需要蛋白质的参与，它是生命活动的主要承担者。蛋白质大量包含在肌肉细胞内，是反映被检测者营养状态，身体发育和健康程度的主要因素。\n";

    public static List<Double> returnStandList(Double weight){
        List<Double> list = Arrays.asList(14D, 18D);
        for(int i = 0; i < list.size(); i++){
            double v = list.get(i) * weight * 0.01;
            BigDecimal bd = new BigDecimal(v);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            list.set(i, bd.doubleValue());
        }

        return list;
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("不足", "标准", "优");
    }

    public static Map<String,?> calLevel(double proteinMassPercent, double weight) {
        Map<String, Object> stringObjectMap = SplitUtil.levelInfo(returnStandList(weight), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), returnColorList(), desc, proteinMassPercent);
        stringObjectMap.put("name", "蛋白质含量");
        stringObjectMap.put("unit", "公斤");
        return stringObjectMap;
    }

    public static List<String> returnColorList(){
        return Arrays.asList(
                "11B6EB",
                "5BCC70",
                "F9AB3E"
        );
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "蛋白质缺乏可能导致代谢下降和免疫力减退。",
                "蛋白质水平满足身体需求。",
                "蛋白质储备充足，利于组织修复和肌肉合成。"

        );
    }
    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "每天行走6000步，每周150分钟中等强度有氧。",
                "常规运动计划可维持，注意训练后蛋白质补充。",
                "可增加训练强度，蛋白质支持恢复能力。"

        );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "增加鸡蛋、鱼、牛肉、牛奶、大豆等优质蛋白，避免过多脂肪。",
                "每日蛋白质摄入0.8-1.0g/kg体重，动植物蛋白均衡。",
                "维持当前高蛋白饮食，注意选择低脂来源（鸡胸肉、虾仁）。"

        );
    }
}
