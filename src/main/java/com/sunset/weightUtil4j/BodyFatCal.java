package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 4 体脂率 百分比
 * @author sunset
 * @date 2022/08/05
 **/
public class BodyFatCal {

    public static String desc = "身体脂肪包含皮下脂肪和内脏脂肪，身体脂肪率是指体重中身体脂肪所占的比例。测量脂肪率比单纯的只测量体重更能反映我们身体的脂肪水平(肥胖程度)。体脂肪超出正常范围称之为“肥胖”。\n";

    public static Map<String, Object> calLevel(Double fatPercentage, Boolean male){
        return SplitUtil.levelInfo(returnStandList(male), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), returnColorList(), desc, fatPercentage);
    }

    public static List<String> returnColorList(){
        return Arrays.asList(
                "11B6EB",
                "5BCC70",
                "F9AB3E",
                "F84F2E"
        );
    }

    public static List<Double> returnStandList(boolean male){
        if(male){
            return Arrays.asList(14D, 24D, 29D);
        }
        return Arrays.asList(31D, 31D, 36D);
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("偏瘦", "标准", "偏高", "超高");
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "体脂率过低可能影响激素分泌（尤其女性）。",
                "体脂率在健康区间，反映良好代谢状态。",
                "体脂率超出标准，需预防代谢综合征。",
                "脂肪率超高，已属于肥胖，疾病风险显著增加。"

        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "减少有氧运动频率，专注抗阻训练（每周4次），避免过度消耗。",
                "混合训练：力量（深蹲/卧推）和有氧（慢跑）交替进行。",
                "增加有氧运动频率（每周5次，45分钟/次），加入HIIT（每周2次）。",
                "咨询专业教练，以低强度有氧为主（快走、骑行、慢跑）。"

        );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "增加健康脂肪摄入（鱼油、坚果），保证每日热量盈余300kcal。",
                "均衡摄入宏量营养素，增加Omega-3食物（深海鱼、奇亚籽）。",
                "控制精制碳水（白米面），替换为全谷物；蛋白质占比增至25-30%。",
                "采用低碳水化合物饮食（<100g/日），增加膳食纤维和瘦肉蛋白摄入。"

        );
    }
}
