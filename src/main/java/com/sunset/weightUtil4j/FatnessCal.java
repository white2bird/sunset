package com.sunset.weightUtil4j;

import java.util.Arrays;
import java.util.List;

/**
 * 3 肥胖度计算 有负数是百分比吗
 */
public class FatnessCal{

    /**
     * 返回bmi对应的标准数组列表
     * @return
     */
    public static List<Double> returnStandList(){
        return Arrays.asList(-20.0D, -10D, 10D, 20D, 50D);
    }

    public static List<String> returnStandListName(){
        return Arrays.asList("消瘦", "偏瘦", "标准", "偏胖", "肥胖", "重度");
    }
}
