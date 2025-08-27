package com.sunset.weightUtil4j;

import com.sunset.utils.SplitUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 11 骨量
 */
public class BoneCal {

    public static String desc = "骨量就是矿物质在骨骼中所含有的量，也就是指钙质等的量。正确的说法是“骨盐量”，骨骼是支撑身体的重要支架，掌握自己的骨量，注意补充流失的钙质，加强骨骼的密度，是必须长期坚持的健康之道。\n";

    public static List<Double> returnStandList(boolean male){
        if( male){
            return Arrays.asList(2.5d, 4.0d);
        }
        return Arrays.asList(1.8d, 3.2d);
    }

    public static List<String> returnColorList(){
        return Arrays.asList(
                "11B6EB",
                "5BCC70",
                "F9AB3E"
        );
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("偏低", "标准", "偏高");
    }

    public static Map<String,?> calLevel(Double bone, boolean male){
        Map<String, Object> stringObjectMap = SplitUtil.levelInfo(returnStandList(male), returnStandListName(), returnAnalyzeList(), returnSportAdvice(), returnEatAdvice(), returnColorList(), desc, bone);
        stringObjectMap.put("name", "骨量");
        stringObjectMap.put("unit", "公斤");
        return stringObjectMap;
    }

    public static List<String> returnAnalyzeList(){
        return Arrays.asList(
                "骨量偏低增加骨质疏松风险。",
                "骨量处于健康范围。",
                "骨量偏高需排除病理因素（如甲状旁腺功能亢进）。"

        );
    }

    public static List<String> returnSportAdvice(){
        return Arrays.asList(
                "承重运动（跳绳、跑步）结合抗阻训练（深蹲），每周3-4次。",
                "维持当前运动习惯，加入跳跃类动作（开合跳）。",
                "避免高冲击运动，选择游泳、瑜伽等低风险项目。"
        );
    }
    public static List<String> returnEatAdvice(){
        return Arrays.asList(
                "每日钙摄入1000mg（牛奶300ml+豆腐200g+芝麻），补充维生素D（日晒20分钟/鱼肝油）。",
                "每日钙摄入800mg，避免过量咖啡因（<300mg/日）。",
                "平衡钙磷镁摄入，避免钙补充剂过量（＞2500mg/日）。"

        );
    }
}
