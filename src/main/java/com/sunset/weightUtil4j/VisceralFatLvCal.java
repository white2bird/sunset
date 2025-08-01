package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 6内脏脂肪率
 */
public class VisceralFatLvCal {

    public static String desc = "从医学上来讲，进行腹部CT拍片得出的内脏脂肪实测值超过100cm²时，容易引起糖尿病、高 血脂、高血压等各种不良生活习惯造成的基本。等级10即相当于100cm²的内脏脂。\n";


    public static Map<String, Object> calLevel(Double visceralFatLevel){
        return SplitUtil.levelInfo(returnStandList(), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(),returnColorList(), desc, visceralFatLevel);
    }

    public static List<String> returnColorList(){
        return Arrays.asList(
                "11B6EB",
                "5BCC70",
                "F9AB3E"
        );
    }

    public static List<Double> returnStandList() {
        return Arrays.asList(9D, 14D);
    }

    public static List<String> returnStandListName() {
        return Arrays.asList("不足", "标准", "优");
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "内脏脂肪严重超标",
                "内脏脂肪水平健康，代谢疾病风险低。",
                "内脏脂肪偏高，需警惕脂肪肝和胰岛素抵抗。",
                "内脏脂肪严重超标，亟需减脂以避免器官损伤。"
        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "每日45分钟以上有氧+HIIT",
                "每周3次力量训练（深蹲、硬拉、俯卧撑、引体向上）和有氧运动结合，辅以拉伸。",
                "增加有氧运动时长（60分钟/次），加入核心训练（平板支撑）；每周运动5-6天。",
                "每日45分钟以上有氧运动（游泳、爬坡走），配合高强度间歇训练（HIIT）每周3次。"

        );
    }

    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "地中海饮食模式：蔬菜+鱼类+橄榄油",
                "保证优质蛋白质摄入，补充维生素D/C/E（深海鱼、柑橘、坚果）。",
                "严格限制反式脂肪（油炸食品），增加可溶性纤维（燕麦、苹果）和单不饱和脂肪（橄榄油、牛油果）。",
                "采用地中海饮食模式：蔬菜为主+鱼类蛋白+橄榄油，戒除酒精和添加糖。"

        );
    }
}
