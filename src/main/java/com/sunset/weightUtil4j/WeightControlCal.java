package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WeightControlCal {

    public static String desc = "体重控制量是当前体重与理想体重的差值。根据体重控制量，科学合理地对体重进行管理，使体重保持正常可以有效降低高血压、糖尿病、高脂血症等相关疾病风险，提高健康状况。\n";


    public static List<Double> standList = Arrays.asList(-5D, 5D);

    public static Map<String,?> calLevel(Double bone){
        return SplitUtil.levelInfo(returnStandList(),returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(),returnColorList(), desc, bone);
    }

    public static List<String> returnColorList(){
        return Arrays.asList(
                "11B6EB",
                "5BCC70",
                "F9AB3E"
        );
    }

    /**
     * 返回bmi对应的标准数组列表
     * @return
     */
    public static List<Double> returnStandList(){
        return standList;
    }

    public static List<String> returnStandListName(){
        return Arrays.asList(
                "不足",
                "标准",
                "优"

        );
    }

    /**
     *
     * @return
     */
    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "体重控制量不足提示增肌需求",
                "体重控制量合理",
                "体重控制量提示减脂需求"

        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "侧重力量训练，限制有氧时长",
                "综合训练维持",
                "增加有氧频率+HIIT"

        );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "热量盈余300-500kcal/日",
                "宏量营养素平衡：碳水50%/蛋白20%/脂肪30%",
                "创造每日300-500kcal热量缺口"
        );
    }
}
